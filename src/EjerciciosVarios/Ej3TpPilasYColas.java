package EjerciciosVarios;

import Exceptions.EmptyStackException;
import TDAPila.PilaConEnlaces;
import TDAPila.Stack;

/**
 * Utilizando la clase PilaArreglo implementada en el Ejercicio 2, programe un método llamado invertir(P) que
	invierta el contenido de la pila P que se recibe como parámetro. De considerarlo necesario, puede recurrir al
	uso de otras pilas. Calcule el orden del tiempo de ejecución de su solución.
 * @author julian Alconcher
 *
 */
public class Ej3TpPilasYColas {
	public static void main (String args[]) {
		Stack<Character> ejemplo = new PilaConEnlaces<Character>();
		ejemplo.push('G');
		ejemplo.push('F');
		ejemplo.push('E');
		ejemplo.push('D');
		ejemplo.push('C');
		ejemplo.push('B');
		ejemplo.push('A');
		System.out.println("Imprimimos la pila sin invertir");
		ImprimirPila(ejemplo);
		System.out.println("");
		System.out.println("Imprimimos la pila invertida");
		InvertirPila(ejemplo);
		ImprimirPila(ejemplo);
	}
	
	private static <E> void InvertirPila(Stack<E> p){
		Stack<E> aux1 = new PilaConEnlaces<E>();
		Stack<E> aux2 = new PilaConEnlaces<E>();
		while(!p.isEmpty()) {
			try {
				aux1.push(p.pop());
			} catch (EmptyStackException e) {
				e.printStackTrace();
			}
		}
		while(!aux1.isEmpty()) {
			try {
				aux2.push(aux1.pop());
			} catch (EmptyStackException e) {
				e.printStackTrace();
			}
		}
		while(!aux2.isEmpty()) {
			try {
				p.push(aux2.pop());
			} catch (EmptyStackException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static <E> void ImprimirPila(Stack<E> p) {
		Stack<E> copia = p;
		while(copia.size()>0) {
			try {
				System.out.println(copia.pop());
			} catch (EmptyStackException e) {
				e.printStackTrace();
			}
		}
	}
}
