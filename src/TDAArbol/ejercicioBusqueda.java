package TDAArbol;

import Exceptions.EmptyTreeException;
import Exceptions.InvalidOperationException;
import Exceptions.InvalidPositionException;

//Programe un metodo recursivo para buscar la posicion de un elemento x en un arbol T.
public class ejercicioBusqueda {
	
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
			arbol.addLastChild( pB, 'F' ); 
			arbol.addFirstChild( pH, 'G' );
			arbol.addFirstChild( pH, 'K' );
			arbol.addLastChild( pC, 'E' ); 
		} catch (InvalidOperationException | InvalidPositionException | EmptyTreeException e) {e.printStackTrace();
		} 

		System.out.println("El tamanio del arbol es: " + arbol.size());
		System.out.println(buscarEnArbol(arbol,'E'));
	
	}
	
	public static <E> Position<E> buscarEnArbol(Tree<E>a,E x){
		Position<E> pos = null;
		if(!a.isEmpty())
			try {
				buscarRec(a,a.root(),x,pos);
			} catch (EmptyTreeException e) {e.printStackTrace();
			}
		return pos;
	}
	
	public static <E> void buscarRec(Tree<E> a, Position<E> p,E x,Position<E> ret){
		if(p.element()==x) {
			System.out.println("entre al igual");
			System.out.println(p.element());
			ret = p;
		}
		try {
			for(Position<E> hijos : a.children(p)) {
					buscarRec(a,hijos,x,ret);
			}

		} catch (InvalidPositionException e) {e.printStackTrace();
		}
	}
}
