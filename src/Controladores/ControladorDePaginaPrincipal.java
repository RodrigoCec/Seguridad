package Controladores;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * author Rodrigo
 */
public class ControladorDePaginaPrincipal implements Initializable {

    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtGrado;
    @FXML
    private TextField txtGrupo;
    @FXML
    private TextField txtCodigo;
    @FXML
    private Label txtHora;
    @FXML
    private ImageView imgFotoPerfil;
    @FXML
    private ImageView imgMarco;
    @FXML
    private ImageView imgAlerta;
    
    

    // Datos de la Conexion a Base de datos
    private static final String bd = "basedatosprueba";
    private static final String direccion = "jdbc:mysql://localhost:3306/" + bd;
    private static final String usuario = "root";
    private static final String password = "";

    // Conexión a la base de datos
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
    
    
    // Método De Consulta Con base de datos para Alumno
    
    String grupo;
    
    
    private void Buscador(String codigo) throws ClassNotFoundException {

        String query = "SELECT Nombre, apellidoPaterno, apellidoMaterno, Grado, Grupo FROM alumnos WHERE codigo = ?";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setString(1, codigo);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String nombre = resultSet.getString("Nombre");
                    String apellidoP = resultSet.getString("apellidoPaterno");
                    String apellidoM = resultSet.getString("apellidoMaterno");
                    grupo = resultSet.getString("Grupo");
                    String grado = resultSet.getString("Grado");

                    String Apellidos = apellidoP + " " + apellidoM;

                    txtNombre.setText(nombre);
                    txtApellido.setText(Apellidos);
                    txtGrado.setText(grado);
                    txtGrupo.setText(grupo);
                }
            }
        } catch (SQLException error) {
            System.out.println(error);
        }
    }

    // Métodos Limitadores
    public String Codigo() {
        String codigo = "";
        codigo = txtCodigo.getText();
        return codigo;
    }

    public Boolean VerificadorDeContenido(String codigo) {
        if (codigo.length() >= 3 && codigo.charAt(0) == 'F' && codigo.charAt(2) == 'E') {
            System.out.println("Código contiene F y E");
            return true;
        } else {
            System.out.println("El código no cumple con F y E");
            return false;
        }
    }

    public Boolean VerificacionDeLongitud(String codigo) {
        if (codigo.length() == 11) {
            System.out.println("El contenido de codigo es de 11 caracteres");
            return true;
        } else {
            System.out.println("Codigo es de menos de 11 caracteres");
            return false;
        }
    }

    public Boolean VerificacionDeTerminacion(String codigo) {
        if (codigo.matches(".*\\d$")) {
            System.out.println("El codigo termina en numero");
            return true;
        } else {
            System.out.println("El codigo no termina en numero");
            return false;
        }
    }

    public void AprobacionDeBusqueda() {
        txtCodigo.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean Verificacion1 = VerificadorDeContenido(newValue);
            boolean Verificacion2 = VerificacionDeLongitud(newValue);
            boolean Verificacion3 = VerificacionDeTerminacion(newValue);

            if (Verificacion1) {
                if (Verificacion2 && Verificacion3) {
                    
                    try {
                        
                        try {//Bloque de codigo
                            Buscador(newValue);///Bloque de codigo
                        } catch (ClassNotFoundException ex) {///Bloque de codigo
                            Logger.getLogger(ControladorDePaginaPrincipal.class.getName()).log(Level.SEVERE, null, ex);//Bloque de codigo
                        }//Bloque de codigo
                        
                        HoraDelAlumno();
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorDePaginaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    VerificadorDeHorarios();
                    
                } else {
                    System.out.println("El codigo No cumple con criterios");
                }
            } else {
                System.out.println("El codigo No cumple con criterios");
            }
        });
    }
    
    
    
    

    // Limitador Basico
    private final int Maximo = 11;
    private boolean Editable = true;

    public void LimitadorLongutid() {
        txtCodigo.textProperty().addListener((observable, ContAnterior, ContActual) -> {
            if (Editable && ContActual.length() > Maximo) {
                Editable = false;
                txtCodigo.setText(ContAnterior);
                Editable = true;
            }
        });
    }

    private void LimpiadorDeCodigo() {
        
        txtCodigo.setOnAction(event -> {
            String codigo = txtCodigo.getText();
            if (codigo != null && !codigo.isEmpty()) {
                txtCodigo.clear();
                txtCodigo.requestFocus();
            }
        });
    }
    
    
    
    //Comparativa de horarios de entrada
    
    public void HoraEnPantalla(){
    
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent event) -> {
            LocalDateTime ahora = LocalDateTime.now();
            ahora = ahora.minusHours(1); // Ajustar la hora actual restando una hora

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
            String horaFormateada = ahora.format(formatter);

            txtHora.setText(horaFormateada);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        
    }
    
    
    public String AsignacionDeDia() {
        String dia = null;
         LocalDateTime fechaHoraActual = LocalDateTime.now();
        LocalDateTime fechaHoraRetrasada = fechaHoraActual.minus(1, ChronoUnit.HOURS);
        DayOfWeek diaSemana = fechaHoraRetrasada.getDayOfWeek();
        String nombreDia = diaSemana.name();

        switch (nombreDia) {
            case "MONDAY":
                dia = "Lunes";
                break;
            case "TUESDAY":
                dia = "Martes";
                break;
            case "WEDNESDAY":
                dia = "Miercoles";
                break;
            case "THURSDAY":
                dia = "Jueves";
                break;
            case "FRIDAY":
                dia = "Viernes";
                break;
        }
        System.out.println(dia);
        return dia;
    }
    
    String CodigoDeHorario;
    
    public void HoraDelAlumno() throws SQLException{
        
        String dia = AsignacionDeDia();
        System.out.println("Dia de Busqueda: " + dia);
        System.out.println("Grupo de Busqueda: " + grupo);
        
        
        
        String query = "SELECT " + dia + " FROM primersemestre WHERE Grupo = '"+ grupo +"'";
        
        try (PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
            
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    CodigoDeHorario = resultSet.getString(dia);
                    
                    System.out.println("Codigode dia: " + CodigoDeHorario);
                    
                }
            }
       
        }catch (SQLException error) {
            System.out.println(error);
        }
    }
    
    
    public String SeparadorDeHoraDeEntrada(){
        
        String HoraDeEntrada =  CodigoDeHorario.substring(1,5);
        
        System.out.println("Se esta separando Hora de entrada : " + CodigoDeHorario);
        System.out.println("Se esta enviando Hora de entrada : " + HoraDeEntrada);
        
        return HoraDeEntrada;

    }
    
    public String SeparadorDeHoraDeSalida(){
        
        String horaDeSalida =  CodigoDeHorario.substring(5,9);
        
        System.out.println("Se esta separando Hora de Salida : " + CodigoDeHorario);
        System.out.println("Se esta enviando Hora de Salida : " + horaDeSalida);
        
        return horaDeSalida;

    }
    
    
    public String HoraEnFormato24hrs() {
        
        DateTimeFormatter hora12horas = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime horaActual = LocalTime.now();
        LocalTime horaFija = horaActual.minusHours(1);
        String horaFormateada = horaFija.format(hora12horas);
        
        System.out.println("Hora ajustada: " + horaFormateada);
        
        //Formato para comprobacion eliminacion de  los dos puntos(:)
        
        String horas = horaFormateada.substring(0,2);
        String minutos = horaFormateada.substring(3,5);
        
        System.out.println("Horas : " + horas);
        System.out.println("Minutos : " + minutos);
        
        
        String formatoDeHoraComparativa = horas + minutos;
        
        System.out.println("Hora que se compara : " + formatoDeHoraComparativa);
        
        
        return formatoDeHoraComparativa;
        
        
    }
    
    
    public void VerificadorDeHorarios(){
        
        String HoraDeEntrada = SeparadorDeHoraDeEntrada();
        String HoraActual = HoraEnFormato24hrs();
        
        int HoraEntradaNumero = Integer.parseInt(HoraDeEntrada);
        int HoraActualNumero = Integer.parseInt(HoraActual);
        
        
        int MinutosDeTolerancia = 10;
        int HoraEntradaHoras = HoraEntradaNumero / 100;
        int HoraEntradaMinutos = HoraEntradaNumero % 100;
        int HoraActualHoras = HoraActualNumero / 100;
        int HoraActualMinutos = HoraActualNumero % 100;

        int minutosEntrada = HoraEntradaHoras * 60 + HoraEntradaMinutos;
        int minutosActual = HoraActualHoras * 60 + HoraActualMinutos;
        int minutosEntradaConTolerancia = minutosEntrada + MinutosDeTolerancia;
        
        
        if (HoraActualNumero <= HoraEntradaNumero) {
            ImagenMarcoVerde();
            System.out.println("El Alumno está a tiempo");
        } else if (minutosActual > minutosEntrada && minutosActual <= minutosEntradaConTolerancia) {
            ImagenMarcoAmarillo();
            System.out.println("El Alumno está dentro de los 10 minutos de tolerancia");
        } else {
            ImagenMarcoRojo();
            System.out.println("El Alumno está retrasado");
        }

    }
    
    
    
    
    //Metodos de las Alertas de tiempo
    
    private void ImagenMarcoVerde(){
        String imagePath;
        imagePath = "/Imagenes/MarcoVerde.png";
        
        Image image = new Image(imagePath);
        imgAlerta.setImage(null);
        imgMarco.setImage(image);
    }
    
    private void ImagenMarcoAmarillo(){
        String imagePath;
        imagePath = "/Imagenes/MarcoAmarillo.png";
        
        Image image = new Image(imagePath);
        imgAlerta.setImage(null);
        imgMarco.setImage(image);
    }
    
    private void ImagenMarcoRojo(){
        String imagePath;
        imagePath = "/Imagenes/MarcoRojo.png";
        
        String imagePath2;
        imagePath2 = "/Imagenes/alerta.png";
        
        Image image = new Image(imagePath);
        Image image2 = new Image(imagePath2);
        imgAlerta.setImage(image2);
        imgMarco.setImage(image);
    }
    
    
    
    
    
    
    
    
   
    
    public void MetodosDelasHoras(){
        
        
        HoraEnPantalla();
    }
    
    
    
    public void ConexionesConBd() {
        // Conexion Base de datos
        ConexionBd();
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        
        // Metodos Indispensables no clasificados xd
        LimitadorLongutid();
        LimpiadorDeCodigo();
        

        AprobacionDeBusqueda();

        

        ConexionesConBd();
        MetodosDelasHoras();
        
    }

    @FXML
    private void btnAgregarAlumno(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ArchivosFXML/VentanaAgregarAlumno.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Agregar Alumno");
            stage.setScene(new Scene(root, 900, 300));
            stage.setIconified(false);
            stage.setResizable(false);
            stage.show();
            Image icon = new Image("file:C:/Users/Rodrigo/Downloads/cecyteh_horizontal1.png");
            stage.getIcons().add(icon);
        } catch (IOException e) {
            Logger.getLogger(ControladorDePaginaPrincipal.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private void btnAsignarHorarios(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ArchivosFXML/VentanaHorarios.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Agregar Alumno");
            stage.setScene(new Scene(root, 1000, 500));
            stage.setIconified(false);
            stage.setResizable(false);
            stage.show();
            Image icon = new Image("file:C:/Users/Rodrigo/Downloads/cecyteh_horizontal1.png");
            stage.getIcons().add(icon);
        } catch (IOException e) {
            Logger.getLogger(ControladorDePaginaPrincipal.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
