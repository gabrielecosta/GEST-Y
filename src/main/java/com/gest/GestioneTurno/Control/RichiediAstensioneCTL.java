package com.gest.GestioneTurno.Control;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import com.gest.Common.DBMSservice;
import com.gest.Entity.Richiesta;

public class RichiediAstensioneCTL {
    public RichiediAstensioneCTL() { }

    public boolean richiediCongedo(String tipo, int matricola, String motivazione, String certificato, LocalDate dataInizio, LocalDate dataFine) {
        long days = ChronoUnit.DAYS.between(dataInizio, dataFine);
        if (tipo.compareTo("congedo lutto")==0 && this.checkDateRichiesta(dataInizio, dataFine, matricola)) {
            if (days > 3) {
                System.out.println("Richiesta non accolta");
                return false;
            } else {
                //chiama gestione assenza
                System.out.println("Richiesta accolta");
                return true;
            }
        } else if (tipo.compareTo("congedo parentale")==0 && this.checkDateRichiesta(dataInizio, dataFine, matricola)) {
            DBMSservice dbms = new DBMSservice();
            int giorniChiesti = dbms.queryGetGiorniRimanenti(matricola,"congedo parentale");
            System.out.println("Giorni richiesti finora: " + giorniChiesti);
            if(giorniChiesti + days > 180) {
                //non puoi fare la richiesta
                //System.out.println("Limite massimo raggiunto");
                System.out.println("Richiesta non accolta");
                return false;
            } else {
                //chiama gestione assenza
                System.out.println("Richiesta accolta");
                return true;
            }
        }
        System.out.println("Richiesta non accolta");
        return false;
     }

    public boolean richiediMalattia(int matricola, String motivazione, String certificato, LocalDate dataInizio, LocalDate dataFine) {
        long days = ChronoUnit.DAYS.between(dataInizio, dataFine);
        if(this.checkDateRichiesta(dataInizio, dataFine, matricola)) {
            if (days >= 180) {
                System.out.println("Più di 6 mesi di malattia");
            } 
            //chiama gestione assenza
            System.out.println("Richiesta accolta");
            return true;
        } else {
            System.out.println("Richiesta non accolta");
            return false;
        }
    }

    public boolean richiediMalattiaAdmin(int matricola, String motivazione, LocalDate dataInizio, LocalDate dataFine) {
        long days = ChronoUnit.DAYS.between(dataInizio, dataFine);
        if(this.checkDateRichiesta(dataInizio, dataFine, matricola)) {
            if (days >= 180) {
                System.out.println("Più di 6 mesi di malattia");
            } 
            //chiama gestione assenza
            System.out.println("Richiesta accolta");
            return true;
        } else {
            System.out.println("Richiesta non accolta");
            return false;
        }
    }

    public boolean existsImpiegato(int matricola) {
        DBMSservice dbms = new DBMSservice();
        return dbms.queryExistsImpiegato(matricola);
    }

    public boolean richiediFerie(int matricola, String motivazione, String certificato, LocalDate dataInizio, LocalDate dataFine) {
        //mettere un controllore che fa selezionare solo a partire dal trimestre successivo
        long days = ChronoUnit.DAYS.between(dataInizio, dataFine);
        DBMSservice dbms = new DBMSservice();
        int year = LocalDate.now().getYear();
        int giorniRimanenti = dbms.queryGetFerieRichieste(matricola,"ferie", year);
        if(days + giorniRimanenti <= 28 && this.checkDateRichiesta(dataInizio, dataFine, matricola) ) {
            //può richiedere, return true e messaggio di invio al datore di lavoro
            //inserisce all'interno del DB
            System.out.println("Richiesta accolta");
            Richiesta richiesta = new Richiesta(matricola, "ferie", motivazione, certificato, dataInizio, dataFine, false);
            return dbms.addRichiesta(richiesta);
        } else {
            System.out.println("Richiesta non accolta");
            return false;
        }
    }

    public boolean richiediSciopero(int matricola, String motivazione, String certificato, LocalDate data) {
        LocalDate today = LocalDate.now();
        long days = ChronoUnit.DAYS.between(today, data);
        System.out.println("Distanza tra i giorni: " + days);
        if( days < 10 && this.checkDateRichiesta(data, data, matricola)) {
            System.out.println("Richiesta non accolta");
            return false;
        } else {
            //chiama gestione assenza
            System.out.println("Richiesta accolta");
            return true;
        }
    }

//se le date sono già state richieste, ritorna falso. Basta che anche una sola delle date richieste è già presa che non fa fare la richiesta
    private boolean checkDateRichiesta(LocalDate dataInizio, LocalDate dataFine, int matricola) {
        ArrayList<LocalDate> dateRichiesta = new ArrayList<>();
        LocalDate inizio = dataInizio;
        dateRichiesta.add(inizio);
        while(inizio.compareTo(dataFine)!=0) {
            inizio = inizio.plusDays(1);
            dateRichiesta.add(inizio);
        }
        boolean flag = true;
        DBMSservice dbms = new DBMSservice();
        for(LocalDate data: dateRichiesta) {
            if(dbms.queryCheckData(matricola,data))
                flag = false;
        }
        return flag;
    }
}
