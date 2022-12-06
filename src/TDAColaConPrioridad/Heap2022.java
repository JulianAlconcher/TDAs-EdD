package TDAColaConPrioridad;

import java.util.Comparator;

import Exceptions.EmptyPriorityQueueException;
import Exceptions.InvalidKeyException;


public class Heap2022<K extends Comparable<K>,V> implements PriorityQueue<K,V> {
	
	protected Entrada<K,V>[] elems;
	protected Comparator<K> comp;
	protected int size;
	
	//Utilizamos la clase entrada para asociar prioridades con valores.
	@SuppressWarnings("hiding")
	private class Entrada<K,V> implements Entry<K,V> { //clase anidada
		private K clave;
		private V valor;
		@SuppressWarnings("unused")
		public Entrada(K clave, V valor) {
			this.clave = clave;
			this.valor = valor;
		}
		public K getKey() {
			return clave;
		}
		public V getValue() {
			return valor;
		}
		public String toString() {
			return "Entrada [clave=" + clave + ", valor=" + valor + "]";
		}
		
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * Constructor de la clase Heap.
	 * @param comp
	 */
	public Heap2022(Default_Comparator<K> comp) {
		elems = (Entrada<K,V>[]) new Entrada[11];
		this.comp = comp;
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
	public Entry<K, V> min() throws EmptyPriorityQueueException {
		if(size == 0)
			throw new EmptyPriorityQueueException("Cola vacia");
		return elems[1];
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("Clave invalida");
		Entrada<K,V> entrada = new Entrada<K,V>(key,value);
		elems[size++] = entrada;
		
		int i = size;
		boolean seguir = true;
		while(i<1 && seguir) {
			Entrada<K,V> elemActual = elems[i];
			Entrada<K,V> elemPadre = elems[i/2];
			if(comp.compare(elemActual.getKey(), elemPadre.getKey())<0) {
				Entrada<K,V> aux = elems[i];
				elems[i] = elems[i/2];
				elems[i/2] = aux;
				i = i/2;
			}
			else
				seguir = false;
		}
		return entrada;
	}

	@Override
	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		Entry<K,V> entrada = min();
		if(size == 1) {
			elems[1] = null;
			size = 0;
			return entrada;
		}
		else {
			elems[1] = elems[size];
			elems[size] = null;
			size--;
			int i = 1;
			boolean seguir = true;
			while(seguir) {
				int hi = i*2;
				int hd = i*2+1;
				boolean tieneHijoIzquierdo = hi <= size();
				boolean tieneHijoDerecho = hd <= size();
				if(!tieneHijoIzquierdo)
					seguir = false;
				else {
					int m;
					if(tieneHijoDerecho) {
						if(comp.compare(elems[hi].getKey(), elems[hd].getKey())<0) {
							m = hi;
						}
						else {m = hd;}	
					}
					else {
						m = hi;
					}
					
					if(comp.compare(elems[i].getKey(), elems[m].getKey())>0) {
						Entrada<K,V> aux = elems[i];
						elems[i]= elems[m];
						elems[m] = aux;
						i = m;
					}
					else
						seguir = false;
				}
				
			}
		}
		return entrada;
	}
	
}
