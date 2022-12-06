package TDAMapeo;

import java.util.Iterator;

import Exceptions.BoundaryViolationException;
import Exceptions.EmptyListException;
import Exceptions.InvalidKeyException;
import Exceptions.InvalidPositionException;
import TDALista.ListaDobleEnlazada;
import TDALista.Position;
import TDALista.PositionList;

public class MapeoConListaUsandoIterator<K,V> implements Map<K,V>{
	
	protected ListaDobleEnlazada<Entrada<K,V>> lista;
	
	public MapeoConListaUsandoIterator() {
		lista = new ListaDobleEnlazada<Entrada<K,V>>();
	}
	
	
	public int size() {
		return lista.size();
	}

	public boolean isEmpty() {
		return lista.isEmpty();
	}

	public V get(K key) throws InvalidKeyException {
		if (key == null)
			throw new InvalidKeyException("Key Invalida");
		Iterator<Entrada<K,V>> it = lista.iterator();
		
		while(it.hasNext()) {
			Entrada<K,V> actual = it.next();
			if(actual.getKey().equals(key))
				return (actual.getValue());
		}
		return null;
	}
	
	public V put(K key, V value) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("Clave Invalida");
		Iterator<Entrada<K,V>> it = lista.iterator();
		V Retorno = null;
		while(it.hasNext()) {
			Entrada<K,V> actual = it.next();
			if(actual.getKey().equals(key)) {
				Retorno = actual.getValue();
				actual.setValue(value);
				return Retorno;
			}
		}
		lista.addLast(new Entrada<K,V>(key,value));
		return Retorno;
	}

	public V remove(K key) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("Clave invalida");
		try {
			if(!lista.isEmpty()) {
				Position<Entrada<K,V>> pos = lista.first();//Me paro en el primer elemento
				while(pos!=null) {
					if(pos.element().getKey().equals(key)) {
						return lista.remove(pos).getValue();//Remuevo y devuelvo 
					}
					if(pos == lista.last())
						pos=null;
					else
						pos=lista.next(pos);
				}
			}
		}catch(InvalidPositionException | EmptyListException | BoundaryViolationException e ) {}
		return null;
	}

	//Devuelve lista de claves
	public Iterable<K> keys() {
		PositionList<K> toReturn = new ListaDobleEnlazada<K>();
		Iterator<Entrada<K,V>> it = lista.iterator();
		
		while(it.hasNext()) {
			Entrada<K,V> actual = it.next();
			toReturn.addLast(actual.getKey());
		}
		
	return toReturn;
	}
	//Devuelve lista de valores
	public Iterable<V> values() {
		
		PositionList<V> Retorno = new ListaDobleEnlazada<V>();//Creo la lista a devolver
		
		Iterator<Entrada<K,V>> it = lista.iterator();//Recorro la lista
		
		while(it.hasNext()) { //Si sigue teniendo siguiente
			Entrada<K,V> actual = it.next(); //Actualizo el valor 
			Retorno.addLast(actual.getValue());//Lo añado a la lista
		}
		
		return Retorno;
	}
	//Devuelve lista de entradas	
	public Iterable<Entry<K,V>> entries() {
		
		PositionList<Entry<K,V>> Retorno = new ListaDobleEnlazada<Entry<K,V>>();
		Iterator<Entrada<K,V>> it = lista.iterator();
		
		while(it.hasNext()) {
			Entrada<K,V> actual = it.next();
			Retorno.addLast(actual);
		}
		
		return Retorno;
	}

}
