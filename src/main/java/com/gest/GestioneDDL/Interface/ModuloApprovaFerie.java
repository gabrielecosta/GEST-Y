package com.gest.GestioneDDL.Interface;
import javax.swing.*;

import com.gest.Entity.Richiesta;
import com.gest.Entity.Turno;
import com.gest.Entity.Utente;
import com.gest.GestioneAccount.Interface.ModuloDashBoard;
import com.gest.GestioneDDL.Control.gestioneFerieCtl;
import com.gest.GestioneTurno.Control.GestioneCalendarioCTL;

import java.awt.*;
import java.awt.event.*;

public class ModuloApprovaFerie extends JFrame implements ActionListener{
    
	private Utente utente;
	JPanel menu;
	JButton indietro,approva,disapprova;
	JLabel vuoto,fatto,nonfatto,errore;
	

	DefaultListModel<Richiesta> l1 = new DefaultListModel<Richiesta>();  
	JList<Richiesta> list = new JList<Richiesta>(l1); 
    JScrollPane listScroller = new JScrollPane(list);
    Richiesta richiesta;
    JLabel info;
	
	
    public ModuloApprovaFerie(Utente utente){
    	setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.utente = new Utente();
		this.utente = utente;
		this.richiesta = richiesta;
		

		gestioneFerieCtl arraylista = new gestioneFerieCtl();
		
		errore = new JLabel();
		errore.setText("Errore! Seleziona prima un impiegato!");
		errore.setBounds(500,340,250,50);
		errore.setForeground(Color.red.darker());
		errore.setVisible(false);
		
        fatto = new JLabel("Ferie approvate per:");
		fatto.setBounds(450,340,150,50);;
		fatto.setVisible(false);
		fatto.setForeground(Color.green.darker());
		
        nonfatto = new JLabel("Ferie non approvate per:");		
		nonfatto.setBounds(450,340,150,50);;
		nonfatto.setVisible(false);
		nonfatto.setForeground(Color.yellow.darker());
		
		vuoto = new JLabel("Non ci sono impiegati da pagare!");
		vuoto.setForeground(Color.red.darker());
		vuoto.setBounds(400,280,300,30);
		vuoto.setVisible(false);
		
		info = new JLabel();
		info.setText("Ferie da approvare");      //set label value for textField1
		info.setBounds(480,50,300,50);
	    info.setForeground(Color.CYAN.darker());
	    info.setFont(new Font("Arial", Font.PLAIN, 30));
		
		indietro= new JButton("Indietro");
		indietro.setBounds(450,500,300,50);
		indietro.setBackground(Color.white);
	
		approva= new JButton("Approva");
		approva.setBounds(450,440,150,50);
		approva.setBackground(Color.white);
		
		disapprova= new JButton("Rifiuta");
		disapprova.setBounds(610,440,150,50);
		disapprova.setBackground(Color.white);
		
	//	  DefaultListModel<String> l1 = new DefaultListModel<>(); 

		  
		for(Richiesta richiesta: arraylista.getListaFerie())
		{
			l1.addElement(richiesta);
		}
	    listScroller.setBounds(300,100,600,250);
		
		
		menu = new JPanel();
		menu.setLayout(null);
		menu.setSize(1200,600);
		menu.setVisible(true);
        menu.add(info);
		menu.add(listScroller);
		menu.add(approva);
		menu.add(disapprova);
		menu.add(vuoto);
		menu.add(indietro);
		menu.add(errore);
		menu.add(fatto);
		menu.add(nonfatto);
		add(menu);
	    indietro.addActionListener(this);	
	    approva.addActionListener(this::Approva);
	    disapprova.addActionListener(this::Rifiuta);
	    
	}
	private void Approva (ActionEvent e) {
		
		if(list.isSelectionEmpty() == true) {
			errore.setVisible(true);
		}
		else {
			gestioneFerieCtl ferie = new gestioneFerieCtl();
			fatto = new JLabel("Ferie approvate per:" + " " + list.getSelectedValue().toString());
			fatto.setVisible(true);
			nonfatto.setVisible(false);
			errore.setVisible(false);
			ferie.approvaFerie(list.getSelectedValue());
		}
	
		
		
	}
	private void Rifiuta (ActionEvent e) {
		
		if(list.isSelectionEmpty() == true) {
			errore.setVisible(true);
		}
		else {
			gestioneFerieCtl ferie = new gestioneFerieCtl();
			nonfatto = new JLabel("Ferie non approvate per:" + " " + list.getSelectedValue().toString());
			fatto.setVisible(false);
			nonfatto.setVisible(true);
			errore.setVisible(false);
			ferie.rifiutaFerie(list.getSelectedValue());

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
