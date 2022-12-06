package TDAMapeo;

import Exceptions.InvalidKeyException;
import TDALista.ListaDobleEnlazada;
import TDALista.PositionList;

public class MapeoConHashCerrado2022<K,V> implements Map<K,V> {
	private Entrada<K,V> [] arreglo;
	private int cantEntradas;
	private int tamanioArreglo=11;
	private static final float factorCarga = 0.5f;
	private final Entrada<K,V> free = new Entrada<K,V>(null,null);
	
	@SuppressWarnings("unchecked")
	public MapeoConHashCerrado2022() {
		arreglo = (Entrada<K,V> []) new Entrada[tamanioArreglo];
		for(int i=0; i<arreglo.length; i++) {
			arreglo[i] = null;
		}
		cantEntradas = 0;
	}
	@Override
	public int size() {
		return cantEntradas;
	}

	@Override
	public boolean isEmpty() {
		return cantEntradas == 0;
	}
	
	private int hashThisKey(K clave) {
		return Math.abs(clave.hashCode()) % arreglo.length;
	}
	
	@Override
	public V get(K key) throws InvalidKeyException {
		V value = null;
		if (key == null)
			throw new InvalidKeyException("get:: Clave invalida");
		int bucket = hashThisKey(key);
		while(arreglo[bucket] != null && value == null) {
			if(arreglo[bucket] != free && arreglo[bucket].getKey().equals(key))
				value = arreglo[bucket].getValue();
		}
		bucket = (bucket +1) % tamanioArreglo;
		return value;
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		if(key == null){
    		throw new InvalidKeyException("Clave invalida");
    	}
    	if((cantEntradas/arreglo.length) >= factorCarga){
    		reHash();
    	}
    	int bucket = hashThisKey(key);
    	Entrada<K,V> entrada = new Entrada<K,V>(key,value);
    	V valor = insertarSiEsta(entrada,bucket);
    	if(valor == null){
    		insertarNoEsta(entrada,bucket);
    		cantEntradas++;
    	}
    	return valor;
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		int bucket;
		Entry<K,V> entrada = null;
		V value = null;
		if(key == null){
			throw new InvalidKeyException("Clave Invalida");
		}else{
			bucket = hashThisKey(key);
			//Busca una entrada igual a la pasada por parametro y la elimina, Si lee un disponible frena.
			while(arreglo[bucket] != null && entrada == null){
				if(arreglo[bucket] != free &&arreglo[bucket].getKey().equals(key)){
					entrada = arreglo[bucket];
					arreglo[bucket] = free;
				}else{
					bucket = (bucket + 1) % arreglo.length;
				}
			}
			if(entrada != null){
				cantEntradas--;
				value = entrada.getValue();
			}
			return value;
	}
	}

	@Override
	public Iterable<K> keys() {
		PositionList<K> lk = new ListaDobleEnlazada<K>();
    	for(int i = 0; i < arreglo.length; i++){
    		if((arreglo[i] != null)&&(arreglo[i] != free)){
    			lk.addLast(arreglo[i].getKey());
    		}
    	}
    	return lk;
	}

	@Override
	public Iterable<V> values() {
		PositionList<V> lv = new ListaDobleEnlazada<V>();
    	for(int i = 0; i < arreglo.length; i++){
    		if((arreglo[i] != null)&&(arreglo[i] != free)){
    			lv.addLast(arreglo[i].getValue());
    		}
    	}
    	return lv;
	}
	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> le = new ListaDobleEnlazada<Entry<K,V>>();
    	for(int i = 0; i < arreglo.length; i++){
    		if((arreglo[i] != null)&&(arreglo[i] != free)){
    			le.addLast(arreglo[i]);
    		}
    	}
    	return le;
	}
	
	 	/**
		 * Crea una tabla mas grande de tama�o 2*N y busca el siguiente primo. 
		 * Luego inserta todas las entradas de la tabla vieja en la nueva.
		 * Orden de 0(n) ya que require recorrer toda la tabla vieja.
		 */
		@SuppressWarnings("unchecked")
		private void reHash(){
			Entrada<K,V> entrada;
			int bucket;
			while (!esPrimo(tamanioArreglo)){
				tamanioArreglo++;
			}
			Entrada<K,V>[] arregloViejo = arreglo;
			arreglo = new Entrada[tamanioArreglo];
			for(int i = 0; i < arregloViejo.length; i++){
				entrada = arregloViejo[i];
				bucket = hashThisKey(entrada.getKey());
				insertarNoEsta(entrada,bucket);
			}
		}
		

	    private void insertarNoEsta(Entrada<K,V> entrada, int bucket){
	    	while(arreglo[bucket] != null && arreglo[bucket] != free){
	    		bucket = (bucket+1) % arreglo.length;
	    	}
	    	arreglo[bucket] = entrada;
	    }
		
		 private V insertarSiEsta(Entrada<K,V> entrada, int bucket){
		    	V valor = null;
		    	int cant = 0;
		    	while(arreglo[bucket] != null && valor == null && cant < arreglo.length){
		    		if(arreglo[bucket] != free && arreglo[bucket].getKey().equals(entrada.getKey())){
		    			valor = arreglo[bucket].getValue();
		    			arreglo[bucket].setValue(entrada.getValue());
		    		}
		    		bucket = (bucket + 1) % arreglo.length;
		    		cant++;
		    	}
		    	return valor;
			}
		 
		/**
		 * Devuelve True si un numero es primo.
		 * False en caso contrario.
		 */
		public boolean esPrimo(int tamanio){
			int contador = 2;
			  boolean primo=true;
			  while ((primo) && (contador!=(tamanio))){
			    if (tamanio % contador == 0)
			      primo = false;
			    contador++;
			  }
			  return primo;
		}

}
