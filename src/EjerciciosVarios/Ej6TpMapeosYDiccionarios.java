package EjerciciosVarios;


import Exceptions.InvalidKeyException;
import Exceptions.MapeoNoInvertibleException;
import Exceptions.MapeoNoInyectivoException;
import TDAMapeo.*;

/*
 * Escriba una función CREAR-MAPEO-INVERSO(D) que reciba un mapeo D, y retorne un
	mapeo inverso M donde estén todas las entradas de D, pero invertidas, esto es, la
	correspondencia es tal que si en D está el par (A,B), entonces, en M deberá estar el par
	(B,A). Proponga excepciones para los casos en los que tal mapeo inverso no pueda ser
	creado.
 */
public class Ej6TpMapeosYDiccionarios {
	public static void main(String args[]) {
		Map<Integer,Integer> m1 = new MapeoConHashAbierto2022<Integer,Integer>();
		try {
			m1.put(3, 3);
			m1.put(15, 15);
			m1.put(11, 1);
			m1.put(3, 13);
			m1.put(44, 44);
			m1.put(8, 88);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		System.out.println("El mapeo antes de invocar al metodo");
		ImprimirMapeoUsandoEntries(m1);
		
		
		
		System.out.println("El mapeo DESPUES de invocar al metodo");
		try {
			ImprimirMapeoUsandoEntries(crearMapeoInverso(m1));
		} catch (MapeoNoInyectivoException | MapeoNoInvertibleException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Metodo que invierte las entradas de un mapeo.
	 * @param <V>
	 * @param <K>
	 * @param d
	 * @return Retorna un mapeo con las entradas invertidas.
	 * @throws MapeoNoInyectivoException 
	 * @throws MapeoNoInvertibleException 
	 */
	private static <V, K> Map<V,K> crearMapeoInverso(Map<K,V> d) throws MapeoNoInyectivoException, MapeoNoInvertibleException{
		Map<V,K> aRetornar = new MapeoConHashAbierto2022<V,K>(); //	C1
		K aux = null;											 // C2
		for(Entry<K,V> en : d.entries()) {						 // n del for y n del entries
			try {
				if(en.getValue() == null) 						 // C5
					throw new MapeoNoInvertibleException("No es posible crear el mapeo :: Clave que antes era valor NULA"); //C6
								
					aux = aRetornar.put(en.getValue(), en.getKey()); // Si el mapeo es con hash, el tiempo es constante C9
					
					if(aux != null)								// C7
						throw new MapeoNoInyectivoException("No es posible crear el mapeo :: Mapeo NO inyectivo ");	// C8
					
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			}
		}
		return aRetornar;										// C10
	}
	
	// C1 + C2 + n (C5 + C6 + C9 + C7 + C8) + C10
	// C1 + C2 + n (C11) + C10
	// O(n)
	
	/**
	 * Metodo privado que imprime por pantalla el mapeo pasado por parametro.
	 * @param d
	 */
	private static <K,V> void ImprimirMapeoUsandoEntries(Map<K,V> d) {
		for(Entry<K,V> en : d.entries()) {
			System.out.println(en + " ");
		}
	}
	
	
}
