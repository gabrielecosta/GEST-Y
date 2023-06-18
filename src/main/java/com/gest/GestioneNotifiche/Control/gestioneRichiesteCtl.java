package com.gest.GestioneNotifiche.Control;

import java.util.ArrayList;

import com.gest.Common.DBMSservice;
import com.gest.Entity.Richiesta;

public class gestioneRichiesteCtl {
    public gestioneRichiesteCtl() { }

    public ArrayList<Richiesta> getListaRichieste(int matricola) {
        DBMSservice dbms = new DBMSservice();
        return dbms.queryGetListaRichiesteImpiegato(matricola);
    }

    public ArrayList<Richiesta> getListaRichiesteConcesse() {
        DBMSservice dbms = new DBMSservice();
        return dbms.queryGetListaRichiesteConcesse();
    }
}
