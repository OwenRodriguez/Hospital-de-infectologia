package org.owenrodriguez.sistema;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class Prueba extends StackPane {   
    
     ScrollPane scrollPane = new ScrollPane();
     VBox vBox = new VBox();
    
    public Prueba(){
         setMaxSize(100, 50);
         vBox.setMaxSize(95, 45);
         vBox.getChildren().setAll();
         scrollPane.setContent(vBox);
         scrollPane.setPrefSize(95,45);
         getChildren().addAll(scrollPane);
}
    
}
