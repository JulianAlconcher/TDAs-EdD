package TDAArbolBinariodeBusqueda;

import TDAColaConPrioridad.Entry;

public class EntradaComparable<K extends Comparable<K>,V> implements Entry<K,V>,Comparable<EntradaComparable<K,V>> {

	@Override
	public int compareTo(EntradaComparable<K, V> o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public K getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
