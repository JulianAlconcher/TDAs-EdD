package TDALista;

import java.util.Iterator;
import Exceptions.*;

public class ListaDeGianSimplementeEnlazada<E> implements PositionList<E>{
	
	protected Nodo<E> head;
	protected int cant;
	
	public ListaDeGianSimplementeEnlazada() {
		head = null;
		cant = 0;
	}
	
	public int size() {
		return cant;
	}

	public boolean isEmpty() {
		return (cant == 0); //head == null, tambien;
	}

	public Position<E> first() throws EmptyListException {
		if (cant == 0) 
			throw new EmptyListException("Lista vacia");

		return head;
	}

	public Position<E> last() throws EmptyListException {
		if (cant == 0)
			throw new EmptyListException("Lista vacia");
		
		Nodo<E> p = head;
		while(p.getSiguiente() != null)
			p = p.getSiguiente();
		
		return p;
	}

	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		Nodo<E> n = checkPosition(p);
		if (n.getSiguiente() == null)
			throw new BoundaryViolationException("Posicion invalida");
		
		return n.getSiguiente();
	}

	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		Nodo<E> n = checkPosition(p);
		
		if (n == head) //Si el primero no tiene previo;
			throw new BoundaryViolationException("Anterior al head");

		Nodo<E> aux = head; //Para buscarlo desde el principio;
		while((aux.getSiguiente() != n) && (aux.getSiguiente() != null)){ 
			aux = aux.getSiguiente(); //Voy recorriendo uno atr�s;
		}

		//Porque si dio una invalida, deberia tener un elemento m�s adelante
		if (aux.getSiguiente() == null)
			throw new InvalidPositionException("Posicion invalida");

		return aux;
	}
	
	public E set(Position<E> p, E element) throws InvalidPositionException {
		Nodo<E> actual = this.checkPosition(p);
		E retornar = actual.getElemento();
		actual.setElemento(element);
		
		return retornar;
	}

	public void addFirst(E element) {
 		//Head lo hago nuevo nodo que apunte a la antigua cabeza;
		head = new Nodo<E> (element,head);
		++cant;
	}

	public void addLast(E element) {

		if (cant == 0) //Si esta vacio
			this.addFirst(element);
		else{
			//Tengo que recorrer hasta el ultimo elemento;
			Nodo<E> aux = head;
			while(aux.getSiguiente() != null)
				aux = aux.getSiguiente();

			Nodo<E> nuevo = new Nodo<E>(element, null);
			aux.setSiguiente(nuevo);
			++cant;

			//Otra forma?
			//aux.setSiguiente(new Nodo<E>(element,null));
			//++longitud;
		}
	}

	//Ingresa nuevo Nodo despu�s (Joda) de la posicion dada;
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		
		Nodo<E> actual = this.checkPosition(p); //Nodo actual (para modif. el siguiente);
		
		Nodo<E> siguiente = null;
		try {
			siguiente = this.checkPosition(this.next(p)); //A cual va apuntar;
		}
		catch (BoundaryViolationException | InvalidPositionException e) {
			//throw new InvalidPositionException("Posicion invalida");
		}
		
		Nodo<E> nuevo = new Nodo<E>(element,null); //Nuevo nodo;
		actual.setSiguiente(nuevo); //Cambio el actual apuntando al nuevo;
		nuevo.setSiguiente(siguiente); //Le asigno el siguiente (que era el siguiente de actual);
		++cant;
	}

	//Ingreso elemento antes de la posicion dada!;
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		
		if (this.checkPosition(p) == head) //Agrego primero porque es antes!
			this.addFirst(element);
		else {
			Nodo<E> previo = null;
			try{
				previo = (Nodo<E>) prev(p);
			}
			catch (BoundaryViolationException | InvalidPositionException e){
				throw new InvalidPositionException("addBefore: no tiene previo");
			}
	
			Nodo<E> siguiente = (Nodo<E>) p;
			Nodo<E> nuevo = new Nodo<E>(element);
			previo.setSiguiente(nuevo);
			nuevo.setSiguiente(siguiente);
	
			++cant;
		}
	}

	public E remove(Position<E> p) throws InvalidPositionException {

		Nodo<E> nodoBorrado = null;
		
		if (cant == 0)
			throw new InvalidPositionException("Lista vacia");
		else{
 			//Obtengo el nodoBorrado;
			nodoBorrado = this.checkPosition(p);

			//Si es el head entonces lo cambio y listo;
			if (head == nodoBorrado){
				head = head.getSiguiente();
			}
			else{
				Nodo<E> previo;
				Nodo<E> siguiente = nodoBorrado.getSiguiente();
				try {
					previo = (Nodo<E>) prev(p);
				} 
				catch (BoundaryViolationException | InvalidPositionException e) {
					throw new InvalidPositionException("Remove: Posicion invalida");
				}
			
				previo.setSiguiente(siguiente); 
			}

			--cant;
		}		
		
		return (nodoBorrado.element());
	}

	public Iterator<E> iterator() {
		return new iteradorMiLista<E>(this);
	}

	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> toReturn = new ListaDeGianSimplementeEnlazada<Position<E>>();

		try {
			Position<E> primera = this.first();
			
			for(int i = 0; i < cant; i++) {
				toReturn.addLast(primera);
				primera = this.next(primera);
			}
		}
		catch(EmptyListException | InvalidPositionException | BoundaryViolationException e) {}
		
		return toReturn;
	}
	
	private Nodo<E> checkPosition(Position<E> p) throws InvalidPositionException{
		try {
			if (p == null || cant == 0)
				throw new InvalidPositionException("Posicion invalida");
			return ((Nodo<E>) p);
		}
		catch (ClassCastException e) {
			throw new InvalidPositionException("Posicion invalida");
		}
	}

	@Override
	public void clonar(PositionList<E> l) throws InvalidPositionException, EmptyListException {
		// TODO Auto-generated method stub
		
	}
	
}
