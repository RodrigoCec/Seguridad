/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetodosExtra;

import java.sql.Time;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Rodrigo
 */
public class ComparadorDeHoras {
    
    public boolean compararHoras(Time timeFromDB) {
        
        System.out.println("Hora que se le pasa al comparador de horas: " + timeFromDB);
        // Convertir a LocalTime
        LocalTime dbTime = timeFromDB.toLocalTime();

        // Hora local actual
        LocalTime localTime = LocalTime.now();

        // Adelantar la hora local una hora
        localTime = localTime.minusHours(1);

        
        System.out.println("segun estamos a esto: " + localTime);
        // Calcular la diferencia en minutos entre las dos horas
        long minutesBetween = ChronoUnit.MINUTES.between(dbTime, localTime);

        // Verificar si han pasado 30 minutos o más
        if (minutesBetween >= 30) {
            System.out.println("Han pasado 30 minutos o más desde la hora de la base de datos.");
            return true;
        } else {
            System.out.println("No han pasado 30 minutos desde la hora de la base de datos.");
            return false;
        }
    }
}
