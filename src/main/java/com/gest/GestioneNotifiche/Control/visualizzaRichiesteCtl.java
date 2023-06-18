package com.gest.GestioneNotifiche.Control;

import java.util.ArrayList;

import com.gest.Common.DBMSservice;
import com.gest.Entity.Richiesta;

public class visualizzaRichiesteCtl {
    public visualizzaRichiesteCtl() { }

    public ArrayList<Richiesta> getListaRichiesteConcesse() {
        DBMSservice dbms = new DBMSservice();
        return dbms.queryGetListaRichiesteConcesse();
    }
}
