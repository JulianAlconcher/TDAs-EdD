package EjerciciosVarios;

import java.util.Iterator;

import Exceptions.InvalidKeyException;
import TDAMapeo.Entry;
import TDAMapeo.Map;
import TDAMapeo.MapeoConLista2022;

/*
 * Ejercicio 7
	a) Escriba en Java un procedimiento que dados dos mapeos M1 y M2, determine si todas las
		claves de M1 están contenidas en M2. //Preguntar!!!!!!
	
	b) Indique el orden del tiempo de ejecución del procedimiento implementado. Justifique su
		respuesta.
 */
public class Ej7TpMapeosYDiccionarios {
	public static void main (String args[]) {
		Map<Integer,Integer> m1 = new MapeoConLista2022<Integer,Integer>();
		Map<Integer,Integer> m2 = new MapeoConLista2022<Integer,Integer>();
		try {
			m1.put(12, 124);
			m1.put(52, 16);
			m1.put(13, 124);
			
			m2.put(52, 14);
			m2.put(12, 124);
			m2.put(13, 13);
			
			System.out.println("M1 Y M2 Cumple: " + Contenidas(m1,m2));
		
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}		
	}
	
	private static <K, V> boolean Contenidas (Map<K,V> m1, Map<K,V> m2) {
		boolean cumple = true;   									// c1
		Iterable<K> it1 = m1.keys();								// n
		Iterator<K> iterador = it1.iterator();						// n
		
		while(iterador.hasNext() && cumple) {						//0(n)	
			try {
				cumple = m2.get(iterador.next()) != null;			//0(n) si es implementado con lista. Preguntar
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			}
		}
		return cumple;												// c2
	}
	
}
