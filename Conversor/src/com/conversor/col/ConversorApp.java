package com.conversor.col;

import java.util.Scanner;

public class ConversorApp {
	public static void main(String[] args) {
	Scanner tecla = new Scanner(System.in);
	
	while(true) {
		Conversor.mostrarMenu();
		char opcion = tecla.next().charAt(0);
		
		switch (opcion) {
		case '1' : 
			 Conversor.conversion(3975.96, "Dolares");
			 break;
		case '2' : 
			Conversor.conversion(4426.58, "Euros");
			 break;
		case '3' : 
			Conversor.conversion(829.15, "Real brasileño");
			 break;
		case '4' : 
			Conversor.conversion(28.04, "Yenes");
			 break;
		case '5' : 
			 System.out.println("CERRANDO PROGRAMA ....");
			 tecla.close();
			 throw new IllegalStateException();
			 
		default:
			System.out.println("OPCIÓN INCORRECTA");
		}
		System.out.println();
	}
}	
}