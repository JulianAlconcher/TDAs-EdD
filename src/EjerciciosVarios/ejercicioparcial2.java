package EjerciciosVarios;

import Exceptions.EmptyPriorityQueueException;
import Exceptions.EmptyTreeException;
import Exceptions.InvalidKeyException;
import Exceptions.InvalidOperationException;
import Exceptions.InvalidPositionException;

import TDAArbol.Arbol2022;
import TDAArbol.Position;
import TDAArbol.Tree;
import TDAColaConPrioridad.ColaCP_con_heap;
import TDAColaConPrioridad.PriorityQueue;

/**
 * ) Implemente en java una consulta que reciba un árbol de enteros e imprima por pantalla los
rótulos de sus nodos ordenados en forma ascendente en base a la cantidad de hijos de cada nodo. Es
decir, considere que un nodo A es mayor que un nodo B si la cantidad de hijos de A es mayor que la
cantidad de hijos de B.
Para resolver este ejercicio utilice una cola con prioridad sin implementarla, pero si deberá
implementar los métodos auxiliares utilizados.
 */
public class ejercicioparcial2 {

	public static void main(String[] args) {
		Tree<Integer> arbol = new Arbol2022<Integer>();
		try {
			arbol.createRoot(1);
			Position<Integer> posde2 = arbol.addLastChild(arbol.root(), 2);
			arbol.addLastChild(arbol.root(), 3);
			arbol.addLastChild(posde2, 4);
			arbol.addLastChild(posde2, 5);
			arbol.addLastChild(posde2, 6);
			
			
			System.out.println(ejercicio(arbol));
		} catch (InvalidOperationException e) {e.printStackTrace();
		} catch (InvalidPositionException e) {e.printStackTrace();
		} catch (EmptyTreeException e) {e.printStackTrace();
		}
	}

	public static String ejercicio(Tree<Integer> a) {
		PriorityQueue<Integer,Integer> colacp = new ColaCP_con_heap<Integer,Integer>();
		for(Position<Integer> pos : a.positions()) {
			int contador = 0;
			try {
				for(Position<Integer> hijos : a.children(pos)) {
					contador++;
				}
				colacp.insert(contador, pos.element());
			} catch (InvalidPositionException e) {e.printStackTrace();
			} catch (InvalidKeyException e) {e.printStackTrace();}
			
			
		}
		return toStringColaCP(colacp);
	}
	
	public static String toStringColaCP(PriorityQueue<Integer,Integer> c) {
		String toString = "";
		while(!c.isEmpty()) {
			try {
				toString = toString + c.removeMin().toString();
			} catch (EmptyPriorityQueueException e) {e.printStackTrace();
			}
		}
		return toString;
	}
	
}
