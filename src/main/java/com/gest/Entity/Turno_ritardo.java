package com.gest.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Turno_ritardo {
    private int ref_turno;
    private int ref_matricola;
    private String motivazione;


    public Turno_ritardo() { }

    public Turno_ritardo(int ref_turno, int ref_matricola, String motivazione) {

    }

    public Turno_ritardo(int ref_turno, String motivazione) {

    }

    public int getRef_turno() {
        return ref_turno;
    }

    public void setRef_turno(int ref_turno) {
        this.ref_turno = ref_turno;
    }

    public int getRef_matricola() {
        return ref_matricola;
    }

    public void setRef_matricola(int ref_matricola) {
        this.ref_matricola = ref_matricola;
    }

    public String getMotivazione() {
        return motivazione;
    }

    public void setMotivazione(String motivazione) {
        this.motivazione = motivazione;
    }

    public String toString() {
        return "{ Turno: " + this.getRef_turno() + ", Matricola: " + this.getRef_matricola() + ", Motivazione: " + this.getMotivazione() + " }";
    }

    public static Turno_ritardo createFromDB(ResultSet row) throws SQLException {
        int ref_turno = row.getInt(1);
        int ref_impiegato = row.getInt(2);
        String motivazione = row.getString(3);
        return new Turno_ritardo(ref_turno, ref_impiegato, motivazione);
    }

}
