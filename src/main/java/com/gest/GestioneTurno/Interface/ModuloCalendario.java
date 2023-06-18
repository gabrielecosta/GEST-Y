package com.gest.GestioneTurno.Interface;
import javax.swing.*;

import com.gest.Entity.Turno;
import com.gest.Entity.Utente;
import com.gest.GestioneAccount.Interface.ModuloDashBoard;
import com.gest.GestioneTurno.Control.GestioneCalendarioCTL;

import java.awt.*;
import java.awt.event.*;

public class ModuloCalendario extends JFrame implements ActionListener{
    
	private Utente utente;
	JPanel menu;
	JButton indietro,roll;
	JLabel erroretxt,inviatotxt,info;
	Turno testo;
	

	DefaultListModel<Turno> l1 = new DefaultListModel<Turno>();  
	JList<Turno> list = new JList<Turno>(l1); 
    JScrollPane listScroller = new JScrollPane(list);
    Turno turno;
	
	
	public ModuloCalendario(Utente utente){
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.utente = new Utente();
		this.utente = utente;
		this.turno = turno;
		

		GestioneCalendarioCTL arraylista = new GestioneCalendarioCTL();

		info = new JLabel();
		info.setText("Seleziona un turno:");      //set label value for textField1
		info.setBounds(450,75, 250,30);
		
		erroretxt = new JLabel();
		erroretxt.setText("Errore! Devi prima selezionare un turno!");      //set label value for textField1
		erroretxt.setBounds(450,350, 250,30);
		erroretxt.setVisible(false);
		erroretxt.setForeground(Color.RED.darker());
		
		inviatotxt = new JLabel();
		inviatotxt.setText("Rol effettuato per: " + testo);      //set label value for textField1
        inviatotxt.setBounds(450,350, 400,30);
        inviatotxt.setVisible(false);
        inviatotxt.setForeground(Color.GREEN.darker());

		roll= new JButton("Rol");
		roll.setBounds(450,380,300,50);
		roll.setBackground(Color.white);
		
		indietro= new JButton("Indietro");
		indietro.setBounds(450,435,300,50);
		indietro.setBackground(Color.white);
	
		
	//	  DefaultListModel<String> l1 = new DefaultListModel<>(); 

		  
		for(Turno turno: arraylista.getListaTurni(this.utente.getMatricola()))
		{
			l1.addElement(turno);
		}
		// JList<String> list = new JList<>(l1);  
	 //    JScrollPane listScroller = new JScrollPane(list);
	     listScroller.setBounds(450,100,300,250);
	     
	       //list.setBounds(400,100, 400,200);           
		   //list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		   //list.setLayoutOrientation(JList.VERTICAL);
		   //listScroller.setPreferredSize(new Dimension(400, 200));
		   testo = list.getSelectedValue();
		menu = new JPanel();
		menu.setLayout(null);
		menu.setSize(1200,600);
		menu.setVisible(true);
        menu.add(erroretxt);
        menu.add(inviatotxt);
        menu.add(roll);
        menu.add(info);
		menu.add(listScroller);
		menu.add(indietro);
		add(menu);
	    indietro.addActionListener(this);	
	    roll.addActionListener(this::Roll);
	    
	}
	public void Roll(ActionEvent e) {
		
		this.turno = list.getSelectedValue();
		
		if(list.isSelectionEmpty() == true) {
			inviatotxt.setVisible(false);
			erroretxt.setVisible(true);
		}
		else {
			ModuloRol modulo = new ModuloRol(this.utente,list.getSelectedValue().toString(),this.turno);
			modulo.setSize(1200,600);
			modulo.setVisible(true);
			dispose();
			
			
		}
		
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