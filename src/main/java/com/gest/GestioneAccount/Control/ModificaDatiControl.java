package com.gest.GestioneAccount.Control;

import javax.swing.JFrame;

import com.gest.Common.DBMSservice;
import com.gest.Entity.Utente;

public class ModificaDatiControl {
    private Utente utente;
    public ModificaDatiControl (Utente utente) {
        this.utente = utente;
    }

    public void modificaDati(String colonna, String valore) {
        DBMSservice dbms = new DBMSservice();
        dbms.queryUpdateDati(colonna, valore, this.utente.getMatricola());
        if(colonna.compareTo("iban")==0) {
            this.utente.setIban(valore);
        } else if(colonna.compareTo("telefono")==0) {
            this.utente.setTelefono(valore);
        } else if (colonna.compareTo("email")==0){
             this.utente.setEmail(valore);
        }
        System.out.println("Dati aggiornati");
    }

    public void modificaDati(String via, String n_civ, String cap) {
        DBMSservice dbms = new DBMSservice();
        dbms.queryUpdateDati("via", via, this.utente.getMatricola());
        dbms.queryUpdateDati("n_civ", n_civ, this.utente.getMatricola());
        dbms.queryUpdateDati("cap", cap, this.utente.getMatricola());
        this.utente.setVia(via);
        this.utente.setN_civ(n_civ);
        this.utente.setCap(cap);
        System.out.println("Dati aggiornati");
    }
}
