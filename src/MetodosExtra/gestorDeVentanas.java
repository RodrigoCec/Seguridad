package MetodosExtra;

import Controladores.ControladorVentanaPrincipalEmergente;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Rodrigo
 */
public class gestorDeVentanas {
    
    private static Stage destinationStage;
    private static ControladorVentanaPrincipalEmergente destinationController;

    public static void openOrUpdateDestinationWindow(String value) {
        if (destinationStage != null && destinationStage.isShowing()) {
            // Ventana ya está abierta, actualizar el contenido
            destinationController.setTextFieldValue(value);
            destinationStage.toFront(); // Pone la ventana al frente
        } else {
            // Crear una nueva instancia de la ventana
            try {
                FXMLLoader loader = new FXMLLoader(gestorDeVentanas.class.getResource("/ArchivosFXML/VentanaPrincipalEmergente.fxml"));
                Scene scene = new Scene(loader.load());

                destinationStage = new Stage();
                destinationStage.setScene(scene);
                destinationStage.setWidth(1700);  // Ancho deseado
                destinationStage.setHeight(950);  // Alto deseado

                // Evitar que el usuario modifique el tamaño de la ventana
                destinationStage.setResizable(false);

                destinationController = loader.getController();
                destinationController.setTextFieldValue(value);

                destinationStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
