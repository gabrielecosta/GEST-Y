package com.gest.GestioneImpiegato.Interface;
import javax.swing.*;

import com.gest.Common.DBMSservice;
import com.gest.Entity.Impiegato;
import com.gest.Entity.Turno;
import com.gest.Entity.Utente;

import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class ModuloCercaDati extends JFrame implements ActionListener{
    
	private Utente utente, new_utente;
	Impiegato impiegato;
	JPanel menu;
	JButton indietro;
	JLabel matricola, Matricola, cf,CF,nome,Nome,cognome,Cognome,email,Email,sesso,Sesso,iban,Iban,ruolo,Ruolo,numero,Numero,dataA,DataA,dataL,DataL;
	
	
	ModuloCercaDati(Utente utente,String ID, Impiegato impiegato){
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.impiegato = impiegato;
		this.utente = new Utente();
		this.utente = utente;
		this.new_utente = new Utente();
		DBMSservice dbms = new DBMSservice();
		this.new_utente = dbms.queryGetUtente(this.impiegato.getMatricola());
		
		nome = new JLabel();
		nome.setText("Nome:");      //set label value for textField2
        nome.setBounds(500,90, 200,30);
        
		Nome = new JLabel();
        Nome.setText(this.new_utente.getNome());      //set label value for textField2
        Nome.setBounds(625,90, 200,30);
        
		cognome = new JLabel();
		cognome.setText("Cognome:");      //set label value for textField2
        cognome.setBounds(500,120, 200,30);
        
		Cognome = new JLabel();
        Cognome.setText(this.new_utente.getCognome());      //set label value for textField2
        Cognome.setBounds(625,120, 200,30);
		
		matricola = new JLabel();
        matricola.setText("Matricola:");      //set label value for textField2
        matricola.setBounds(500,150, 200,30);
        
		Matricola = new JLabel();
        Matricola.setText(Integer.toString(this.new_utente.getMatricola()));      //set label value for textField2
        Matricola.setBounds(625,150, 200,30);
        
		cf = new JLabel();
        cf.setText("Cod. Fiscale:");      //set label value for textField2
        cf.setBounds(500,180, 200,30);
        
		CF = new JLabel();
        CF.setText(this.new_utente.getCf());      //set label value for textField2
        CF.setBounds(625,180, 200,30);
      
		email = new JLabel();
		email.setText("Email:");      //set label value for textField2
		email.setBounds(500,210, 200,30);
        
		Email = new JLabel();
		Email.setText(this.new_utente.getEmail());      //set label value for textField2
		Email.setBounds(625,210, 200,30);
		
		iban = new JLabel();
		iban.setText("Iban:");      //set label value for textField2
		iban.setBounds(500,240, 200,30);
        
		Iban = new JLabel();
		Iban.setText(this.new_utente.getIban());      //set label value for textField2
		Iban.setBounds(625,240, 200,30);
		
		sesso = new JLabel();
		sesso.setText("Sesso:");      //set label value for textField2
		sesso.setBounds(500,270, 200,30);
        
		Sesso = new JLabel();
		Sesso.setText(this.new_utente.getSesso());      //set label value for textField2
		Sesso.setBounds(625,270, 200,30);
		
		ruolo = new JLabel();
		ruolo.setText("Ruolo:");      //set label value for textField2
		ruolo.setBounds(500,300, 200,30);
        
		Ruolo = new JLabel();
		Ruolo.setText(Integer.toString(this.new_utente.getRuolo()));      //set label value for textField2
		Ruolo.setBounds(625,300, 200,30);
        
		numero = new JLabel();
		numero.setText("Numero:");      //set label value for textField2
		numero.setBounds(500,330, 200,30);
        
		Numero = new JLabel();
		Numero.setText(this.new_utente.getTelefono());      //set label value for textField2
		Numero.setBounds(625,330, 200,30);
		
		dataA = new JLabel();
		dataA.setText("Assunzione:");      //set label value for textField2
		dataA.setBounds(500,360, 200,30);
        
		DataA = new JLabel();
		DataA.setText(this.new_utente.getNewAssunzione().toString());
		DataA.setBounds(625,360, 200,30);
		
		dataL = new JLabel();
		dataL.setText("Licenziamento:");      //set label value for textField2
		dataL.setBounds(500,390, 200,30);
        
		DataL = new JLabel();
		DataL.setText(this.new_utente.getIsLicenziato().toString());
		DataL.setBounds(625,390, 200,30);
		
		indietro= new JButton("Indietro");
		indietro.setBounds(450,500,300,50);
		indietro.setBackground(Color.white);
		
		if(!Objects.isNull(this.new_utente.getDataAssunzione())) {
			DataA.setText(this.new_utente.getDataAssunzione().toString());      //set label value for textField2
		}
		if(!Objects.isNull(this.new_utente.getDataLicenziamento())) {
			DataL.setText(this.new_utente.getDataLicenziamento().toString());      //set label value for textField2
		}
		
		menu = new JPanel();
		menu.setLayout(null);
		menu.setSize(1200,600);
		menu.setVisible(true);
		menu.add(indietro);
		menu.add(nome);
		menu.add(Nome);
		menu.add(cognome);
		menu.add(Cognome);
		menu.add(matricola);
		menu.add(Matricola);
		menu.add(cf);
		menu.add(CF);
		menu.add(sesso);
		menu.add(Sesso);
		menu.add(dataA);
		menu.add(DataA);
		menu.add(dataL);
		menu.add(DataL);
		menu.add(iban);
		menu.add(Iban);
		menu.add(ruolo);
		menu.add(Ruolo);
		menu.add(email);
		menu.add(Email);
		menu.add(numero);
		menu.add(Numero);
		add(menu);
	    indietro.addActionListener(this);
	    
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ModuloCerca modulo = new ModuloCerca(this.utente);
		modulo.setSize(1200,600);
		modulo.setVisible(true);
		dispose();
	}

	
	
	
}