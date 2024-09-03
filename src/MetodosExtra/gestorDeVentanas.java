package MetodosExtra;

import Controladores.ControladorDePaginaPrincipal;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Rodrigo
 */
public class gestorDeVentanas {
    
    private static Stage destinationStage;
    private static ControladorDePaginaPrincipal destinationController;

    public static void openOrUpdateDestinationWindow(String value) {
        if (destinationStage != null && destinationStage.isShowing()) {
            // Ventana ya está abierta, actualizar el contenido
            destinationController.setTextFieldValue(value);
            destinationStage.toFront(); // Pone la ventana al frente
        } else {
            // Crear una nueva instancia de la ventana
            try {
                FXMLLoader loader = new FXMLLoader(gestorDeVentanas.class.getResource("/ArchivosFXML/PaginaPrincipal.fxml"));
                Scene scene = new Scene(loader.load());

                destinationStage = new Stage();
                destinationStage.setScene(scene);
                destinationController = loader.getController();
                destinationController.setTextFieldValue(value);

                destinationStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
