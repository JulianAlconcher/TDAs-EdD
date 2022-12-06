package TDAArbolBinariodeBusqueda;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;

public class ConjuntoABB<E extends Comparable<E>> implements Set<E>{

	protected ArbolBinariodeBusqueda<E> elemns;
	protected int size;
	
	public ConjuntoABB(Comparator<E> comp) {
		elemns = new ArbolBinariodeBusqueda<E>(comp); // Creo un ABB vacío
		size = 0; // El conjunto vacío no tiene elementos
		}
	public ConjuntoABB() {
		this(new DefaultComparator<E>());
		}
	
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	/*
	public void insert( E x ) {
		// Busco la ubicación de x en el ABB elems:
		NodoABB<E> p = elemns.buscar(x);
		if (p.getRotulo() == null ) { // x no estaba en el abb
		elemns.expandir(p); // Agrega dos hijos dummy en el abb
		// Comportamiento específico del conjunto:
		p.setRotulo(x); // Almaceno x en el nodo
		size++; // Cuento el nuevo elemento
		}
		}

	public boolean member( E x ) {
		return elemns.buscar(x).getRotulo() != null;
		// Si encontré a x, el rótulo del nodo no es null
		}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
*/
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
}
