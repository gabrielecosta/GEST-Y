package com.gest.GestioneAccount.Control;
import java.util.ArrayList;

import com.gest.Common.DBMSservice;
import com.gest.Entity.Utente;

public class MockUtenti {
    public static void main(String[] args) throws Exception {
        ArrayList<Utente> listaImpiegati = new ArrayList<>();
        //creazione del datore di lavoro
        String iban = "IT001E1234";
        String telefono = "+39123456";
        String via = "Roma";
        String n_civ = "100";
        String cap = "90100";

        //Utente ddl = new Utente("GBRL02","Gabriele","Costa","M","gabrielenicolocosta.90@gmail.com", "123456", iban, telefono, via, n_civ, cap, true, true, 1, false, false, null, null);
        //creazione impiegati ruolo 1
        DBMSservice dbmsService = new DBMSservice();
        dbmsService.getStatus();
        //dbmsService.addUtente(ddl);
        //ddl.setMatricola(dbmsService.getMatricola(ddl.getCf()));

        //System.out.println(ddl.toString());

        //creazione impiegati ruolo 1
        //per semplicità i primi 5 sono admin
        for(int i=1; i<=10; i++) {
            String s = String.valueOf(i);
            Utente utente = new Utente("MRCBNC" + s,"Marco", "Bianchi" ,"M", "marcobianchi" + s + "@gmail.com", "123456", iban, telefono, via, n_civ, cap, false, false, 1, false, false, null, null);
            if(i <= 5) {
                utente.setIsAdmin(true);
            }
            listaImpiegati.add(utente);
        }
        //creazione impiegato ruolo 2
        for(int i=1; i<=5; i++) {
            String s = String.valueOf(i);
            Utente utente = new Utente("MRRRSS" + s,"Mario", "Rossi" ,"M", "mariorossi" + s + "@gmail.com", "123456", iban, telefono, via, n_civ, cap, false, false, 2, false, false, null, null);
            listaImpiegati.add(utente);
        }
        //creazione impiegato ruolo 3
        for(int i=1; i<=5; i++) {
            String s = String.valueOf(i);
            Utente utente = new Utente("CRLNRR" + s,"Carlo", "Neri" ,"M", "carloneri" + s + "@gmail.com", "123456", iban, telefono, via, n_civ, cap, false, false, 3, false, false, null, null);
            listaImpiegati.add(utente);
        }
        //creazione impiegato ruolo 4
        for(int i=1; i<=5; i++) {
            String s = String.valueOf(i);
            Utente utente = new Utente("GSPVRD" + s,"Giuseppe", "Verdi" ,"M", "giuseppeverdi" + s + "@gmail.com", "123456", iban, telefono, via, n_civ, cap, false, false, 4, false, false, null, null);
            listaImpiegati.add(utente);
        }

        for(int i=0; i<listaImpiegati.size(); i++) {
            dbmsService.addUtente(listaImpiegati.get(i));
            listaImpiegati.get(i).setMatricola(dbmsService.getMatricola(listaImpiegati.get(i).getCf()));
        }

        for(Utente utente: listaImpiegati) {
            System.out.println(utente.toString());
        }
    }
}
