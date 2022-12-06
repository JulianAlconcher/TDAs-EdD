package TDALista;

public class DNodo <E> implements Position <E> {
	/**
	 * Elemento almacenado en el nodo
	 */
	protected E element;
	/**
	 * Nodo previo
	 */
	protected DNodo <E> prev;
	/**
	 * Nodo siguiente
	 */
	protected DNodo <E> next;
	/**
	 * Crea un nuevo Nodo de la lista doblemente enlazada espesificando su anterior y posterior.
	 * @param item Es el elemento que va a guardar el nodo.
	 * @param n Es el siguiente nodo al creado.
	 * @param p Es el anterior nodo al creado.
	 */
	public DNodo(E item, DNodo<E> n, DNodo<E> p) {
		element=item;
		next=n;
		prev=p;
	}
	/**
	 * Crea un nuevo Nodo de la lista doblemente enlazada.
	 * @param item Es el elemento que va a guardar el nodo.
	 */
	public DNodo(E item){
		this(item,null,null);
	}
	/**
	 * Cambia el elemento actual del nodo por el espesificado en el parametro.
	 * @param item El elemento nuevo del nodo.
	 */
	public void setElement(E item){
		element = item;
	}	
	/**
	 * Cambia el siguiente del nodo por el espesificado en el parametro.
	 * @param siguiente El nuevo nodo siguiente. 
	 */
	public void setNext(DNodo<E> siguiente){
		next= siguiente;
	}	
	/**
	 * Cambia el anterior del nodo por el espesificado en el parametro.
	 * @param anterior El nuevo nodo anterior.
	 */
	public void setPrev(DNodo<E> anterior){
		prev = anterior;
	}	
	@Override 
	public E element() {
		return element;
	}	
	/**
	 * Consulta cual es el siguiente del nodo.
	 * @return El siguiente del nodo.
	 */
	public DNodo<E> getNext(){
		return next;
	}
	/**
	 * Consulta cual es el anterior del nodo.
	 * @return El anterior del nodo.
	 */
	public DNodo<E> getPrev(){
		return prev;
	}
}
