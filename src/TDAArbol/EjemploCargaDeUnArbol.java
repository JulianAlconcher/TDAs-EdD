package TDAArbol;

import Exceptions.EmptyTreeException;
import Exceptions.InvalidOperationException;
import Exceptions.InvalidPositionException;

public class EjemploCargaDeUnArbol {

	public static void main(String[] args) throws InvalidPositionException, InvalidOperationException, EmptyTreeException {
		
		Tree<Character> arbol = new Arbol<Character>(); // Creo un árbol de caracteres
		arbol.createRoot('A' ); // Agrego la raíz
		Position<Character> raiz = arbol.root();
		
		// Agrego hijos de A:
		Position<Character> pB = arbol.addLastChild( raiz, 'B' );
		Position<Character> pC = arbol.addLastChild( raiz, 'C' );
		@SuppressWarnings("unused")
		Position<Character> pD = arbol.addLastChild( raiz, 'D' );
		
		// Agrego hijos de B:
		Position<Character> pH = arbol.addLastChild( pB,'H' );
		arbol.addLastChild( pB, 'F' ); // Ocurre un “voiding”
		
		// Agrego los hijos de H:
		arbol.addFirstChild( pH, 'G' );
		arbol.addFirstChild( pH, 'K' );
		
		// Agrego hijo de C:
		arbol.addLastChild( pC, 'E' ); // Completar con descendientes propios de D
	}

}
