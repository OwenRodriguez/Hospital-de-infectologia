
package org.owenrodriguez.controller;

import java.net.URL;
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
import org.owenrodriguez.bean.RegistrarUsuarios;
import org.owenrodriguez.db.Conexion;
import org.owenrodriguez.sistema.Principal;


public class RegistrarUsuariosController implements Initializable {
 private enum operaciones{NUEVO, GUARDAR, ELIMINAR, EDITAR, ACTUALIZAR, CANCELAR, NINGUNO};
 private operaciones tipoDeOperacion = operaciones.NINGUNO;  
 private Principal escenarioPrincipal;
 private ObservableList <RegistrarUsuarios> listaRegistrarUsuarios; 
 
 @FXML private TextField txtUsuarioLogin;
 @FXML private TextField txtContrasena;
 @FXML private TextField txtFecha;
 @FXML private TextField txtHora;
 @FXML private ComboBox cmbEstado;
 @FXML private TableView tblRegistrarUsuarios;
 @FXML private TableColumn colCodigoUsuario;
 @FXML private TableColumn colUsuarioLogin;
 @FXML private TableColumn colContrasena;
 @FXML private TableColumn colEstado;
 @FXML private TableColumn colFecha;
 @FXML private TableColumn colHora;
 @FXML private Button btnNuevo;
 @FXML private Button btnEliminar;
 @FXML private Button btnReporte;       
         
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
//    public void cargarDatos(){
//    tblRegistrarUsuarios.setItems(getRegistrarUsuarios());
//    }
//    public ObservableList<RegistrarUsuarios> getRegistrarUsuarios(){
//        ArrayList <RegistrarUsuarios> lista = new ArrayList <RegistrarUsuarios>();
//        try{
//            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{}")
//            
//        }catch (Exception e){
//        e.printStackTrace();
//        }
//        return listaRegistrarUsuarios = FXCollections.observableArrayList(lista);
//    }
    
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
