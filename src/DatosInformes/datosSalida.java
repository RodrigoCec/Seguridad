/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosInformes;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Rodrigo
 */
public class datosSalida {
    private String nombre;
    private String grado;
    private String grupo;
    private String matricula;
    private LocalDate fecha;
    private LocalTime hora;
    private String estado;

    /**
     * Constructor para inicializar todos los campos de la clase.
     * 
     * @param nombre Nombre de la persona.
     * @param grado Grado de la persona.
     * @param grupo Grupo de la persona.
     * @param matricula Matrícula de la persona.
     * @param fecha Fecha de la entrada.
     * @param hora Hora de la entrada.
     */
    public datosSalida(String nombre, String grado, String grupo, String matricula, LocalDate fecha, LocalTime hora, String estado) {
        this.nombre = nombre;
        this.grado = grado;
        this.grupo = grupo;
        this.matricula = matricula;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
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
    
    public String getEstado(){
        return estado;
    }
        
    public void setEstado(String estado){
        this.estado = estado;
    }
    
    
    
}
