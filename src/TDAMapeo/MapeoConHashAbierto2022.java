package TDAMapeo;

import Exceptions.InvalidKeyException;
import Exceptions.InvalidPositionException;
import TDALista.ListaDoblementeEnlazada2022;
import TDALista.Position;
import TDALista.PositionList;
/**
 * MAPEO CON HASH ABIERTO IMPLEMENTADO CON LISTA.
 * @author Julian Alconcher
 * @param <K> keys
 * @param <V> values
 */
public class MapeoConHashAbierto2022<K,V> implements Map<K,V> {
	protected PositionList<Entrada<K,V>> [] arreglo;
	protected int cantEntradas;//n
	protected int tamanioArreglo;//N
	protected static final float factor = 0.5f;
	
	@SuppressWarnings("unchecked")
	public MapeoConHashAbierto2022() {
		tamanioArreglo= 13;
		cantEntradas = 0;
		arreglo = (PositionList<Entrada<K,V>> []) new PositionList[tamanioArreglo];
		for(int i=0; i<tamanioArreglo; i++) {
			arreglo[i] = new ListaDoblementeEnlazada2022<Entrada<K,V>>();
		}
	}
	
	
	@Override
	public int size() {
		return cantEntradas;
	}

	@Override
	public boolean isEmpty() {
		return cantEntradas == 0;
	}

	@Override
	public V get(K key) throws InvalidKeyException {
		checkKey(key);
		V toReturn = null;
		for(Position<Entrada<K,V>> pos : arreglo[hashThisKey(key)].positions()) {
			if(pos.element().getKey().equals(key)) {
				toReturn = pos.element().getValue();
			}
		}
		return toReturn;
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		checkKey(key);
		V valorAntiguo = get(key);
		Entrada<K,V> nuevo = new Entrada<K,V>(key,value);
		arreglo[hashThisKey(key)].addLast(nuevo);
		cantEntradas++;
		if(cantEntradas/tamanioArreglo > factor)
			reHash();
		
		return valorAntiguo;
	}

	
	@Override
	public V remove(K key) throws InvalidKeyException {
		checkKey(key);
		V toReturn = null;
		try {
		for(Position<Entrada<K,V>> en : arreglo[hashThisKey(key)].positions()) {
			if(en.element().getKey().equals(key)) {
				toReturn = en.element().getValue();
				arreglo[hashThisKey(key)].remove(en);
				cantEntradas--;
			}				
		}
		}catch(InvalidPositionException e) {e.getStackTrace();}
		
		return toReturn;
	}

	@Override
	public Iterable<K> keys() {
		 	
		PositionList<K> lk = new ListaDoblementeEnlazada2022<K>();
		for(int i=0; i<tamanioArreglo; i++) {
			for(Position<Entrada<K, V>> pos : arreglo[i].positions()) {
				lk.addLast(pos.element().getKey());
		}
		}
		return lk;
	}
	//entry es lo que ve el cliente

	@Override
	public Iterable<V> values() {
		PositionList<V> lv = new ListaDoblementeEnlazada2022<V>();
		for(int i=0; i<tamanioArreglo; i++) {
		for(Position<Entrada<K, V>> pos : arreglo[i].positions()) {
			lv.addLast(pos.element().getValue());
		}
		}
		return lv;
	} 

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> le = new ListaDoblementeEnlazada2022<Entry<K,V>>();
		for(int i=0; i<tamanioArreglo; i++) {
		for(Position<Entrada<K, V>> pos : arreglo[i].positions()) {
			le.addLast(pos.element());
		}
		}
		return le;
	}

	/**
	 * Computa la funcion de hash para la clave pasada por parametro.
	 * @param clave
	 * @return Cubeta al que pertenece la clave.
	 */
	private int hashThisKey(K clave) throws InvalidKeyException {
		checkKey(clave);
		return Math.abs(clave.hashCode()) % tamanioArreglo;
	}
	
	/**
	 * Chequea que la clave no sea nula, y la retorna
	 * @param clave
	 * @return	clave chequeada
	 * @throws InvalidKeyException si la clave es nula.
	 */
	private K checkKey(K clave) throws InvalidKeyException{
		if(clave==null)
			throw new InvalidKeyException("La clave es invalida");
		return clave;
	}
	
	
	/**
	 * Metodo privado que REHASHEA
	 * @param num
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	private void reHash() {
		Iterable<Entry<K,V>> entradas = entries();
		tamanioArreglo = tamanioArreglo*2;
		while(!esPrimo(tamanioArreglo)) {
			tamanioArreglo++;
		}
		cantEntradas = 0;
		arreglo = new PositionList[tamanioArreglo];
		
		for(int i=0; i<tamanioArreglo; i++) {
			arreglo[i] = new ListaDoblementeEnlazada2022<Entrada<K,V>>();
		}
		try 
		{
		for(Entry<K,V> e : entradas) {
			this.put(e.getKey(),e.getValue());
		}
		}catch(InvalidKeyException e) 
			{e.getStackTrace();}
	}
	
	/**
	 * Devuelve True si un numero es primo.
	 * False en caso contrario.
	 */
	private boolean esPrimo(int tamanio)
	{
		 // El 0, 1 y 4 no son primos
		  if (tamanio == 0 || tamanio == 1 || tamanio == 4) {
		    return false;}
		  for (int i = 2; i < tamanio / 2; i++) {
		    if (tamanio % i == 0)
		      return false;
		  }
		  // Si no se pudo dividir por ninguno de los de arriba, sí es primo
		  return true;
	}	
}
