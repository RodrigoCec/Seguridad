/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import DatosBasesJSON.ExtractorDatosConexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Rodrigo
 */
public class conexionEnvioDeAlumnos {
    
    public static Connection getConnection() throws SQLException {
        ExtractorDatosConexion.ConfiguracionDB config = ExtractorDatosConexion.cargarConfiguracion();
        String addres = config.direccion + config.bd ;
        Connection connection = DriverManager.getConnection(addres, config.usuario,config.password);
        System.out.println("La direccion es:" + config.direccion + config.bd);
        
        return connection;
    }
    
}
