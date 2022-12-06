package TDAPila;

import Exceptions.EmptyStackException;

public class PilaUsandoArreglo2022<E> implements Stack<E> {
	private E [] arreglo;
	private int tamanio;
	
	@SuppressWarnings("unchecked")
	public PilaUsandoArreglo2022(int max) {
		arreglo = (E[]) new Object[max];
		tamanio=0;
	}
	public PilaUsandoArreglo2022() {
		this(20);
	}
	@Override
	public int size() {
		return tamanio;
	}

	@Override
	public boolean isEmpty() {
		return tamanio==0;
	}

	@Override
	public E top() throws EmptyStackException {
		if(tamanio==0)
			throw new EmptyStackException("TOP:: La pila esta vacia");
		return arreglo[tamanio-1];
	}

	@Override
	public void push(E element) {
		if(tamanio == arreglo.length)	
			reSize();
		arreglo[tamanio++] = element;
	}

	@Override
	public E pop() throws EmptyStackException {
		if(tamanio==0)
			throw new EmptyStackException("POP:: La pila esta vacia");
		E aux = this.top();
		tamanio--;
		return aux;
	}
	
	private void reSize() {
		@SuppressWarnings("unchecked")
		E [] aux = (E[]) new Object[tamanio*2];
		for(int i=0; i<tamanio; i++) {
			aux[i] = arreglo[i];
		}
		arreglo = aux;
	}

}
