package com.gest.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.gest.Common.DBMSservice;

public class Impiegato extends Utente{
    private Indisponibilita dateIndisp;
    private boolean isAdmin;

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public Impiegato(int matricola, String cf, String nome, String cognome, String email, String iban, int ruolo, boolean admin) {
                this.setMatricola(matricola);
                this.setCf(cf);
                this.setNome(nome);
                this.setCognome(cognome);
                this.setEmail(email);
                this.setIban(iban);
                this.setRuolo(ruolo);
                dateIndisp = new Indisponibilita(matricola);
                this.setDateIndisp();
                this.isAdmin = admin;
    }

    public Impiegato() {
		// TODO Auto-generated constructor stub
	}

	public Indisponibilita getDateIndisp() {
        return dateIndisp;
    }

    //le prendo dal DB
    public void setDateIndisp() {
        DBMSservice dbms = new DBMSservice();
        for(LocalDate data: dbms.queryGetPeriodiAstensione(this.getMatricola()))
            this.dateIndisp.addData(data);
    }


    public Boolean checkDisp(LocalDate dataCalendario) {
        boolean returnValue = true;
        for(LocalDate data: this.getDateIndisp().getData()) {
            if (data.equals(dataCalendario))
                returnValue = false;
        }
        return returnValue;
    }

    public static Impiegato createFromDB(ResultSet row) throws SQLException {
        int matricola = row.getInt(1);
        String cf = row.getString(2);
        String nome = row.getString(3);
        String cognome = row.getString(4);
        String email = row.getString(6);
        String iban = row.getString(8);
        int ruolo = row.getInt(15);
        boolean admin = row.getBoolean(13);
        return new Impiegato(matricola, cf, nome, cognome, email, iban, ruolo, admin);
    }
}
