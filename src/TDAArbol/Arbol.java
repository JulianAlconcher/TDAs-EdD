package TDAArbol;

import java.util.Iterator;

import Exceptions.BoundaryViolationException;
import Exceptions.EmptyListException;
import Exceptions.EmptyTreeException;
import Exceptions.InvalidOperationException;
import Exceptions.InvalidPositionException;
import TDALista.ListaDobleEnlazada;
import TDALista.PositionList;

public class Arbol<E> implements Tree<E> {
	/*
	 * El nodo raiz del arbol
	 */
	protected TNodo<E> raiz;
	/*
	 * tamaño actual del arbol
	 */
	protected int tamanio;
	/*
	 * Crea un nuevo arbol vacio
	 */
	public Arbol() {
		raiz=null;
		tamanio=0;
	}
	/*
	 * Retorna el tamaño del arbol
	 */
	public int size() {
		return tamanio;
	}
	/*
	 * Testea si el arbol es vacio
	 */
	public boolean isEmpty() {
		return (tamanio==0 && raiz==null);
	}
	/*
	 * Retorna un irerador con los elementos ubicados en los nodos del arbol.
	 */
	public Iterator<E> iterator() {
		TDALista.PositionList<E> l = new ListaDobleEnlazada<E>();
		for( Position<E> p : positions()){
			l.addLast( p.element());
		}
		return l.iterator();
	}
	/*
	 * Retorna una coleccion iterable de los nodos del arbol.
	 */
	public Iterable<Position<E>> positions() {
		TDALista.PositionList<Position<E>> l = new ListaDobleEnlazada<Position<E>>();
		if(!isEmpty()){
			pre(raiz, l); 
		}
		return l;
	}
	/*
	 * Reemplaza e y retorna el elemento ubicado en v.
	 */
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(v);
		E elemento = nodo.element();
		nodo.setElemento(e);
		return elemento;
	}
	/*
	 * Retorna la raiz del arbol.
	 */
	public Position<E> root() throws EmptyTreeException {
		if(raiz==null)
			throw new EmptyTreeException("Arbol vacio");
		return raiz;
	}
	/*
	 * Retorna el padre de v.
	 * Error si v es la raiz
	 */
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		TNodo<E> nodo = checkPosition(v);
		if(nodo == raiz){
			throw new BoundaryViolationException("La raiz no tiene padre");
		}
		return nodo.getPadre();
	}
	/*
	 * Retorna una coleccion iterable conteniendo a los hijos del nodo v.
	 */
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		TNodo<E> p = checkPosition(v);
		PositionList<Position<E>> lista = new ListaDobleEnlazada<Position<E>>();
		for(TNodo<E> n : p.getHijos())
			lista.addLast(n);
		return lista;
		
	}
	/*
	 * Testea si v es un nodo interno.
	 */
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(v);
		return !(nodo.getHijos().isEmpty());
	}
	/*
	 * Testea si v es una hoja.
	 */
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(v);
		return (nodo.getHijos()).isEmpty();
	}
	/*
	 * Testea si v es la raiz.
	 */
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(v);
		return (raiz == nodo);
	}
	/*
	 * Crea un nodo raiz con rotulo en e.
	 * Si el tamaño es distinto a cero tira una exepcion.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void createRoot(E e) throws InvalidOperationException {
		if(tamanio != 0){
			throw new InvalidOperationException("Arbol no vacio");
		}
		raiz = new TNodo(e);
		tamanio++;
	}
	/*
	 * Agrega un primer hijo al nodo p con rotulo e.
	 */
	public Position<E> addFirstChild(Position<E> p, E e) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(p);
		TNodo<E> nuevo = new TNodo<E>(e,nodo);
		nodo.getHijos().addFirst(nuevo);
		tamanio++;
		return nuevo;
	}
	/*
	 * Agrega un ultimo hijo al nodo p con rotulo e.
	 */
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException {
		if(tamanio == 0){
			throw new InvalidPositionException("Arbol vacio");
		}
		TNodo<E> nodo = checkPosition(p);
		TNodo<E> nuevo = new TNodo<E>(e,nodo);
		nodo.getHijos().addLast(nuevo);
		tamanio++;
		return nuevo;
	}
	/*
	 * Agrega un nodo con rótulo e como hijo de un nodo padre p dado. 
	 * El nuevo nodo se agregará delante de otro nodo hermano rb también dado.
	 */
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException {
		if(tamanio==0) {
			throw new InvalidPositionException("ARBOL VACIO");
		}
		TNodo<E> padre = checkPosition(p);
		TNodo<E> hermanoder = checkPosition(rb);
		TNodo<E> nuevo = new TNodo<E>(e,padre);
		PositionList<TNodo<E>> hijos = padre.getHijos();
		
		boolean encontre = false;
		TDALista.Position<TNodo<E>> pp = null;
		try {
			pp = hijos.first();
		} catch (EmptyListException e2) {
			e2.printStackTrace();
		}
		while(pp != null && !encontre) {
			if(hermanoder == pp.element()) {
				encontre = true;
			} else {
				try {
					if(pp != hijos.last()) {
						try {
							pp = hijos.next(pp);
						} catch (InvalidPositionException | BoundaryViolationException e1) {
							e1.printStackTrace();
						}
					} else {
						pp = null;
					}
				} catch (EmptyListException e1) {
					e1.printStackTrace();
				}
			}
		}
		if(!encontre) {
			throw new InvalidPositionException("P NO ES PADRE DE RB");
		}
		hijos.addBefore(pp,nuevo);
		tamanio++;
		return (Position<E>) nuevo;
	}
	/*
	 * Agrega un nodo con rótulo e como hijo de un nodo padre p dado. 
	 * El nuevo nodo se agregará detrás de otro hermano lb también dado.
	 */
	public Position<E> addAfter(Position<E> p, Position<E> lb, E e) throws InvalidPositionException {
		if(tamanio==0) {
			throw new InvalidPositionException("ARBOL VACIO");
		}
		TNodo<E> padre = checkPosition(p);
		TNodo<E> hermanoder = checkPosition(lb);
		TNodo<E> nuevo = new TNodo<E>(e,padre);
		PositionList<TNodo<E>> hijos = padre.getHijos();
		
		boolean encontre = false;
		TDALista.Position<TNodo<E>> pp = null;
		try {
			pp = hijos.first();
		} catch (EmptyListException e2) {
			e2.printStackTrace();
		}
		while(pp != null && !encontre) {
			if(hermanoder == pp.element()) {
				encontre = true;
			} else {
				try {
					if(pp != hijos.last()) {
						try {
							pp = hijos.next(pp);
						} catch (InvalidPositionException | BoundaryViolationException e1) {
							e1.printStackTrace();
						}
					} else {
						pp = null;
					}
				} catch (EmptyListException e1) {
					e1.printStackTrace();
				}
			}
		}
		if(!encontre) {
			throw new InvalidPositionException("P NO ES PADRE DE RB");
		}
		hijos.addAfter(pp,nuevo);
		tamanio++;
		return (Position<E>) nuevo;
	}
	/*
	 * Elimina la hoja p.
	 */
	public void removeExternalNode(Position<E> p) throws InvalidPositionException {

		TNodo<E> n = checkPosition(p);
		if(!n.getHijos().isEmpty()){
			throw new InvalidPositionException("No es un nodo externo");
		}
		removeNode(n);
	}
	
	/*
	 * Elimina el nodo interno p. Los hijos del nodo eliminado lo reemplazan en el mismo orden en el que aparecen. 
	 * La raíz se puede eliminar si tiene un único hijo.
	 */
	public void removeInternalNode(Position<E> p) throws InvalidPositionException {
		TNodo<E> n = checkPosition(p);
		if(n.getHijos().isEmpty()){
			throw new InvalidPositionException("No es un nodo interno");
		}
		removeNode(n);	
	}
	/*
	 * Elimina el nodo p.
	 */
	public void removeNode(Position<E> p) throws InvalidPositionException {
		TNodo<E> actual = checkPosition(p);
		TNodo<E> padre = actual.getPadre();
		TDALista.PositionList<TNodo<E>> hijos = actual.getHijos();
		try {
			if(actual==raiz) {
				if(hijos.size() == 0) {
					raiz=null;
			}
			else 
			{
				if(hijos.size() == 1) {
					TNodo<E> hijo = hijos.remove(hijos.first());
					hijo.setPadre(null);
					raiz=hijo;
			} else throw new InvalidPositionException("No se puede elminar la raiz con mas de un hijo");
			}
		}else {
			TDALista.PositionList<TNodo<E>> hermanos = padre.getHijos();
			TDALista.Position<TNodo<E>> posListaHermanos = hermanos.isEmpty() ? null : hermanos.first();
			while(posListaHermanos != null && posListaHermanos.element() != actual){
				posListaHermanos = (hermanos.last() == posListaHermanos) ? null : hermanos.next(posListaHermanos);
			}
			if (posListaHermanos == null){
				throw new InvalidPositionException("La posici�n p no se encuentra en la lista del padre");
			}
			//Se agregan en la lista de hermanos de nEliminar, todos los hijos nEliminar.
			while(!hijos.isEmpty()){
				TNodo<E> hijo = hijos.remove(hijos.first());
				hijo.setPadre(padre);
				hermanos.addBefore(posListaHermanos, hijo);
			}
			hermanos.remove(posListaHermanos);
		}
		
			actual.setPadre(null);
			actual.setElemento(null);
			tamanio--;
			
		}catch(EmptyListException ex){
			ex.getMessage();
		}catch(BoundaryViolationException ex){
			ex.getMessage();
		}catch(InvalidPositionException ex) {
			ex.getMessage();
		}
}

	/**
	 * Convierte a la posicion pasada por parametro en un Nodo del arbol.
	 * @param p Es la posicion que se quiere convertir en nodo
	 * @return El nodo correspondiente a la posicion
	 * @throws InvalidPositionException Si la posicion pasada por parametro es invalida o que el arbol esta vacio.
	 */
	private TNodo<E> checkPosition(Position<E> p) throws InvalidPositionException{
		try{
			if(p == null){
				throw new InvalidPositionException("Posicion nula");
			}
			if(p.element() == null){
				throw new InvalidPositionException("Posicion eliminada previamente");
			}
			return (TNodo<E>) p;
		}catch(ClassCastException e){
			throw new InvalidPositionException("La posicion no es de esta lista");
		}
	}
	
	/**
	 * Recorre el arbol en preorden a�adiendo el nodo actual a la lista.
	 * @param v Es el nodo que hay que a�adir a la lista
	 * @param l Lista a la cual se le van a a�adir los nodos
	 */
	private void pre(TNodo<E> v, TDALista.PositionList<Position<E>> l){
		l.addLast(v);
		for( TNodo<E> h : v.getHijos()){
			pre(h,l); 
		}
	}


	
}
