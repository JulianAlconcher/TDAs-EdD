package Laboratorios.TDAConjunto;

public class LongdeCadenaMasLarga 
{
	
	public static void main (String args[]) throws ConjuntoVacioException {
	
    Conjunto<Character> c1 = new ConjuntoArreglo<Character>(1);
	
    c1.put('a');
   
   
	
	if(c1.isEmpty())
		throw new ConjuntoVacioException("El conjunto es vacio");
	for(int i=0; i<c1.size(); i++) {
	
		System.out.print(c1.get(i) + " ");
	
	}
	
	
	}
	
	


}
