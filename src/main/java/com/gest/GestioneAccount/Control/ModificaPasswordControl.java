package com.gest.GestioneAccount.Control;

import com.gest.Common.DBMSservice;
import com.gest.Entity.Utente;

public class ModificaPasswordControl {
    private String old_password;
    private String new_password;
    private String confirm_password;
    private Utente utente;

    public ModificaPasswordControl(Utente utente) {
        this.utente = utente;
    }

    //public String getPassword(Utente )

    public String getOld_password() {
        return old_password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }
    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public boolean checkPassword() {
        if(this.getOld_password().compareTo(this.utente.getPassw()) != 0 ) {
            System.out.println("La vecchia password non corrisponde");
            return false;
        } else if (this.getOld_password().compareTo(this.getNew_password()) != 0) {
            if(this.getNew_password().compareTo(this.getConfirm_password()) == 0) {
                return true;
            } else {
                System.out.println("La conferma della password non corrisponde");
                return false;
            }
        } else {
            System.out.println("La nuova password è uguale a quella vecchia");
            return false;
        }
    }

    public void updatePassword() {
        DBMSservice dbms = new DBMSservice();
        dbms.queryUpdatePassword(this.utente.getMatricola(), this.new_password);
        this.utente.setPassw(this.new_password);
        System.out.println("Password aggiornata");
    }

}
