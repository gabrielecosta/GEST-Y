package com.gest.GestionePresenza.Interface;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import com.gest.Entity.Turno;
import com.gest.Entity.Utente;
import com.gest.GestioneAccount.Interface.ModuloDashBoard;
import com.gest.GestionePresenza.Control.visualizzaPresenzaCtl;
import com.gest.GestioneTurno.Control.GestioneCalendarioCTL;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.lang.Exception;
public class ModuloVisualizzaPresenza extends JFrame implements ActionListener{
    
	private Utente utente;
	JPanel menu;
	JButton indietro;
	

	DefaultListModel<Turno> l1 = new DefaultListModel<Turno>();  
	JList<Turno> list = new JList<Turno>(l1); 
    JScrollPane listScroller = new JScrollPane(list);
    Turno turno;
    JLabel info;
	
	
	public ModuloVisualizzaPresenza(Utente utente){
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.utente = new Utente();
		this.utente = utente;
		this.turno = turno;
		

		visualizzaPresenzaCtl arraylista = new visualizzaPresenzaCtl();

		info = new JLabel();
		info.setText("Turni Firmati");      //set label value for textField1
		info.setBounds(530,50,300,50);
	    info.setForeground(Color.CYAN.darker());
	    info.setFont(new Font("Arial", Font.PLAIN, 30));
		
		indietro= new JButton("Indietro");
		indietro.setBounds(450,435,300,50);
		indietro.setBackground(Color.white);
	
		
	//	  DefaultListModel<String> l1 = new DefaultListModel<>(); 

		  
		for(Turno turno: arraylista.getListaTurniFirmati(this.utente.getMatricola()))
		{
			l1.addElement(turno);
		}
	    listScroller.setBounds(300,100,600,250);
		
		
		menu = new JPanel();
		menu.setLayout(null);
		menu.setSize(1200,600);
		menu.setVisible(true);
        menu.add(info);
		menu.add(listScroller);
		menu.add(indietro);
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
