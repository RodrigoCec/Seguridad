/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetodosExtra;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Rodrigo
 */
public class ExtractorDatosConexion {
    private static final String CONFIG_PATH = "/ArchivosJSON/datosDeConexion.json";

    // Clase interna para la estructura de los datos JSON
    public static class ConfiguracionDB {
        public String bd;
        public String direccion;
        public String usuario;
        public String password;
    }

    // Método para leer el archivo JSON y cargar los datos de configuración
     public static ConfiguracionDB cargarConfiguracion() {
         
        Gson gson = new Gson();
        ConfiguracionDB configuracion = null;

        try (InputStream inputStream = ExtractorDatosConexion.class.getResourceAsStream(CONFIG_PATH);
             InputStreamReader reader = new InputStreamReader(inputStream)) {

            if (inputStream == null) {
                System.out.println("Archivo no encontrado: " + CONFIG_PATH);
                return null;
            }

            configuracion = gson.fromJson(reader, ConfiguracionDB.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return configuracion;
    }
}
