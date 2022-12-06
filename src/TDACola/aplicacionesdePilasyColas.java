package TDACola;

import Exceptions.EmptyQueueException;
import Exceptions.EmptyStackException;
import TDAPila.PilaConEnlaces;
import TDAPila.Stack;


public class aplicacionesdePilasyColas 
{
	public static void main (String args[]) throws EmptyQueueException, EmptyStackException {
	
	
	Queue<Character> q1 = new ColaEnlazada<Character>();
	q1.enqueue('3');
	q1.enqueue('+');
	q1.enqueue('(');
	q1.enqueue('4');
	q1.enqueue('*');
	q1.enqueue('6');
	q1.enqueue(')');
	System.out.print("Q1 es: " );
	//q1.Imprimir();
	
	Queue<Character> q2 = new ColaEnlazada<Character>();
	q2.enqueue('3');
	q2.enqueue('+');
	q2.enqueue('(');
	q2.enqueue('4');
	q2.enqueue('*');
	q2.enqueue('(');
	q2.enqueue(')');
	
	System.out.println("");
	
	System.out.print("Q2 es: " );
	//q2.Imprimir();
	
	System.out.println("");
	
	System.out.println("Testeemos si la sentencia en q1 esta bien parentizada");
	
	System.out.println("El resultado es: " + CoincidenParentesis(q1)); 
	
	System.out.println("Testeemos si la sentencia en q2 esta bien parentizada");
	
	System.out.println("El resultado es: " + CoincidenParentesis(q2)); 
	
	}
	
	
	public static boolean CoincidenParentesis(Queue<Character> q) throws EmptyQueueException, EmptyStackException {
		Stack<Character> S = new PilaConEnlaces<Character>();
		boolean retorno = false;
		try {
		while(q.isEmpty()!=true)
		{
			Character x = q.dequeue();
			
			
			if(x == '(')
				S.push(x);
			
			else if(x==')') 
			{
				
				if(S.isEmpty())
					retorno = false;
				
				if(S.pop() != ')')
					retorno = false;
			}
		}
		
		if(S.isEmpty())
			retorno = true;
		else
			retorno = false;
		}catch(EmptyQueueException e) 
			{System.out.println("La cola esta vacia");
		}catch(EmptyStackException a)
			{System.out.println("La pila esta vacia");
		}
		
		
		
		
		return retorno;
	}
	

}
