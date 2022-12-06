 package EjerciciosVarios;

import java.util.Scanner;

import TDALista.ListaDobleEnlazada;
import TDALista.PositionList;

public class Ej7Tp4 {
	public static void main(String[] args) {
	PositionList<String> listaNombres = new ListaDobleEnlazada<String>();
	new ListaDobleEnlazada<Integer>();
	new ListaDobleEnlazada<Integer>();
	@SuppressWarnings({ "resource" })
	Scanner reader = new Scanner(System.in);
	
	
	listaNombres.addFirst("Juan Perez");
	listaNombres.addLast("Valentina Plat");
	listaNombres.addLast("Joaquin Paez");
	listaNombres.addLast("Julian Alconcher");
	listaNombres.addLast("Valentin Phil");
	listaNombres.addLast("Giuliano Palmisa");
	listaNombres.addLast("Juan Gamoez");
	
	System.out.println("Programa de materias");
	System.out.println("Inserte 1 para agregar una materia cursada o 2 para una aprobada");
	reader.nextInt();
	System.out.println("Inserte el numero de la materia");
	reader.nextInt();
	
	System.out.println(listaNombres.toString());
	}
}
