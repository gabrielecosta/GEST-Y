package com.gest.Entity;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Richiesta {
    private int id_richiesta;
    private int ref_impiegato;
    private String tipologia;
    private String motivazione;
    private String link_certificato;
    private LocalDate data_inizio;
    private LocalDate data_fine;
    private boolean esito;

    public Richiesta() { }
    
    public Richiesta(int id_richiesta, int ref_impiegato, String tipologia, String motivazione, String link_certificato, LocalDate data_inizio, LocalDate data_fine, boolean esito) {
        this.setId_richiesta(id_richiesta);
        this.setRef_impiegato(ref_impiegato);
        this.setTipologia(tipologia);
        this.setMotivazione(motivazione);
        this.setLink_certificato(link_certificato);
        this.setData_inizio(data_inizio);
        this.setData_fine(data_fine);
        this.setEsito(esito);
    }

    public Richiesta(int ref_impiegato, String tipologia, String motivazione, String link_certificato, LocalDate data_inizio, LocalDate data_fine, boolean esito) {
        this.setRef_impiegato(ref_impiegato);
        this.setTipologia(tipologia);
        this.setMotivazione(motivazione);
        this.setLink_certificato(link_certificato);
        this.setData_inizio(data_inizio);
        this.setData_fine(data_fine);
        this.setEsito(esito);
    }
    
    public Richiesta(int ref_impiegato, String tipologia, String motivazione, String link_certificato, LocalDate data, boolean esito) {
        this.setRef_impiegato(ref_impiegato);
        this.setTipologia(tipologia);
        this.setMotivazione(motivazione);
        this.setLink_certificato(link_certificato);
        this.setData_inizio(data);
        this.setData_fine(data);
        this.setEsito(esito);
    }

    public Richiesta(int ref_impiegato, String tipologia, String motivazione, LocalDate data_inizio, LocalDate data_fine, boolean esito) {
        this.setRef_impiegato(ref_impiegato);
        this.setTipologia(tipologia);
        this.setMotivazione(motivazione);
        this.setLink_certificato("null");
        this.setData_inizio(data_inizio);
        this.setData_fine(data_fine);
        this.setEsito(esito);
    }
    
    public int getId_richiesta() {
        return id_richiesta;
    }
    public void setId_richiesta(int id_richiesta) {
        this.id_richiesta = id_richiesta;
    }
    public int getRef_impiegato() {
        return ref_impiegato;
    }
    public void setRef_impiegato(int ref_impiegato) {
        this.ref_impiegato = ref_impiegato;
    }
    public String getTipologia() {
        return tipologia;
    }
    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }
    public String getMotivazione() {
        return motivazione;
    }
    public void setMotivazione(String motivazione) {
        this.motivazione = motivazione;
    }
    public String getLink_certificato() {
        return link_certificato;
    }
    public void setLink_certificato(String link_certificato) {
        this.link_certificato = link_certificato;
    }
    public LocalDate getData_inizio() {
        return data_inizio;
    }
    public void setData_inizio(LocalDate data_inizio) {
        this.data_inizio = data_inizio;
    }
    public LocalDate getData_fine() {
        return data_fine;
    }
    public void setData_fine(LocalDate data_fine) {
        this.data_fine = data_fine;
    }
    public boolean isEsito() {
        return esito;
    }
    public void setEsito(boolean esito) {
        this.esito = esito;
    }

    public String toString() {
        return "{ " + this.getRef_impiegato() + ", " + this.getTipologia() + ", " + this.getData_inizio() + ", " + this.getData_fine() + ", " + this.isEsito() + " }";
    }

    public static Richiesta createFromDB(ResultSet row) throws SQLException {
        int id_richiesta = row.getInt(1);
        int ref_impiegato = row.getInt(2);
        String tipologia = row.getString(3);
        String motivazione = row.getString(4);
        String link_certificato = row.getString(5);
        LocalDate dataInizio = convertToLocalDateViaSqlDate(row.getDate(6));
        LocalDate dataFine = convertToLocalDateViaSqlDate(row.getDate(7));
        Boolean esito = row.getBoolean(8);
        return new Richiesta(id_richiesta,ref_impiegato, tipologia, motivazione, link_certificato, dataInizio, dataFine, esito);
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
