package com.gest.GestioneAccount.Control;

import com.gest.Entity.Utente;

public class TestGestioneAccount {
    public static void main(String[] args) throws Exception {
        System.out.println("Classe per testare le control del macro caso d'uso GESTIONE ACCOUNT");
        
        /*
        System.out.println("Test di LOGIN: ");
        int matricola1 = 15;
        String passw1 = "123456";
        int matricola2 = 25;
        String passw2 = "123456";
        int matricola3 = 45;
        String passw3 = "123456";


         ControlLogin login = new ControlLogin();

         if(login.verificaCredenziali(matricola1, passw1)) {
             Utente utente = new Utente();
             utente = login.createEntity(matricola1);
             System.out.println(utente.toString());
         } else {
             System.out.println("Credeziali errate o utente inesistente");
         }
 
         if(login.verificaCredenziali(matricola2, passw2)) {
             Utente utente = new Utente();
             utente = login.createEntity(matricola2);
             System.out.println(utente.toString());
         } else {
             System.out.println("Credeziali errate o utente inesistente");
         }
 
         if(login.verificaCredenziali(matricola3, passw3)) {
             Utente utente = new Utente();
             utente = login.createEntity(matricola3);
             System.out.println(utente.toString());
         } else {
             System.out.println("Credeziali errate o utente inesistente");
         }
         */


        /*
        System.out.println("Testo adesso Modifica password: ");
        int matricola1 = 15;
        String passw1 = "1234567";
        ControlLogin login = new ControlLogin();

        if(login.verificaCredenziali(matricola1, passw1)) {
            Utente utente = new Utente();
            utente = login.createEntity(matricola1);
            //System.out.println(utente.toString());
            //System.out.println("Vecchia password: ");
            String old_password = "1234567";
            String new_password = "123456";
            String confirm_password = "123456";

            ModificaPasswordControl modificaPassword = new ModificaPasswordControl(utente);
            modificaPassword.setOld_password(old_password);
            modificaPassword.setNew_password(new_password);
            modificaPassword.setConfirm_password(confirm_password);
            if(modificaPassword.checkPassword())
                modificaPassword.updatePassword();
        } else {
            System.out.println("Credeziali errate o utente inesistente");
        }
         */

         System.out.println("Testo adesso Modifica dati: ");
         int matricola1 = 15;
         String passw1 = "123456";
         ControlLogin login = new ControlLogin();
         if(login.verificaCredenziali(matricola1, passw1)) {
            Utente utente = new Utente();
            utente = login.createEntity(matricola1);
            //System.out.println(utente.toString());
            //System.out.println("Vecchia password: ");

            ModificaDatiControl modificaDati = new ModificaDatiControl(utente);
            modificaDati.modificaDati("email", "bianchi1234@gmail.com");
            modificaDati.modificaDati("Palermo", "100", "90121");

        } else {
            System.out.println("Credeziali errate o utente inesistente");
        }
    }
}
