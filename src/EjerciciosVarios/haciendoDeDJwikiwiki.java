package EjerciciosVarios;

import java.util.Iterator;

import Exceptions.EmptyPriorityQueueException;
import Exceptions.InvalidKeyException;
import Exceptions.InvalidPositionException;
import TDAArbol.Position;
import TDAArbol.Tree;
import TDAColaConPrioridad.ColaCP_con_heap;
import TDAColaConPrioridad.PriorityQueue;

/*
 * Dado un arbol T de enteros y un rotulo r, eliminar el subarbol con raiz p 
 * e imprimir por pantalla a sus nodos ordenados de menor a mayor 
 */
public class haciendoDeDJwikiwiki {
	public void wikiwiki(Tree<Integer> t,Integer r) {
		boolean encontre = false;
		PriorityQueue<Integer,Integer> heap = new ColaCP_con_heap<Integer,Integer>();
		Iterable<Position<Integer>> p = t.positions();
		Iterator<Position<Integer>> hijos = p.iterator();
		Position<Integer> pos = null;
		while(hijos.hasNext() && !encontre) {
			pos = hijos.next();
			if(pos.element()==r) {
				encontre = true;
			}
		}
		recorrido(t,pos,heap);
		
		while(!heap.isEmpty()) {
			try {
				System.out.println("-> " + heap.removeMin());
			} catch (EmptyPriorityQueueException e) {e.printStackTrace();
			}
		}
	}
	
	private void recorrido(Tree<Integer> t,Position<Integer> p,PriorityQueue<Integer,Integer> h) {
		try {
			for(Position<Integer> hijos : t.children(p)) {
				recorrido(t,hijos,h);
			}
			h.insert(p.element(), p.element());
			t.removeNode(p);
		} catch (InvalidPositionException | InvalidKeyException e) {e.printStackTrace();
		}
	}
}

