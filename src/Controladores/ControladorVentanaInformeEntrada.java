
package Controladores;

import Conexiones.conexionDeConsulta;
import DatosInformes.datos;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Rodrigo
 */
public class ControladorVentanaInformeEntrada implements Initializable {

    @FXML
    private TextField txtGrupo;
    @FXML
    private TextField txtGrado;
    
    @FXML
    private TableView<datos> TablaGeneral;
    @FXML
    private TableColumn<datos, String> Nombre;
    @FXML
    private TableColumn<datos, String> Grado;
    @FXML
    private TableColumn<datos, String> Grupo;
    @FXML
    private TableColumn<datos, String> Matricula;
    @FXML
    private TableColumn<datos, LocalDate> Fecha;
    @FXML
    private TableColumn<datos, LocalTime> Hora;
    @FXML
    private TableColumn<datos, String> Tiempo;
    @FXML
    private TableColumn<datos, String> Reporte;
    @FXML
    private TableColumn<datos, String> ReporteDos;
    @FXML
    private TableColumn<datos, String> ReporteTres;
    @FXML
    private TableColumn<datos, String> Observaciones;
    

    /**
     * Initializes the controller class.
     */
    
    
    public void ActualizadorDeDatos(){
        
        String Grado = txtGrado.getText();
        String Grupo = txtGrupo.getText();

        // Actualiza la tabla con los valores actuales de Grado y Grupo
        MostradorDeDatos(Grado, Grupo);
        
    }
   
    public void MostradorDeDatos(String Semestre, String Grup){
        // Configurar las columnas para utilizar las propiedades de la clase datos
        Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        Grado.setCellValueFactory(new PropertyValueFactory<>("grado"));
        Grupo.setCellValueFactory(new PropertyValueFactory<>("grupo"));
        Matricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        Fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        Hora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        Tiempo.setCellValueFactory(new PropertyValueFactory<>("tiempo"));
        Reporte.setCellValueFactory(new PropertyValueFactory<>("reporte"));
        ReporteDos.setCellValueFactory(new PropertyValueFactory<>("reporteDos"));
        ReporteTres.setCellValueFactory(new PropertyValueFactory<>("reporteTres"));
        Observaciones.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        ObservableList<datos> users = FXCollections.observableArrayList();

        try {
            // Utilizar el método de conexión proporcionado
            Connection connection = conexionDeConsulta.getConnection();

            // Crear la consulta con parámetros
            String query = "SELECT Nombre, Grado, Grupo, Matricula, Fecha, Hora, Estado, Reporte, ReporteDos, ReporteTres, Descripcion FROM registros WHERE Grado = ? AND Grupo = ?";
            PreparedStatement statement = connection.prepareStatement(query);

            // Establecer los valores de los parámetros
            statement.setString(1, Semestre);
            statement.setString(2, Grup);

            // Ejecutar la consulta
            ResultSet resultSet = statement.executeQuery();

            // Agregar los datos al ObservableList
            while (resultSet.next()) {
                String nombre = resultSet.getString("Nombre");
                String grado = resultSet.getString("Grado");
                String grupo = resultSet.getString("Grupo");
                String matricula = resultSet.getString("Matricula");
                LocalDate fecha = resultSet.getDate("Fecha").toLocalDate();
                LocalTime hora = resultSet.getTime("Hora").toLocalTime();
                String tiempo = resultSet.getString("Estado");
                String reporte = resultSet.getString("Reporte");
                String reporteDos = resultSet.getString("ReporteDos");
                String reporteTres = resultSet.getString("ReporteTres");
                String descripcion = resultSet.getString("Descripcion");

                // Crear un nuevo objeto datos y agregarlo a la lista
                users.add(new datos(nombre, grado, grupo, matricula, fecha, hora, tiempo, reporte, reporteDos, reporteTres, descripcion));
            }

            // Cerrar la conexión
            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Configurar el TableView con los datos
        TablaGeneral.setItems(users);

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         ActualizadorDeDatos();

        // Agrega listeners para actualizar los datos en la tabla cuando cambien los valores de los TextField
        txtGrado.textProperty().addListener((observable, oldValue, newValue) -> {
            ActualizadorDeDatos();
        });

        txtGrupo.textProperty().addListener((observable, oldValue, newValue) -> {
            ActualizadorDeDatos();
        });
    }    
    
}
