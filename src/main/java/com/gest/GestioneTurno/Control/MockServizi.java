package com.gest.GestioneTurno.Control;

import com.gest.Common.DBMSservice;
import com.gest.Entity.Servizio;

public class MockServizi {
    public static void main(String[] args) {
        DBMSservice dbms = new DBMSservice();
        Servizio servizio1 = new Servizio(1,"Servizio1", 20);
        System.out.println("Servizio1: " + servizio1.toString());
        dbms.addServizio(servizio1);
        
        Servizio servizio2 = new Servizio(2,"Servizio2", 15);
        System.out.println("Servizio2: " + servizio2.toString());
        dbms.addServizio(servizio2);

        Servizio servizio3 = new Servizio(3,"Servizio3", 10);
        System.out.println("Servizio3: " + servizio3.toString());
        dbms.addServizio(servizio3);

        Servizio servizio4 = new Servizio(4,"Servizio4", 5);
        System.out.println("Servizio4: " + servizio4.toString());
        dbms.addServizio(servizio4);
        
        
    }
}
