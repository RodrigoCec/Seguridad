/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

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
import javafx.scene.control.TextField;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Rodrigo
 */
public class VentanaAgregarAlumnoController implements Initializable {

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
    private TextField txtCodigo;

    /**
     * Initializes the controller class.
     */
    
    
    //Metodo conexion A base de datos
    
    private static final String bd = "basedatosprueba";
    private static final String direccion = "jdbc:mysql://localhost:3306/" + bd;
    private static final String usuario = "root";
    private static final String password = "";
    
    private static Connection conexion;

    // Código de conexión
    public static Connection ConexionBd() {
        if (conexion == null) {
            try {
                conexion = DriverManager.getConnection(direccion, usuario, password);
                System.out.println("Conexión exitosa");
            } catch (SQLException e) {
                Logger.getLogger(ControladorDePaginaPrincipal.class.getName()).log(Level.SEVERE, "Error de conexión", e);
            }
        }
        return conexion;
    }
    
 
    
    //metodo Generador de codigo de alumno
    
    String Nombre;
    String ApellidoPat;
    String ApellidoMat;
    String Grado;
    String Grupo;
    String Codigo;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
    int numerodeGrupo;
    String gradoEnNum;
    
    
    public void recuperarDatos(){
        Nombre = txtNombre.getText();
        ApellidoPat = txtApellidoPaterno.getText();
        ApellidoMat = txtApellidoMaterno.getText();
        Grado = txtGrado.getText();
        Grupo = txtGrupo.getText();
    }
    
    
    public void generadorCodQr(){
        recuperarDatos();
      
        char InName = Character.toUpperCase(Nombre.charAt(0));
        char InAp = Character.toUpperCase(ApellidoPat.charAt(0));
        char InAm = Character.toUpperCase(ApellidoMat.charAt(0));
        char InGrad = Character.toUpperCase(Grado.charAt(0));
        char Group = Character.toUpperCase(Grupo.charAt(0));
        
        
        int i = 0;
        int LetA = 65;
        char PrefijoGrup = 'A';
        
        
        if(Group >= 'J' && Group < 'S'){
            PrefijoGrup = 'B';
        }else if(Group >= 'S'){
            PrefijoGrup = 'C';
        }
        
        do{
            i += 1;
            if(i > 9){
                i = 1;
            }
            
            LetA += 1;
            
        }while(LetA <= Group);
        
        //char codGrado;
        
        int numeroLonP;
        int numeroLonM;
        int ultDigito;

        numeroLonP = ApellidoPat.length();
        numeroLonM = ApellidoMat.length();

        Random rand = new Random();
        ultDigito = rand.nextInt(9);

        numeroLonP += ultDigito;
        numeroLonM += ultDigito;

        if (numeroLonP > 9) {
            int rest = numeroLonP - 9;
            do {
                rest -= 9;
            } while (rest > 9);
    
            while (rest < 0) {
                rest += 9;
            }
    
            numeroLonP = rest;
        }

        if (numeroLonM > 9) {
        
        int rest = numeroLonM - 9;
            do {
                rest -= 9;
            } while (rest > 9);
    
            while (rest < 0) {
                rest += 9;
            }
    
           numeroLonM = rest;
        }

        
        Codigo = ("F" + InGrad + "E" + i + PrefijoGrup + InAp + numeroLonM + InAm+ numeroLonP + InName + ultDigito);
        
        System.out.println(Codigo);
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
        try (Connection connection = ConexionBd();
             Statement statement = connection.createStatement()) {

            String sql = "INSERT INTO alumnos (Nombre, apellidoPaterno, apellidoMaterno, Grado, Grupo, codigo) "
                + "VALUES ('" + Nombre +  "', '" + ApellidoPat +  "', '" + ApellidoMat + "', '" + Grado + "', '" + Grupo +  "', '" + Codigo + "')";

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
        
        String Consulta = "SELECT codigo FROM alumnos WHERE codigo = '"+ valorAverificar +"'";
        
        try(Connection connection = ConexionBd();
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
        
        generadorCodQr();
        
        txtCodigo.setText(Codigo);
        
      
        String filePath = "C:/Users/Rodrigo/Pictures/sas/" + Codigo + ".png";
        
        try {
            generateQRCode(Codigo, filePath);
        } catch (IOException | WriterException e) {
        }
        
        subirDatos();
        
    }

    @FXML
    private void bntLimpiarDatos(ActionEvent event) {
      
        txtNombre.setText("");
        txtApellidoPaterno.setText("");
        txtApellidoMaterno.setText("");
        txtGrado.setText("");
        txtGrupo.setText("");
        txtCodigo.setText("");
    
    }
    
}
