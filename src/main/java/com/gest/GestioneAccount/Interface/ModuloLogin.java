package com.gest.GestioneAccount.Interface;
import javax.swing.*;

import com.gest.Entity.Utente;
import com.gest.GestioneAccount.Control.ControlLogin;

import java.awt.*;
import java.awt.event.*;


@SuppressWarnings("serial")
public class ModuloLogin extends JFrame implements ActionListener{

    public JButton b1;
    public JFrame frame;
    public JPanel newPanel;
    public JLabel userLabel, passLabel, Forgot, Wrong, costast,background,welcome,errorematricola,errorepassword,erroreempty,errorecar;
    public JTextField  textField1, textField2;
	public ModuloLogin(){
		
        setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon costas = new ImageIcon("dashboard.png");
        costast = new JLabel();
        costast.setText("");
        costast.setBounds(475,40, 249,39);
        costast.setIcon(costas);
		
		errorepassword = new JLabel();
        errorepassword.setText("Errore! Campo password vuoto");
        errorepassword.setVisible(false);
        errorepassword.setBounds(550,175, 200,30);
        errorepassword.setForeground(Color.RED.darker());
        
		errorecar = new JLabel();
		errorecar.setText("Errore! Matricola non valida!");
		errorecar.setVisible(false);
        errorecar.setBounds(550,175, 200,30);
        errorecar.setForeground(Color.RED.darker());
        
        errorematricola = new JLabel();
        errorematricola.setText("Errore! Campo matricola vuoto");
        errorematricola.setVisible(false);
        errorematricola.setBounds(550,175, 200,30);
        errorematricola.setForeground(Color.RED.darker());
        
        erroreempty = new JLabel();
        erroreempty.setText("Errore! Campi credenziali vuoti");
        erroreempty.setVisible(false);
        erroreempty.setBounds(550,175, 200,30);
        erroreempty.setForeground(Color.RED.darker());

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        userLabel = new JLabel();
        userLabel.setText("Matricola:");      //set label value for textField1
        userLabel.setBounds(450,100, 200,30);

        Forgot = new JLabel();
        Forgot.setText("Password Dimenticata?"); //126 +2 +2 +2 +2 +2
        Forgot .setForeground(Color.BLUE.darker());
        Forgot.setBounds(537,240, 136,30);
        Forgot.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        //create text field to get username from the user
        textField1 = new JTextField(15);    //set length of the text
        textField1.setBounds(550,100, 200,30);


        //create label for password
        passLabel = new JLabel();
        passLabel.setText("Password:");      //set label value for textField2
        passLabel.setBounds(450,150, 200,30);
     //   passLabel.setSize(400,400);

        //create text field to get password from the user
        textField2 = new JPasswordField(10);    //set length for the password
        textField2.setBounds(550,150, 200,30);
        //create submit button
        
        b1 = new JButton("Entra"); //set label to button
        b1.setBounds(500,210, 200,30);
        b1.addActionListener(this);
        b1.setBackground(Color.white);
		
        Wrong = new JLabel();
        Wrong.setText("Credenziali Errate, riprova");
        Wrong.setVisible(false);
        Wrong.setBounds(550,175, 200,30);
        Wrong.setForeground(Color.RED.darker());
        
        
        
        newPanel = new JPanel();
        newPanel.setLayout(null);
        newPanel.setSize(1200,600);
      //  newPanel.setBackground(Color.white.darker());
        newPanel.add(costast);
        newPanel.add(userLabel);    //set username label to panel
        newPanel.add(textField1);   //set text field to panel
        newPanel.add(passLabel);    //set password label to panel
        newPanel.add(textField2);   //set text field to panel
        newPanel.add(b1);           //set button to panel
        newPanel.add(Forgot);
        newPanel.add(errorecar);
        newPanel.add(Wrong);
        newPanel.add(errorematricola);
        newPanel.add(errorepassword);
        newPanel.add(erroreempty);
        add(newPanel);
		newPanel.setVisible(true);
		
		

		
		
        Forgot.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // the user clicks on the label
            	ModuloForgot forgot= new ModuloForgot();
            	forgot.setSize(1200,600);
            	forgot.setVisible(true);
                dispose();
            }

        });
        
	}


	public void StartModuloLogin() {
		// TODO Auto-generated method stub
		
        newPanel = new JPanel();
        newPanel.setLayout(null);
        newPanel.setSize(1200,600);
        newPanel.add(costast);
        newPanel.add(userLabel);    //set username label to panel
        newPanel.add(textField1);   //set text field to panel
        newPanel.add(passLabel);    //set password label to panel
        newPanel.add(textField2);   //set text field to panel
        newPanel.add(b1);           //set button to panel
        newPanel.add(Forgot);
        newPanel.add(Wrong);
        newPanel.add(errorematricola);
        newPanel.add(errorepassword);
        newPanel.add(erroreempty);
        add(newPanel);
		newPanel.setVisible(true);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
        String userValue = textField1.getText();        //get user entered username from the textField1
        String passValue = textField2.getText();        //get user entered pasword from the textField2
        boolean s = userValue.matches(".*[a-zA-Z]+.*");

        if(passValue.equals("") && userValue.equals("")) {
        	errorecar.setVisible(false);
        	errorematricola.setVisible(false);
        	errorepassword.setVisible(false);
        	erroreempty.setVisible(true);
        	Wrong.setVisible(false);
        }
        else if(passValue.equals("")) {
        	errorecar.setVisible(false);
        	errorematricola.setVisible(false);
        	errorepassword.setVisible(true);
        	erroreempty.setVisible(false);
        	Wrong.setVisible(false);
        }
        else if(userValue.equals(""))
        {
        	errorecar.setVisible(false);
            Wrong.setVisible(false);
        	errorematricola.setVisible(true);
        	errorepassword.setVisible(false);
        	erroreempty.setVisible(false);
        }
        else if(s == true) {
        	errorecar.setVisible(true);
            Wrong.setVisible(false);
        	errorematricola.setVisible(false);
        	errorepassword.setVisible(false);
        	erroreempty.setVisible(false);
        }
        else {
            int matricola = Integer.parseInt(textField1.getText());
            ControlLogin login = new ControlLogin();
            if(login.verificaCredenziali(matricola, passValue)) {
            	//utente esistente, creo la entity
            	Utente utente = new Utente();
            	utente = login.createEntity(matricola);
            	if(utente.getIsAdmin()==false && utente.getIsDDL()==false) {
            		//utente è impiegato
            		ModuloDashBoard dashboard = new ModuloDashBoard(utente);
                    //	dashboard.StartModuloDashBoard(userValue);
                    dashboard.setSize(1200,600);
                    dashboard.setVisible(true);
                	dispose();
            	} else if (utente.getIsAdmin()==true && utente.getIsDDL()==false) {
            		//utente è admin
            		ModuloDashBoard dashboard = new ModuloDashBoard(utente);
                    //	dashboard.StartModuloDashBoard(userValue);
                    dashboard.setSize(1200,600);
                    dashboard.setVisible(true);
                	dispose();
            	} else if(utente.getIsAdmin()==true && utente.getIsDDL()==true){
            		//utente è DDL
            		ModuloDashBoard dashboard = new ModuloDashBoard(utente);
                    //	dashboard.StartModuloDashBoard(userValue);
                    dashboard.setSize(1200,600);
                    dashboard.setVisible(true);
                	dispose();
            	}
            } else {
            	//utente non esiste
            	 //show error message
                System.out.println("Please enter valid username and password");
                Wrong.setVisible(true);
            	errorematricola.setVisible(false);
            	errorepassword.setVisible(false);
            	erroreempty.setVisible(false);
            }
        }
  
	
	} 
}
