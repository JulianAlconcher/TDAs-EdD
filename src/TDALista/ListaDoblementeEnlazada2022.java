package TDALista;

import java.util.Iterator;
import Exceptions.BoundaryViolationException;
import Exceptions.EmptyListException;
import Exceptions.InvalidPositionException;
/**
 * ListaDoblementeEnlazada - Implementacion con nodos centinelas (dummys) - Abril 2022 - Julian Alconcher
 */
public class ListaDoblementeEnlazada2022<E> implements PositionList<E> {
	
	protected DNodo<E> head;
	protected DNodo<E> tail;
	protected int tamanio;

	public ListaDoblementeEnlazada2022() {
		head = new DNodo<E>(null);
		tail = new DNodo<E>(null);
		head.setPrev(null);
		head.setNext(tail);
		tail.setPrev(head);
		tail.setNext(null);
		tamanio=0;
		
	}

	@Override
	public int size() {
		return tamanio;
	}

	@Override
	public boolean isEmpty() {
		return (tamanio==0);
	}

	@Override
	public Position<E> first() throws EmptyListException {
		if(tamanio==0)
			throw new EmptyListException("La lista es vacia");
		return head.getNext();//Devolvemos el proximo del dummy
	}

	@Override
	public Position<E> last() throws EmptyListException {
		if(tamanio==0)
			throw new EmptyListException("La lista es vacia");
		return tail.getPrev();//Devolvemos el anterior del dummy
	}

	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNodo<E> n = checkPosition(p);
		if(n.getNext()==tail) 
			throw new BoundaryViolationException("No se puede pedir la posicion siguiente de la ultima posicion");
		return n.getNext();//Devuelvo el siguiente de la posicion pasada por parametro
		
	}

	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNodo<E> n = checkPosition(p);
		if(n.getPrev()==head)
			throw new BoundaryViolationException("No se puede pedir la posicion anterior de la primera posicion");
		return n.getPrev();//Devuelvo el anterior de la posicion pasada por parametro
	}

	@Override
	public void addFirst(E element) {
		DNodo<E> nuevo = new DNodo<E>(element);//Creo el nodo a insertar y lo conecto
		(head.getNext()).setPrev(nuevo);
		nuevo.setNext(head.getNext());
		nuevo.setPrev(head);
		head.setNext(nuevo);
		tamanio++;//Aumento el tamaño
		
	}

	@Override
	public void addLast(E element) {
		DNodo<E> nuevo = new DNodo<E>(element);//Creo el nodo a insertar y lo conecto.
		nuevo.setPrev(tail.getPrev());
		nuevo.setNext(tail);
		(tail.getPrev()).setNext(nuevo);
		tail.setPrev(nuevo);
		tamanio++;//Aumento el tamaño
		
	}

	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		DNodo<E> n = checkPosition(p);//Chequeo la posicion que pasan por parametro.
		DNodo<E> nuevo = new DNodo<E>(element);//Creo el nodo a insertar y lo conecto.
		nuevo.setNext(n.getNext());
		nuevo.setPrev(n);
		nuevo.getNext().setPrev(nuevo);
		n.setNext(nuevo);
		tamanio++;//Aumento el tamaño
	}

	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		DNodo<E> n = checkPosition(p);//Chequeo la posicion que pasan por parametro.
		DNodo<E> nuevo = new DNodo<E>(element);//Creo el nodo a insertar y lo conecto
		nuevo.setPrev(n.getPrev());
		nuevo.setNext(n);
		n.getPrev().setNext(nuevo);
		n.setPrev(nuevo);
		tamanio++;//Aumento el tamaño
		
	}

	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		if(tamanio==0)
			throw new InvalidPositionException("no se puede remover una posicion de una lista que es vacia");
		DNodo<E> n = checkPosition(p);
		E aux = n.element();//Guardo el elemento para devolverlo.
		(n.getPrev()).setNext((n.getNext()));
		(n.getNext()).setPrev((n.getPrev()));
		
		//Dejo desconectado el nodo que elimino.
		
		n.setElement(null);
		n.setNext(null);
		n.setPrev(null);
		
		
		tamanio--;
		
		return aux;
	}

	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException {
		if(tamanio==0)
			throw new InvalidPositionException("Posicion invalida");
		DNodo<E> n = checkPosition(p);
		E aux = n.element();//Guardo el elemento anterior para devolverlo.
		n.setElement(element);//seteo en la posicion p el elemento "element".
		return aux;//Devuelvo el elemento que antes estaba en esa posicion.
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator<E>(this);
	}

	@Override	
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> p = new ListaDoblementeEnlazada2022<Position<E>>();
		if(!isEmpty()) {
			try {
				Position<E> pos = head.getNext();
				while( pos != tail.getPrev() ) 
				{
					p.addLast(pos);
					pos = next(pos);
				}
				p.addLast(pos); 
				}
			catch(BoundaryViolationException e){
				System.out.println("Violacion de limites");
				return null;
			}
			catch(InvalidPositionException e){
				System.out.println("Posicion invalida");
				return null;
			}
		} 	
		return p;
	}
	/**
	 * Metodo Privado que chequea que la posicion sea valida y castea la posicion a un DNodo.
	 * @return Posicion p casteada a nodo.
	 * @exception InvalidPositionException si p no es un nodo de la lista.
	 * @exception InvalidPositionException si p es un nodo centinela.
	 */
	private DNodo<E> checkPosition(Position<E> p) throws InvalidPositionException{
		try {
			if(p==null)
				throw new InvalidPositionException("p es una posicion invalida");
			if(p==head || p==tail)
				throw new InvalidPositionException("p es nodo centinela");
			return (DNodo<E>) p;
		}catch(ClassCastException e){throw new InvalidPositionException("p no es un nodo de la lista");}
			
		}
	
	/**
	 *Ejercico de ejemplo explicado en clase practica jueves 21.04.2022
	Dada dos listas l1 y l2, retornar verdadero si l1 esta compuesta por las componentes de l2
	en el mismo orden seguidos de las componentes de l2 en orden inverso.
	*@return booleano True si cumple, false en caso contraio
		*b) Recibe por parametro a l2 y l1 recibe el mensaje.
		*ESTAMOS ADENTRO DEL TDA LISTA DE L1. 
	 */
	@SuppressWarnings("unused")
	private boolean respetaFormato(PositionList<E> l2) {
		boolean respeta = (tamanio == l2.size()*2);
		DNodo<E> cursorHead = head;
		DNodo<E> cursorTail = tail;
		Iterator<E> it = l2.iterator();
		
		while(it.hasNext() && respeta) {
			respeta = cursorHead.element().equals(it.next()) && cursorHead.element().equals(cursorTail.element());
			cursorHead = cursorHead.getNext();// Avanzo uno para adelante
			cursorTail = cursorTail.getPrev();// Avanazo uno para atras
		}
		return respeta;
	}
	
	
	/*
	 * Metodo de practica de un parcial viejo
	 */
	public void concatenar(PositionList<E> l) {
			for(Position<E> pos : l.positions()) {
					DNodo<E> n;
					try {
						n = checkPosition(pos);
						(tail.getPrev()).setNext(n);
						n.setNext(tail);
						n.setPrev(tail.getPrev());
						tail.setPrev(n);
					} catch (InvalidPositionException e) {
						e.printStackTrace();
					}
					
			}
			tamanio = tamanio + l.size();
	}
	
	public void clonar(PositionList<E> l) throws InvalidPositionException, EmptyListException {
		while(!l.isEmpty()) {
			l.remove(l.last());
		}
		for(Position<E> p : positions()) {
			l.addLast(p.element());
		}
	}
}