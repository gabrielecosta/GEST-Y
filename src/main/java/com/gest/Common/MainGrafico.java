package com.gest.Common;
import javax.swing.*;

import com.gest.GestioneAccount.Interface.ModuloLogin;

import java.awt.*;
import java.awt.event.*;
import java.lang.Exception;

public class MainGrafico extends JFrame{
public JFrame Main;

MainGrafico(){
	
}
	    public static void main(String arg[]) {
	        try {
	            //Instanziamo la funzione che mette a schermo la nostra prima schermata

               ModuloLogin();
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, e.getMessage());
	        }
	    }
	    //Con questa funzione instanziamo ModuloLogin e lo mettiamo a schermo
	    public static void ModuloLogin() {
	    	 ModuloLogin form = new ModuloLogin();
	            form.setSize(1200, 600);  //set size of the frame
	            form.setVisible(true);  //make form visible to the user
	    }
	}
	

	
	

