package com.gest.GestioneImpiegato.Interface;
import javax.swing.*;

import com.gest.Entity.Impiegato;
import com.gest.Entity.Turno;
import com.gest.Entity.Utente;
import com.gest.GestioneAccount.Interface.ModuloDashBoard;
import com.gest.GestioneImpiegato.Control.cercaImpiegatoCtl;
import com.gest.GestioneTurno.Control.GestioneCalendarioCTL;

import java.awt.*;
import java.awt.event.*;

public class ModuloCerca extends JFrame implements ActionListener{
    Turno turno;
	private Utente utente;
	JPanel menu;
	JTextField textfield,textfield2;
	JLabel testo,testo2,errore;
	JButton indietro,cerca,Turni,Buste,Dati,Modifica;
	
	DefaultListModel<Impiegato> l1 = new DefaultListModel<Impiegato>();  
	JList<Impiegato> list = new JList<Impiegato>(l1); 
    JScrollPane listScroller = new JScrollPane(list);
    Impiegato impiegato;
	
	public ModuloCerca(Utente utente){
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.utente = new Utente();
		this.utente = utente;
		this.impiegato = new Impiegato();	//this.impiegato = impiegato;
		
        textfield = new JTextField(15);    //set length of the text
        textfield.setBounds(370,50, 200,30);
        
        textfield2 = new JTextField(15);    //set length of the text
        textfield2.setBounds(715,50, 200,30);
		
		errore = new JLabel();
		errore.setText("Errore! Seleziona prima un impiegato!");
		errore.setBounds(495,455,300,50);
		errore.setForeground(Color.red.darker());
		errore.setVisible(false);
        
		testo = new JLabel();
		testo.setText("Inserisci Nome:");
		testo.setBounds(275,50,180,30);
		
		testo2 = new JLabel();
		testo2.setText("Inserisci Cognome:");
		testo2.setBounds(600,50,180,30);

        ImageIcon search = new ImageIcon("cerca2.png");
		cerca= new JButton(search);
		cerca.setBounds(920,50,30,30);
		cerca.setBackground(Color.white);
        cerca.setContentAreaFilled(false);
   //     cerca.setBorder(null);
		
		Turni= new JButton("Turni Impiegato");
		Turni.setBounds(270,430,150,30);
		Turni.setBackground(Color.white);
		Turni.setVisible(false);
		
		Buste= new JButton("Buste Paga Impiegato");
		Buste.setBounds(440,430,150,30);
		Buste.setBackground(Color.white);
		Buste.setVisible(false);
		
		Dati= new JButton("Dati Impiegato");
		Dati.setBounds(610,430,150,30);
		Dati.setBackground(Color.white);
		Dati.setVisible(false);
		
		Modifica= new JButton("Modifica Dati Impiegato");
		Modifica.setBounds(780,430,150,30);
		Modifica.setBackground(Color.white);
		Modifica.setVisible(false);
		
		indietro= new JButton("Indietro");
		indietro.setBounds(450,500,300,50);
		indietro.setBackground(Color.white);
		
        cercaImpiegatoCtl arraylista = new cercaImpiegatoCtl ();
		for(Impiegato impiegato: arraylista.getListaImpiegatiFiltrata(textfield.getText(),textfield2.getText()))
		{
			l1.addElement(impiegato);
		}
	     listScroller.setBounds(200,100,800,300);
         listScroller.setVisible(false);		
 	    
		menu = new JPanel();
		menu.setLayout(null);
		menu.setSize(1200,600);
		menu.setVisible(true);
		menu.add(testo);
		menu.add(listScroller);
		menu.add(textfield);
		menu.add(cerca);
		menu.add(indietro);
		menu.add(testo2);
		menu.add(Turni);
		menu.add(Buste);
		menu.add(Dati);
		menu.add(errore);
		menu.add(Modifica);
		menu.add(textfield2);
		add(menu);
	    indietro.addActionListener(this);	
	    cerca.addActionListener(this::Cerca);
	    Modifica.addActionListener(this::Modifica);
	    Dati.addActionListener(this::Dati);
	    Turni.addActionListener(this::Turni);
	    Buste.addActionListener(this::Buste);
	}
	
	//modifica dati
	public void Modifica(ActionEvent e) {
		this.impiegato = list.getSelectedValue();
		if(list.isSelectionEmpty() == true) {
			errore.setVisible(true);
		}
		else {
			ModuloCercaModifica modulo = new ModuloCercaModifica(this.utente,list.getSelectedValue().toString(),this.impiegato);
			modulo.setSize(1200,600);
			modulo.setVisible(true);
			dispose();
		}
	}
	
	
	public void Dati(ActionEvent e) {
		if(list.isSelectionEmpty() == true) {
			errore.setVisible(true);
		}
		else {
			this.impiegato = list.getSelectedValue();
			ModuloCercaDati modulo = new ModuloCercaDati(this.utente,list.getSelectedValue().toString(),this.impiegato);
			modulo.setSize(1200,600);
			modulo.setVisible(true);
			dispose();
		}
	}
	
	//visualizza turni
	public void Turni(ActionEvent e) {
		if(list.isSelectionEmpty() == true) {
			errore.setVisible(true);
		}
		else {
			this.impiegato = list.getSelectedValue();
			ModuloCercaTurni modulo = new ModuloCercaTurni(this.utente, list.getSelectedValue().toString(),this.impiegato);
			modulo.setSize(1200,600);
			modulo.setVisible(true);
			dispose();
		}
	}
	
	//visualizza buste paga
	public void Buste(ActionEvent e) {
		if(list.isSelectionEmpty() == true) {
			errore.setVisible(true);
		}
		else {
			this.impiegato = list.getSelectedValue();
			ModuloCercaBuste modulo = new ModuloCercaBuste(this.utente,this.impiegato);
			modulo.setSize(1200,600);
			modulo.setVisible(true);
			dispose();
		}
	}
	
	
	public void Cerca(ActionEvent e) {
		l1.clear();
		cercaImpiegatoCtl arraylista = new cercaImpiegatoCtl ();
		for(Impiegato impiegato: arraylista.getListaImpiegatiFiltrata(textfield.getText(),textfield2.getText()))
		{
			l1.addElement(impiegato);
		}
		
		   listScroller.setVisible(true);
		   Modifica.setVisible(true);
		   Dati.setVisible(true);
		   Turni.setVisible(true);
		   Buste.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ModuloDashBoard dashboard = new ModuloDashBoard(utente);
        dashboard.setSize(1200,600);
        dashboard.setVisible(true);
    	dispose();
	}

	
	
	
}