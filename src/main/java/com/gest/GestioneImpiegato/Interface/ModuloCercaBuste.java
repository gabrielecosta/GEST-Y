package com.gest.GestioneImpiegato.Interface;
import javax.swing.*;

import com.gest.Entity.BustaPaga;
import com.gest.Entity.Impiegato;
import com.gest.Entity.Turno;
import com.gest.Entity.Utente;
import com.gest.GestioneSalario.Control.GestioneSalarioCtl;
import com.gest.GestioneTurno.Control.GestioneCalendarioCTL;

import java.awt.*;
import java.awt.event.*;

public class ModuloCercaBuste extends JFrame implements ActionListener{
    
	Impiegato impiegato;
	private Utente utente;
	JPanel menu;
	JButton indietro;
	JLabel titolo;
	
	DefaultListModel<BustaPaga> l1 = new DefaultListModel<BustaPaga>();  
	JList<BustaPaga> list = new JList<BustaPaga>(l1); 
    JScrollPane listScroller = new JScrollPane(list);
	Turno turno;
	ModuloCercaBuste(Utente utente, Impiegato impiegato){
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.utente = new Utente();
		this.utente = utente;
		this.impiegato = new Impiegato();
		this.impiegato = impiegato;
		
		GestioneSalarioCtl arraylista = new GestioneSalarioCtl();
		for(BustaPaga bustapaga: arraylista.getBustePagaImp(this.impiegato.getMatricola()))
		{
			l1.addElement(bustapaga);
		}
	    listScroller.setBounds(200,100,800,300);
	     
	    titolo = new JLabel("BustePaga per l'impiegato " + this.impiegato.getMatricola());
	    titolo.setBounds(500,50,300,50);
	    titolo.setForeground(Color.CYAN.darker());
	    titolo.setFont(new Font("Arial", Font.PLAIN, 30));
	
		indietro= new JButton("Indietro");
		indietro.setBounds(450,450,300,50);
		indietro.setBackground(Color.white);
		
		menu = new JPanel();
		menu.setLayout(null);
		menu.setSize(1200,600);
		menu.setVisible(true);
		menu.add(indietro);
		menu.add(titolo);
		menu.add(listScroller);
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