package com.gest.GestioneNotifiche.Control;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import com.gest.Common.DBMSservice;
import com.gest.Entity.Turno;
import com.gest.Common.SendEmail;

public class notificaRitardoCtl {
    private ArrayList<Turno> listaTurni;

    public notificaRitardoCtl() { 
        this.listaTurni = new ArrayList<>();
        this.listaTurni = this.checkTurno();
    }

    public LocalTime checkTime() {
        //return LocalTime.parse("08:00");
        return LocalTime.now();
    }

    public LocalDate checkDate() {
        //return LocalDate.parse("2023-01-01");
        return LocalDate.now();
    }

    public boolean checkFirmaIngresso(Turno turno) {
        return turno.isFirmaIN();
    }

    public boolean checkFirmaUscita(Turno turno) {
        return turno.isFirmaOUT();
    }

    public String getEmailImpiegato(Turno turno) {
        DBMSservice dbms = new DBMSservice();
        return dbms.queryGetEmail(turno.getRef_impiegato());
    }

    public ArrayList<Turno> checkTurno() {
        DBMSservice dbms = new DBMSservice();
        return dbms.queryCercaTurni(this.checkDate(), this.checkTime());
    }

    public void checkRitardoIngressoListaTurni() {
        for(Turno turno: this.listaTurni) {
            if(checkRitardoIngresso(turno)==true && checkFirmaIngresso(turno)==false) {
                //notifica ritardo ingresso
                this.notifyRitardoIngresso(turno);
                this.updateRitardo(turno);
                String descrizione = "Ritardo in ingresso rilevato in data " + turno.getDataTurno();
                this.sendEmailRitardoAssenza(this.getEmailImpiegato(turno), "Ritardo in ingresso", descrizione);
            }
        }
    }

    public void checkRitardoUscitaListaTurni() {
        for(Turno turno: this.listaTurni) {
            if(checkRitardoUscita(turno)==true && checkFirmaUscita(turno)==false) {
                //notifica ritardo uscita
                this.notifyRitardoUscita(turno);
                String descrizione = "Ritardo in uscita nel giorno " + turno.getDataTurno();
                this.sendEmailRitardoAssenza(this.getEmailImpiegato(turno), "Ritardo uscita", descrizione);
                this.inserisciFirma(turno);
            }
        }
    }

    public void checkAssenzaListaTurni() {
        for(Turno turno: this.listaTurni) {
            if(checkRitardoUscita(turno)==true && checkFirmaIngresso(turno)==false) {
                //notifica ritardo ingresso
                this.notifyAssenza(turno);
                this.updateAssenze(turno);
                String descrizione = "Assenza nel giorno " + turno.getDataTurno();
                this.sendEmailRitardoAssenza(this.getEmailImpiegato(turno), "Assenza", descrizione);
            }
        }
    }

    public boolean checkRitardoIngresso(Turno turno) {
        return this.checkTime().isAfter(turno.getOraInizio().plusMinutes(10));
    }

    public boolean checkRitardoUscita(Turno turno) {
        return this.checkTime().isAfter(turno.getOraFine().plusMinutes(10));
    }

    public void notifyRitardoIngresso(Turno turno) {
        //inserisce una comunicazione
        DBMSservice dbms = new DBMSservice();
        String descrizione = "impiegato " + turno.getRef_impiegato() + " in ritardo in ingresso nel giorno " + turno.getDataTurno();
        System.out.println(descrizione);
        dbms.queryNotificaRitardo(turno.getRef_impiegato(), descrizione);
    }

    public void notifyRitardoUscita(Turno turno) {
        //inserisce una comunicazione
        DBMSservice dbms = new DBMSservice();
        String descrizione = "impiegato " + turno.getRef_impiegato() + " in ritardo in uscita nel giorno " + turno.getDataTurno();
        System.out.println(descrizione);
        dbms.queryNotificaRitardo(turno.getRef_impiegato(), descrizione);
    }

    public void notifyAssenza(Turno turno) {
        //inserisce una comunicazione
        DBMSservice dbms = new DBMSservice();
        String descrizione = "impiegato " + turno.getRef_impiegato() + " assente nel giorno " + turno.getDataTurno();
        System.out.println(descrizione);
        dbms.queryNotificaAssenza(turno.getRef_impiegato(), descrizione);
    }

    public void inserisciFirma(Turno turno) {
        turno.setFirmaOUT(true);
        DBMSservice dbms = new DBMSservice();
        dbms.queryFirmaUscita(turno);
    }

    public void updateRitardo(Turno turno) {
        DBMSservice dbms = new DBMSservice();
        dbms.queryUpdateRitardo(turno);
    }

    public void updateAssenze(Turno turno) {
        DBMSservice dbms = new DBMSservice();
        dbms.queryUpdateAssenze(turno);
    }

    public void sendEmailRitardoAssenza(String email, String oggetto, String descrizione) {
        SendEmail sender = new SendEmail();
        sender.sendEmailGenerica(email, oggetto, descrizione);
    }
    
}
