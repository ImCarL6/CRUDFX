/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudfx;

/**
 *
 * @author carlo
 */
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    
    
    
    @Override
    public void start(Stage stage) throws Exception{
        
        
        
        Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/CSS/Tableview.css");
        stage.setTitle("Sistema de Cadastramento");
        stage.setScene(scene);
        stage.show();
        
    }

    
    
    public static void main(String[] args) {
        launch(args);
    }
}
