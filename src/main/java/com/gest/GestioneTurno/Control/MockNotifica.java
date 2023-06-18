package com.gest.GestioneTurno.Control;

import com.gest.Common.DBMSservice;
import com.gest.Entity.Notifica;

public class MockNotifica {
    public static void main(String[] args) {
        DBMSservice dbms = new DBMSservice();
        Notifica notifica1 = new Notifica(1,"comunicazione");
        System.out.println("Notifica: " + notifica1.toString());
        dbms.addNotifica(notifica1);

        Notifica notifica2 = new Notifica(2,"licenziamento");
        System.out.println("Notifica: " + notifica2.toString());
        dbms.addNotifica(notifica2);

        Notifica notifica3 = new Notifica(3,"ritardo");
        System.out.println("Notifica: " + notifica3.toString());
        dbms.addNotifica(notifica3);

        Notifica notifica4 = new Notifica(4,"assenza");
        System.out.println("Notifica: " + notifica4.toString());
        dbms.addNotifica(notifica4);

        Notifica notifica5 = new Notifica(5,"variazione turni");
        System.out.println("Notifica: " + notifica5.toString());
        dbms.addNotifica(notifica5);

        Notifica notifica6 = new Notifica(6,"calcolo salario");
        System.out.println("Notifica: " + notifica6.toString());
        dbms.addNotifica(notifica6);
    }
}
