package TDACola;

import Exceptions.EmptyQueueException;
import TDAPila.Nodo;

public class ColaEnlazada<E> implements Queue<E>
{
	//Nodo cabeza de la cola
	protected Nodo<E> head;
	// Nodo rabo de la cola.
	protected Nodo<E> tail;
	// Entero que almacena el tamaño de la cola
	protected int tamanio;
	
	/* Constructor de la cola
	 * Inicializa a cola y rabo en null, y tamañao en 0.
	 */
	public ColaEnlazada()
	{
		head=null;
		tail=null;
		tamanio=0;
	}
	/* Consulta que retorna el tamaño de la cola. 
	* @return el tamaño de la cola.
	*/
	public int size() {
		
		return tamanio;
	}

	/* Consulta que retorna TRUE si la cola es vacia. FALSE en caso de que no lo este.
	 * @return True si la cola es vacia, False en caso contrario. 
	 */
	public boolean isEmpty() {
		
		return (head==null && tail==null && tamanio==0);
	}

	/*
	 * @return Retorna el elemento del frente de la cola. 
	 * Si la cola está vacía se produce un error. 
	 */
	public E front() throws EmptyQueueException {
		if(tamanio==0)
			throw new EmptyQueueException("Cola vacia");
		else
			return head.getElemento();
	}

	/*
	 * Inserta el elemento element en el rabo de la cola.
	 */
	public void enqueue(E element) 
	{
		Nodo<E> nodo = new Nodo<E>(element); 
		if(tamanio==0)
			head=nodo;
		else
			tail.setSiguiente(nodo);
		 
		tail=nodo;
		tamanio++;
		
	}

	/*
	 * Elimina el elemento del frente de la cola 
	 * @return elemento del frente
	 *  Si la cola está vacía se produce un error.
	 */
	public E dequeue() throws EmptyQueueException {
		if(tamanio==0)
			throw new EmptyQueueException("cola vacia");
		
		E toReturn = head.getElemento();
		head = head.getSiguiente();
		tamanio--;
		if(tamanio==0)
			tail=null;
		
		return toReturn;
	}
	
	
	//------------------
	public void Imprimir()
	{
		Nodo<E> p =  head;
		for(int i=0; i<size(); i++ ) {
			
			System.out.print(p.getElemento());
			
			p = p.getSiguiente();
		}
	}

}
