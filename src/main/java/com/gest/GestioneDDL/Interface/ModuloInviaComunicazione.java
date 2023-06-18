package com.gest.GestioneDDL.Interface;
import javax.swing.*;

import com.gest.Entity.Utente;
import com.gest.GestioneAccount.Control.ModificaPasswordControl;
import com.gest.GestioneAccount.Interface.ModuloAccount;
import com.gest.GestioneAccount.Interface.ModuloDashBoard;
import com.gest.GestioneImpiegato.Control.gestioneImpiegatoCtl;
import com.gest.GestioneNotifiche.Control.gestioneComunicazioniCtl;

import java.awt.*;
import java.awt.event.*;
public class ModuloInviaComunicazione extends JFrame implements ActionListener{
    

	  JPanel menu_password;
	  JButton inviapassword,indietropassword;
	  JLabel nuovapassword,confermapassword,errore1,errore2,errore3,errore4,conferma;
	  final JTextField nuovapasswordField,confermapasswordField;
	  private Utente utente;
	  public ModuloInviaComunicazione(Utente utente){
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.utente = new Utente();
		this.utente = utente;
		conferma = new JLabel("Comunicazione Inviata");
		conferma.setBounds(540,230,200,30);
		conferma.setForeground(Color.GREEN.darker());
		conferma.setVisible(false);
		
		errore1 = new JLabel("Errore! Campi Vuoti!");
		errore1.setBounds(550,120,200,30);
		errore1.setForeground(Color.RED.darker());
		errore1.setVisible(false);
		
		errore2 = new JLabel("Errore! Inserire una matricola!");
		errore2.setBounds(550,120,250,30);
		errore2.setForeground(Color.RED.darker());
		errore2.setVisible(false);

		errore3 = new JLabel("Errore! Inserire una descrizione!");
		errore3.setBounds(550,120,200,30);
		errore3.setForeground(Color.RED.darker());
		errore3.setVisible(false);
		
		errore4 = new JLabel("Errore! Inserire una matricola valida!");
		errore4.setBounds(550,120,250,30);
		errore4.setForeground(Color.RED.darker());
		errore4.setVisible(false);
		
      inviapassword = new JButton("Invio"); //set label to button
      inviapassword.setBounds(500, 260, 200, 60);
      inviapassword.setBackground(Color.white);
      
      indietropassword = new JButton("Indietro"); //set label to button
      indietropassword.setBounds(500,330, 200,60);
		indietropassword.setBackground(Color.white);
		
  	nuovapassword = new JLabel();
      nuovapassword.setText("Matricola:");      //set label value for textField2
      nuovapassword.setBounds(440,150, 200,30);
		
		nuovapasswordField = new JTextField(10);    //set length for the password
	    nuovapasswordField.setBounds(550,150, 200,30);
	        
	    confermapassword = new JLabel();
	        	     
	    confermapassword.setText("Descrizione:");      //set label value for textField2
	    confermapassword.setBounds(440,200, 200,30);
	    	
	    confermapasswordField = new JTextField(15);    //set length of the text
	    confermapasswordField.setBounds(550,200, 200,30);
		
      menu_password = new JPanel();
      menu_password.setLayout(null);
      menu_password.setSize(1200,600);
      add(menu_password);
      menu_password.add(inviapassword);
      menu_password.add(indietropassword);
      menu_password.add(nuovapassword);        
      menu_password.add(nuovapasswordField);
      menu_password.add(confermapassword);
      menu_password.add(confermapasswordField);
      menu_password.add(confermapassword);
      menu_password.add(errore1);
      menu_password.add(errore2);
      menu_password.add(errore3);
      menu_password.add(errore4);
      menu_password.add(conferma);
      indietropassword.addActionListener(this);
      inviapassword.addActionListener(this::invios);
      menu_password.setVisible(true);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	   	 ModuloDashBoard dash = new ModuloDashBoard(this.utente);
	    	dash.setSize(1200, 600);  //set size of the frame
	    	dash.setVisible(true);  //make form visible to the user
	 	 	dispose();
	}
	
  public void invios(ActionEvent e) {
  
  	String matricola = nuovapasswordField.getText();
  	String descrizione = confermapasswordField.getText();
  	gestioneImpiegatoCtl gestione = new gestioneImpiegatoCtl();
  	if(matricola.equals("") & descrizione.equals("")) {	
  		conferma.setVisible(false);
        errore1.setVisible(true);
        errore2.setVisible(false);
        errore3.setVisible(false);
  	}
  	else if(matricola.equals("")){
        errore1.setVisible(false);
        errore2.setVisible(true);
        errore3.setVisible(false);
  		conferma.setVisible(false);
  	}
  	else if (descrizione.equals("")) {
        errore1.setVisible(false);
        errore2.setVisible(false);
        errore3.setVisible(true);
  		conferma.setVisible(false);
  
  	} else if(!gestione.existsImpiegato(Integer.parseInt(matricola))) {
  		//impiegato non esiste
  		errore4.setVisible(true);
  	} else{
  		//impiegato esiste
  		gestioneComunicazioniCtl gestioneCom = new gestioneComunicazioniCtl();
  		gestioneCom.inserisciComunicazione(Integer.parseInt(matricola), descrizione);
  		indietropassword.setBounds(500, 260, 200, 60);
  		inviapassword.setVisible(false);
  		nuovapassword.setVisible(false);
  		nuovapasswordField.setVisible(false);
  		confermapassword.setVisible(false);
  		confermapasswordField.setVisible(false);
        errore1.setVisible(false);
        errore2.setVisible(false);
        errore3.setVisible(false);
  		conferma.setVisible(true);
  		
  		
  		
  	}
  	
  	
  	
  	
  	
  }
	
	
	
}
