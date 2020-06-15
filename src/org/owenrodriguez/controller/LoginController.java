
package org.owenrodriguez.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.owenrodriguez.sistema.Principal;


public class LoginController implements Initializable{
    private Principal escenarioPrincipal;
    private enum operaciones {LOGIN, REGISTER, CANCEL, REPORT, NINGUNO}
    private operaciones tipoDeOperaciones = operaciones.NINGUNO;
    
    @FXML private TextField txtUsuario;
    @FXML private PasswordField pswPassword;
    @FXML private ComboBox cmbTipoDeUsuario;
    @FXML private Button btnLogin;
    @FXML private Button btnRegister;
    @FXML private Button btnCancel;
    @FXML private Button btnReport;

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
