package Laboratorios.TDALista1;

import Exceptions.*;

//Lista desarrollada en laboratorio de EDD, el dia 18.04.2022.
//Implementa una interfaz llamada InterfaceLista que no usa iteradores.

public class ListaSE<E> implements InterfaceLista<E> {
	
	private Nodo<E> head;
	private int tamanio;
	
	public ListaSE()
	{
		head = null;
		tamanio=0;
	}
	
	@Override
	public int size() {
		return tamanio;
	}

	@Override
	public boolean isEmpty() {
		return (head==null);
	}

	@Override
	public Position<E> first() throws EmptyListException {
		if(head == null) 
			throw new EmptyListException("La lista es vacia");
		return head;
	}

	@Override
	public Position<E> last() throws EmptyListException {
		if(head == null)
			throw new EmptyListException("Lista vacia");
		Nodo<E> aux = head;
		while(aux.getSiguiente()!=null ) {
			aux = aux.getSiguiente();
		}
		
		return aux;
	}

	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		Nodo<E> n = checkPosition(p);
		if(n.getSiguiente()==null)
			throw new BoundaryViolationException("Posicion siguiente nula");
		return n.getSiguiente();
	}

	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		Nodo<E> n = checkPosition(p);
		
		if(head == null)
			throw new BoundaryViolationException("La lista es vacia");
		
		Nodo<E> aux = head;
		while(aux.getSiguiente()!= n && aux.getSiguiente()!=null) {
			aux = aux.getSiguiente();
		}
		
		if(aux.getSiguiente()==null)
			throw new InvalidPositionException("Posicion invalida");
	
		return aux;
	}

	@Override
	public void addFirst(E element) {
		head = new Nodo<E>(element,head);
		tamanio++;
		
	}

	@Override
	public void addLast(E element) {
		if(head == null)
			this.addFirst(element);
		Nodo<E> aux = head;
		while(aux.getSiguiente()!=null) {
			aux = aux.getSiguiente();
		}
		Nodo<E> nuevo = new Nodo<E>(element, null);
		aux.setSiguiente(nuevo);
		tamanio++;
	}

	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		Nodo<E> n = checkPosition(p);
		Nodo<E> nuevo = new Nodo<E>(element);
		
		nuevo.setSiguiente(n.getSiguiente());
		n.setSiguiente(nuevo);
		tamanio++;
	}

	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		Nodo<E> n = checkPosition(p);
		Nodo<E> anteriorAN = head;
		
		while(anteriorAN.getSiguiente()!= n) {
			anteriorAN = anteriorAN.getSiguiente();
		}
		
		if(anteriorAN.getSiguiente() == n) {
			Nodo<E> nuevo = new Nodo<E>(element);
			nuevo.setSiguiente(n);
			anteriorAN.setSiguiente(nuevo);
			tamanio++;
		}
		
	}

	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		Nodo<E> n = checkPosition(p);
		if(tamanio==0)
			throw new InvalidPositionException("Lista vacia");
		Nodo<E> aux = head;
		while(aux.getSiguiente()!= n) {
			aux = aux.getSiguiente();
		}
		
		aux.setSiguiente(n.getSiguiente());
		tamanio--;
		
		return n.element();
		
	}

	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException {
		Nodo<E> n = checkPosition(p);
		E retornar = n.getElemento();
		n.setElemento(element);
		
		return retornar;
	}
	/*
	 * Metodo privado que castea la posicion a un nodo y chequea q la posicion sea valida.
	 * @return la posicion casteada a nodo.
	 */
	private Nodo<E> checkPosition(Position<E> p ) throws InvalidPositionException{
		try {
			return (Nodo<E>) p ;
		}catch(ClassCastException e) {
			throw new InvalidPositionException("posicion invalida");
		}
		
	}

}