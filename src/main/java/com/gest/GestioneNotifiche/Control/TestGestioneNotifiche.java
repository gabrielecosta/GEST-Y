package com.gest.GestioneNotifiche.Control;

import com.gest.Entity.Notifica;
import com.gest.Entity.Richiesta;
import com.gest.Entity.Turno_ritardo;
import com.gest.Entity.Utente_Notifica;

import java.time.LocalDate;

public class TestGestioneNotifiche {
    public static void main(String[] args) {
        gestioneNotificheCtl gestioneNotifiche = new gestioneNotificheCtl();
        gestioneRichiesteCtl gestioneRichieste = new gestioneRichiesteCtl();
        notificaRitardoCtl notificaCtl = new notificaRitardoCtl();

        /*
        //Stampa notifiche chiusura servizi
        System.out.println("Notifiche chisura servizi: ");
        for(Utente_Notifica notifica: gestioneNotifiche.getListaChiusuraServizi())
            System.out.println(notifica.toString());

        //Stampa notifiche liste assenze e ritardi
        LocalDate inizio = LocalDate.parse("2023-01-01");
        LocalDate fine = LocalDate.parse("2023-01-02");

        System.out.println("Notifiche assenze e ritardi: ");
        for(Utente_Notifica notifica: gestioneNotifiche.getListaNotificheRitardiAssenze(inizio, fine))
            System.out.println(notifica.toString());

        //Stampa turni in ritardo
        System.out.println("Turni in ritardo con la motivazione: ");
        for(Turno_ritardo turno: gestioneNotifiche.getListaMotivazioni()) {
            System.out.println(turno.toString());
        }
         */

        /*
        System.out.println("Lista richieste concesse: ");
        for(Richiesta richiesta: gestioneRichieste.getListaRichiesteConcesse())
            System.out.println(richiesta.toString());

        System.out.println("Lista richieste: ");
            for(Richiesta richiesta: gestioneRichieste.getListaRichieste(20))
               System.out.println(richiesta.toString());
         */


        notificaCtl.checkRitardoIngressoListaTurni();
        notificaCtl.checkRitardoUscitaListaTurni();
        notificaCtl.checkAssenzaListaTurni();
    }
}
