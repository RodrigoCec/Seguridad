package Controladores;

import Conexiones.dateBaseconnection;
import static Controladores.VentanaAgregarAlumnoController.ConexionBd;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.LocalDate;
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
    private TextField txtUniforme;
    @FXML
    private ImageView imgFotoPerfil;
    @FXML
    private ImageView imgMarco;
    @FXML
    private ImageView imgAlerta;
    
    //Prueba base De datos Cambio

    // Datos de la Conexion a Base de datos
    private static final String bd = "pruebacecyteh";
    private static final String direccion = "jdbc:mysql://localhost:3306/" + bd;
    private static final String usuario = "root";
    private static final String password = "";

    // Conexión a la base de datos
    private static Connection conexion;
    private static Connection conexionDeregistro;
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
    public Connection ConexionDeregistoBd() {
        if (conexionDeregistro == null) {
            try {
                conexionDeregistro = DriverManager.getConnection(direccion, usuario, password);
                System.out.println("Conexión exitosa");
            } catch (SQLException e) {
                Logger.getLogger(ControladorDePaginaPrincipal.class.getName()).log(Level.SEVERE, "Error de conexión", e);
            }
        }
        return conexionDeregistro;
    }
    
    
    private String[] BuscadorDeAlumno (String codigo){

        String query = "SELECT Nombre, apellidoPaterno, apellidoMaterno, Grado, Grupo FROM alumnos WHERE matricula = ?";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setString(1, codigo);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String Nombre = resultSet.getString("Nombre");
                    String apellidoP = resultSet.getString("apellidoPaterno");
                    String apellidoM = resultSet.getString("apellidoMaterno");
                    String Grupo = resultSet.getString("Grupo");
                    String Grado = resultSet.getString("Grado");

                    String Apellidos = apellidoP + " " + apellidoM;
                    
                    txtNombre.setText(Nombre);
                    txtApellido.setText(Apellidos);
                    txtGrado.setText(Grado);
                    txtGrupo.setText(Grupo);
                    
                    return new String[]{Nombre, Apellidos, Grado, Grupo};
                }
            }
        } catch (SQLException error) {
            System.out.println("La extraccion de datos del alumno tuvo los errores: " + error);
        }
        return null;
    }
    
    public void SeteadorDeUniforme(String Codigo){
        String Dia = SelectorDeDiaSemana();
        //System.out.println("Dia es: " + Dia);
        String Semestre = AsignadorDeSemestre(Codigo);
        //System.out.println("Semestre es: " + Semestre);
        
        String[] Datos =  BuscadorDeAlumno(Codigo);
        //System.out.println("Grupo es: " + Datos[3]);
        String Uniforme = SelectorDeUniforme(Dia,Semestre, Datos[3]);
        
        txtUniforme.setText(Uniforme);
    }

    public void VerificacionDeLongitud(){
        
        txtCodigo.textProperty().addListener((observable, oldValue, newValue) -> {
            
            if(newValue.length() == 14){
               String[] Datos = BuscadorDeAlumno(newValue);
               ComparadorDeEntrada(newValue);
               SeteadorDeUniforme(newValue);
               EnvioDeregistro(Datos[0], Datos[1], Datos[2], Datos[3], newValue);
            }
        });
    }

    // Limitador Basico
    private final int Maximo = 15;
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
    
    
    public String SelectorDeDiaSemana() {
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
    
    public String AsignadorDeSemestre(String Codigo){
        String[] Datos =  BuscadorDeAlumno(Codigo); 
        
        String BaseDatos = "";
        switch(Datos[2]){
            case "1":
                BaseDatos = "primersemestre";
                break;
            case "2":
                BaseDatos = "segundosemestre";
                break;
            case "3":
                BaseDatos = "tercersemestre";
                break;
            case "4":
                BaseDatos = "cuartosemestre";
                break;
            case "5":
                BaseDatos = "quintosemestre";
                break;
            case "6":
                BaseDatos = "sextosemestre";
                break;
                 
        }
        System.out.println("Se supone que es: " + BaseDatos);
        return BaseDatos;
    }
    
    public String BusquedaHoraDelAlumno(String dia, String grado, String grupo) {
        String query = "SELECT " + dia + " FROM " + grado + " WHERE Grupo = ?";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setString(1, grupo);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String codigoDeHorario = resultSet.getString(dia);
                    System.out.println("Código del día: " + codigoDeHorario);
                    return codigoDeHorario;
                }
            }
        } catch (SQLException error) {
            System.out.println("La búsqueda del horario del alumno falló en: " + error);
        }
        return null;
    }

    
    public String[] SeparadorDeHorarios(String Dia,String Grado,String Grupo){
        String CodigoCompleto =  BusquedaHoraDelAlumno(Dia,Grado, Grupo );
        
        String uniforme = CodigoCompleto.substring(0, 1);   // "1"
        String horaEntrada = CodigoCompleto.substring(1, 3);   // "23"
        String minutosEntrada = CodigoCompleto.substring(3, 5);   // "45"
        String horaSalida = CodigoCompleto.substring(5, 7);   // "67"
        String minutosSalida = CodigoCompleto.substring(7, 9);   // "89"
        
        return new String[]{uniforme,horaEntrada,minutosEntrada,horaSalida,minutosSalida} ;
    }

    
    public String[] HoraEnFormato24hrs() {
        
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
        
        
        return new String[]{horas, minutos};
        
        
    }
    
    public void ComparadorDeEntrada(String Codigo){
        
        String Dia = SelectorDeDiaSemana();
        //System.out.println("Dia es: " + Dia);
        String Semestre = AsignadorDeSemestre(Codigo);
        //System.out.println("Semestre es: " + Semestre);
        
        String[] Datos =  BuscadorDeAlumno(Codigo);
        //System.out.println("Grupo es: " + Datos[3]);
        
        String[] horasEntradaAlumno = SeparadorDeHorarios(Dia, Semestre, Datos[3]);
        String[] horasEntradaActual = HoraEnFormato24hrs();
        
       //Organizador De horas
       int HoraAlumno = Integer.parseInt(horasEntradaAlumno[1]);
       int HoraActual = Integer.parseInt(horasEntradaActual[0]);
       
       System.out.println("La hora del alumno a comparar es: " + HoraAlumno);
       System.out.println("La hora actual a comparar es: " + HoraActual);
       
       //Organizador De minutos
       int MinutosAlumno = Integer.parseInt(horasEntradaAlumno[2]);
       int MinutosActual = Integer.parseInt(horasEntradaActual[1]);
       
       System.out.println("Los minutos del alumno a comparar es: " + MinutosAlumno);
       System.out.println("La minutos actual a comparar es: " + MinutosActual);
       
       int minutosTotalesAlumno = HoraAlumno * 60 + MinutosAlumno;
        int minutosTotalesActual = HoraActual * 60 + MinutosActual;
        
        int diferenciaMinutos = Math.abs(minutosTotalesAlumno - minutosTotalesActual);
        
        
       //Comparador y asignador
       if(HoraAlumno <= HoraActual){ 
           if( MinutosAlumno == MinutosActual || MinutosAlumno > MinutosActual){
               ImagenMarcoVerde();
           }else if(diferenciaMinutos <= 10 && MinutosAlumno < MinutosActual){
               ImagenMarcoAmarillo();
           }else{
               ImagenMarcoRojo();
           }
       }else{
         ImagenMarcoVerde();  
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
    
    public String SelectorDeUniforme(String Dia,String Grado,String Grupo){
        String[] Datos =  SeparadorDeHorarios(Dia,Grado,Grupo); 
        
        String BaseDatos = "";
        switch(Datos[0]){
            case "1":
                BaseDatos = "Oficial";
                break;
            case "2":
                BaseDatos = "Deportivo";
                break;
            case "3":
                BaseDatos = "Alimentos";
                break;
            case "4":
                BaseDatos = "Enfermeria";
                break;
            case "5":
                BaseDatos = "Puericultura";
                break;
                 
        }
        System.out.println("Se supone que el uniforne es: " + BaseDatos);
        return BaseDatos;
    }
    
    
    
    public void EnvioDeregistro(String Nombre,String Apellidos, String Grado, String Grupo, String Matricula){
            try (Connection connection = dateBaseconnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                 "INSERT INTO registros (Nombre, Grado, Grupo, Matricula, Fecha, Hora) VALUES (?, ?, ?, ?, ?, ?)")) {

            // Obtener la fecha y la hora actuales
            LocalDate fechaActual = LocalDate.now();
            LocalTime horaActual = LocalTime.now();
            String NombreCompleto = Nombre + " " +  Apellidos; 
            // Configurar los parámetros de la consulta
            statement.setString(1, NombreCompleto);
            statement.setString(2, Grado);
            statement.setString(3, Grupo);
            statement.setString(4, Matricula);
            statement.setDate(5, java.sql.Date.valueOf(fechaActual));
            statement.setTime(6, java.sql.Time.valueOf(horaActual));

            // Ejecutar la consulta
            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Los datos se han registrado correctamente.");
            } else {
                System.out.println("No se pudieron ingresar los datos.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();  // Manejo de errores
        } 
    }
    
    public void ConexionesConBd() {
        ConexionBd();
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        
        // Metodos Indispensables no clasificados xd
        LimitadorLongutid();
        LimpiadorDeCodigo();
        
        VerificacionDeLongitud();

        
        HoraEnPantalla();
        ConexionesConBd();
        
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

    @FXML
    private void btnInformeDeEntrada(ActionEvent event) {
    }

    @FXML
    private void btnInformeDeSalida(ActionEvent event) {
    }
}
