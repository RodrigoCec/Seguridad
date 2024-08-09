package Controladores;


import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Rodrigo
 */
public class VentanaHorariosController implements Initializable {

    @FXML
    private TextField txtGrupo;
    @FXML
    private TextField txtGrado;
    @FXML
    private TextField txtMinutosEntradaViernes;
    @FXML
    private TextField txtMinutosSalidaLunes;
    @FXML
    private TextField txtHoraSalidaLunes;
    @FXML
    private TextField txtHoraEntradaLunes;
    private MenuButton txtTurnoEntrada;
    @FXML
    private MenuButton btnUniformeLunes;
    @FXML
    private TextField txtMinutosSalidaMartes;
    @FXML
    private TextField txtHoraSalidaMartes;
    @FXML
    private TextField txtHoraEntradaMartes;
    @FXML
    private MenuButton txtTurnoEntradaMartes;
    @FXML
    private MenuButton btnUniformeMartes;
    @FXML
    private TextField txtMinutosSalidaMiercoles;
    @FXML
    private TextField txtHoraSalidaMiercoles;
    private TextField txtMInutosEntradaMiercoles;
    @FXML
    private TextField txtHoraEntradaMiercoles;
    @FXML
    private MenuButton txtTurnoEntradaMiercoles;
    @FXML
    private MenuButton btnUniformeMiercoles;
    @FXML
    private TextField txtMinutosSalidaJueves;
    @FXML
    private TextField txtHoraSalidaJueves;
    private TextField txtMInutosEntradaJueves;
    @FXML
    private TextField txtHoraEntradaJueves;
    @FXML
    private MenuButton txtTurnoEntradaJueves;
    @FXML
    private MenuButton btnUniformeJueves;
    @FXML
    private TextField txtMinutosSalidaViernes;
    @FXML
    private TextField txtHoraSalidaViernes;
    @FXML
    private TextField txtHoraEntradaViernes;
    @FXML
    private MenuButton txtTurnoEntradaViernes;
    @FXML
    private MenuButton btnUniformeViernes;
    @FXML
    private TextField txtMinutosEntradaLunes;
    @FXML
    private MenuButton txtTurnoEntradaLunes;
    @FXML
    private MenuItem txtAmEntradaLunes;
    @FXML
    private MenuItem txtPmEntradaLunes;
    @FXML
    private MenuButton txtTurnoSalidaLunes;
    @FXML
    private MenuItem txtAmSalidaLunes;
    @FXML
    private MenuItem txtPmSalidaLunes;
    @FXML
    private MenuItem txtOficialLunes;
    @FXML
    private MenuItem txtDeportivoLunes;
    @FXML
    private MenuItem txtAlimentosLunes;
    @FXML
    private MenuItem txtEnfermeriaLunes;
    @FXML
    private MenuItem txtPuericulturaLunes;
    @FXML
    private MenuButton txtTurnoSalidaMartes;
    @FXML
    private MenuItem txtAmSalidaMartes;
    @FXML
    private MenuItem txtPmSalidaMartes;
    @FXML
    private MenuItem txtAmEntradaMartes;
    @FXML
    private MenuItem txtPmEntradaMartes;
    @FXML
    private MenuButton txtTurnoSalidaMiercoles;
    @FXML
    private MenuItem txtAmSalidaMiercoles;
    @FXML
    private MenuItem txtPmSalidaMiercoles;
    @FXML
    private TextField txtMinutosEntradaMiercoles;
    @FXML
    private MenuItem txtAmEntradaMiercoles;
    @FXML
    private MenuItem txtPmEntradaMiercoles;
    @FXML
    private MenuButton txtTurnoSalidaJueves;
    @FXML
    private MenuItem txtAmSalidaJueves;
    @FXML
    private MenuItem txtPmSalidaJueves;
    @FXML
    private TextField txtMinutosEntradaJueves;
    @FXML
    private MenuItem txtAmEntradaJueves;
    @FXML
    private MenuItem txtPmEntradaJueves;
    @FXML
    private MenuButton txtTurnoSalidaViernes;
    @FXML
    private MenuItem txtAmSalidaViernes;
    @FXML
    private MenuItem txtPmSalidaViernes;
    @FXML
    private MenuItem txtAmEntradaViernes;
    @FXML
    private MenuItem txtPmEntradaViernes;
    @FXML
    private MenuItem txtOficialJueves;
    @FXML
    private MenuItem txtDeportivoJueves;
    @FXML
    private MenuItem txtAlimentosJueves;
    @FXML
    private MenuItem txtEnfermeriaJueves;
    @FXML
    private MenuItem txtPuericulturaJueves;
    @FXML
    private MenuItem txtOficialMiercoles;
    @FXML
    private MenuItem txtDeportivoMiercoles;
    @FXML
    private MenuItem txtAlimentosMiercoles;
    @FXML
    private MenuItem txtEnfermeriaMiercoles;
    @FXML
    private MenuItem txtPuericulturaMiercoles;
    @FXML
    private MenuItem txtOficialMartes;
    @FXML
    private MenuItem txtDeportivoMartes;
    @FXML
    private MenuItem txtAlimentosMartes;
    @FXML
    private MenuItem txtEnfermeriaMartes;
    @FXML
    private MenuItem txtPuericulturaMartes;
    @FXML
    private MenuItem txtOficialViernes;
    @FXML
    private MenuItem txtDeportivoViernes;
    @FXML
    private MenuItem txtAlimentosViernes;
    @FXML
    private MenuItem txtEnfermeriaViernes;
    @FXML
    private MenuItem txtPuericulturaViernes;
    
   

    /**
     * Initializes the controller class.
     */
    
    
    // Datos de la Conexion a Base de datos
    private static final String bd = "basedatosprueba";
    private static final String direccion = "jdbc:mysql://localhost:3306/" + bd;
    private static final String usuario = "root";
    private static final String password = "";

    // Conexión a la base de datos
    private static Connection conexion;

    // Código de conexión
    public static Connection ConexionBd() {
        try {
        if (conexion == null || conexion.isClosed()) {
            conexion = DriverManager.getConnection(direccion, usuario, password);
            System.out.println("Conexión exitosa");
        }
    } catch (SQLException e) {
        Logger.getLogger(VentanaHorariosController.class.getName()).log(Level.SEVERE, "Error de conexión", e);
    }
    return conexion;
    }
    @FXML
    private TextField txtMinutosEntradaMartes;
    
    
  
     //Metodos para obtener datos
     
    public String DatosDeBD(String Dia,String Grupo){
         String CodigoHorario =  "";
         
            
        String query = "SELECT " + Dia + " FROM primersemestre WHERE Grupo = '" + Grupo + "'";
        try (Connection connection = ConexionBd();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    CodigoHorario = resultSet.getString(Dia);
                    
                    
                    //System.out.println(CodigoHorario);
                }
                
            }
        } catch (SQLException error) {
            System.out.println("Error de obtencion de datos: " + error);
        }
        
        return CodigoHorario;
     }
    
    public String DatosDeBD(String Dia, String Semestre, String Grupo) {
        String CodigoHorario = "";
        
        String query = "SELECT " + Dia + " FROM " + Semestre + " WHERE Grupo = ?";

        try (Connection connection = ConexionBd();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, Grupo);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    CodigoHorario = resultSet.getString(1);
                }
            }
        } catch (SQLException error) {
            System.out.println("Error de obtencion de datos: " + error);
        }

        return CodigoHorario;
    }

     
     
     
     
     
     
    //Convertidores de tiempo
    public String CambioDeFormatoHorario(String Hora){
        boolean cero = false;

        try {
            int HorasFormato24hrs = Integer.parseInt(Hora);
            int HoraFormato12Hrs = HorasFormato24hrs % 12;

            if (HoraFormato12Hrs == 0) {
                HoraFormato12Hrs = 12;
            } else if (HoraFormato12Hrs < 10) {            
                cero = true;        
            }

            String Formato12 = Integer.toString(HoraFormato12Hrs);

            if (cero) {
                Formato12 = "0" + Formato12;
            }

            return Formato12;
        } catch (NumberFormatException e) {
            System.err.println("Error: Hora no es un número válido - " + e.getMessage());
            return "00"; // valor por defecto en caso de error
        }
    }
     
     
    
     
    //Metodo para mostrar datos en los recuadros de la ventana
    public void MostradorEnPantalla(TextField Campo, String time){
        
        Campo.setText(time);       
    
    }
    
    
    //MetodoActualizador De Base de datos;
    private void ActualizarCodigoHorabd(String dia, String newValue, String grupo) {
        String query = "UPDATE primersemestre SET " + dia + " = ? WHERE Grupo = ?";
        try (Connection connection = ConexionBd();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, newValue);
            preparedStatement.setString(2, grupo);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public String Desconvertidor(String Hora, String Horario){
        
        int hora = Integer.parseInt(Hora);
        String CodigoHora = "";

        if (Horario.equals("AM")) {
            if (hora == 12) {
                hora = 0; // 12 AM es 00 en formato de 24 horas
            }
            CodigoHora = String.format("%02d", hora);
        } else if (Horario.equals("PM")) {
            if (hora != 12) {
                hora += 12;
            }
            CodigoHora = Integer.toString(hora);
        } else {
            System.out.println("Horario no válido");
        }

        return CodigoHora;

       
    }
    
    
    
    
    public void FormatoAmyPm(String Hora){
        //Ayuda a identificar y colocar Am o pm segun el horario devido a las diferencias de 12 y 24 horas que se trabajan
        //Solo muestra apartir de iniciar el programa no cambia de acuerdo a intereacciones
        int hora = Integer.parseInt(Hora);
        
        if(hora < 12){
            txtTurnoEntradaLunes.setText("AM");
        }else{
            txtTurnoEntradaLunes.setText("PM");
        }
    }
    
    
    
    //Compurueba que sea de dos digitos si no no hace empieza nada
    public String ComprobadorDeLongitud(TextField textField){
        
        String Codigo = "";
        
        textField.textProperty().addListener((observable, ContAnterior, ContActual) -> {
            if (ContActual.length() == 2) {
                
                
            }else {
                System.out.println("El textfiel no tiene 2 digitos, No empieza nada");
            }
        });
        
        return Codigo;
    }
    
    
    
    
    public void ControladorDePrueba() {
        String Grupo = txtGrupo.getText();
        String Datos = DatosDeBD("Lunes", Grupo); // Se tiene que Automatizar ///Creo que ya

        // Verificar que los datos no son nulos ni vacíos
        if (Datos == null || Datos.isEmpty()) {
            System.err.println("Error: Datos de la base de datos son nulos o vacíos");
            return;
        }

        // Verificar que los datos tienen el formato esperado antes de separarlos
        String[] Fechas;
        try {
            Fechas = SeparadorHoraMinutos(Datos);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            return;
        }

        if (Fechas[0] == null || Fechas[0].isEmpty() || Fechas[1] == null || Fechas[1].isEmpty()) {
            System.err.println("Error: Las fechas separadas son nulas o vacías");
            return;
        }

        FormatoAmyPm(Fechas[0]);
        MostradorEnPantalla(txtHoraEntradaLunes, CambioDeFormatoHorario(Fechas[0]));
        MostradorEnPantalla(txtMinutosEntradaLunes, Fechas[1]);
        
        
        
        
        //MetodoDeActualizacion
         txtHoraEntradaLunes.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() == 2) {
               
                String filteredValue = extractorDeCodigoSeparado(newValue, Tipo.HORA_ENTRADA, Datos, txtTurnoEntradaLunes);
                ActualizarCodigoHorabd("Lunes", filteredValue, "A");
            } else if (newValue.length() == 1) {
                System.out.println("No es de dos digitos pa");
            }
        });
         
        
    }
    
    
    
    
   
    
    
  
    
    
    
    
    
    
    
    

  
    //Metodo para sacar el semestre segun el grupo
    public String AsignadorDeSemestre(){
        String Semestre = txtGrado.getText(); 
        String BaseDatos = "";
        switch(Semestre){
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
        System.out.println(BaseDatos);
        return BaseDatos;
    }
    
    
    
    
    //------> Metodos de Logica(Que separan, asignan y gestionan Cosas que no se ven graficamente)
    
    
     public String[] SeparadorHoraMinutos(String Codigo){
        
        if (Codigo == null || Codigo.length() < 5) {
            throw new IllegalArgumentException("El código proporcionado es inválido: " + Codigo);
        }

        String Hora;
        String Minutos;

        try {
            Hora = Codigo.substring(1, 3);
            Minutos = Codigo.substring(3, 5);
        } catch (StringIndexOutOfBoundsException e) {
            System.err.println("Error al separar hora y minutos: " + e.getMessage());
            return new String[]{"00", "00"}; // o cualquier valor por defecto que consideres adecuado
        }
        
        return new String[]{Hora, Minutos};
        
        //-------) Codigo Obsoleto Falta Temas de Reconocimiento
        //Este metodo Al obtener el codigo: "123456789" lo que hace es 
        //separar la hora y despues lo devuelve
    }

    
     
     
    //Supuesto Metodo De Separacion y reconocimiento de codigo para su remplazo y actualizacion
    public enum Tipo {
        
        HORA_ENTRADA,
        MINUTOS_ENTRADA,
        HORA_SALIDA,
        MINUTOS_SALIDA  
    }
    
    public String extractorDeCodigoSeparado(TextField time, Tipo tipo, String codigo, MenuItem amPmButton) {
        String anterior = "";
        String editor = "";
        String posterior = "";

        // Descomposición del código según el tipo
        switch (tipo) {
            case HORA_ENTRADA:
                anterior = codigo.substring(0, 1);
                editor = codigo.substring(1, 3);
                posterior = codigo.substring(3, 9);
                break;
            case MINUTOS_ENTRADA:
                anterior = codigo.substring(0, 3);
                editor = codigo.substring(3, 5);
                posterior = codigo.substring(5, 9);
                break;
            case HORA_SALIDA:
                anterior = codigo.substring(0, 5);
                editor = codigo.substring(5, 7);
                posterior = codigo.substring(7, 9);
                break;
            case MINUTOS_SALIDA:
                anterior = codigo.substring(0, 7);
                editor = codigo.substring(7, 9);
                break;
        }

        String codigoRemplazar = time.getText();
        String amPm = amPmButton.getText().toUpperCase();

        int hora = Integer.parseInt(codigoRemplazar);
        if (amPm.equals("PM") && hora != 12) {
            hora += 12;
        } else if (amPm.equals("AM") && hora == 12) {
            hora = 0;
        }

        String horaConvertida = String.format("%02d", hora);

        // Reconstrucción del código completo
        String codigoCompleto = anterior + horaConvertida;
        if (tipo != Tipo.MINUTOS_SALIDA) {
            codigoCompleto += posterior;
        }

        System.out.println("En este caso el anterior es: " + anterior);
        System.out.println("En este caso el editado es: " + editor);
        System.out.println("En este caso el posterior es: " + posterior);
        System.out.println("Codigo Remplazar: " + codigoRemplazar);
        System.out.println("Codigo Completo: " + codigoCompleto);

        return codigoCompleto;
    }
    
    public String extractorDeCodigoSeparado(String time, Tipo tipo, String codigo, MenuButton amPmButton) {
        
        String anterior = "";
        String editor = "";
        String posterior = "";

        // Descomposición del código según el tipo
        switch (tipo) {
            case HORA_ENTRADA:
                anterior = codigo.substring(0, 1);
                editor = codigo.substring(1, 3);
                posterior = codigo.substring(3, 9);
                break;
            case MINUTOS_ENTRADA:
                anterior = codigo.substring(0, 3);
                editor = codigo.substring(3, 5);
                posterior = codigo.substring(5, 9);
                break;
            case HORA_SALIDA:
                anterior = codigo.substring(0, 5);
                editor = codigo.substring(5, 7);
                posterior = codigo.substring(7, 9);
                break;
            case MINUTOS_SALIDA:
                anterior = codigo.substring(0, 7);
                editor = codigo.substring(7, 9);
                break;
        }

        //String codigoRemplazar = time.getText();
        String amPm = amPmButton.getText().toUpperCase();

        int hora = Integer.parseInt(time);
        if (amPm.equals("PM") && hora != 12) {
            hora += 12;
        } else if (amPm.equals("AM") && hora == 12) {
            hora = 0;
        }

        String horaConvertida = String.format("%02d", hora);

        // Reconstrucción del código completo
        String codigoCompleto = anterior + horaConvertida;
        if (tipo != Tipo.MINUTOS_SALIDA) {
            codigoCompleto += posterior;
        }

        System.out.println("En este caso el anterior es: " + anterior);
        System.out.println("En este caso el editado es: " + editor);
        System.out.println("En este caso el posterior es: " + posterior);
        System.out.println("Codigo Remplazar: " + time);
        System.out.println("Codigo Completo: " + codigoCompleto);

        return codigoCompleto;
    }

    
    
    
    
    public enum DiasSemana {
        
        LUNES,
        MARTES,
        MIERCOLES,
        JUEVES,
        VIERNES
    }
    
    public void ActualizadorDeRecuadroMinutos(DiasSemana dia, TextField TextMinutos){
        
        String Grupo = txtGrupo.getText();
        
        String Dias = "";
        String Datos = "";
        
        switch (dia) {
            case LUNES:
                Datos = DatosDeBD("Lunes", Grupo);
                Dias = "Lunes";
                break;
            case MARTES:
                Datos = DatosDeBD("Martes", Grupo);
                Dias = "Martes";
                break;
            case MIERCOLES:
                Datos = DatosDeBD("Miercoles", Grupo);
                Dias = "Miercoles";
                break;
            case JUEVES:
                Datos = DatosDeBD("Jueves", Grupo);
                Dias = "Jueves";
                break;
            case VIERNES:
                Datos = DatosDeBD("Viernes", Grupo);
                Dias = "Viernes";
                break;
        }
        
        String DatosFinales = Datos;
        String DiasFinales = Dias;
        
        TextMinutos.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() == 2) {
                String filteredValue = extractorDeCodigoSeparado(newValue, Tipo.MINUTOS_ENTRADA, DatosFinales, txtTurnoEntradaLunes);
                ActualizarCodigoHorabd(DiasFinales, filteredValue, Grupo);
            } else if (newValue.length() == 1) {
                System.out.println("No es de dos digitos pa");
            }
        });
        
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    //Funciones de Asignacion de Am y Pm para cada Boton
    
    //---->Hora entrada Lunes
    @FXML
    private void AmEntradaLunes(ActionEvent event) {
        
        txtTurnoEntradaLunes.setText(txtAmEntradaLunes.getText());
        
        
        
        ///---->Purebas
        String Grupo = txtGrupo.getText();
        String Datos = DatosDeBD("Lunes", Grupo);
        String dev = extractorDeCodigoSeparado(txtHoraEntradaLunes, Tipo.HORA_ENTRADA, Datos, txtAmEntradaLunes);
       
        System.out.println("con el boton Am se manda esto: " + dev);
        ActualizarCodigoHorabd("Lunes", dev, Grupo);
        //String xd = txtTurnoEntradaLunes.getText();
        //CambiadorDeHorarios(txtHoraEntradaLunes, xd);
    }

    @FXML
    private void PmEntradaLunes(ActionEvent event) {
        
        ///---->Purebas
        
        
        String Grupo = txtGrupo.getText();
        String Datos = DatosDeBD("Lunes", Grupo);
        String dev = extractorDeCodigoSeparado(txtHoraEntradaLunes, Tipo.HORA_ENTRADA, Datos, txtPmEntradaLunes);
        System.out.println("con el boton Am se manda esto: " + dev);
        
        ActualizarCodigoHorabd("Lunes", dev, Grupo);
        
        
        txtTurnoEntradaLunes.setText(txtPmEntradaLunes.getText());
    
    }
    
    
    public void gestionarHorario(String dia, TextField txtHoraEntrada, TextField txtMinutosEntrada, TextField txtTurnoEntrada, TextField txtGrupo) {
        String grupo = txtGrupo.getText();
        String datos = DatosDeBD(dia, grupo);

        // Verificar que los datos no son nulos ni vacíos
        if (datos == null || datos.isEmpty()) {
            System.err.println("Error: Datos de la base de datos son nulos o vacíos");
            return;
        }

        // Verificar que los datos tienen el formato esperado antes de separarlos
        String[] fechas;
        try {
            fechas = SeparadorHoraMinutos(datos);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            return;
        }

        if (fechas[0] == null || fechas[0].isEmpty() || fechas[1] == null || fechas[1].isEmpty()) {
            System.err.println("Error: Las fechas separadas son nulas o vacías");
            return;
        }

        FormatoAmyPm(fechas[0]);
        MostradorEnPantalla(txtHoraEntrada, CambioDeFormatoHorario(fechas[0]));
        MostradorEnPantalla(txtMinutosEntrada, fechas[1]);

        // Método de actualización
        txtHoraEntrada.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() == 2) {//public String extractorDeCodigoSeparado(String time, Tipo tipo, String codigo, MenuButton amPmButton)
                //String filteredValue = extractorDeCodigoSeparado(txtHoraEntrada, Tipo.HORA_ENTRADA, datos, txtTurnoEntrada);
               // ActualizarCodigoHorabd(dia, filteredValue, "A");
            } else if (newValue.length() == 1) {
                System.out.println("No es de dos dígitos");
            }
        });
    }
    
    
    //MetodosParaTodosLosRecuadrosDeLunes
    public void HoraEntradaLunes(){
        
        //Horas
        
        
        
    
        //Restricciones
        RestriccionMinutos(txtMinutosEntradaLunes);
        RestriccionHoras(txtHoraEntradaLunes);
    }
    
    public void MetodosLunes(){
        
        HoraEntradaLunes();
    
    }
    
   
    
    
    
    
    
   
    //------> Limitadores de gestion  <-----
   
    private void restringirAPatron(TextField textField, String patron) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(patron)) {
                textField.setText(oldValue);
            }
        });
    }
    
    public void restringirANumeros(TextField textField) {
        restringirAPatron(textField, "\\d*");
    }

    public void restringirALetras(TextField textField) {
        restringirAPatron(textField, "[a-zA-Z]*");
    }

    public void RestriccionMinutos(TextField textField) {
        PauseTransition pause = new PauseTransition(Duration.millis(500));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            pause.setOnFinished(event -> {
                if (newValue.isEmpty()) {
                    return;
                }
                if (newValue.matches("\\d*")) {
                    try {
                        boolean seguir = true;
                        if("00".equals(newValue) ||"0".equals(newValue)){
                            seguir = true;
                        }else if (seguir){
                             int value = Integer.parseInt(newValue);
                            if (value < 1 || value > 59) {
                                textField.setText(oldValue);
                            } else {
                                if (newValue.length() == 1 && !newValue.equals("0")) {
                                    textField.setText("0" + newValue);
                                } else if (newValue.equals("00")) {
                                    textField.setText("0");
                                }
                            }
                        }
                        
                       
                    } catch (NumberFormatException e) {
                        textField.setText(oldValue);
                    }
                } else {
                    textField.setText(oldValue);
                }
            });
            pause.playFromStart();
        });
    }

    public void RestriccionHoras(TextField textField) {
        PauseTransition pause = new PauseTransition(Duration.millis(500));
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            pause.setOnFinished(event -> {
                if (newValue.isEmpty()) {
                    return;
                }
                if (newValue.matches("\\d*")) {
                    try {
                        boolean seguir = true;
                        if("00".equals(newValue) ||"0".equals(newValue)){
                           seguir = false;
                        }else if(seguir){
                            int value = Integer.parseInt(newValue);
                            if (value < 1 || value > 12) {
                                textField.setText(oldValue);
                            } else {
                                if (newValue.length() == 1 && value > 0) {
                                    textField.setText("0" + newValue);
                                }
                            }
                        }
                    } catch (NumberFormatException e) {
                        textField.setText(oldValue);
                    }
                } else {
                    textField.setText(oldValue);
                }
            });
            pause.playFromStart();
        });
    }

    public void convertirAMayusculas(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Convertir el texto a mayúsculas y actualizar el campo de texto
            textField.setText(newValue.toUpperCase());
            // Establecer el cursor al final del texto
            textField.positionCaret(textField.getText().length());
        });
    }
    
    public void restringirA1A6(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(oldValue);
            } else if (!newValue.isEmpty()) {
                int value = Integer.parseInt(newValue);
                if (value < 1 || value > 6) {
                    textField.setText(oldValue);
                }
            }
        });
    }
    
    
    private boolean Editable = true;

    public void LimitadorLongutid(TextField textField, int num) {
        textField.textProperty().addListener((observable, ContAnterior, ContActual) -> {
            if (Editable && ContActual.length() > num) {
                Editable = false;
                textField.setText(ContAnterior);
                Editable = true;
            }
        });
    }
   
    //Acciones sin asignar:
    @FXML
    private void AmSalidaLunes(ActionEvent event) {
    }

    @FXML
    private void PmSalidaLunes(ActionEvent event) {
    }

    @FXML
    private void btnOficialLunes(ActionEvent event) {
    }

    @FXML
    private void btnDeportivoLunes(ActionEvent event) {
    }

    @FXML
    private void btnAlimentosLunes(ActionEvent event) {
    }

    @FXML
    private void btnEnfermeriaLunes(ActionEvent event) {
    }

    @FXML
    private void btnPuericulturaLunes(ActionEvent event) {
    }

    @FXML
    private void AmSalidaMartes(ActionEvent event) {
    }

    @FXML
    private void PmSalida(ActionEvent event) {
    }

    @FXML
    private void AmEntradaMartes(ActionEvent event) {
    }

    @FXML
    private void PmEntradaMartes(ActionEvent event) {
    }

    @FXML
    private void AmSalidaMiercoles(ActionEvent event) {
    }

    @FXML
    private void PmSalidaMiercoles(ActionEvent event) {
    }

    @FXML
    private void txtAmEntradaMiercoles(ActionEvent event) {
    }

    @FXML
    private void PmEntradaMiercoles(ActionEvent event) {
    }

    @FXML
    private void txtAmSalidaJueves(ActionEvent event) {
    }

    @FXML
    private void PmSalidaJueves(ActionEvent event) {
    }

    @FXML
    private void txtAmEntradaJueves(ActionEvent event) {
    }

    @FXML
    private void PmEntradaJueves(ActionEvent event) {
    }

    @FXML
    private void AmSalidaViernes(ActionEvent event) {
    }

    @FXML
    private void PmSalidaViernes(ActionEvent event) {
    }

    @FXML
    private void txtAmEntradaViernes(ActionEvent event) {
    }

    @FXML
    private void PmEntradaViernes(ActionEvent event) {
    }

    @FXML
    private void btnOficialJueves(ActionEvent event) {
    }

    @FXML
    private void btnDeportivoJueves(ActionEvent event) {
    }

    @FXML
    private void btnAlimentosJueves(ActionEvent event) {
    }

    @FXML
    private void btnEnfermeriaJueves(ActionEvent event) {
    }

    @FXML
    private void btnPuericulturaJueves(ActionEvent event) {
    }

    @FXML
    private void btnOficialMiercoles(ActionEvent event) {
    }

    @FXML
    private void btnDeportivoMiercoles(ActionEvent event) {
    }

    @FXML
    private void btnAlimentosMiercoles(ActionEvent event) {
    }

    @FXML
    private void btnEnfermeriaMiercoles(ActionEvent event) {
    }

    @FXML
    private void btnPuericulturaMiercoles(ActionEvent event) {
    }

    @FXML
    private void btnOficialMartes(ActionEvent event) {
    }

    @FXML
    private void btnDeportivoMartes(ActionEvent event) {
    }

    @FXML
    private void btnAlimentosMartes(ActionEvent event) {
    }

    @FXML
    private void btnEnfermeriaMartes(ActionEvent event) {
    }

    @FXML
    private void btnPuericulturaMartes(ActionEvent event) {
    }

    @FXML
    private void btnOficialViernes(ActionEvent event) {
    }

    @FXML
    private void btnDeportivoViernes(ActionEvent event) {
    }

    @FXML
    private void btnAlimentosViernes(ActionEvent event) {
    }

    @FXML
    private void btnEnfermeriaViernes(ActionEvent event) {
    }

    @FXML
    private void btnPuericulturaViernes(ActionEvent event) {
    }
    
    
   
   //LLamado de limitadores
   public void LimitadoresDeHoras(){
       
        LimitadorLongutid(txtMinutosEntradaViernes, 2);
        LimitadorLongutid(txtMinutosSalidaLunes, 2);
        LimitadorLongutid(txtHoraSalidaLunes, 2);
        LimitadorLongutid(txtHoraEntradaLunes, 2);
        LimitadorLongutid(txtMinutosSalidaMartes, 2);
        LimitadorLongutid(txtHoraSalidaMartes, 2);
        LimitadorLongutid(txtMinutosEntradaMartes, 2);
        LimitadorLongutid(txtHoraEntradaMartes, 2);
        LimitadorLongutid(txtMinutosSalidaMiercoles, 2);
        LimitadorLongutid(txtHoraSalidaMiercoles, 2);
        LimitadorLongutid(txtMinutosEntradaMiercoles, 2);
        LimitadorLongutid(txtHoraEntradaMiercoles, 2);
        LimitadorLongutid(txtMinutosSalidaJueves, 2);
        LimitadorLongutid(txtHoraSalidaJueves, 2);
        LimitadorLongutid(txtMinutosEntradaJueves, 2);
        LimitadorLongutid(txtHoraEntradaJueves, 2);
        LimitadorLongutid(txtMinutosSalidaViernes, 2);
        LimitadorLongutid(txtHoraSalidaViernes, 2);
        LimitadorLongutid(txtHoraEntradaViernes, 2);
        LimitadorLongutid(txtMinutosEntradaLunes, 2);
   }
   
   
   public void metodoPruebaxd(){
   
   
   }
   
   
   public void limitadoresParaGradoYGrupo(){
        
        restringirANumeros(txtGrado);
        LimitadorLongutid(txtGrado, 1);
        restringirA1A6(txtGrado);
        
        LimitadorLongutid(txtGrupo, 1);
        restringirALetras(txtGrupo);
        convertirAMayusculas(txtGrupo);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ControladorDePrueba();
        ActualizadorDeRecuadroMinutos(DiasSemana.LUNES, txtMinutosEntradaLunes);
       
        
        
        System.out.println("Supuesto nuevo: " + DatosDeBD("Lunes","primersemestre","A"));
        
        
        
        //--->Metodos Finales <-----
        LimitadoresDeHoras();
        limitadoresParaGradoYGrupo();
        
        //---Lunes
        MetodosLunes();
        //---> --- <-----
        
    
        //Conexion a base de datos
        ConexionBd();
    }    

    
}







