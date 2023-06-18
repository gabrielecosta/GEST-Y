package com.gest.GestioneAccount.Interface;
import javax.swing.*;

import com.gest.GestioneAccount.Control.RecuperaCredenzialiControl;

import java.awt.*;
import java.awt.event.*;
public class ModuloForgot extends JFrame implements ActionListener {

	
    JButton indietrorecupero,inviorecupero,ok;
    JPanel menu_forgot,panel;
    JLabel MailLabel, emailinviatatxt,Gif,errore,testoerrore;
    JLabel erroremail,erroremailvuoto,confermamail;
    private String emailValue;
    String userValue;
    final JTextField  MailText;
    public static final Color VERY_LIGHT_RED = new Color(255,102,102);
    
	ModuloForgot(){
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon gif = new ImageIcon("mail.gif");
        Gif = new JLabel();
        Gif.setText("");
        Gif.setBounds(490,80, 220,100);
        Gif.setIcon(gif);
        Gif.setVisible(false);
		
        MailLabel = new JLabel();
        MailLabel.setText("La tua mail:");      //set label value for textField1
        MailLabel.setBounds(450,160, 200,30);
        
        errore = new JLabel();
        errore.setText("Errore, inserisci prima una email!");      //set label value for textField1
        errore.setBounds(550,135, 200,30);
        errore.setForeground(Color.red.darker());
        errore.setVisible(false);
        
        indietrorecupero = new JButton("Indietro"); //set label to button
        indietrorecupero.setBounds(500,290, 200,30);

        inviorecupero = new JButton("Invia");
        inviorecupero.setBounds(500, 210, 200, 60);

		
        MailText = new JTextField(15);    //set length of the text
        MailText.setBounds(550,160, 200,30);
        
        emailinviatatxt = new JLabel();
        emailinviatatxt.setVisible(false);
        emailinviatatxt.setText("Credenziali Inviate Tramite Email");
        emailinviatatxt.setForeground(Color.GREEN.darker());
        emailinviatatxt.setBounds(508,180, 184,30);
		
        testoerrore = new JLabel("Errore, mail non trovata nel sistema!");
		testoerrore.setForeground(Color.white);
		testoerrore.setBounds(50,5,240,50);
		
		ok = new JButton("OK");
		ok.setBackground(Color.white);
		ok.setBounds(100,50,100,50);
		
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(450, 350,300,120);
		panel.setBackground(Color.RED);
		panel.add(testoerrore);
		panel.add(ok);
		add(panel);
		panel.setVisible(false);
        
        
        
        menu_forgot = new JPanel();
        menu_forgot.setLayout(null);
        menu_forgot.setSize(1200,600);
        menu_forgot.setBackground(Color.white);
        menu_forgot.add(inviorecupero);
        menu_forgot.add(panel);
        menu_forgot.add(indietrorecupero);
        menu_forgot.add(MailText);
        menu_forgot.add(MailLabel);
        menu_forgot.add(errore);
        menu_forgot.add(emailinviatatxt);
        menu_forgot.add(Gif);
        add(menu_forgot);
        
        indietrorecupero.addActionListener(this::indietro);
        inviorecupero.addActionListener(this);
        ok.addActionListener(this::ok);
        
	}
	

	private void ok(ActionEvent e) {
		panel.setVisible(false);
	}
    @Override
	public void actionPerformed(ActionEvent e) {
	        emailValue = MailText.getText();        //get user entered pasword from the textField2
	        //check whether the credentials are authentic or not
	        if(!emailValue.equals("")) {
            RecuperaCredenzialiControl recuperaCtl = new RecuperaCredenzialiControl();
	        if (!recuperaCtl.verificaEmail(emailValue)){
	            //show error message
	            System.out.println("Email non trovata!");
              ModuloErroreMail errore = new ModuloErroreMail();
	            errore.setSize(300, 150);  //set size of the frame
	            panel.setVisible(true);  //make form visible to the user
	        }	   
            else if (recuperaCtl.verificaEmail(emailValue)) {  //QUI VA MESSO IL CHECK DEL DMBS PER VEDERE SE LA MAIL ESISTE
	        	emailinviatatxt.setVisible(true);
	            MailText.setVisible(false);
	            MailLabel.setVisible(false);
	            errore.setVisible(false);
	            indietrorecupero.setBounds(500, 210, 200, 60);
	            inviorecupero.setVisible(false);
	            Gif.setVisible(true);
	            recuperaCtl.sendEmail();
	        }
	        }
	        
	        else {
	            
	        errore.setVisible(true);
	            
	            
	        }


	    }
	public void indietro(ActionEvent e)
	{
		ModuloLogin login = new ModuloLogin();
		login.setSize(1200,600);
		login.setVisible(true);
		dispose();
	}

}
