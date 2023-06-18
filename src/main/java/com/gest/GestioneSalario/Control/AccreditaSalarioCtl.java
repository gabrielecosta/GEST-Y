package com.gest.GestioneSalario.Control;

import java.time.LocalDate;
import java.util.ArrayList;

import com.gest.Common.DBMSservice;
import com.gest.Entity.BustaPaga;
import com.gest.Entity.Impiegato;
import com.gest.Common.SendEmail;

public class AccreditaSalarioCtl {
    private int giornoPaga = 26;
    private int giornoCalcolo = 24;
    private ArrayList<BustaPaga> listaBustePaga;

    public AccreditaSalarioCtl() { }

    private LocalDate checkMese() {
       return LocalDate.now().minusMonths(1);
       //return LocalDate.now();
    }

    //serve per vedere se è giorno di paga
    public boolean checkGiornoPaga() {
        int giorno = LocalDate.now().getDayOfMonth();
        return giorno == giornoPaga;
    }

    //serve per vedere se è giorno di calcolo
    public boolean checkGiornoCalcolo() {
        int giorno = LocalDate.now().getDayOfMonth();
        return giorno == giornoCalcolo;
    }

    public ArrayList<BustaPaga> getBustePagaDaPagare() {
        DBMSservice dbms = new DBMSservice();
        return dbms.queryGetListaBugaDaPagare(this.checkMese().getMonthValue(), this.checkMese().getYear());
    }

    private ArrayList<Impiegato> getListaImpiegati() {
        DBMSservice dbms = new DBMSservice();
        return dbms.queryGetListaImpiegati();
    }

    private BustaPaga getBustaPaga(int matricola) {
        for(BustaPaga bustaPaga: this.listaBustePaga) {
            if(bustaPaga.getRef_impiegato() == matricola)
                return bustaPaga;
        }
        return null;
    }

    public void accreditaSalario() {
        DBMSservice dbms = new DBMSservice();
        if(this.checkGiornoPaga()) {
            this.listaBustePaga = dbms.queryGetListaBugaDaPagare(this.checkMese().getMonthValue(), this.checkMese().getYear());
            for(Impiegato imp: this.getListaImpiegati()) {
                BustaPaga bustaPaga = new BustaPaga();
                bustaPaga = this.getBustaPaga(imp.getMatricola());
                //invio email pagamento effettuato
                this.sendEmailPagamento(imp.getEmail(), "Pagamento Busta Paga", imp.getIban());
                //aggiorna busta paga con pagato
                dbms.queryUpdateBustaPaga(bustaPaga);
            }
        } else {
            System.out.println("Non è giorno di paga ai cinesi");
        }
    }

    public void sendEmailPagamento(String email, String oggetto, String IBAN) {
        String descrizione = "Abbiamo effettuato il pagamento al seguente IBAN: " + IBAN;
        System.out.println("Email a: " + email);
        System.out.println("Oggetto: " + oggetto);
        System.out.println("Testo: " + descrizione);
        //invio email
        SendEmail sender = new SendEmail();
        sender.sendEmailGenerica(email, oggetto, descrizione);
    }
    
}
