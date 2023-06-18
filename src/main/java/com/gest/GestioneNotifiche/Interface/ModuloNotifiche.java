package com.gest.GestioneNotifiche.Interface;
import javax.swing.*;

import com.gest.Entity.Notifica;
import com.gest.Entity.Utente;
import com.gest.Entity.Utente_Notifica;
import com.gest.GestioneAccount.Interface.ModuloDashBoard;
import com.gest.GestioneNotifiche.Control.gestioneNotificheCtl;
import com.gest.GestioneTurno.Control.GestioneCalendarioCTL;

import java.awt.*;
import java.awt.event.*;
public class ModuloNotifiche extends JFrame implements ActionListener{
    
	private Utente utente;
	JPanel menu;
	JButton indietro, visualizza, indietro2;
	JLabel CentroNotifiche, Notifica,erroretxt;
	DefaultListModel<Utente_Notifica> l1 = new DefaultListModel<Utente_Notifica>();  
	JList<Utente_Notifica> list = new JList<Utente_Notifica>(l1); 
    JScrollPane listScroller = new JScrollPane(list);
    Utente_Notifica notifica;
    
	public ModuloNotifiche(Utente utente){
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.utente = new Utente();
		this.utente = utente;
		this.notifica = notifica;
		gestioneNotificheCtl arraylista = new gestioneNotificheCtl();
		for(Utente_Notifica notifica: arraylista.getListaNotifiche(this.utente.getMatricola()))
		{
			l1.addElement(notifica);
		}
	     listScroller.setBounds(200,100,800,300);
	     
	    CentroNotifiche = new JLabel("Centro Notifiche");
	    CentroNotifiche.setBounds(500,50,300,50);
	    CentroNotifiche.setForeground(Color.CYAN.darker());
	    CentroNotifiche.setFont(new Font("Arial", Font.PLAIN, 30));

	    erroretxt = new JLabel("Errore! Seleziona una notifica");
	    erroretxt.setBounds(520,395,200,50);
	    erroretxt.setVisible(false);
	    erroretxt.setForeground(Color.RED.darker());
	    
	    Notifica = new JLabel("");
	    Notifica.setBounds(450,200,500,50);
	    Notifica.setVisible(false);
	    Notifica.setFont(new Font("Arial", Font.PLAIN, 20));
	    
		visualizza = new JButton("Visualizza");
		visualizza.setBounds(450,440,300,50);
		visualizza.setBackground(Color.white);
		
		indietro = new JButton("Indietro");
		indietro.setBounds(450,500,300,50);
		indietro.setBackground(Color.white);
		
		indietro2 = new JButton("Indietro");
		indietro2.setBounds(450,450,300,50);
		indietro2.setBackground(Color.white);
		indietro2.setVisible(false);
		
		menu = new JPanel();
		menu.setLayout(null);
		menu.setSize(1200,600);
		menu.setVisible(true);
		menu.add(indietro);
		menu.add(indietro2);
		menu.add(CentroNotifiche);
		menu.add(visualizza);
		menu.add(listScroller);
		menu.add(Notifica);
		menu.add(erroretxt);
		add(menu);
	    indietro.addActionListener(this);	
	    visualizza.addActionListener(this::Visualizza);
	    indietro2.addActionListener(this::Indietro2);
	}
	private void Indietro2(ActionEvent e) {
		
		CentroNotifiche.setVisible(true);
		indietro.setVisible(true);
		listScroller.setVisible(true);
		visualizza.setVisible(true);
		indietro2.setVisible(false);
		Notifica.setVisible(false);
	}
	private void Visualizza(ActionEvent e) {
		
		if(list.isSelectionEmpty() == true) {
			erroretxt.setVisible(true);
		}
		else {

			this.notifica = list.getSelectedValue();
			CentroNotifiche.setVisible(false);
			indietro.setVisible(false);
			listScroller.setVisible(false);
			visualizza.setVisible(false);
			indietro2.setVisible(true);
			Notifica.setText(list.getSelectedValue().toString());
			Notifica.setVisible(true);
			erroretxt.setVisible(false);
			
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