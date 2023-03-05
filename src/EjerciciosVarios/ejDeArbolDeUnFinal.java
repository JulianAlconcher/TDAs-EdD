package EjerciciosVarios;

import Exceptions.EmptyTreeException;
import Exceptions.InvalidOperationException;
import Exceptions.InvalidPositionException;
import TDAArbol.Arbol;
import TDAArbol.Position;
import TDAArbol.Tree;

/**
 * Escriba un procesidimiento recursivo que reciba un arbol T como parametro
 * y elimine de T a todos los nodos con exactamente un hijo. 
 */
public class ejDeArbolDeUnFinal {

	public static void main (String args[]) {
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
			System.out.println("Size del arbol: " + arbol.size());
			
			for(Position<Character> p : arbol.positions())
				System.out.println(p.element());
			System.out.println("Probamos metodo");
			
			eliminarTodosNodosConUnHijo(arbol,arbol.root());
			System.out.println("Size del arbol: " + arbol.size());
			for(Position<Character> p : arbol.positions())
				System.out.println(p.element());
			
			
		} catch (InvalidOperationException | InvalidPositionException | EmptyTreeException e) {e.printStackTrace();
		} 
	}
	public static <E> void eliminarTodosNodosConUnHijo(Tree<E> a,Position<E> p) {
		try {
			if(tieneUnHijo(a,p)) {
				System.out.println("REMUEVO");
				a.removeNode(p);
			}
			if(p!=null) 
			for(Position<E> h : a.children(p))
				eliminarTodosNodosConUnHijo(a,h);
		} catch (InvalidPositionException e) {e.printStackTrace();
		}
	}
	
	private static <E> boolean tieneUnHijo(Tree<E> a,Position<E> p) {
		int cont = 0;
		try {
			for(Position<E> h : a.children(p) )
				cont++;
		} catch (InvalidPositionException e) {e.printStackTrace();
		}
		return cont==1;
	}
}
