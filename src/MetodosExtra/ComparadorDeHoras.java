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

        LocalTime dbTime = timeFromDB.toLocalTime();

        LocalTime localTime = LocalTime.now();

        localTime = localTime.minusHours(1);
        System.out.println("Hora local ajustada: " + localTime);
        long minutesBetween = ChronoUnit.MINUTES.between(dbTime, localTime);
        if (minutesBetween >= 60) {
            System.out.println("Han pasado 60 minutos o más desde la hora de la base de datos.");
            return true;
        } else {
            System.out.println("No han pasado 60 minutos desde la hora de la base de datos.");
            return false;
        }
    }   

}
