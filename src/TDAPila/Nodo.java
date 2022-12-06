 package TDAPila;

public class Nodo<E> 
{
	//Elemento que contiene el nodo
	private E elemento;
	//donde apunta el nodo actual, al nodo siguiente
	private Nodo<E> siguiente;
	
	
	/* Constructor de Nodo
	 * Inicializa a elemtno en item, y siquiente en siguiente. 
	 */
	public Nodo( E item, Nodo<E> sig )
	{ 
		elemento=item; 
		siguiente=sig; 
    }
	public Nodo( E item )
	{ 
		this(item,null); 
	}
	
	/* Setea el elemento.
	 * 
	 */
	public void setElemento( E elemento )
	{ 
		this.elemento = elemento; 
	}
	/* Setea el siguiente.
	 * 
	 */
	public void setSiguiente( Nodo<E> siguiente )
	{ 
		this.siguiente = siguiente; 
	}
	
	// Getters:
	/* Consulta que retorna el elemento.
	 * @return elemento E. 
	 */
	public E getElemento() 
	{ 
		return elemento; 
	}
	/* Consulta que retorna el siguiente.
	 * @return el siguiente
	 */
	public Nodo<E> getSiguiente() 
	{ 
		return siguiente; 
	}
	
}
