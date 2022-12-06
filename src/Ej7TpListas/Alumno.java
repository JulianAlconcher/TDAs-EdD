package Ej7TpListas;
/*
 * addCursada() : agrega un int a la lista de cursadas si esta no fue agregada anteriormente

	addAprobada():agrega un int a la lista de aprobadas si esta no fue agregada anteriormente y ademas esta en la lista de cursadas

	cursadas(): devuelve la lista de cursadas

	aprobadas(): devuelve la lista de aprobadas

	nombre(): devuelve el string nombre
 */

import java.util.Iterator;

import TDALista.ListaDoblementeEnlazada2022;
import TDALista.Position;
import TDALista.PositionList;

public class Alumno {
	PositionList<Persona> nombreAlumnos = new ListaDoblementeEnlazada2022<Persona>();
	PositionList<Integer> materiasCursadas = new ListaDoblementeEnlazada2022<Integer>();
	PositionList<Integer> materiasAprobadas = new ListaDoblementeEnlazada2022<Integer>();
	
	public Alumno(Persona nombre) {
		nombreAlumnos.addLast(nombre);
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public void addCursada(int materia) {
		Iterator<Integer> it = materiasCursadas.iterator();
		boolean existe = false;
		
		while(it.hasNext()) {
			if(it.equals(materia))
				existe = true;
		}
		if(existe==false)
			materiasCursadas.addLast(materia);
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public void addAprobada(int materia) {
		Iterator<Integer> it = materiasCursadas.iterator();
		Iterator<Integer> it2 = materiasAprobadas.iterator();
		boolean existe = false;
		boolean encontreCursada = false;
		while(it.hasNext()) 
			if(it.equals(materia))
				encontreCursada = true;
		while(it2.hasNext())
			if(it2.equals(materia))
				existe = true;
		
		
		if(existe==false && encontreCursada==true)
			materiasCursadas.addLast(materia);
	}
	
	public Iterator<Integer> cursadas(){
		return materiasCursadas.iterator();
	}
	
	public Iterator<Integer> aprobadas(){
		return materiasAprobadas.iterator();
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public Persona nombre(Position<Persona> nom) {
		Iterator<Persona> it = nombreAlumnos.iterator();
		while(it.hasNext())
			if(it.equals(nom))
				return nom.element();
		return null;
	}
}
