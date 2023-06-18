package com.gest.Entity;

public class Servizio {
    private int id_servizio;
    private String nome;
    private int gratifica;
    //gratifica da intendere in percentuale

   public Servizio(int id_servizio, String nome_servizio, int gratifica_servizio) {
    this.setId_servizio(id_servizio);
    this.setNome(nome_servizio);
    this.setGratifica(gratifica_servizio);
   }

    public int getId_servizio() {
        return id_servizio;
    }
    
    public void setId_servizio(int id_servizio) {
        this.id_servizio = id_servizio;
    }

     public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

     public int getGratifica() {
        return gratifica;
    }

    public void setGratifica(int gratifica) {
        this.gratifica = gratifica;
    }

    public String toString() {
        return "{ " + this.getId_servizio() + ", " + this.getNome() + ", " + this.getGratifica() + "% }";
    }

}
