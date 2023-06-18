package com.gest.Entity;

import java.time.LocalDate;
import java.util.ArrayList;

public class Indisponibilita {
    private int ref_impiegato;
    private ArrayList<LocalDate> data;

    public Indisponibilita(int id_impiegato) {
        this.setRef_impiegato(id_impiegato);
        data = new ArrayList<>();
    }

    public int getRef_impiegato() {
        return ref_impiegato;
    }

    public void setRef_impiegato(int ref_impiegato) {
        this.ref_impiegato = ref_impiegato;
    }

    public ArrayList<LocalDate> getData() {
        return data;
    }

    public void addData(LocalDate data) {
        this.data.add(data);
    }

    public String toString() {
        return "{ " + this.getRef_impiegato() + ", Date: [ " + this.getData().toString() + "] }";
    }

}
