package com.gest.GestionePresenza.Interface;
import javax.swing.*;

import com.gest.Entity.Turno;
import com.gest.Entity.Utente;
import com.gest.GestioneAccount.Interface.ModuloDashBoard;
import com.gest.GestionePresenza.Control.ingressoRemotoCtl;

import java.awt.*;
import java.awt.event.*;
public class ModuloPresenza extends JFrame implements ActionListener{
    
	private Utente utente;
	JPanel menu;
	JButton indietro,invia;
	JLabel errore,fatto,motivo,matricola,errore2;
	JTextField Motivo,Matricola;
	
	public ModuloPresenza(Utente utente){
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.utente = new Utente();
		this.utente = utente;

        Motivo = new JTextField(15);    //set length of the text
        Motivo.setBounds(500,200,200,30);
		
		motivo = new JLabel();
		motivo.setText("Motivo Ritardo:");
        motivo.setBounds(400,200,200,30);
        
		errore = new JLabel();
		errore.setText("Errore! Inserire Motivazione");
		errore.setBounds(500,170, 200,30);
		errore.setForeground(Color.red.darker());
		errore.setVisible(false);
		
		errore2 = new JLabel();
		errore2.setText("Errore! Non ci sono turni da firmare!");
		errore2.setBounds(500,170, 250,30);
		errore2.setForeground(Color.red.darker());
		errore2.setVisible(false);
        
		fatto = new JLabel();
		fatto.setText("Presenza registrata con successo!");
		fatto.setBounds(500,170, 200,30);
		fatto.setForeground(Color.GREEN);
		fatto.setVisible(false);
		
		indietro= new JButton("Indietro");
		indietro.setBounds(500,300,200,30);
		indietro.setBackground(Color.white);
		
		invia= new JButton("Invia");
		invia.setBounds(500,250,200,30);
		invia.setBackground(Color.white);
		
		menu = new JPanel();
		menu.setLayout(null);
		menu.setSize(1200,600);
		menu.setVisible(true);
		menu.add(Motivo);
		menu.add(errore);
		menu.add(errore2);
		menu.add(fatto);
		menu.add(invia);
		menu.add(indietro);
		menu.add(motivo);
		add(menu);
		invia.addActionListener(this::Invia);
	    indietro.addActionListener(this);	
	}
	
	public void Invia(ActionEvent e) {
		String Mot = Motivo.getText();
		if(Mot.equals("")) {
			errore.setVisible(true);
			errore2.setVisible(false);
		}
		else
		{
			ingressoRemotoCtl ingresso = new ingressoRemotoCtl();
			Turno turno = new Turno();
			turno = ingresso.getTurno(this.utente.getMatricola());
			//se non è in turno:
			boolean inTurno = !(ingresso.isPresente(turno));
			
			
			if(inTurno == false) {
				Errore();
			}
			else {
				ingresso.firmaIngresso(turno, this.utente.getMatricola(), Motivo.getText());
				boolean flag = ingresso.getFlag();
				if (flag) {
					Conferma();
					//cofnerma
				} else {
					//errore
					Errore();
				}
				
			}
			

		}

		
		
	}

	
	public void Conferma() {
		this.errore2.setVisible(false);
		this.fatto.setVisible(true);
		this.errore.setVisible(false);
		this.Motivo.setVisible(false);
		this.motivo.setVisible(false);
		this.invia.setVisible(false);
		this.indietro.setBounds(500,200,200,50);
	}
	public void Errore() {
		this.errore2.setVisible(true);
		this.errore.setVisible(false);
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