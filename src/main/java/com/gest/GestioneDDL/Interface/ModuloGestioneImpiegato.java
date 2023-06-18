package com.gest.GestioneDDL.Interface;
import javax.swing.*;

import com.gest.Entity.Utente;
import com.gest.GestioneAccount.Interface.ModuloDashBoard;

import java.awt.*;
import java.awt.event.*;
public class ModuloGestioneImpiegato extends JFrame implements ActionListener{
    
	private Utente utente;
	JPanel menu;
	JButton indietro,assumi,licenzia,modifica;
	JLabel info;
	
	
	public ModuloGestioneImpiegato(Utente utente){
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.utente = new Utente();
		this.utente = utente;
		
		info = new JLabel();
		info.setText("Gestione Impiegati");      //set label value for textField1
		info.setBounds(480,50,300,50);
	    info.setFont(new Font("Arial", Font.PLAIN, 30));
		
	    assumi = new JButton("Assumi"); //set label to button
	    assumi.setBounds(500,120, 200,80);
	    assumi.setFont(new Font("Arial", Font.PLAIN, 16));
	    assumi.setBackground(Color.white);

    	licenzia = new JButton("Licenzia");
    	licenzia.setBounds(500,210, 200,80);
    	licenzia.setFont(new Font("Arial", Font.PLAIN, 14));
    	licenzia.setBackground(Color.white);
    	
    	modifica = new JButton("Modifica");
    	modifica.setBounds(500,300, 200,80);
    	modifica.setFont(new Font("Arial", Font.PLAIN, 16));
    	modifica.setBackground(Color.white);
    	
	    indietro = new JButton("Indietro");
	    indietro.setBounds(500,390, 200,80);
    	indietro.setFont(new Font("Arial", Font.PLAIN, 16));
    	indietro.setBackground(Color.white);
		
		menu = new JPanel();
		menu.setLayout(null);
		menu.setSize(1200,600);
		menu.setVisible(true);
		menu.add(assumi);
		menu.add(licenzia);
		menu.add(info);
		menu.add(modifica);
		menu.add(indietro);
		add(menu);
	    indietro.addActionListener(this);	
	    assumi.addActionListener(this::Assumi);
	    licenzia.addActionListener(this::Licenzia);
	    modifica.addActionListener(this::Modifica);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ModuloDashBoard dashboard = new ModuloDashBoard(utente);
        dashboard.setSize(1200,600);
        dashboard.setVisible(true);
    	dispose();
	}
	private void Assumi(ActionEvent e) {
		ModuloAssunzione assunzione = new ModuloAssunzione(utente);
		assunzione.setSize(1200,600);
		assunzione.setVisible(true);
		dispose();
	}
	private void Licenzia(ActionEvent e) {
		ModuloLicenziamento licenziamento = new ModuloLicenziamento(utente);
		licenziamento.setSize(1200,600);
		licenziamento.setVisible(true);
		dispose();
	}
	private void Modifica(ActionEvent e) {
		ModuloModificaRuolo modificaruolo = new ModuloModificaRuolo(utente);
		modificaruolo.setSize(1200,600);
		modificaruolo.setVisible(true);
		dispose();
	}

	
	
	
}