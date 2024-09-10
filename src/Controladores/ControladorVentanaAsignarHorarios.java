/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexion.conexionDeConsulta;
import Conexion.conexionDeRegistro;
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
public class ControladorVentanaAsignarHorarios implements Initializable {

   //---------> Organizacion de Etiquetas
    
        //Datos
    
    @FXML
    private TextField txtGrupo;
    @FXML
    private TextField txtGrado;
    
    
    // Lunes

        //----------> Horas
    
    @FXML
    private TextField txtHoraEntradaLunes;
    @FXML
    private TextField txtMinutosEntradaLunes;
    @FXML
    private TextField txtHoraSalidaLunes;
    @FXML
    private TextField txtMinutosSalidaLunes;

        //----------> Horarios
    
            // Mostradores de horario
    
    @FXML
    private MenuButton txtTurnoEntradaLunes;
    @FXML
    private MenuButton txtTurnoSalidaLunes;
    
            // Selectores de horario
    
    @FXML
    private MenuItem txtAmEntradaLunes;
    @FXML
    private MenuItem txtPmEntradaLunes;
    @FXML
    private MenuItem txtAmSalidaLunes;
    @FXML
    private MenuItem txtPmSalidaLunes;

        //----------> Uniformes
    
            // Mostrador de Uniformes
    
    @FXML
    private MenuButton btnUniformeLunes;
    
            // Selector de uniformes
    
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


    // Martes

        //----------> Horas

    @FXML
    private TextField txtHoraEntradaMartes;
    @FXML
    private TextField txtMinutosEntradaMartes;
    @FXML
    private TextField txtHoraSalidaMartes;
    @FXML
    private TextField txtMinutosSalidaMartes;

        //----------> Horarios
    
            // Mostradores de horario
    
    @FXML
    private MenuButton txtTurnoEntradaMartes;
    @FXML
    private MenuButton txtTurnoSalidaMartes;
    
            // Selectores de horario
    
    @FXML
    private MenuItem txtAmEntradaMartes;
    @FXML
    private MenuItem txtPmEntradaMartes;
    @FXML
    private MenuItem txtAmSalidaMartes;
    @FXML
    private MenuItem txtPmSalidaMartes;

        //----------> Uniformes
    
            // Mostrador de Uniformes
    
    @FXML
    private MenuButton btnUniformeMartes;
    
            // Selector de uniformes
    
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


// Miércoles

        //----------> Horas

    @FXML
    private TextField txtHoraEntradaMiercoles;
    @FXML
    private TextField txtMinutosEntradaMiercoles;
    @FXML
    private TextField txtHoraSalidaMiercoles;
    @FXML
    private TextField txtMinutosSalidaMiercoles;

        //----------> Horarios
    
            // Mostradores de horario
    
    @FXML
    private MenuButton txtTurnoEntradaMiercoles;
    @FXML
    private MenuButton txtTurnoSalidaMiercoles;
    
            // Selectores de horario
    
    @FXML
    private MenuItem txtAmEntradaMiercoles;
    @FXML
    private MenuItem txtPmEntradaMiercoles;
    @FXML
    private MenuItem txtAmSalidaMiercoles;
    @FXML
    private MenuItem txtPmSalidaMiercoles;

        //----------> Uniformes
    
            // Mostrador de Uniformes
    
    @FXML
    private MenuButton btnUniformeMiercoles;
    
            // Selector de uniformes
    
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


// Jueves

        //----------> Horas

    @FXML
    private TextField txtHoraEntradaJueves;
    @FXML
    private TextField txtMinutosEntradaJueves;
    @FXML
    private TextField txtHoraSalidaJueves;
    @FXML
    private TextField txtMinutosSalidaJueves;

        //----------> Horarios
    
            // Mostradores de horario
    
    @FXML
    private MenuButton txtTurnoEntradaJueves;
    @FXML
    private MenuButton txtTurnoSalidaJueves;
    
            // Selectores de horario
    
    @FXML
    private MenuItem txtAmEntradaJueves;
    @FXML
    private MenuItem txtPmEntradaJueves;
    @FXML
    private MenuItem txtAmSalidaJueves;
    @FXML
    private MenuItem txtPmSalidaJueves;

        //----------> Uniformes
    
            // Mostrador de Uniformes
    
    @FXML
    private MenuButton btnUniformeJueves;
    
            // Selector de uniformes
    
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


// Viernes

        //----------> Horas

    @FXML
    private TextField txtHoraEntradaViernes;
    @FXML
    private TextField txtMinutosEntradaViernes;
    @FXML
    private TextField txtHoraSalidaViernes;
    @FXML
    private TextField txtMinutosSalidaViernes;

        //----------> Horarios
    
            // Mostradores de horario
    
    @FXML
    private MenuButton txtTurnoEntradaViernes;
    @FXML
    private MenuButton txtTurnoSalidaViernes;
    
            // Selectores de horario
    
    @FXML
    private MenuItem txtAmEntradaViernes;
    @FXML
    private MenuItem txtPmEntradaViernes;
    @FXML
    private MenuItem txtAmSalidaViernes;
    @FXML
    private MenuItem txtPmSalidaViernes;

        //----------> Uniformes
    
            // Mostrador de Uniformes
    
    @FXML
    private MenuButton btnUniformeViernes;
    
            // Selector de uniformes
    
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


    
    // Datos de la Conexion a Base de datos
    
  
            //Metodos para obtener datos   
    public String DatosDeBD(String Dia, String Semestre, String Grupo) {
        String CodigoHorario = "";
        
        String query = "SELECT `" + Dia + "` FROM `" + Semestre + "` WHERE Grupo = ?";


        try (Connection connection = conexionDeConsulta.getConnection();
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
    
            //MetodoActualizador De Base de datos;
    private void ActualizarCodigoHorabd(String Semestre, String dia, String newValue, String grupo) {
        String query = "UPDATE " + Semestre +" SET " + dia + " = ? WHERE Grupo = ?";
        try (Connection connection = conexionDeRegistro.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, newValue);
            preparedStatement.setString(2, grupo);
            System.out.println("Se actualizo el: " + Semestre);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Empiezan convertidores
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
    
    public void FormatoAmyPm(String Hora, MenuButton txtTurno){
        //Ayuda a identificar y colocar Am o pm segun el horario devido a las diferencias de 12 y 24 horas que se trabajan
        //Solo muestra apartir de iniciar el programa no cambia de acuerdo a intereacciones
        int hora = Integer.parseInt(Hora);
        
        if(hora < 12){
            txtTurno.setText("AM");
        }else{
            txtTurno.setText("PM");
        }
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
        System.out.println("Se supone que es: " + BaseDatos);
        return BaseDatos;
    }
    
            //Metodo para mostrar datos en los recuadros de la ventana
    public void MostradorEnPantalla(TextField Campo, String time){
        
        Campo.setText(time);       
    
    }


            //Metodos de Logica(Que separan, asignan y gestionan Cosas que no se ven graficamente)    
    public String[] SeparadorHoraMinutos(String Codigo, Tipo tipo){
        
        if (Codigo == null || Codigo.length() < 5) {
            throw new IllegalArgumentException("El código proporcionado es inválido: " + Codigo);
        }

        String Hora = "";
        String Minutos = "";
        

        try {
            switch(tipo){
            case HORA_ENTRADA :
                Hora = Codigo.substring(1, 3);
                Minutos = Codigo.substring(3, 5);
                break;
            case HORA_SALIDA :
                Hora = Codigo.substring(5, 7);
                Minutos = Codigo.substring(7, 9);
                break;
        }
        } catch (StringIndexOutOfBoundsException e) {
            System.err.println("Error al separar hora y minutos: " + e.getMessage());
            return new String[]{"00", "00"}; // o cualquier valor por defecto que consideres adecuado
        }
        
        return new String[]{Hora, Minutos};
        
    }
            //Supuesto Metodo De Separacion y reconocimiento de codigo para su remplazo y actualizacion
    public enum Tipo {
        
        HORA_ENTRADA,
        MINUTOS_ENTRADA,
        HORA_SALIDA,
        MINUTOS_SALIDA  
    }

    public String extractorDeCodigoSeparadoParaBotones(TextField time, Tipo tipo, String codigo, MenuItem amPmButton) {
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
    
    public String extractorDeCodigoSeparadoParaHoras(String time, Tipo tipo, String codigo, MenuButton amPmButton) {
        System.out.println("------>");
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

        
        String amPm = amPmButton.getText().toUpperCase();
        int hora = Integer.parseInt(time);
        if (amPm.equals("PM") && hora != 12) {
            System.out.println("Se sumo 12");//Coment?
            hora += 12;
        } else if (amPm.equals("AM") && hora == 12) {
            hora = 00;
            System.out.println("se dejo normal o en 00");//Coment?
        }
        
        System.out.println("Hora :" + hora);//Coment?
        
        String horaConvertida = String.format("%02d", hora);
        
        
        System.out.println("Hora convertida: " + horaConvertida);//Coment?
        
        String codigoCompleto = "";
            
        if(tipo == Tipo.HORA_ENTRADA || tipo == Tipo.HORA_SALIDA){
            System.out.println("Horas");
            codigoCompleto = anterior + horaConvertida + posterior;
            System.out.println("aqui anterior es: " + anterior);
        }else if (tipo == Tipo.MINUTOS_ENTRADA || tipo == Tipo.MINUTOS_SALIDA){
            
            codigoCompleto = anterior + time;
            System.out.println("Minutos");
            System.out.println("aqui minutos anterior es: " + anterior + posterior);
            if (tipo != Tipo.MINUTOS_SALIDA) {
                codigoCompleto += posterior;
            }
        }
        

        System.out.println("En este caso el anterior es: " + anterior);
        System.out.println("En este caso el editado es: " + editor);
        System.out.println("En este caso el posterior es: " + posterior);
        System.out.println("Codigo Remplazar: " + time);
        System.out.println("Codigo Completo: " + codigoCompleto);
        System.out.println("------>");

        return codigoCompleto;
    }

    public enum DiasSemana {
        
        LUNES,
        MARTES,
        MIERCOLES,
        JUEVES,
        VIERNES
    }

    public void RestriccionesGeneralesDeNumeros(){
        
        restringirANumeros(txtMinutosEntradaViernes);
        restringirANumeros(txtMinutosSalidaLunes);
        restringirANumeros(txtHoraSalidaLunes);
        restringirANumeros(txtHoraEntradaLunes);
        restringirANumeros(txtMinutosSalidaMartes);
        restringirANumeros(txtHoraSalidaMartes);
        restringirANumeros(txtMinutosEntradaMartes);
        restringirANumeros(txtHoraEntradaMartes);
        restringirANumeros(txtMinutosSalidaMiercoles);
        restringirANumeros(txtHoraSalidaMiercoles);
        restringirANumeros(txtMinutosEntradaMiercoles);
        restringirANumeros(txtHoraEntradaMiercoles);
        restringirANumeros(txtMinutosSalidaJueves);
        restringirANumeros(txtHoraSalidaJueves);
        restringirANumeros(txtMinutosEntradaJueves);
        restringirANumeros(txtHoraEntradaJueves);
        restringirANumeros(txtMinutosSalidaViernes);
        restringirANumeros(txtHoraSalidaViernes);
        restringirANumeros(txtHoraEntradaViernes);
        restringirANumeros(txtMinutosEntradaLunes);
    }
    
    public void RestriccionesMinutos(){
        
        //Lunes
        RestriccionMinutos(txtMinutosEntradaLunes);
        RestriccionMinutos(txtMinutosSalidaLunes);
        
        // Martes
        RestriccionMinutos(txtMinutosEntradaMartes);
        RestriccionMinutos(txtMinutosSalidaMartes);

        // Miércoles
        RestriccionMinutos(txtMinutosEntradaMiercoles);
        RestriccionMinutos(txtMinutosSalidaMiercoles);

        // Jueves
        RestriccionMinutos(txtMinutosEntradaJueves);
        RestriccionMinutos(txtMinutosSalidaJueves);

        // Viernes
        RestriccionMinutos(txtMinutosEntradaViernes);
        RestriccionMinutos(txtMinutosSalidaViernes);
        
    }
    
    public void RestriccionesHoras(){
        
        //Lunes
        RestriccionHoras(txtHoraEntradaLunes);
        RestriccionHoras(txtHoraSalidaLunes);
        
        // Martes
        RestriccionHoras(txtHoraEntradaMartes);
        RestriccionHoras(txtHoraSalidaMartes);

        // Miércoles
        RestriccionHoras(txtHoraEntradaMiercoles);
        RestriccionHoras(txtHoraSalidaMiercoles);

        // Jueves
        RestriccionHoras(txtHoraEntradaJueves);
        RestriccionHoras(txtHoraSalidaJueves);

        // Viernes
        RestriccionHoras(txtHoraEntradaViernes);
        RestriccionHoras(txtHoraSalidaViernes);
        
        
    }
    
    public void LimitadoresDeLongitudGeneral(){
       
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
    
    public void LimitadoresGeneralesDeEscritura(){
        
        LimitadoresDeLongitudGeneral();
        RestriccionesGeneralesDeNumeros();
        RestriccionesHoras();
        RestriccionesMinutos();
        
    }
   
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
    
    public void LimitadorDeGrados(TextField textField) {
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

    
    
        //--------> Sistema de gestion y control de asignacion de semtres

    public void ActualizadorDeCodigosParaSemestre(){
        String grupo = txtGrupo.getText();
        String SemestreInicial = AsignadorDeSemestre();
        ActualizadorDeHoras(SemestreInicial, grupo);

       // Listener para detectar cambios en txtGrado
        txtGrado.textProperty().addListener((observable, oldValue, newValue) -> {
               
            if (!newValue.equals(oldValue) & newValue.length() == 1) {
               String Semestre = AsignadorDeSemestre();
               ActualizadorDeHoras(Semestre, grupo);
            }
        });
    }
    
            //CodigoDeActualizacion de Grupo
    public void ActualizadorDeGrupo(){
        String grupo = txtGrupo.getText();
        String grado = AsignadorDeSemestre();
        ActualizadorDeHoras(grado, grupo);
        
        txtGrupo.textProperty().addListener((observable, oldValue, newValue) -> {
           
            if (!newValue.equals(oldValue) & newValue.length() == 1) {               
                
                String grupoSub = txtGrupo.getText();
                String gradoSub  = AsignadorDeSemestre();
                ActualizadorDeHoras(gradoSub, grupoSub);
            
            }
            
        
        });
    }
   
    private boolean verificarDatosNoNulosNiVacios(String datos) {
        if (datos == null || datos.isEmpty()) {
            System.err.println("Error: Datos de la base de datos son nulos o vacíos");
            return false;
        }
        return true;
    }
    
    private String[] obtenerFechasSeparadas(String datos, Tipo tipo) {
        try {
            String[] fechas = SeparadorHoraMinutos(datos, tipo);

            if (fechas == null || fechas.length < 2 || fechas[0] == null || fechas[0].isEmpty() || fechas[1] == null || fechas[1].isEmpty()) {
                System.err.println("Error: Las fechas separadas son nulas o vacías");
                return null;
            }

            return fechas; // Devuelve todo el array, no solo los elementos individuales
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    private void procesarHorario(String dia, String semestre, String grupo,TextField txtHoraEntrada, TextField txtMinutosEntrada, MenuButton txtTurnoEntrada, Tipo tipoEntrada, TextField txtHoraSalida, TextField txtMinutosSalida, MenuButton txtTurnoSalida, Tipo tipoSalida) {
        // Obtener datos para la entrada
        String datosEntrada = DatosDeBD(dia, semestre, grupo);
        if (!verificarDatosNoNulosNiVacios(datosEntrada)) {
            return;
        }
        String[] fechasEntrada = obtenerFechasSeparadas(datosEntrada, tipoEntrada);
        FormatoAmyPm(fechasEntrada[0], txtTurnoEntrada);
        MostradorEnPantalla(txtHoraEntrada, CambioDeFormatoHorario(fechasEntrada[0]));
        MostradorEnPantalla(txtMinutosEntrada, fechasEntrada[1]);

        // Obtener datos para la salida
        String datosSalida = DatosDeBD(dia, semestre, grupo);
        if (!verificarDatosNoNulosNiVacios(datosSalida)) {
            return;
        }
        String[] fechasSalida = obtenerFechasSeparadas(datosSalida, tipoSalida);
        FormatoAmyPm(fechasSalida[0], txtTurnoSalida);
        MostradorEnPantalla(txtHoraSalida, CambioDeFormatoHorario(fechasSalida[0]));
        MostradorEnPantalla(txtMinutosSalida, fechasSalida[1]);
    }

    public void ActualizadorDeHoras(String Semestre, String Grupo){
       
        // Lunes
        procesarHorario("Lunes", Semestre, Grupo, txtHoraEntradaLunes, txtMinutosEntradaLunes, txtTurnoEntradaLunes, Tipo.HORA_ENTRADA, 
                        txtHoraSalidaLunes, txtMinutosSalidaLunes, txtTurnoSalidaLunes, Tipo.HORA_SALIDA);

        // Martes
        procesarHorario("Martes", Semestre, Grupo, txtHoraEntradaMartes, txtMinutosEntradaMartes, txtTurnoEntradaMartes, Tipo.HORA_ENTRADA, 
                        txtHoraSalidaMartes, txtMinutosSalidaMartes, txtTurnoSalidaMartes, Tipo.HORA_SALIDA);

        // Miércoles
        procesarHorario("Miercoles", Semestre, Grupo, txtHoraEntradaMiercoles, txtMinutosEntradaMiercoles, txtTurnoEntradaMiercoles, Tipo.HORA_ENTRADA, 
                        txtHoraSalidaMiercoles, txtMinutosSalidaMiercoles, txtTurnoSalidaMiercoles, Tipo.HORA_SALIDA);

        // Jueves
        procesarHorario("Jueves", Semestre, Grupo, txtHoraEntradaJueves, txtMinutosEntradaJueves, txtTurnoEntradaJueves, Tipo.HORA_ENTRADA, 
                        txtHoraSalidaJueves, txtMinutosSalidaJueves, txtTurnoSalidaJueves, Tipo.HORA_SALIDA);

        // Viernes
        procesarHorario("Viernes", Semestre, Grupo, txtHoraEntradaViernes, txtMinutosEntradaViernes, txtTurnoEntradaViernes, Tipo.HORA_ENTRADA, 
                        txtHoraSalidaViernes, txtMinutosSalidaViernes, txtTurnoSalidaViernes, Tipo.HORA_SALIDA);

    }

    
        //Gestionador De actualizadores de horas
    /**
     *
     * @param textfield Selecciona el textfield del que se obtienen lo datos
     * @param dia Dia de la semana del que se esta registrando
     * @param tipo Selecciona el tipo de filtro que se usa(Entrada[Hora y minutos], Salida[Hora y minutos] )
     * @param Turno Seleciona el boton del que se obtendra el turno (AM o PM) para filtrar y poner en formato 24 horas
     */
    public void ActualizadorHorasEnBaseDeDatos(TextField textfield, String dia, Tipo tipo, MenuButton Turno){
        
        String Grupo = txtGrupo.getText();
        String SemestreInicial = AsignadorDeSemestre();
        
        String Datos = DatosDeBD(dia, SemestreInicial, Grupo);
        
        //MetodoDeActualizacion
        
         textfield.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != oldValue){
                if (newValue.length() == 2) {

                    String filteredValue = extractorDeCodigoSeparadoParaHoras(newValue, tipo , Datos, Turno);
                    if(filteredValue.length() > 8){ 
                        String SemestreFinal = AsignadorDeSemestre();
                        System.out.println("Se envia: " + SemestreFinal);
                        ActualizarCodigoHorabd(SemestreFinal , dia, filteredValue, Grupo);
                    }else{
                        System.err.println("Tas mal mijo, te salve");
                    }

                } else if (newValue.length() == 1) {
                    System.out.println("No es de dos digitos pa");
                }
            }
        });   
    }
    
    public void ActualizadoresHorasEntrada(){
        
        ActualizadorHorasEnBaseDeDatos(txtHoraEntradaLunes, "Lunes", Tipo.HORA_ENTRADA, txtTurnoEntradaLunes);
        ActualizadorHorasEnBaseDeDatos(txtHoraEntradaMartes, "Martes", Tipo.HORA_ENTRADA, txtTurnoEntradaMartes);
        ActualizadorHorasEnBaseDeDatos(txtHoraEntradaMiercoles, "Miercoles", Tipo.HORA_ENTRADA, txtTurnoEntradaMiercoles);
        ActualizadorHorasEnBaseDeDatos(txtHoraEntradaJueves, "Jueves", Tipo.HORA_ENTRADA, txtTurnoEntradaJueves);
        ActualizadorHorasEnBaseDeDatos(txtHoraEntradaViernes, "Viernes", Tipo.HORA_ENTRADA, txtTurnoEntradaViernes);
        
    }
    
    public void ActualizadoresHorasSalida(){
        
        ActualizadorHorasEnBaseDeDatos(txtHoraSalidaLunes, "Lunes", Tipo.HORA_SALIDA, txtTurnoSalidaLunes);
        ActualizadorHorasEnBaseDeDatos(txtHoraSalidaMartes, "Martes", Tipo.HORA_SALIDA, txtTurnoSalidaMartes);
        ActualizadorHorasEnBaseDeDatos(txtHoraSalidaMiercoles, "Miercoles", Tipo.HORA_SALIDA, txtTurnoSalidaMiercoles);
        ActualizadorHorasEnBaseDeDatos(txtHoraSalidaJueves, "Jueves", Tipo.HORA_SALIDA, txtTurnoSalidaJueves);
        ActualizadorHorasEnBaseDeDatos(txtHoraSalidaViernes, "Viernes", Tipo.HORA_SALIDA, txtTurnoSalidaViernes);
    
    }
    
    public void ActualizadoresMinutosEntrada(){
        
        ActualizadorHorasEnBaseDeDatos(txtMinutosEntradaLunes, "Lunes", Tipo.MINUTOS_ENTRADA, txtTurnoEntradaLunes);
        ActualizadorHorasEnBaseDeDatos(txtMinutosEntradaMartes, "Martes", Tipo.HORA_SALIDA, txtTurnoEntradaMartes);
        ActualizadorHorasEnBaseDeDatos(txtMinutosEntradaMiercoles, "Miercoles", Tipo.HORA_SALIDA, txtTurnoEntradaMiercoles);
        ActualizadorHorasEnBaseDeDatos(txtMinutosEntradaJueves, "Jueves", Tipo.HORA_SALIDA, txtTurnoEntradaJueves);
        ActualizadorHorasEnBaseDeDatos(txtMinutosEntradaViernes, "Viernes", Tipo.HORA_SALIDA, txtTurnoEntradaViernes);
    
    }
    
     public void ActualizadoresMinutosSalida(){
        
        ActualizadorHorasEnBaseDeDatos(txtMinutosSalidaLunes, "Lunes", Tipo.MINUTOS_SALIDA, txtTurnoSalidaLunes);
        ActualizadorHorasEnBaseDeDatos(txtMinutosSalidaMartes, "Martes", Tipo.HORA_SALIDA, txtTurnoSalidaMartes);
        ActualizadorHorasEnBaseDeDatos(txtMinutosSalidaMiercoles, "Miercoles", Tipo.HORA_SALIDA, txtTurnoSalidaMiercoles);
        ActualizadorHorasEnBaseDeDatos(txtMinutosSalidaJueves, "Jueves", Tipo.HORA_SALIDA, txtTurnoSalidaJueves);
        ActualizadorHorasEnBaseDeDatos(txtMinutosSalidaViernes, "Viernes", Tipo.HORA_SALIDA, txtTurnoSalidaViernes);
    
    }
     
     public void GestionadorDeActualizadores(){
     
            ActualizadoresHorasEntrada();
            ActualizadoresHorasSalida();
            ActualizadoresMinutosEntrada();
            ActualizadoresMinutosSalida();
     }
    
     
     
    //ReorganizadorDeFormatos
    public void ActualizacionDeHorariosPorTurnos(String Dia, TextField Hora, Tipo tipo, MenuItem Turnos){
         
        String Grupo = txtGrupo.getText();
        String semestre = AsignadorDeSemestre();
        String Datos = DatosDeBD(Dia, semestre, Grupo);
        String Codigo = extractorDeCodigoSeparadoParaBotones(Hora, tipo, Datos, Turnos);
        System.out.println("con el boton Am se manda esto: " + Codigo);
        
        if(Codigo.length() > 8){
            ActualizarCodigoHorabd(semestre, "Lunes", Codigo, Grupo);
        }else {
            System.out.println("Error no cuenta con la longitud necesaria no se actualizo");
        }
        
     }

    public void MostradorDeRespuestasBotones(MenuButton Asignador, MenuItem Seleccionador){
    
        Asignador.setText(Seleccionador.getText());
    }
    
    
    //Gestionador De Uniformes
    
    
    public void ActualizadorDeCodigoParaUniformes(String Dia, MenuItem SelectorUniforme){
        String Grupo = txtGrupo.getText();
        String semestre = AsignadorDeSemestre();
        String Datos = DatosDeBD(Dia, semestre, Grupo);
        
        String codigoRestante = Datos.substring(1);       
        String tipoUniforme = SelectorUniforme.getText();      
        
        System.out.println("Codigo Restante: " + codigoRestante);
        
        
        String CodigoAsignado = "1";   
        switch(tipoUniforme){
            
            case "Oficial":
                
                CodigoAsignado = "1";
                break;
            case "Deportivo":
                
                CodigoAsignado = "2";
                break;
            case "Alimentos":
                
                CodigoAsignado = "3";
                break;
            case "Enfermeria":
                
                CodigoAsignado = "4";
                break;
            case "Puericultura":
                
                CodigoAsignado = "5";
                break;
        }
        
        String CodigoGeneral = CodigoAsignado + codigoRestante;
        
        
        
        if(CodigoGeneral.length() >= 9){
            ActualizarCodigoHorabd(semestre, Dia, CodigoGeneral, Grupo);
        }else{
        
            System.err.println("El codigo no es mayor a 9 ");
        }
    }
    
    public void ActualizadorDeUniformes(String Dia, MenuButton Asignador, String Grado){
        
        String Grupo = txtGrupo.getText();
        String Datos = DatosDeBD(Dia, Grado, Grupo);
        
        char codigoUniforme = Datos.charAt(0);   
        
        String CodigoAsignado = "Oficial";   
        switch(codigoUniforme){
            
            case '1':
                
                CodigoAsignado = "Oficial";
                break;
            case '2':
                
                CodigoAsignado = "Deportivo";
                break;
            case '3':
                
                CodigoAsignado = "Alimentos";
                break;
            case '4':
                
                CodigoAsignado = "Enfermeria";
                break;
            case '5':
                
                CodigoAsignado = "Puericultura";
                
                break;
        }
        
        System.err.println("Se le asigna: " + CodigoAsignado + " Por: " + codigoUniforme + " el codigo fue: " + Datos);
        Asignador.setText(CodigoAsignado);
    }
    
    public void AsigadorDeActualizacionUnifrmes(){
        
        String Grado = AsignadorDeSemestre();
        
        ActualizadorDeUniformes("Lunes", btnUniformeLunes, Grado);
        ActualizadorDeUniformes("Martes", btnUniformeMartes, Grado);
        ActualizadorDeUniformes("Miercoles", btnUniformeMiercoles, Grado);
        ActualizadorDeUniformes("Jueves", btnUniformeJueves, Grado);
        ActualizadorDeUniformes("Viernes", btnUniformeViernes, Grado);
        
        txtGrado.textProperty().addListener((observable, oldValue, newValue) -> {
            
            if(oldValue != newValue){
                String GradoFinal = AsignadorDeSemestre();
                
                ActualizadorDeUniformes("Lunes", btnUniformeLunes, GradoFinal);
                ActualizadorDeUniformes("Martes", btnUniformeMartes, GradoFinal);
                ActualizadorDeUniformes("Miercoles", btnUniformeMiercoles, GradoFinal);
                ActualizadorDeUniformes("Jueves", btnUniformeJueves, GradoFinal);
                ActualizadorDeUniformes("Viernes", btnUniformeViernes, GradoFinal);
            }
        });
        
        txtGrupo.textProperty().addListener((observable, oldValue, newValue) -> {
            
            if(oldValue != newValue){
                String GradoFinal = AsignadorDeSemestre();
                
                ActualizadorDeUniformes("Lunes", btnUniformeLunes, GradoFinal);
                ActualizadorDeUniformes("Martes", btnUniformeMartes, GradoFinal);
                ActualizadorDeUniformes("Miercoles", btnUniformeMiercoles, GradoFinal);
                ActualizadorDeUniformes("Jueves", btnUniformeJueves, GradoFinal);
                ActualizadorDeUniformes("Viernes", btnUniformeViernes, GradoFinal);
            }
        });
        
    }
    
    
    
    
    //---------> Botones de turnos
    
    
        //Lunes
    
    @FXML
    private void AmEntradaLunes(ActionEvent event) {
        
        ActualizacionDeHorariosPorTurnos("Lunes", txtHoraEntradaLunes, Tipo.HORA_ENTRADA, txtAmEntradaLunes);
        MostradorDeRespuestasBotones(txtTurnoEntradaLunes, txtAmEntradaLunes);
    }
    
    @FXML
    private void PmEntradaLunes(ActionEvent event) {
      
        ActualizacionDeHorariosPorTurnos("Lunes", txtHoraEntradaLunes, Tipo.HORA_ENTRADA, txtPmEntradaLunes);
        MostradorDeRespuestasBotones(txtTurnoEntradaLunes, txtPmEntradaLunes);
        
    }
    
    @FXML
    private void AmSalidaLunes(ActionEvent event) {
        
        ActualizacionDeHorariosPorTurnos("Lunes", txtHoraSalidaLunes, Tipo.HORA_SALIDA, txtAmSalidaLunes);
        MostradorDeRespuestasBotones(txtTurnoSalidaLunes, txtAmSalidaLunes); 
        
    }

    @FXML
    private void PmSalidaLunes(ActionEvent event) {
        
        ActualizacionDeHorariosPorTurnos("Lunes", txtHoraSalidaLunes, Tipo.HORA_SALIDA, txtPmSalidaLunes);
        MostradorDeRespuestasBotones(txtTurnoSalidaLunes, txtPmSalidaLunes); 
        
    }

   
        
        //Martes
    
    @FXML
    private void AmEntradaMartes(ActionEvent event) {
        
        ActualizacionDeHorariosPorTurnos("Martes", txtHoraEntradaMartes, Tipo.HORA_ENTRADA, txtAmEntradaMartes);
        MostradorDeRespuestasBotones(txtTurnoEntradaMartes, txtAmEntradaMartes);
        
    }

    @FXML
    private void PmEntradaMartes(ActionEvent event) {
        
        ActualizacionDeHorariosPorTurnos("Martes", txtHoraEntradaMartes, Tipo.HORA_ENTRADA, txtPmEntradaMartes);
        MostradorDeRespuestasBotones(txtTurnoEntradaMartes, txtPmEntradaMartes);
        
    }
    
    @FXML
    private void AmSalidaMartes(ActionEvent event) {
    
        ActualizacionDeHorariosPorTurnos("Martes", txtHoraSalidaMartes, Tipo.HORA_SALIDA, txtAmSalidaMartes);
        MostradorDeRespuestasBotones(txtTurnoSalidaMartes, txtAmSalidaMartes);
        
    }

    @FXML
    private void PmSalidaMartes(ActionEvent event) {
        
        ActualizacionDeHorariosPorTurnos("Martes", txtHoraSalidaMartes,Tipo.HORA_SALIDA, txtPmSalidaMartes);
        MostradorDeRespuestasBotones(txtTurnoSalidaMartes, txtPmSalidaMartes);
        
    }
    
        //Miercoles
    
    @FXML
    private void AmEntradaMiercoles(ActionEvent event) {
        
        ActualizacionDeHorariosPorTurnos("Miercoles", txtHoraEntradaMiercoles, Tipo.HORA_ENTRADA, txtAmEntradaMiercoles);
        MostradorDeRespuestasBotones(txtTurnoEntradaMiercoles, txtAmEntradaMiercoles);
    }

    @FXML
    private void PmEntradaMiercoles(ActionEvent event) {
        
        ActualizacionDeHorariosPorTurnos("Miercoles", txtHoraEntradaMiercoles, Tipo.HORA_ENTRADA, txtPmEntradaMiercoles);
        MostradorDeRespuestasBotones(txtTurnoEntradaMiercoles, txtPmEntradaMiercoles);
    
    }
    
    @FXML
    private void AmSalidaMiercoles(ActionEvent event) {
        
        ActualizacionDeHorariosPorTurnos("Miercoles", txtHoraSalidaMiercoles, Tipo.HORA_SALIDA, txtAmSalidaMiercoles);
        MostradorDeRespuestasBotones(txtTurnoSalidaMiercoles, txtAmSalidaMiercoles);
        
    }

    @FXML
    private void PmSalidaMiercoles(ActionEvent event) {
        
        ActualizacionDeHorariosPorTurnos("Miercoles", txtHoraSalidaMiercoles, Tipo.HORA_SALIDA, txtPmSalidaMiercoles);
        MostradorDeRespuestasBotones(txtTurnoSalidaMiercoles, txtPmSalidaMiercoles);
        
    }
    
    
        //Jueves
    
    @FXML
    private void AmEntradaJueves(ActionEvent event) {
        
        ActualizacionDeHorariosPorTurnos("Jueves", txtHoraEntradaJueves, Tipo.HORA_ENTRADA, txtAmEntradaJueves);
        MostradorDeRespuestasBotones(txtTurnoEntradaJueves, txtAmEntradaJueves);
        
    }

    @FXML
    private void PmEntradaJueves(ActionEvent event) {
        
        ActualizacionDeHorariosPorTurnos("Jueves", txtHoraEntradaJueves, Tipo.HORA_ENTRADA, txtPmEntradaJueves);
        MostradorDeRespuestasBotones(txtTurnoEntradaJueves, txtPmEntradaJueves);
        
    }
    
    @FXML
    private void AmSalidaJueves(ActionEvent event) {
        
        ActualizacionDeHorariosPorTurnos("Jueves", txtHoraSalidaJueves, Tipo.HORA_SALIDA, txtAmSalidaJueves);
        MostradorDeRespuestasBotones(txtTurnoSalidaJueves, txtAmSalidaJueves);
    }

    @FXML
    private void PmSalidaJueves(ActionEvent event) {
        
        ActualizacionDeHorariosPorTurnos("Jueves", txtHoraSalidaJueves, Tipo.HORA_SALIDA, txtPmSalidaJueves);
        MostradorDeRespuestasBotones(txtTurnoSalidaJueves, txtPmSalidaJueves);
        
    }
    
    
        //Viernes
    
    @FXML
    private void AmEntradaViernes(ActionEvent event) {
        
        ActualizacionDeHorariosPorTurnos("Viernes", txtHoraEntradaViernes, Tipo.HORA_ENTRADA, txtAmEntradaViernes);
        MostradorDeRespuestasBotones(txtTurnoEntradaViernes, txtAmEntradaViernes);
        
    }

    @FXML
    private void PmEntradaViernes(ActionEvent event) {
        
        ActualizacionDeHorariosPorTurnos("Viernes", txtHoraEntradaViernes, Tipo.HORA_ENTRADA, txtPmEntradaViernes);
        MostradorDeRespuestasBotones(txtTurnoEntradaViernes, txtPmEntradaViernes);
        
    }
    
    @FXML
    private void AmSalidaViernes(ActionEvent event) {
        
        ActualizacionDeHorariosPorTurnos("Viernes", txtHoraSalidaViernes, Tipo.HORA_SALIDA, txtAmSalidaViernes);
        MostradorDeRespuestasBotones(txtTurnoSalidaViernes, txtAmSalidaViernes);
        
    }

    @FXML
    private void PmSalidaViernes(ActionEvent event) {
        
        ActualizacionDeHorariosPorTurnos("Viernes", txtHoraSalidaViernes, Tipo.HORA_SALIDA, txtPmSalidaViernes);
        MostradorDeRespuestasBotones(txtTurnoSalidaViernes, txtPmSalidaViernes);
        
    }


    
    //---------> Uniformes
        
        //Lunes
    
    @FXML
    private void btnOficialLunes(ActionEvent event) { 
        
        ActualizadorDeCodigoParaUniformes("Lunes", txtOficialLunes);
        MostradorDeRespuestasBotones(btnUniformeLunes, txtOficialLunes);
        
    }

    @FXML
    private void btnDeportivoLunes(ActionEvent event) {
        
        ActualizadorDeCodigoParaUniformes("Lunes", txtDeportivoLunes);
        MostradorDeRespuestasBotones(btnUniformeLunes, txtDeportivoLunes);
    }

    @FXML
    private void btnAlimentosLunes(ActionEvent event) {
        
        ActualizadorDeCodigoParaUniformes("Lunes", txtAlimentosLunes);
        MostradorDeRespuestasBotones(btnUniformeLunes, txtAlimentosLunes);
    }

    @FXML
    private void btnEnfermeriaLunes(ActionEvent event) {
        
        ActualizadorDeCodigoParaUniformes("Lunes", txtEnfermeriaLunes);
        MostradorDeRespuestasBotones(btnUniformeLunes, txtEnfermeriaLunes);
    }

    @FXML
    private void btnPuericulturaLunes(ActionEvent event) {
        
        ActualizadorDeCodigoParaUniformes("Lunes", txtPuericulturaLunes);
        MostradorDeRespuestasBotones(btnUniformeLunes, txtPuericulturaLunes);
    }
        
        //Martes
    
    @FXML
    private void btnOficialMartes(ActionEvent event) {
        
        ActualizadorDeCodigoParaUniformes("Martes", txtOficialMartes);
        MostradorDeRespuestasBotones(btnUniformeMartes, txtOficialMartes);
        
    }

    @FXML
    private void btnDeportivoMartes(ActionEvent event) {
        
        ActualizadorDeCodigoParaUniformes("Martes", txtDeportivoMartes);
        MostradorDeRespuestasBotones(btnUniformeMartes, txtDeportivoMartes);
    }

    @FXML
    private void btnAlimentosMartes(ActionEvent event) {
        
        ActualizadorDeCodigoParaUniformes("Martes", txtAlimentosMartes);
        MostradorDeRespuestasBotones(btnUniformeMartes, txtAlimentosMartes);
    }

    @FXML
    private void btnEnfermeriaMartes(ActionEvent event) {
        
        ActualizadorDeCodigoParaUniformes("Martes", txtEnfermeriaMartes);
        MostradorDeRespuestasBotones(btnUniformeMartes, txtEnfermeriaMartes);
    }

    @FXML
    private void btnPuericulturaMartes(ActionEvent event) {
        
        ActualizadorDeCodigoParaUniformes("Martes", txtPuericulturaMartes);
        MostradorDeRespuestasBotones(btnUniformeMartes, txtPuericulturaMartes);
        
    }
    
        //Miercoles
    
    @FXML
    private void btnOficialMiercoles(ActionEvent event) {
        
        ActualizadorDeCodigoParaUniformes("Miercoles", txtOficialMiercoles);
        MostradorDeRespuestasBotones(btnUniformeMiercoles, txtOficialMiercoles);
    }

    @FXML
    private void btnDeportivoMiercoles(ActionEvent event) {
        
        ActualizadorDeCodigoParaUniformes("Miercoles", txtDeportivoMiercoles);
        MostradorDeRespuestasBotones(btnUniformeMiercoles, txtDeportivoMiercoles);
        
    }

    @FXML
    private void btnAlimentosMiercoles(ActionEvent event) {
        
        ActualizadorDeCodigoParaUniformes("Miercoles", txtAlimentosMiercoles);
        MostradorDeRespuestasBotones(btnUniformeMiercoles, txtAlimentosMiercoles);
        
    }

    @FXML
    private void btnEnfermeriaMiercoles(ActionEvent event) {
        
        ActualizadorDeCodigoParaUniformes("Miercoles", txtEnfermeriaMiercoles);
        MostradorDeRespuestasBotones(btnUniformeMiercoles, txtEnfermeriaMiercoles);
        
    }

    @FXML
    private void btnPuericulturaMiercoles(ActionEvent event) {
        
        ActualizadorDeCodigoParaUniformes("Miercoles", txtPuericulturaMiercoles);
        MostradorDeRespuestasBotones(btnUniformeMiercoles, txtPuericulturaMiercoles);
        
    }
    
        //Jueves
    
    @FXML
    private void btnOficialJueves(ActionEvent event) {
        
        ActualizadorDeCodigoParaUniformes("Jueves", txtOficialJueves);
        MostradorDeRespuestasBotones(btnUniformeJueves, txtOficialJueves);
        
    }

    @FXML
    private void btnDeportivoJueves(ActionEvent event) {
        
        ActualizadorDeCodigoParaUniformes("Jueves", txtDeportivoJueves);
        MostradorDeRespuestasBotones(btnUniformeJueves, txtDeportivoJueves);
        
    }

    @FXML
    private void btnAlimentosJueves(ActionEvent event) {
        
        ActualizadorDeCodigoParaUniformes("Jueves", txtAlimentosJueves);
        MostradorDeRespuestasBotones(btnUniformeJueves, txtAlimentosJueves);
        
    }

    @FXML
    private void btnEnfermeriaJueves(ActionEvent event) {
        
        ActualizadorDeCodigoParaUniformes("Jueves", txtEnfermeriaJueves);
        MostradorDeRespuestasBotones(btnUniformeJueves, txtEnfermeriaJueves);
        
    }

    @FXML
    private void btnPuericulturaJueves(ActionEvent event) {
        
        ActualizadorDeCodigoParaUniformes("Jueves", txtPuericulturaJueves);
        MostradorDeRespuestasBotones(btnUniformeJueves, txtPuericulturaJueves);
        
    }
    
        //Viernes
    
    @FXML
    private void btnOficialViernes(ActionEvent event) {
        
        ActualizadorDeCodigoParaUniformes("Viernes", txtOficialViernes);
        MostradorDeRespuestasBotones(btnUniformeViernes, txtOficialViernes);
    }

    @FXML
    private void btnDeportivoViernes(ActionEvent event) {
        
        ActualizadorDeCodigoParaUniformes("Viernes", txtDeportivoViernes);
        MostradorDeRespuestasBotones(btnUniformeViernes, txtDeportivoViernes);
    }

    @FXML
    private void btnAlimentosViernes(ActionEvent event) {
        
        ActualizadorDeCodigoParaUniformes("Viernes", txtAlimentosViernes);
        MostradorDeRespuestasBotones(btnUniformeViernes, txtAlimentosViernes);
        
    }

    @FXML
    private void btnEnfermeriaViernes(ActionEvent event) {
        
        ActualizadorDeCodigoParaUniformes("Viernes", txtEnfermeriaViernes);
        MostradorDeRespuestasBotones(btnUniformeViernes, txtEnfermeriaViernes);
    }

    @FXML
    private void btnPuericulturaViernes(ActionEvent event) {
        
        ActualizadorDeCodigoParaUniformes("Viernes", txtPuericulturaViernes);
        MostradorDeRespuestasBotones(btnUniformeViernes, txtPuericulturaViernes);
        
    }
    
    
    
    
    
   
   public void RestrictorDeGrupo(){
       
       restringirALetras(txtGrupo);
       convertirAMayusculas(txtGrupo);
       LimitadorLongutid(txtGrupo, 1);
   }
   
   public void RestrictorDeGrado(){
       
       LimitadorDeGrados(txtGrado);
       restringirANumeros(txtGrado);
       LimitadorLongutid(txtGrado, 1);
   }
   
   public void limitadoresDeDatos(){
       
       RestrictorDeGrupo();
       RestrictorDeGrado();
   
   }
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
       
        
        
        
        
        //--->Metodos Finales <-----
        limitadoresDeDatos();
        LimitadoresGeneralesDeEscritura();
        GestionadorDeActualizadores();
        ActualizadorDeCodigosParaSemestre();
        ActualizadorDeGrupo();
        AsigadorDeActualizacionUnifrmes();
        
        //---> --- <-----
    
        
    }
    
}
