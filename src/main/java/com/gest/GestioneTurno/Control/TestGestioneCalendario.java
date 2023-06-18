package com.gest.GestioneTurno.Control;

import java.time.LocalDate;

import com.gest.Entity.Impiegato;
import com.gest.Entity.Turno;

public class TestGestioneCalendario {
    public static void main(String[] args) {
        GestioneCalendarioCTL calendario = new GestioneCalendarioCTL();
        
        //test getListaImpiegati
        //for(Impiegato imp: calendario.getListaImpiegati())
        //    System.out.println(imp.toString());
        //LOCALDATE YYYY-MM-DD
        //String start = "2023-01-01";
        //LocalDate today = LocalDate.now();
        //LocalDate startDay = LocalDate.parse(start);
        //calendario.generaCalendario(startDay);
        //calendario.inserisciCalendario();

        //System.out.println("Dimensione calendario: " + calendario.getCalendario().size());

        //for(Turno turno: calendario.getPersonalCalendario(8))
        //    System.out.println(turno.toString());

        String start = "2023-01-01";
        //LocalDate today = LocalDate.now();
        LocalDate startDay = LocalDate.parse(start);
        //for(Turno turno: calendario.getCalendarioGiornaliero(startDay))
        //    System.out.println(turno.toString());

        //for(Turno turno: calendario.getCalendarioGiornaliero(startDay, 2))
        //    System.out.println(turno.toString());

        calendario.generaBustePaga(startDay);

    }
}
