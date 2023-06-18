package com.gest.GestionePostazione.Control;

import java.util.Objects;

import com.gest.Entity.Turno;

public class TestGestionePresenza {
    public static void main(String[] args) {
        //dichiaro la matricola
        int matricola = 9;
        /*
        //dovrebbe farlo timbrare perché è in ritardo
        //test con la matricola 22, che inizia invece dopo
        //ingressoRemotoCtl ingressoRitardo = new ingressoRemotoCtl();
        
        
        //firma ingresso da remoto
        ingressoRemotoCtl ingressoRitardo = new ingressoRemotoCtl();
        if(ingressoRitardo.checkImpiegato(matricola)) {
            //impiegato esiste
            System.out.println("Impiegato esiste");
            ingressoRitardo.firmaIngresso(ingressoRitardo.getTurno(matricola), matricola);
        } else {
            //impiegato non esiste
            System.out.println("Impiegato non esiste");
        }
        
        
        //test registrazione presenza dalla postazione
        //firma dell'ingresso

        registraPresenzaCtl registraCtl = new registraPresenzaCtl();

        //firma ingresso dalla postazione
        registraPresenzaCtl registraCtl = new registraPresenzaCtl();
        if(registraCtl.checkImpiegato(matricola)) {
            System.out.println("Impiegato esiste");
            if(registraCtl.isPresente(registraCtl.getTurno(matricola))) {
                //turno non esiste in ingresso
                System.out.println("Impiegato non in turno");
            } else {
                registraCtl.firmaIngresso(registraCtl.getTurno(matricola), matricola);
            }
        } else {
            System.out.println("Impiegato non esiste");
        }

        //firma uscita dalla postazione
        registraPresenzaCtl registraCtl = new registraPresenzaCtl();
        if(registraCtl.checkImpiegato(matricola)) {
            System.out.println("Impiegato esiste");
            if(registraCtl.isPresente(registraCtl.getTurnoFirmato(matricola))) {
                //turno non esiste in ingresso
                System.out.println("Impiegato non in turno");
            } else {
                registraCtl.firmaUscita(registraCtl.getTurnoFirmato(matricola), matricola);
            }
        } else {
            System.out.println("Impiegato non esiste");
        }
        
*/
    }    
}
