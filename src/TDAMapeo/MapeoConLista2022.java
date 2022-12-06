package TDAMapeo;

import Exceptions.InvalidKeyException;
import Exceptions.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;
import TDALista.ListaDoblementeEnlazada2022;
/**
 * Mapeo implementado con lista doblemente enlazada 2022
 * @author julian Alconcher
 *	
 * @param <K>  clave
 * @param <V>  valor
 */
public class MapeoConLista2022<K,V>  implements Map<K,V>{
	
	protected PositionList<Entrada<K,V>> S;
	
	public MapeoConLista2022(){
		S = new ListaDoblementeEnlazada2022<Entrada<K,V>>();
	}
	@Override
	public int size() {
		return S.size();
	}

	@Override
	public boolean isEmpty() {
		return S.isEmpty();
	}

	@Override
	public V get(K key) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("Clave ingresada invalida");
		for(Position<Entrada<K,V>> pos : S.positions())
			if(pos.element().getKey().equals(key))
				return pos.element().getValue();
		
		return null;
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("Clave ingresada invalida");
		for(Position<Entrada<K,V>> pos : S.positions())
			if(pos.element().getKey().equals(key)) {
				V aux = pos.element().getValue();
				pos.element().setValue(value);
				return aux;}
		S.addLast(new Entrada<K,V>(key,value));
		return null;
				
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("Clave invalid");
		try {
		for(Position<Entrada<K,V>> pos : S.positions())
			if(pos.element().getKey().equals(key)) {
				V aux = pos.element().getValue();
				S.remove(pos);
				return aux;}
		}catch(InvalidPositionException e) {e.getStackTrace();};
		return null;
	}

	@Override
	public Iterable<K> keys() {
		ListaDoblementeEnlazada2022<K> lk = new ListaDoblementeEnlazada2022<K>();
		if(!isEmpty())
			for(Position<Entrada<K,V>> pos : S.positions())
				lk.addLast(pos.element().getKey());
		return lk;
	}

	@Override
	public Iterable<V> values() {
		ListaDoblementeEnlazada2022<V> lv = new ListaDoblementeEnlazada2022<V>();
		if(!isEmpty())
			for(Position<Entrada<K,V>> pos : S.positions())
				lv.addLast(pos.element().getValue());
		return lv;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		ListaDoblementeEnlazada2022<Entry<K,V>> le = new ListaDoblementeEnlazada2022<Entry<K,V>>();
		if(!isEmpty())
			for(Position<Entrada<K,V>> pos : S.positions())
				le.addLast(pos.element());
		return le;
	}

}
