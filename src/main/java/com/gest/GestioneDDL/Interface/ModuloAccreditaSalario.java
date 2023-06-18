package com.gest.GestioneDDL.Interface;
import javax.swing.*;

import com.gest.Entity.BustaPaga;
import com.gest.Entity.Turno;
import com.gest.Entity.Utente;
import com.gest.GestioneAccount.Interface.ModuloDashBoard;
import com.gest.GestioneDDL.Control.AccreditaSalarioCtl;
import com.gest.GestioneTurno.Control.GestioneCalendarioCTL;

import java.awt.*;
import java.awt.event.*;

public class ModuloAccreditaSalario extends JFrame implements ActionListener{
    
	 
		private Utente utente;
		JPanel menu;
		JButton indietro,accredita;
		JLabel vuoto,fatto,erroretxt;
		

		DefaultListModel<BustaPaga> l1 = new DefaultListModel<BustaPaga>();  
		JList<BustaPaga> list = new JList<BustaPaga>(l1); 
	    JScrollPane listScroller = new JScrollPane(list);
	    BustaPaga bustapaga;
	    JLabel info;
		
		
		public ModuloAccreditaSalario(Utente utente){
			setResizable(false);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.utente = new Utente();
			this.utente = utente;
			this.bustapaga = bustapaga;
			

			AccreditaSalarioCtl arraylista = new AccreditaSalarioCtl();

			fatto = new JLabel("Impiegato pagati con successo!");
			fatto.setForeground(Color.green.darker());
			fatto.setBounds(500,250,300,30);
			fatto.setVisible(false);
			
			erroretxt = new JLabel("Errore! Non ci sono impiegati da pagare :)");
			erroretxt.setForeground(Color.red.darker());
			erroretxt.setBounds(480,380,300,30);
			erroretxt.setVisible(false);
			
			info = new JLabel();
			info.setText("Impiegati da pagare");      //set label value for textField1
			info.setBounds(480,50,300,50);
		    info.setForeground(Color.CYAN.darker());
		    info.setFont(new Font("Arial", Font.PLAIN, 30));
			
			indietro= new JButton("Indietro");
			indietro.setBounds(450,500,300,50);
			indietro.setBackground(Color.white);
		
			accredita= new JButton("Accredita");
			accredita.setBounds(450,440,300,50);
			accredita.setBackground(Color.white);
			
		//	  DefaultListModel<String> l1 = new DefaultListModel<>(); 

			  
			for(BustaPaga bustapaga: arraylista.getBustePagaDaPagare())
			{
				l1.addElement(bustapaga);
			}
		    listScroller.setBounds(300,100,600,250);
			
			
			menu = new JPanel();
			menu.setLayout(null);
			menu.setSize(1200,600);
			menu.setVisible(true);
	        menu.add(info);
			menu.add(listScroller);
			menu.add(accredita);
			menu.add(indietro);
			menu.add(erroretxt);
			menu.add(fatto);
			add(menu);
		    indietro.addActionListener(this);	
		    accredita.addActionListener(this::Accredita);
		    
		}
		private void Accredita (ActionEvent e) {
			
			if(list.isSelectionEmpty() == true) {
				erroretxt.setVisible(true);
			}
			else {
				AccreditaSalarioCtl accreditaCtl = new AccreditaSalarioCtl();
				if(accreditaCtl.checkGiornoPaga()==true) {
					erroretxt.setVisible(false);
					listScroller.setVisible(false);
					info.setVisible(false);
					accredita.setVisible(false);
					fatto.setVisible(true);
					indietro.setBounds(450,275,300,50);
					accreditaCtl.accreditaSalario();
				} else {
					erroretxt.setVisible(true);
					listScroller.setVisible(false);
					info.setVisible(false);
					accredita.setVisible(false);
					fatto.setVisible(false);
					indietro.setBounds(450,275,300,50);
				}
			}
			
			
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

	
