package com.gest.GestioneTurno.Interface;
import javax.swing.*;

import com.gest.Common.SendEmail;
import com.gest.Entity.Utente;
import com.gest.GestioneAccount.Interface.ModuloDashBoard;
import com.gest.GestioneTurno.Control.GestioneAssenza;
import com.gest.GestioneTurno.Control.RichiediAstensioneCTL;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class ModuloMalattiaAdmin extends JFrame implements ActionListener{
	private Utente utente;
	JPanel menu;
	JButton indietro,invia;
	JLabel errore,fatto,motivo,matricola,DataTXT,Data2TXT,iniziotxt,finetxt;
	JTextField Motivo,Matricola;
	JFormattedTextField Inizio, Fine;
	
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	
	public ModuloMalattiaAdmin(Utente utente){
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.utente = new Utente();
		this.utente = utente;

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Inizio = new JFormattedTextField(format);
		Inizio.setBounds(500,100,200,30);
		Fine = new JFormattedTextField(format);
		Fine.setBounds(500,150,200,30);
		
		DataTXT = new JLabel("Seleziona intervallo date:");
	    DataTXT.setBounds(500,50,200,50);
	    
		Data2TXT = new JLabel("FORMATO: DD/MM/YYYY");
	    Data2TXT.setBounds(500,65,200,50);
	    Data2TXT.setForeground(Color.GRAY.darker());
	    
		iniziotxt = new JLabel("Data Inizio:");
		iniziotxt.setBounds(400,90,200,50);
	    
		finetxt = new JLabel("Data Fine:");
	    finetxt.setBounds(400,140,200,50);
		
        Motivo = new JTextField(15);    //set length of the text
        Motivo.setBounds(500,200,200,30);
		
		motivo = new JLabel();
		motivo.setText("Motivazione:");
        motivo.setBounds(400,200,200,30);
		errore = new JLabel();
		
		errore.setText("Errore! Inserire Motivazione");
		errore.setBounds(500,170, 200,30);
		errore.setForeground(Color.red.darker());
		errore.setVisible(false);
		
		matricola = new JLabel();
		matricola.setText("Matricola:");
        matricola.setBounds(400,250,200,30);
        
        Matricola = new JTextField(15);    //set length of the text
        Matricola.setBounds(500,250,200,30);
        
		fatto = new JLabel();
		fatto.setText("Presenza registrata con successo!");
		fatto.setBounds(500,170, 200,30);
		fatto.setForeground(Color.GREEN.darker());
		fatto.setVisible(false);
		
		indietro= new JButton("Indietro");
		indietro.setBounds(500,350,200,30);
		indietro.setBackground(Color.white);
		
		invia= new JButton("Invia");
		invia.setBounds(500,300,200,30);
		invia.setBackground(Color.white);
		
		menu = new JPanel();
		menu.setLayout(null);
		menu.setSize(1200,600);
		menu.setVisible(true);
		menu.add(Motivo);
		menu.add(iniziotxt);
		menu.add(Inizio);
		menu.add(finetxt);
		menu.add(Fine);
		menu.add(DataTXT);
		menu.add(Data2TXT);
		menu.add(errore);
		menu.add(fatto);
		menu.add(invia);
		menu.add(motivo);
		menu.add(indietro);
		menu.add(Matricola);
		menu.add(matricola);
		add(menu);
		invia.addActionListener(this::Invia);
	    indietro.addActionListener(this);	
	}
	
	public void Invia(ActionEvent e) {
		String Mot = Motivo.getText();
    	LocalDate In = LocalDate.parse(Inizio.getText(),dateFormat);
    	LocalDate Fi = LocalDate.parse(Fine.getText(),dateFormat);
		if(Mot.equals("")) {
			errore.setVisible(true);
		}
		else
		{
			RichiediAstensioneCTL richiediAstensione = new RichiediAstensioneCTL();
			if(richiediAstensione.richiediMalattiaAdmin(Integer.parseInt(Matricola.getText()), Motivo.getText(), In, Fi)) {
    			GestioneAssenza gestioneAssenza = new GestioneAssenza(In, Fi, Integer.parseInt(Matricola.getText()));
    			//gestioneAssenza.aggiungiIndisponibilita(this.utente.getMatricola(), In, Fi);
    			gestioneAssenza.aggiungiRichiestaMalattia(Integer.parseInt(Matricola.getText()), Motivo.getText(), "https://titanic", In, Fi);
    			gestioneAssenza.gestioneAssenza();
// QUI CONFERMA
    		} else {
    			//qua ci va l'errore
    			
    		}
			
			fatto.setVisible(true);
			errore.setVisible(false);
			matricola.setVisible(false);
			Matricola.setVisible(false);
			finetxt.setVisible(false);
			iniziotxt.setVisible(false);
			DataTXT.setVisible(false);
			Data2TXT.setVisible(false);
			
			Inizio.setVisible(false);
			Fine.setVisible(false);
			Motivo.setVisible(false);
			motivo.setVisible(false);
			motivo.setVisible(false);
			invia.setVisible(false);
			indietro.setBounds(500,200,200,50);
			
		}
		
	
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ModuloDashBoard dashboard = new ModuloDashBoard(utente);
        dashboard.setSize(1200,600);
        dashboard.setVisible(true);
    	dispose();
	}
}