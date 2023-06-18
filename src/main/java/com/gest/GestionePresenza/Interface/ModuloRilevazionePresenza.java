package com.gest.GestionePresenza.Interface;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import com.gest.Entity.Utente;
import com.gest.GestioneAccount.Interface.ModuloDashBoard;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.lang.Exception;
public class ModuloRilevazionePresenza extends JFrame implements ActionListener{
    
	private Utente utente;
	JPanel menu_rilevazionepresenza;
	JButton indietro;
	
	
	ModuloRilevazionePresenza(Utente utente){
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.utente = new Utente();
		this.utente = utente;
		
		indietro= new JButton("Indietro");
		indietro.setBounds(500,200,200,100);
		indietro.setBackground(Color.white);
		
		menu_rilevazionepresenza = new JPanel();
		menu_rilevazionepresenza.setLayout(null);
		menu_rilevazionepresenza.setSize(1200,600);
		menu_rilevazionepresenza.setVisible(true);
		menu_rilevazionepresenza.add(indietro);
		add(menu_rilevazionepresenza);
	    indietro.addActionListener(this);	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ModuloDashBoard dashboard = new ModuloDashBoard(utente);
        dashboard.setSize(1200,600);
        dashboard.setVisible(true);
    	dispose();
	}

	
	
	
}