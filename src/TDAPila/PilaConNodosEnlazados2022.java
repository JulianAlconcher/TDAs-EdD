package TDAPila;

import Exceptions.EmptyStackException;
/**
 * Implementacion de pila usando nodos enlazados
 * @author Julian Alconcher
 *
 * @param <E>
 */
public class PilaConNodosEnlazados2022<E> implements Stack<E>{
	private Nodo<E> head;
	private int tamanio;
	
	public PilaConNodosEnlazados2022() {
		head = null;
		tamanio = 0;
	}
	@Override
	public int size() {
		return tamanio;
	}

	@Override
	public boolean isEmpty() {
		return tamanio == 0;
	}

	@Override
	public E top() throws EmptyStackException {
		if(tamanio==0)
			throw new EmptyStackException("TOP :: La pila esta vacia");
		return head.getElemento();
	}

	@Override
	public void push(E element) {
		Nodo<E> nuevo = new Nodo<E>(element);
		nuevo.setSiguiente(head);
		head = nuevo;
		tamanio++;
		
	}

	@Override
	public E pop() throws EmptyStackException {
		if(tamanio==0)
			throw new EmptyStackException("POP:: La lista es vacia");
		E aux = head.getElemento();
		head = head.getSiguiente();
		tamanio--;
		return aux;
	}

}
