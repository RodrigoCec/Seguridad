package Main;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Clase que representa una entrada de datos con información personal,
 * horario y reportes.
 * 
 * @autor Rodrigo
 */
public class datos {
    private String nombre;
    private String grado;
    private String grupo;
    private String matricula;
    private LocalDate fecha;
    private LocalTime hora;
    private String reporte;
    private String reporteDos;
    private String reporteTres;
    private String descripcion;

    /**
     * Constructor para inicializar todos los campos de la clase.
     * 
     * @param nombre Nombre de la persona.
     * @param grado Grado de la persona.
     * @param grupo Grupo de la persona.
     * @param matricula Matrícula de la persona.
     * @param fecha Fecha de la entrada.
     * @param hora Hora de la entrada.
     * @param reporte Reporte principal.
     * @param reporteDos Segundo reporte.
     * @param reporteTres Tercer reporte.
     * @param descripcion Descripción adicional.
     */
    public datos(String nombre, String grado, String grupo, String matricula, LocalDate fecha, LocalTime hora, String reporte, String reporteDos, String reporteTres, String descripcion) {
        this.nombre = nombre;
        this.grado = grado;
        this.grupo = grupo;
        this.matricula = matricula;
        this.fecha = fecha;
        this.hora = hora;
        this.reporte = reporte;
        this.reporteDos = reporteDos;
        this.reporteTres = reporteTres;
        this.descripcion = descripcion;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getReporte() {
        return reporte;
    }

    public void setReporte(String reporte) {
        this.reporte = reporte;
    }

    public String getReporteDos() {
        return reporteDos;
    }

    public void setReporteDos(String reporteDos) {
        this.reporteDos = reporteDos;
    }

    public String getReporteTres() {
        return reporteTres;
    }

    public void setReporteTres(String reporteTres) {
        this.reporteTres = reporteTres;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

