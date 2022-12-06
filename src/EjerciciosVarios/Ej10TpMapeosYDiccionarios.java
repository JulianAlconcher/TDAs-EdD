package EjerciciosVarios;

import java.util.Iterator;

import Exceptions.InvalidKeyException;
import TDADiccionario.DiccionarioConLista;
import TDADiccionario.DiccionarioConLista2022;
import TDADiccionario.Dictionary;
import TDADiccionario.Entry;

/*
 * Ejercicio 10 (ejercicio de parcial)
	a) Escriba un método cuya signatura sea: public Dictionary<K,V> acomodar
		(Dictionary<K,V> d) que reciba un diccionario d, y que retorne un nuevo diccionario
		igual a d pero sin claves repetidas. De esta manera, el diccionario resultante de este
		procedimiento no tendrá entradas con claves iguales. Asuma que cuenta con los TDA
		diccionario y mapeo totalmente implementados.
			Por ejemplo:
				Si d = {(1,a), (2,b), (3,a), (2,c), (1,d), (4,b)}, 
				entonces el diccionario resultante es dRes =	{(1,d), (2,c), (3,a) (4,b)}.
	b) Calcule el tiempo de ejecución de su solución. Especifique las estructuras de datos subyacentes.
 */
public class Ej10TpMapeosYDiccionarios<K, V> {
	public static void main (String args[]) {
		Dictionary<Integer,String> d1 = new DiccionarioConLista2022<Integer,String>();
		try {
			d1.insert(1, "Sebastian");
			d1.insert(2, "Julian");
			d1.insert(3, "Juan");
			d1.insert(4, "Pablo");
			d1.insert(5, "Josefina");
			d1.insert(8, "Toto");
			d1.insert(7, "Luz");
			d1.insert(8, "Claudia");
			d1.insert(7, "Mario");
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		
		System.out.println("Imprimo el diccionario original");
		ImprimirDiccionario(d1);
		
		System.out.println("");
				
		System.out.println("Luego, aplico el metodo acomodar");
		System.out.println("Imprimo el diccionario MODIFICADO: ");
		ImprimirDiccionario(acomodar(d1));
		
		
	}
	
	/**
	 * Metodo que retorna un nuevo diccionario sin claves repetidas.
	 * @param <K>
	 * @param <V>
	 * @param d Diccionario a acomodar
	 * @return	Nuevo diccionario sin claves repetidas
	 */
	public static <K,V> Dictionary<K,V> acomodar(Dictionary<K,V> d){
		Dictionary<K,V> aRetornar = new DiccionarioConLista<K,V>(); 		//C1

		for(Entry<K,V> cur : d.entries()) {								
			if((existeEsaClave(cur.getKey(),aRetornar)) == false) {		//T(existeEsaClave) : O(n)
				try {
					aRetornar.insert(cur.getKey(),cur.getValue());	//C3
				} catch (InvalidKeyException e) {
					e.printStackTrace();
				}
			}
			else {System.out.println("Encontre una que esta repetida");}
		}
		return aRetornar;													//C4
	}
	/**
	 * Metodo privado que responde true, si "clave" se encuentra en el diccionario
	 * @param <K>
	 * @param <V>
	 * @param clave
	 * @param d
	 * @return	True si clave esta, False en caso contrario
	 */
	private static <K,V> boolean existeEsaClave(K clave,Dictionary<K,V> d) {
		for(Entry<K,V> cur : d.entries()) {
			if(cur.getKey().equals(clave)) {
				return true;
			}
		}
		return false;
	}
	
	private static <K,V> void ImprimirDiccionario(Dictionary<K,V> d) {
		Iterable<Entry<K,V>> a = d.entries();
		Iterator<Entry<K,V>> it = a.iterator();
		int suma = 0;
		while(it.hasNext()) {
			System.out.println(it.next() + " ");
			suma++;
		}
		System.out.println("La cantidad de elementos del diccionario es: " + suma);
	}
}
