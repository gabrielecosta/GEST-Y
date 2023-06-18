package com.gest.GestioneDDL.Interface;
import javax.swing.*;

import com.gest.Entity.Utente;
import com.gest.GestioneAccount.Control.ControlLogin;
import com.gest.GestioneDDL.Control.gestioneImpiegatoCtl;

import java.awt.*;
import java.awt.event.*;

public class ModuloModificaRuolo extends JFrame implements ActionListener{
    
	private Utente utente;
	JPanel menu;
	JButton indietro;
	JLabel motivotxt, oretxt, success,result,errore,errore2,errore3,errore4,password;
	JButton invia,invia2,indietro2; //MAX 2 ORE DI STACCO
	JTextField ore;
	JPasswordField Password;
	SpinnerModel spinnerModel = new SpinnerNumberModel(1,1,4,1);
	JSpinner anlist = new JSpinner(spinnerModel);
	
	
	ModuloModificaRuolo(Utente utente){
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.utente = new Utente();
		this.utente = utente;
		
		errore = new JLabel();
		
		invia = new JButton("Modifica Ruolo");
		invia.setBounds(500,250,200,30);
		invia.setBackground(Color.white);
		
		invia2 = new JButton("Modifica Ruolo");
		invia2.setBounds(500,250,200,30);
		invia2.setBackground(Color.white);
		invia2.setVisible(false);
		
		result = new JLabel();
		result.setBounds(500,225,300,30);
		result.setForeground(Color.green.darker());
		result.setVisible(false);
		
		errore = new JLabel();
		errore.setText("Errore! Inserire Matricola");
		errore.setBounds(500,175, 200,30);
		errore.setForeground(Color.red.darker());
		errore.setVisible(false);
		
		errore2 = new JLabel();
		errore2.setText("Errore! Inserire una Matricola Valida!");
		errore2.setBounds(500,175, 300,30);
		errore2.setForeground(Color.red.darker());
		errore2.setVisible(false);
		
		errore3 = new JLabel();
		errore3.setText("Errore! Password Errata!");
		errore3.setBounds(500,170, 200,30);
		errore3.setForeground(Color.red.darker());
		errore3.setVisible(false);
		
		errore4 = new JLabel();
		errore4.setText("Errore! Inserisci la tua password");
		errore4.setBounds(500,170, 200,30);
		errore4.setForeground(Color.red.darker());
		errore4.setVisible(false);
		
		motivotxt = new JLabel();
		motivotxt.setText("Matricola: ");
		motivotxt.setBounds(400,190,250,50);
		
		oretxt = new JLabel();
		oretxt.setText("Nuovo Ruolo:");
		oretxt.setBounds(400,150,200,30);
		
		password = new JLabel();
		password.setText("Password: ");
		password.setBounds(400,190,250,50);
		password.setVisible(false);
		
		Password = new JPasswordField();
		Password.setBounds(500,200,200,30);
		Password.setVisible(false);
		
		anlist.setBounds(500,150,50,30);
		
        ore = new JTextField(2);    //set length of the text
        ore.setBounds(500,200,200,30);

		indietro= new JButton("Indietro");
		indietro.setBounds(500,300,200,30);
		indietro.setBackground(Color.white);
		
		indietro2= new JButton("Indietro");
		indietro2.setBounds(500,300,200,30);
		indietro2.setBackground(Color.white);
		indietro2.setVisible(false);
		
		menu = new JPanel();
		menu.setLayout(null);
		menu.add(result);
		menu.setSize(1200,600);
		menu.setVisible(true);
		menu.add(motivotxt);
		menu.add(indietro);
		menu.add(invia);
		menu.add(ore);
		menu.add(oretxt);
		menu.add(anlist);
		menu.add(errore);
		menu.add(errore2);
		menu.add(errore3);
		menu.add(errore4);
		menu.add(Password);
		menu.add(password);
		menu.add(invia2);
		menu.add(indietro2);
		add(menu);
	    indietro.addActionListener(this);	
	    invia.addActionListener(this::Invia);
	    invia2.addActionListener(this::Invia2);
	    indietro2.addActionListener(this::Indietro2);
	}
	
	public void Indietro2(ActionEvent e) {
		motivotxt.setVisible(true);
		invia.setVisible(true);
		ore.setVisible(true);
		oretxt.setVisible(true);
		anlist.setVisible(true);
		errore2.setVisible(false);
		errore3.setVisible(false);
		errore4.setVisible(false);
		errore.setVisible(false);
		
		password.setVisible(false);
		Password.setVisible(false);
		indietro2.setVisible(false);
		invia2.setVisible(false);
		indietro.setVisible(true);
	}
	
	public void Invia2(ActionEvent e) {
		String passS = Password.getText();
		gestioneImpiegatoCtl gestione = new gestioneImpiegatoCtl();
		if(passS.equals("")) {
          errore4.setVisible(true);
          errore3.setVisible(false);
		}
		else if(gestione.checkPassword(utente.getPassw(), passS)) {

			indietro.setVisible(true);
			errore3.setVisible(false);
			errore4.setVisible(false);
			password.setVisible(false);
			Password.setVisible(false);
			indietro2.setVisible(false);
			invia2.setVisible(false);
			gestione.modificaRuolo(Integer.parseInt(ore.getText()), Integer.parseInt(anlist.getValue().toString()));
			
			result.setText(ore.getText() + " " + "impostato a ruolo:" + " " + anlist.getValue());
		       result.setVisible(true);
			
		       indietro.setBounds(500,250,200,30);
			
			
			
		
		}
		else
		{
          errore3.setVisible(true);
			errore4.setVisible(false);
		}
	}
	
	
	public void Invia(ActionEvent e) {
		String Mot = ore.getText();
		boolean t = Mot.matches(".*[a-zA-Z]+.*");
		if(Mot.equals("")) {
			errore.setVisible(true);
			errore2.setVisible(false);
		}
		else if (Mot.length() > 10 || t == true)
		{
			errore2.setVisible(true);
			errore.setVisible(false);
		}
		else{
		
			motivotxt.setVisible(false);
			invia.setVisible(false);
			ore.setVisible(false);
			oretxt.setVisible(false);
			anlist.setVisible(false);
			errore2.setVisible(false);
			errore.setVisible(false);
			indietro.setVisible(false);
			password.setVisible(true);
			Password.setVisible(true);
			indietro2.setVisible(true);
			invia2.setVisible(true);
			

			
		}
		
		

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ModuloGestioneImpiegato mod = new ModuloGestioneImpiegato(utente);
        mod.setSize(1200,600);
        mod.setVisible(true);
    	dispose();
	}
}