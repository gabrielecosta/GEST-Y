package com.gest.GestioneImpiegato.Interface;
import javax.swing.*;

import com.gest.Entity.Impiegato;
import com.gest.Entity.Turno;
import com.gest.Entity.Utente;
import com.gest.GestioneTurno.Control.GestioneCalendarioCTL;


import java.awt.*;
import java.awt.event.*;

public class ModuloCercaTurni extends JFrame implements ActionListener{
    
	private Utente utente;
	private Impiegato impiegato;
	JLabel titolo;
	JLabel erroretxt,inviatotxt;
	JPanel menu;
	JButton indietro, roll;
	
	DefaultListModel<Turno> l1 = new DefaultListModel<Turno>();  
	JList<Turno> list = new JList<Turno>(l1); 
    JScrollPane listScroller = new JScrollPane(list);
	Turno turno;
	
	ModuloCercaTurni(Utente utente, String id, Impiegato impiegato){
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.utente = new Utente();
		this.utente = utente;
		this.impiegato = new Impiegato();
		this.impiegato = impiegato;
		
		GestioneCalendarioCTL arraylista = new GestioneCalendarioCTL();
		for(Turno turno1: arraylista.getListaTurni(this.impiegato.getMatricola()))
		{
			l1.addElement(turno1);
		}
	     listScroller.setBounds(200,100,800,300);
	     
	    titolo = new JLabel("Lista Turni per l'impiegato " + this.impiegato.getMatricola());
	    titolo.setBounds(500,50,300,50);
	    titolo.setForeground(Color.CYAN.darker());
	    titolo.setFont(new Font("Arial", Font.PLAIN, 30));
	
		indietro= new JButton("Indietro");
		indietro.setBounds(450,450,300,50);
		indietro.setBackground(Color.white);
		
		roll= new JButton("Rol");
		roll.setBounds(450,400,300,50);
		roll.setBackground(Color.white);
		
		erroretxt = new JLabel();
		erroretxt.setText("Errore! Devi prima selezionare un turno!");      //set label value for textField1
		erroretxt.setBounds(450,350, 250,30);
		erroretxt.setVisible(false);
		erroretxt.setForeground(Color.RED.darker());
		
		inviatotxt = new JLabel();
		inviatotxt.setText("Rol effettuato");      //set label value for textField1
        inviatotxt.setBounds(450,350, 400,30);
        inviatotxt.setVisible(false);
        inviatotxt.setForeground(Color.GREEN.darker());
        
		
		menu = new JPanel();
		menu.setLayout(null);
		menu.setSize(1200,600);
		menu.setVisible(true);
		menu.add(indietro);
		menu.add(titolo);
		menu.add(listScroller);
		menu.add(roll);
		menu.add(erroretxt);
        menu.add(inviatotxt);
		add(menu);
	    indietro.addActionListener(this);
	    roll.addActionListener(this::Roll);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ModuloCerca modulo = new ModuloCerca(this.utente);
		modulo.setSize(1200,600);
		modulo.setVisible(true);
		dispose();
	}

	
		public void Roll(ActionEvent e) {
			this.turno = list.getSelectedValue();
		
			if(list.isSelectionEmpty() == true) {
				inviatotxt.setVisible(false);
				erroretxt.setVisible(true);
			}
			else {
				ModuloROL modulo = new ModuloROL(this.utente,list.getSelectedValue().toString(),this.turno);
				modulo.setSize(1200,600);
				modulo.setVisible(true);
				dispose();		
			
		}
		
	}	
	
}