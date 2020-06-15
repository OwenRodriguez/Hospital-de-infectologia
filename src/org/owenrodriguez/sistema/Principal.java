
package org.owenrodriguez.sistema;

import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.owenrodriguez.controller.AreaController;
import org.owenrodriguez.controller.CargoController;
import org.owenrodriguez.controller.ContactoUrgenciaController;
import org.owenrodriguez.controller.EspecialidadController;
import org.owenrodriguez.controller.HorarioController;
import org.owenrodriguez.controller.LoginController;
import org.owenrodriguez.controller.MedicoController;
import org.owenrodriguez.controller.MedicoEspecialidadController;
import org.owenrodriguez.controller.MedicosRabiController;
import org.owenrodriguez.controller.MenuPrincipalController;
import org.owenrodriguez.controller.PacienteController;
import org.owenrodriguez.controller.ProgramadorController;
import org.owenrodriguez.controller.RegistrarUsuariosController;
import org.owenrodriguez.controller.ResponsableTurnoController;
import org.owenrodriguez.controller.TelefonoMedicosController;
import org.owenrodriguez.controller.TurnoController;


public class Principal extends Application {
    private final String PAQUETE_VISTA = "/org/owenrodriguez/view/";
    private Stage escenarioPrincipal;
    private Scene escena;   
    
    
    @Override
    public void start(Stage escenarioPrincipal) {
     this.escenarioPrincipal = escenarioPrincipal;
     escenarioPrincipal.setTitle("Hospital de infectologia");
     menuPrincipal ();
     escenarioPrincipal.sizeToScene();
     escenarioPrincipal.show();
        
    }
    
    public void casa (Stage segundoEscenario){
        Prueba owen = new Prueba();
        Scene scena = new Scene (owen,100,100);
        segundoEscenario.setScene(scena);
        segundoEscenario.show();
    }
    

    public void menuPrincipal (){
        try{
            MenuPrincipalController menuPrincipal = (MenuPrincipalController)cambiarEscena("MenuPrincipalView.fxml" ,720 ,539);
            menuPrincipal.setEscenarioPrincipal(this);
        }catch(Exception e){
        
            e.printStackTrace();
        }
    }
    
    public void menuRegistrarUsuarios(){
    try{
    RegistrarUsuariosController registrarUsuariosController = (RegistrarUsuariosController) cambiarEscena("RegistrarUsuariosView.fxml",485,400); 
    registrarUsuariosController.setEscenarioPrincipal(this);
    }catch (Exception e){
    e.printStackTrace();
    }
    }
    
        public void menuLogin(){
    try{
        LoginController loginController = (LoginController) cambiarEscena ("LoginView.fxml" ,269,226 );
        loginController.setEscenarioPrincipal(this);
    }catch(Exception e){
    e.printStackTrace();
    }
    }
     
   public void ventanaMedicos(){
       try{
           MedicoController medicoController = (MedicoController) cambiarEscena("MedicosView.fxml",700, 422);
           medicoController.setEscenarioPrincipal(this);
       }catch(Exception e){
           e.printStackTrace();
       }
   }
   
   public void ventanaProgramador(){
      try{
          ProgramadorController programadorController =(ProgramadorController)cambiarEscena ("ProgramadorView.fxml",600,350);
          programadorController.setEscenarioPrincipal(this);
         } catch(Exception e)  {
             e.printStackTrace();
             }
     }
    
   public void ventanaTelefonoMedicos(){
      try {
            TelefonoMedicosController telefonoMedicosController  = (TelefonoMedicosController) cambiarEscena ("TelefonoMedicosView.fxml", 600, 372);
            telefonoMedicosController.setEscenarioPrincipal(this);
      } catch (Exception e){
      e.printStackTrace();
           } 
     }

   public void ventanaPacientes(){
     try{
         PacienteController pacienteController = (PacienteController) cambiarEscena("PacientesView.fxml",600, 400);
         pacienteController.setEscenarioPrincipal(this);
         }catch (Exception e){
         e.printStackTrace();
         }   
     }    
   
   public void ventanaCargos(){
     try{
         CargoController cargoController = (CargoController) cambiarEscena("MenuCargosView.fxml",452,386);
         cargoController.setEscenarioPrincipal (this);
         }catch (Exception e){
         e.printStackTrace();
         }
     }
   
   public void ventanaAreas(){
     try{
     AreaController areaController = (AreaController) cambiarEscena("MenuAreasView.fxml",390,376);
     areaController.setEscenarioPrincipal(this);
     }catch (Exception e){
         e.printStackTrace();
         }
     }
   
    public void ventanaContactoUrgencias(){
     try{
     ContactoUrgenciaController contactoUrgenciaController = (ContactoUrgenciaController) cambiarEscena("MenuContactoUrgencias.fxml",637,422);
     contactoUrgenciaController.setEscenarioPrincipal(this);
     }catch (Exception e){
         e.printStackTrace();
         }
     }
    
    public void ventanaEspecialidad(){
     try{
     EspecialidadController especialidadController = (EspecialidadController) cambiarEscena("MenuEspecialidad.fxml",420,362);
     especialidadController.setEscenarioPrincipal(this);
     }catch (Exception e){
         e.printStackTrace();
         }
     }
    
       public void ventanaResponsableTurno(){
     try{
     ResponsableTurnoController responsableTurnoController = (ResponsableTurnoController) cambiarEscena("ResponsableTurnoView.fxml",714,456);
     responsableTurnoController.setEscenarioPrincipal(this);
     }catch (Exception e){
         e.printStackTrace();
         }
     }
       
       public void ventanaHorario(){
     try{
     HorarioController horarioController = (HorarioController) cambiarEscena("HorariosView.fxml",567,417);
     horarioController.setEscenarioPrincipal(this);
     }catch (Exception e){
         e.printStackTrace();
         }
     }
       
       public void ventanaTurno(){
     try{
     TurnoController turnoController = (TurnoController) cambiarEscena("TurnoView.fxml",630,460);
     turnoController.setEscenarioPrincipal(this);
     }catch (Exception e){
         e.printStackTrace();
         }
     } 
    
      public void ventanaMedicoEspecialidad(){
      try {
          MedicoEspecialidadController medicoEspecialidadController = (MedicoEspecialidadController) cambiarEscena("MenuMedicoEspecialidad.fxml",627,377);
          medicoEspecialidadController.setEscenarioPrincipal(this);
      }catch (Exception e){
          e.printStackTrace();
        }
      }  
      
      public void ventanaMedicosRabi(){
          try {
              MedicosRabiController medicosRabiController = (MedicosRabiController) cambiarEscena ("MedicosRabi.fxml",600,400);
              medicosRabiController.setEscenarioPrincipal(this);
          } catch (Exception e){
              e.printStackTrace();
          }
      }

      
    
    public Initializable cambiarEscena(String fxml,int ancho,int alto) throws Exception{
    
        Initializable resultado = null;
        FXMLLoader fxmlCharger = new FXMLLoader();
        InputStream archive = Principal.class.getResourceAsStream(PAQUETE_VISTA + fxml);        
        fxmlCharger.setBuilderFactory(new JavaFXBuilderFactory());
        fxmlCharger.setLocation(Principal.class.getResource(PAQUETE_VISTA + fxml));       
        escena = new Scene((AnchorPane)fxmlCharger.load(archive),ancho,alto);
        escenarioPrincipal.setScene(escena);
        resultado = (Initializable) fxmlCharger.getController();
        return resultado;
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    

    
    
}
