package com.gest.GestionePostazione.Interface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.gest.Entity.Turno;
import com.gest.GestionePostazione.Control.registraPresenzaCtl;

public class DashboardPostazione extends JFrame implements ActionListener{
	JPanel  dashboard;
	JLabel  testoentrata,testouscita,testoerrore,costast,wel_label;
	JButton ingresso,uscita,indietro;
	Boolean entrato;
	private int matricola;
	private Turno turno;
	
public DashboardPostazione(int matricola){
	this.matricola = matricola;
	//controllo se è in turno:
	registraPresenzaCtl registra = new registraPresenzaCtl();
	
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setResizable(false);
	 testoentrata = new JLabel();
     testoentrata.setText("Entrata registrata con successo!");
     testoentrata.setBounds(504,70, 192,100);

     testouscita = new JLabel();
     testouscita.setText("Uscita registrata con successo!");
     testouscita.setBounds(504,70, 200,100);

     testoerrore= new JLabel();
     testoerrore.setText("");
     testoerrore.setBounds(20, 310, 200, 60);
     testoerrore.setForeground(Color.red.darker());
     testoerrore.setVisible(false);
     
     ImageIcon costas = new ImageIcon("dashboard.png");
     costast = new JLabel();
     costast.setText("");
     costast.setBounds(475,20, 249,39);
     costast.setIcon(costas);
     
     wel_label = new JLabel();
     wel_label.setBounds(475,20, 200,100);

     ingresso = new JButton("Registra Ingresso");
     ingresso.setBounds(450,140, 300,150);
     ingresso.setBackground(Color.white);

     uscita = new JButton("Registra Uscita");
     uscita.setBounds(450,140, 300,150);
     uscita.setBackground(Color.white);
     uscita.setVisible(false);
     indietro = new JButton("Indietro");
     indietro.setBounds(450,310, 300,150);
     indietro.setBackground(Color.white);
     
     this.turno = registra.getTurno(matricola);
 		if(registra.isPresente(turno)) {
 		//impiegato in turno
 			//adesso devo disabilitare un bottone in ingresso o in uscita
 			ingresso.setVisible(registra.enableFirmaIngresso(turno,matricola));
 			uscita.setVisible(registra.enableFirmaUscita(turno,matricola));
 			testoentrata.setVisible(false);
 			testouscita.setVisible(false);
 			testoerrore.setVisible(false);
 			if(registra.enableFirmaIngresso(turno,matricola)==false) {
 				testoerrore.setText("Impossibile timbrare la presenza di ingresso");
 			} else if (registra.enableFirmaUscita(turno,matricola)==false) {
 				testoerrore.setText("Impossibile timbrare la presenza di uscita");
 			}
 		} else if (registra.isPresente(turno)==false){
 		//impiegato non in turno
 			ingresso.setVisible(registra.enableFirmaIngresso(turno,matricola));
 			uscita.setVisible(registra.enableFirmaUscita(turno,matricola));
 			testoentrata.setVisible(false);
 			testouscita.setVisible(false);
 			testoerrore.setVisible(true);
 			testoerrore.setText("Errore! Utente non in turno nella giornata odierna");
 		}
     
     
     dashboard = new JPanel();
     dashboard.setLayout(null);
     dashboard.setSize(1200, 600);

     dashboard.add(testoentrata);
     dashboard.add(testouscita);
     dashboard.add(testoerrore);
     dashboard.add(costast);
     dashboard.add(wel_label);
     dashboard.add(ingresso);
     dashboard.add(uscita);
     dashboard.add(indietro);

     add(dashboard);
     setTitle("Login Aziendale");
     indietro.addActionListener(this);
     ingresso.addActionListener(this::entrata);
     uscita.addActionListener(this::uscita);
}
private void entrata(ActionEvent e) {
    testoentrata.setVisible(true);
    testouscita.setVisible(false);
    testoerrore.setVisible(false);
    entrato = true;
    indietro.setBounds(450,140, 300,150);
    ingresso.setVisible(false);
    registraPresenzaCtl registra = new registraPresenzaCtl();
    registra.firmaIngresso(turno, matricola);
}

private void uscita(ActionEvent e){
    testoentrata.setVisible(false);
    testouscita.setVisible(true);
    testoerrore.setVisible(false);
    entrato = false;
    indietro.setBounds(450,140, 300,150);
    uscita.setVisible(false);
    registraPresenzaCtl registra = new registraPresenzaCtl();
    registra.firmaUscita(turno, matricola);
}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	createform form = new createform();
    form.setSize(1200, 600);  //set size of the frame
    form.setVisible(true);  //make form visible to the user
    dispose();
}
}
