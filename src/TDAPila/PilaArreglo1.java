package TDAPila;

import Exceptions.EmptyStackException;

public class PilaArreglo1<E> implements Stack<E> {
	
	protected int cant;
	protected E T[];
	
	@SuppressWarnings("unchecked")
	public PilaArreglo1(int max) {
		T = (E[]) new Object[max];
		cant = 0;
	}
	
	public PilaArreglo1() {
		this(20);
	}
	
	public int size() {
		return cant;
	}

	public boolean isEmpty() {
		return (cant == 0);
	}

	public E top() throws EmptyStackException {
		
		if (cant == 0)
			throw new EmptyStackException("top: Pila vacia");
		
		return T[cant-1];
	}

	public void push(E element) {
		if (cant == T.length)
			this.reSize();
		
		T[cant] = element;
		cant++;
	}

	public E pop() throws EmptyStackException {
		
		if (cant == 0)
			throw new EmptyStackException("pop: Pila vacia");
		
		E aux = this.top();
	    cant--;
		return aux;
	}
	
	@SuppressWarnings("unchecked")
	private void reSize() {
		E aux[];
		aux = (E[]) new Object[T.length*2];
		
		int i = 0;
		while(i < T.length) {
			aux[i] = T[i];
			++i;
		}
	
		T = aux;
	}

}
