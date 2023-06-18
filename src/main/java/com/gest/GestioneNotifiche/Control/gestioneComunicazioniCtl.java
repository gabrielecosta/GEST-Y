package com.gest.GestioneNotifiche.Control;

import java.util.ArrayList;

import com.gest.Common.DBMSservice;
import com.gest.Entity.Impiegato;
import com.gest.Entity.Utente_Notifica;

public class gestioneComunicazioniCtl {
    private ArrayList<Impiegato> listaImpiegatiSelezionati;

    public gestioneComunicazioniCtl() {
        this.listaImpiegatiSelezionati = new ArrayList<>();
    }

    public ArrayList<Impiegato> getListaImpiegatiSelezionati() {
        return this.listaImpiegatiSelezionati;
    }

    public ArrayList<Impiegato> getListaImpiegati() {
        DBMSservice dbms = new DBMSservice();
        return dbms.queryGetListaImpiegati();
    }

    public void addImpiegatoSelezionato(Impiegato imp) {
        this.listaImpiegatiSelezionati.add(imp);
    }

    public ArrayList<Utente_Notifica> getListaComunicazioni(int matricola) {
        DBMSservice dbms = new DBMSservice();
        return dbms.queryGetListaComunicazioni(matricola);
    }

    public void inserisciComunicazione(String comunicazione) {
        DBMSservice dbms = new DBMSservice();
        for(Impiegato imp: this.getListaImpiegatiSelezionati())
            dbms.querySendComunicazione(imp.getMatricola(), comunicazione);
    }
    
    public void inserisciComunicazione(int matricola, String comunicazione) {
        DBMSservice dbms = new DBMSservice();
        dbms.querySendComunicazione(matricola, comunicazione);
    }
}
