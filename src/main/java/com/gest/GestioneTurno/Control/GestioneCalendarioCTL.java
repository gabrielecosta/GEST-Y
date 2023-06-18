package com.gest.GestioneTurno.Control;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import com.gest.Common.DBMSservice;
import com.gest.Entity.BustaPaga;
import com.gest.Entity.Impiegato;
import com.gest.Entity.Turno;

public class GestioneCalendarioCTL {
    private ArrayList<Turno> calendario;
    private ArrayList<Turno> nuovoCalendario;
    private ArrayList<Impiegato> listaImpiegati;
    private ArrayList<Turno> turniDaRisolvere;
//bambine venite a me

    public GestioneCalendarioCTL() {
        this.nuovoCalendario = new ArrayList<>();
        //lista impiegati la prendo dal DBMS
        this.listaImpiegati = new ArrayList<>();
        //il calendario lo prendo dal DB
        this.calendario = new ArrayList<>();
        this.setCalendario();
        this.setListaImpiegati();
        this.turniDaRisolvere = new ArrayList<>();
    }
//funzioe che serve a generare il calendario per le bimbe
    public void generaCalendario(LocalDate today) {
        ArrayList<Impiegato> ruolo1 = new ArrayList<>();
        ruolo1 = this.listaImpiegatiRuolo(1);
        int size1 = ruolo1.size();
        int indice1 = 0;
        ArrayList<Impiegato> ruolo2 = new ArrayList<>();
        ruolo2 = this.listaImpiegatiRuolo(2);
        int size2 = ruolo2.size();
        int indice2 = 0;
        ArrayList<Impiegato> ruolo3 = new ArrayList<>();
        ruolo3 = this.listaImpiegatiRuolo(3);
        int size3 = ruolo3.size();
        int indice3 = 0;
        ArrayList<Impiegato> ruolo4 = new ArrayList<>();
        ruolo4 = this.listaImpiegatiRuolo(4);
        int size4 = ruolo4.size();
        int indice4 = 0;
        //MEMO: I for sono malefici
        for(int i=0; i<90; i++) {
            LocalDate data = today.plusDays(i);
            LocalTime inizio = LocalTime.parse("08:00");
            LocalTime fine = LocalTime.parse("16:00");
            this.aggiungiTurno(data, inizio, fine, ruolo1, indice1, size1, 1);
            indice1++;
            this.aggiungiTurno(data, inizio, fine, ruolo1, indice1, size1, 1);
            indice1++;
            this.aggiungiTurno(data, inizio, fine, ruolo1, indice1, size1, 1);
            indice1++;
            //aumenta l'ora di inzio e l'ora di fine
            inizio = inizio.plus(1, ChronoUnit.HOURS);
            fine = fine.plus(1,ChronoUnit.HOURS);
            this.aggiungiTurno(data, inizio, fine, ruolo2, indice2, size2, 2);
            indice2++;
            this.aggiungiTurno(data, inizio, fine, ruolo2, indice2, size2, 2);
            indice2++;
            this.aggiungiTurno(data, inizio, fine, ruolo2, indice2, size2, 2);
            indice2++;
            //aumenta l'ora di inzio e l'ora di fine
            inizio = inizio.plus(1, ChronoUnit.HOURS);
            fine = fine.plus(1,ChronoUnit.HOURS);
            this.aggiungiTurno(data, inizio, fine, ruolo3, indice3, size3, 3);
            indice3++;
            this.aggiungiTurno(data, inizio, fine, ruolo3, indice3, size3, 3);
            indice3++;
            this.aggiungiTurno(data, inizio, fine, ruolo3, indice3, size3, 3);
            indice3++;
            //aumenta l'ora di inzio e l'ora di fine
            inizio = inizio.plus(1, ChronoUnit.HOURS);
            fine = fine.plus(1,ChronoUnit.HOURS);
            this.aggiungiTurno(data, inizio, fine, ruolo4, indice4, size4, 4);
            indice4++;
            this.aggiungiTurno(data, inizio, fine, ruolo4, indice4, size4, 4);
            indice4++;
            this.aggiungiTurno(data, inizio, fine, ruolo4, indice4, size4, 4);
            indice4++;
            //aumenta l'ora di inzio e l'ora di fine
            inizio = inizio.plus(1, ChronoUnit.HOURS);
            fine = fine.plus(1,ChronoUnit.HOURS);
            this.aggiungiTurno(data, inizio, fine, ruolo1, indice1, size1, 1);
            indice1++;
            this.aggiungiTurno(data, inizio, fine, ruolo1, indice1, size1, 1);
            indice1++;
            this.aggiungiTurno(data, inizio, fine, ruolo1, indice1, size1, 1);
            indice1++;
        }
        this.gestioneAssenza();
        System.out.println("Calendario creato");
    }

    public void aggiungiTurno(LocalDate data, LocalTime inizio, LocalTime fine, ArrayList<Impiegato> ruolo, int indice, int size, int id_servizio) {
        Turno turno = new Turno(ruolo.get(indice % size).getMatricola(), id_servizio, data, inizio, fine);
        this.nuovoCalendario.add(turno);
        if(!ruolo.get(indice % size).checkDisp(data)) {
            this.turniDaRisolvere.add(turno);
        } else {
            //ruolo.get(indice % size).setOreSvolte(8);
        }
    }

    public void inserisciCalendario() {
        for(Turno turno: this.nuovoCalendario)
            this.inserisciTurno(turno);
        System.out.println("Calendario inserito correttamente");
    }
    
    public void inserisciTurno(Turno turno) {
        DBMSservice dbms = new DBMSservice();
        dbms.queryAddTurno(turno);
    }

    public void generaBustePaga(LocalDate today) {
        DBMSservice dbms = new DBMSservice();
        for(Impiegato imp: this.getListaImpiegati()) {
            for(int i=0; i<3; i++) {
                int month = today.plusMonths(i).getMonthValue();
                int year = today.getYear();
                BustaPaga bustaPaga = new BustaPaga(imp, month , year);
                dbms.addBustaPaga(bustaPaga);
                if(month == 12) {
                    //inserisco tredicesima
                    BustaPaga tredicesima = new BustaPaga(imp, month+1, year);
                    dbms.addBustaPaga(tredicesima);
                }
            }
        }
        System.out.println("Buste paga create");  
    }

    private ArrayList<Impiegato> listaImpiegatiRuolo(int ruolo) {
        ArrayList<Impiegato> lista = new ArrayList<>();
        for(Impiegato imp: this.listaImpiegati) {
            if(imp.getRuolo() == ruolo)
                lista.add(imp);
        }
        return lista;
    }

    public void gestioneAssenza() {
        ArrayList<Impiegato> impLiberi = new ArrayList<>();
        //for(Turno turno: this.getTurniDaRisolvere()) {
        while(!this.getTurniDaRisolvere().isEmpty()) {
            int size = this.getTurniDaRisolvere().size();
            Turno turno = this.getTurniDaRisolvere().get(size-1);
            impLiberi = this.getListaImpiegatiLiberi(turno.getDataTurno());
            if(!impLiberi.isEmpty()) {
                //impiegato libero, sostituisco con chi ha fatto meno ore
                //al momento sostituisce con il primo disponibile
                this.sostuisciImpiegato(turno,impLiberi.get(0).getMatricola());
            } else { //non ci sono impiegati liberi
                //verifico ora che il servizio può essere comunque garantito per quella data
                if (this.verifyServizio(turno.getDataTurno(), turno.getRef_servizio())) {
                    //il servizio può essere garantito
                    //rimuovi il turno dell'impiegato
                    this.removeTurno(turno);
                } else {
                    //il servizio non può essere garantito
                    if(turno.getRef_servizio() > 1) {
                        //servizio a priorità minima, può essere chiuso il turno
                        this.removeTurno(turno);
                        //inserisci comunicazione chiusura servizio
                        this.stampComunicazioneChiusuraServizio(turno);
                        //VIVA LA CINA
                    } else {
                        //servizio a priorità massima, non posso chiudere il turno
                        ArrayList<Turno> listaTurniGiorno = new ArrayList<>();
                        listaTurniGiorno = this.getTurniDisponibili(turno);
                        if(!listaTurniGiorno.isEmpty()) {
                            //sostituisco con un impiegato in turno in quella giornata a partire da un impiegato di ruolo4
                            //dunque faccio in modo eventualmente di chiudere un servizio a priorità minima tra quelli aperti quel giorno
                            int sizeLista = listaTurniGiorno.size();
                            this.sostuisciTurno(turno, listaTurniGiorno.get(sizeLista-1));
                        } else {
                            //non ho trovato sostituti neanche in quella giornata, caso estremo!
                            //l'azienda in quella giornata non lavora, pazienza tanto paga l'INPS
                            this.removeTurno(turno);
                        }
                        
                    }
                }//CALLARI 6 mia <3

            }
            this.getTurniDaRisolvere().remove(size-1);
        }
        
    }

    public ArrayList<Impiegato> getListaImpiegati() {
        return this.listaImpiegati;
    }

    public void setListaImpiegati() {
        DBMSservice dbms = new DBMSservice();
        this.listaImpiegati = dbms.queryGetListaImpiegati();
    }

    //sbagliato come trova gli impiegati liberi in un giorno data
    //IMPLEMENTARE LA RICERCA DEL MINIMO CON IL SORT
    public ArrayList<Impiegato> getListaImpiegatiLiberi(LocalDate data) {
        ArrayList<Impiegato> listaImp = new ArrayList<>();
        for(Impiegato imp: this.getListaImpiegati()) {
            if( this.checkLavora(imp.getMatricola(), data)==false && imp.checkDisp(data) )
                listaImp.add(imp);
        }
        return listaImp;
    }

    public boolean checkLavora(int matricola, LocalDate data) {
        boolean returnValue = false;
        for(Turno turno: this.getCalendarioGiornaliero(data)) {
            if(turno.getRef_impiegato() == matricola)
                returnValue = true;
        }
        return returnValue;
    }
    //questo metodo ritorna VERO se l'impiegato lavora in quel giorno, altrimenti falso

    
    //poi questa sarà una query
    /*
     * public void riduciOre(int matricola) {
        for(Impiegato imp: this.getListaImpiegati()) {
            if(imp.getMatricola()==matricola) {
                imp.setOreSvolte(-8);
            }
        }
    }
     */
    /*
    public void aggiungiOre(int matricola) {
        for(Impiegato imp: this.getListaImpiegati()) {
            if(imp.getMatricola()==matricola) {
                imp.setOreSvolte(8);
            }
        }
    }
    */

    /* diventa una query in SQL  
    public ArrayList<Turno> getListaTurniFiltrata(LocalDate inizio, LocalDate fine) {
        ArrayList<Turno> turniFiltrati = new ArrayList<>();
        for(Turno turno: this.getCalendario()) {
            if(turno.getDataTurno().isAfter(inizio) && turno.getDataTurno().isBefore(fine))
                turniFiltrati.add(turno);
        }
        return turniFiltrati;
    }
    */

    //prende i turni da query in seguito
    public void stampaCalendario() {
        for(Turno turno: this.getCalendario()) {
            System.out.println(turno.toString());
        }
    }

    public ArrayList<Turno> getCalendario() {
        return calendario;
    }

    public void setCalendario() {
        DBMSservice dbms = new DBMSservice();
        this.calendario = dbms.queryGetListaTurni();
    }

    public ArrayList<Turno> getTurniDaRisolvere() {
        return turniDaRisolvere;
    }

    public void setTurniDaRisolvere(ArrayList<Turno> turniDaRisolvere) {
        this.turniDaRisolvere = turniDaRisolvere;
    }

    //diventa una query
    /*
    public void getPersonalCalendario(int matricola) {
        System.out.println("Calendario per la matricola: " + matricola);
        ArrayList<Turno> personalCalendar = new ArrayList<>();
        for(Turno turno: this.getCalendario()) {
            if(turno.getRef_impiegato() == matricola) {
                personalCalendar.add(turno);
            }
        }
        for(Turno turno: personalCalendar) {
            System.out.println(turno.toString());
        }
    }
     */
    public ArrayList<Turno> getPersonalCalendario(int matricola) {
        System.out.println("Calendario per la matricola: " + matricola);
        ArrayList<Turno> personalCalendar = new ArrayList<>();
        DBMSservice dbms = new DBMSservice();
        personalCalendar = dbms.queryGetListaTurni(matricola);
        return personalCalendar;
    }

    //diventa una query
    /* 
    public ArrayList<Turno> getCalendarioGiornaliero(LocalDate data) {
        //System.out.println("Calendario per il giorno: " + data);
        ArrayList<Turno> personalCalendar = new ArrayList<>();
        for(Turno turno: this.getCalendario()) {
            if(turno.getDataTurno().equals(data)) {
                personalCalendar.add(turno);
            }
        }
        return personalCalendar;
    }
    */
    public ArrayList<Turno> getCalendarioGiornaliero(LocalDate data) {
        System.out.println("Calendario per il giorno: " + data);
        ArrayList<Turno> personalCalendar = new ArrayList<>();
        DBMSservice dbms = new DBMSservice();
        personalCalendar = dbms.queryGetListaTurni(data);
        return personalCalendar;
    }

    public ArrayList<Turno> getTurniDisponibili(Turno turnoProblematico) {
        ArrayList<Turno> lista = new ArrayList<>();
        for(Turno turno: this.getCalendarioGiornaliero(turnoProblematico.getDataTurno())) {
            if( turno.getRef_impiegato() != turnoProblematico.getRef_impiegato()) {
                if(turno.getOraInizio().isAfter(turnoProblematico.getOraInizio()))
                    lista.add(turno);
            }
        }
        return lista;
    }

    //diventa una query
    /*
    public ArrayList<Turno> getCalendarioGiornaliero(LocalDate data, int id_servizio) {
        ArrayList<Turno> personalCalendar = new ArrayList<>();
        for(Turno turno: this.getCalendario()) {
            if(turno.getDataTurno().equals(data) && turno.getRef_servizio()==id_servizio) {
                personalCalendar.add(turno);
            }
        }
        return personalCalendar;
    }
     */
    public ArrayList<Turno> getCalendarioGiornaliero(LocalDate data, int id_servizio) {
        System.out.println("Calendario per il giorno: " + data + "; per il servizio: " + id_servizio);
        ArrayList<Turno> personalCalendar = new ArrayList<>();
        DBMSservice dbms = new DBMSservice();
        personalCalendar = dbms.queryGetListaTurni(data, id_servizio);
        return personalCalendar;
    }

    public boolean verifyServizio(LocalDate data, int id_servizio) {
        //controllo se il servizio possa essere garantito: almeno 1 impiegato per servizio 2,3,4 e 3 per servizio 1
        boolean returnValue = true;
        ArrayList<Turno> lista = new ArrayList<>();
        lista = this.getCalendarioGiornaliero(data, id_servizio);
        if(id_servizio == 1) {
            // servizio livello 1
            if (lista.size() <= 3)
                returnValue = false;
        } else {
            if (lista.size() == 1)
                returnValue = false;
        }
        return returnValue;
    }
    //ritorna vero se il servizio può essere garantito anche senza impiegato, altrimenti falso
    
    public void removeTurno(Turno turno) {
        System.out.println("Ho rimosso il turno: " + turno.toString());
        //rimuovi turno, togliendo le ore all'impiegato e rimuovendo
        //il turno dalla lista del calendario e dai turni da risolvere
        this.nuovoCalendario.remove(turno);
        //imposta il turno a isClosed=true nel DB e inserisce comunicazione di variazione turno per l'impiegato
        DBMSservice dbms = new DBMSservice();
        dbms.queryRimuoviTurno(turno);
        String descrizione = "Chiusura turno " + turno.getId_turno() + " della data " + turno.getDataTurno() + " .";
        dbms.addNotificaVariazione(turno, descrizione);
        //this.riduciOre(turno.getRef_impiegato());
    }

    public void sostuisciTurno(Turno turnoProblematico, Turno nuovoTurno) {
        //sostituisco l'impiegato del nuovoTurno nel TurnoProblematico
        //rimuovo il nuovoTurno dato che ho sostituito
        System.out.println("Ho sostituito la matricola del turno (nuovo)" + nuovoTurno.toString() + "nel turno (problematico)" + turnoProblematico.toString());
        int index = this.nuovoCalendario.indexOf(turnoProblematico);
        //this.riduciOre(turnoProblematico.getMatricola_impiegato());
        this.nuovoCalendario.get(index).setRef_impiegato(nuovoTurno.getRef_impiegato());
        this.nuovoCalendario.remove(nuovoTurno);
        //rimozione nuovoTurno dal DB:
        DBMSservice dbms = new DBMSservice();
        dbms.queryRimuoviTurno(nuovoTurno);
        dbms.queryUpdateTurno(turnoProblematico, nuovoTurno.getRef_impiegato());
        String descrizione = "Chiusura turno " + nuovoTurno.getId_turno() + " della data " + nuovoTurno.getDataTurno() + " .";
        dbms.addNotificaVariazione(nuovoTurno, descrizione);
        descrizione = "Sostituzione turno " + turnoProblematico.getId_turno() + " della data " + turnoProblematico.getDataTurno() + " .";
        dbms.addNotificaVariazione(nuovoTurno, descrizione);
    }

    public void sostuisciImpiegato(Turno turnoProblematico, int nuovaMatricola) {
        //sostituisco l'impiegato del nuovoTurno nel TurnoProblematico
        int index = this.nuovoCalendario.indexOf(turnoProblematico);
        System.out.println("Ho sostituto l'impiegato " + turnoProblematico.getRef_impiegato() + " con " + nuovaMatricola);
        this.nuovoCalendario.get(index).setRef_impiegato(nuovaMatricola);
        //this.riduciOre(turnoProblematico.getRef_impiegato());
        //this.aggiungiOre(nuovaMatricola);
        DBMSservice dbms = new DBMSservice();
        dbms.queryUpdateTurno(turnoProblematico, nuovaMatricola);
        String descrizione = "Sostituzione turno " + turnoProblematico.getId_turno() + " della data " + turnoProblematico.getDataTurno() + " .";
        dbms.addNotificaVariazione(nuovaMatricola, descrizione);
        //aggiungo 8 ore di straordinario nello straordinario
        dbms.updateOreStraordinario(turnoProblematico, nuovaMatricola);

    }

    public void stampComunicazioneChiusuraServizio(Turno turno) {
        String descrizione = "Chiuso il servizio " + turno.getRef_servizio() + " nel giorno " + turno.getDataTurno();
        System.out.println(descrizione);
        DBMSservice dbms = new DBMSservice();
        dbms.addNotificaChiusuraServizio(descrizione); //destinata al datore di lavoro
    }


    public boolean richiediROL(Turno turno, int tipo, long ore_richieste) {
        long hours = ChronoUnit.HOURS.between(turno.getOraInizio(), turno.getOraFine());
        if(hours > 6) {
        	if(tipo == 0) {
        		//entrata posticipata
        		turno.setOraInizio(turno.getOraInizio().plusHours(ore_richieste));
        	} else {
        		//uscita anticipata
        		turno.setOraFine(turno.getOraFine().minusHours(ore_richieste));
        	}
            DBMSservice dbms = new DBMSservice();
            dbms.queryUpdateOrario(turno, turno.getOraInizio(), turno.getOraFine());
            return true;
        } else {
            System.out.println("Impossibile effettuare la richiesta");
            return false;
        }
    }

    public ArrayList<Turno> getListaTurni(int matricola) {
        LocalDate today = LocalDate.now();
        DBMSservice dbms = new DBMSservice();
        return dbms.queryGetListaTurni(matricola, today);
    }
}
