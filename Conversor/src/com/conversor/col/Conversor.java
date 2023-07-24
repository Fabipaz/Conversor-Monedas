package com.conversor.col;

import java.util.Scanner;

public class Conversor {

	public static void mostrarMenu() {
		
			System.out.println("CONVERSOR DE MONEDAS ALURA");
			System.out.println(	  "1 - Pesos Colombianos a Dolares\n"
								+ "2 - Pesos Colombianos a Euros\n"
								+ "3 - Pesos Colombianos a Real Brasileño\n"
								+ "4 - Pesos Colombianos a Yenes\n"
								+ "5 - Salir");
			System.out.println("INGRESE UNA OPCIÓN: ");
			
	}

			

			
	static void conversion(double valorMoneda, String pais) {
		Scanner tecla = new Scanner(System.in);
		System.out.print("Ingrese la cantidad de Pesos Colombianos para convertir a " + pais);
		double cantidadMoneda = tecla.nextDouble();
		
		double  moneda = cantidadMoneda / valorMoneda;
		
		moneda = (double) Math.round(moneda * 100d)/100; //se redondea el valor a dos decimales
		System.out.println("-------------------------------");
		System.out.println("|  Tienes $" +moneda+" "+pais);
		System.out.println("-------------------------------");
	}

}
