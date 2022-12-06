package EjerciciosVarios;

import Exceptions.EmptyStackException;

import TDAPila.PilaConEnlaces;
import TDAPila.Stack;

@SuppressWarnings("hiding")
public class Ej9TpPilasyColas<Integer> {
	public Stack<Integer> PilaUnica (Stack<Stack<Integer>> ppe){
		Stack<Integer> resultado = new PilaConEnlaces<Integer>();
		try {
			while(!ppe.isEmpty()) {
				Stack<Integer> aux = ppe.pop();
				while(!aux.isEmpty()) {
					resultado.push(aux.pop());
				}
			}
			return invertir(resultado);
		}
		catch(EmptyStackException e) {e.getStackTrace();
		return null;}
	}
	
	private Stack<Integer> invertir (Stack<Integer> p)
	{
		Stack<Integer> p1 = new PilaConEnlaces<Integer>();
		Stack<Integer> p2 = new PilaConEnlaces<Integer>();
		try {
		while(!p.isEmpty()) 
		{ p1.push(p.pop()); }
		
		while(!p1.isEmpty()) 
		{ p2.push(p1.pop()); }

		while(!p2.isEmpty()) 
		{ p.push(p2.pop()); }
		
		}catch( Exceptions.EmptyStackException e) {e.getStackTrace();}
		
		return p;
	} 
}
