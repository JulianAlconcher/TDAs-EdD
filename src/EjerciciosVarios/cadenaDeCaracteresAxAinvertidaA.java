package EjerciciosVarios;

import Exceptions.EmptyQueueException;
import Exceptions.EmptyStackException;
import TDACola.ColaConArregloCircular;
import TDACola.Queue;
import TDAPila.PilaConEnlaces;
import TDAPila.Stack;

public class cadenaDeCaracteresAxAinvertidaA {
	public static void main(String[] args) {
		String palabrita = "abXbaab";
		
		boolean qpaso = cumpla(palabrita);
		
		System.out.println("tendria que decir true: " + qpaso);
	}
	public static boolean cumpla(String palabra) {
	    boolean cumple = true;
		Queue<Character> Q = new ColaConArregloCircular<Character>();
		Queue<Character> cola = new ColaConArregloCircular<Character>();
		Stack<Character> pila = new PilaConEnlaces<Character>();
		try {
		for(int i=0; i<palabra.length(); i++) {
			Q.enqueue(palabra.charAt(i));
		}
		
		while(Q.front() != 'X' ) {
			if(Q.front() == 'a' || Q.front() == 'b') {
				cola.enqueue(Q.front());
				pila.push(Q.dequeue());
			}
			else
				cumple = false;
		}
		Q.dequeue();
		if(Q.isEmpty())
			cumple = false;
		
		while(!Q.isEmpty() && !pila.isEmpty() && cumple) {
			if(!pila.pop().equals(Q.dequeue()))
				cumple = false;
		}
		
		while(!Q.isEmpty() &&!cola.isEmpty() && cumple) {
			if(!cola.dequeue().equals(Q.dequeue()))
				cumple = false;
		}
		
		if(!Q.isEmpty())
			cumple = false;
		
		}catch(EmptyQueueException | EmptyStackException e) {e.getMessage();}
		return cumple;
	}
	
}
