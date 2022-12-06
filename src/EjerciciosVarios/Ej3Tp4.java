package EjerciciosVarios;


import Exceptions.InvalidKeyException;
import TDALista.ListaDobleEnlazada;
import TDALista.PositionList;
import TDAMapeo.Entry;
import TDAMapeo.Map;

public class Ej3Tp4<K,V> {

	public PositionList<Entry<K,V>> ejercicio3(Map<K,V> M1, Map<K,V> M2){
		
		PositionList<Entry<K,V>> listaNueva = new ListaDobleEnlazada<Entry<K,V>>();
		V E1, E2;
		Iterable<Entry<K,V>> entradas;
		
		try {
			
			if(M1.size() <= M2.size())
				entradas = M1.entries();
			else
				entradas = M2.entries();
			
			for(Entry<K, V> clave: entradas) {
				E1 = M1.get(clave.getKey());
				E2 = M2.get(clave.getKey());
				if(E1.equals(E2))
					listaNueva.addLast(clave);
			}
			
			return listaNueva;
			
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		
		return listaNueva;
	}
}
