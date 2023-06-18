package com.gest.GestioneSalario.Interface;
import javax.swing.*;

import com.gest.Entity.BustaPaga;
import com.gest.Entity.Turno;
import com.gest.Entity.Utente;
import com.gest.Entity.Utente_Notifica;
import com.gest.GestioneAccount.Interface.ModuloDashBoard;
import com.gest.GestionePresenza.Control.visualizzaPresenzaCtl;
import com.gest.GestioneSalario.Control.AccreditaSalarioCtl;
import com.gest.GestioneSalario.Control.GestioneSalarioCtl;
import com.gest.GestioneTurno.Control.GestioneCalendarioCTL;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;




@SuppressWarnings("serial")
public class ModuloSalario extends JFrame implements ActionListener{

	public JPanel menu;
	public JLabel formato,inizio,fine,DataTXT,errore,Titolo;
	public JButton Indietro,invia,indietro2;
	private Utente utente;
	
	DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	JFormattedTextField Inizio = new JFormattedTextField(format);
	JFormattedTextField Fine = new JFormattedTextField(format);

	 DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
 	String in,fi;
	int mese_inizio,anno_inizio,mese_fine,anno_fine = 0;
	String meseinizio;
	BustaPaga bustapaga;

	DefaultListModel<BustaPaga> l1 = new DefaultListModel<BustaPaga>();  
	JList<BustaPaga> list = new JList<BustaPaga>(l1); 
    JScrollPane listScroller = new JScrollPane(list);

	 
	public ModuloSalario(Utente utente){
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.utente = new Utente();
		this.utente = utente;
		
		Inizio.setBounds(500,200,200,30);
		Fine.setBounds(500,250,200,30);
		
	    Titolo = new JLabel("Salario da:" + " " + in + " " + "a" + " " + fi);
	    Titolo.setBounds(375,50,600,50);
	    Titolo.setForeground(Color.CYAN.darker());
	    Titolo.setFont(new Font("Arial", Font.PLAIN, 30));
	    Titolo.setVisible(false);
		
		DataTXT = new JLabel("Seleziona intervallo date:");
	    DataTXT.setBounds(500,150,200,50);
		
		inizio = new JLabel("Data Inizio:");
		inizio.setBounds(430,190,200,50);
	    
		fine = new JLabel("Data Fine:");
	    fine.setBounds(430,240,200,50);
		
		formato = new JLabel("FORMATO: DD/MM/YYYY");
	    formato.setBounds(500,165,200,50);
	    formato.setForeground(Color.GRAY.darker());
	    
		errore = new JLabel("Errore! Seleziona un Intervallo Date Corretto!");
	    errore.setBounds(500,115,200,50);
	    errore.setForeground(Color.RED.darker());
        errore.setVisible(false);
		invia= new JButton("Invia");
		invia.setBounds(500,300,200,50);
		invia.setBackground(Color.white);
	    
		Indietro= new JButton("Indietro");
		Indietro.setBounds(500,450,200,50);
		Indietro.setBackground(Color.white);

		indietro2 = new JButton("Indietro");
		indietro2.setBounds(500,450,200,50);
		indietro2.setBackground(Color.white);
		indietro2.setVisible(false);
	 
		/*
		 * GestioneSalarioCtl arraylista = new GestioneSalarioCtl();
		for(BustaPaga bustapaga: arraylista.getBustePagaImp(this.utente.getMatricola())) {
			l1.addElement(bustapaga);
		}
		 */
		
	    //listScroller.setBounds(100,50,1000,500);
	    //listScroller.setVisible(true);
	 menu = new JPanel();
	 menu.setLayout(null);
	 menu.setSize(1200,600);
	 menu.add(Indietro);
	 menu.add(indietro2);
	 menu.add(Fine);
	 menu.add(Inizio);
	 menu.add(DataTXT);
	 menu.add(fine);
	 menu.add(formato);
	 menu.add(inizio);
	 menu.add(errore);
	 menu.add(invia);
	 menu.add(listScroller);
	 menu.add(Titolo);
	 add(menu);
	 
	 menu.setVisible(true);
	 Indietro.addActionListener(this::indietro);
	 indietro2.addActionListener(this::indietro2);
	 invia.addActionListener(this::Invia);
	 
	}
	private void indietro2(ActionEvent e) {
		Fine.setVisible(true);
		Inizio.setVisible(true);
		DataTXT.setVisible(true);
		fine.setVisible(true);
		formato.setVisible(true);
		inizio.setVisible(true);
		errore.setVisible(false);
		listScroller.setVisible(false);
		invia.setVisible(true);
		indietro2.setVisible(false);
		Indietro.setVisible(true);
		Titolo.setVisible(false);
		l1.clear();
		
	}
    private void Invia(ActionEvent e) {
    	GestioneSalarioCtl arraylista = new GestioneSalarioCtl();
    	
    	LocalDate In = LocalDate.parse(Inizio.getText(),dateFormat);
    	LocalDate Fi = LocalDate.parse(Fine.getText(),dateFormat);

    	in = Inizio.getText();
    	fi = Fine.getText();
    	if(in.equals(null)){
    		errore.setVisible(true);
    	}
    	else if(fi.equals(null)){
    		errore.setVisible(true);
    	}
    	else if(In.isAfter(Fi)) {
    		errore.setVisible(true);
    	}
    	else {
    		
    		Fine.setVisible(false);
    		Inizio.setVisible(false);
    		DataTXT.setVisible(false);
    		fine.setVisible(false);
    		formato.setVisible(false);
    		inizio.setVisible(false);
    		errore.setVisible(false);
    		
    		listScroller.setVisible(true);
    		invia.setVisible(false);
    		indietro2.setVisible(true);
    		Indietro.setVisible(false);
        	in = Inizio.getText();
        	fi = Fine.getText();
    		Titolo.setVisible(true);
    		
    	    mese_inizio = In.getMonthValue();
    	    anno_inizio = In.getYear();
    	    mese_fine = Fi.getMonthValue();
    	    anno_fine = Fi.getYear();
    	    
    	    Titolo.setText("Buste paga da:" + " " + in + " " + "a" + " " + fi);
    	    
    	    
    		for(BustaPaga bustapaga: arraylista.getBustePaga(this.utente.getMatricola(), mese_inizio, anno_inizio, mese_fine, anno_fine))
    		{
    			l1.addElement(bustapaga);
    		}
    		listScroller.setVisible(true);
   	     listScroller.setBounds(200,100,800,300);
    		
    		
    	}
    }
    
	private void indietro(ActionEvent ActionEvent) {
   	 ModuloDashBoard dash = new ModuloDashBoard(this.utente);
   	dash.setSize(1200, 600);  //set size of the frame
   	dash.setVisible(true);  //make form visible to the user
	 	dispose();
	 	
	 	
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
