/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosInformes;

/**
 *
 * @author Rodrigo
 */
public class datosBusqueda {
    private String nombre;
    private String paterno;
    private String materno;
    private String grado;
    private String grupo;
    private String matricula;

    /**
     * Constructor para inicializar todos los campos de la clase.
     * 
     * @param nombre Nombre de la persona.
     * @param grado Grado de la persona.
     * @param grupo Grupo de la persona.
     * @param matricula Matrícula de la persona.
     */
    public datosBusqueda(String nombre, String paterno, String materno, String grado, String grupo, String matricula) {
        
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.grado = grado;
        this.grupo = grupo;
        this.matricula = matricula;
        
    }
    
     public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }
    
    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
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
    
}
