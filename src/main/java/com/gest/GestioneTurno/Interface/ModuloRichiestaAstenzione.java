package com.gest.GestioneTurno.Interface;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;
//import com.toedter.calendar.JDateChooser;

import com.gest.Common.SendEmail;
import com.gest.Entity.Utente;
import com.gest.GestioneAccount.Interface.ModuloDashBoard;
import com.gest.GestioneTurno.Control.GestioneAssenza;
import com.gest.GestioneTurno.Control.RichiediAstensioneCTL;


public class ModuloRichiestaAstenzione extends JFrame implements ActionListener {

	JButton Indietro, Invia, Seleziona;
	JLabel RichiestaTXT, l,CheckTXT, DataTXT, Data2TXT,iniziotxt,finetxt,tipocongedo,successo,erroremotivo,erroredate,erroregenerico;
	JPanel Modulo;
	JTextField Oggetto;
    String Motivo, in, fi;
    File j;
    String filename;
	private Utente utente;
	
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	JFormattedTextField Inizio = new JFormattedTextField(format);
	JFormattedTextField Fine = new JFormattedTextField(format);
	
    JCheckBox checkBox1 = new JCheckBox("Malattia",true); 
    JCheckBox checkBox2 = new JCheckBox("Congedo",false); 
    JCheckBox checkBox3 = new JCheckBox("Ferie",false);  
    JCheckBox checkBox4 = new JCheckBox("Sciopero",false);  
    JCheckBox checkBox5 = new JCheckBox("Familiare",true);  
    JCheckBox checkBox6 = new JCheckBox("Lutto",false);  
	
	
	public ModuloRichiestaAstenzione(Utente utente){
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.utente = new Utente();
		this.utente = utente;
		
		Inizio.setBounds(350,100,200,30);
		Fine.setBounds(350,150,200,30);
		
		DataTXT = new JLabel("Seleziona intervallo date:");
	    DataTXT.setBounds(350,50,200,50);
	    
		Data2TXT = new JLabel("FORMATO: DD/MM/YYYY");
	    Data2TXT.setBounds(350,65,200,50);
	    Data2TXT.setForeground(Color.GRAY.darker());
	    
		successo = new JLabel("Richiesta Inoltrata con successo");
		successo.setBounds(510,230,400,30);
		successo.setForeground(Color.GREEN.darker());
	    successo.setVisible(false);
	    
		iniziotxt = new JLabel("Data Inizio:");
		iniziotxt.setBounds(280,90,200,50);
	    
		finetxt = new JLabel("Data Fine:");
	    finetxt.setBounds(280,140,200,50);
	    
		tipocongedo = new JLabel("Seleziona tipo Congedo");
	    tipocongedo.setBounds(600,200,250,50);
		
		Indietro = new JButton("Indietro");
	    Indietro.setBounds(500,430,200,50);
	    Indietro.setBackground(Color.white);
	    
		Invia = new JButton("Invia");
	    Invia.setBounds(500,310,200,50);
	    Invia.setBackground(Color.white);
	    
		Seleziona = new JButton("Seleziona");
	    Seleziona.setBounds(500,370,200,50);
	    Seleziona.setBackground(Color.white);
		
		RichiestaTXT = new JLabel("Inserisci Oggetto Richiesta:");
		RichiestaTXT.setBounds(370,250,250,50);
		
		CheckTXT = new JLabel("Seleziona Motivo Richiesta:");
		CheckTXT.setBounds(600,50,250,50);
		
		l = new JLabel("Nessun File Selezionato");
		l.setBounds(790,250,250,50);
		

        checkBox1.setBounds(600,90, 100,50);  

        checkBox2.setBounds(600,140, 150,50);  

        checkBox3.setBounds(750,90, 100,50);  

        checkBox4.setBounds(750,140, 100,50);  

        checkBox5.setBounds(750,200, 100,50);  

        checkBox6.setBounds(850,200, 100,50);  
        checkBox5.setVisible(false);
        checkBox6.setVisible(false);
        tipocongedo.setVisible(false);
        
        final ButtonGroup bg = new ButtonGroup();
        bg.add(checkBox1);
        bg.add(checkBox2);
        bg.add(checkBox3);
        bg.add(checkBox4);
        
        final ButtonGroup bg2 = new ButtonGroup();
        bg2.add(checkBox5);
        bg2.add(checkBox6);
        
        checkBox1.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                Seleziona.setVisible(true);
                l.setVisible(true);
                Indietro.setBounds(500,430,200,50);
                checkBox5.setVisible(false);
                checkBox6.setVisible(false);
                tipocongedo.setVisible(false);
                finetxt.setVisible(true);
                Fine.setVisible(true);
            }
        });
        checkBox2.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                Seleziona.setVisible(true);
                l.setVisible(true);
                Indietro.setBounds(500,430,200,50);
                checkBox5.setVisible(true);
                checkBox6.setVisible(true);
                tipocongedo.setVisible(true);
                finetxt.setVisible(true);
                Fine.setVisible(true);
            }
        });
        checkBox3.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
             Seleziona.setVisible(false);
             l.setVisible(false);
             Indietro.setBounds(500,370,200,50);
             checkBox5.setVisible(false);
             checkBox6.setVisible(false);
             tipocongedo.setVisible(false);
             finetxt.setVisible(true);
             Fine.setVisible(true);
            }
        });
        checkBox4.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                Seleziona.setVisible(true);
                l.setVisible(true);
                Indietro.setBounds(500,430,200,50);
                checkBox5.setVisible(false);
                checkBox6.setVisible(false);
                tipocongedo.setVisible(false);
                
                finetxt.setVisible(false);
                Fine.setVisible(false);
            	
            }
        });
        
		Oggetto = new JTextField("");
		Oggetto.setBounds(570,260, 200,30);
		
		erroremotivo = new JLabel("Errore, inserisci una motivazione!");
		erroremotivo.setBounds(580,220,210,30);
		erroremotivo.setForeground(Color.red.darker());
		erroremotivo.setVisible(false);
		
		erroregenerico = new JLabel("Errore! Non è stato possibile elaborare la richiesta");
		erroregenerico.setBounds(370,230,400,30);
		erroregenerico.setForeground(Color.red.darker());
		erroregenerico.setVisible(false);
		
		erroredate = new JLabel("Errore, inserisci un valido intervallo date!");
		erroredate.setBounds(580,220,300,30);
		erroredate.setForeground(Color.red.darker());
		erroredate.setVisible(false);
		Modulo = new JPanel();
		Modulo.setLayout(null);
        Modulo.setSize(1200,600);
        Modulo.setVisible(true);
        
        Modulo.add(l);
        Modulo.add(Indietro);
        Modulo.add(Invia);
        Modulo.add(Seleziona);
        Modulo.add(RichiestaTXT);
        Modulo.add(Oggetto);
        Modulo.add(CheckTXT);
        Modulo.add(iniziotxt);
        Modulo.add(finetxt);
        Modulo.add(Inizio);
        Modulo.add(Fine);
        Modulo.add(checkBox1);
        Modulo.add(checkBox2);
        Modulo.add(checkBox3);
        Modulo.add(checkBox4);
        Modulo.add(checkBox5);
        Modulo.add(checkBox6);
        Modulo.add(tipocongedo);
        Modulo.add(DataTXT);
        Modulo.add(Data2TXT);
        Modulo.add(erroredate);
        Modulo.add(erroregenerico);
        Modulo.add(successo);
        Modulo.add(erroremotivo);
        add(Modulo);
        Indietro.addActionListener(this);
		Seleziona.addActionListener(this::Selezione);
		Invia.addActionListener(this::Invia);
		setFile(null);
		setFileName(null);
		
		

    }
	

	public void Invia(ActionEvent e) {
		RichiediAstensioneCTL richiediAstenzione = new RichiediAstensioneCTL();
		
		Motivo = Oggetto.getText();
		//QUI FARE TRY CATCH
    	LocalDate In = LocalDate.parse(Inizio.getText(),dateFormat);
    	LocalDate Fi = LocalDate.parse(Fine.getText(),dateFormat);
    	in = Inizio.getText();
    	fi = Fine.getText();
    	
		if(Motivo.equals("")) {
			erroremotivo.setVisible(true);
			erroredate.setVisible(false);
		}
    	else if(in.equals(null)){
    		erroredate.setVisible(true);
    		erroremotivo.setVisible(false);
    	}
    	else if(fi.equals(null)){
    		erroredate.setVisible(true);
    		erroremotivo.setVisible(false);
    	}
  //  	else if(In.isAfter(Fi)) {
  //  		erroredate.setVisible(true);
  //  		erroremotivo.setVisible(false);
  //  	}
    	else if(checkBox1.isSelected()) { //MALATTIA
    		if(richiediAstenzione.richiediMalattia(this.utente.getMatricola(), Oggetto.getText(), "https://titanic", In, Fi)) {
    			GestioneAssenza gestioneAssenza = new GestioneAssenza(In, Fi, this.utente.getMatricola());
    			//gestioneAssenza.aggiungiIndisponibilita(this.utente.getMatricola(), In, Fi);
    			gestioneAssenza.aggiungiRichiestaMalattia(this.utente.getMatricola(), Oggetto.getText(), "https://titanic", In, Fi);
    			gestioneAssenza.gestioneAssenza();
        		erroredate.setVisible(false);
    			erroremotivo.setVisible(false);
    			
    	        SendEmail sender = new SendEmail();
    			sender.sendEmailAssenzione(Motivo,this.getFile(),this.getFileName()); // INVIO MAIL CON ALLEGATO
    			Conferma();
    		} else {
    			//qua ci va l'errore
    			Errore();
    		}
    	}else if(checkBox2.isSelected()) { //CONGEDO:
    		if(checkBox5.isSelected()) { //1FAMILIARE
    		   	if(richiediAstenzione.richiediCongedo("congedo parentale", this.utente.getMatricola(), Oggetto.getText(), "http/bella_bionda", In, Fi)) {
    		   		GestioneAssenza gestioneAssenzaCtl = new GestioneAssenza(In, Fi, this.utente.getMatricola());
    		   		//gestioneAssenza.aggiungiIndisponibilita(this.utente.getMatricola(), In, Fi);
    		   		gestioneAssenzaCtl.aggiungiRichiestaCongedoParentale("congedo parentale", this.utente.getMatricola(), Oggetto.getText(), "http/bella_bionda", In, Fi);
    		   		gestioneAssenzaCtl.gestioneAssenza();
    	    		erroredate.setVisible(false);
    				erroremotivo.setVisible(false);
    				
    		        SendEmail sender = new SendEmail();
    				sender.sendEmailAssenzione(Motivo,this.getFile(),this.getFileName()); // INVIO MAIL CON ALLEGATO
    				Conferma();
    		   	} else {
    		   		Errore();
    		   	}
        	}else if(checkBox6.isSelected()) { //2LUTTO
        		if(richiediAstenzione.richiediCongedo("congedo lutto", this.utente.getMatricola(), Oggetto.getText(), "https://titanic", In, Fi)) {
        			GestioneAssenza gestioneAssenza = new GestioneAssenza(In, Fi, this.utente.getMatricola());
    		   		//gestioneAssenza.aggiungiIndisponibilita(this.utente.getMatricola(), In, Fi);
    		   		gestioneAssenza.aggiungiRichiestaCongedoParentale("congedo lutto", this.utente.getMatricola(), Oggetto.getText(), "https://titanic", In, Fi);
    		   		gestioneAssenza.gestioneAssenza();
    	    		erroredate.setVisible(false);
    				erroremotivo.setVisible(false);
    		        SendEmail sender = new SendEmail();
    				sender.sendEmailAssenzione(Motivo,this.getFile(),this.getFileName()); // INVIO MAIL CON ALLEGATO
    				Conferma();
    		   	} else {
    		   		Errore();
    		   	}
        	}
   	
    	}else if(checkBox3.isSelected()) { //FERIE
    		if(richiediAstenzione.richiediFerie(this.utente.getMatricola(), Oggetto.getText(), "https://titanic", In, Fi)) {
    			GestioneAssenza gestioneAssenza = new GestioneAssenza(In, Fi, this.utente.getMatricola());
		   		gestioneAssenza.aggiungiIndisponibilita(this.utente.getMatricola(), In, Fi);
	    		erroredate.setVisible(false);
				erroremotivo.setVisible(false);
				
				SendEmail sender = new SendEmail();
				sender.sendEmailAstenzione2(Motivo);
				Conferma();
    		} else {
    			Errore();
    		}
    	}else if(checkBox4.isSelected()) { //SCIOPERO
    		if(richiediAstenzione.richiediSciopero(this.utente.getMatricola(), Oggetto.getText(), "https://titanic", In)) {
    			GestioneAssenza gestioneAssenza = new GestioneAssenza(In, Fi, this.utente.getMatricola());
		   		//gestioneAssenza.aggiungiIndisponibilita(this.utente.getMatricola(), In, Fi);
		   		gestioneAssenza.aggiungiRichiestaSciopero(this.utente.getMatricola(), Oggetto.getText(), "https://titanic", In);
		   		gestioneAssenza.gestioneAssenza();
	    		erroredate.setVisible(false);
				erroremotivo.setVisible(false);
				
		        SendEmail sender = new SendEmail();
				sender.sendEmailAssenzione(Motivo,this.getFile(),this.getFileName()); // INVIO MAIL CON ALLEGATO
				Conferma();
				
    		} else {
    			Errore();
    		}
    	}
		
		
		



		
        //File j = null;
	}
	
	
	public void Conferma() {
		successo.setVisible(true);
		Indietro.setBounds(Invia.getBounds());
		
		Invia.setVisible(false);
		Seleziona.setVisible(false);
		RichiestaTXT.setVisible(false);
		Oggetto.setVisible(false);
		CheckTXT.setVisible(false);
		iniziotxt.setVisible(false);
		finetxt.setVisible(false);
		Inizio.setVisible(false);
		Fine.setVisible(false);
		checkBox1.setVisible(false);
		checkBox2.setVisible(false);
		checkBox3.setVisible(false);
		checkBox4.setVisible(false);
		checkBox5.setVisible(false);
		checkBox6.setVisible(false);
		tipocongedo.setVisible(false);
		DataTXT.setVisible(false);
		Data2TXT.setVisible(false);
		erroredate.setVisible(false);
		erroregenerico.setVisible(false);
		erroremotivo.setVisible(false);
		l.setVisible(false);
		
	}
	
	public void Errore() {
		erroregenerico.setVisible(true);
		erroremotivo.setVisible(false);
		erroredate.setVisible(false);
	}
	public void Invia2(ActionEvent e) {
		Motivo = Oggetto.getText();
        SendEmail sender = new SendEmail();
		sender.sendEmailAstenzione2(Motivo);
		
	}
	
	public File getFile() {
		return this.j;
	}
	
	public void setFile(File new_file) {
		this.j = new_file;
	}
	
	public String getFileName() {
		return this.filename;
	}
	
	public void setFileName(String pippo) {
		this.filename = pippo;
	}
	
	
	public void Selezione(ActionEvent e) {
		
		 String com = e.getActionCommand();
		 JFileChooser j_new = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		 
	        if (com.equals("save")) {
	            // create an object of JFileChooser class
	            //JFileChooser j_new = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	 
	            // invoke the showsSaveDialog function to show the save dialog
	            int r = j_new.showSaveDialog(null);
	 
	            // if the user selects a file
	            if (r == JFileChooser.APPROVE_OPTION)
	 
	            {
	                // set the label to the path of the selected file
	                l.setText(j_new.getSelectedFile().getAbsolutePath());
	                setFile(j_new.getSelectedFile());
	                setFileName(j_new.getSelectedFile().getName());
	            }
	            // if the user cancelled the operation
	            else {
	            	l.setText("the user cancelled the operation");
	            	setFile(null);
	            }
	        }
	 
	        // if the user presses the open dialog show the open dialog
	        else {
	            // create an object of JFileChooser class
	            //JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	 
	            // invoke the showsOpenDialog function to show the save dialog
	            int r = j_new.showOpenDialog(null);
	 
	            // if the user selects a file
	            if (r == JFileChooser.APPROVE_OPTION)
	           	 
	            {
	                // set the label to the path of the selected file
	                l.setText(j_new.getSelectedFile().getAbsolutePath());
	                setFile(j_new.getSelectedFile());
	                setFileName(j_new.getSelectedFile().getName());
	            }
	            // if the user cancelled the operation
	            else {
	            	l.setText("the user cancelled the operation");
	            	setFile(null);
	            }
	        }
	        
		
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
   	 ModuloDashBoard dash = new ModuloDashBoard(this.utente);
     dash.setSize(1200, 600);  //set size of the frame
     dash.setVisible(true);  //make form visible to the user
     dispose();
	}
}
