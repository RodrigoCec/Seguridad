/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexion.conexionDeConsulta;
import Conexion.conexionDeRegistro;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Rodrigo
 */
public class ControladorVentanaAgregarAlumnos implements Initializable {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidoPaterno;
    @FXML
    private TextField txtApellidoMaterno;
    @FXML
    private TextField txtGrado;
    @FXML
    private TextField txtGrupo;
    @FXML
    private TextField txtMatricula;

    /**
     * Initializes the controller class.
     */
    
    
    //Metodo conexion A base de datos
    
    
    //metodo Generador de codigo de alumno
    
    String Nombre;
    String ApellidoPat;
    String ApellidoMat;
    String Grado;
    String Grupo;
    String Matricula;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
    int numerodeGrupo;
    String gradoEnNum;
    @FXML
    private MenuItem btnSeleccionar;
    
    
    public void recuperarDatos(){
        Nombre = txtNombre.getText();
        ApellidoPat = txtApellidoPaterno.getText();
        ApellidoMat = txtApellidoMaterno.getText();
        Grado = txtGrado.getText();
        Grupo = txtGrupo.getText();
        Matricula = txtMatricula.getText();
    }
    
  
    
    
    //Metodo Generador de codigo Qr
    
    public static void generateQRCode(String data, String filePath) throws IOException, WriterException {
        int AnchoCodigo = 1000;
        int AltoCodigo = 1000;

        Map<EncodeHintType, Object> mapa = new HashMap<>();
        mapa.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        mapa.put(EncodeHintType.MARGIN, 1);
        mapa.put(EncodeHintType.QR_VERSION, 2);
        
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, AnchoCodigo, AltoCodigo, mapa);

        BufferedImage originalImage = new BufferedImage(AnchoCodigo, AltoCodigo, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < AnchoCodigo; x++) {
            for (int y = 0; y < AltoCodigo; y++) {
                originalImage.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF); // Gestiona los colores
            }
        }
        

        File outputFile = new File(filePath);
        ImageIO.write(originalImage, "png", outputFile);

        System.out.println("Código QR generado con éxito en: " + filePath);
    }
    
    
    //Metodos con base de datos para subir datos de alumno
    
    public void subirDatos(){
        //
        recuperarDatos();
        
        try (Connection connection = conexionDeRegistro.getConnection();
             Statement statement = connection.createStatement()) {

            String sql = "INSERT INTO alumnos (Nombre, apellidoPaterno, apellidoMaterno, Grado, Grupo, matricula) "
                + "VALUES ('" + Nombre +  "', '" + ApellidoPat +  "', '" + ApellidoMat + "', '" + Grado + "', '" + Grupo +  "', '" + Matricula + "')";

            int filasAfectadas = statement.executeUpdate(sql);

            if (filasAfectadas > 0) {
                System.out.println("los datos son correctos.");
            } else {
                System.out.println("no se pueden ingresar datos.");
            }

        } catch (SQLException ex) {
        }
    }
    
    
    
    //Metodo para limitar codigos repetidos
    public void alerta(Alert.AlertType tipo,String titulo, String texto){
    
        Alert alerta = new Alert(tipo);   
        alerta.setTitle(titulo);
        alerta.setContentText(texto);
        alerta.showAndWait();
        
    }
    
    
    public void MetodoCodigoRepetido(String valorAverificar) throws SQLException{
        
        String Consulta = "SELECT matricula FROM alumnos WHERE matricula = '"+ valorAverificar +"'";
        
        try(Connection connection = conexionDeConsulta.getConnection();
            PreparedStatement DatosDeConsulta = connection.prepareStatement(Consulta)){
            
            //DatosDeConsulta.setString(1, valorAverificar);
            
            
            try(ResultSet dato = DatosDeConsulta.executeQuery()){
                if(dato.next()){
                    int count = dato.getInt("count");
                    
                    if (count > 0) {
                        System.out.println("Ya existe un registro con valor: " + valorAverificar);
                        alerta(Alert.AlertType.WARNING,"Alumno Repetido", "Ya existe un alumno con el mismo codigo Asignado, Deseas remplazarlo?");
                    } else {
                        System.out.println("No existe ningún registro con valor: " + valorAverificar);
                    }
                }
            } 
        }
    }
    
    
    
    
    //Metodos para limitar Escrituras en recuadros
    
    private final int Maximo = 1;
    private boolean Editable = true;

    public void LimitadorLongutidDeGrado() {
        txtGrado.textProperty().addListener((observable, ContAnterior, ContActual) -> {
            if (Editable && ContActual.length() > Maximo) {
                Editable = false;
                txtGrado.setText(ContAnterior);
                Editable = true;
            }
        });
    }
    
    public void LimitadorLongutidDeGrupo() {
        txtGrupo.textProperty().addListener((observable, ContAnterior, ContActual) -> {
            if (Editable && ContActual.length() > Maximo) {
                Editable = false;
                txtGrupo.setText(ContAnterior);
                Editable = true;
            }
        });
    }
    
    
    
    private void limitarSoloNumeros(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    private void limitarSoloLetras(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[a-zA-Z]*")) {
                textField.setText(newValue.replaceAll("[^a-zA-Z]", ""));
            }
        });
    }
    
    
    
    
    
    
    
    
    //Ayudas de escrituta
    
    
    @FXML
    private void clickPasarSiguiente(ActionEvent event) {
        txtApellidoPaterno.requestFocus();
    }
    @FXML
    private void clickPasarSiguiente2(ActionEvent event) {
        txtApellidoMaterno.requestFocus();
    }
    @FXML
    private void clickPasarSiguiente3(ActionEvent event) {
        txtGrado.requestFocus();
    }
    @FXML
    private void clickPasarSiguiente4(ActionEvent event) {
        txtGrupo.requestFocus();
    }
    @FXML
    private void clickPasarSiguiente5(ActionEvent event) {
        txtNombre.requestFocus();
    }
    
    
    
    
    public void limitadoresDeCuadrosDeTexto(){
        
        LimitadorLongutidDeGrado();
        LimitadorLongutidDeGrupo();
        limitarSoloNumeros(txtGrado);
        limitarSoloLetras(txtGrupo);
    
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        limitadoresDeCuadrosDeTexto();
        
    }    

    
    
    @FXML
    private void bntRegistrarAllumno(ActionEvent event) {
        try {
            recuperarDatos();
            
            String filePath = "C:/Users/Rodrigo/Pictures/sas/" + Matricula + ".png";
            
            generateQRCode(Matricula, filePath);
            subirDatos();
        } catch (IOException | WriterException ex) {
            Logger.getLogger(ControladorVentanaAgregarAlumnos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void bntLimpiarDatos(ActionEvent event) {
      
        txtNombre.setText("");
        txtApellidoPaterno.setText("");
        txtApellidoMaterno.setText("");
        txtGrado.setText("");
        txtGrupo.setText("");
        txtMatricula.setText("");
    
    }

    @FXML
    private void bntSelecionarCarpeta(ActionEvent event) {
        
       DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Seleccionar Carpeta");

        // Obtener el Stage a partir del MenuItem
        Stage stage = (Stage) btnSeleccionar.getParentPopup().getOwnerWindow();

        // Abrir el explorador de archivos para seleccionar una carpeta
        File selectedDirectory = directoryChooser.showDialog(stage);

        // Si se selecciona una carpeta, puedes hacer algo con ella
        if (selectedDirectory != null) {
            System.out.println("Carpeta seleccionada: " + selectedDirectory.getAbsolutePath());
        }
    }
    
}
