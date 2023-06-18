package com.gest.GestioneSalario.Control;

import com.gest.Entity.BustaPaga;

public class TestGestioneSalario {
    public static void main(String[] args) {
        AccreditaSalarioCtl accreditaCtl = new AccreditaSalarioCtl();
        CalcoloSalarioCtl calcoloCtl = new CalcoloSalarioCtl();
        GestioneSalarioCtl gestioneSalarioCtl = new GestioneSalarioCtl();

        //for(BustaPaga bustaPaga: accreditaCtl.getBustePagaDaPagare())
        //    System.out.println(bustaPaga.toString());

        //System.out.println("Effettuo il calcolo: " );
        //calcoloCtl.effettuaCalcolo();

        //for(BustaPaga bustaPaga: accreditaCtl.getBustePagaDaPagare())
        //    System.out.println(bustaPaga.toString());

        //System.out.println("Accredita salario: ");

        //accreditaCtl.accreditaSalario();

        for(BustaPaga bustaPaga: gestioneSalarioCtl.getBustePaga(7, 1, 2022, 2, 2024)) {
            System.out.println(bustaPaga);
        }
    }
}
