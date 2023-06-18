package com.gest.GestionePostazione.Automatismi;

import java.time.LocalDate;
import java.time.LocalTime;

import com.gest.GestionePostazione.Control.CalcoloSalarioCtl;
import com.gest.GestionePostazione.Control.GestioneCalendarioCTL;

public class PostazioneAutomatica {
	 public static void main(String[] args) {
	 
		 LocalDate today = LocalDate.now();
		 LocalTime now = LocalTime.now();
		
		 CalcoloSalarioCtl calcolo = new CalcoloSalarioCtl();
		 GestioneCalendarioCTL calendario = new GestioneCalendarioCTL();
		 
		 if(calcolo.checkGiornoCalcolo()==true) {
			 System.out.println("Giorno calcolo");
			 //calcolo il salario se è giorno di calcolo
			 calcolo.effettuaCalcolo();
		 }

		 //genera il calendario SOLO se è il 15-03, 15-06, 15-09, 15-12
		 //in prossimita di queste date viene generato il calendario trimestrale a partire da
		 //01-04, 01-07, 01-10, 01-01 del prossimo anno, o comunque a partire dal prossimo mese
		 if(today.getDayOfMonth()==15) {
			 if(today.getMonthValue()==3) {
				 //è il 15-03
				 LocalDate start = today.plusDays(17);
				 System.out.println(start);
				 calendario.generaCalendario(start);
				 calendario.generaBustePaga(start);
			 }
			 else if(today.getMonthValue()==6) {
				 //è il 15-06
				 LocalDate start = today.plusDays(16);
				 System.out.println(start);
				 calendario.generaCalendario(start);
				 calendario.generaBustePaga(start);
			 }
			 else if(today.getMonthValue()==9) {
				 //è il 15-09
				 LocalDate start = today.plusDays(16);
				 System.out.println(start);
				 calendario.generaCalendario(start);
				 calendario.generaBustePaga(start);
			 }
			 else if(today.getMonthValue()==12) {
				 //è il 15-12
				 LocalDate start = today.plusDays(17);
				 System.out.println(start);
				 calendario.generaCalendario(start);
				 calendario.generaBustePaga(start);
			 } else {
				 System.out.println("Non siamo in periodo calcolo calendario");
			 }
		 } else {
			 System.out.println("Non è ancora il 15 del mese");
		 }
		 
		 //adesso avviamo i meccanismo di controllo orari
		 
		 
		 
		 
	 }
}
