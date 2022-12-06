package TDAColaConPrioridad;

import java.util.Comparator;

import Exceptions.EmptyPriorityQueueException;
import Exceptions.InvalidKeyException;

public class ColaCP_con_arreglo<K extends Comparable<K>,V> implements PriorityQueue<K,V>{
	protected Entry<K,V>[] queue;
	int size;
	Comparator<K> comparator;
	
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
	public ColaCP_con_arreglo() {
		queue = (Entrada<K,V>[]) new Entrada[12];
		size = 0;
		comparator = new Default_Comparator<K>();
	}
	
	@SuppressWarnings("unchecked")
	public ColaCP_con_arreglo(Comparator<K> c) {
		queue = (Entrada<K,V>[]) new Entrada[12];
		size = 0;
		comparator = c;
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
		if(size==0)
			throw new EmptyPriorityQueueException("la cola esta vacia brodi");
		else return queue[size-1];
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		Entrada<K,V> newEntry = new Entrada<K,V>(key,value);                                                                   
		int pos;
		if(key==null)
			throw new InvalidKeyException("la clave es invalida brodi");
		if(size==queue.length)
			resize();
		pos = searchPos(key);
		moveRight(pos);
		queue[pos] = newEntry;
		size++;
		return newEntry;
		
	}
	
	private void moveRight(int pos) {
		for(int i=size;i>pos;i--)
			queue[i] = queue[i-1];
		
	}

	@SuppressWarnings("unchecked")
	private void resize() {
		Entry<K,V>[] aux = (Entrada<K,V>[]) new Entrada[queue.length*2];
		for(int i=0; i<queue.length;i++)
			aux[i]=queue[i];
		queue = aux;
	}
	private int searchPos(K key) {
		int i = 0;
		while(i<size&&0>comparator.compare(key, queue[i].getKey())) 
			i++;
		return i;
	}
	@Override
	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		if(size==0)
			throw new EmptyPriorityQueueException("la cola esta vacia brodi");
		Entry<K,V> toReturn = queue[size-1];
		queue[size-1]=null;
		size--;
		return toReturn;
	}

}