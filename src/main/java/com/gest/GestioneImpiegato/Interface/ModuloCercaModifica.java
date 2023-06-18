package com.gest.GestioneImpiegato.Interface;
import javax.swing.*;

import com.gest.Entity.Impiegato;
import com.gest.Entity.Turno;
import com.gest.Entity.Utente;
import com.gest.GestioneAccount.Control.ModificaDatiControl;

import java.awt.*;
import java.awt.event.*;

public class ModuloCercaModifica extends JFrame implements ActionListener{
    
	private Utente utente;
	Impiegato impiegato;
	JPanel menu;
	JButton indietro,invia;
	JLabel email,iban,numero,trenove,erroremail,erroremailvuoto;
	JTextField textfield1,textfield2,textfield3;
	
	ModuloCercaModifica(Utente utente,String ID, Impiegato impiegato){
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.impiegato = impiegato;
		this.utente = new Utente();
		this.utente = utente;
		
        trenove = new JLabel();
        trenove.setText("(+39)");      //set label value for textField2
        trenove.setBounds(550,175, 200,30);
        trenove.setForeground(Color.YELLOW.darker());
		
		email = new JLabel();
        email.setText("Email:");      //set label value for textField2
        email.setBounds(410,100, 200,30);
		
		textfield1 = new JTextField(10);    //set length for the password
	    textfield1.setBounds(550,100, 200,30);
	    textfield1.setText(this.impiegato.getEmail());
	        
	    iban = new JLabel();
	    iban.setText("Iban:");      //set label value for textField2
	    iban.setBounds(410,150, 200,30);
	    	
	    textfield2 = new JTextField(15);    //set length of the text
	    textfield2.setBounds(550,150, 200,30);
	    textfield2.setText(this.impiegato.getIban());
	    
	    numero = new JLabel();    	     
	    numero.setText("Numero:");      //set label value for textField2
	    numero.setBounds(410,200, 200,30);
	    	
	    textfield3 = new JTextField(15);    //set length of the text
	    textfield3.setBounds(550,200, 200,30);
	    textfield3.setText(this.impiegato.getTelefono());
	    
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
		
		invia = new JButton("Modifica Dati"); //set label to button
		invia.setBounds(500, 260, 200, 60);
		invia.setBackground(Color.white);
        
		indietro = new JButton("Indietro"); //set label to button
		indietro.setBounds(500,330, 200,60);
		indietro.setBackground(Color.white);
		
		menu = new JPanel();
		menu.setLayout(null);
		menu.setSize(1200,600);
		menu.setVisible(true);
		menu.add(indietro);
		menu.add(iban);
		menu.add(email);
		menu.add(numero);
		menu.add(invia);
		menu.add(textfield1);
		menu.add(textfield2);
		menu.add(textfield3);
		menu.add(trenove);
		add(menu);
	    indietro.addActionListener(this);	
	    invia.addActionListener(this::Invia);
	}
	private void Invia(ActionEvent e) {
		String email = textfield1.getText();
		String numero = textfield3.getText();
		String iban = textfield2.getText();
		
    	if(email.equals(""))
    	{
        	erroremailvuoto.setVisible(true);	
    		erroremail.setVisible(false);
    	}
    	else if (!email.contains("@") & !email.contains(".")){
        	erroremailvuoto.setVisible(false);	
    		erroremail.setVisible(true);
    	}else {
    		ModificaDatiControl modificaDatiControl = new ModificaDatiControl(this.impiegato);
    		modificaDatiControl.modificaDati("email", email);
    		modificaDatiControl.modificaDati("telefono", numero);
    		modificaDatiControl.modificaDati("iban", iban);
        	erroremailvuoto.setVisible(false);
    		erroremail.setVisible(false);
    	}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ModuloCerca modulo = new ModuloCerca(this.utente);
		modulo.setSize(1200,600);
		modulo.setVisible(true);
		dispose();
	}

	
	
	
}