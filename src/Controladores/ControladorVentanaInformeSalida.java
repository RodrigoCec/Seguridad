/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexion.conexionConsultasGeneralesRegistros;
import DatosInformes.datosSalida;
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
public class ControladorVentanaInformeSalida implements Initializable {

    @FXML
    private TextField txtGrupo;
    @FXML
    private TextField txtGrado;
    @FXML
    private TableView<datosSalida> TablaGeneral;
    @FXML
    private TableColumn<datosSalida, String> Nombre;
    @FXML
    private TableColumn<datosSalida, String> Grado;
    @FXML
    private TableColumn<datosSalida, String> Grupo;
    @FXML
    private TableColumn<datosSalida, String> Matricula;
    @FXML
    private TableColumn<datosSalida, LocalDate> Fecha;
    @FXML
    private TableColumn<datosSalida, LocalTime> Hora;

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

        ObservableList<datosSalida> users = FXCollections.observableArrayList();

        try {
            // Utilizar el método de conexión proporcionado
            Connection connection = conexionConsultasGeneralesRegistros.getConnection();

            // Crear la consulta con parámetros
            String query = "SELECT Nombre, Grado, Grupo, Matricula, Fecha, Hora FROM salida WHERE Grado = ? AND Grupo = ?";
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

                // Crear un nuevo objeto datos y agregarlo a la lista
                users.add(new datosSalida(nombre, grado, grupo, matricula, fecha, hora));
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
