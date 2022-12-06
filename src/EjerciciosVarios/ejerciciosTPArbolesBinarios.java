package EjerciciosVarios;

import Exceptions.BoundaryViolationException;
import Exceptions.EmptyTreeException;
import Exceptions.InvalidOperationException;
import Exceptions.InvalidPositionException;
import TDAArbolBinario.ArbolBinario2022;
import TDAArbolBinario.BinaryTree;
import TDAArbolBinario.Position;

public class ejerciciosTPArbolesBinarios {

	public static void main(String[] args) {
		ArbolBinario2022<Character> A1 = new ArbolBinario2022<Character>();
		try {
			A1.createRoot('a');
			A1.addLeft(A1.root(), 'b');
			A1.addRight(A1.root(), 'c');
			A1.addLeft(A1.right(A1.root()), 'd');
			A1.addRight(A1.right(A1.root()), 'e');
			A1.addLeft(A1.left(A1.root()), 's');
			A1.addRight(A1.left(A1.root()), 'z');
		} catch (InvalidOperationException e) {e.printStackTrace();
		} catch (InvalidPositionException e) {e.printStackTrace();
		} catch (EmptyTreeException e) {e.printStackTrace();
		} catch (BoundaryViolationException e) {e.printStackTrace();
		}
		
		System.out.println("ARBOL ANTERIOR");
		System.out.println(printTree(A1));
		
		System.out.println("ARBOL ESPEJO");
		System.out.println(printTree(espejo(A1)));
		
		
	}
	
	/**
	 * Escriba un método que dado un AB devuelva un "espejo" del mismo. Realice las mismas consideraciones que en el inciso (c).
	 * @param <E>
	 */
	public static <E> BinaryTree<E> espejo(BinaryTree<E> t){

		BinaryTree<E> nuevo = new ArbolBinario2022<E>();
		if (!t.isEmpty()){
			try {
				nuevo.createRoot(t.root().element());
				clonarRec(t, nuevo, t.root(), nuevo.root());
			} catch (InvalidOperationException | EmptyTreeException e) {e.printStackTrace();
			}
			
		}
		return nuevo;
	}

	private static <E> void clonarRec(BinaryTree<E> t, BinaryTree<E> nuevo, Position<E> posT, Position<E> posN){

		try {
			if (t.hasLeft(posT)){
				Position<E> p = nuevo.addRight(posN, t.left(posT).element());
				clonarRec(t, nuevo, t.left(posT), p);
			}
			if (t.hasRight(posT)){
				Position<E> p = nuevo.addLeft(posN, t.right(posT).element());
				clonarRec(t, nuevo, t.right(posT), p);
			}
		} catch (InvalidPositionException | InvalidOperationException | BoundaryViolationException e) {e.printStackTrace();
		}
	}
	
	
	
	//----------------METODOS PARA IMPRIMIR EL ABOL---------------
	public static <E> String printTree(BinaryTree<E> arBi) {
		String toReturn = "";
		for(Position<E> pos : arBi.positions() ) {
			for(int i=1; i<=getOrder1(pos,arBi); i++) 
				toReturn = toReturn + " | ";
			toReturn = toReturn + (pos.element());
			toReturn = toReturn + ("\n" );
		}
		return toReturn;
	}
	
	private static <E> int getOrder1(Position<E> p,BinaryTree<E> arBi) {
		int contador = 0;
		try {
		while(arBi.parent(p) != null) {
			contador++;
			p = arBi.parent(p);
		}
		} catch (InvalidPositionException | BoundaryViolationException e) {e.getMessage();}
		
		return contador;
	}
}
