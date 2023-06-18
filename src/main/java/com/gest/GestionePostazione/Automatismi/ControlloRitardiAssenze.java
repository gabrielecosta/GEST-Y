package com.gest.GestionePostazione.Automatismi;

import java.time.LocalTime;

import com.gest.GestionePostazione.Control.notificaRitardoCtl;


public class ControlloRitardiAssenze {
	public static void main(String[] args) {
		notificaRitardoCtl notifica = new notificaRitardoCtl();
		
		notifica.checkRitardoIngressoListaTurni();
		notifica.checkRitardoUscitaListaTurni();
		notifica.checkAssenzaListaTurni();
		
	}
}
