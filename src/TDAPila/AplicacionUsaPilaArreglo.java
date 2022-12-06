package TDAPila;

import Exceptions.EmptyStackException;

public class AplicacionUsaPilaArreglo 
{
	public static void main (String args[]) throws EmptyStackException
	{
		try
		{
		Stack<Integer> s = new PilaArreglo1<Integer>(9);
		s.push(2);
		s.push(4);
		s.push(52);
		s.push(6);
		s.push(55);
		s.push(2);
		s.push(7);
		s.push(8);
		s.push(45);
		
		System.out.println(s.size());
		
		System.out.println("quite el elemento: " + s.pop());
		System.out.println("quite el elemento: " + s.pop());
		
		
		System.out.println("Saco un elemento de la pila: ");
		
		System.out.println("Ahora tiene: " + s.size());
		}
		catch(EmptyStackException e)
		{
			System.out.println("La pila esta vacia");
		}
	}
	
	

}
