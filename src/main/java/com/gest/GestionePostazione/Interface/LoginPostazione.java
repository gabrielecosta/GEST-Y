package com.gest.GestionePostazione.Interface;
import javax.swing.*;

import com.gest.GestionePostazione.Control.registraPresenzaCtl;

import java.awt.*;
import java.awt.event.*;
import java.lang.Exception;

class createform extends JFrame
{
    JPanel newPanel;
    JButton submit, ingresso, uscita, indietro, inviomail;
    JLabel nome, cognome, matricola,costast,testouscita,testoentrata,testoerrore,wel_label, Wrong,dashboardlogo;
    final JTextField textField1, textField2, textField3;
    boolean entrato = false;

    String userValue;

    createform() {
    	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	setResizable(false);
        ImageIcon dashboardad = new ImageIcon("dashboard.png");
        dashboardlogo = new JLabel();
        dashboardlogo.setText("");
        dashboardlogo.setBounds(475,20, 249,39);
        dashboardlogo.setIcon(dashboardad);

        Wrong = new JLabel();
        Wrong.setText("Credenziali Errate, riprova");
        Wrong.setVisible(false);
        Wrong.setBounds(525, 300, 200, 60);


        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        nome = new JLabel();
        nome.setText("Nome: ");
        nome.setBounds(450,100, 200,30);
        cognome = new JLabel();
        cognome.setText("Cognome: ");
        cognome.setBounds(450,150, 200,30);
        matricola = new JLabel();
        matricola.setText("Matricola: ");
        matricola.setBounds(450, 200, 200, 30);

        textField1 = new JTextField(15);    //set length of the text
        textField1.setBounds(550,100, 200,30);

        textField2 = new JTextField(15);    //set length of the text
        textField2.setBounds(550,150, 200,30);

        textField3 = new JTextField(15);    //set length of the text
        textField3.setBounds(550,200, 200,30);

        submit = new JButton("Entra");
        submit.setBounds(500, 250, 200, 60);
        submit.setBackground(Color.white);

       
        newPanel = new JPanel();
        newPanel.setLayout(null);
        newPanel.setSize(1200, 600);

        newPanel.add(nome);
        newPanel.add(cognome);
        newPanel.add(matricola);
        newPanel.add(textField1);
        newPanel.add(textField2);
        newPanel.add(textField3);
        newPanel.add(submit);
        newPanel.add(Wrong);
        newPanel.add(dashboardlogo);

        add(newPanel);
        setTitle("Login Aziendale");

        submit.addActionListener(this::submit);


    }

   
    private void submit(ActionEvent e){


        String userValue = textField1.getText();        //get user entered username from the textField1
        String passValue = textField2.getText();        //get user entered pasword from the textField2
        String matValue = textField3.getText();        //get user entered pasword from the textField2
        System.out.println(matValue);
        // DICHIARIAMO LA CONTROL DEL LOGIN
        //check whether the credentials are authentic or not
        registraPresenzaCtl registra = new registraPresenzaCtl();
        if (registra.checkImpiegato(Integer.parseInt(matValue), userValue, passValue)) {  //if authentic, navigate user to a new page

            Wrong.setVisible(false);
            DashboardPostazione form = new DashboardPostazione(Integer.parseInt(matValue));
            form.setSize(1200, 600);  //set size of the frame
            form.setVisible(true);  //make form visible to the user
            dispose();
        }
        else{
            //show error message
            System.out.println("Please enter valid username and password");
            Wrong.setVisible(true);
        }

    }
   
}


public class LoginPostazione {
    public static void main(String arg[]) {

        try {
            //create instance of the CreateLoginForm
            createform form = new createform();
            form.setSize(1200, 600);  //set size of the frame
            form.setVisible(true);  //make form visible to the user
        } catch (Exception e) {
            //handle exception
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}