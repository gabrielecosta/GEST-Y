package com.gest.GestioneImpiegato.Control;

import com.gest.Entity.Impiegato;
import com.gest.Entity.Richiesta;

public class TestGestioneImpiegato {
    public static void main(String[] args) {
        cercaImpiegatoCtl cercaCtl = new cercaImpiegatoCtl();
        gestioneFerieCtl ferieCtl = new gestioneFerieCtl();
        /*
        System.out.println("Lista impiegati: ");
        for(Impiegato imp: cercaCtl.getListaImpiegati())
            System.out.println(imp.toString());

        String nome = "Mar";
        String cognome = "";
        System.out.println("Filtraggio per " + nome + " e " + cognome );
        for(Impiegato imp: cercaCtl.getListaImpiegatiFiltrata(nome, cognome)) {
            System.out.println(imp.toString());
        }
         */

        //for(Richiesta richiesta: ferieCtl.getListaFerie()) {
        //    System.out.println(richiesta);
        //}

        //approvo la prima per esempio e rifiuto la seconda
        //ferieCtl.approvaFerie(ferieCtl.getListaFerie().get(0));

        //nego la seconda
        //ferieCtl.rifiutaFerie(ferieCtl.getListaFerie().get(0));
    }
}
