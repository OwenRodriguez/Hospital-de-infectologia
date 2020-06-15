
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
import org.owenrodriguez.bean.Pacientes;
import org.owenrodriguez.db.Conexion;
import org.owenrodriguez.sistema.Principal;


public class PacienteController implements Initializable{
    private enum operaciones {NUEVO, GUARDAR, ELIMINAR, EDITAR, ACTUALIZAR, CANCELAR, NINGUNO}; 
 private Principal escenarioPrincipal;
 private operaciones tipoDeOperacion = operaciones.NINGUNO;
 private ObservableList <Pacientes> listaPacientes;
 
 @FXML private TextField txtCodigoPaciente;
 @FXML private TextField txtDPI;
 @FXML private TextField txtFechaNacimiento;
 @FXML private TextField txtNombres;
 @FXML private TextField txtApellidos;
 @FXML private TextField txtEdad;
 @FXML private TextField txtDireccion;
 @FXML private TextField txtOcupacion;
 @FXML private TextField txtSexo;
 @FXML private TableView tblPacientes; 
 @FXML private TableColumn colCodPaciente;
 @FXML private TableColumn colDPI;
 @FXML private TableColumn colNombres;
 @FXML private TableColumn colApellidos;
 @FXML private TableColumn colFechaNacimiento;
 @FXML private TableColumn colEdad;
 @FXML private TableColumn colDireccion;
 @FXML private TableColumn colOcupacion;
 @FXML private TableColumn colSexo;
 @FXML private Button btnNuevo;
 @FXML private Button btnEliminar;  
 @FXML private Button btnEditar;
 @FXML private Button btnReporte;
 
 
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
    }
    
    public void seleccionarElemento(){
    txtDPI.setText(((Pacientes)tblPacientes.getSelectionModel().getSelectedItem()).getDpi());
    txtFechaNacimiento.setText(((Pacientes)tblPacientes.getSelectionModel().getSelectedItem()).getFechaNacimiento());
    txtNombres.setText(((Pacientes)tblPacientes.getSelectionModel().getSelectedItem()).getNombre());
    txtApellidos.setText(((Pacientes)tblPacientes.getSelectionModel().getSelectedItem()).getApellidos());
    txtEdad.setText(String.valueOf(((Pacientes)tblPacientes.getSelectionModel().getSelectedItem()).getEdad()));
    txtDireccion.setText(((Pacientes)tblPacientes.getSelectionModel().getSelectedItem()).getDireccion());
    txtOcupacion.setText(((Pacientes)tblPacientes.getSelectionModel().getSelectedItem()).getOcupacion());
    txtSexo.setText(((Pacientes)tblPacientes.getSelectionModel().getSelectedItem()).getSexo());
}
    
     public void cargarDatos(){
    tblPacientes.setItems(getPacientes());
    colCodPaciente.setCellValueFactory(new PropertyValueFactory<Pacientes, Integer>("codPaciente"));
    colDPI.setCellValueFactory(new PropertyValueFactory<Pacientes, String>("dpi"));
    colNombres.setCellValueFactory(new PropertyValueFactory<Pacientes, String>("apellidos"));
    colApellidos.setCellValueFactory(new PropertyValueFactory<Pacientes, String>("nombre"));
    colFechaNacimiento.setCellValueFactory(new PropertyValueFactory<Pacientes, String>("fechaNacimiento"));
    colEdad.setCellValueFactory(new PropertyValueFactory<Pacientes, String>("edad"));
    colDireccion.setCellValueFactory(new PropertyValueFactory<Pacientes, String>("direccion"));
    colOcupacion.setCellValueFactory(new PropertyValueFactory<Pacientes, String>("ocupacion"));
    colSexo.setCellValueFactory(new PropertyValueFactory<Pacientes, String>("sexo"));
    }
    
      public ObservableList<Pacientes> getPacientes(){
        ArrayList<Pacientes> lista= new ArrayList <Pacientes>();
        try{
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call  sp_ListarPacientes()}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()){
            lista.add(new Pacientes(resultado.getInt("codPaciente"),
                                   resultado.getString("dpi"),
                                   resultado.getString("apellidos"),
                                   resultado.getString("nombre"),
                                   resultado.getString("fechaNacimiento"),
                                   resultado.getInt("edad"),
                                   resultado.getString("direccion"),
                                   resultado.getString("ocupacion"),
                                   resultado.getString("sexo")  
            ));
            }
        }catch (Exception e){
        e.printStackTrace();
        }
        return listaPacientes = FXCollections.observableList(lista);
    }
     
    public Pacientes buscarPaciente(int codigoPacientes){
    Pacientes resultado = null;
    try{
        PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_BuscarPacientes(?)}");
        procedimiento.setInt(1, codigoPacientes);
        ResultSet registro = procedimiento.executeQuery();
             while (registro.next()){
              resultado = new Pacientes (registro.getInt("CodPaciente"),
                                         registro.getString("dpi"),
                                         registro.getString("Apellidos"),
                                         registro.getString("Nombre"),
                                         registro.getString("Fecha Nacimiento"),
                                         registro.getInt("Edad"),
                                         registro.getString("Direccion"),
                                         registro.getString("Ocupacion"),
                                         registro.getString("Sexo")
              );
            }
        
    }catch (Exception e){
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
        } 
     
    } 
    
      public void guardar(){
        Pacientes registro = new Pacientes();
        registro.setDpi((txtDPI.getText()));
        registro.setApellidos((txtApellidos.getText()));
        registro.setNombre((txtNombres.getText()));
        registro.setFechaNacimiento((txtFechaNacimiento.getText()));
        registro.setEdad(Integer.parseInt(txtEdad.getText()));
        registro.setDireccion((txtDireccion.getText()));
        registro.setOcupacion((txtOcupacion.getText()));
        registro.setSexo((txtSexo.getText()));
        try{
         PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_addPacientes(?,?,?,?,?,?,?,?)}");
         procedimiento.setString(1, registro.getDpi());
         procedimiento.setString(2, registro.getApellidos());
         procedimiento.setString(3, registro.getNombre());
         procedimiento.setString(4, registro.getFechaNacimiento());
         procedimiento.setInt(5, registro.getEdad());
         procedimiento.setString(6, registro.getDireccion());
         procedimiento.setString(7, registro.getOcupacion());
         procedimiento.setString(8, registro.getSexo());
         procedimiento.execute();
         listaPacientes.add(registro);
        }catch (Exception e){
        e.printStackTrace();
        }
    } 
    
    public void editar(){
    switch(tipoDeOperacion){
        case NINGUNO:
            if (tblPacientes.getSelectionModel().getSelectedItem() != null){
            btnEditar.setText("Actualizar");
            btnReporte.setText("Cancelar");
            btnNuevo.setDisable(true);
            btnEliminar.setDisable(true);
            activarControles();
            tipoDeOperacion = operaciones.ACTUALIZAR;
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
        PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call  sp_updatePacientes(?,?,?,?,?,?,?,?)}");
        Pacientes registro = (Pacientes)tblPacientes.getSelectionModel().getSelectedItem();
        registro.setDpi(txtDPI.getText());
        registro.setApellidos(txtApellidos.getText());
        registro.setNombre(txtNombres.getText());
        registro.setFechaNacimiento(txtFechaNacimiento.getText());
        registro.setEdad(Integer.parseInt(txtEdad.getText()));
        registro.setDireccion(txtDireccion.getText());
        registro.setOcupacion(txtOcupacion.getText());
        registro.setSexo(txtSexo.getText());
        procedimiento.setString(1, registro.getDpi());
        procedimiento.setString(2, registro.getApellidos());
        procedimiento.setString(3, registro.getNombre());
        procedimiento.setString(4, registro.getFechaNacimiento());
        procedimiento.setInt(5, registro.getEdad());
        procedimiento.setString(6, registro.getDireccion());
        procedimiento.setString(7, registro.getOcupacion());
        procedimiento.setString(8, registro.getSexo());
        procedimiento.execute();
        }catch (Exception e){
        e.printStackTrace();
        }
    }
    
    public void eliminar(){
    switch (tipoDeOperacion){
        case GUARDAR:
        desactivarControles();
        limpiarControles();
        btnNuevo.setText("Nuevo");
        btnEliminar.setText("Eliminar");
        btnEditar.setDisable(false);
        btnReporte.setDisable(false);
        tipoDeOperacion = operaciones.NUEVO;
        break;
        default:
            if (tblPacientes.getSelectionModel().getSelectedItem()==null){
                JOptionPane.showMessageDialog(null, "Error: Debe seleccionar un elemento");
            }
            if (tblPacientes.getSelectionModel().getSelectedItem()!= null){
            int respuesta = JOptionPane.showConfirmDialog(null, "Seguro que quiere borrar el registro", "Eliminar pacientes", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (respuesta == JOptionPane.YES_OPTION){
                try{
                    PreparedStatement eliminar = Conexion.getInstancia().getConexion().prepareCall("{call sp_deletePacientes(?)}");
                     eliminar.setInt(1,((Pacientes)tblPacientes.getSelectionModel().getSelectedItem()).getCodPaciente());
                     eliminar.execute();
                      listaPacientes.remove(tblPacientes.getSelectionModel().getSelectedIndex());
                    limpiarControles();
                }catch (Exception e){
                e.printStackTrace();
                }
                }
            }else{
            }
     }
    }

    public void desactivarControles(){
    txtDPI.setEditable(false);
    txtCodigoPaciente.setEditable(false);
    txtFechaNacimiento.setEditable(false);
    txtNombres.setEditable(false);
    txtApellidos.setEditable(false);
    txtEdad.setEditable(false);
    txtDireccion.setEditable(false);
    txtOcupacion.setEditable(false);
    txtSexo.setEditable(false);
    }
    
    public void activarControles(){
    txtDPI.setEditable(true);
    txtCodigoPaciente.setEditable(true);
    txtFechaNacimiento.setEditable(true);
    txtNombres.setEditable(true);
    txtApellidos.setEditable(true);
    txtEdad.setEditable(true);
    txtDireccion.setEditable(true);
    txtOcupacion.setEditable(true);
    txtSexo.setEditable(true);
    }
    
    public void limpiarControles(){
    txtDPI.setText("");
    txtCodigoPaciente.setText("");
    txtFechaNacimiento.setText("");
    txtNombres.setText("");
    txtApellidos.setText("");
    txtEdad.setText("");
    txtDireccion.setText("");
    txtOcupacion.setText("");
    txtSexo.setText("");
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
