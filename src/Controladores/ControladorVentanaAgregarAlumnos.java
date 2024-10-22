/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;


import Conexion.conexionEnvioDeAlumnos;
import Conexion.conexionGeneralDeConsultaAlumnos;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
        int AnchoCodigo = 400; //100
        int AltoCodigo = 400;

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
        
        try (Connection connection = conexionEnvioDeAlumnos.getConnection();
             Statement statement = connection.createStatement()) {

            String sql = "INSERT INTO alumnos (Nombre, ApellidoPaterno, ApellidoMaterno, Grado, Grupo, Matricula) "
                + "VALUES ('" + Nombre +  "', '" + ApellidoPat +  "', '" + ApellidoMat + "', '" + Grado + "', '" + Grupo +  "', '" + Matricula + "')";

            int filasAfectadas = statement.executeUpdate(sql);

            if (filasAfectadas > 0) {
                System.out.println("los datos son correctos.");
                alerta(Alert.AlertType.CONFIRMATION, "Registro", "Se ah registrado correctamente al alumno");
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
    
    
    public boolean MetodoCodigoRepetido(String valorAverificar){
        
        String Consulta = "SELECT COUNT(*) FROM alumnos WHERE Matricula = "+ valorAverificar +"";
        
        try(Connection connection = conexionGeneralDeConsultaAlumnos.getConnection();
            PreparedStatement DatosDeConsulta = connection.prepareStatement(Consulta)){
            
            
            try(ResultSet dato = DatosDeConsulta.executeQuery()){
                if(dato.next()){
                    int count = dato.getInt(1);
                    if (count > 0) {
                        System.out.println("Ya existe un registro con valor: " + valorAverificar);
                        alerta(Alert.AlertType.WARNING,"Alumno Repetido", "Ya existe un alumno con el mismo codigo Asignado, Deseas remplazarlo?");
                        return true;
                    } else {
                        System.out.println("No existe ningún registro con valor: " + valorAverificar);
                        return false;
                    }
                }
            } 
        } catch (SQLException ex) {
            Logger.getLogger(ControladorVentanaAgregarAlumnos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
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
        txtMatricula.requestFocus();
    }
    @FXML
    private void clickPasarSiguiente6(ActionEvent event) {
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
        CredencialesCompletas();
        limitadoresDeCuadrosDeTexto();
        
    }    

    
    
    @FXML
    private void bntRegistrarAllumno(ActionEvent event) {
        try {
            recuperarDatos();
            
            String filePath = "C:/Users/Rodrigo/Pictures/sas/" + Matricula + ".png";
            
            generateQRCode(Matricula, filePath);
            boolean seguir = MetodoCodigoRepetido(Matricula);
            if(!seguir){
                subirDatos();
            }else{
                alerta(Alert.AlertType.WARNING,"Alumno Repetido", "Ya existe un alumno con el mismo codigo Asignado, Deseas remplazarlo?");
            }
            
            CredencialesCompletas();
            
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
        
        txtNombre.requestFocus();
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
    
    //Creador de credenciales
    public int[] CordenadasTexto(Graphics2D image ,String name, String lastName, String Matricula){
            
            int margenIzquierdo = 145;
            int areaWidth = 780;
            //Codigo Nombre
            FontMetrics metrics = image.getFontMetrics(image.getFont());
            int textWidthName = metrics.stringWidth(name);
            int textWidtLastName = metrics.stringWidth(lastName);
            int textWidtMatricula = metrics.stringWidth(Matricula);
            
            int firstX = margenIzquierdo + (areaWidth - textWidthName) / 2;
            int secondX = margenIzquierdo + (areaWidth - textWidtLastName) / 2;
            int thirdX = margenIzquierdo + (areaWidth - textWidtMatricula) / 2;
            
             


            int[] valores = new int[]{firstX, secondX, thirdX};
            
            return valores;
    }
    
    
    
    public void CredencialesCompletas(){
        
        recuperarDatos();
        
        String name = Nombre;
        String lastName = ApellidoPat;
        String secondLastName = ApellidoMat;
        String apellidos =  lastName + " " +  secondLastName;
        String matricula = Matricula;
        
        System.out.println("Nombre: " + name);
        System.out.println("Apellido Paterno: " + lastName);
        System.out.println("Apellido Materno: " + secondLastName);
        System.out.println("Matrícula: " + matricula);
        
        
        String pahtImgFondo = "C:/Users/Rodrigo/Pictures/sas/fondo.png";
        String matriculaImgQr = "C:/Users/Rodrigo/Pictures/sas/" + matricula + ".png";
        //String pahtImgFondo = "/Imagenes/fondo.png";
        //String matriculaImgQr = "C:/Users/Rodrigo/Pictures/sas/" + matricula + ".png";
        //String rutaGuardado = "";
        
        File fondoCredencialFile = new File(pahtImgFondo);
        File matriculaQrFile = new File(matriculaImgQr);

        if (!fondoCredencialFile.exists()) {
            System.out.println("El archivo del fondo de la credencial no se encontró: " + pahtImgFondo);
        } else {
            System.out.println("Archivo del fondo de la credencial encontrado: " + pahtImgFondo);
        }

        if (!matriculaQrFile.exists()) {
            System.out.println("El archivo del código QR de la matrícula no se encontró: " + matriculaImgQr);
        } else {
            System.out.println("Archivo del código QR de la matrícula encontrado: " + matriculaImgQr);
        }
        
        
        int cordenadaX = 325;
        int cordenadaY = 350;
        
        int fontSize = 40;
        String fuente = "Garet-Heavy";
        
        try{
            
            BufferedImage fondoCredencial = ImageIO.read(new File(pahtImgFondo));
            BufferedImage matriculaQr = ImageIO.read(new File(matriculaImgQr));
            
            BufferedImage image = new BufferedImage(
                    fondoCredencial.getWidth(),
                    fondoCredencial.getHeight(),
                    BufferedImage.TYPE_INT_ARGB);
            
            Graphics2D credencial = image.createGraphics();
            credencial.drawImage(fondoCredencial, 0, 0, null);
            credencial.drawImage(matriculaQr, cordenadaX, cordenadaY, null);
            
            credencial.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            credencial.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            credencial.setFont(new Font(fuente, Font.BOLD, fontSize));
            credencial.setColor(new Color(0x37291d));
            
            int[] result = CordenadasTexto(credencial, name,apellidos, Matricula);
            
            credencial.drawString(name, result[0], 810);
            credencial.drawString(apellidos, result[1], 850);
            credencial.drawString(matricula, result[2], 990);
            
            String GuardadoCredencia = name + " " + apellidos; 
            ImageIO.write(image, "png", new File("C:/Users/Rodrigo/Pictures/"+ GuardadoCredencia+ ".png"));

            System.out.println("Imagen combinada con texto centrado guardada correctamente.");
        }catch(Exception e){}
        
    }

    
    
}
