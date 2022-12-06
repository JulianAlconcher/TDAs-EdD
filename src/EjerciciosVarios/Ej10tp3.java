package EjerciciosVarios;

import Exceptions.EmptyQueueException;
import Exceptions.EmptyStackException;
import TDACola.ColaConArregloCircular;
import TDACola.Queue;
import TDAPila.PilaConEnlaces;
import TDAPila.Stack;

public class Ej10tp3{
	

public static boolean metodoPrincipal(String e) {
		return metodoaux1(e) && metodoaux2(e);
	}
	
	public static boolean metodoaux1(String entrada) { // Verifica A = A'
		Stack<Character> pila = new PilaConEnlaces<Character>();
		int cant = 1;
		char[] cadena = entrada.toCharArray();
		boolean coinciden = true;
		
		try {
			for(int i = 0; cadena[i] != 'x'; i++) {	// cadena[i] != 'x'
				pila.push(cadena[i]); // Apila A
				cant++;
			}
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("No se encontro la 'x' en la cadena seleccionada.");
		}
		
		
		if(pila.size() != cadena.length - cant)
				coinciden = false;
		
		for(int v = cant; v < cadena.length && coinciden ; v++) {
			try {
				if(pila.pop() != cadena[v])
					coinciden = false;
			} catch (EmptyStackException e) {
				e.printStackTrace();
			}	
		}
		return coinciden;
	}
	
	public static boolean metodoaux2(String entrada) { // Verifica si la porcion hasta z
		Queue<Character> cola1 = new ColaConArregloCircular<Character>();			// aparece 2 veces luego.
		Queue<Character> cola2 = new ColaConArregloCircular<Character>();
		char [] cadena = entrada.toCharArray();
		int cant = 1;
		boolean coinciden = true;
		for(int i = 0; cadena[i] != 'z'; i++) {
			cola1.enqueue(cadena[i]);
			cola2.enqueue(cadena[i]);
			cant++;
		}
		
		int recorrido = cola1.size();
		
		try {		
			for(int i = 0; i < recorrido; i++) {
			if(cola1.dequeue() != cadena[cant])
				coinciden = false;
			cant++;
		}

		for(int i = 0; i < recorrido; i++) {
			if(cola2.dequeue() != cadena[cant])
				coinciden = false;
			cant++;
		}
			
		}catch(EmptyQueueException e) {
			System.out.println("Cola vacia.");
		}
		
		return coinciden;		
	}
}