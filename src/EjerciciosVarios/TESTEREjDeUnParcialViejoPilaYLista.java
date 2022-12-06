package EjerciciosVarios;

import Exceptions.EmptyStackException;
import Exceptions.OperacionInvalidaException;
import TDALista.ListaSimplementeEnlazada2022;
import TDALista.PositionList;
import TDAPila.PilaConNodosEnlazados2022;
import TDAPila.Stack;

/**
 * c) En Java, agregue un método a la clase ListaSE<E> definida anteriormente, 
 * que dada una pila verifique si en la lista receptora del mensaje se encuentra el elemento del fondo de la pila. 
 * En caso de encontrarlo deberá insertarlo al final de la lista, caso contrario se deberá lanzar la excepción programada 
 * en el inciso (b). Si utiliza métodos del TDA lista deberá implementarlos, así también todo método auxiliar utilizado. 
 * Puede asumir que cuenta con el TDA pila totalmente implementado.
 */
public class TESTEREjDeUnParcialViejoPilaYLista {
	public static void main (String args[]) {
	Stack<Integer> pila = new PilaConNodosEnlazados2022<Integer>();
	PositionList<Integer> lista = new ListaSimplementeEnlazada2022<Integer>();
	
	pila.push(219);
	pila.push(4);
	pila.push(5);
	pila.push(6);
	pila.push(57);
	
	Stack<Character> pila1 = new PilaConNodosEnlazados2022<Character>();
	pila1.push('A');
	pila1.push('B');
	remover(pila1);
	while(!pila1.isEmpty()) {
		try {
			System.out.println(pila1.pop() + " ");
		} catch (EmptyStackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	try {
		System.out.println("fondodePilaEnLista cumple?: " + ((ListaSimplementeEnlazada2022<Integer>) lista).estaEnFondoDePila(pila));
	} catch (OperacionInvalidaException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	public static <E> void remover(Stack<E> p) {
		Stack<E> aux = p;
		try {
			aux.pop();
		} catch (EmptyStackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
