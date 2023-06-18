package com.gest.GestioneDDL.Control;

import java.time.LocalDate;
import java.util.ArrayList;

import com.gest.Common.DBMSservice;
import com.gest.Entity.Richiesta;
import com.gest.Common.SendEmail;


public class gestioneFerieCtl {
    private ArrayList<Richiesta> listaFerie;

    public gestioneFerieCtl() { 
        this.listaFerie = new ArrayList<>();
        this.setListaFerie();
    }

    public ArrayList<Richiesta> getListaFerie() {
        return this.listaFerie;
    }

    public void setListaFerie() {
        DBMSservice dbms = new DBMSservice();
        this.listaFerie = dbms.queryGetListaFerie();
    }

    public void approvaFerie(Richiesta richiesta) {
        DBMSservice dbms = new DBMSservice();
        dbms.queryApprovaFerie(richiesta);
        dbms.querySendComunicazione(richiesta.getRef_impiegato(), "Ferie approvate");
        this.aggiungiIndisponibilita(richiesta.getRef_impiegato(), richiesta.getData_inizio(), richiesta.getData_fine());
        String email = dbms.queryGetEmail(richiesta.getRef_impiegato());
        String descrizione = "Richiesta ferie dal " + richiesta.getData_inizio() + " al " + richiesta.getData_fine() + " approvata";
        String oggetto = "Richiesta ferie";
        this.sendEmail(email, descrizione, oggetto);
    }

    public void rifiutaFerie(Richiesta richiesta) {
        DBMSservice dbms = new DBMSservice();
        dbms.queryRifiutaFerie(richiesta);
        dbms.querySendComunicazione(richiesta.getRef_impiegato(), "Ferie rifiutate");
        String email = dbms.queryGetEmail(richiesta.getRef_impiegato());
        String descrizione = "Richiesta ferie dal " + richiesta.getData_inizio() + " al " + richiesta.getData_fine() + " rifiutata";
        String oggetto = "Richiesta ferie";
        this.sendEmail(email, descrizione, oggetto);
    }

    public void sendEmail(String email, String descrizione, String oggetto) {
        System.out.println("Email: " + email);
        System.out.println("Oggetto: " + oggetto);
        System.out.println("Descrizione: " + descrizione);
        SendEmail sender = new SendEmail();
        sender.sendEmailGenerica(email, oggetto, descrizione);
    }

    public void aggiungiIndisponibilita(int matricola, LocalDate dataInizio, LocalDate dataFine) {
        DBMSservice dbms = new DBMSservice();
        ArrayList<LocalDate> dateIndisp = new ArrayList<>();
        LocalDate inizio = dataInizio;
        dateIndisp.add(inizio);
        while(inizio.compareTo(dataFine)!=0) {
            inizio = inizio.plusDays(1);
            dateIndisp.add(inizio);
        }
        for(LocalDate data: dateIndisp) {
            dbms.addIndisponibilita(matricola, data);
        }
    }

}
