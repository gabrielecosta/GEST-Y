package com.gest.GestioneDDL.Interface;
import javax.swing.*;

import com.gest.Entity.Turno;
import com.gest.Entity.Utente;
import com.gest.GestioneDDL.Control.gestioneImpiegatoCtl;

import java.awt.*;
import java.awt.event.*;

public class ModuloAssunzione extends JFrame implements ActionListener{
    
	private Utente utente;
	Turno turno;
	JPanel menu;
	JButton indietro,invia;
	JLabel password,cf,nome,cognome,email,sesso,iban,ruolo,numero,dataA,dataL,conferma,errore1;
	JTextField Password,CF,Nome,Cognome,Email,Sesso,Iban,Ruolo,Numero,DataA;
	JLabel errorenome,errorecognome,errorecf,erroreemail,errorepassword,erroresesso,erroreiban,erroreruolo,errorenumero,erroredata,erroreconferma,erroreresidenza;

    JCheckBox checkBox5 = new JCheckBox("Si",false);  
    JCheckBox checkBox6 = new JCheckBox("No",true);  
    
	public ModuloAssunzione(Utente utente){
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.utente = new Utente();
		this.utente = utente;
		
		nome = new JLabel();
		nome.setText("Nome:");      //set label value for textField2
        nome.setBounds(500,30, 200,30);
        
		Nome = new JTextField();
        Nome.setBounds(625,30, 200,30);
        
		errorenome = new JLabel();
		errorenome.setText("Errore! Inserire un nome corretto");      //set label value for textField2
		errorenome.setBounds(850,30, 300,30);
		errorenome.setForeground(Color.red.darker());
		errorenome.setVisible(false);
		
		
		errore1 = new JLabel("Errore, uno o pi� campi vuoti/errati!");
		errore1.setBounds(760,450,300,40);
		errore1.setForeground(Color.red.darker());
		errore1.setVisible(false);
        
		conferma = new JLabel();
		conferma.setBounds(450,250, 500,30);
        conferma.setForeground(Color.green.darker());
        conferma.setVisible(false);
        

		cognome = new JLabel();
		cognome.setText("Cognome:");      //set label value for textField2
        cognome.setBounds(500,65, 200,30);
        
		Cognome = new JTextField();
        Cognome.setBounds(625,65, 200,30);
        
		errorecognome = new JLabel();
		errorecognome.setText("Errore! Inserire un Cognome valido!");      //set label value for textField2
		errorecognome.setBounds(850,65, 300,30);
		errorecognome.setForeground(Color.red.darker());
        errorecognome.setVisible(false);

		password = new JLabel();
		password.setText("Password:");      //set label value for textField2
        password.setBounds(500,100, 200,30);
        
		errorepassword = new JLabel();
		errorepassword.setText("Errore! Inserire una password corretta");      //set label value for textField2
		errorepassword.setBounds(850,100, 300,30);
		errorepassword.setForeground(Color.red.darker());
		errorepassword.setVisible(false);
        
        Password = new JTextField();
        Password.setBounds(625,100, 200,30);
        
		cf = new JLabel();
        cf.setText("Cod. Fiscale:");      //set label value for textField2
        cf.setBounds(500,135, 200,30);
        
		errorecf = new JLabel();
		errorecf.setText("Errore! Inserire un C.F. corretto");      //set label value for textField2
		errorecf.setBounds(850,135, 300,30);
		errorecf.setForeground(Color.red.darker());
		errorecf.setVisible(false);
        
		CF = new JTextField();
        CF.setBounds(625,135, 200,30);
      
		email = new JLabel();
		email.setText("Email:");      //set label value for textField2
		email.setBounds(500,170, 200,30);
        
		Email = new JTextField();
		Email.setBounds(625,170, 200,30);
		
		erroreemail = new JLabel();
		erroreemail.setText("Errore! Inserire una email corretta");      //set label value for textField2
		erroreemail.setBounds(850,170, 300,30);
		erroreemail.setForeground(Color.red.darker());
		erroreemail.setVisible(false);
		
		iban = new JLabel();
		iban.setText("Iban:");      //set label value for textField2
		iban.setBounds(500,205, 200,30);
        
		Iban = new JTextField();
		Iban.setBounds(625,205, 200,30);

		erroreiban = new JLabel();
		erroreiban.setText("Errore! Inserire una email corretta");      //set label value for textField2
		erroreiban.setBounds(850,205, 300,30);
		erroreiban.setForeground(Color.red.darker());
		erroreiban.setVisible(false);
		
		sesso = new JLabel();
		sesso.setText("Sesso:");      //set label value for textField2
		sesso.setBounds(500,240, 200,30);
         
		erroresesso = new JLabel();
		erroresesso.setText("Errore! Inserire un sesso corretto");      //set label value for textField2
		erroresesso.setBounds(850,240, 300,30);
		erroresesso.setForeground(Color.red.darker());
		erroresesso.setVisible(false);
		
		Sesso = new JTextField();
		Sesso.setBounds(625,240, 200,30);
		
		ruolo = new JLabel();
		ruolo.setText("Ruolo:");      //set label value for textField2
		ruolo.setBounds(500,275, 200,30);
        
		Ruolo = new JTextField();
		Ruolo.setBounds(625,275, 200,30);
        
		erroreruolo = new JLabel();
		erroreruolo.setText("Errore! Inserire un ruolo corretto");      //set label value for textField2
		erroreruolo.setBounds(850,275, 300,30);
		erroreruolo.setForeground(Color.red.darker());
		erroreruolo.setVisible(false);
		
		numero = new JLabel();
		numero.setText("Numero:");      //set label value for textField2
		numero.setBounds(500,310, 200,30);
        
		errorenumero = new JLabel();
		errorenumero.setText("Errore! Inserire un numero corretto");      //set label value for textField2
		errorenumero.setBounds(850,310, 300,30);
		errorenumero.setForeground(Color.red.darker());
		errorenumero.setVisible(false);
		
		Numero = new JTextField();
		Numero.setBounds(625,310, 200,30);
		
		dataA = new JLabel();
		dataA.setText("Residenza:");      //set label value for textField2
		dataA.setBounds(500,345, 200,30);     
		
		DataA = new JTextField();
		DataA.setBounds(625,345, 200,30);
		
		erroreresidenza = new JLabel();
		erroreresidenza.setText("Errore! Inserire una residenza corretta");      //set label value for textField2
		erroreresidenza.setBounds(850,345, 300,30);
		erroreresidenza.setForeground(Color.red.darker());
		erroreresidenza.setVisible(false);
		
		dataL = new JLabel();
		dataL.setText("Admin?:");      //set label value for textField2
		dataL.setBounds(500,380, 200,30);
        
        checkBox5.setBounds(625,380, 100,30);  
        checkBox6.setBounds(725,380, 100,30);  
        final ButtonGroup bg2 = new ButtonGroup();
        bg2.add(checkBox5);
        bg2.add(checkBox6);
		
		indietro= new JButton("Indietro");
		indietro.setBounds(450,500,300,40);
		indietro.setBackground(Color.white);
	    
		invia = new JButton("Assumi");
		invia.setBounds(450,450,300,40);
		invia.setBackground(Color.white);
		
		menu = new JPanel();
		menu.setLayout(null);
		menu.setSize(1200,600);
		menu.setVisible(true);
		menu.add(conferma);
		menu.add(checkBox6);
		menu.add(checkBox5);
		menu.add(invia);
		menu.add(indietro);
		menu.add(nome);
		menu.add(Nome);
		menu.add(errorenome);
		menu.add(cognome);
		menu.add(Cognome);
		menu.add(errorecognome);
		menu.add(password);
		menu.add(Password);
		menu.add(errorepassword);
		menu.add(cf);
		menu.add(CF);
		menu.add(errorecf);
		menu.add(sesso);
		menu.add(Sesso);
		menu.add(erroresesso);
		menu.add(errore1);
		menu.add(dataA);
		menu.add(DataA);
		menu.add(erroreresidenza);
		menu.add(dataL);
		menu.add(iban);
		menu.add(Iban);
		menu.add(erroreiban);
		menu.add(ruolo);
		menu.add(Ruolo);
		menu.add(erroreruolo);
		menu.add(email);
		menu.add(Email);
		menu.add(erroreemail);
		menu.add(numero);
		menu.add(Numero);
		menu.add(errorenumero);
		
		add(menu);
		
		invia.addActionListener(this::Invio);
	    indietro.addActionListener(this);	
	}
	
	public void Invio(ActionEvent e) {
		
		if(Nome.getText().equals("")) {
			errorenome.setVisible(true);
		}else {
			errorenome.setVisible(false);
		}
		if(Cognome.getText().equals("")) {
			errorecognome.setVisible(true);
		}else {
			errorecognome.setVisible(false);
		}
		if(Password.getText().equals("")) {
			errorepassword.setVisible(true);
		}else {
			errorepassword.setVisible(false);
		}
		if(CF.getText().equals("")) {
			errorecf.setVisible(true);
		}else {
			errorecf.setVisible(false);
		}if(Sesso.getText().equals("")) {
			erroresesso.setVisible(true);
		}else {
			erroresesso.setVisible(false);
		}
		if(DataA.getText().equals("")) {
			erroreresidenza.setVisible(true);
		}else {
			erroreresidenza.setVisible(false);
		}
		if(Iban.getText().equals("")) {
			erroreiban.setVisible(true);
		}else {
			erroreiban.setVisible(false);
		}
		if(Ruolo.getText().equals("")) {
			erroreruolo.setVisible(true);
		}else {
			erroreruolo.setVisible(false);
		}
		if(Email.getText().equals("")) {
			erroreemail.setVisible(true);
		}else {
			erroreemail.setVisible(false);
		}if(Numero.getText().equals("")) {
			errorenumero.setVisible(true);
		}else {
			errorenumero.setVisible(false);
		}if(!Nome.getText().equals("") & !Cognome.getText().equals("") & !Password.getText().equals("") & !CF.getText().equals("") & !Sesso.getText().equals("") & !DataA.getText().equals("") & !Iban.getText().equals("") & !Ruolo.getText().equals("") & !Email.getText().equals("") & !Numero.getText().equals("")){
			
			errorenumero.setVisible(false);
			erroreemail.setVisible(false);
			errore1.setVisible(false);
			erroreruolo.setVisible(false);
			erroreiban.setVisible(false);
			erroreresidenza.setVisible(false);
			erroresesso.setVisible(false);
			errorecf.setVisible(false);
			errorepassword.setVisible(false);
			errorecognome.setVisible(false);
			errorenome.setVisible(false);
			
			indietro.setBounds(450,300,300,40);
			conferma.setText("Impiegato" + " " + Nome.getText() + " " + Cognome.getText() + " " + "assunto con successo!");  
			conferma.setVisible(true);
			
			gestioneImpiegatoCtl gestione = new gestioneImpiegatoCtl();
			Utente new_utente = new Utente();
			new_utente.setNome(Nome.getText());
			new_utente.setCognome(Cognome.getText());
			new_utente.setPassw(Password.getText());
			new_utente.setCf(CF.getText());
			new_utente.setEmail(Email.getText());
			new_utente.setIban(Iban.getText());
			new_utente.setSesso(Sesso.getText());
			new_utente.setRuolo(Integer.parseInt(Ruolo.getText()));
			new_utente.setTelefono(Numero.getText());
			new_utente.setVia(DataA.getText());
			new_utente.setN_civ("30");
			new_utente.setCap("90030L");
			Boolean flag = false;
			if(checkBox5.isSelected()) {
				flag = true;
			} else {
				flag = false;
			}
			new_utente.setIsAdmin(flag);
			new_utente.setIsDDL(false);
			
			
			gestione.assumi(new_utente);
			
			invia.setVisible(false);
			nome.setVisible(false);
			Nome.setVisible(false);
			cognome.setVisible(false);
			Cognome.setVisible(false);
			password.setVisible(false);
			Password.setVisible(false);
			cf.setVisible(false);
			CF.setVisible(false);
			sesso.setVisible(false);
			Sesso.setVisible(false);
			dataA.setVisible(false);
			DataA.setVisible(false);
			dataL.setVisible(false);
			iban.setVisible(false);
			Iban.setVisible(false);
			ruolo.setVisible(false);
			Ruolo.setVisible(false);
			email.setVisible(false);
			Email.setVisible(false);
			numero.setVisible(false);
			Numero.setVisible(false);
			checkBox5.setVisible(false);
			checkBox6.setVisible(false);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ModuloGestioneImpiegato mod = new ModuloGestioneImpiegato(utente);
        mod.setSize(1200,600);
        mod.setVisible(true);
    	dispose();
	}

	
	
	
}