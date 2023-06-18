package com.gest.GestioneAccount.Interface;
import javax.swing.*;

import com.gest.Entity.Utente;
import com.gest.GestioneAccount.Control.ModificaDatiControl;

import java.awt.*;
import java.awt.event.*;

public class ModuloModificaTelefono extends JFrame implements ActionListener{

	JPanel menu_telefono;
	JLabel nuovotelefono,confermatelefono,erroretelefono,erroretelefonovuoto,trenove,errorelettere;
	JButton inviatelefono,indietrotelefono;
	final JTextField nuovotelefonoField;
	private Utente utente;
	ModuloModificaTelefono(Utente utente){
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.utente = new Utente();
		this.utente = utente;
		
		nuovotelefono = new JLabel();
        nuovotelefono.setText("Nuovo Telefono:");      //set label value for textField2
        nuovotelefono.setBounds(450,150, 200,30);
        
        trenove = new JLabel();
        trenove.setText("(+39)");      //set label value for textField2
        trenove.setBounds(550,125, 200,30);
        trenove.setForeground(Color.YELLOW.darker());
        
        nuovotelefonoField = new JTextField(15);    //set length of the text
        nuovotelefonoField.setBounds(550,150, 200,30);
    	
        inviatelefono = new JButton("Invio"); //set label to button
        inviatelefono.setBounds(500, 210, 200, 60);
        inviatelefono.setBackground(Color.white);
        
        indietrotelefono = new JButton("Indietro"); //set label to button
        indietrotelefono.setBounds(500,280, 200,60);
        indietrotelefono.setBackground(Color.white);
    	errorelettere = new JLabel();
    	errorelettere.setText("Errore, inserire solo numeri!");      //set label value for textField2
    	errorelettere.setBounds(500,180, 300,30);
    	errorelettere.setForeground(Color.RED.darker());
        errorelettere.setVisible(false);
        
    	erroretelefono = new JLabel();
    	erroretelefono.setText("Errore, inserire un numero di telefono valido");      //set label value for textField2
    	erroretelefono.setBounds(500,180, 300,30);
        erroretelefono.setForeground(Color.RED.darker());
    	erroretelefono.setVisible(false);
    	
    	erroretelefonovuoto = new JLabel();
    	erroretelefonovuoto.setText("Errore, campo telefono vuoto");      //set label value for textField2
    	erroretelefonovuoto.setBounds(500,180, 300,30);
    	erroretelefonovuoto.setForeground(Color.RED.darker());
        erroretelefonovuoto.setVisible(false);
    	
    	confermatelefono = new JLabel();
    	confermatelefono.setText("Nuovo numero di telefono registrato con successo");      //set label value for textField2
    	confermatelefono.setBounds(465,180, 300,30);
        confermatelefono.setForeground(Color.GREEN.darker());
        confermatelefono.setVisible(false);
		
        menu_telefono = new JPanel();
        menu_telefono.setLayout(null);
        menu_telefono.setSize(1200,600);
        add(menu_telefono);
        menu_telefono.add(inviatelefono);
        menu_telefono.add(indietrotelefono);
        menu_telefono.add(nuovotelefono);
        menu_telefono.add(nuovotelefonoField);
        menu_telefono.add(confermatelefono);
        menu_telefono.add(erroretelefono);
        menu_telefono.add(erroretelefonovuoto);
        menu_telefono.add(trenove);
        menu_telefono.add(errorelettere);
        menu_telefono.setVisible(true);
		indietrotelefono.addActionListener(this);
		inviatelefono.addActionListener(this::invio_nuovotelefono);
		
	}
	 private void invio_nuovotelefono(ActionEvent event) {
		 this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    	String numero = nuovotelefonoField.getText();
	    	boolean s = numero.matches(".*[a-zA-Z]+.*");
	    	if(numero.equals(""))
	    	{
	        	erroretelefonovuoto.setVisible(true);	
	        	erroretelefono.setVisible(false);
	        	confermatelefono.setVisible(false);
	        	errorelettere.setVisible(false);
	    	}
	    	else if(numero.length() >= 11 || numero.length()<=9){
	        	erroretelefonovuoto.setVisible(false);
	    		erroretelefono.setVisible(true);
	    		errorelettere.setVisible(false);
	    		confermatelefono.setVisible(false);
	    	}
	    	else if(numero.length() == 10 && s == true)
	    	{
	    		errorelettere.setVisible(true);
	        	erroretelefonovuoto.setVisible(false);
	    		erroretelefono.setVisible(false);
	    		confermatelefono.setVisible(false);
	    	}
	    	else if(numero.length() == 10 && s == false) {
	    		
	        	nuovotelefonoField.setVisible(false);
	        	inviatelefono.setVisible(false);
	        	nuovotelefono.setVisible(false);
	        	indietrotelefono.setBounds(500, 210, 200, 60);
	        	confermatelefono.setVisible(true);
	        	erroretelefono.setVisible(false);
	        	erroretelefonovuoto.setVisible(false);
	        	trenove.setVisible(false);
	        	errorelettere.setVisible(false);
	        	ModificaDatiControl modificaDatiControl = new ModificaDatiControl(this.utente);
	        	modificaDatiControl.modificaDati("telefono", "+39" + numero);
	      
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
//modifico il telefono nel database
ModificaDatiControl modificaDatiControl = new ModificaDatiControl(this.utente);
modificaDatiControl.modificaDati("telefono", numero);
*/
