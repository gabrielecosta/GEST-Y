package com.gest.Entity;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class BustaPaga {
    private int ref_impiegato;
    private int mese_competenza;
    private int anno_competenza;
    private int stipendio_base;
    private int ore_servizio_1;
    private int ore_servizio_2;
    private int ore_servizio_3;
    private int ore_servizio_4;
    private int ore_straodinario;
    private int num_ritardi;
    private int num_assenze;
    private int importoTotale;
    private boolean isPagato;

    public BustaPaga() { }

    public BustaPaga(int ref_impiegato, int mese_competenza, int anno_competenza, int stipendio_base, 
    int ore_servizio_1, int ore_servizio_2, int ore_servizio_3, int ore_servizio_4, int ore_straodinario,
    int num_ritardi, int num_assenze, int importoTotale, boolean isPagato) {
        this.setRef_impiegato(ref_impiegato);
        this.setMese_competenza(mese_competenza);
        this.setAnno_competenza(anno_competenza);
        this.setOre_servizio_1(ore_servizio_1);
        this.setOre_servizio_2(ore_servizio_2);
        this.setOre_servizio_3(ore_servizio_3);
        this.setOre_servizio_4(ore_servizio_4);
        this.setOre_straodinario(ore_straodinario);
        this.setStipendio_base(stipendio_base);
        this.setImportoTotale(importoTotale);
        this.setNum_ritardi(num_ritardi);
        this.setNum_assenze(num_assenze);
        this.setPagato(isPagato);
    }

    public BustaPaga(Impiegato utente, int mese_competenza, int anno_competenza) {
        this.setRef_impiegato(utente.getMatricola());
        this.setMese_competenza(mese_competenza);
        this.setAnno_competenza(anno_competenza);
        this.setOre_servizio_1(0);
        this.setOre_servizio_2(0);
        this.setOre_servizio_3(0);
        this.setOre_servizio_4(0);
        this.setOre_straodinario(0);
        this.setStipendio_base(utente);
        this.setImportoTotale(this.getStipendio_base());
        this.setNum_ritardi(0);
        this.setNum_assenze(0);
        this.setPagato(false);
    }
    
    public int getRef_impiegato() {
        return ref_impiegato;
    }
    public void setRef_impiegato(int ref_impiegato) {
        this.ref_impiegato = ref_impiegato;
    }
    public int getMese_competenza() {
        return mese_competenza;
    }
    public void setMese_competenza(int mese_competenza) {
        this.mese_competenza = mese_competenza;
    }
    public int getAnno_competenza() {
        return anno_competenza;
    }
    public void setAnno_competenza(int anno_competenza) {
        this.anno_competenza = anno_competenza;
    }
    public int getStipendio_base() {
        return stipendio_base;
    }

    public void setStipendio_base(int stipendio_base) {
        this.stipendio_base = stipendio_base;
    }

    public void setStipendio_base(Impiegato utente) {
        if(utente.getRuolo()== 4)
            this.stipendio_base = 1000;
        if (utente.getRuolo() == 3)
            this.stipendio_base = 1100;
        if (utente.getRuolo() == 2)
            this.stipendio_base = 1200;
        if (utente.getRuolo() == 1)
            this.stipendio_base = 1300;

        if(utente.getIsAdmin())
            this.stipendio_base += 100;
    }
    
    public int getOre_servizio_1() {
        return ore_servizio_1;
    }
    public void setOre_servizio_1(int ore_servizio_1) {
        this.ore_servizio_1 = ore_servizio_1;
    }
    public int getOre_servizio_2() {
        return ore_servizio_2;
    }
    public void setOre_servizio_2(int ore_servizio_2) {
        this.ore_servizio_2 = ore_servizio_2;
    }
    public int getOre_servizio_3() {
        return ore_servizio_3;
    }
    public void setOre_servizio_3(int ore_servizio_3) {
        this.ore_servizio_3 = ore_servizio_3;
    }
    public int getOre_servizio_4() {
        return ore_servizio_4;
    }
    public void setOre_servizio_4(int ore_servizio_4) {
        this.ore_servizio_4 = ore_servizio_4;
    }
    public int getOre_straodinario() {
        return ore_straodinario;
    }
    public void setOre_straodinario(int ore_straodinario) {
        this.ore_straodinario = ore_straodinario;
    }
    public int getNum_ritardi() {
        return num_ritardi;
    }
    public void setNum_ritardi(int num_ritardi) {
        this.num_ritardi = num_ritardi;
    }
    public int getNum_assenze() {
        return num_assenze;
    }
    public void setNum_assenze(int num_assenze) {
        this.num_assenze = num_assenze;
    }
    public int getImportoTotale() {
        return importoTotale;
    }
    public void setImportoTotale(int importoTotale) {
        this.importoTotale = importoTotale;
    }
    public boolean isPagato() {
        return isPagato;
    }
    public void setPagato(boolean isPagato) {
        this.isPagato = isPagato;
    }
    
    public static BustaPaga createFromDB(ResultSet row) throws SQLException {
        int ref_impiegato = row.getInt(1);
        int mese_competenza = row.getInt(2);
        int anno_competenza = row.getInt(3);
        int stipendio_base = row.getInt(4);
        int ore_servizio_1 = row.getInt(5);
        int ore_servizio_2 = row.getInt(6);
        int ore_servizio_3 = row.getInt(7);
        int ore_servizio_4 = row.getInt(8);
        int ore_straodinario = row.getInt(9);
        int num_ritardi = row.getInt(10);
        int num_assenze = row.getInt(11);
        int importoTotale = row.getInt(12);
        boolean isPagato = row.getBoolean(13);
        return new BustaPaga(ref_impiegato, mese_competenza, anno_competenza, stipendio_base, ore_servizio_1, ore_servizio_2, ore_servizio_3, ore_servizio_4, ore_straodinario, num_ritardi, num_assenze, importoTotale, isPagato);
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

    public String toString() {
        return "{ Matricola: " + this.getRef_impiegato() + ", Mese: " + this.getMese_competenza() + ", Anno: " + this.getAnno_competenza() + ", ImportoTotale: " + this.getImportoTotale() + ", Pagato: " + this.isPagato() + " }"; 
    }
}
