package com.gest.GestioneAccount.Control;

import com.gest.Common.DBMSservice;
import com.gest.Common.SendEmail;
import com.gest.Entity.Utente;

public class RecuperaCredenzialiControl {
    private String email;
    private Utente utente;

    public RecuperaCredenzialiControl() {
        utente = new Utente();
    }

    public boolean verificaEmail(String email) {
        DBMSservice dbms = new DBMSservice();
        this.utente = dbms.queryVerificaEmail(email);
        if(this.utente == null)
            return false;
        this.email = email;
        return true;
    }

    public void sendEmail() {
        SendEmail sender = new SendEmail();
	    sender.sendEmail(this.utente.getEmail(), Integer.toString(this.utente.getMatricola()), this.utente.getPassw());
    } 

}
