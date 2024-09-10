/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexion.conexionDeConsulta;
import Conexion.conexionDeRegistro;
import MetodosExtra.ComparadorDeHoras;
import MetodosExtra.ExtractorDatosConexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Rodrigo
 */
public class ControladorPaginaPrincipal implements Initializable {

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
    @FXML
    private Button txtButtonUniforme;
    @FXML
    private Button txtButtonCorte;
    @FXML
    private Button txtButtonCredencial;
    @FXML
    private Button txtButtonPersonalizado;
    @FXML
    private Button txtButtonUns;

    /**
     * Initializes the controller class.
     */
    
    
    
    private String[] BuscadorDeAlumno (String codigo){

        String query = "SELECT Nombre, apellidoPaterno, apellidoMaterno, Grado, Grupo FROM alumnos WHERE matricula = ?";
        
        try (Connection connection = conexionDeConsulta.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, codigo);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String Nombre = resultSet.getString("Nombre");
                    String apellidoP = resultSet.getString("apellidoPaterno");
                    String apellidoM = resultSet.getString("apellidoMaterno");
                    String Grupo = resultSet.getString("Grupo");
                    String Grado = resultSet.getString("Grado");

                    String Apellidos = apellidoP + " " + apellidoM;
                    
                    System.out.println("Nombre: " + Nombre);
                    System.out.println("Apellido Paterno: " + apellidoP);
                    System.out.println("Apellido Materno: " + apellidoM);
                    System.out.println("Grupo: " + Grupo);
                    System.out.println("Grado: " + Grado);
                    
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
    
    public void SeteadorImagenUniforme(String Codigo){
        String Dia = SelectorDeDiaSemana();
        //System.out.println("Dia es: " + Dia);
        String Semestre = AsignadorDeSemestre(Codigo);
        //System.out.println("Semestre es: " + Semestre);
        String Grupo = txtGrupo.getText();
        
        String[] datos =  SeparadorDeHorarios(Dia,Semestre,Grupo);
        
        String BaseDatos = "";
        switch(datos[0]){
            case "1":
                uniformeOficial();
                
                break;
            case "2":
                uniformeDeportivo();
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
    }

    public void VerificacionDeLongitud(){
        
        txtCodigo.textProperty().addListener((observable, oldValue, newValue) -> {
            
            if(newValue.length() == 14){
               String[] Datos = BuscadorDeAlumno(newValue);
               String estado = ComparadorDeEntrada(newValue);
               SeteadorDeUniforme(newValue);
               SeteadorImagenUniforme(newValue);
               EnvioDeRegistrosGeneral(Datos[0], Datos[1], Datos[2], Datos[3], newValue, estado);
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
        String Grado = Datos[2];
        switch(Grado){
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

        try (Connection connection = conexionDeConsulta.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
    
    public String ComparadorDeEntrada(String Codigo){
        
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
       String Estado = "";
       if(HoraAlumno <= HoraActual){ 
           if( MinutosAlumno == MinutosActual || MinutosAlumno > MinutosActual){
               ImagenMarcoVerde();
               Estado = "Puntual";
               return Estado;
           }else if(diferenciaMinutos <= 10 && MinutosAlumno < MinutosActual){
               ImagenMarcoAmarillo();
               Estado = "Tolerancia";
               return Estado;
           }else{
               
               ImagenMarcoRojo();
               Estado = "Retardo";
               return Estado;
           }
       }else{
           ImagenMarcoVerde(); 
           Estado = "Puntual";
           return Estado;
          
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
    
    private void uniformeOficial(){
        String imagePath = "/Imagenes/oficial.png";
        
        Image img = new Image(imagePath);
        
        imgFotoPerfil.setImage(img);
    }
    private void uniformeDeportivo(){
        String imagePath = "/Imagenes/deportivo.png";
        
        Image img = new Image(imagePath);
        
        imgFotoPerfil.setImage(img);
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
    
    
    //
    public boolean Conexion (String Query, String TipoDato){
        
        try(Connection connection = conexionDeConsulta.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                Query)){
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String datos = resultSet.getString(TipoDato);
                    System.out.println("Tiene: " + datos);
                    if(datos == null){
                        return true;
                    }else{
                        return false;
                    }
                }
            }
        }catch(SQLException ex){
        
        }
        
        return false;
    }
    public void SubidaDeSancionesAutomatica(String Sancion){
        String matricula = SelectorDeMatricula();
        for (int i = 1; i < 4; i++) {
            boolean editable = false;
            boolean editable2 = false;
            boolean editable3 = false;
            String dato = "";
            String dato2 = "";
            String dato3 = "";
            switch(i){
                case 1:
                    String query = "SELECT Reporte FROM `registros` WHERE Matricula = '" +matricula+ "';";
                    dato = "Reporte";
                    editable = Conexion(query, dato);
                    System.out.println(editable);
                    break;
                case 2:
                    String query2 = "SELECT ReporteDos FROM `registros` WHERE Matricula = '" +matricula+ "';";
                    dato2 = "ReporteDos";
                    editable2 = Conexion(query2, dato2);
                    System.out.println(editable2);
                    break;
                case 3:
                    String query3 = "SELECT ReporteTres FROM `registros` WHERE Matricula = '" +matricula+ "';";
                     dato3 = "ReporteTres";
                    editable3 = Conexion(query3, dato3);
                    System.out.println(editable3);
                    break;
            }
            if (editable) {
                SubidaDeSanciones(matricula, dato, Sancion);
                break;
            } else if (editable2) {
                SubidaDeSanciones(matricula, dato2, Sancion);
                break;
            } else if (editable3) {
                SubidaDeSanciones(matricula, dato3, Sancion);
                break;
            } else {
                // Exceso de sanciones
            }

        }
    }
    public String SelectorDeMatricula(){
        try(Connection connection = conexionDeConsulta.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                "SELECT Matricula FROM registros ORDER BY Id_registro DESC LIMIT 1")){
             
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
    public void SubidaDeSanciones(String Matricula, String Tipo,String Sancion){
        
        try(Connection connection = conexionDeRegistro.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                "UPDATE registros SET " + Tipo + " = ? WHERE Matricula = ? ")){
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
    
    private Time ComprobadorDeTiempo(String Matricula) {
        try(Connection connection = conexionDeConsulta.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                "SELECT Hora FROM registros WHERE Matricula = ?")){
                statement.setString(1, Matricula);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Time existencia = resultSet.getTime("Hora");
                   return existencia; 

                }
            }
        }catch(SQLException ex){
        
        }
        
        return null; // Aquí debería ir la lógica que devuelve un Time
    }
    
    public boolean Comparador(String Matricula) {
        // Supongamos que tienes un método que te da la hora de la base de datos
        Time Horabd = ComprobadorDeTiempo(Matricula);

        // Crear instancia de ComparadorDeHoras
        ComparadorDeHoras comparador = new ComparadorDeHoras();

        // Usar el método compararHoras
        boolean diferencia = comparador.compararHoras(Horabd);
        
        return diferencia;
    }

    
    
    
    
    
    
    
    public boolean ComprobadorDeExistencia(String Matricula){
        
        try(Connection connection = conexionDeConsulta.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                "SELECT EXISTS (SELECT 1 FROM registros WHERE Matricula = ?) AS existe")){
                statement.setString(1, Matricula);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    boolean existencia = resultSet.getBoolean("existe");
                    System.out.println("Existe en la base de datos: " + existencia);

                    if (!existencia) {
                        System.out.println("Entrada detectada asdf asdfas dfasdf asd");
                        return false;
                    } else {
                        System.out.println("Salida detectada asdfasdfasd");
                        return true;
                    }
                }
            }
        }catch(SQLException ex){
        
        }
        return true;
    }   
    
    public boolean ComprobadorDeExistenciaSalida (String Matricula){
        
        try(Connection connection = conexionDeConsulta.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                "SELECT EXISTS (SELECT 1 FROM salida WHERE Matricula = ?) AS existe")){
                statement.setString(1, Matricula);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    boolean existencia = resultSet.getBoolean("existe");
                    System.out.println("Existe en la base de datos: " + existencia);

                    if (!existencia) {
                        System.out.println("Entrada detectada asdf asdfas dfasdf asd");
                        return false;
                    } else {
                        System.out.println("Salida detectada asdfasdfasd");
                        return true;
                    }
                }
            }
        }catch(SQLException ex){
        
        }
        return true;
    }
    
    public void EnvioDeRegistrosGeneral(String Nombre,String Apellidos , String Grado, String Grupo,String Matricula,String Estado){
        boolean existencia = ComprobadorDeExistencia(Matricula);
        boolean existenciaSalida = ComprobadorDeExistenciaSalida(Matricula);
        
        
        
        if(!existencia){ //
            //Entrada
            
            EnvioDeregistroEntrada(Nombre, Apellidos, Grado, Grupo, Matricula, Estado);
            
        }else if(existencia){
            //Salida
            boolean tolerancia = Comparador(Matricula);
            if(tolerancia){
                if(!existenciaSalida){
                    
                    EnvioDeregistroSalida(Nombre, Apellidos, Grado, Grupo, Matricula);
                }else{
                    System.out.println("YA esxiste un registro");
                }
                
            }
        }
        
    
    }

    
    public void EnvioDeregistroEntrada(String Nombre,String Apellidos, String Grado, String Grupo, String Matricula,String Estado){
        String nombreTabla = NombreTablaActualEntrada();
        
        try (Connection connection = conexionDeRegistro.getConnection();
            PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO " + nombreTabla + " (Nombre, Grado, Grupo, Matricula, Fecha, Hora, Estado) VALUES (?, ?, ?, ?, ?, ?, ?)")){

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
            statement.setString(7, Estado);

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
    
    public void EnvioDeregistroSalida(String Nombre,String Apellidos, String Grado, String Grupo, String Matricula){

        
            try (Connection connection = conexionDeRegistro.getConnection();
            PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO salida (Nombre, Grado, Grupo, Matricula, Fecha, Hora) VALUES (?, ?, ?, ?, ?, ?)")) {

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
    
    
    //Tablas entrada
    public String NombreTablaActualEntrada(){
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
        String nombreTabla = fechaActual.format(formatter);
        
        String TablaEntrada = "E-" +nombreTabla;
        
        return TablaEntrada;
    }
    
    public void CreadorDeTablasDeEntrada(String tabla){
        // Obtener la fecha actual
            // Consulta SQL para crear la tabla
            String query = "CREATE TABLE `" + tabla + "` (\n" +
                "  `Id_registro` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `Nombre` text NOT NULL,\n" +
                "  `Grado` text NOT NULL,\n" +
                "  `Grupo` text NOT NULL,\n" +
                "  `Matricula` text NOT NULL,\n" +
                "  `Fecha` date NOT NULL,\n" +
                "  `Hora` time NOT NULL,\n" +
                "  `Estado` text NOT NULL,\n" +
                "  `Reporte` text DEFAULT NULL,\n" +
                "  `ReporteDos` text DEFAULT NULL,\n" +
                "  `ReporteTres` text DEFAULT NULL,\n" +
                "  `Descripcion` text DEFAULT NULL,\n" +
                "  PRIMARY KEY (`Id_registro`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;";

            try (Connection connection = conexionDeRegistro.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
                // Ejecutar la consulta para crear la tabla
                statement.executeUpdate();
                System.out.println("Tabla creada: "  + tabla);
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    
    public void GestionadorDeExistenciaDeTablasEntrada(){
        String nombreTabla = NombreTablaActualEntrada();
        
        boolean existencia = ComprobadorDeExistenciaDeTableEntrada(nombreTabla);
        
        if(!existencia){
            CreadorDeTablasDeEntrada(nombreTabla);
        }else{
            System.out.println("ya esxiste la Tabla: " + nombreTabla);
        }
        
    }
    
    public boolean ComprobadorDeExistenciaDeTableEntrada(String dia){
        
        ExtractorDatosConexion.ConfiguracionDB config = ExtractorDatosConexion.cargarConfiguracion();
        String bd = config.bd;
        String query = "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = ? AND table_name = ?";
        
        try(Connection connection = conexionDeConsulta.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);)
            {  statement.setString(1,bd);
               statement.setString(2, dia);
            
               try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    
                   int cont  = resultSet.getInt(1);
                   if (cont > 0 ){
                       return true;
                   }    
                }
            }
        }
        catch(SQLException ex){
                    
        }       
        return false;
    }
    
    
    //Tablas Salida
    public String NombreTablaActualSalida(){
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
        String nombreTabla = fechaActual.format(formatter);
        
        String TablaSalida = "S-" +nombreTabla;
        
        return TablaSalida;
    }
    
    public void CreadorDeTablasDeSalida(String tabla){
        // Obtener la fecha actual
            // Consulta SQL para crear la tabla
            String query = "CREATE TABLE `" + tabla + "` (\n" +
                "  `Id_registro` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `Nombre` text NOT NULL,\n" +
                "  `Grado` text NOT NULL,\n" +
                "  `Grupo` text NOT NULL,\n" +
                "  `Matricula` text NOT NULL,\n" +
                "  `Fecha` date NOT NULL,\n" +
                "  `Hora` time NOT NULL,\n" +
                "  `Estado` text NOT NULL,\n" +
                "  PRIMARY KEY (`Id_registro`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;";

            try (Connection connection = conexionDeRegistro.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
                // Ejecutar la consulta para crear la tabla
                statement.executeUpdate();
                System.out.println("Tabla creada: "  + tabla);
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    
    public void GestionadorDeExistenciaDeTablasSalida(){
        String nombreTabla = NombreTablaActualEntrada();
        
        boolean existencia = ComprobadorDeExistenciaDeTableSalida(nombreTabla);
        
        if(!existencia){
            CreadorDeTablasDeEntrada(nombreTabla);
        }else{
            System.out.println("ya esxiste la Tabla: " + nombreTabla);
        }
        
    }
    
    public boolean ComprobadorDeExistenciaDeTableSalida(String dia){
        
        ExtractorDatosConexion.ConfiguracionDB config = ExtractorDatosConexion.cargarConfiguracion();
        String bd = config.bd;
        String query = "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = ? AND table_name = ?";
        
        try(Connection connection = conexionDeConsulta.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);)
            {  statement.setString(1,bd);
               statement.setString(2, dia);
            
               try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    
                   int cont  = resultSet.getInt(1);
                   if (cont > 0 ){
                       return true;
                   }    
                }
            }
        }
        catch(SQLException ex){
                    
        }       
        return false;
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GestionadorDeExistenciaDeTablasEntrada();
        // Metodos Indispensables no clasificados xd
        LimitadorLongutid();
        LimpiadorDeCodigo();
        
        VerificacionDeLongitud();

        
        HoraEnPantalla();
        
    }

    @FXML
    private void btnAgregarAlumno(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ArchivosFXML/VentanaAgregarAlumnos.fxml"));
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
            Logger.getLogger(ControladorPaginaPrincipal.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private void btnAsignarHorarios(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ArchivosFXML/VentanaAsignarHorarios.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Asignar horarios");
            stage.setScene(new Scene(root, 1000, 500));
            stage.setIconified(false);
            stage.setResizable(false);
            stage.show();
            Image icon = new Image("file:C:/Users/Rodrigo/Downloads/cecyteh_horizontal1.png");
            stage.getIcons().add(icon);
        } catch (IOException e) {
            Logger.getLogger(ControladorPaginaPrincipal.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private void btnInformeDeEntrada(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ArchivosFXML/VentanaInformeEntrada.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Informe de entrada");
            stage.setScene(new Scene(root, 1900, 900));
            stage.setIconified(false);
            stage.setResizable(false);
            stage.show();
            Image icon = new Image("file:C:/Users/Rodrigo/Downloads/cecyteh_horizontal1.png");
            stage.getIcons().add(icon);
        } catch (IOException e) {
            Logger.getLogger(ControladorPaginaPrincipal.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @FXML
    private void btnInformeDeSalida(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ArchivosFXML/VentanaInformeSalida.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Informe de salida");
            stage.setScene(new Scene(root, 1200, 750));
            stage.setIconified(false);
            stage.setResizable(false);
            stage.show();
            Image icon = new Image("file:C:/Users/Rodrigo/Downloads/cecyteh_horizontal1.png");
            stage.getIcons().add(icon);
        } catch (IOException e) {
            Logger.getLogger(ControladorPaginaPrincipal.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private void bntUniforme(ActionEvent event) {
        if(!txtNombre.getText().isEmpty()){
            SubidaDeSancionesAutomatica("Uniforme incompleto");
        }
        
    }

    @FXML
    private void btnCorte(ActionEvent event) {
        if(!txtNombre.getText().isEmpty()){
            SubidaDeSancionesAutomatica("Corte de cabello");
        }
       
    }
    
    @FXML
    private void btnuns(ActionEvent event) {
        
        if(!txtNombre.getText().isEmpty()){
            SubidaDeSancionesAutomatica("Uñas");
        }
        
    }

    @FXML
    private void btnCredencial(ActionEvent event) {
        
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ArchivosFXML/VentanaSancionCredencial.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Busqueda de alumno");
            stage.setScene(new Scene(root, 700,420));
            stage.setIconified(false);
            stage.setResizable(false);
            stage.show();
            Image icon = new Image("file:C:/Users/Rodrigo/Downloads/cecyteh_horizontal1.png");
            stage.getIcons().add(icon);
        } catch (IOException e) {
            Logger.getLogger(ControladorPaginaPrincipal.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
    
    public void setTextFieldValue(String value) {
        txtCodigo.setText(value);
    }

    @FXML
    private void btnPersonalizado(ActionEvent event) {
        
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ArchivosFXML/VentanaSancionPersonalizada.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Generar un reporte personalizado");
            stage.setScene(new Scene(root, 450, 250));
            stage.setIconified(false);
            stage.setResizable(false);
            stage.show();
            Image icon = new Image("file:C:/Users/Rodrigo/Downloads/cecyteh_horizontal1.png");
            stage.getIcons().add(icon);
        } catch (IOException e) {
            Logger.getLogger(ControladorPaginaPrincipal.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
    
}
