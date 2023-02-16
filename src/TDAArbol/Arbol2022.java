package TDAArbol;

import java.util.Iterator;

import Exceptions.BoundaryViolationException;
import Exceptions.EmptyListException;
import Exceptions.EmptyTreeException;
import Exceptions.InvalidOperationException;
import Exceptions.InvalidPositionException;
import TDALista.ListaDoblementeEnlazada2022;
import TDALista.PositionList;

public class Arbol2022<E> implements Tree<E>{
	
	protected TNodo<E> root;
	protected int size;
	
	public Arbol2022() {
		root = null;
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<E> iterator() {
		PositionList<E> l = new ListaDoblementeEnlazada2022<E>();
		for( Position<E> p : positions() )
			l.addLast( p.element() );
		
		return l.iterator();
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> l = new ListaDoblementeEnlazada2022<Position<E>>();
		// Si el árbol no está vacío, hago un recorrido preorden desde la raíz:
		if(size>0) 
			recPreorden(root, l);
		return l;
	}
	/**
	 * Recorrido pre-orden del arbol
	 * @param v
	 * @param l
	 */
	private void recPreorden(TNodo<E> v, PositionList<Position<E>> l) {
		l.addLast( v ); // La visita de v consiste de encolar v en l
		for( TNodo<E> h : v.getHijos()) // para cada hijo h de v hacer
			recPreorden( h, l ); // preorden de h
	}
	/**
	 * Recorrido post-orden del arbol
	 * @param v
	 * @param l
	 */
	@SuppressWarnings("unused")
	private void rePostorden(TNodo<E> v, PositionList<Position<E>> l) {
		for( TNodo<E> h: v.getHijos()) 
			rePostorden(h,l);
		l.addLast(v);
	}

	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(v);
		E elem = nodo.element();
		nodo.setElemento(e);
		
		return elem;
	}

	@Override
	public Position<E> root() throws EmptyTreeException {
		if(size == 0)
			throw new EmptyTreeException("Arbol vacio");
		return root;
	}

	@Override
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
	
		TNodo<E> nodo = checkPosition(v);
		if(nodo == root)
			throw new BoundaryViolationException("el nodo es la raiz");
		
		return nodo.getPadre();
	}

	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		TNodo<E> p = checkPosition(v);
		PositionList<Position<E>> lista = new ListaDoblementeEnlazada2022<Position<E>>();
		for(TNodo<E> n : p.getHijos())
			lista.addLast(n);
		return lista;
	}

	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(v);
		return !nodo.getHijos().isEmpty();
	}

	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(v);
		return nodo.getHijos().isEmpty();
	}
	

	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(v);
		return (root == nodo);
	}

	@Override
	public void createRoot(E e) throws InvalidOperationException {
		if(size != 0){
			throw new InvalidOperationException("Arbol no vacio");
		}
		root = new TNodo<E>(e);
		size++;
		
	}

	@Override
	public Position<E> addFirstChild(Position<E> p, E e) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(p);
		TNodo<E> nuevo = new TNodo<E>(e,nodo);
		if(size==0)
			throw new InvalidPositionException("arbol vacio");
		nodo.getHijos().addFirst(nuevo);
		size++;
		return nuevo;
	}

	@Override
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException {
		if(size == 0){
			throw new InvalidPositionException("Arbol vacio");
		}
		TNodo<E> nodo = checkPosition(p);
		TNodo<E> nuevo = new TNodo<E>(e,nodo);
		nodo.getHijos().addLast(nuevo);
		size++;
		return nuevo;
	}

	@Override
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException {
		TNodo<E> n = checkPosition(p);
		TNodo<E> hd = checkPosition(rb);
		TNodo<E> nuevo = new TNodo<E>(e,n);
		PositionList<TNodo<E>> hijos = n.getHijos();
		if(size==0)
			throw new InvalidPositionException("arbol vacio");
		boolean encontre = false;
		//buscamos donde esta el right brother en la lista de hijos
		try {
		TDALista.Position<TNodo<E>> pp = hijos.first();
		while(pp != null && !encontre) {
			if(hd == pp.element())
				encontre = true;
			else
				if(pp != hijos.last())
					pp=hijos.next(pp);
				else
					pp = null;
		}	
		
		hijos.addBefore(pp, nuevo);
		
		}catch(EmptyListException | BoundaryViolationException f) {f.getMessage();}
		if(!encontre)
			throw new InvalidPositionException("p no es padre de rb");
		size++;
		return nuevo;
	}
	

	@Override
	public Position<E> addAfter(Position<E> p, Position<E> lb, E e) throws InvalidPositionException {
		TNodo<E> n = checkPosition(p);
		TNodo<E> hi = checkPosition(lb);
		TNodo<E> nuevo = new TNodo<E>(e,n);
		boolean encontre = false;
		if(size==0)
			throw new InvalidPositionException("arbol vacio");
		PositionList<TNodo<E>> hijos = n.getHijos();
		
		try {
			TDALista.Position<TNodo<E>> aux = hijos.first();
			while( aux!=null && !encontre) {
				if(hi == aux.element())
					encontre = true;
				else
					if( aux != hijos.last())
						aux = hijos.next(aux);
					else
						aux = null;
				
			}
			hijos.addAfter(aux, nuevo);
		}catch(InvalidPositionException | BoundaryViolationException | EmptyListException f) {f.getMessage();}
		if(!encontre)
			throw new InvalidPositionException("p no es padre de lb");
		
		size++;
		return nuevo;
	}
	@Override
	public void removeExternalNode(Position<E> p) throws InvalidPositionException {
		if(size == 0)
			throw new InvalidPositionException("Arbol vacio");
		TNodo<E> n = checkPosition(p);
		if(isInternal(p))
			throw new InvalidPositionException("p no es una hoja");
		if(n == root)
			removeRoot(p);
		else {
				
		TNodo<E> padre = n.getPadre();
		PositionList<TNodo<E>> hijosPadre = padre.getHijos();
		boolean encontre = false;
		
		TDALista.Position<TNodo<E>> pp = null;
		Iterable<TDALista.Position<TNodo<E>>> posiciones = hijosPadre.positions();
		Iterator<TDALista.Position<TNodo<E>>> it = posiciones.iterator();
		
		while(it.hasNext() && !encontre) {
			pp = it.next();
			if(pp.element() == n)
				encontre = true;
		}
		if(!encontre)
			throw new InvalidPositionException("P no aparece en la lista de hijos de su padre: NO ELIMINE!");
		
		hijosPadre.remove(pp);
		n.setElemento(null);
		size--;
		
		if(size==0)
			root = null;
		}
	}
	
	@Override
	public void removeInternalNode(Position<E> p) throws InvalidPositionException {
		if(size == 0)
			throw new InvalidPositionException("El arbol esta vacio");
		if(isExternal(p))
			throw new InvalidPositionException("El nodo no es interno");
		TNodo<E> n = checkPosition(p);
		TNodo<E> padre = n.getPadre();
		
		if(isRoot(p))
			removeRoot(p);
		else {
			PositionList<TNodo<E>> hPadre = padre.getHijos();
			PositionList<TNodo<E>> hN = n.getHijos();
			try {
				TDALista.Position<TNodo<E>> posDeN;
				TDALista.Position<TNodo<E>> cursor = hPadre.first();
				
				while(cursor.element() != n && cursor !=null) {
					if(cursor == hPadre.last())
						cursor = null;
					else 
						cursor = hPadre.next(cursor);
				}
				
				if(cursor != null)
					posDeN = cursor;
				
				else throw new InvalidPositionException("La estructura no corresponde a un arbol");
				
				while(!hN.isEmpty()) {
					TDALista.Position<TNodo<E>> hijoN = hN.first();
					hPadre.addBefore(posDeN, hijoN.element());
					hijoN.element().setPadre(padre);
					hN.remove(hijoN);
				}
				hPadre.remove(posDeN);
			
			}catch(EmptyListException | BoundaryViolationException e) {e.getMessage();}
		}
		size--;
			
	}
	@Override
	public void removeNode(Position<E> p) throws InvalidPositionException {
		if(isRoot(p))
			removeRoot(p);
		else 
			if(isInternal(p))
				removeInternalNode(p);
		else 
			if(isExternal(p))
				removeExternalNode(p);
	}
	
	protected void removeRoot(Position<E> p) throws InvalidPositionException{
		if(size == 0)
			throw new InvalidPositionException("no se puede eliminar un arbol vacio");
		try {
			if(root.getHijos().size() == 1) 
			{
				TDALista.Position<TNodo<E>> rN = root.getHijos().first();
				rN.element().setPadre(null);
				TNodo<E> aux = rN.element();
				root.getHijos().remove(rN);
				root = aux;
				size--;
			}
			else if(size == 1) 
			{
				root = null;
				size--;
			}
			else throw new InvalidPositionException("Solo se puede eliminar la raiz si es el unico elemento o tiene un solo hijo");
		
		}catch(EmptyListException e) {
			e.getMessage();
		}
	}
	
	
	private TNodo<E> checkPosition(Position<E> p) throws InvalidPositionException{
		try{
			if(p == null){
				throw new InvalidPositionException("Posicion nula");
			}
			if(p.element() == null){
				throw new InvalidPositionException("Posicion eliminada previamente");
			}
			return (TNodo<E>) p;
		}catch(ClassCastException e){
			throw new InvalidPositionException("La posicion no es de esta lista");
		}
	}

	public String toStringARBOL(Position<E> p) {
		TNodo<E> nodo;
		try {
			nodo = checkPosition(p);
			return (String) nodo.element();
		} catch (InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String stringDePos(Position<E> p) {
		TNodo<E> n;
		try {
			n = checkPosition(p);
			return n.element().toString();

		} catch (InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
