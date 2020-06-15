
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import org.owenrodriguez.bean.Medico;
import org.owenrodriguez.db.Conexion;
import org.owenrodriguez.sistema.Principal;


public class MedicoController implements Initializable{
    private enum operaciones{NUEVO, GUARDAR, ELIMINAR, EDITAR, ACTUALIZAR, CANCELAR, NINGUNO};
    private Principal escenarioPrincipal;
    private operaciones tipoDeOperacion = operaciones.NINGUNO;
    private ObservableList<Medico> listaMedico;
    
    @FXML private TextField txtNombres;
    @FXML private TextField txtApellidos;
    @FXML private TextField txtLicenciaMedica;
    @FXML private TextField txtSexo;       
    @FXML private TextField txtHoraDeEntrada;
    @FXML private TextField txtHoraDeSalida;
    @FXML private TextField txtTurnoMaximo;
    @FXML private TableView tblMedicos;
    @FXML private TableColumn colLicenciaMedica;
    @FXML private TableColumn colNombres;
    @FXML private TableColumn colApellidos;
    @FXML private TableColumn colHoraDeSalida;
    @FXML private TableColumn colSexo;
    @FXML private TableColumn colHoraDeEntrada;
    @FXML private TableColumn colCodigoMedico;       
    @FXML private TableColumn colTurnoMaximo;     
    @FXML private Button btnNuevo;
    @FXML private Button btnEliminar;
    @FXML private Button btnEditar;
    @FXML private Button btnReporte;       
            
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
    }
    
         public void cargarDatos(){     
        tblMedicos.setItems(getMedicos());
        colCodigoMedico.setCellValueFactory(new PropertyValueFactory<Medico, Integer>("codMedico"));
        colLicenciaMedica.setCellValueFactory(new PropertyValueFactory<Medico, Integer>("licenciaMedica"));
        colNombres.setCellValueFactory(new PropertyValueFactory<Medico, String>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<Medico, String>("apellidos"));
        colHoraDeEntrada.setCellValueFactory(new PropertyValueFactory<Medico, String>("horaEntrada"));
        colHoraDeSalida.setCellValueFactory(new PropertyValueFactory<Medico, String>("horaSalida"));
        colSexo.setCellValueFactory(new PropertyValueFactory<Medico, String>("sexo"));    
    }
    
     public void seleccionarElemento(){
         if(!(btnNuevo.getText().equals("Nuevo") || btnNuevo.getText().equals("Guardar"))){
        txtLicenciaMedica.setText(String.valueOf(((Medico)tblMedicos.getSelectionModel().getSelectedItem()).getLicenciaMedica()));
        txtNombres.setText(((Medico)tblMedicos.getSelectionModel().getSelectedItem()).getNombre());
        txtApellidos.setText(((Medico)tblMedicos.getSelectionModel().getSelectedItem()).getApellidos());
        txtSexo.setText(((Medico)tblMedicos.getSelectionModel().getSelectedItem()).getSexo());
        txtHoraDeEntrada.setText(((Medico)tblMedicos.getSelectionModel().getSelectedItem()).getHoraEntrada());
        txtHoraDeSalida.setText(((Medico)tblMedicos.getSelectionModel().getSelectedItem()).getHoraSalida());
         }
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
        
        public Medico buscarMedico(int codigoMedico){
        Medico resultado = null;
        try{
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_BuscarMedico(?)}");
            procedimiento.setInt(1, codigoMedico);
            ResultSet registro = procedimiento.executeQuery();
            while (registro.next()){
            resultado = new Medico (registro.getInt("codMedico"),
                    registro.getInt("licenciaMedica"),
                    registro.getString("nombre"),
                    registro.getString("apellidos"),
                    registro.getString("horaEntrada"),
                    registro.getString("horaSalida"),
                    registro.getInt("turnoMaximo"),
                    registro.getString("sexo"));  
            }
            
           }catch(Exception e){
           e.printStackTrace();
           }
        return resultado;     
    }
              
     public void nuevo(){
     switch (tipoDeOperacion){
         case NINGUNO:
         activarControles();
         btnNuevo.setText("Guardar");
         btnEliminar.setText("Cancelar");
         btnEditar.setDisable(true);
         btnReporte.setDisable(true);
         tipoDeOperacion = operaciones.GUARDAR;
         break;
         
         case GUARDAR:
             guardar();
             desactivarControles();
             limpiarControles();
             btnNuevo.setText("Nuevo");
             btnEliminar.setText("Eliminar");
             btnEditar.setDisable(false);
             btnReporte.setDisable(false);
             tipoDeOperacion = operaciones.NINGUNO;
             cargarDatos();
         }     
     
     }      
     
       public void guardar(){
     Medico registro = new Medico();
     registro.setLicenciaMedica(Integer.parseInt(txtLicenciaMedica.getText()));
     registro.setNombre(txtNombres.getText());
     registro.setApellidos(txtApellidos.getText());
     registro.setHoraEntrada(txtHoraDeEntrada.getText());
     registro.setHoraSalida(txtHoraDeSalida.getText());
     registro.setSexo(txtSexo.getText());
     try{
         PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_addMedicos(?,?,?,?,?,?)}");
         procedimiento.setInt(1, registro.getLicenciaMedica());
         procedimiento.setString(2, registro.getNombre());
         procedimiento.setString(3, registro.getApellidos());
         procedimiento.setString(4, registro.getHoraEntrada());
         procedimiento.setString(5, registro.getHoraSalida());
         procedimiento.setString(6, registro.getSexo());
         procedimiento.execute();
         listaMedico.add(registro);
         
         }catch(Exception e){
             e.printStackTrace();
          }
     
     } 
        
    public void Editar(){
     switch(tipoDeOperacion){
     case NINGUNO:            
        if (tblMedicos.getSelectionModel().getSelectedItem() != null){
        btnEditar.setText("Actualizar");
        btnReporte.setText("Cancelar");
        btnNuevo.setDisable(true);
        btnEliminar.setDisable(true);
        activarControles();
        tipoDeOperacion= operaciones.ACTUALIZAR;
        }else{
        JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento");
        }
        break;
     case ACTUALIZAR:
         actualizar();
         btnEditar.setText("Editar");
         btnReporte.setText("Reporte");
         btnNuevo.setDisable(false);
         btnEliminar.setDisable(false);
         tipoDeOperacion = operaciones.NINGUNO;
         cargarDatos();
         break;
     }
     
    }

    public void actualizar(){
    try{
        PreparedStatement actualizar = Conexion.getInstancia().getConexion().prepareCall("{call sp_updateMedicos(?,?,?,?,?,?,?)}");
        Medico registro = (Medico)tblMedicos.getSelectionModel().getSelectedItem();
        registro.setLicenciaMedica(Integer.parseInt(txtLicenciaMedica.getText()));
        registro.setNombre(txtNombres.getText());
        registro.setApellidos(txtApellidos.getText());
        registro.setHoraEntrada(txtHoraDeEntrada.getText());
        registro.setHoraSalida(txtHoraDeSalida.getText());
        registro.setSexo(txtSexo.getText());
        
        actualizar.setInt(1, registro.getCodMedico());
        actualizar.setInt(2, registro.getLicenciaMedica());
        actualizar.setString(3, registro.getNombre());
        actualizar.setString(4, registro.getApellidos());
        actualizar.setString(5, registro.getHoraEntrada());
        actualizar.setString(6, registro.getHoraSalida());
        actualizar.setString(7, registro.getSexo());
        actualizar.execute();
        
    }catch(Exception e){
    e.printStackTrace();
    }
    }
     
    public void eliminar  (){
    switch (tipoDeOperacion){
        
        case GUARDAR:
            desactivarControles ();
            limpiarControles ();        
            btnNuevo.setText("Nuevo");
            btnEliminar.setText("Eliminar");   
            btnEditar.setDisable(false);
            btnReporte.setDisable(false);
            tipoDeOperacion = operaciones.NUEVO;
        break;
        default :
            if (tblMedicos.getSelectionModel().getSelectedItem()== null){
              JOptionPane.showMessageDialog(null, "ERROR! : Debe seleccionar un elemento");
            }
            if (tblMedicos.getSelectionModel().getSelectedItem()!= null){
            int respuesta = JOptionPane.showConfirmDialog(null,"Seguro que quiere borrar el registro", "eliminar medicos", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (respuesta == JOptionPane.YES_OPTION){
                 try {
                   PreparedStatement eliminar = Conexion.getInstancia().getConexion().prepareCall("{call sp_DeleteMedicos(?)}");
                   eliminar.setInt(1,((Medico)tblMedicos.getSelectionModel().getSelectedItem()).getCodMedico());
                   eliminar.execute();
                   listaMedico.remove(tblMedicos.getSelectionModel().getSelectedIndex());
                    limpiarControles();
             } catch(Exception e){
             e.printStackTrace();
             }             
            }
            }else{
            }   
      }
    }
    
    public void desactivarControles(){
     txtNombres.setEditable(false);
     txtApellidos.setEditable(false);
     txtLicenciaMedica.setEditable(false);
     txtSexo.setEditable(false);
     txtHoraDeEntrada.setEditable(false);
     txtHoraDeSalida.setEditable(false);
     txtTurnoMaximo.setEditable(false);
    }
    
    public void activarControles(){
     txtNombres.setEditable(true);
     txtApellidos.setEditable(true);
     txtLicenciaMedica.setEditable(true);
     txtSexo.setEditable(true);
     txtHoraDeEntrada.setEditable(true);
     txtHoraDeSalida.setEditable(true);
     txtTurnoMaximo.setEditable(false);
    
      }
    
    public void limpiarControles(){
     
     txtNombres.setText("");
     txtApellidos.setText("");
     txtLicenciaMedica.setText("");
     txtSexo.setText("");
     txtHoraDeEntrada.setText("");
     txtHoraDeSalida.setText("");
     txtTurnoMaximo.setText("");
     
    }

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    public void ventanaMedicos (){
        escenarioPrincipal.menuPrincipal();
    }
    
    public void menuPrincipal (){
     escenarioPrincipal.menuPrincipal();
      }
    
    
}
