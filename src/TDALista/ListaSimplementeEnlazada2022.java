package TDALista;

import java.util.Iterator;

import Exceptions.*;
import TDAPila.Stack;

public class ListaSimplementeEnlazada2022<E> implements PositionList<E> {
	
	private Nodo<E> head;
	private int tamanio;
	
	public ListaSimplementeEnlazada2022()
	{
		head = null;
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
		return head;
	}

	@Override
	public Position<E> last() throws EmptyListException {
		if(tamanio==0)
			throw new EmptyListException("Lista vacia");
		Nodo<E> aux = head;
		while(aux.getSiguiente()!=null ) {
			aux = aux.getSiguiente();
		}
		
		return aux;
	}

	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		Nodo<E> n = checkPosition(p);
		if(n.getSiguiente()==null)
			throw new BoundaryViolationException("Posicion siguiente nula");
		return n.getSiguiente();
	}

	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		Nodo<E> n = checkPosition(p);
		if(head==null)
			throw new BoundaryViolationException("anterior al head");
		Nodo<E> aux = head;
		while((aux.getSiguiente()!= n) && (aux.getSiguiente()!=null)) {
			aux = aux.getSiguiente();
		}
		if(aux.getSiguiente()==null)
			throw new InvalidPositionException("Posicion invalida");
	
		return aux;
	}

	@Override
	public void addFirst(E element) {
		head = new Nodo<E>(element,head);
		tamanio++;
		
	}

	@Override
	public void addLast(E element) {
		if(tamanio==0)
			addFirst(element);
		else {
		Nodo<E> p = head;
		while(p.getSiguiente()!=null) {
			p = p.getSiguiente();
		}
		Nodo<E> nuevo = new Nodo<E>(element, null);
		p.setSiguiente(nuevo);
		tamanio++;
		}
	}

	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		if(p==null)
			throw new InvalidPositionException("No se puede añadir a lo ultimo en una lista vacia");
		Nodo<E> n = checkPosition(p);
		
		Nodo<E> nuevo = new Nodo<E>(element);
		n.setSiguiente(nuevo);
		nuevo.setSiguiente(n.getSiguiente());
		tamanio++;
	}

	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		Nodo<E> n = checkPosition(p);
		if(n==head)
			addFirst(element);
		else {
		Nodo<E> aux = head;
		
		while(aux.getSiguiente()!= n) {
			aux = aux.getSiguiente();
		}
		
		Nodo<E> nuevo = new Nodo<E>(element);
		aux.setSiguiente(nuevo);
		nuevo.setSiguiente(n);
		}
		tamanio++;
		
		
	}

	@Override
	public E remove(Position<E> p) throws InvalidPositionException {

		Nodo<E> nodoBorrado = null;
		
		if (tamanio == 0)
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

			--tamanio;
		}		
		
		return (nodoBorrado.element());
	}

	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException {
		if(tamanio == 0) 
			throw new InvalidPositionException("Lista vacia");
		
		Nodo<E> n = checkPosition(p);
		E retornar = n.getElemento();
		n.setElemento(element);
		
		return retornar;
	}
	/**
	 * Metodo privado que castea la posicion a un nodo y chequea q la posicion sea valida.
	 * @return la posicion casteada a nodo.
	 */
	private Nodo<E> checkPosition(Position<E> p) throws InvalidPositionException{
		try {
			if (p == null )
				throw new InvalidPositionException("Posicion invalida");
			return ((Nodo<E>) p);
		}
		catch (ClassCastException e) {
			throw new InvalidPositionException("Posicion invalida");
		}
	}

	@Override
	public Iterator<E> iterator() {
		
		return new iteradorMiLista<E>(this);
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> toReturn = new ListaSimplementeEnlazada2022<Position<E>>();

		try {
			Position<E> primera = this.first();
			
			for(int i = 0; i < tamanio; i++) {
				toReturn.addLast(primera);
				primera = this.next(primera);
			}
		}
		catch(EmptyListException | InvalidPositionException | BoundaryViolationException e) {}
		
		return toReturn;
	}
	
	/**
	 * c) En Java, agregue un método a la clase ListaSE<E> definida anteriormente, que dada una pila verifique si en la lista receptora 
	 * del mensaje se encuentra el elemento del fondo de la pila. En caso de encontrarlo deberá insertarlo al final de la lista,
	 *  caso contrario se deberá lanzar la excepción programada en el inciso (b). 
	 *  Si utiliza métodos del TDA lista deberá implementarlos, así también todo método auxiliar utilizado. 
	 * Puede asumir que cuenta con el TDA pila totalmente implementado.
	 * @throws OperacionInvalidaException 
	 */
	public boolean estaEnFondoDePila(Stack<E> p) throws OperacionInvalidaException {
		Stack<E> copia = p;
		boolean retorno = false;
		E fondoDePila = null;
		while(!copia.isEmpty()) {
			try {
				fondoDePila = copia.pop();
			} catch (EmptyStackException e) {
				e.printStackTrace();
			}
		}
		Nodo<E> aux = head;
		try {
			while(aux.getSiguiente()!= this.last() && retorno==false) {
				
				if(aux.element().equals(fondoDePila)) {
					retorno = true;
					this.addLast(aux.element());
				}
				else aux = aux.getSiguiente();
			}
			if(retorno == false)
				throw new OperacionInvalidaException("No es posible encontrar el elemento");
		} catch (EmptyListException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	@Override
	public void clonar(PositionList<E> l) throws InvalidPositionException, EmptyListException {
		// TODO Auto-generated method stub
		
	}
}