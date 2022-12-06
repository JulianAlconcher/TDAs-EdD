package TDALista;

import java.util.Iterator;

import Exceptions.BoundaryViolationException;
import Exceptions.EmptyListException;
import Exceptions.EmptyStackException;
import Exceptions.InvalidPositionException;
import Exceptions.OperacionInvalidaException;
import TDAPila.PilaConEnlaces;

public class ListaSimpleEnlazada<E> implements PositionList<E> 
{
	protected Nodo<E> head;
	protected int tamanio;
	
	/*
	 * Inicializa la cabeza en nulo y el tamaño en 0
	 */
	public ListaSimpleEnlazada()
	{
		head = null;
		tamanio = 0;
	}
	/*
	 * Retorna el tamaño de la lista
	 */
	public int size() {
		
		return tamanio;
	}

	/*
	 * Devuelve si la lista esta vacia o no.
	 */
	public boolean isEmpty() {
		
		return (head==null);
	}

	/*
	 * retorna la posición del primer elemento de la lista.
	 * error si la lista está vacía.
	 */
	public Position<E> first() throws EmptyListException 
	{
		if(tamanio==0)
				throw new EmptyListException ("Lista vacia");
			
			return head;
	}

	/*
	 * retorna la posicion del último elemento de la lista
	 * error si la lista está vacía
	 */
	public Position<E> last() throws EmptyListException {
		if(tamanio == 0)
			throw new EmptyListException("Lista vacia");
		
		Nodo<E> aux = head;
		while(aux.getSiguiente() != null)
			aux = aux.getSiguiente();
		
		return aux;
		
	}

	/*
	 * retorna la posición del elemento que sigue al elemento en la posición p
	 * error si p = last()
	 */
	public Position<E> next (Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		Nodo<E> n = checkPosition(p);
		if(n.getSiguiente() == null)
			throw new BoundaryViolationException("Posicion invalida");
		return n.getSiguiente();
	}

	/*
	 * retorna la posición del elemento que precede al elemento en la posición p
	 * error si p = first()
	 */
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		
		Nodo<E> a = checkPosition(p);
		//Primer caso: que el primero NO tenga previo.
		if(head==null)
			throw new BoundaryViolationException("Anterior a la cabeza de la lista ");
		//Segundo caso: Para buscar el P desde el principio
		Nodo<E> aux = head;
		while((aux.getSiguiente() != a) && (aux.getSiguiente() != null))
			aux= aux.getSiguiente();//avanzo en el recorrido
		//Tercera caso, si es el ultimo elemento
		if(aux.getSiguiente()==null)
			throw new InvalidPositionException("Posicion Invalida");
		
		return aux;
	}

	/*
	 * Inserta un nuevo elemento e como primer elemento
	 */
	public void addFirst(E element) 
	{
		head = new Nodo<E>(element,head);
		tamanio++;
		
	}

	/*
	 * Inserta un nuevo elemento e como último elemento
	 */
	public void addLast(E element) {
		//Caso de que la lista este vacia, lo agrego al principio (o a lo ultimo)
		if(tamanio == 0 && head==null)
			this.addFirst(element);
		
		//Caso contrario, recorro hasta el ultimo elemento
		Nodo<E> aux = head;
		while(aux.getSiguiente() != null)//mientras que el elemento no tenga un nulo que le siga
			aux = aux.getSiguiente();//recorro la lista
		Nodo<E> nuevo = new Nodo<E>(element, null);
		aux.setSiguiente(nuevo);//seteo el nuevo como siguiente del aux
		tamanio++;//incremento el tamanio
		
	}

	/*
	 * Inserta un nuevo elemento e luego de la posición p
	 */
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		
		Nodo<E> n = this.checkPosition(p);//chequeo posicion
		Nodo<E> nuevo = new Nodo<E>(element);
		
		nuevo.setSiguiente(n.getSiguiente());//el siguiente del nuevo va a ser el siguiente de n
		n.setSiguiente(nuevo);//el siguiente de n pasa a ser el nuevo
		tamanio++;
		
	}

	/*
	 *Inserta un nuevo elemento e antes de la posición p 
	 */
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		Nodo<E> n = checkPosition(p);
		Nodo<E> anteriorAN = head;
		
		while(anteriorAN.getSiguiente()!= n) {
			anteriorAN = anteriorAN.getSiguiente();
		}
		
		if(anteriorAN.getSiguiente() == n) {
			Nodo<E> nuevo = new Nodo<E>(element);
			nuevo.setSiguiente(n);
			anteriorAN.setSiguiente(nuevo);
			tamanio++;
		}
	}

	/*
	 * : Elimina y retorna el elemento en la posición p invalidando la posición p.
	 */
	public E remove(Position<E> p) throws InvalidPositionException {
		Nodo<E> n = checkPosition(p);
		if(tamanio==0)
			throw new InvalidPositionException("Lista vacia");
		Nodo<E> aux = head;
		while(aux.getSiguiente()!= n) {
			aux = aux.getSiguiente();
		}
		
		aux.setSiguiente(n.getSiguiente());
		tamanio--;
		
		return n.element();
		
	}
	

	/*
	 * Reemplaza al elemento en la posición p con e, 
	 * @return el elemento que estaba antes en la posición p
	 */
	public E set(Position<E> p, E element) throws InvalidPositionException {
		
		Nodo<E> actual = this.checkPosition(p);
		E retornar = actual.getElemento();
		actual.setElemento(element);
		
		return retornar;
	}


	public Iterator<E> iterator() {
	
		return new iteradorMiLista<E>(this);
	}

	
	public Iterable<Position<E>> positions() {
	
		PositionList<Position<E>> toReturn = new ListaSimpleEnlazada<Position<E>>();

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
	
	/*
	 * METODO PRIVADO
	 * Convierte a la posicion pasada por parametro en un Nodo de la lista doblemente enlazada.
	 * comprueba que la posicion p sea valida
	 */

	private Nodo<E> checkPosition( Position<E> p ) throws InvalidPositionException {
		try{return (Nodo<E>) p;} 
		
		catch( ClassCastException e ) {throw new InvalidPositionException( "La posicion es invalida" );}
		
	} 
	
	
	
	private E fondoDePila(PilaConEnlaces<E> p) throws EmptyStackException 
	{
	E retorno = null;
	if(head==null && tamanio==0) 
		throw new EmptyStackException("Pila vacia");
	else 
	{
		while(tamanio != 1 || !(p.isEmpty())) 
		{
		retorno= p.pop();
		tamanio--;
		}
	}
		return retorno;
	}
	
	@SuppressWarnings("unused")
	private void MetodoPilaLista(PilaConEnlaces<E> p) throws OperacionInvalidaException, EmptyStackException
	{
	boolean encontre=false;
	while(tamanio != 0 || encontre==true) 
	{
		if(head.getElemento()==fondoDePila(p)) 
		{
			this.addLast(fondoDePila(p));
			encontre=true;
		}
		else
		{ 
			head.getSiguiente();
			tamanio--;
		}
		if(encontre==false)
			throw new OperacionInvalidaException("La operacion es invalida");
	}
		
		
	}
}
