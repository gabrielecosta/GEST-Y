package com.gest.GestionePostazione.Control;
import com.gest.Common.DBMSservice;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import com.gest.Common.DBMSservice;
import com.gest.Entity.BustaPaga;
import com.gest.Entity.Impiegato;
import com.gest.Entity.Turno;

public class CalcoloSalarioCtl {
    private int giornoPaga = 26;
    private int giornoCalcolo = 24;
    private ArrayList<BustaPaga> listaBustePaga;
    private ArrayList<Turno> listaTurniImp;

    public ArrayList<Turno> getListaTurni() {
        return listaTurniImp;
    }

    public void setListaTurni(int matricola) {
        DBMSservice dbms = new DBMSservice();
        for(Turno turno: dbms.queryGetListaTurni(matricola)) {
            if(turno.getDataTurno().getMonthValue() == this.checkMese().getMonthValue() && turno.getDataTurno().getYear() == this.checkMese().getYear())
                this.listaTurniImp.add(turno);
        }
    }

    public CalcoloSalarioCtl() {
        this.listaBustePaga = new ArrayList<>();
        this.listaTurniImp = new ArrayList<>();
     }

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
        DBMSservice dbms = new DBMSservice();
        return dbms.queryGetBustaPaga(matricola, this.checkMese().getMonthValue(), this.checkMese().getYear());
    }

    public void effettuaCalcolo() {
        DBMSservice dbms = new DBMSservice();
        if(this.checkGiornoCalcolo()) {
            for(Impiegato imp: this.getListaImpiegati()) {
                this.setListaTurni(imp.getMatricola());
                int paga_oraria = 3;
                int paga_straordinaria = 5;
                int gratifica_1 = 20;
                int gratifica_2 = 15;
                int gratifica_3 = 10;
                int gratifica_4 = 5;
                BustaPaga bustaPaga = new BustaPaga();
                bustaPaga = this.getBustaPaga(imp.getMatricola());
                int stipendio_base = bustaPaga.getStipendio_base();
                int ore_servizio_1 = this.contaOre(1);
                bustaPaga.setOre_servizio_1(ore_servizio_1);
                int ore_servizio_2 = this.contaOre(2);
                bustaPaga.setOre_servizio_2(ore_servizio_2);
                int ore_servizio_3 = this.contaOre(3);
                bustaPaga.setOre_servizio_3(ore_servizio_3);
                int ore_servizio_4 = this.contaOre(4);
                bustaPaga.setOre_servizio_4(ore_servizio_4);
                int ore_straodinario = bustaPaga.getOre_straodinario();
                int num_ritardi = bustaPaga.getNum_ritardi();
                int num_assenze = bustaPaga.getNum_assenze();
                int malus = calcolaMalus(num_ritardi, num_assenze);
                int totale = stipendio_base + calcoloOreGratifica(ore_servizio_1,gratifica_1,paga_oraria) + calcoloOreGratifica(ore_servizio_2,gratifica_2,paga_oraria) + calcoloOreGratifica(ore_servizio_3,gratifica_3,paga_oraria)
                + calcoloOreGratifica(ore_servizio_4,gratifica_4,paga_oraria) + (ore_straodinario*paga_straordinaria);
                totale = totale - (totale/100)*malus;
                bustaPaga.setImportoTotale(totale);
                //System.out.println("busta paga" + bustaPaga);
                dbms.queryUpdateBustaPaga(bustaPaga, totale);
                this.listaTurniImp.clear();
            }
            //fine calcolo, inserimento della comunicazione
            this.inserisciComunicazione();
        } else {
            System.out.println("Non è ancora giorno di calcolo");
        }
    }

    public void inserisciComunicazione() {
        DBMSservice dbms = new DBMSservice();
        dbms.addNotificaCalcoloSalario();
    }

    private int contaOre(int servizio) {
        int count = 0;
        for(Turno turno: this.getListaTurni()) {
            if(turno.getRef_servizio()==servizio)
                count = count + (int) abs(ChronoUnit.HOURS.between(turno.getOraFine(), turno.getOraInizio()));
        }
        return count;
    }

    private int calcoloOreGratifica(int num_ore, int gratifica, int paga) {
        int totale = num_ore * paga;
        totale = totale + (totale/100)*gratifica;
        return totale;
    }

    private int calcolaMalus(int ritardi, int assenze) {
        //ogni 4 ritardi in ingresso malus al 2% della busta paga
        int variabile = 0;
        if(ritardi > 3) {
            variabile = ritardi / 4;
        }
        int malus_ritardi = 2 * variabile;
        //ogni assenza riduzione del 5% della busta paga
        int malus_assenze = 5 * assenze;
        return malus_ritardi + malus_assenze;
    }
    
    private long abs(long number) {
        if (number < 0)
            number = -number;
        return number;
    }

}
