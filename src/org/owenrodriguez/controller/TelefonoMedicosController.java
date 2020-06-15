
    package org.owenrodriguez.controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import org.owenrodriguez.bean.Medico;
import org.owenrodriguez.bean.TelefonoMedicos;
import org.owenrodriguez.db.Conexion;
import org.owenrodriguez.sistema.Principal;

    
public class TelefonoMedicosController implements Initializable{
    
    private enum operaciones {NUEVO, GUARDAR, ELIMINAR, EDITAR, ACTUALIZAR, CANCELAR, NINGUNO} 
   private Principal escenarioPrincipal; 
   private operaciones tipoDeOperacion = operaciones.NINGUNO;
   private ObservableList <Medico> listaMedico;
   private ObservableList<TelefonoMedicos> listaTelefonoMedico; 
   
   @FXML private TextField txtTelefonoPersonal;
   @FXML private TextField txtTelefonoTrabajo;
   @FXML private TableView tblTelefonoMedicos;
   @FXML private TableColumn colTelefonoPersonal;
   @FXML private TableColumn colTelefonoTrabajo;
   @FXML private TableColumn colCodigoTelefono;
   @FXML private TableColumn colCodigoMedico;
   @FXML private ComboBox cmbCodigoMedico; 
   @FXML private Button btnNuevo;
   @FXML private Button btnEliminar;
   @FXML private Button btnEditar;
   @FXML private Button btnReporte;
     
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
        cmbCodigoMedico.setItems(getMedicos()); 
    }
    
     public void cargarDatos (){
           tblTelefonoMedicos.setItems(getTelefonoMedicos());
           colCodigoTelefono.setCellValueFactory(new PropertyValueFactory<TelefonoMedicos, Integer>("codTelefonoMedico"));
           colTelefonoPersonal.setCellValueFactory(new PropertyValueFactory<TelefonoMedicos, String>("telefonoPersonal"));
           colTelefonoTrabajo.setCellValueFactory(new PropertyValueFactory<TelefonoMedicos, String>("telefonoTrabajo"));
           colCodigoMedico.setCellValueFactory(new PropertyValueFactory<TelefonoMedicos, Integer>("codMedico"));
        }
    
    public void seleccionarElemento() {
        colTelefonoTrabajo.setCellValueFactory(new PropertyValueFactory<TelefonoMedicos, String>("telefonoTrabajo"));
        colTelefonoTrabajo.setCellValueFactory(new PropertyValueFactory<TelefonoMedicos, String>("telefonoPersonal"));
    }      
    
    public ObservableList<TelefonoMedicos>getTelefonoMedicos(){
    ArrayList<TelefonoMedicos> lista = new ArrayList <TelefonoMedicos>();
    try{
        PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_ListarTelefonosMedicos}");
        ResultSet resultado = procedimiento.executeQuery();
        while(resultado.next()){
         lista.add(new TelefonoMedicos(resultado.getInt("codTelefonoMedico"),
                     resultado.getString("telefonoPersonal"),
                     resultado.getString("telefonoTrabajo"),
                     resultado.getInt("codMedico")));
              }
                    }catch(Exception e){
            e.printStackTrace();    
            }
      return listaTelefonoMedico = FXCollections.observableArrayList (lista);
    }
    
   public ObservableList<Medico> getMedicos(){
        ArrayList <Medico>  lista = new ArrayList <Medico>(); 
        try{
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_ListarMedicos()}"); 
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()){
            lista.add(new Medico(resultado.getInt("codMedico"),
                                 resultado.getInt("licenciaMedica"),
                                 resultado.getString("nombre"),
                                 resultado.getString("apellidos"),
                                 resultado.getString("horaEntrada"),
                                 resultado.getString("horaSalida"),
                                 resultado.getInt("turnoMaximo"),
                                 resultado.getString("sexo")));        
             }
 
         }catch(Exception e){
             e.printStackTrace();   
         }
        
        return listaMedico = FXCollections.observableList(lista);
     }
        
        public void nuevo(){
        
        switch (tipoDeOperacion){
            case NINGUNO:
              activarControles();
                btnNuevo.setText("guardar");
                btnEliminar.setText("Cancelar");
                btnEditar.setDisable(true);
                btnReporte.setDisable(true);     
                limpiarControles();
                tipoDeOperacion = operaciones.GUARDAR;
                break;
                
            case GUARDAR:
                 agregar();
                 desactivarControles();
                 btnNuevo.setText("Nuevo");
                 btnEliminar.setText("Eliminar");
                 btnEditar.setDisable(false);
                 btnReporte.setDisable(false);
                 tipoDeOperacion = operaciones.NINGUNO;
                 cargarDatos();
                 break;
         }
        }
        
    public void agregar(){  
     TelefonoMedicos registro = new TelefonoMedicos();
     registro.setTelefonoPersonal(txtTelefonoPersonal.getText());
     registro.setTelefonoTrabajo(txtTelefonoTrabajo.getText());
     registro.setCodMedico(((Medico)cmbCodigoMedico.getSelectionModel().getSelectedItem()).getCodMedico());
     try{
         PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{ sp_addTelefonosMedico(?,?)}");
         procedimiento.setString(1,registro.getTelefonoPersonal());
         procedimiento.setString(2, registro.getTelefonoTrabajo());
         procedimiento.setInt(3,registro.getCodMedico());
         procedimiento.execute();
         listaTelefonoMedico.add(registro);
     }catch(Exception e){
      e.printStackTrace();
     }
     }    
        
     public void editar(){
        switch(tipoDeOperacion){
            case NINGUNO:
                if(tblTelefonoMedicos.getSelectionModel().getSelectedItem() != null){
                    btnNuevo.setDisable(true);
                    btnEliminar.setDisable(true);
                    btnEditar.setText("Actualizar");
                    btnReporte.setText("Cancelar");
                    activarControles();
                    tipoDeOperacion = operaciones.ACTUALIZAR;
                    }
                else{
                    JOptionPane.showMessageDialog(null,"DEBE SELECCIONAR UN ELEMENTO");
               }
            break;
            
            case ACTUALIZAR:
                actualizar();
                btnNuevo.setDisable(false);
                btnEliminar.setDisable(false);
                btnEditar.setText("Editar");
                btnReporte.setText("Reporte");
                tipoDeOperacion = operaciones.NINGUNO;
                limpiarControles();
                cargarDatos();
        }
    }
     
     public void actualizar(){
        try{
            PreparedStatement actualizar = Conexion.getInstancia().getConexion().prepareCall("{call sp_updateTelefonosMedicos(?,?,?)}");
            
            TelefonoMedicos registro = (TelefonoMedicos)tblTelefonoMedicos.getSelectionModel().getSelectedItem();
            registro.setTelefonoPersonal(txtTelefonoPersonal.getText());
            registro.setTelefonoTrabajo(txtTelefonoTrabajo.getText());
            
            actualizar.setInt(1, registro.getCodTelefonoMedico());
            actualizar.setString(2, registro.getTelefonoPersonal());
            actualizar.setString(3, registro.getTelefonoTrabajo());
            
            actualizar.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    } 
     
        public void eliminar(){
        switch(tipoDeOperacion){
            case GUARDAR:
                agregar();
                    btnReporte.setDisable(false);
                    btnEditar.setDisable(false);
                    btnNuevo.setText("Nuevo");
                    btnEliminar.setText("Eliminar");
                    tipoDeOperacion = operaciones.NINGUNO;
            break;
            default:
                if(tblTelefonoMedicos.getSelectionModel().getSelectedItem() == null){
                    JOptionPane.showMessageDialog(null,"DEBE SELECCIONAR UN ELEMENTO");
                }
                if(tblTelefonoMedicos.getSelectionModel().getSelectedItem() != null){
                    int respuesta = JOptionPane.showConfirmDialog(null,"¿DESEA ELIMINAR ESTE ELEMENTO?","ELIMINAR TELEFONO MEDICO",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                        if(respuesta == JOptionPane.YES_OPTION){
                            try{
                            PreparedStatement eliminar = Conexion.getInstancia().getConexion().prepareCall("{call sp_deleteTelefonosMedicos(?)}");
                            eliminar.setInt(1, ((TelefonoMedicos)tblTelefonoMedicos.getSelectionModel().getSelectedItem()).getCodTelefonoMedico());
                            eliminar.execute();
                            listaTelefonoMedico.remove(tblTelefonoMedicos.getSelectionModel().getSelectedIndex());
                            limpiarControles();
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                    }
                }
        }
      }
    
        
    public void desactivarControles(){
     txtTelefonoPersonal.setEditable(false);
     txtTelefonoTrabajo.setEditable(false);
     cmbCodigoMedico.setDisable(true);       
    }    
        
    public void activarControles (){
        txtTelefonoPersonal.setEditable(true);
        txtTelefonoTrabajo.setEditable(true);
        cmbCodigoMedico.setDisable(false);
    }    

    public void limpiarControles (){
        txtTelefonoPersonal.setText("");
        txtTelefonoTrabajo.setText("");
        cmbCodigoMedico.getSelectionModel().clearSelection();
    }

   
    
    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {   

        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    public void menuPrincipal(){
        escenarioPrincipal.menuPrincipal();
    }
    
    
    
}
