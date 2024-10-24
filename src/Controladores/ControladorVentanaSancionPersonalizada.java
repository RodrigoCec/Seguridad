/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexion.conexionConsultasGeneralesRegistros;
import Conexion.conexionEnvioDeSanciones;
import MetodosExtra.nombreTablasRegistroEntrada;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;


/**
 * FXML Controller class
 *
 * @author Rodrigo
 */
public class ControladorVentanaSancionPersonalizada implements Initializable {

    @FXML
    private TextArea txtAreaTexto;

    /**
     * Initializes the controller class.
     */
    
    public String NombreTablaActualEntrada(){
       nombreTablasRegistroEntrada tablaRegistro = new nombreTablasRegistroEntrada();
        String TablaEntrada = tablaRegistro.NombreTablaActualEntrada();
        return TablaEntrada;
    }
    
    public String SelectorDeMatricula(String nombreTabla){
        try(Connection connection = conexionConsultasGeneralesRegistros.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                "SELECT Matricula FROM ´" + nombreTabla + "´ ORDER BY Id_registro DESC LIMIT 1")){
             
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String Matriucla = resultSet.getString("Matricula");
                    System.out.println("Matricula: " + Matriucla);
                    return Matriucla;
                }
            }
        }catch(SQLException ex){
        
        }
        
        return null;
    }
    
    public void SubidaDeSanciones(String nombreTabla, String Matricula, String Sancion){
        
        try(Connection connection = conexionEnvioDeSanciones.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                "UPDATE ´" + nombreTabla + "´ SET Descripcion = ? WHERE Matricula = ? ")){
                statement.setString(1, Sancion);
                statement.setString(2, Matricula);
                
            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Los datos se han registrado correctamente.");
            } else {
                System.out.println("No se pudieron ingresar los datos.");
            }
            
        }catch(SQLException ex){
        
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnEnviar(ActionEvent event) {
        
        String Sancion = txtAreaTexto.getText();
        String nameTabla = NombreTablaActualEntrada();
        String Matricula = SelectorDeMatricula(nameTabla);
        SubidaDeSanciones(nameTabla,Matricula, Sancion);
        
        txtAreaTexto.setText("");
        
    }
    
}
