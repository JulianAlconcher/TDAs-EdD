package EjerciciosVarios;

import Exceptions.EmptyTreeException;
import Exceptions.InvalidKeyException;
import Exceptions.InvalidOperationException;
import Exceptions.InvalidPositionException;
import TDAArbol.Arbol;
import TDAArbol.Position;
import TDAArbol.Tree;
import TDAMapeo.Entry;
import TDAMapeo.Map;
import TDAMapeo.MapeoConHashAbierto2022;

/*
 * 	Escribir un metodo que al recibir un arbol, devuelva un mapeo donde las claves son los rotulos del arbol, 
 * y los valores la profundidad + la cantidad de hijos de cada nodo
 */
public class unEjercicioClasicoDeLalo {
	
	public static void main(String args[]) {
		Tree<Character> arbol = new Arbol<Character>(); // Creo un árbol de caracteres
		try {
			arbol.createRoot('A' );
			Position<Character> raiz = arbol.root();
			Position<Character> pB = arbol.addLastChild( raiz, 'B' );
			Position<Character> pC = arbol.addLastChild( raiz, 'C' );
			@SuppressWarnings("unused")
			Position<Character> pD = arbol.addLastChild( raiz, 'D' );
			Position<Character> pH = arbol.addLastChild( pB,'H' );
			arbol.addLastChild( pB, 'F' ); // Ocurre un “voiding”
			arbol.addFirstChild( pH, 'G' );
			arbol.addFirstChild( pH, 'K' );
			arbol.addLastChild( pC, 'E' ); // Completar con descendientes propios de D
			
			Map<Character,Integer> m = construirMapeo(arbol);
			for(Entry<Character,Integer> e : m.entries()) {
				System.out.println(e.getKey() + " " + e.getValue() );
			}
		} catch (InvalidOperationException | EmptyTreeException | InvalidPositionException e) {e.printStackTrace();
		} 
		
	
	}

	public static <E> Map<E,Integer> construirMapeo(Tree<E> t){
		Map<E,Integer> mapeo = new MapeoConHashAbierto2022<E,Integer>();
		if(!t.isEmpty())
			try {
				preOrden(t,mapeo,t.root(),0);
			} catch (EmptyTreeException e) {e.printStackTrace();
			}
		
		return mapeo;
	}
	
	private static <E> void preOrden(Tree<E> t, Map<E,Integer> m,Position<E> n,int prof) {
		int cantHijos = 0;
		try {
			for(Position<E> h : t.children(n)) {
				preOrden(t,m,h,prof+1);
				cantHijos++;
			}
			m.put(n.element(), prof + cantHijos);
			
		} catch (InvalidPositionException | InvalidKeyException e) {e.printStackTrace();}
		

	}
	
}
