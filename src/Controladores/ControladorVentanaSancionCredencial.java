/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexion.conexionDeConsulta;
import DatosInformes.datos;
import DatosInformes.datosBusqueda;
import MetodosExtra.gestorDeVentanas;
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
import static javafx.scene.input.KeyCode.S;
import static javafx.scene.input.KeyCode.T;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Rodrigo
 */
public class ControladorVentanaSancionCredencial implements Initializable {

    @FXML
    private TableView<datosBusqueda> tablaGeneral;
    @FXML
    private TableColumn<datosBusqueda, String> nombre;
    @FXML
    private TableColumn<datosBusqueda, String> grado;
    @FXML
    private TableColumn<datosBusqueda, String> grupo;
    @FXML
    private TableColumn<datosBusqueda, String> matricula;
    @FXML
    private TextField txtGrado;
    @FXML
    private TextField txtGrupo;
    

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
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        grado.setCellValueFactory(new PropertyValueFactory<>("grado"));
        grupo.setCellValueFactory(new PropertyValueFactory<>("grupo"));
        matricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));

        ObservableList<datosBusqueda> users = FXCollections.observableArrayList();

        try {
            // Utilizar el método de conexión proporcionado
            Connection connection = conexionDeConsulta.getConnection();

            // Crear la consulta con parámetros
            String query = "SELECT Nombre, Grado, Grupo, Matricula FROM alumnos WHERE Grado = ? AND Grupo = ?";
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

                // Crear un nuevo objeto datos y agregarlo a la lista
                users.add(new datosBusqueda(nombre, grado, grupo, matricula));
            }

            // Cerrar la conexión
            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Configurar el TableView con los datos
        tablaGeneral.setItems(users);

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
    

    @FXML
    private void ObtenerDato(TableColumn.CellEditEvent<datosBusqueda, String> event) {
        datosBusqueda person = event.getRowValue();
            String currentValue = person.getMatricula();
            
            gestorDeVentanas.openOrUpdateDestinationWindow(currentValue);
            System.out.println("Editing Started. Current Value: " + currentValue);
    }  
    
}
