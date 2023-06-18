package com.gest.GestioneTurno.Control;

import java.time.LocalDate;
import java.util.ArrayList;

import com.gest.Common.DBMSservice;
import com.gest.Entity.Impiegato;
import com.gest.Entity.Richiesta;
import com.gest.Entity.Turno;

public class GestioneAssenza {
    private ArrayList<LocalDate> date;
    private ArrayList<Turno> calendario;
    private ArrayList<Impiegato> listaImpiegati;
    private ArrayList<Turno> turniDaRisolvere;

    public GestioneAssenza(ArrayList<LocalDate> date, int matricola) {
        this.date = new ArrayList<>();
        this.calendario = new ArrayList<>();
        this.listaImpiegati = new ArrayList<>();
        this.turniDaRisolvere = new ArrayList<>();
        this.setListaImpiegati();
        this.setDate(date);
        this.setCalendario();
        this.setTurniDaRisolvere(matricola);
    }

    public GestioneAssenza(LocalDate dataInizio, LocalDate dataFine, int matricola) {
        this.date = new ArrayList<>();
        this.calendario = new ArrayList<>();
        this.listaImpiegati = new ArrayList<>();
        this.turniDaRisolvere = new ArrayList<>();
        this.setListaImpiegati();
        //System.out.println(this.getListaImpiegati().size());
        this.setDate(dataInizio, dataFine);
        //System.out.println(this.getDate().size());
        this.setCalendario();
        //System.out.println(this.getCalendario().size());
        this.setTurniDaRisolvere(matricola);
        //System.out.println(this.getTurniDaRisolvere());
    }

    public ArrayList<LocalDate> getDate() {
        return date;
    }

    public void setDate(ArrayList<LocalDate> date) {
        this.date = date;
    }

    public void setDate(LocalDate dataInizio, LocalDate dataFine) {
        ArrayList<LocalDate> dateIndisp = new ArrayList<>();
        LocalDate inizio = dataInizio;
        dateIndisp.add(inizio);
        while(inizio.compareTo(dataFine)!=0) {
            inizio = inizio.plusDays(1);
            dateIndisp.add(inizio);
        }
        this.setDate(dateIndisp);
    }
    
    public ArrayList<Turno> getCalendario() {
        return calendario;
    }

    public void setCalendario() {
        DBMSservice dbms = new DBMSservice();
        this.calendario = dbms.queryGetListaTurni();
    }

    public ArrayList<Impiegato> getListaImpiegati() {
        return listaImpiegati;
    }

    public void setListaImpiegati() {
        DBMSservice dbms = new DBMSservice();
        this.listaImpiegati = dbms.queryGetListaImpiegati();
    }

    public ArrayList<Turno> getTurniDaRisolvere() {
        return turniDaRisolvere;
    }

    public void setTurniDaRisolvere(int matricola) {
        for(Turno turno: this.getCalendario()) {
            if(turno.getRef_impiegato() == matricola && checkData(turno.getDataTurno()) == true)
                this.turniDaRisolvere.add(turno);
        }
    }

    //restituisce vero solo se la data passata come parametro rientra nelle date della richiesta
    private boolean checkData(LocalDate dataDaVerificare) {
        for(LocalDate data: this.getDate()) {
            if(data.equals(dataDaVerificare))
                return true;
        }
        return false;
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

    //sbagliato come trova gli impiegati liberi in un giorno data
    //IMPLEMENTARE LA RICERCA DEL MINIMO CON IL SORT
    public ArrayList<Impiegato> getListaImpiegatiLiberi(LocalDate data) {
        ArrayList<Impiegato> listaImp = new ArrayList<>();
        this.setListaImpiegati();
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


    public ArrayList<Turno> getCalendarioGiornaliero(LocalDate data) {
        //System.out.println("Calendario per il giorno: " + data);
        ArrayList<Turno> personalCalendar = new ArrayList<>();
        DBMSservice dbms = new DBMSservice();
        personalCalendar = dbms.queryGetListaTurni(data);
        return personalCalendar;
    }

    public ArrayList<Turno> getCalendarioGiornaliero(LocalDate data, int id_servizio) {
        System.out.println("Calendario per il giorno: " + data + "; per il servizio: " + id_servizio);
        ArrayList<Turno> personalCalendar = new ArrayList<>();
        DBMSservice dbms = new DBMSservice();
        personalCalendar = dbms.queryGetListaTurni(data, id_servizio);
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
        this.calendario.remove(turno);
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
        int index = this.calendario.indexOf(turnoProblematico);
        //this.riduciOre(turnoProblematico.getMatricola_impiegato());
        this.calendario.get(index).setRef_impiegato(nuovoTurno.getRef_impiegato());
        this.calendario.remove(nuovoTurno);
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
        int index = this.calendario.indexOf(turnoProblematico);
        System.out.println("Ho sostituto l'impiegato " + turnoProblematico.getRef_impiegato() + " con " + nuovaMatricola);
        this.calendario.get(index).setRef_impiegato(nuovaMatricola);
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

    public void aggiungiIndisponibilita(int matricola, LocalDate dataInizio, LocalDate dataFine) {
        DBMSservice dbms = new DBMSservice();
        ArrayList<LocalDate> dateIndisp = new ArrayList<>();
        LocalDate inizio = dataInizio;
        dateIndisp.add(inizio);
        while(inizio.compareTo(dataFine)!=0) {
            inizio = inizio.plusDays(1);
            dateIndisp.add(inizio);
        }
        for(LocalDate data: dateIndisp) {
            dbms.addIndisponibilita(matricola, data);
        }
    }

    public boolean aggiungiRichiestaCongedoLutto(String tipo, int matricola, String motivazione, String certificato, LocalDate dataInizio, LocalDate dataFine) {
        DBMSservice dbms = new DBMSservice();
        Richiesta richiesta = new Richiesta(matricola, "congedo lutto", motivazione, certificato, dataInizio, dataFine, true);
        this.aggiungiIndisponibilita(matricola, dataInizio, dataFine);
        System.out.println("Richiesta approvata");
        return dbms.addRichiesta(richiesta);
    }

    public boolean aggiungiRichiestaCongedoParentale(String tipo, int matricola, String motivazione, String certificato, LocalDate dataInizio, LocalDate dataFine) {
        DBMSservice dbms = new DBMSservice();
        Richiesta richiesta = new Richiesta(matricola, "congedo parentale", motivazione, certificato, dataInizio, dataFine, true);
        this.aggiungiIndisponibilita(matricola, dataInizio, dataFine);
        System.out.println("Richiesta approvata");
        return dbms.addRichiesta(richiesta);
    }

    public boolean aggiungiRichiestaMalattia(int matricola, String motivazione, String certificato, LocalDate dataInizio, LocalDate dataFine) {
       DBMSservice dbms = new DBMSservice();
        Richiesta richiesta = new Richiesta(matricola, "malattia", motivazione, certificato, dataInizio, dataFine, true);
        this.aggiungiIndisponibilita(matricola, dataInizio, dataFine);
        System.out.println("Richiesta approvata");
        return dbms.addRichiesta(richiesta); 
    }

    public boolean aggiungiRichiestaSciopero(int matricola, String motivazione, String certificato, LocalDate data) {
        DBMSservice dbms = new DBMSservice();
        Richiesta richiesta = new Richiesta(matricola, "sciopero", motivazione, certificato, data, true);
        this.aggiungiIndisponibilita(matricola, data, data);
        System.out.println("Richiesta approvata");
        return dbms.addRichiesta(richiesta);
    }
    
}
