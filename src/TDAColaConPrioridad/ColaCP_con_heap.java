package TDAColaConPrioridad;

import java.util.Comparator;

import Exceptions.EmptyPriorityQueueException;
import Exceptions.InvalidKeyException;

public class ColaCP_con_heap<K extends Comparable<K>,V> implements PriorityQueue<K,V> {
	protected Entry<K,V>[] queue;
	protected int size;
	protected Comparator<K> comparator;
	
	
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
	public ColaCP_con_heap() {
		queue = (Entrada<K,V>[]) new Entrada[12];
		size = 0;
		comparator = new Default_Comparator<K>();
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
			throw new EmptyPriorityQueueException("La cola esta vacia");
		return queue[1];
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("la clave es nula");
		if(queue.length == size + 1)
			redimensionar();
		Entry<K,V> newEntry= new Entrada<K,V>(key,value);
		queue[++size] = newEntry;
		ordenar(size,size/2);
		return newEntry;
	}

	@SuppressWarnings("unchecked")
	private void redimensionar() {
		Entry<K,V>[] aux = queue;
		queue = (Entrada<K,V>[]) new Entrada[aux.length*2];
		for(int i = 1;i<=size;i++)
			queue[i] = aux[i];
	}

	private void ordenar(int i, int pi) {
		Entry<K,V> aux;
		if(pi!=0 && queue[pi].getKey().compareTo(queue[i].getKey())>0) {
			aux = queue[pi];
			queue[pi] = queue[i];
			queue[i] = aux;
			i = pi;
			pi = i/2;
			ordenar(i,pi);
		}
		
	}

	@Override
	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		if(size == 0)
			throw new EmptyPriorityQueueException("La cola esta vacia");
		Entry<K,V> toReturn = queue[1];
		queue[1] = queue[size];
		queue[size] = null;
		size--;
		acomodar(1);
		return toReturn;
	}

	private void acomodar(int i) {
		int izq = i*2;
		int der = i*2 + 1;
		Entry<K,V> aux;
		if(size >= der && queue[izq]!=null && queue[der]!=null) {
			if(queue[i].getKey().compareTo(queue[izq].getKey())>0 || queue[i].getKey().compareTo(queue[der].getKey())>0)
				if(queue[i].getKey().compareTo(queue[izq].getKey())>0 && queue[der].getKey().compareTo(queue[izq].getKey())>0) {
					aux = queue[izq];
					queue[izq] = queue[i];
					queue[i] = aux;
					acomodar(izq);
				}
				else {
					aux = queue[der];
					queue[der] = queue[i];
					queue[i] = aux;
					acomodar(der);
				}
			}
		else
			if(size >= izq && queue[izq]!=null)
				if(queue[i].getKey().compareTo(queue[izq].getKey())>0) {
					aux = queue[izq];
					queue[izq] = queue[i];
					queue[i] = aux;
					acomodar(izq);
				}
		}
}