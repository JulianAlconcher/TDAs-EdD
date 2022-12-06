package EjerciciosVarios;

import Exceptions.EmptyQueueException;
import Exceptions.EmptyStackException;
import TDACola.Queue;
import TDAPila.PilaConEnlaces;
import TDAPila.Stack;

public class ColaConPilas<E> implements Queue<E> {
	
	   Stack<E> st1 = new PilaConEnlaces<E>();
	   Stack<E> st2 = new PilaConEnlaces<E>();
	  
	   public void enqueue(E x) {
		   // remueve todos los elementos de st1 y los pone en st2
	        while (!st1.isEmpty()) {
	            try {
					st2.push(st1.pop());
				} catch (EmptyStackException e) {
					e.printStackTrace();
				}
	        }
	        // push x a st2
	        st2.push(x);
	        // remueve todos los elementos de st2 y los poene denuevo en st1.
	        while (!st2.isEmpty()) {
	            try {
					st1.push(st2.pop());
				} catch (EmptyStackException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }
	    public E dequeue() throws EmptyQueueException  {
	        if (st1.isEmpty()) 
	        	throw new EmptyQueueException("Cola llena");
	        // si st1 no es vacia, retorno y remuevo el tope de st1
	        try {
				return st1.pop();
			} catch (EmptyStackException e) {
				e.printStackTrace();
				return null;
			}
	    }

		public int size() {

			return st1.size();
		}

		public boolean isEmpty() {
			return st1.isEmpty();
		}

		public E front() throws EmptyQueueException {
			if(st1.isEmpty())
				throw new EmptyQueueException("Cola vacia");
			try {
				return st1.pop();
			} catch (EmptyStackException e) {
				System.out.println("La pila es vacia");
				return null;
			}
		}
}

