package com.gest.Entity;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Utente_Notifica {
    private int id_utente_notifica;
    private int ref_notifica;
    private int ref_impiegato;
    private LocalDate dataNotifica;
    private String descrizione;

    public Utente_Notifica(int id_utente_notifica, int ref_notifica, int ref_impiegato, LocalDate data_notifica, String descrzione) {
        this.setId_utente_notifica(id_utente_notifica);
        this.setRef_notifica(ref_notifica);
        this.setRef_impiegato(ref_impiegato);
        this.setData(data_notifica);
        this.setDescrizione(descrzione);
    }

    public Utente_Notifica(int ref_notifica, int ref_impiegato, LocalDate data_notifica, String descrzione) {
        this.setRef_notifica(ref_notifica);
        this.setRef_impiegato(ref_impiegato);
        this.setData(data_notifica);
        this.setDescrizione(descrzione);
    }

    public int getRef_notifica() {
        return ref_notifica;
    }

    public void setRef_notifica(int ref_notifica) {
        this.ref_notifica = ref_notifica;
    }

    public int getRef_impiegato() {
        return ref_impiegato;
    }

    public void setRef_impiegato(int ref_impiegato) {
        this.ref_impiegato = ref_impiegato;
    }
    
    public LocalDate getData() {
        return dataNotifica;
    }

    public void setData(LocalDate data) {
        this.dataNotifica = data;
    }
    
    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getId_utente_notifica() {
        return id_utente_notifica;
    }

    public void setId_utente_notifica(int id_utente_notifica) {
        this.id_utente_notifica = id_utente_notifica;
    }


    public String toString() {
        return "{ " + this.getRef_notifica() + ", " + this.getRef_impiegato() + ", " + this.getData() + ", " + this.getDescrizione() + " }";
    }

    public static Utente_Notifica createFromDB(ResultSet row) throws SQLException {
        int id_utente_notifica = row.getInt(1);
        int ref_notifica = row.getInt(2);
        int ref_impiegato = row.getInt(3);
        LocalDate data_notifica = convertToLocalDateViaSqlDate(row.getDate(4));
        String descrizione = row.getString(5);
        return new Utente_Notifica(id_utente_notifica, ref_notifica,ref_impiegato, data_notifica, descrizione);
    }

    private static Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        if(dateToConvert == null) {
            return null;
        } else {
            return java.sql.Date.valueOf(dateToConvert);
        }
    }

    private static LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        if(dateToConvert == null) {
            return null;
        } else {
            return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
        }
    }
    
}
