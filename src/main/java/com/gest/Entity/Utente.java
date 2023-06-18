package com.gest.Entity;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.sql.SQLException;
import java.util.Date;

/*
CREATE TABLE utente (
  matricola int auto_increment NOT NULL,
  cf varchar(16) NOT NULL,
  nome varchar(75) NOT NULL,
  cognome varchar(75) NOT NULL,
  sesso varchar(1),
  email varchar(75) NOT NULL,
  passw varchar(30) NOT NULL,
  iban varchar(30) NOT NULL,
  telefono varchar(30) NOT NULL,
  via varchar(20),
  n_civ varchar(6),
  cap varchar(6),
  isAdmin boolean NOT NULL,
  isDDL boolean NOT NULL,
  ruolo int NOT NULL,
  newAssunzione boolean NOT NULL,
  isLicenziato boolean NOT NULL,
  dataAssunzione date,
  dataLicenziamento date,
  PRIMARY KEY (matricola)
);
 */
public class Utente {
    private int matricola;
    private String cf;
    private String nome;
    private String cognome;
    private String sesso;
    private String email;
    private String passw;
    private String iban;
    private String telefono;
    private String via;
    private String n_civ;
    private String cap;
    private Boolean isAdmin;
    private Boolean isDDL;
    private int ruolo;
    private Boolean newAssunzione;
    private Boolean isLicenziato;
    private LocalDate dataAssunzione;
    private LocalDate dataLicenziamento;

    public Utente() { }

    public Utente(int matricola, String cf, String nome, String cognome, String sesso, String email, String passw,
            String iban, String telefono, String via, String n_civ, String cap, Boolean isAdmin, Boolean isDDL, int ruolo,
            Boolean newAssunzione, Boolean isLicenziato, LocalDate dataAssunzione, LocalDate dataLicenziamento) {
                this.setMatricola(matricola);
                this.setCf(cf);
                this.setNome(nome);
                this.setCognome(cognome);
                this.setSesso(sesso);
                this.setEmail(email);
                this.setPassw(passw);
                this.setIban(iban);
                this.setTelefono(telefono);
                this.setVia(via);
                this.setN_civ(n_civ);
                this.setCap(cap);
                this.setIsAdmin(isAdmin);
                this.setIsDDL(isDDL);
                this.setRuolo(ruolo);
                this.setNewAssunzione(newAssunzione);
                this.setIsLicenziato(isLicenziato);
                this.setDataAssunzione(dataAssunzione);
                this.setDataLicenziamento(dataLicenziamento);
    }

    public Utente(String cf, String nome, String cognome, String sesso, String email, String passw,
            String iban, String telefono, String via, String n_civ, String cap, Boolean isAdmin, Boolean isDDL, int ruolo,
            Boolean newAssunzione, Boolean isLicenziato, LocalDate dataAssunzione, LocalDate dataLicenziamento) {
                //this.setMatricola(matricola);
                this.setCf(cf);
                this.setNome(nome);
                this.setCognome(cognome);
                this.setSesso(sesso);
                this.setEmail(email);
                this.setPassw(passw);
                this.setIban(iban);
                this.setTelefono(telefono);
                this.setVia(via);
                this.setN_civ(n_civ);
                this.setCap(cap);
                this.setIsAdmin(isAdmin);
                this.setIsDDL(isDDL);
                this.setRuolo(ruolo);
                this.setNewAssunzione(newAssunzione);
                this.setIsLicenziato(isLicenziato);
                this.setDataAssunzione(dataAssunzione);
                this.setDataLicenziamento(dataLicenziamento);
    }

    public int getMatricola() {
        return matricola;
    }

    public void setMatricola(int matricola) {
        this.matricola = matricola;
    }
    
    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }
    
    public String getN_civ() {
        return n_civ;
    }

    public void setN_civ(String n_civ) {
        this.n_civ = n_civ;
    }
    
    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }
    
    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Boolean getIsDDL() {
        return isDDL;
    }

    public void setIsDDL(Boolean isDDL) {
        this.isDDL = isDDL;
    }
    
    public int getRuolo() {
        return ruolo;
    }

    public void setRuolo(int ruolo) {
        this.ruolo = ruolo;
    }
    
    public Boolean getNewAssunzione() {
        return newAssunzione;
    }

    public void setNewAssunzione(Boolean newAssunzione) {
        this.newAssunzione = newAssunzione;
    }
    
    public Boolean getIsLicenziato() {
        return isLicenziato;
    }

    public void setIsLicenziato(Boolean isLicenziato) {
        this.isLicenziato = isLicenziato;
    }
    
    public LocalDate getDataAssunzione() {
        return dataAssunzione;
    }

    public void setDataAssunzione(LocalDate dataAssunzione) {
        this.dataAssunzione = dataAssunzione;
    }

    public LocalDate getDataLicenziamento() {
        return dataLicenziamento;
    }

    public void setDataLicenziamento(LocalDate dataLicenziamento) {
        this.dataLicenziamento = dataLicenziamento;
    }

    @Override
    //attualmente fa tornare solo matricola, cf, nome, cognome
    public String toString() {
        String ret;
        ret = "{ " + this.getMatricola() + ", " + this.getCf() + ", " + this.getNome() + ", " + this.getCognome() + " }";
        return ret;
    }

    public static Utente createFromDB(ResultSet row) throws SQLException {
        int matricola = row.getInt(1);
        String cf = row.getString(2);
        String nome = row.getString(3);
        String cognome = row.getString(4);
        String sesso = row.getString(5);
        String email = row.getString(6);
        String passw = row.getString(7);
        String iban = row.getString(8);
        String telefono = row.getString(9); 
        String via = row.getString(10);
        String n_civ = row.getString(11);
        String cap = row.getString(12); 
        Boolean isAdmin = row.getBoolean(13); 
        Boolean isDDL = row.getBoolean(14); 
        int ruolo = row.getInt(15);
        Boolean newAssunzione = row.getBoolean(16); 
        Boolean isLicenziato = row.getBoolean(17); 
        LocalDate dataAssunzione = convertToLocalDateViaSqlDate(row.getDate(18));
        LocalDate dataLicenziamento = convertToLocalDateViaSqlDate(row.getDate(19)) ;
        return new Utente(matricola, cf, nome, cognome, sesso, email, passw, iban, telefono, via, n_civ, cap, isAdmin, isDDL, ruolo, newAssunzione, isLicenziato, dataAssunzione, dataLicenziamento);
    }

    private static LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        if(dateToConvert == null) {
            return null;
        } else {
            return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
        }
    }

}

/*
 * public static Impiegato createFromDB(ResultSet row) throws SQLException {
        int matricola = row.getInt(1);
        String nome = row.getString(2);
        String cognome = row.getString(3);
        String email = row.getString(4);
        String passw = row.getString(5);
        int stipendio = row.getInt(6);
        return new Impiegato(matricola, nome, cognome, email, passw, stipendio);
    }
 */