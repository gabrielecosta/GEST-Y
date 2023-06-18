package com.gest.GestioneDDL.Interface;
import javax.swing.*;

import com.gest.Entity.Utente;
import com.gest.GestioneAccount.Control.ControlLogin;
import com.gest.GestioneDDL.Control.gestioneImpiegatoCtl;

import java.awt.*;
import java.awt.event.*;
public class ModuloLicenziamento extends JFrame implements ActionListener{
    
	private Utente utente;
	JPanel menu;
	JButton indietro,invia,indietro2,invia2;
	JLabel errore,fatto,motivo,matricola,passlabel,errore2,errore3,errore4;
	JTextField Motivo;
	JPasswordField pass;
	
	ModuloLicenziamento(Utente utente){
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.utente = new Utente();
		this.utente = utente;

        Motivo = new JTextField(15);    //set length of the text
        Motivo.setBounds(500,200,200,30);
		
		motivo = new JLabel();
		motivo.setText("Matricola:");
        motivo.setBounds(400,200,200,30);
		errore = new JLabel();
		
        pass = new JPasswordField(15);    //set length of the text
        pass.setBounds(500,200,200,30);
		pass.setVisible(false);
		
        passlabel = new JLabel();
		passlabel.setText("Password:");
		passlabel.setBounds(400,200,200,30);
		passlabel.setVisible(false);
		
		errore = new JLabel();
		errore.setText("Errore! Inserire Matricola");
		errore.setBounds(500,170, 200,30);
		errore.setForeground(Color.red.darker());
		errore.setVisible(false);
		
		errore2 = new JLabel();
		errore2.setText("Errore! Inserire una Matricola Valida!");
		errore2.setBounds(500,170, 300,30);
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
        
		fatto = new JLabel();
		fatto.setText("Licenziato con successo!");
		fatto.setBounds(500,170, 200,30);
		fatto.setForeground(Color.GREEN.darker());
		fatto.setVisible(false);
		
		indietro= new JButton("Indietro");
		indietro.setBounds(500,300,200,30);
		indietro.setBackground(Color.white);
		
		invia= new JButton("Invia");
		invia.setBounds(500,250,200,30);
		invia.setBackground(Color.white);
		
		indietro2 = new JButton("Indietro");
		indietro2.setBounds(500,300,200,30);
		indietro2.setBackground(Color.white);
		indietro2.setVisible(false);
		
		invia2 = new JButton("Invia");
		invia2.setBounds(500,250,200,30);
		invia2.setBackground(Color.white);
	    invia2.setVisible(false);
		
	    menu = new JPanel();
		menu.setLayout(null);
		menu.setSize(1200,600);
		menu.setVisible(true);
		menu.add(indietro2);
		menu.add(invia2);
		menu.add(Motivo);
		menu.add(errore);
		menu.add(errore2);
		menu.add(errore3);
		menu.add(errore4);
		menu.add(fatto);
		menu.add(invia);
		menu.add(indietro);
		menu.add(motivo);
		menu.add(pass);
		menu.add(passlabel);
		add(menu);
		invia.addActionListener(this::Invia);
	    indietro.addActionListener(this);	
	    indietro2.addActionListener(this::Indietro2);
	    invia2.addActionListener(this::Invia2);
	}

	public void Indietro2(ActionEvent e) {
		indietro.setVisible(true);
		errore.setVisible(false);
		Motivo.setVisible(true);
		motivo.setVisible(true);
		invia.setVisible(true);
		indietro2.setVisible(false);
		invia2.setVisible(false);
		pass.setVisible(false);
		passlabel.setVisible(false);
	}
	
	public void Invia2(ActionEvent e) {
		String passS = pass.getText();
		gestioneImpiegatoCtl gestione = new gestioneImpiegatoCtl();
		if(passS.equals("")) {
          errore4.setVisible(true);
          errore3.setVisible(false);
		}
		else if(gestione.checkPassword(utente.getPassw(), passS)) {
			invia2.setVisible(false);
			fatto.setText(Motivo.getText() + " " + "licenziato con successo!");
			fatto.setVisible(true);
			indietro.setBounds(500,200,200,30);
			indietro.setVisible(true);
			indietro2.setVisible(false);
			pass.setVisible(false);
			passlabel.setVisible(false);
			errore3.setVisible(false);
			errore4.setVisible(false);
			gestione.licenzia(Integer.parseInt(Motivo.getText()));
		
		}
		else
		{
          errore3.setVisible(true);
			errore4.setVisible(false);
		}
	}
	
	
	
	public void Invia(ActionEvent e) {
		String Mot = Motivo.getText();
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
			errore.setVisible(false);
			errore2.setVisible(false);
			Motivo.setVisible(false);
			motivo.setVisible(false);
			invia.setVisible(false);
			indietro2.setVisible(true);
			indietro.setVisible(false);
			invia2.setVisible(true);
			pass.setVisible(true);
			passlabel.setVisible(true);
			//fatto.setVisible(true);
			//indietro.setBounds(500,200,200,50);
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

	
	
	