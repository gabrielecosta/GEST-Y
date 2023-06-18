package com.gest.GestioneSalario.Control;
import com.gest.Common.DBMSservice;
import java.util.ArrayList;

import com.gest.Common.DBMSservice;
import com.gest.Entity.BustaPaga;

public class GestioneSalarioCtl {

    public GestioneSalarioCtl() { }

    public ArrayList<BustaPaga> getBustePaga(int matricola, int mese_inizio, int anno_inizio, int mese_fine, int anno_fine) {
        DBMSservice dbms = new DBMSservice();
        System.out.println("Matricola: " + matricola);
        System.out.println("Mese_inizio: " + mese_inizio);
        System.out.println("Mese_fine: " + mese_fine);
        System.out.println("Anno inizio: " + anno_inizio);
        System.out.println("Anno fine: " + anno_fine);
        return dbms.queryGetListaBustePaga(matricola, mese_inizio, anno_inizio, mese_fine, anno_fine);
    }
    
    public ArrayList<BustaPaga> getBustePagaImp(int matricola) {
        DBMSservice dbms = new DBMSservice();
        System.out.println("Matricola: " + matricola);
        for(BustaPaga bustapaga: dbms.queryGetListaBustePaga(matricola)) {
        	System.out.println("Busta paga: " + bustapaga);
        }
        return dbms.queryGetListaBustePaga(matricola);
    }
}
