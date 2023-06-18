package com.gest.GestioneImpiegato.Interface;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import com.gest.Entity.Turno;
import com.gest.Entity.Utente;
import com.gest.GestioneTurno.Control.GestioneCalendarioCTL;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.lang.Exception;
class ModuloROL extends JFrame implements ActionListener{
    
	private Utente utente;
	private Turno turno;
	JPanel menu;
	JButton indietro;
	JLabel IDtxt, motivotxt, oretxt, success,errorevuoto,errorenumero,result,errorerichiesta;
	JButton invia; //MAX 2 ORE DI STACCO
	JTextField ore;
	SpinnerModel spinnerModel = new SpinnerNumberModel(1,1,2,1);
	JSpinner anlist = new JSpinner(spinnerModel);
	String motivo = "Entrata Posticipata";
    JCheckBox checkBox1 = new JCheckBox("Entrata Posticipata",true);  
    JCheckBox checkBox2 = new JCheckBox("Uscita Anticipata",false);  
	
	ModuloROL(Utente utente, String ID,Turno turno){
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.utente = new Utente();
		this.utente = utente;
		this.turno = new Turno();
		this.turno = turno;
		
		anlist.setBounds(620,180,50,30);
		
		invia = new JButton("Richiedi Permesso");
		invia.setBounds(450,385,300,50);
		invia.setBackground(Color.white);
		
		result = new JLabel();
		result.setBounds(410,300,400,50);
		result.setVisible(false);
		
		motivotxt = new JLabel();
		motivotxt.setText("Seleziona Motivo Rol: ");
		motivotxt.setBounds(420,130,250,50);

		oretxt = new JLabel();
		oretxt.setText("Inserisci numero ore di permesso: ");
		oretxt.setBounds(420,170,250,50);
		
        ore = new JTextField(2);    //set length of the text
        ore.setBounds(550,150, 50,30);
		
		success = new JLabel();
		success.setText("Modifica Effettuata con Successo!");
		success.setBounds(420,250,250,50);
		success.setVisible(false);
		success.setForeground(Color.GREEN.darker());
		
		errorevuoto = new JLabel();
		errorevuoto.setText("Errore, inserire un quantitativo di ore di permesso");
		errorevuoto.setBounds(420,250,250,50);
		errorevuoto.setVisible(false);
		errorevuoto.setForeground(Color.RED.darker());
		
		errorenumero = new JLabel();
		errorenumero.setText("Errore, il massimo consentito � di 2 ore");
		errorenumero.setBounds(420,250,250,50);
		errorenumero.setVisible(false);
		errorenumero.setForeground(Color.RED.darker());
		
		errorerichiesta = new JLabel();
		errorerichiesta.setText("Errore, non � possibile effettuare questa richiesta");
		errorerichiesta.setBounds(420,200,400,50);
		errorerichiesta.setVisible(false);
		errorerichiesta.setForeground(Color.RED.darker());
		
        checkBox1.setBounds(550,130, 150,50);  
        checkBox2.setBounds(725,130, 150,50);  
        
        checkBox1.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
              motivo = "Entrata Posticipata";
            }
        });
        checkBox2.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
            	motivo = "Uscita Anticipata";
            }
        });
        
        
        
        final ButtonGroup bg = new ButtonGroup();
        bg.add(checkBox1);
        bg.add(checkBox2);
		
		IDtxt = new JLabel();
		IDtxt.setText("Turno selezionato" + ID);
		IDtxt.setBounds(420,100,400,50);
		IDtxt.setForeground(Color.GREEN.darker());
		
		indietro= new JButton("Indietro");
		indietro.setBounds(450,435,300,50);
		indietro.setBackground(Color.white);
		
		menu = new JPanel();
		menu.setLayout(null);
		menu.add(result);
		menu.setSize(1200,600);
		menu.setVisible(true);
		menu.add(IDtxt);
		menu.add(checkBox1);
		menu.add(checkBox2);
		menu.add(motivotxt);
		menu.add(success);
		menu.add(indietro);
		menu.add(invia);
		menu.add(errorerichiesta);
		menu.add(errorenumero);
		menu.add(errorevuoto);
		menu.add(ore);
		menu.add(oretxt);
		menu.add(anlist);
		add(menu);
	    indietro.addActionListener(this);	
	    invia.addActionListener(this::Invia);
	}
	public void Invia(ActionEvent e) {
		String stringa =  anlist.getValue().toString();
		int hours = Integer.parseInt(stringa);
		int tipo = 0;
		if(checkBox1.isSelected()) {
			//entrata posticipata
			tipo = 0;
		} else if (checkBox2.isSelected()) {
			//uscita anticipata
			tipo = 1;
		}
		GestioneCalendarioCTL gestione = new GestioneCalendarioCTL();
		if(gestione.richiediROL(this.turno, tipo, hours) ) {
			result.setText("Permesso di ore:" + " " + anlist.getValue() + " " + "per" + " " + motivo + " " + "richiesto con successo");
		       result.setVisible(true);	
		       result.setForeground(Color.green.darker());
		       
		       IDtxt.setVisible(false);
		       motivotxt.setVisible(false);
		       errorenumero.setVisible(false);
		       errorevuoto.setVisible(false);
		       ore.setVisible(false);
		       oretxt.setVisible(false);
		       anlist.setVisible(false);
		       invia.setVisible(false);
		       errorerichiesta.setVisible(false);
		       checkBox1.setVisible(false);
		       checkBox2.setVisible(false);
				indietro.setBounds(450,250,300,50);
				result.setBounds(410,200,400,50);
		}else {
			errorerichiesta.setVisible(true);
			
			
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