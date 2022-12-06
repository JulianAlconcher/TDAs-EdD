package TDAMapeo;

import Exceptions.InvalidKeyException;
import Exceptions.InvalidPositionException;
import TDALista.ListaDobleEnlazada;
import TDALista.Position;

public class MapeoConLista<K,V> implements Map<K,V> {

	protected ListaDobleEnlazada<Entrada<K,V>> S;
	
	public MapeoConLista()
	{
		S = new ListaDobleEnlazada<Entrada<K,V>>();
	}

	public int size() {
		return S.size();
	}

	
	public boolean isEmpty() {
		return S.isEmpty();
	}

	/** 
	 * Si S contiene una entrada e con igual clave que k, retorna el valor de v
	 * Si no retorna null
	 */
	public V get(K key) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("Clave Invalida");
		else 
		{	
			
		for(Position<Entrada<K,V>> p : S.positions())//Para cada posicion p de la lista S
			{
				if(p.element().getKey().equals(key))//Si la clave de la entrada actual es key
					return p.element().getValue();//retorno el valor de la entrada actual
			}
		return null;//Si no encontre ninguna entrada, devuelvo null
		}
	}

	/**
	 * Si S no tiene una entrada con clave k, entonces agrega una entrada (k,v) a S y retona null
	 */
	public V put(K key, V value) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("Clave invalida");
		else {
			for(Position<Entrada<K,V>> p : S.positions())
			{
				if(p.element().getKey().equals(key))
				{
					V aux = p.element().getValue();
					p.element().setValue(value);
					return aux;
				}
			}
			S.addLast(new Entrada<K,V>(key,value));
			return null;
		}
	}

	/**
	 * Remueve de S la entrada con clave k y retorna su valor
	 * Si S no tiene entrada con clave k, retorna null
	 */
	public V remove(K key) throws InvalidKeyException {
		try {
			if(key==null)
				throw new InvalidKeyException("Clave invalida");
			else {
				for(Position<Entrada<K,V>> p :S.positions())//Para cada posicion de p hacer
				{
					if(p.element().getKey().equals(key))//Si la entrada de la posicion p tiene clave key
					{
						V value = p.element().getValue();//Guardo el valor en value
						S.remove(p);//Elimino la posicion p de la lista
						return value;//Retorno
					}
				}
				return null;//Si no encontre retorno null
				}
			} catch(InvalidPositionException e) {e.getStackTrace();}
		return null;
	}

	/*
	 * Retorna una coleccion iterable de las claves en S
	 */
	public Iterable<K> keys(){
		ListaDobleEnlazada<K> lk = new ListaDobleEnlazada<K>();
		if(!isEmpty()){
			for(Position<Entrada<K,V>> p : S.positions()){
				lk.addLast(p.element().getKey());
			}
		}
		return lk;
	}

	/*
	 * Retorna una coleccion iterable con los valores de las claves almacenadas en S
	 */
	public Iterable<V> values() {
		ListaDobleEnlazada<V> lv = new ListaDobleEnlazada<V>();
		if(!isEmpty()){
			for(Position<Entrada<K,V>> p : S.positions()){
				lv.addLast(p.element().getValue());
			}
		}
		return lv;	
	}

	/*
	 * Retorna una coleccion iterable con las entradas de S
	 */
	public Iterable<Entry<K, V>> entries() {
		ListaDobleEnlazada<Entry<K,V>> le = new ListaDobleEnlazada<Entry<K,V>>();
		if(!isEmpty()){
			for(Position<Entrada<K,V>> p : S.positions()){
				le.addLast(p.element());
			}
		}
		return le;
	}
}
