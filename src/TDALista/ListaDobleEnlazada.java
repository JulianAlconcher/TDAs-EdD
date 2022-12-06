package TDALista;

import java.util.Iterator;

import Exceptions.BoundaryViolationException;
import Exceptions.EmptyListException;
import Exceptions.InvalidPositionException;

public class ListaDobleEnlazada<E> implements PositionList<E> {
	
	protected int tamanio;//tamaño de la lista
	protected DNodo<E> head;//cabeza
	protected DNodo<E> tail;//cola
	
	/*
	 * Inicializa todo en null, la cabeza no tiene previo, 
	 * el siguiente de head es el rabo
	 * el previo del rabo es la cabeza, el rabo no tiene siguiente y la lista no tiene elementos. 
	 */
	public ListaDobleEnlazada()
	{
		head= new DNodo<E>(null);
		tail= new DNodo<E>(null);
		head.setNext(tail);
		tail.setPrev(head);
		tamanio=0;
	}
	/*
	 * Retorna el tamaño de la lista
	 */
	public int size() {
		
		return tamanio;
	}

	/*
	 * Retorna si esta vacia o no
	 */
	public boolean isEmpty() {
		
		return (tamanio==0);
	}

	/*
	 * Devuelve el primer elemento de la lista
	 * Error si la lista esta vacia
	 */
	public Position<E> first() throws EmptyListException {
		
		if(tamanio==0)
		throw new EmptyListException("lista vacia");
		
		return head.getNext();
	}

	/*
	 * Retorna el ultimo elemento de la lista
	 * Error si la lista esta vacia
	 */
	public Position<E> last() throws EmptyListException {
		if(tamanio==0)
			throw new EmptyListException("Lista vacia, no tiene ultima posicion");
		return tail.getPrev();
	}

	/*
	 * Retorna el elemento siguiente a la posicion p
	 * Error si la posicion es invalida o si se pide la posicion sig a la ultima
	 */
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		
		DNodo<E> n = checkPosition(p);
		
		if(n.getNext()==tail)
		 throw new BoundaryViolationException ("No se puede pedir posicion siguiente a la ultima");
		
		return n.getNext();
		
	}

	/*
	 * Retorna la posición del elemento que precede al elemento en la posición p
	 * Error si p = first()
	 */
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		
		DNodo<E> n = checkPosition(p);
		if(n.getPrev()==head)
			throw new BoundaryViolationException("No se puede pedir la posicion anterior a la primera");
		return n.getPrev();
	}

	/*
	 * Inserta un nuevo elemento 'element' como primer elemento
	 */
	public void addFirst(E element) {
		DNodo<E> nuevo= new DNodo<E>(element);//Creo un nuevo nodo
		
		nuevo.setNext(head.getNext());//El siguiente del nuevo es el siguiente de la head
		nuevo.setPrev(head);//El anterior del nuevo es el head
		
		head.getNext().setPrev(nuevo);
		head.setNext(nuevo);
		
		tamanio++;//Aumento tamaño en 1
	}

	/*
	 * Inserta un nuevo elemento 'element' como último elemento
	 */
	public void addLast(E element) {
		DNodo<E> nuevo= new DNodo<E>(element);
		
		nuevo.setPrev(tail.getPrev());
		nuevo.setNext(tail);
		
		tail.getPrev().setNext(nuevo);
		tail.setPrev(nuevo);
		
		tamanio++;
	}

	/*
	 * Inserta un nuevo elemento 'element' luego de la posición 'p'
	 */
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		DNodo<E> n= checkPosition(p);
		DNodo<E> nuevo= new DNodo<E>(element);
		
		nuevo.setNext(n.getNext());
		nuevo.setPrev(n);
		nuevo.getNext().setPrev(nuevo);
		
		n.setNext(nuevo);
		
		tamanio++;
	}

	/*
	 * Inserta un nuevo elemento 'element' antes de la posición 'p'
	 */
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		DNodo<E> n= checkPosition(p);
		DNodo<E> nuevo= new DNodo<E>(element);
		nuevo.setPrev(n.getPrev());
		nuevo.setNext(n);
		n.getPrev().setNext(nuevo);
		n.setPrev(nuevo);
		tamanio++;
	}
    /*
     * Elimina y retorna el elemento en la posicion 'p', invalidandola
     */
	public E remove(Position<E> p) throws InvalidPositionException {
		DNodo<E> n=checkPosition(p);
		
		E aux=n.element();
		
		n.getPrev().setNext(n.getNext());
		n.getNext().setPrev(n.getPrev());
		n.setElement(null);
		n.setNext(null);
		n.setPrev(null);
		
		tamanio--;
		
		return aux;
	}

	/*
	 * Reemplaza al elemento en la posicion 'p' con 'element' retornando el elemento que estaba antes
	 * en la posicion 'p'.
	 * Lanza una exepcion si la lista esta vacia
	 */
	public E set(Position<E> p, E element) throws InvalidPositionException {
		if(tamanio == 0) {
			throw new InvalidPositionException("Lista vacia");
		}
		DNodo <E> n= checkPosition(p);
		E aux= n.element();
		n.setElement(element);
		
		return aux;
	}

	public Iterator<E> iterator() {
		return new ElementIterator<E>(this);
	}

	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> l = new ListaDobleEnlazada<Position<E>>();
		DNodo<E> p = null;
		/*Si la lista no esta vacia la posicion p toma el valor de la primera posicion, 
		de lo contrario retorna la lista vacia*/
		if(tamanio != 0){
			p = head.getNext();
			/*Mientra la posicion p sea diferente a la ultina posicion se agrega la posicion 
			 p a la lista y p avanza de posicion*/
			while(p != tail.getPrev()){
				l.addLast(p);
				p = p.getNext();
			}
			l.addLast(p);
		}
		return l;	
	
	}
	
	/*
	 *
	 * METODO PRIVADO
	 * Convierte a la posicion pasada por parametro en un Nodo de la lista doblemente enlazada.
	 * comprueba que la posicion p sea valida
	 * @param p Es la posicion que se quiere convertir en nodo
	 * @return El nodo correspondiente a la posicion
	 * @throws InvalidPositionException Si la posicion pasada por parametro es invalida o la lista esta vacia
	 */
	
	private DNodo<E> checkPosition( Position<E> p ) throws InvalidPositionException {
		try {return (DNodo<E>) p;} 
		catch( ClassCastException e )
		{throw new InvalidPositionException( "Posicion Invalida" );}} 

}
