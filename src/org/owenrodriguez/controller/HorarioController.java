
package org.owenrodriguez.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.owenrodriguez.sistema.Principal;


public class HorarioController implements Initializable {
 private Principal escenarioPrincipal;

 @FXML private TextField txtHoraInicio;
 @FXML private TextField txtHoraSalida;
 @FXML private TableView tblHorarios;
 @FXML private TableColumn colCodHorario;
 @FXML private TableColumn colHorarioInicio;
 @FXML private TableColumn colHorarioSalida;
 @FXML private TableColumn colLunes;
 @FXML private TableColumn colMartes;
 @FXML private TableColumn colMiercoles;
 @FXML private TableColumn colJueves;
 @FXML private TableColumn colViernes;
 @FXML private Button btnAgregar;
 @FXML private Button btnEliminar;        
 @FXML private Button btnEditar; 
 @FXML private Button btnReporte;         

         
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
