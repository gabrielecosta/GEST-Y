package com.gest.Entity;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class Turno {
    private int id_turno;
    private int ref_impiegato;
    private int ref_servizio;
    private LocalDate dataTurno;
    private LocalTime oraInizio;
    private LocalTime oraFine;
    private boolean firmaIN;
    private boolean firmaOUT;
    private boolean isClosed;

    public Turno () { }
    
    public Turno(int id_turno, int ref_impiegato, int ref_servizio, LocalDate dataTurno, LocalTime oraInizio, LocalTime orafine) {
        this.setId_turno(id_turno);
        this.setRef_impiegato(ref_impiegato);
        this.setRef_servizio(ref_servizio);
        this.setDataTurno(dataTurno);
        this.setOraInizio(oraInizio);
        this.setOraFine(orafine);
        this.setFirmaIN(false);
        this.setFirmaOUT(false);
        this.setClosed(false);
    }

    public Turno(int ref_impiegato, int ref_servizio, LocalDate dataTurno, LocalTime oraInizio, LocalTime orafine) {
        this.setRef_impiegato(ref_impiegato);
        this.setRef_servizio(ref_servizio);
        this.setDataTurno(dataTurno);
        this.setOraInizio(oraInizio);
        this.setOraFine(orafine);
        this.setFirmaIN(false);
        this.setFirmaOUT(false);
        this.setClosed(false);
    }

    public Turno(int id_turno, int ref_impiegato, int ref_servizio, LocalDate dataTurno, LocalTime oraInizio, LocalTime orafine, boolean firmaIn, boolean firmaOut, boolean isClosed) {
        this.setId_turno(id_turno);
        this.setRef_impiegato(ref_impiegato);
        this.setRef_servizio(ref_servizio);
        this.setDataTurno(dataTurno);
        this.setOraInizio(oraInizio);
        this.setOraFine(orafine);
        this.setFirmaIN(firmaIn);
        this.setFirmaOUT(firmaOut);
        this.setClosed(isClosed);
    }


    public int getId_turno() {
        return id_turno;
    }
    public void setId_turno(int id_turno) {
        this.id_turno = id_turno;
    }
    public int getRef_impiegato() {
        return ref_impiegato;
    }
    public void setRef_impiegato(int ref_impiegato) {
        this.ref_impiegato = ref_impiegato;
    }
    public int getRef_servizio() {
        return ref_servizio;
    }
    public void setRef_servizio(int ref_servizio) {
        this.ref_servizio = ref_servizio;
    }
    public LocalDate getDataTurno() {
        return dataTurno;
    }
    public void setDataTurno(LocalDate dataTurno) {
        this.dataTurno = dataTurno;
    }
    public LocalTime getOraInizio() {
        return oraInizio;
    }
    public void setOraInizio(LocalTime oraInizio) {
        this.oraInizio = oraInizio;
    }
    public LocalTime getOraFine() {
        return oraFine;
    }
    public void setOraFine(LocalTime oraFine) {
        this.oraFine = oraFine;
    }

    public boolean isFirmaIN() {
        return firmaIN;
    }
    public void setFirmaIN(boolean firmaIN) {
        this.firmaIN = firmaIN;
    }
    public boolean isFirmaOUT() {
        return firmaOUT;
    }
    public void setFirmaOUT(boolean firmaOUT) {
        this.firmaOUT = firmaOUT;
    }
    public boolean isClosed() {
        return isClosed;
    }
    public void setClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public String toString() {
        return "{ " + this.getRef_impiegato() + ", " + this.getRef_servizio() + ", " + this.getDataTurno() + ", " + this.getOraInizio() + ", " + this.getOraFine() + " }";
    }

    public static Turno createFromDB(ResultSet row) throws SQLException {
        int id_turno = row.getInt(1);
        int ref_impiegato = row.getInt(2);
        int ref_servizio = row.getInt(3);
        LocalDate dataTurno = convertToLocalDateViaSqlDate(row.getDate(4));
        LocalTime oraInizio = toLocalTime(row.getTime(5));
        LocalTime oraFine = toLocalTime(row.getTime(6));
        Boolean firmaIngresso = row.getBoolean(7);
        Boolean firmaUscita = row.getBoolean(8);
        Boolean isClosed = row.getBoolean(9);
        return new Turno(id_turno, ref_impiegato, ref_servizio, dataTurno, oraInizio, oraFine, firmaIngresso, firmaUscita, isClosed);
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

    public static LocalTime toLocalTime(java.sql.Time time) {
        return time.toLocalTime();
    }

    public static Time toSQLTime(LocalTime timeLocal) {
        return Time.valueOf(timeLocal);
    }

    
}
