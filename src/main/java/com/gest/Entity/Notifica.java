package com.gest.Entity;

public class Notifica {
    private int id_notifica;
    private String tipologia;
    
    public Notifica(int id, String tipo) {
        this.setId_notifica(id);
        this.setTipologia(tipo);
    }

    public int getId_notifica() {
        return id_notifica;
    }

    public void setId_notifica(int id_notifica) {
        this.id_notifica = id_notifica;
    }

    public String getTipologia() {
        return tipologia;
    }
    
    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public String toString() {
        return "{" + this.getId_notifica() + ", " + this.getTipologia() + " }";
    }
}
