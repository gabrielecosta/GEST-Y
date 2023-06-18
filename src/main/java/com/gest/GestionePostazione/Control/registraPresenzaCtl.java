package com.gest.GestionePostazione.Control;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import com.gest.Common.DBMSservice;
import com.gest.Entity.Turno;

public class registraPresenzaCtl {
    
    public registraPresenzaCtl() { }

    private LocalDate checkDate() {
        //String date = "2023-01-01";
        return LocalDate.now();
        //return LocalDate.parse(date);
    }

    private LocalTime checkTime() {
        //String time = "08:05";
        //String time = "16:10";
        return LocalTime.now();
        //return LocalTime.parse(time);
    }

    public boolean checkImpiegato(int matricola) {
        DBMSservice dbms = new DBMSservice();
        return dbms.queryExistsImpiegato(matricola);
    }
    
    public boolean checkImpiegato(int matricola, String nome, String cognome) {
        DBMSservice dbms = new DBMSservice();
        return dbms.queryExistsImpiegato(matricola,nome,cognome);
    }

    public Turno getTurno(int matricola) {
        //per ora gestisco io date e tempo, poi ci penseranno le classi checkTime e checkDate
        //LocalDate data = this.checkDate();
        LocalDate data = this.checkDate();
        LocalTime ora = this.checkTime();
        //System.out.println("Sono le " + ora + " del " + data );
        DBMSservice dbms = new DBMSservice();
        if(dbms.queryGetTurno(matricola, data).size()==0) {
            return null;
        } else {
            return dbms.queryGetTurno(matricola, data).get(0);
        }
    }

    public Turno getTurnoFirmato(int matricola) {
        //per ora gestisco io date e tempo, poi ci penseranno le classi checkTime e checkDate
        LocalDate data = this.checkDate();
        //LocalDate data = LocalDate.parse("2023-01-01");
        //LocalTime ora = LocalTime.parse("17:01");
        LocalTime ora = this.checkTime();
        //System.out.println("Sono le " + ora + " del " + data );
        DBMSservice dbms = new DBMSservice();
        if(dbms.queryGetTurnoFirmato(matricola, data).size()==0) {
            return null;
        } else {
            return dbms.queryGetTurnoFirmato(matricola, data).get(0);
        }
    }

    public boolean isPresente(Turno turno) {
        return Objects.isNull(turno);
    }

    public void firmaIngresso(Turno turno, int matricola) {
        if(Objects.isNull(turno)) {
            System.out.println("Impiegato non in turno");
        } else {
            System.out.println(turno.toString());
            if(turno.isFirmaIN()==false && turno.isFirmaOUT()==false) {
                //turno non ancora firmato, ne in ingresso ne in uscita
                //System.out.println("Ora rilevata: " + this.checkTime());
                //System.out.println("isAfter: " + this.checkTime().isAfter(turno.getOraInizio().minusMinutes(1)));
                //System.out.println("isBefore: " + this.checkTime().isBefore(turno.getOraInizio().plusMinutes(11)));
                if(this.checkTime().isAfter(turno.getOraInizio().minusMinutes(1)) && this.checkTime().isBefore(turno.getOraInizio().plusMinutes(11))) {
                    //può registrare la presenza
                    DBMSservice dbms = new DBMSservice();
                    dbms.queryFirmaIngresso(turno);
                    System.out.println("Presenza registrata in ingresso per il turno " + turno.getId_turno() + " in data " + turno.getDataTurno());
                } else if(this.checkTime().isBefore(turno.getOraInizio().minusMinutes(1))==true) {
                    //sono in anticipo
                    System.out.println("Sei in anticipo");
                    long minutes = ChronoUnit.MINUTES.between(turno.getOraInizio(),this.checkTime());
                    System.out.println("Puoi registrare la presenza dalla postazione in " + minutes + " minuti");
                } else if (this.checkTime().isAfter(turno.getOraInizio().plusMinutes(11))==true){
                    //sono in ritardo
                    System.out.println("Sei in ritardo. Puoi registrare la presenza da remoto");
                }
            } else if(turno.isFirmaOUT()==true) {
                System.out.println("Turno già firmato in uscita");
            } else if (turno.isFirmaIN()==true) {
                System.out.println("Turno già firmato in ingresso");
            } else if (turno.isFirmaIN()==true && turno.isFirmaOUT()==true) {
                System.out.println("Turno già firmato in ingresso ed uscita");
            }
        }
    }
    
    public boolean enableFirmaIngresso(Turno turno, int matricola) {
        if(Objects.isNull(turno)) {
            System.out.println("Impiegato non in turno");
            return false;
        } else {
            System.out.println(turno.toString());
            if(turno.isFirmaIN()==false && turno.isFirmaOUT()==false) {
                //turno non ancora firmato, ne in ingresso ne in uscita
                //System.out.println("Ora rilevata: " + this.checkTime());
                //System.out.println("isAfter: " + this.checkTime().isAfter(turno.getOraInizio().minusMinutes(1)));
                //System.out.println("isBefore: " + this.checkTime().isBefore(turno.getOraInizio().plusMinutes(11)));
                if(this.checkTime().isAfter(turno.getOraInizio().minusMinutes(1)) && this.checkTime().isBefore(turno.getOraInizio().plusMinutes(11))) {
                    //può registrare la presenza
                    //DBMSservice dbms = new DBMSservice();
                    //dbms.queryFirmaIngresso(turno);
                    System.out.println("Presenza registrata in ingresso per il turno " + turno.getId_turno() + " in data " + turno.getDataTurno());
                    return true;
                } else if(this.checkTime().isBefore(turno.getOraInizio().minusMinutes(1))==true) {
                    //sono in anticipo
                    System.out.println("Sei in anticipo");
                    long minutes = ChronoUnit.MINUTES.between(turno.getOraInizio(),this.checkTime());
                    System.out.println("Puoi registrare la presenza dalla postazione in " + minutes + " minuti");
                    return false;
                } else if (this.checkTime().isAfter(turno.getOraInizio().plusMinutes(11))==true){
                    //sono in ritardo
                    System.out.println("Sei in ritardo. Puoi registrare la presenza da remoto");
                    return false;
                }
            } else if(turno.isFirmaOUT()==true) {
                System.out.println("Turno già firmato in uscita");
                return false;
            } else if (turno.isFirmaIN()==true) {
                System.out.println("Turno già firmato in ingresso");
                return false;
            } else if (turno.isFirmaIN()==true && turno.isFirmaOUT()==true) {
                System.out.println("Turno già firmato in ingresso ed uscita");
                return false;
            }
        }
        return false;
    }

    public void firmaUscita(Turno turno, int matricola) {
        if(Objects.isNull(turno)) {
            System.out.println("Impiegato non in turno");
        } else {
            System.out.println(turno.toString());
            if(turno.isFirmaIN()==true && turno.isFirmaOUT()==false) {
                //turno firmato in entrata
                //System.out.println("Ora rilevata: " + this.checkTime());
                //System.out.println("isAfter: " + this.checkTime().isAfter(turno.getOraInizio().minusMinutes(1)));
                //System.out.println("isBefore: " + this.checkTime().isBefore(turno.getOraInizio().plusMinutes(11)));
                if(this.checkTime().isAfter(turno.getOraFine().minusMinutes(1)) && this.checkTime().isBefore(turno.getOraFine().plusMinutes(11))) {
                    //può registrare la presenza
                    DBMSservice dbms = new DBMSservice();
                    dbms.queryFirmaUscita(turno);
                    System.out.println("Presenza di uscita registrata per il turno " + turno.getId_turno() + " in data " + turno.getDataTurno());
                } else if(this.checkTime().isBefore(turno.getOraFine().minusMinutes(1))==true) {
                    //sono in anticipo
                    System.out.println("Sei in anticipo");
                    long minutes = ChronoUnit.MINUTES.between(turno.getOraFine(),this.checkTime());
                    System.out.println("Puoi registrare la presenza dalla postazione in " + minutes + " minuti");
                } else if (this.checkTime().isAfter(turno.getOraFine().plusMinutes(11))==true){
                    //sono in ritardo
                    System.out.println("Sei in ritardo");
                }
            } else if(turno.isFirmaOUT()==true) {
                System.out.println("Turno già firmato in uscita");
            } else if (turno.isFirmaIN()==true) {
                System.out.println("Turno già firmato in ingresso");
            } else if (turno.isFirmaIN()==true && turno.isFirmaOUT()==true) {
                System.out.println("Turno già firmato in ingresso ed uscita");
            }
        }
    }
    
    public boolean enableFirmaUscita(Turno turno, int matricola) {
        if(Objects.isNull(turno)) {
            System.out.println("Impiegato non in turno");
            return false;
        } else {
            System.out.println(turno.toString());
            if(turno.isFirmaIN()==true && turno.isFirmaOUT()==false) {
                //turno firmato in entrata
                //System.out.println("Ora rilevata: " + this.checkTime());
                //System.out.println("isAfter: " + this.checkTime().isAfter(turno.getOraInizio().minusMinutes(1)));
                //System.out.println("isBefore: " + this.checkTime().isBefore(turno.getOraInizio().plusMinutes(11)));
                if(this.checkTime().isAfter(turno.getOraFine().minusMinutes(1)) && this.checkTime().isBefore(turno.getOraFine().plusMinutes(11))) {
                    //può registrare la presenza
                    //DBMSservice dbms = new DBMSservice();
                    //dbms.queryFirmaUscita(turno);
                    System.out.println("Presenza di uscita registrata per il turno " + turno.getId_turno() + " in data " + turno.getDataTurno());
                    return true;
                } else if(this.checkTime().isBefore(turno.getOraFine().minusMinutes(1))==true) {
                    //sono in anticipo
                    System.out.println("Sei in anticipo");
                    long minutes = ChronoUnit.MINUTES.between(turno.getOraFine(),this.checkTime());
                    System.out.println("Puoi registrare la presenza dalla postazione in " + minutes + " minuti");
                    return false;
                } else if (this.checkTime().isAfter(turno.getOraFine().plusMinutes(11))==true){
                    //sono in ritardo
                    System.out.println("Sei in ritardo");
                    return false;
                }
            } else if(turno.isFirmaOUT()==true) {
                System.out.println("Turno già firmato in uscita");
                return false;
            } else if (turno.isFirmaIN()==true) {
                System.out.println("Turno già firmato in ingresso");
                return false;
            } else if (turno.isFirmaIN()==true && turno.isFirmaOUT()==true) {
                System.out.println("Turno già firmato in ingresso ed uscita");
                return false;
            }
        }
        return false;
    }
    
}
