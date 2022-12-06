package TDAArbol;

import TDALista.ListaDobleEnlazada;

/**
 * Implementa los metodos de la clase TNodo.
 * @author Genaro Martin Garcia
 * @version 1.0 Genaro Martin Garcia 2020
 * @param <E> Tipo de dato de los elementos a almacenar en el nodo.
 */

public class TNodo<E>  implements Position<E>{
	/**
	 * Elemento que guarda el nodo.
	 */
	private E elemento;
	/**
	 * El nodo padre del nodo.
	 */
	private TNodo<E> padre;
	/**
	 * La lista de nodos hijos del nodo.
	 */
	private TDALista.PositionList<TNodo<E>> hijos;
	
	/**
	 * Crea un nuevo nodo que guarda el elemento pasado por parametro y tiene como padre el nodo pasado por parametro.
	 * @param elemento Es el elemento que va a guardar el nodo.
	 * @param padre Es el nodo que sera enlazado como padre.
	 */
	public TNodo(E elemento, TNodo<E> padre){
		this.elemento = elemento; 
		this.padre= padre;
		hijos = new ListaDobleEnlazada<TNodo<E>>();
	}
	
	/**
	 * Crea un nuevo nodo que guarda el elemento pasado por parametro y sin padre.
	 * @param elemento Es el elemento que va a guardar el nodo.
	 */
	public TNodo(E elemento){
		this(elemento, null);
	}
	
	/**
	 * Consulta el elemento que guarda el nodo.
	 * @return El elemento que guarda el nodo.
	 */
	public E element(){
		return elemento;
	}
	
	/**
	 * Consulta la lista de hijos que tiene el nodo.
	 * @return La lista de hijos del nodo.
	 */
	public TDALista.PositionList<TNodo<E>> getHijos(){
		return hijos;
	}
	
	/**
	 * Cambia el elemento que guarda el nodo por el pasado por parametro.
	 * @param elemento Es el nuevo valor que va a tener el nodo en su elemento.
	 */
	public void setElemento(E elemento){
		this.elemento = elemento;
	}
	
	/**
	 * Consulta cual es el padre del nodo.
	 * @return El padre del nodo.
	 */
	public TNodo<E> getPadre(){
		return padre;
	}
	
	/**
	 * Cambia el padre actual del nodo por el nodo pasado por parametro.
	 * @param padre El nuevo padre del nodo.
	 */
	public void setPadre(TNodo<E> padre){
		this.padre = padre;
	}
	
	public String toStringA() {
		return elemento.toString();
	}
	
}