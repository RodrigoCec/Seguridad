/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetodosExtra;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Rodrigo
 */
public class nombreTablasRegistroSalida {
    
    public String NombreTablaActualSalida(){
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
        String nombreTabla = fechaActual.format(formatter);
        
        String TablaEntrada = "02-" + nombreTabla;
        System.out.println("El nombre de tabla deberia ser: " + TablaEntrada);
        
        return TablaEntrada;
    }
    
}
