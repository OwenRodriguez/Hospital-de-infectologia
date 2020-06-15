
package org.owenrodriguez.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import org.owenrodriguez.sistema.Principal;


public class MenuPrincipalController implements Initializable {
    
    private Principal escenarioPrincipal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
    }
    
    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    public void menuRegistrarUsuarios(){
    escenarioPrincipal.menuRegistrarUsuarios();
    }
    
   public void menuLogin(){
    escenarioPrincipal.menuLogin();
   }
    
    public void ventanaMedicos(){
       escenarioPrincipal.ventanaMedicos();
    }
    
    public void  ventanaProgramador (){
     escenarioPrincipal.ventanaProgramador();    
     }
    
    public void ventanaTelefonoMedicos(){
     escenarioPrincipal.ventanaTelefonoMedicos();
     }
    
    public void ventanaPacientes(){
     escenarioPrincipal.ventanaPacientes();
     }
    
    public void ventanaCargos(){
     escenarioPrincipal.ventanaCargos();
      }
    
    public void ventanaAreas(){
     escenarioPrincipal.ventanaAreas();
     }
  
    public void ventanaContactoUrgencias(){
      escenarioPrincipal.ventanaContactoUrgencias();
    }
      public void ventanaEspecialidad(){
      escenarioPrincipal.ventanaEspecialidad();
      }
       
         public void ventanaResponsableTurno(){
      escenarioPrincipal.ventanaResponsableTurno();
      }
         
     public void ventanaHorario(){
       escenarioPrincipal.ventanaHorario();
    }     
     
     public void ventanaTurno(){
       escenarioPrincipal.ventanaTurno();
     }
     
     public void ventanaMedicoEspecialidad(){
       escenarioPrincipal.ventanaMedicoEspecialidad();  
     }
         
     
     public void ventanaMedicosRabi(){
         escenarioPrincipal.ventanaMedicosRabi();
     }
}