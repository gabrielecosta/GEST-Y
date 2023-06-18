package com.gest.Common;
import java.sql.*;
import java.util.ArrayList;

import com.gest.Entity.BustaPaga;
import com.gest.Entity.Impiegato;
import com.gest.Entity.Notifica;
import com.gest.Entity.Richiesta;
import com.gest.Entity.Servizio;
import com.gest.Entity.Turno;
import com.gest.Entity.Turno_ritardo;
import com.gest.Entity.Utente;
import com.gest.Entity.Utente_Notifica;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;


public class DBMSservice {
    private static final String baseUrl = "151.52.206.26";
    private static final int port = 49184;
    private static final String user = "root";
    private static final String pass = "suca";
    private static final String DBAzienda = "azienda_pa";
    private static Connection connAzienda = null;

    public DBMSservice() {
        DBMSservice.connectAzienda();
    }

    private static void erroreComunicazioneDBMS(Exception e) {
        final String errore = "Errore durante comunicazione con DBMS" + e;
        System.out.println(errore);
        ModuloErroreGenerico modulo = new ModuloErroreGenerico();
        modulo.setSize(600,400);
		modulo.setVisible(true);
    }

    /**
     * @hidden
     */
    private static String buildConnectionUrl(String dbName) {
        if (dbName.equals(DBAzienda))
            return "jdbc:mysql://" + baseUrl + ":" + port + "/" + dbName + "?user=" + user + "&password=" + pass;
        return "";
    }

    public static void connectAzienda() {
        try {
            if (connAzienda == null || connAzienda.isClosed()) {
                System.out.println("Connettendo con Azienda...");
                DBMSservice.connAzienda = DriverManager.getConnection(buildConnectionUrl(DBAzienda));
                System.out.println("Connesso con Azienda");
            }
        } catch (java.sql.SQLException e) {
            erroreComunicazioneDBMS(e);
        }
    }

    public void getStatus() {
        if (connAzienda != null) {
            System.out.println("connesso....");
        }
    }


    public int getMatricola(String cf) {
        connectAzienda();
        String query = "SELECT * FROM utente WHERE cf=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setString(1, cf);
            stmt.setMaxRows(1); 
            ResultSet rs = stmt.executeQuery();
            int matricola = 0;
            while(rs.next()) {
                matricola = rs.getInt(1);
            }
            return matricola;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return -1;
    }
   
    public static boolean addUtente (Utente utente) {
        connectAzienda();
        String query = "INSERT INTO utente(cf, nome, cognome, sesso, email, passw, iban, telefono, via, n_civ, cap, isAdmin, isDDL, ruolo, newAssunzione, isLicenziato, dataAssunzione, dataLicenziamento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setString(1, utente.getCf());
            stmt.setString(2, utente.getNome());
            stmt.setString(3, utente.getCognome());
            stmt.setString(4, utente.getSesso());
            stmt.setString(5, utente.getEmail());
            stmt.setString(6, utente.getPassw());
            stmt.setString(7, utente.getIban());
            stmt.setString(8, utente.getTelefono());
            stmt.setString(9, utente.getVia());
            stmt.setString(10, utente.getN_civ());
            stmt.setString(11, utente.getCap());
            stmt.setBoolean(12, utente.getIsAdmin());
            stmt.setBoolean(13, utente.getIsDDL());
            stmt.setInt(14, utente.getRuolo());
            stmt.setBoolean(15, utente.getNewAssunzione());
            stmt.setBoolean(16, utente.getIsLicenziato());
            stmt.setDate(17, convertToDateViaSqlDate(utente.getDataAssunzione()));
            stmt.setDate(18, convertToDateViaSqlDate(utente.getDataLicenziamento()));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    private static Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        if(dateToConvert == null) {
            return null;
        } else {
            return java.sql.Date.valueOf(dateToConvert);
        }
    }

    private static LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        if(dateToConvert == null) {
            return null;
        } else {
            return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
        }
    }

    public static boolean addServizio (Servizio servizio) {
        connectAzienda();
        String query = "INSERT INTO servizio(id_servizio, nome, gratifica) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, servizio.getId_servizio());
            stmt.setString(2, servizio.getNome());
            stmt.setInt(3, servizio.getGratifica());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    public static boolean addNotifica (Notifica notifica) {
        connectAzienda();
        String query = "INSERT INTO notifica(id_notifica, tipologia) VALUES (?, ?)";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, notifica.getId_notifica());
            stmt.setString(2, notifica.getTipologia());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    public static boolean addIndisponibilita (int matricola, LocalDate data) {
        connectAzienda();
        String query = "INSERT INTO indisponibilita(ref_imp, dataIndisp) VALUES (?, ?)";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, matricola);
            stmt.setDate(2, convertToDateViaSqlDate(data));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    public static boolean queryAddTurno(Turno turno) {
        connectAzienda();
        String query = "INSERT INTO turno(ref_impiegato, ref_servizio, dataTurno, oraInizio, oraFine, firmaIngresso, firmaUscita, isClosed) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, turno.getRef_impiegato());
            stmt.setInt(2, turno.getRef_servizio());
            stmt.setDate(3, convertToDateViaSqlDate(turno.getDataTurno()));
            stmt.setTime(4, toSQLTime(turno.getOraInizio()));
            stmt.setTime(5, toSQLTime(turno.getOraFine()));
            stmt.setBoolean(6, false);
            stmt.setBoolean(7, false);
            stmt.setBoolean(8, false);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    public static LocalTime toLocalTime(java.sql.Time time) {
        return time.toLocalTime();
    }

    public static Time toSQLTime(LocalTime timeLocal) {
        return Time.valueOf(timeLocal);
    }

    public static boolean addNotificaCalcoloSalario () {
        connectAzienda();
        String query = "INSERT INTO utente_notifica(ref_notifica, ref_impiegato, dataNotifica, descrizione) VALUES (?,?,?,?)";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, 6);
            stmt.setInt(2,6);
            stmt.setDate(3, convertToDateViaSqlDate(LocalDate.now()));
            stmt.setString(4, "calcolo salario effettuato. Attendere il 26 del mese corrente per accreditare");
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    public static boolean addNotificaVariazione (Turno turno, String descrizione) {
        connectAzienda();
        String query = "INSERT INTO utente_notifica(ref_notifica, ref_impiegato, dataNotifica, descrizione) VALUES (?,?,?,?)";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, 5);
            stmt.setInt(2,turno.getRef_impiegato());
            stmt.setDate(3, convertToDateViaSqlDate(LocalDate.now()));
            stmt.setString(4, descrizione);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    public static boolean addNotificaVariazione (int matricola, String descrizione) {
        connectAzienda();
        String query = "INSERT INTO utente_notifica(ref_notifica, ref_impiegato, dataNotifica, descrizione) VALUES (?,?,?,?)";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, 5);
            stmt.setInt(2, matricola);
            stmt.setDate(3, convertToDateViaSqlDate(LocalDate.now()));
            stmt.setString(4, descrizione);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    public static boolean addNotificaChiusuraServizio(String descrizione) {
        connectAzienda();
        String query = "INSERT INTO utente_notifica(ref_notifica, ref_impiegato, dataNotifica, descrizione) VALUES (?,?,?,?)";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, 7); 
            stmt.setInt(2, 6); //per il datore di lavoro
            stmt.setDate(3, convertToDateViaSqlDate(LocalDate.now()));
            stmt.setString(4, descrizione);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    public static boolean addBustaPaga (BustaPaga bustaPaga) {
        connectAzienda();
        String query = "INSERT INTO bustapaga(ref_impiegato, mese_competenza, anno_competenza, stipendioBase, ore_servizio_1, ore_servizio_2, ore_servizio_3, ore_servizio_4, ore_straordinario, num_ritardi, num_assenze, importoTotale, isPagato) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, bustaPaga.getRef_impiegato());
            stmt.setInt(2, bustaPaga.getMese_competenza());
            stmt.setInt(3, bustaPaga.getAnno_competenza());
            stmt.setInt(4, bustaPaga.getStipendio_base());
            stmt.setInt(5, bustaPaga.getOre_servizio_1());
            stmt.setInt(6, bustaPaga.getOre_servizio_2());
            stmt.setInt(7, bustaPaga.getOre_servizio_3());
            stmt.setInt(8, bustaPaga.getOre_servizio_4());
            stmt.setInt(9, bustaPaga.getOre_straodinario());
            stmt.setInt(10, bustaPaga.getNum_ritardi());
            stmt.setInt(11, bustaPaga.getNum_assenze());
            stmt.setInt(12, bustaPaga.getImportoTotale());
            stmt.setBoolean(13, bustaPaga.isPagato());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    public static boolean addRichiesta (Richiesta richiesta) {
        connectAzienda();
        String query = "INSERT INTO richiesta(ref_impiegato, tipologia, motivazione, link_certificato, dataInizio, dataFine, esito) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, richiesta.getRef_impiegato());
            stmt.setString(2, richiesta.getTipologia());
            stmt.setString(3, richiesta.getMotivazione());
            stmt.setString(4, richiesta.getLink_certificato());
            stmt.setDate(5, convertToDateViaSqlDate(richiesta.getData_inizio()));
            stmt.setDate(6, convertToDateViaSqlDate(richiesta.getData_fine()));
            stmt.setBoolean(7, richiesta.isEsito());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    public static boolean queryNotificaRitardo (int matricola, String comunicazione) {
        connectAzienda();
        String query = "INSERT INTO utente_notifica(ref_notifica, ref_impiegato, dataNotifica, descrizione) VALUES (?,?,?,?)";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, 3);
            stmt.setInt(2, matricola);
            stmt.setDate(3, convertToDateViaSqlDate(LocalDate.now()));
            stmt.setString(4, comunicazione);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    public static boolean queryNotificaAssenza (int matricola, String comunicazione) {
        connectAzienda();
        String query = "INSERT INTO utente_notifica(ref_notifica, ref_impiegato, dataNotifica, descrizione) VALUES (?,?,?,?)";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, 4);
            stmt.setInt(2, matricola);
            stmt.setDate(3, convertToDateViaSqlDate(LocalDate.now()));
            stmt.setString(4, comunicazione);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    public static boolean querySendComunicazione (int matricola, String comunicazione) {
        connectAzienda();
        String query = "INSERT INTO utente_notifica(ref_notifica, ref_impiegato, dataNotifica, descrizione) VALUES (?,?,?,?)";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, 1);
            stmt.setInt(2, matricola);
            stmt.setDate(3, convertToDateViaSqlDate(LocalDate.now()));
            stmt.setString(4, comunicazione);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    public static boolean queryInsertLicenziamento (int matricola, String comunicazione) {
        connectAzienda();
        String query = "INSERT INTO utente_notifica(ref_notifica, ref_impiegato, dataNotifica, descrizione) VALUES (?,?,?,?)";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, 2);
            stmt.setInt(2, matricola);
            stmt.setDate(3, convertToDateViaSqlDate(LocalDate.now()));
            stmt.setString(4, comunicazione);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    public boolean queryVerificaCredenziali(int matricola, String password) {
        connectAzienda();
        String query = "SELECT count(*) FROM utente WHERE matricola=? AND passw=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, matricola);
            stmt.setString(2, password);
            stmt.setMaxRows(1); 
            ResultSet rs = stmt.executeQuery();
            rs.next();
            //rs.last();
            return rs.getInt(1)>0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    public boolean queryExistsImpiegato(int matricola) {
        connectAzienda();
        String query = "SELECT count(*) FROM utente WHERE matricola=? ";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, matricola);
            stmt.setMaxRows(1); 
            ResultSet rs = stmt.executeQuery();
            rs.next();
            //rs.last();
            return rs.getInt(1)>0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }
    
    public boolean queryExistsImpiegato(int matricola, String nome, String cognome) {
        connectAzienda();
        String query = "SELECT count(*) FROM utente WHERE matricola=? AND nome=? AND cognome=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, matricola);
            stmt.setString(2, nome);
            stmt.setString(3, cognome);
            stmt.setMaxRows(1); 
            ResultSet rs = stmt.executeQuery();
            rs.next();
            //rs.last();
            return rs.getInt(1)>0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    public boolean queryIsAdmin(int matricola) {
        connectAzienda();
        String query = "SELECT isAdmin FROM utente WHERE matricola=? ";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, matricola);
            stmt.setMaxRows(1); 
            ResultSet rs = stmt.executeQuery();
            rs.next();
            //rs.last();
            return rs.getBoolean(1);
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    public String queryGetEmail(int matricola) {
        connectAzienda();
        String query = "SELECT email FROM utente WHERE matricola=? ";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, matricola);
            stmt.setMaxRows(1); 
            ResultSet rs = stmt.executeQuery();
            rs.next();
            //rs.last();
            return rs.getString(1);
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return "null";
    }

    public int queryGetMatricola(String email, String cf, String nome, String cognome) {
        connectAzienda();
        String query = "SELECT matricola FROM utente WHERE email=? AND cf=? AND nome=? AND cognome=? ";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, cf);
            stmt.setString(3, nome);
            stmt.setString(4, cognome);
            stmt.setMaxRows(1); 
            ResultSet rs = stmt.executeQuery();
            rs.next();
            //rs.last();
            return rs.getInt(1);
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return 0;
    }

    public boolean queryCheckData(int matricola, LocalDate data) {
        connectAzienda();
        String query = "SELECT count(*) FROM indisponibilita WHERE ref_imp=? AND dataIndisp=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, matricola);
            stmt.setDate(2, convertToDateViaSqlDate(data));
            stmt.setMaxRows(1); 
            ResultSet rs = stmt.executeQuery();
            rs.next();
            //rs.last();
            return rs.getInt(1)>0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    public Utente queryVerificaEmail(String email) {
        connectAzienda();
        String query = "SELECT * FROM utente WHERE email=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Utente> utenti = new ArrayList<>();
            while (rs.next()) {
                utenti.add(Utente.createFromDB(rs));
            }
            //System.out.println(utenti.toString());
            return utenti.get(0);
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return null;
    }

    public Utente queryGetUtente(int matricola) {
        connectAzienda();
        String query = "SELECT * FROM utente WHERE matricola=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, matricola);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Utente> utenti = new ArrayList<>();
            while (rs.next()) {
                utenti.add(Utente.createFromDB(rs));
            }
            //System.out.println(utenti.toString());
            return utenti.get(0);
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return null;
    }

    public BustaPaga queryGetBustaPaga(int matricola, int mese, int anno) {
        connectAzienda();
        String query = "SELECT * FROM bustapaga WHERE ref_impiegato=? AND mese_competenza=? AND anno_competenza=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, matricola);
            stmt.setInt(2, mese);
            stmt.setInt(3, anno);
            ResultSet rs = stmt.executeQuery();
            ArrayList<BustaPaga> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(BustaPaga.createFromDB(rs));
            }
            return lista.get(0);
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return null;
    }

    public ArrayList<Impiegato> queryGetListaImpiegati() {
        String query = "SELECT * FROM utente WHERE isDDL=? AND newAssunzione=? AND isLicenziato=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setBoolean(1, false);
            stmt.setBoolean(2, false);
            stmt.setBoolean(3, false);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Impiegato> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(Impiegato.createFromDB(rs));
            }
            //System.out.println(utenti.toString());
            return lista;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return null;
    }

    public ArrayList<Utente_Notifica> queryGetListaComunicazioni(int matricola) {
        String query = "SELECT * FROM utente_notifica WHERE ref_impiegato=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, matricola);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Utente_Notifica> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(Utente_Notifica.createFromDB(rs));
            }
            //System.out.println(utenti.toString());
            return lista;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return null;
    }

    public ArrayList<Impiegato> queryGetListaImpiegatiNew() {
        String query = "SELECT * FROM utente WHERE isDDL=? AND isLicenziato=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setBoolean(1, false);
            stmt.setBoolean(2, false);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Impiegato> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(Impiegato.createFromDB(rs));
            }
            //System.out.println(utenti.toString());
            return lista;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return null;
    }

    public ArrayList<Turno> queryGetListaTurni() {
        String query = "SELECT * FROM turno WHERE isClosed=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setBoolean(1, false);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Turno> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(Turno.createFromDB(rs));
            }
            //System.out.println(utenti.toString());
            return lista;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return null;
    }
    

    public ArrayList<Turno> queryGetListaTurni(int matricola) {
        String query = "SELECT * FROM turno WHERE ref_impiegato=? AND isClosed=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, matricola);
            stmt.setBoolean(2, false);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Turno> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(Turno.createFromDB(rs));
            }
            //System.out.println(utenti.toString());
            return lista;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return null;
    }

    public ArrayList<Turno> queryGetListaTurniFirmati(int matricola) {
        String query = "SELECT * FROM turno WHERE ref_impiegato=? AND firmaIngresso=? AND firmaUscita=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, matricola);
            stmt.setBoolean(2, true);
            stmt.setBoolean(3, true);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Turno> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(Turno.createFromDB(rs));
            }
            //System.out.println(utenti.toString());
            return lista;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return null;
    }

    public ArrayList<Turno> queryGetListaAssenze(int matricola, LocalDate data) {
        String query = "SELECT * FROM turno WHERE ref_impiegato=? AND firmaIngresso=? AND firmaUscita=? AND dataTurno<?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, matricola);
            stmt.setBoolean(2, false);
            stmt.setBoolean(3, false);
            stmt.setDate(4, convertToDateViaSqlDate(data));
            ResultSet rs = stmt.executeQuery();
            ArrayList<Turno> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(Turno.createFromDB(rs));
            }
            //System.out.println(utenti.toString());
            return lista;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return null;
    }


    public ArrayList<Turno> queryGetListaTurni(LocalDate data) {
        String query = "SELECT * FROM turno WHERE dataTurno=? AND isClosed=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setDate(1, convertToDateViaSqlDate(data));
            stmt.setBoolean(2, false);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Turno> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(Turno.createFromDB(rs));
            }
            //System.out.println(utenti.toString());
            return lista;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return null;
    }

    public ArrayList<Turno> queryGetListaTurni(LocalDate data, int id_servizio) {
        String query = "SELECT * FROM turno WHERE dataTurno=? AND ref_servizio=? AND isClosed=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setDate(1, convertToDateViaSqlDate(data));
            stmt.setInt(2,id_servizio);
            stmt.setBoolean(3, false);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Turno> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(Turno.createFromDB(rs));
            }
            //System.out.println(utenti.toString());
            return lista;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return null;
    }

    public ArrayList<Turno> queryGetListaTurni(int matricola, LocalDate data) {
        String query = "SELECT * FROM turno WHERE dataTurno >= ? AND ref_impiegato=? AND isClosed=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setDate(1, convertToDateViaSqlDate(data));
            stmt.setInt(2, matricola);
            stmt.setBoolean(3, false);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Turno> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(Turno.createFromDB(rs));
            }
            //System.out.println(utenti.toString());
            return lista;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return null;
    }

    public ArrayList<Utente_Notifica> queryGetListaChiusuraServizi() {
        String query = "SELECT * FROM utente_notifica WHERE ref_notifica=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, 7);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Utente_Notifica> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(Utente_Notifica.createFromDB(rs));
            }
            //System.out.println(utenti.toString());
            return lista;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return null;
    }

    public ArrayList<Richiesta> queryGetListaRichiesteConcesse() {
        String query = "SELECT * FROM richiesta WHERE esito=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setBoolean(1, true);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Richiesta> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(Richiesta.createFromDB(rs));
            }
            //System.out.println(utenti.toString());
            return lista;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return null;
    }

    public ArrayList<Richiesta> queryGetListaRichiesteImpiegato(int matricola) {
        String query = "SELECT * FROM richiesta WHERE ref_impiegato=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, matricola);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Richiesta> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(Richiesta.createFromDB(rs));
            }
            //System.out.println(utenti.toString());
            return lista;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return null;
    }

    public ArrayList<Utente_Notifica> getListaNotificheRitardiAssenze(LocalDate inizio, LocalDate fine) {
        String query = "SELECT * FROM utente_notifica WHERE (ref_notifica=? OR ref_notifica=?) AND dataNotifica>=? AND dataNotifica<=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, 3);
            stmt.setInt(2, 4);
            stmt.setDate(3, convertToDateViaSqlDate(inizio));
            stmt.setDate(4, convertToDateViaSqlDate(fine));
            ResultSet rs = stmt.executeQuery();
            ArrayList<Utente_Notifica> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(Utente_Notifica.createFromDB(rs));
            }
            //System.out.println(utenti.toString());
            return lista;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return null;
    }
    
    public ArrayList<Utente_Notifica> getListaNotifiche(int matricola) {
        String query = "SELECT * FROM utente_notifica WHERE ref_impiegato=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, matricola);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Utente_Notifica> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(Utente_Notifica.createFromDB(rs));
            }
            //System.out.println(utenti.toString());
            return lista;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return null;
    }

    public ArrayList<Turno> queryGetTurno(int matricola, LocalDate data) {
        String query = "SELECT * FROM turno WHERE dataTurno = ? AND ref_impiegato=? AND isClosed=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setDate(1, convertToDateViaSqlDate(data));
            stmt.setInt(2, matricola);
            stmt.setBoolean(3, false);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Turno> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(Turno.createFromDB(rs));
            }
            //System.out.println(utenti.toString());
            return lista;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return null;
    }

    public ArrayList<Turno> queryCercaTurni(LocalDate data, LocalTime tempo) {
        String query = "SELECT * FROM turno WHERE dataTurno = ? AND isClosed=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setDate(1, convertToDateViaSqlDate(data));
            stmt.setBoolean(2, false);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Turno> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(Turno.createFromDB(rs));
            }
            //System.out.println(utenti.toString());
            return lista;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return null;
    }
    

    public ArrayList<Turno> queryGetTurnoFirmato(int matricola, LocalDate data) {
        String query = "SELECT * FROM turno WHERE dataTurno = ? AND ref_impiegato=? AND isClosed=? AND firmaIngresso=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setDate(1, convertToDateViaSqlDate(data));
            stmt.setInt(2, matricola);
            stmt.setBoolean(3, false);
            stmt.setBoolean(4, true);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Turno> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(Turno.createFromDB(rs));
            }
            //System.out.println(utenti.toString());
            return lista;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return null;
    }

    public ArrayList<Turno_ritardo> queryGetMotivazioneRitardo(int matricola) {
        String query = "SELECT id_turno, ref_impiegato, motivazione FROM turno, turno_ritardo WHERE turno.id_turno = turno_ritardo.ref_turno and turno.ref_impiegato=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, matricola);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Turno_ritardo> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(Turno_ritardo.createFromDB(rs));
            }
            //System.out.println(utenti.toString());
            return lista;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return null;
    }

    public ArrayList<Turno_ritardo> queryGetMotivazioneRitardo() {
        String query = "SELECT id_turno, ref_impiegato, motivazione FROM turno, turno_ritardo WHERE turno_ritardo.ref_turno=turno.id_turno";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            ArrayList<Turno_ritardo> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(Turno_ritardo.createFromDB(rs));
            }
            //System.out.println(utenti.toString());
            return lista;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return null;
    }


    public boolean queryFirmaIngresso(Turno turno) {
        String query = "UPDATE turno SET firmaIngresso=? WHERE id_turno=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setBoolean(1, true);
            stmt.setInt(2, turno.getId_turno());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    public boolean queryInserisciMotivazioneRitardo(Turno turno, String motivazione) {
        String query = "INSERT INTO turno_ritardo(ref_turno, motivazione) VALUES (?,?)";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, turno.getId_turno());
            stmt.setString(2, motivazione);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    public boolean queryFirmaUscita(Turno turno) {
        String query = "UPDATE turno SET firmaUscita=? WHERE id_turno=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setBoolean(1, true);
            stmt.setInt(2, turno.getId_turno());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    public ArrayList<LocalDate> queryGetPeriodiAstensione(int matricola) {
        String query = "SELECT dataIndisp FROM indisponibilita WHERE ref_imp=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, matricola);
            ResultSet rs = stmt.executeQuery();
            ArrayList<LocalDate> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(convertToLocalDateViaSqlDate(rs.getDate(1)));
            }
            //System.out.println(utenti.toString());
            return lista;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return null;
    }

    public ArrayList<BustaPaga> queryGetListaBustePaga(int matricola, int mese_inizio, int anno_inizio, int mese_fine, int anno_fine) {
        String query = "SELECT * FROM bustapaga WHERE ref_impiegato=? AND mese_competenza>=? AND anno_competenza>=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, matricola);
            stmt.setInt(2, mese_inizio);
            stmt.setInt(3, anno_inizio);
            ResultSet rs = stmt.executeQuery();
            ArrayList<BustaPaga> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(BustaPaga.createFromDB(rs));
            }
            ArrayList<BustaPaga> new_lista = new ArrayList<>();
            String mese_inizio_String;
            if(mese_inizio<10) {
                mese_inizio_String = "0" + Integer.toString(mese_inizio);
            } else {
                mese_inizio_String = Integer.toString(mese_inizio);
            }
            String mese_fine_String;
            if(mese_fine<10) {
                mese_fine_String = "0" + Integer.toString(mese_fine);
            } else {
                mese_fine_String = Integer.toString(mese_fine);
            }
            LocalDate data_inizio = LocalDate.parse(anno_inizio + "-" + mese_inizio_String + "-01").minusMonths(1);
            LocalDate data_fine = LocalDate.parse(anno_fine + "-" + mese_fine_String + "-01").plusMonths(1);
            for(BustaPaga bustaPaga: lista) {
                String mese_String;
                if(bustaPaga.getMese_competenza()<10) {
                    mese_String = "0" + Integer.toString(bustaPaga.getMese_competenza());
                } else {
                    mese_String = Integer.toString(bustaPaga.getMese_competenza());
                }
                
                LocalDate data = LocalDate.parse(bustaPaga.getAnno_competenza() + "-" + mese_String + "-01");
                if(data.isAfter(data_inizio) && data.isBefore(data_fine))
                    new_lista.add(bustaPaga);
            }
            return new_lista;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return null;
    }

    public ArrayList<BustaPaga> queryGetListaBustePaga(int matricola) {
        String query = "SELECT * FROM bustapaga WHERE ref_impiegato=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, matricola);
            ResultSet rs = stmt.executeQuery();
            ArrayList<BustaPaga> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(BustaPaga.createFromDB(rs));
            }
            ArrayList<BustaPaga> new_lista = new ArrayList<>();
            return lista;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return null;
    }

    
    public ArrayList<BustaPaga> queryGetListaBugaDaPagare(int mese, int anno) {
        String query = "SELECT * FROM bustapaga WHERE mese_competenza=? AND anno_competenza=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, mese);
            stmt.setInt(2, anno);
            ResultSet rs = stmt.executeQuery();
            ArrayList<BustaPaga> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(BustaPaga.createFromDB(rs));
            }
            //System.out.println(utenti.toString());
            return lista;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return null;
    }

    public ArrayList<Richiesta> queryGetListaFerie() {
        String query = "SELECT * FROM richiesta WHERE esito=? AND tipologia=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setBoolean(1, false);
            stmt.setString(2, "ferie");
            ResultSet rs = stmt.executeQuery();
            ArrayList<Richiesta> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(Richiesta.createFromDB(rs));
            }
            //System.out.println(utenti.toString());
            return lista;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return null;
    }

    public static int queryGetGiorniRimanenti(int matricola, String tipologia) {
        connectAzienda();
        String query = "SELECT * FROM richiesta WHERE ref_impiegato=? AND tipologia=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, matricola);
            stmt.setString(2, tipologia);
            //stmt.setMaxRows(1); 
            ResultSet rs = stmt.executeQuery();
            ArrayList<Richiesta> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(Richiesta.createFromDB(rs));
            }
            int count = 0;
            for(Richiesta richiesta: lista) {
                long days = ChronoUnit.DAYS.between(richiesta.getData_inizio(), richiesta.getData_fine()) + 1;
                count += days;
            }
            return count;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return (Integer) null;
    }

    public static int queryGetFerieRichieste(int matricola, String tipologia, int year) {
        connectAzienda();
        String query = "SELECT * FROM richiesta WHERE ref_impiegato=? AND tipologia=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, matricola);
            stmt.setString(2, tipologia);
            //stmt.setMaxRows(1); 
            ResultSet rs = stmt.executeQuery();
            ArrayList<Richiesta> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(Richiesta.createFromDB(rs));
            }
            int count = 0;
            for(Richiesta richiesta: lista) {
                if(richiesta.getData_inizio().getYear() == year) {
                    long days = ChronoUnit.DAYS.between(richiesta.getData_inizio(), richiesta.getData_fine()) + 1;
                    count += days;
                }
            }
            return count;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return (Integer) null;
    }
    
    //sezione update
    public boolean queryUpdatePassword(int matricola, String password) {
        connectAzienda();
        String query = "UPDATE utente SET passw=? WHERE matricola=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setString(1, password);
            stmt.setInt(2, matricola);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    public boolean queryUpdateDati(String colonna, String valore, int matricola) {
        connectAzienda();
        String query = "UPDATE utente SET "+ colonna +"=? WHERE matricola=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setString(1, valore);
            stmt.setInt(2, matricola);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    public boolean queryUpdateDati(String colonna, int valore, int matricola) {
        connectAzienda();
        String query = "UPDATE utente SET "+ colonna +"=? WHERE matricola=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, valore);
            stmt.setInt(2, matricola);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    public boolean queryUpdateTurno(Turno turno, int nuovaMatricola) {
        connectAzienda();
        String query = "UPDATE turno SET ref_impiegato=? WHERE id_turno=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, nuovaMatricola);
            stmt.setInt(2, turno.getId_turno());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    public boolean queryUpdateOrario(Turno turno, LocalTime oraInizio, LocalTime oraFine) {
        connectAzienda();
        String query = "UPDATE turno SET oraInizio=?, oraFine=? WHERE id_turno=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setTime(1, toSQLTime(oraInizio));
            stmt.setTime(2, toSQLTime(oraFine));
            stmt.setInt(3, turno.getId_turno());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    public boolean updateOreStraordinario(Turno turno, int nuovaMatricola) {
        connectAzienda();
        String query = "UPDATE bustapaga SET ore_straordinario=ore_straordinario+? WHERE ref_impiegato=? AND mese_competenza=? AND anno_competenza=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, 8);
            stmt.setInt(2, nuovaMatricola);
            stmt.setInt(3, turno.getDataTurno().getMonthValue());
            stmt.setInt(4, turno.getDataTurno().getYear());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    public boolean queryUpdateRitardo(Turno turno) {
        connectAzienda();
        String query = "UPDATE bustapaga SET num_ritardi=num_ritardi+? WHERE ref_impiegato=? AND mese_competenza=? AND anno_competenza=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, 1);
            stmt.setInt(2, turno.getRef_impiegato());
            stmt.setInt(3, turno.getDataTurno().getMonthValue());
            stmt.setInt(4, turno.getDataTurno().getYear());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    public boolean queryUpdateAssenze(Turno turno) {
        connectAzienda();
        String query = "UPDATE bustapaga SET num_assenze=num_assenze+? WHERE ref_impiegato=? AND mese_competenza=? AND anno_competenza=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, 1);
            stmt.setInt(2, turno.getRef_impiegato());
            stmt.setInt(3, turno.getDataTurno().getMonthValue());
            stmt.setInt(4, turno.getDataTurno().getYear());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    public boolean queryUpdateBustaPaga(BustaPaga bustaPaga) {
        connectAzienda();
        String query = "UPDATE bustapaga SET isPagato=? WHERE ref_impiegato=? AND mese_competenza=? AND anno_competenza=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setBoolean(1, true);
            stmt.setInt(2, bustaPaga.getRef_impiegato());
            stmt.setInt(3, bustaPaga.getMese_competenza());
            stmt.setInt(4, bustaPaga.getAnno_competenza());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    public boolean queryUpdateBustaPaga(BustaPaga bustaPaga, int totale) {
        connectAzienda();
        //System.out.println("busta paga dal dbms" + bustaPaga);
        String query = "UPDATE bustapaga SET importoTotale=?, ore_servizio_1=?, ore_servizio_2=?, ore_servizio_3=?, ore_servizio_4=? WHERE ref_impiegato=? AND mese_competenza=? AND anno_competenza=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, bustaPaga.getImportoTotale());
            stmt.setInt(2, bustaPaga.getOre_servizio_1());
            stmt.setInt(3, bustaPaga.getOre_servizio_2());
            stmt.setInt(4, bustaPaga.getOre_servizio_3());
            stmt.setInt(5, bustaPaga.getOre_servizio_4());
            stmt.setInt(6, bustaPaga.getRef_impiegato());
            stmt.setInt(7, bustaPaga.getMese_competenza());
            stmt.setInt(8, bustaPaga.getAnno_competenza());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    public boolean queryUpdateBustaPaga(int ref_impiegato, int stipendio_base, int mese, int anno) {
        connectAzienda();
        String query = "UPDATE bustapaga SET stipendioBase=? WHERE ref_impiegato=? AND mese_competenza>=? AND anno_competenza>=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, stipendio_base);
            stmt.setInt(2, ref_impiegato);
            stmt.setInt(3, mese);
            stmt.setInt(4, anno);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    public boolean queryApprovaFerie(Richiesta richiesta) {
        connectAzienda();
        String query = "UPDATE richiesta SET esito=? WHERE id_richiesta=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setBoolean(1, true);
            stmt.setInt(2, richiesta.getId_richiesta());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    public boolean queryUpdateImpiegatiAssunti() {
        connectAzienda();
        String query = "UPDATE utente SET newAssunzione=? WHERE 1";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setBoolean(1, false);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    //sezione rimuovi

    public boolean queryLicenziaImpiegato(int matricola) {
        connectAzienda();
        String query = "UPDATE utente SET isLicenziato=?, dataLicenziamento=? WHERE matricola=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setBoolean(1, true);
            stmt.setDate(2, convertToDateViaSqlDate(LocalDate.now()));
            stmt.setInt(3, matricola);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    public boolean queryRimuoviTurno(Turno turno) {
        connectAzienda();
        String query = "UPDATE turno SET isClosed=? WHERE id_turno=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setBoolean(1, true);
            stmt.setInt(2, turno.getId_turno());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }

    public boolean queryRifiutaFerie(Richiesta richiesta) {
        connectAzienda();
        String query = "DELETE FROM richiesta WHERE id_richiesta=?";
        try (PreparedStatement stmt = connAzienda.prepareStatement(query)) {
            stmt.setInt(1, richiesta.getId_richiesta());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            erroreComunicazioneDBMS(e);
        }
        return false;
    }
    

}
