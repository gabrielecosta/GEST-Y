package com.gest.GestioneNotifiche.Control;

import java.time.LocalDate;
import java.util.ArrayList;

import com.gest.Common.DBMSservice;
import com.gest.Entity.Turno_ritardo;
import com.gest.Entity.Utente_Notifica;

public class gestioneNotificheCtl {
    public gestioneNotificheCtl() { }

    public ArrayList<Utente_Notifica> getListaChiusuraServizi() {
        DBMSservice dbms = new DBMSservice();
        return dbms.queryGetListaChiusuraServizi();
    }
    
    public ArrayList<Utente_Notifica> getListaNotifiche(int matricola) {
    	DBMSservice dbms = new DBMSservice();
    	return dbms.getListaNotifiche(matricola);
    }

    public ArrayList<Utente_Notifica> getListaNotificheRitardiAssenze(LocalDate inizio, LocalDate fine) {
        DBMSservice dbms = new DBMSservice();
        return dbms.getListaNotificheRitardiAssenze(inizio, fine);
    }

    public ArrayList<Turno_ritardo> getListaMotivazioni(int matricola) {
        DBMSservice dbms = new DBMSservice();
        return dbms.queryGetMotivazioneRitardo(matricola);
    }

    public ArrayList<Turno_ritardo> getListaMotivazioni() {
        DBMSservice dbms = new DBMSservice();
        return dbms.queryGetMotivazioneRitardo();
    }
}
