package com.gest.GestioneImpiegato.Control;

import java.time.LocalDate;

import com.gest.Common.DBMSservice;
import com.gest.Entity.Impiegato;
import com.gest.Entity.Utente;
import com.gest.Common.SendEmail;

public class gestioneImpiegatoCtl {
    public gestioneImpiegatoCtl() { }

    public boolean existsImpiegato(int matricola) {
        DBMSservice dbms = new DBMSservice();
        return dbms.queryExistsImpiegato(matricola);
    }

    public boolean checkPassword(String password, String password_inserita) {
        if(password.compareTo(password_inserita) == 0)
            return true;
        return false;
    }

    public void licenzia(int matricola) {
        DBMSservice dbms = new DBMSservice();
        //prende invia una email di avvenuto licenziamento
        String descrizione = "Ci dispiace comunicarle che è stata presentata istanza di licenziamento.";
        this.sendEmail(dbms.queryGetEmail(matricola), descrizione, "Licenziamento");
        //inserisce comunicazione nel portale
        dbms.queryInsertLicenziamento(matricola, descrizione);
        //modifico lo status dell'impiegato a licenziato e imposto la data di licenziamento
        dbms.queryLicenziaImpiegato(matricola);
    }

    public void assumi(Utente utente) {
        DBMSservice dbms = new DBMSservice();
        //inserisci impiegato
        utente.setDataAssunzione(LocalDate.now());
        dbms.addUtente(utente);
        //prendi la matricola
        int matricola = dbms.queryGetMatricola(utente.getEmail(), utente.getCf(), utente.getNome(), utente.getCognome());
        //invia email di assunzione
        String descrizione = "Benvenuto nella nostra azienda! A partire dal prossimo trimestre inizierai a lvaorare...Ecco intanto le tue credenziali: ";
        descrizione = descrizione + " {Matricola: " + matricola + ", Password: " + utente.getPassw() + "}";
        this.sendEmail(utente.getEmail(), "Assunzione", descrizione);
    }

    public void sendEmail(String email, String oggetto, String descrizione) {
        System.out.println("Email: " + email);
        System.out.println("Oggetto: " + oggetto);
        System.out.println("Descrizione: " + descrizione);
        SendEmail sender = new SendEmail();
        sender.sendEmailGenerica(email, oggetto, descrizione);
    }

    public void modificaDati(Impiegato imp) {
        DBMSservice dbms = new DBMSservice();
        // modifica email:
        dbms.queryUpdateDati("email", imp.getEmail(), imp.getMatricola());
        // modifica telefono:
        dbms.queryUpdateDati("telefono", imp.getTelefono(), imp.getMatricola());
    }

    public void modificaRuolo(int matricola, int nuovo_ruolo) {
        //modifica ruolo nel DB
        DBMSservice dbms = new DBMSservice();
        dbms.queryUpdateDati("ruolo", nuovo_ruolo, matricola);
        //devo aggiornare pure le sue buste paga non ancora pagate a partire dal mese successivo
        int stipendio_base = 0;
        if(nuovo_ruolo== 4)
            stipendio_base = 1000;
        if (nuovo_ruolo == 3)
           stipendio_base = 1100;
        if (nuovo_ruolo == 2)
            stipendio_base = 1200;
        if (nuovo_ruolo == 1)
            stipendio_base = 1300;

        if(dbms.queryIsAdmin(matricola))
            stipendio_base += 100;
            
        dbms.queryUpdateBustaPaga(matricola, stipendio_base, LocalDate.now().getMonthValue(), LocalDate.now().getYear());
    }
}
