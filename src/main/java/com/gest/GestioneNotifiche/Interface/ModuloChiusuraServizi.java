package com.gest.GestioneNotifiche.Interface;
import javax.swing.*;

import com.gest.Entity.Notifica;
import com.gest.Entity.Turno;
import com.gest.Entity.Utente;
import com.gest.Entity.Utente_Notifica;
import com.gest.GestioneAccount.Interface.ModuloDashBoard;
import com.gest.GestioneNotifiche.Control.gestioneNotificheCtl;
import com.gest.GestioneTurno.Control.GestioneCalendarioCTL;

import java.awt.*;
import java.awt.event.*;

public class ModuloChiusuraServizi extends JFrame implements ActionListener{
    
	Utente_Notifica notifica;
	private Utente utente;
	JPanel menu;
	JButton indietro;
	JLabel CentroNotifiche;
	DefaultListModel<Utente_Notifica> l1 = new DefaultListModel<Utente_Notifica>();  
	JList<Utente_Notifica> list = new JList<Utente_Notifica>(l1); 
    JScrollPane listScroller = new JScrollPane(list);
    
	public ModuloChiusuraServizi(Utente utente){
		
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.utente = new Utente();
		this.utente = utente;
		
		gestioneNotificheCtl arraylista = new gestioneNotificheCtl();
		for(Utente_Notifica notifica: arraylista.getListaChiusuraServizi())
		{
			l1.addElement(notifica);
		}
	     listScroller.setBounds(200,100,800,300);
	     
	    CentroNotifiche = new JLabel("Notifiche Chiusure Servizi");
	    CentroNotifiche.setBounds(500,50,350,50);
	    CentroNotifiche.setForeground(Color.CYAN.darker());
	    CentroNotifiche.setFont(new Font("Arial", Font.PLAIN, 30));
	
		indietro= new JButton("Indietro");
		indietro.setBounds(450,450,300,50);
		indietro.setBackground(Color.white);
		
		menu = new JPanel();
		menu.setLayout(null);
		menu.setSize(1200,600);
		menu.setVisible(true);
		menu.add(indietro);
		menu.add(CentroNotifiche);
		menu.add(listScroller);
		add(menu);
	    indietro.addActionListener(this);	
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