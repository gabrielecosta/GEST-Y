package com.gest.GestioneAccount.Interface;
import javax.swing.*;

import com.gest.Entity.Utente;
import com.gest.GestioneAccount.Control.ModificaPasswordControl;

import java.awt.*;
import java.awt.event.*;

public class ModuloModificaPassword extends JFrame implements ActionListener{

	  JPanel menu_password;
	  JButton inviapassword,indietropassword;
	  JLabel nuovapassword,vecchiapassword,confermapassword,errorecorrispondenza,errorecampovuoto1,errorecampovuoto2,errorecampovuoto3,errorecampivuoti,errorepassword,errorepassworduguale,conferma;
	  final JTextField nuovapasswordField,vecchiapasswordField,confermapasswordField;
	  private Utente utente;
	ModuloModificaPassword(Utente utente){
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.utente = new Utente();
		this.utente = utente;
		conferma = new JLabel("Password Modificata");
		conferma.setBounds(410,50,200,60);
		conferma.setForeground(Color.GREEN.darker());
		conferma.setVisible(false);
		
		errorepassworduguale = new JLabel("Errore, le nuova password coincide con quella vecchia!");
		errorepassworduguale.setBounds(410,50,400,60);
		errorepassworduguale.setForeground(Color.RED.darker());
		errorepassworduguale.setVisible(false);
		
		errorecorrispondenza = new JLabel("Errore, le password non corrispondono");
		errorecorrispondenza.setBounds(410,50,250,60);
		errorecorrispondenza.setForeground(Color.RED.darker());
		errorecorrispondenza.setVisible(false);
		
		errorecampovuoto1 = new JLabel("Errore, campo Vecchia Password vuoto!");
		errorecampovuoto1.setBounds(410,50,250,60);
		errorecampovuoto1.setForeground(Color.RED.darker());
		errorecampovuoto1.setVisible(false);
		
		errorecampovuoto2 = new JLabel("Errore, campo Nuova Password vuoto!");
		errorecampovuoto2.setBounds(410,50,250,60);
		errorecampovuoto2.setForeground(Color.RED.darker());
		errorecampovuoto2.setVisible(false);
		
		errorecampovuoto3 = new JLabel("Errore, campo Conferma Password vuoto!");
		errorecampovuoto3.setBounds(410,50,250,60);
		errorecampovuoto3.setForeground(Color.RED.darker());
		errorecampovuoto3.setVisible(false);
		
		errorecampivuoti = new JLabel("Errore, ci sono due o più campi vuoti!");
		errorecampivuoti.setBounds(410,50,250,60);
		errorecampivuoti.setForeground(Color.RED.darker());
		errorecampivuoti.setVisible(false);
		
		errorepassword = new JLabel("Errore, password errata!");
		errorepassword.setBounds(410,50,200,60);
		errorepassword.setForeground(Color.RED.darker());
		errorepassword.setVisible(false);
		
		
        inviapassword = new JButton("Invio"); //set label to button
        inviapassword.setBounds(500, 260, 200, 60);
        inviapassword.setBackground(Color.white);
        
        indietropassword = new JButton("Indietro"); //set label to button
        indietropassword.setBounds(500,330, 200,60);
		indietropassword.setBackground(Color.white);
		
    	nuovapassword = new JLabel();
        nuovapassword.setText("Nuova Password:");      //set label value for textField2
        nuovapassword.setBounds(410,150, 200,30);
		
		nuovapasswordField = new JPasswordField(10);    //set length for the password
	    nuovapasswordField.setBounds(550,150, 200,30);
	        
	    vecchiapassword = new JLabel();
	    vecchiapassword.setText("Vecchia Password:");      //set label value for textField2
	    vecchiapassword.setBounds(410,100, 200,30);
	    	
	    vecchiapasswordField = new JTextField(15);    //set length of the text
        vecchiapasswordField.setBounds(550,100, 200,30);
	    confermapassword = new JLabel();
	        	     
	    confermapassword.setText("Conferma Password:");      //set label value for textField2
	    confermapassword.setBounds(410,200, 200,30);
	    	
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
        menu_password.add(vecchiapassword);
        menu_password.add(vecchiapasswordField);
        menu_password.add(confermapassword);
        menu_password.add(confermapasswordField);
        menu_password.add(errorecampovuoto1);
        menu_password.add(errorecampovuoto2);
        menu_password.add(errorecampovuoto3);
        menu_password.add(errorecorrispondenza);
        menu_password.add(errorecampivuoti);
        menu_password.add(errorepassword);
        menu_password.add(confermapassword);
        menu_password.add(errorepassworduguale);
        menu_password.add(conferma);
        indietropassword.addActionListener(this);
        inviapassword.addActionListener(this::invios);
        menu_password.setVisible(true);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	   	 ModuloAccount account = new ModuloAccount(this.utente);
	     account.setSize(1200, 600);  //set size of the frame
	     account.setVisible(true);  //make form visible to the user
	     dispose();
	}
    public void invios(ActionEvent e) {
    
    	String vecchiapassword = vecchiapasswordField.getText();
    	String nuovapassword = nuovapasswordField.getText();
    	String confermapassword = confermapasswordField.getText();
    	if(nuovapassword.equals("") & vecchiapassword.equals("") & confermapassword.equals("")) {
    		errorepassworduguale.setVisible(false);
    		errorecorrispondenza.setVisible(false);
    		errorecampovuoto1.setVisible(false);
    		errorecampovuoto2.setVisible(false);
    		errorecampovuoto3.setVisible(false);
    		errorecampivuoti.setVisible(true);
    		errorepassword.setVisible(false);    	
    		conferma.setVisible(false);
    	}
    	else if(nuovapassword.equals("") & vecchiapassword.equals("")) {
    		errorepassworduguale.setVisible(false);
    		errorecorrispondenza.setVisible(false);
    		errorecampovuoto1.setVisible(false);
    		errorecampovuoto2.setVisible(false);
    		errorecampovuoto3.setVisible(false);
    		errorecampivuoti.setVisible(true);
    		errorepassword.setVisible(false);    	
    		conferma.setVisible(false);
    	}
    	else if(nuovapassword.equals("") & confermapassword.equals("")) {
    		errorepassworduguale.setVisible(false);
    		errorecorrispondenza.setVisible(false);
    		errorecampovuoto1.setVisible(false);
    		errorecampovuoto2.setVisible(false);
    		errorecampovuoto3.setVisible(false);
    		errorecampivuoti.setVisible(true);
    		errorepassword.setVisible(false); 
    		conferma.setVisible(false);
    	}
    	else if (confermapassword.equals("") & vecchiapassword.equals("")) {
    		errorepassworduguale.setVisible(false);
    		errorecorrispondenza.setVisible(false);
    		errorecampovuoto1.setVisible(false);
    		errorecampovuoto2.setVisible(false);
    		errorecampovuoto3.setVisible(false);
    		errorecampivuoti.setVisible(true);
    		errorepassword.setVisible(false);  
    		conferma.setVisible(false);
    	}
    	else if(nuovapassword.equals("")) {
    		
    		errorepassworduguale.setVisible(false);
    		errorecorrispondenza.setVisible(false);
    		errorecampovuoto1.setVisible(false);
    		errorecampovuoto2.setVisible(true);
    		errorecampovuoto3.setVisible(false);
    		errorecampivuoti.setVisible(false);
    		errorepassword.setVisible(false);
    		conferma.setVisible(false);
    	}
    	else if (vecchiapassword.equals("")) {
    		errorepassworduguale.setVisible(false);
    		errorecorrispondenza.setVisible(false);
    		errorecampovuoto1.setVisible(true);
    		errorecampovuoto2.setVisible(false);
    		errorecampovuoto3.setVisible(false);
    		errorecampivuoti.setVisible(false);
    		errorepassword.setVisible(false);
    		conferma.setVisible(false);
    	}
    	else if (confermapassword.equals("")) {
    		errorepassworduguale.setVisible(false);
    		errorecorrispondenza.setVisible(false);
    		errorecampovuoto1.setVisible(false);
    		errorecampovuoto2.setVisible(false);
    		errorecampovuoto3.setVisible(true);
    		errorecampivuoti.setVisible(false);
    		errorepassword.setVisible(false);
    		conferma.setVisible(false);
    	}
    	else if(vecchiapassword.equals(nuovapassword) & !nuovapassword.equals("") & !vecchiapassword.equals("")) {
    		
    		errorepassworduguale.setVisible(true);
    		errorecorrispondenza.setVisible(false);
    		errorecampovuoto1.setVisible(false);
    		errorecampovuoto2.setVisible(false);
    		errorecampovuoto3.setVisible(false);
    		errorecampivuoti.setVisible(false);
    		errorepassword.setVisible(false);
    		conferma.setVisible(false);
    	}else if(!nuovapassword.equals(confermapassword) & !nuovapassword.equals("")& !confermapassword.equals("")) {
    		
    		errorepassworduguale.setVisible(false);
    		errorecorrispondenza.setVisible(true);
    		errorecampovuoto1.setVisible(false);
    		errorecampovuoto2.setVisible(false);
    		errorecampovuoto3.setVisible(false);
    		errorecampivuoti.setVisible(false);
    		errorepassword.setVisible(false);
    		conferma.setVisible(false);
    	}
    	//SE LA PASSWORD E' ERRATA
    	else if(!vecchiapassword.equals(this.utente.getPassw())){
    		errorepassworduguale.setVisible(false);
    		errorecorrispondenza.setVisible(false);
    		errorecampovuoto1.setVisible(false);
    		errorecampovuoto2.setVisible(false);
    		errorecampovuoto3.setVisible(false);
    		errorecampivuoti.setVisible(false);
    		errorepassword.setVisible(true);
    		conferma.setVisible(false);
    	
    	}
    	//SE PASSWORD E' GIUSTA E NUOVA E CONFERMA COINCIDONO
    	else if (nuovapassword.equals(confermapassword) & vecchiapassword.equals(this.utente.getPassw())){
    		errorepassworduguale.setVisible(false);
    		errorecorrispondenza.setVisible(false);
    		errorecampovuoto1.setVisible(false);
    		errorecampovuoto2.setVisible(false);
    		errorecampovuoto3.setVisible(false);
    		errorecampivuoti.setVisible(false);
    		errorepassword.setVisible(false);
    		conferma.setVisible(true);
    		
    		//modifico la password nel database
    		ModificaPasswordControl modificaPasswControl = new ModificaPasswordControl(this.utente);
    		modificaPasswControl.setNew_password(confermapassword);
    		modificaPasswControl.updatePassword();
    		
    		//modifico la password della entity
    		this.utente.setPassw(confermapassword);
    		
    	}
    	
    	
    	
    	
    	
    }
	
	
	
}
