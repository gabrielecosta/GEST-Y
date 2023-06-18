package com.gest.GestioneAccount.Interface;
import javax.swing.*;

import com.gest.Entity.Utente;
import com.gest.GestioneAccount.Control.ModificaDatiControl;

import java.awt.*;
import java.awt.event.*;

public class ModuloModificaIban extends JFrame implements ActionListener {

	JPanel menu_iban;
	JLabel erroreiban,errore2,errore3,ibanLabel,confermaiban; 
	JButton okerroremail,invioiban,indietroiban;
	final JTextField ibanField;
	private Utente utente;
	ModuloModificaIban(Utente utente){
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.utente = new Utente();
		this.utente = utente;
		
	    errore3 = new JLabel();
        errore3.setText("Errore, iban troppo lungo");      //set label value for textField1
        errore3.setBounds(550,125, 250,30);
        errore3.setVisible(false);
        errore3.setForeground(Color.RED.darker());
        
	    errore2 = new JLabel();
        errore2.setText("Errore, inserire un Iban valido");      //set label value for textField1
        errore2.setBounds(550,125, 170,30);
        errore2.setVisible(false);
        errore2.setForeground(Color.RED.darker());
		
		    erroreiban = new JLabel();
	        erroreiban.setText("Errore, campo Iban vuoto");      //set label value for textField1
	        erroreiban.setBounds(550,125, 170,30);
	        erroreiban.setVisible(false);
	        erroreiban.setForeground(Color.RED.darker());
	    	
	        okerroremail = new JButton("OK"); //set label to button
	        okerroremail.setBounds(550,400, 80,30);
	        okerroremail.setVisible(false);
	        okerroremail.setBackground(Color.white);
	    	
	        ibanLabel = new JLabel();
	        ibanLabel.setText("Nuovo Iban: ");      //set label value for textField1
	        ibanLabel.setBounds(450,100, 200,30);
	        
	        ibanField = new JTextField(15);    //set length of the text
	        ibanField.setBounds(550,100, 200,30);
	        
	        confermaiban = new JLabel();
	        confermaiban.setText("Iban Modificato con successo");      //set label value for textField1
	        confermaiban.setBounds(515,115, 170,30);
	        confermaiban.setVisible(false);
	        confermaiban.setForeground(Color.GREEN.darker());
	        
	        invioiban = new JButton("Conferma Iban"); //set label to button
	        invioiban.setBounds(500,150, 200,60);
	        invioiban.setBackground(Color.white);
	        
	        indietroiban = new JButton("Indietro"); //set label to button
	        indietroiban.setBounds(500,220, 200,60);
	        indietroiban.setBackground(Color.white);

        menu_iban = new JPanel();
        menu_iban.setLayout(null);
        menu_iban.setSize(1200,600);
        add(menu_iban);
        menu_iban.setVisible(false);
        menu_iban.add(ibanLabel);
        menu_iban.add(ibanField);
        menu_iban.add(confermaiban);
        menu_iban.add(indietroiban);
        menu_iban.add(invioiban);
        menu_iban.add(erroreiban);
        menu_iban.add(errore2);
        menu_iban.add(errore3);
        menu_iban.setVisible(true);
		
        indietroiban.addActionListener(this);
        invioiban.addActionListener(this::inviaiban);
	}
	
	public void inviaiban(ActionEvent e) {
		String iban = ibanField.getText();
		iban = iban.toUpperCase();
    	if(iban.length() == 27 & iban.startsWith("IT")){
    	confermaiban.setVisible(true);
    	ibanField.setVisible(false);
    	ibanLabel.setVisible(false);
    	invioiban.setVisible(false);
    	indietroiban.setBounds(500,150, 200,100);
    	erroreiban.setVisible(false);
    	errore2.setVisible(false);
    	errore3.setVisible(false);
    	//modifico l'iban nel database
    	ModificaDatiControl modificaDatiControl = new ModificaDatiControl(this.utente);
    	modificaDatiControl.modificaDati("iban", iban);
    	System.out.println(iban);
    	
    	}
    	else if(iban.length() >= 28) {
    		erroreiban.setVisible(false);
    		errore2.setVisible(false);
    		errore3.setVisible(true);
    	}
    	else if(iban.equals("")) {
    		erroreiban.setVisible(true);
      		errore2.setVisible(false);
      		errore3.setVisible(false);
    	}
    	else {
    		erroreiban.setVisible(false);
    		errore2.setVisible(true);
    		errore3.setVisible(false);
    	}
    	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	   	 ModuloAccount account = new ModuloAccount(this.utente);
	     account.setSize(1200, 600);  //set size of the frame
	     account.setVisible(true);  //make form visible to the user
	     dispose();
	}

}

/*
//modifico l'iban nel database
ModificaDatiControl modificaDatiControl = new ModificaDatiControl(this.utente);
modificaDatiControl.modificaDati("iban", iban);
*/