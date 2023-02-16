package EjerciciosVarios;

import java.util.Comparator;

import Exceptions.EmptyPriorityQueueException;
import Exceptions.EmptyTreeException;
import Exceptions.InvalidKeyException;
import Exceptions.InvalidOperationException;
import Exceptions.InvalidPositionException;
import TDAArbol.Arbol;
import TDAArbol.Position;
import TDAArbol.Tree;
import TDAColaConPrioridad.ColaCP_con_heap;
import TDAColaConPrioridad.PriorityQueue;

/**
 	Implemente en java una consulta que reciba un árbol de enteros e 
 	-->imprima por pantalla los rótulos de sus nodo ordenados en forma ascendente en base a la cantidad de hijos de cada nodo. 
 	Es decir, considere que un nodo A es mayor que un nodo B si la cantidad de hijos de A es mayor que la cantidad de hijos de B. 
	
	Para resolver este ejercicio utilice una cola con prioridad sin implementarla, pero sí deberá implementar los métodos auxiliares
	utilizados. CALCULAR TIEMPO DE EJECUCIÓN
*/
public class ArbolConColita {
	static Comparator<Integer> compa = new ComparadorInverso<Integer>();
	
	public static void main(String args[]) {
		Tree<Character> arbol = new Arbol<Character>(); // Creo un arbol de caracteres
		
		try {
			arbol.createRoot('A' );
			Position<Character> raiz = arbol.root();
			Position<Character> pB = arbol.addLastChild( raiz, 'B' );
			Position<Character> pC = arbol.addLastChild( raiz, 'C' );
			@SuppressWarnings("unused")
			Position<Character> pD = arbol.addLastChild( raiz, 'D' );
			Position<Character> pH = arbol.addLastChild( pB,'H' );
			arbol.addLastChild( pB, 'F' );
			arbol.addFirstChild( pH, 'G' );
			arbol.addFirstChild( pH, 'K' );
			arbol.addLastChild( pC, 'E' ); 
			
			System.out.println("Probamos metodo de menor a mayor (por ahora) ");
			metodoNORec(arbol);
			System.out.println("");
			metodoSIRec(arbol);

		} catch (InvalidOperationException | InvalidPositionException | EmptyTreeException e) {e.printStackTrace();
		} 
		
	}
	
	public static  class ComparadorInverso<E extends Comparable<E>> implements Comparator<E> {
	    
	    @Override
	    public int compare(E o1, E o2) {
	    	System.out.println("Estoy usando al compa");
	        return o2.compareTo(o1);
	    }
	                
	}
	
	public static <E> void metodoNORec(Tree<E> t) {
		int cont = 0;
		PriorityQueue<Integer,E> cola = new ColaCP_con_heap<Integer,E>();
		for(Position<E> p : t.positions()) {
			try {
				for(Position<E> h : t.children(p)) {
					cont++;
				}
				cola.insert(cont, p.element());
				cont = 0;
			} catch (InvalidPositionException | InvalidKeyException e) {e.printStackTrace();
			}
		}
		while(!cola.isEmpty())
			try {
				System.out.print( cola.removeMin().getValue() + " , ");
			} catch (EmptyPriorityQueueException e) {e.printStackTrace();
			}
	}
	
	public static <E> void metodoSIRec(Tree<E> t) {
		PriorityQueue<Integer,E> cola = new ColaCP_con_heap<Integer,E>();
		try {
			metodoRec(t,cola,t.root());
			
			System.out.println("METODO RECURSIVO");
			while(!cola.isEmpty())
				try {
					
					System.out.print( cola.removeMin().getValue() + " , ");
				} catch (EmptyPriorityQueueException e) {e.printStackTrace();
			}
		} catch (EmptyTreeException e) {e.printStackTrace();
		}
	}
	
	public static <E> void metodoRec(Tree<E> t,PriorityQueue<Integer,E> cola,Position<E> r) {
		int cont = 0;
		try {
			for(Position<E> h : t.children(r)) {
				cont++;
				metodoRec(t,cola,h);
				cola.insert(cont, h.element());
			}
		} catch (InvalidPositionException | InvalidKeyException e) {e.printStackTrace();
		}
		
	}
}
