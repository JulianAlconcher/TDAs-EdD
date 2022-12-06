package EjerciciosVarios;

import java.util.Iterator;

import Exceptions.InvalidKeyException;
import TDALista.ListaDoblementeEnlazada2022;
import TDALista.PositionList;
import TDAMapeo.Entry;
import TDAMapeo.Map;
import TDAMapeo.MapeoConLista2022;

/*
 * Se poseen dos correspondencias (mapeos) M1 y M2 cuyas entradas tienen por dominio un
número de libreta, y por imagen la nota de una materia (de 0 a 10). Ambos mapeos están
implementados con una lista simplemente enlazada y sin celda de encabezamiento.

a) Escriba un método que reciba los dos mapeos M1 y M2 y devuelva una lista L con aquellos
elementos E1 de M1 y E2 de M2 que coincidan en el valor del dominio, pero tengan una
imagen diferente. Por ejemplo, si E1= (LU: 29303, Nota: 8) pertenece a M1 y E2=
(LU:29303, Nota: 7) pertenece a M2, entonces E1 y E2 serán puestos en L.

b) Calcule el orden del tiempo de ejecución del procedimiento implementado. Justifique su
respuesta.
 */
public class Ej2TpMapeosYDiccionarios {
	public static void main (String args []) {
		Map<Integer,Integer> M1 = new MapeoConLista2022<Integer,Integer>();
		Map<Integer,Integer> M2 = new MapeoConLista2022<Integer,Integer>();
		try {
			//Agrego entradas a M1
			M1.put(29303, 8);
			M1.put(126094, 8);
			M1.put(99456, 3);
			M1.put(124098, 8);
			
			//Agrego entradas a M2
			M2.put(29303, 7);
			M2.put(23123, 10);
			M2.put(126094, 9);
			M2.put(124098, 8);
		
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Ejecuto el metodo: "  );
		
		//Impresion de la lista
		System.out.println("Muestro la lista con las claves iguales, pero los valores diferentes");
		Iterator<Entry<Integer,Integer>> it2= Coincidencias(M1,M2).iterator();
		System.out.print("[ ");
		while(it2.hasNext()) {
			System.out.print(it2.next()+" ");
		}
		System.out.print("] ");		
		
	}
	
	
	/**
	 * Método que recibe los dos mapeos M1 y M2 y devuelve una lista L con aquellos
		elementos E1 de M1 y E2 de M2 que coincidan en el valor del dominio, pero tengan una
		imagen diferente.
	 * @param m01
	 * @param m02
	 * @return	Lista con las entradas con mismo dominio y diferente imagen
	 */
	private static PositionList<Entry<Integer,Integer>> Coincidencias(Map<Integer,Integer> m01, Map<Integer,Integer> m02){
		PositionList<Entry<Integer,Integer>> retorno = new ListaDoblementeEnlazada2022<Entry<Integer,Integer>>();
		for(TDAMapeo.Entry<Integer, Integer> en : m01.entries()) {
			for(TDAMapeo.Entry<Integer, Integer> en2 : m02.entries()) {
				if(en.getKey().equals(en2.getKey())) {
					if(!en.getValue().equals(en2.getValue())) {
						retorno.addLast(en);
						retorno.addLast(en2);}}
					}
		}
		return retorno;	
	}
}
