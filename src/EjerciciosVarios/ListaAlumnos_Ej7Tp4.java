package EjerciciosVarios;

import java.util.Iterator;

import TDALista.ListaDobleEnlazada;
import TDALista.PositionList;


public class ListaAlumnos_Ej7Tp4 {

	private PositionList<Alumno_Ej7Tp4> lista;
	
	public ListaAlumnos_Ej7Tp4() {
		
		lista = new ListaDobleEnlazada<Alumno_Ej7Tp4>();
	}
	
	public void agregarAlumno(String nombre) {
		
		if(!estaAlumno(nombre))
			lista.addLast(new Alumno_Ej7Tp4(nombre));
		else
			System.out.println("El alumno " + nombre +" ya existe en el sistema");
	}
	
	private boolean estaAlumno(String nombre) {
		
		Iterator<Alumno_Ej7Tp4> it = lista.iterator();
		Alumno_Ej7Tp4 a;
		while(it.hasNext()) {
			a = it.next();
			if(a.nombre().equals(nombre))
				return true;
		}
		
		return false;
	}
	
	public void cargarCursada(String nombre, int c) {
		
		Iterator<Alumno_Ej7Tp4> it = lista.iterator();
		Alumno_Ej7Tp4 a;
		boolean encontre = false;
		while(it.hasNext() && !encontre) {
			a = it.next();
			if(a.nombre().equals(nombre)) {
				a.agregarCursada(c);
				encontre = true;
			}
		}
		
		if(!encontre)
			System.out.println("El alumno" + nombre + " no existe en el sistema");
	}
	
	public void cargarAprobada(String nombre, int c) {
		
		Iterator<Alumno_Ej7Tp4> it = lista.iterator();
		Alumno_Ej7Tp4 a;
		boolean encontre = false;
		while(it.hasNext() && !encontre) {
			a = it.next();
			if(a.nombre().equals(nombre)) {
				a.agregarAprobada(c);
				encontre = true;
			}
		}
		
		if(!encontre)
			System.out.println("El alumno" + nombre + " no existe en el sistema");
	}
	
}