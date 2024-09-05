/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Rodrigo
 */
public class dateBaseconnection {
    
    private static final String bd = "basedatosprueba";
    private static final String direccion = "jdbc:mysql://localhost:3306/" + bd;
    private static final String usuario = "root";
    private static final String password = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(direccion, usuario, password);
    }
}
