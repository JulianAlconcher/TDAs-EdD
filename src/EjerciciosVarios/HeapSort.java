package EjerciciosVarios;

import Exceptions.EmptyPriorityQueueException;
import Exceptions.InvalidKeyException;
import TDAColaConPrioridad.ColaCP_con_heap;
import TDAColaConPrioridad.Entry;
import TDAColaConPrioridad.PriorityQueue;

public class HeapSort {
	
	public <K extends Comparable<K>,V> void heapSort(Entry<K,V>[] arreglo) {
		PriorityQueue<K,V> cola = new ColaCP_con_heap<K ,V>();
		
			try {
				for(int i=0; i<arreglo.length; i++)
					cola.insert(arreglo[i].getKey(), arreglo[i].getValue());
				int j=0;
				while(!cola.isEmpty())
					arreglo[j++] = cola.removeMin();
			} catch (InvalidKeyException | EmptyPriorityQueueException e) {e.printStackTrace();
			}
	}
}
