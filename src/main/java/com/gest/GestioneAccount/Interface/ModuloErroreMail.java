package com.gest.GestioneAccount.Interface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ModuloErroreMail extends JFrame implements ActionListener{

	JPanel panel;
	JLabel testoerrore;
	JButton ok;
	
	
	ModuloErroreMail(){
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		testoerrore = new JLabel("Errore, mail non trovata nel sistema!");
		testoerrore.setForeground(Color.white);
		testoerrore.setBounds(50,10,240,50);
		
		ok = new JButton("OK");
		ok.setBackground(Color.white);
		ok.setBounds(100,50,100,50);
		
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setSize(300,150);
		panel.setBackground(Color.RED);
		panel.add(testoerrore);
		panel.add(ok);
		add(panel);
		panel.setVisible(true);
		ok.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		dispose();
	}
	
	
	
	
}
