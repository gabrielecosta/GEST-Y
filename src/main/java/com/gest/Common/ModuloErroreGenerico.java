package com.gest.Common;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;

import com.gest.Entity.Utente;

public class ModuloErroreGenerico extends JFrame implements ActionListener{

	JPanel panel;
	JLabel ImgErrore, testoerrore,testoerrore2;
	JButton Ok;
	Utente utente;
	Color colore = new Color(255,224,224);
	public ModuloErroreGenerico(){
		
        setResizable(false);				
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.utente = new Utente();
		
		Ok = new JButton("Indietro");
		Ok.setBounds(250,190,100,50);
		Ok.setBackground(Color.white);
		Ok.setBorder(new LineBorder(Color.red));
		testoerrore = new JLabel();
		testoerrore.setText("Si è verificato un errore, riprovare più tardi.");
		testoerrore.setBounds(175,125,250,30);
		testoerrore.setForeground(Color.red.darker());
		
		testoerrore2 = new JLabel();
		testoerrore2.setText("Se il problema persiste, contattare un amministratore!");
		testoerrore2.setBounds(150,145,330,30);
		testoerrore2.setForeground(Color.red.darker());
		
        ImageIcon error = new ImageIcon("error.png");
    	ImgErrore = new JLabel(error);
    	ImgErrore.setBounds(250,10,100,100);
        ImgErrore.setBorder(null);
		Ok.addActionListener(this);
        
        panel = new JPanel();
        panel.setBackground(colore);
        panel.setLayout(null);
        panel.setSize(600,300);
        panel.add(ImgErrore);
        panel.add(Ok);
        panel.add(testoerrore);
        panel.add(testoerrore2);
        add(panel);
		
		
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		dispose();
		
	}
	
}



