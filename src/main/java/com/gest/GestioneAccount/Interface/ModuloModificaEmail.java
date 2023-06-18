package com.gest.GestioneAccount.Interface;
import javax.swing.*;

import com.gest.Entity.Utente;
import com.gest.GestioneAccount.Control.ModificaDatiControl;

import java.awt.*;
import java.awt.event.*;

public class ModuloModificaEmail extends JFrame implements ActionListener{

	JPanel menu_email;
	JButton invianuovaemail,indietro;
    JLabel nuovaemail,erroremail,erroremailvuoto,confermamail;
    final JTextField nuovamailField;
    private Utente utente;
	ModuloModificaEmail(Utente utente){
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.utente = new Utente();
		this.utente = utente;
		erroremail = new JLabel();
    	erroremail.setText("Errore, inserire una email valida");      //set label value for textField2
    	erroremail.setBounds(500,180, 200,30);
    	erroremail.setForeground(Color.RED.darker());
    	erroremail.setVisible(false);
    	
    	erroremailvuoto = new JLabel();
    	erroremailvuoto.setText("Errore, campo email vuoto");      //set label value for textField2
    	erroremailvuoto.setBounds(500,180, 200,30);
    	erroremailvuoto.setForeground(Color.RED.darker());
    	erroremailvuoto.setVisible(false);
    	
    	confermamail = new JLabel();
    	confermamail.setText("Nuova mail registrata con successo");      //set label value for textField2
    	confermamail.setBounds(500,180, 250,30);
    	confermamail.setForeground(Color.GREEN.darker());
    	confermamail.setVisible(false);
    	
        invianuovaemail = new JButton("Invio"); //set label to button
        invianuovaemail.setBounds(500, 210, 200, 60);
        invianuovaemail.setBackground(Color.white);
        
        indietro = new JButton("Indietro"); //set label to button
        indietro.setBounds(500,280, 200,60);
        indietro.setBackground(Color.white);
        
        nuovamailField = new JTextField(15);    //set length of the text
        nuovamailField.setBounds(550,150, 200,30);
        
        nuovaemail = new JLabel();
        nuovaemail.setText("Nuova Email:");      //set label value for textField2
        nuovaemail.setBounds(450,150, 200,30);
		
        menu_email = new JPanel();
        menu_email.setLayout(null);
        menu_email.setSize(1200,600);
        add(menu_email);
        menu_email.add(invianuovaemail);
        menu_email.add(indietro);
        menu_email.add(nuovaemail);
        menu_email.add(nuovamailField);
        menu_email.add(confermamail);
        menu_email.add(erroremail);
        menu_email.add(erroremailvuoto);
        menu_email.setVisible(true);	
        invianuovaemail.addActionListener(this::invio_nuovaemail);
        indietro.addActionListener(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	   	 ModuloAccount account = new ModuloAccount(this.utente);
	     account.setSize(1200, 600);  //set size of the frame
	     account.setVisible(true);  //make form visible to the user
	     dispose();
		
	}
	 private void invio_nuovaemail(ActionEvent event) {
	    	String email = nuovamailField.getText();
	    	if(email.equals(""))
	    	{
	        	erroremailvuoto.setVisible(true);	
	        	erroremail.setVisible(false);
	        	confermamail.setVisible(false);
	    	}
	    	else if (!email.contains("@") & !email.contains(".")){
	        	erroremailvuoto.setVisible(false);
	    		erroremail.setVisible(true);
	    		confermamail.setVisible(false);
	    	}else {
	    		
	    		ModificaDatiControl modificaDatiControl = new ModificaDatiControl(this.utente);
	    		modificaDatiControl.modificaDati("email", email);
	        	erroremailvuoto.setVisible(false);
	    		erroremail.setVisible(false);
	    		confermamail.setVisible(true);
	    	}
	    	
	    }

}

/*
//modifico la email nel database
ModificaDatiControl modificaDatiControl = new ModificaDatiControl(this.utente);
modificaDatiControl.modificaDati("email", email);
*/
