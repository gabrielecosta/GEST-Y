package com.gest.GestioneTurno.Control;

import java.time.LocalDate;
import java.util.ArrayList;

public class TestRichiediAstensione {
    public static void main(String[] args) {
        RichiediAstensioneCTL richiediAstensione = new RichiediAstensioneCTL();

        //test richiesta sciopero
        String start = "2023-01-02";
        //LocalDate today = LocalDate.now();
        LocalDate day = LocalDate.parse(start);
        //if(richiediAstensione.richiediSciopero(20, "sciopero sindacato", "http://link",day)) {
        //    GestioneAssenza gestioneAssenzaCTL = new GestioneAssenza(day, day, 20);
        //    gestioneAssenzaCTL.aggiungiIndisponibilita(20, day, day);
        //    gestioneAssenzaCTL.aggiungiRichiestaSciopero(20, "sciopero sindacato","http://",day);
        //    gestioneAssenzaCTL.gestioneAssenza();
        //}
        //test richiesta ferie
        //richiediAstensione.richiediFerie(10, "ferie figa", "http://link",day, day.plusDays(2));
        //test richiesta malattia
        if(richiediAstensione.richiediMalattia(21, "malattia forzata", "http:Link", day, day.plusDays(2))) {
            GestioneAssenza gestioneAssenzaCTL = new GestioneAssenza(day, day.plusDays(2), 21);
            gestioneAssenzaCTL.aggiungiIndisponibilita(21, day, day.plusDays(2));
            gestioneAssenzaCTL.aggiungiRichiestaMalattia(21, "malattia broncopolominte", "hhttpss../",day,day.plusDays(2));
            gestioneAssenzaCTL.gestioneAssenza();
        }
        //test richiesta congedo
        //richiediAstensione.richiediCongedo("congedo parentale", 10, "malattia forzata", "http:Link", day, day);
    }
}
