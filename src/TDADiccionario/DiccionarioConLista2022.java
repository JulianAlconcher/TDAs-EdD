package TDADiccionario;

import Exceptions.InvalidEntryException;
import Exceptions.InvalidKeyException;
import Exceptions.InvalidPositionException;
import TDALista.ListaDoblementeEnlazada2022;
import TDALista.Position;
import TDALista.PositionList;

/**
 * Diccionario Implementado Con lista.
 * @author julian Alconcher
 *
 * @param <K> key	
 * @param <V> value
 */
public class DiccionarioConLista2022<K,V> implements Dictionary<K,V>{
	protected PositionList<Entry<K,V>> D;
	
	public DiccionarioConLista2022() {
		D = new ListaDoblementeEnlazada2022<Entry<K,V>>();
	}
	
	@Override
	public int size() {
		return D.size();
	}

	@Override
	public boolean isEmpty() {
		return D.isEmpty();
	}

	@Override
	public Entry<K, V> find(K key) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("La clave es invalida");
		for(Position<Entry<K,V>> pos : D.positions()) {
			if(pos.element().getKey().equals(key))
				return pos.element();
		}
		return null;
	}

	@Override
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("La clave es invalida");
		PositionList<Entry<K,V>> retorno = new ListaDoblementeEnlazada2022<Entry<K,V>>();
		
		for(Position<Entry<K,V>> pos : D.positions())
			if(pos.element().getKey().equals(key))
				retorno.addLast(pos.element());
		
		return retorno;
				
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("La clave es invalida");
		Entry<K,V> nuevo = new Entrada<K,V>(key,value);
		if(D.isEmpty())
			D.addFirst(nuevo);
		else
			D.addLast(nuevo);
		return nuevo;
	}

	@Override
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		if(e==null)
			throw new InvalidEntryException("La entrada es invalida");
		
		boolean encontre=false;
		
		for(Position<Entry<K,V>> pos : D.positions())
			if(pos.element().equals(e)){
				try {
					D.remove(pos);
					encontre = true;
				}
				catch(InvalidPositionException ex) {ex.getStackTrace();}
				return e;
			}
		
		if(encontre==false)
			throw new InvalidEntryException("Remove:: la entrada es invalida");
		
		return null;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> retorno = new ListaDoblementeEnlazada2022<Entry<K,V>>();
		if(!isEmpty())
			for(Position<Entry<K,V>> pos : D.positions())
				retorno.addLast(pos.element());
		
		return retorno;
	}

}
