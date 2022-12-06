package EjerciciosVarios;

import Exceptions.InvalidKeyException;
import TDAMapeo.Map;

import TDALista.ListaSimpleEnlazada;
import TDALista.PositionList;
import TDAMapeo.Entrada;
import TDAMapeo.Entry;
import TDAMapeo.MapeoConLista;

public class Ej2Tp5 {

	public static void main(String[] args) 
	{
		Map<Integer,Integer> m1 = new MapeoConLista<Integer,Integer>();
		Map<Integer,Integer> m2 = new MapeoConLista<Integer,Integer>();
		PositionList<Entry<Integer,Integer>> l = new ListaSimpleEnlazada<Entry<Integer,Integer>>();
		try {
			m1.put(29303,8);
			m1.put(2900,8);
			m1.put(3921,9);
			m1.put(4000,6);
			
			m2.put(29303,7);
			m2.put(2900,8);
			m2.put(4000,10);
			m2.put(3921, 4);
			
			l = m1ym2(m1,m2);
			for(Entry<Integer,Integer> e : l)
				System.out.println(e.toString());
		}catch(InvalidKeyException e) {
			e.printStackTrace();
		}

	}
	
	private static PositionList<Entry<Integer,Integer>> m1ym2 (Map<Integer,Integer> m1,Map<Integer,Integer> m2){
		PositionList<Entry<Integer,Integer>> aux = new ListaSimpleEnlazada<Entry<Integer,Integer>>();
		Integer v2;
		for (Entry<Integer,Integer> p : m1.entries()) {
			try {
				v2 = m2.get(p.getKey());
				if(v2!=null && v2 !=p.getValue()) {
					aux.addFirst(p);
					aux.addFirst(new Entrada<Integer,Integer>(p.getKey(),v2));
				}
			}catch(InvalidKeyException e) {
				e.printStackTrace();
			}
		}
		return aux;
	}

}
