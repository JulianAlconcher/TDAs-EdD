package TDAPila;

import Exceptions.EmptyStackException;

//La pila solo conoce el nodo cabeza, que corresponde al ultimo apilado

public class PilaConEnlaces<E> implements Stack<E> 
{
	// Nodo tope de la pila.
	protected Nodo<E> head;
	// Entero que almacena el tamaño de la pila.
	protected int tamanio;
	
	/**
	 * Constructor de la clase
	 * Inicializa el tamaño en cero, y la cabeza es nula.
	 */
	public PilaConEnlaces()
	{
		head=null;
		tamanio = 0;
	}
	/* Consulta que retorna el tamaño de la pila. 
	 * @return el tamaño de la pila
	 */
	public int size() 
	{
		return tamanio;
	}

	/* Consulta que retorna TRUE si la pila es vacia. FALSE en caso de que no lo este.
	 * @return True si la pila es vacia, False en caso contrario. 
	 */
	public boolean isEmpty() 
	{
		return (head==null && tamanio==0);
	}

	/*Retorna el elemento del tope de la pila. Si se aplica a una pila vacía, produce una situación de error.
	 * @return el elemento del tope de la pila.
	 */
	public E top() throws EmptyStackException 
	{
		if(head==null && tamanio==0)
			  throw new EmptyStackException("Pila vacia");
		  
		return head.getElemento();
	}

	/*
	 * Inserta el elemento "element" en el tope de la pila.
	 */
	public void push(E element) 
	{
		Nodo<E> aux = new Nodo<E>(element);
		aux.setSiguiente( head );
		head = aux;
		tamanio++;
	}
		
	
	/*
	 * Elimina el elemento del tope de la pila 
	 * @return tope de la pila 
	 * Si se aplica a una pila vacía, produce una situación de error.
	 */

	public E pop() throws EmptyStackException 
	{
	  
      if(head==null && tamanio==0)
		  throw new EmptyStackException("Pila vacia");
	
	  	E toReturn = head.getElemento(); 
	    head = head.getSiguiente();
	    tamanio--;
	    
	    return toReturn;
	  
		
	}

}
