package EjerciciosVarios;

import java.util.Iterator;

import TDALista.ListaDobleEnlazada;
import TDALista.PositionList;

public class Alumno_Ej7Tp4 {

	private PositionList<Integer> cursadas, aprobadas;
	private String nombre;
	
	public Alumno_Ej7Tp4(String nombre) {
		
		cursadas = new ListaDobleEnlazada<Integer>();
		aprobadas = new ListaDobleEnlazada<Integer>();
		this.nombre = nombre;
	}
	
	public void agregarCursada(int c) {
		
		if(!estaCursada(c))
			cursadas.addLast(c);
		else
			System.out.println("La materia ya está entre las cursadas");
	}
	
	private boolean estaCursada(int c) {
		
		Iterator<Integer> it = cursadas.iterator();
		while(it.hasNext()) {
			if(it.next().equals(c))
				return true;
		}
		
		return false;
	}
	
	public void agregarAprobada(int c) {
		
		if(estaCursada(c))
			aprobadas.addLast(c);
		else
			System.out.println("Materia no cursada");
	}
	
	public String nombre() {
		
		return nombre;
	}
}