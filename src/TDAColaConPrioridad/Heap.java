package TDAColaConPrioridad;

import java.util.Comparator;

import Exceptions.*;

public class Heap<K, V> implements PriorityQueue<K, V> {
	
	protected Entrada<K,V>[] elems;
	protected Comparator<K> comp;
	protected int size;
	
	@SuppressWarnings("hiding")
	private class Entrada<K,V> implements Entry<K,V> { //clase anidada
		private K clave;
		private V valor;
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
	public Heap(Comparator<K> comp) {
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
		return (size == 0);
	}

	@Override
	public Entry<K, V> min() throws EmptyPriorityQueueException {
		if(size==0) {
			throw new EmptyPriorityQueueException("COLA VACIA");
		}
		return elems[1]; //La componente 0 del arreglo NO se usa
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		if(key == null) {
			throw new InvalidKeyException("CLAVE INVALIDA");
		}
		
		if(size == elems.length - 1) {
			resize();
		}
		
		Entrada<K,V> nueva = new Entrada<K,V>(key,value);
		elems[size++] = nueva;
		
		//ORDENAMIENTO BURBUJA HACIA ARRIBA
		int i = size;
		boolean seguir = true;
		while(i > 1 && seguir) {
			Entrada<K,V> actual = elems[i];
			Entrada<K,V> padre = elems[i/2];
			if(comp.compare(actual.getKey(),padre.getKey()) < 0) {
				Entrada<K,V> aux = elems[i];
				elems[i] = elems[i/2];
				elems[i/2] = aux;
				i /= 2;
			} else {
				seguir = false;
			}
		}
		return nueva;
	}
	
	/*
	 * Duplica la cola
	 */
	@SuppressWarnings("unchecked")
	private void resize() {
		Entrada<K,V>[] viejo = elems;
		elems = (Entrada<K,V>[]) new Entrada[elems.length * 2];
		for(int i = 0;i < viejo.length;i++) {
			elems[i] = viejo[i];
		}
	}

	@Override
	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		Entrada<K,V> ret = (Entrada<K, V>) min();
		elems[1] = elems[size];
		elems[size] = null;
		size--;
		
		//ORDENAMIENTO BURBUJA HACIA ABAJO
		int i = 1;
		boolean seguir = true;
		while(seguir) {
			boolean tieneHIzq = (i * 2) <= size();
			boolean tieneHDer = (i * 2 + 1) <= size();
			int posmin = 0;
			if(!tieneHIzq) {
				seguir = false;
			} else {
				if(tieneHDer) {
					if(comp.compare(elems[i*2].getKey(), elems[i*2 + 1].getKey()) < 0) {
						posmin = i * 2;
					} else {
						posmin = i * 2 + 1;
					}
				} else {
					posmin = i * 2;
				}
			}
			if(comp.compare(elems[i].getKey(), elems[posmin].getKey()) < 0) {
				Entrada<K,V> aux = elems[i];
				elems[i] = elems[posmin];
				elems[posmin] = aux;
				i = posmin;
			} else {
				seguir = false;
			}
		}
		
		return ret;
	}

}
