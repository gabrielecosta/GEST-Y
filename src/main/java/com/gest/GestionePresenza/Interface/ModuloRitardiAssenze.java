package com.gest.GestionePresenza.Interface;
import javax.swing.*;

import com.gest.Entity.Notifica;
import com.gest.Entity.Turno_ritardo;
import com.gest.Entity.Utente;
import com.gest.Entity.Utente_Notifica;
import com.gest.GestioneAccount.Interface.ModuloDashBoard;
import com.gest.GestioneNotifiche.Control.gestioneNotificheCtl;
import com.gest.GestioneTurno.Control.GestioneCalendarioCTL;

import java.awt.*;
import java.awt.event.*;
public class ModuloRitardiAssenze extends JFrame implements ActionListener{
    
	private Utente utente;
	JPanel menu;
	JButton indietro;
	JLabel CentroNotifiche;
	DefaultListModel<Turno_ritardo> l1 = new DefaultListModel<Turno_ritardo>();  
	JList<Turno_ritardo> list = new JList<Turno_ritardo>(l1); 
    JScrollPane listScroller = new JScrollPane(list);
    Utente_Notifica notifica;
	public ModuloRitardiAssenze(Utente utente){
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.utente = new Utente();
		this.utente = utente;
		
		gestioneNotificheCtl arraylista = new gestioneNotificheCtl();
		for(Turno_ritardo notifica: arraylista.getListaMotivazioni())
		{
			l1.addElement(notifica);
		}
	     listScroller.setBounds(200,100,800,300);
	     
	    CentroNotifiche = new JLabel("Ritardi/Assenze:");
	    CentroNotifiche.setBounds(500,50,300,50);
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