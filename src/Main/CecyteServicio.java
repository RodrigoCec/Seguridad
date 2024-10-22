/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Rodrigo
 */
public class CecyteServicio extends Application {
    
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/ArchivosFXML/PaginaPrincipal.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
          
        // Verifica si el Stage no está maximizado
        if (!stage.isMaximized()) {
            // Establece el tamaño de la ventana si no está maximizada
            stage.setHeight(1000);  // Altura de la ventana
            stage.setWidth(1920);   // Ancho de la ventana
        }

        // Si quieres maximizar después de cambiar el tamaño, puedes hacerlo
        stage.setMaximized(true);
        Image icon = new Image("file:C:/Users/Rodrigo/Downloads/cecyteh_horizontal1.png");
        stage.getIcons().add(icon);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);   
    }
    
}
