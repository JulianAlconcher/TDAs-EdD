package TDAMapeo;

import Exceptions.InvalidKeyException;
import TDALista.ListaDobleEnlazada;
import TDALista.PositionList;
/*
 * Mapeo implementado usando un map
 */
public class MapeoConHashAbierto<K,V> implements Map<K,V> {
	
	// Arreglo A de N componentes, cada celda es una coleccion de entradas (pares de clave-valor).
	protected Map<K,V> [] arreglo; 
	protected int cantEntradas;
	protected int tamanioArreglo = 11;
	protected static final float factorCarga = 0.9f;
	
	@SuppressWarnings("unchecked")
	public MapeoConHashAbierto()
	{
		cantEntradas = 0;
		arreglo =(Map<K,V> []) new MapeoConLista[tamanioArreglo];
		for(int i=0; i<tamanioArreglo; i++)
			arreglo[i] = new MapeoConLista<K,V>();
	}
	/**
	 * Computa la funcion de hash para la clave pasada por parametro.
	 * @param clave clave a la cual se le quiere computar la funcion de hash.
	 * @return Bucket al cual pertenece la clave.
	 */
	private int hash(K clave)
	{
		return Math.abs(clave.hashCode()) % tamanioArreglo;
	}
	/*
	 * Retorna el numero de entradas de M
	 */
	public int size() {
		return cantEntradas;
	}

	/*
	 * Testea si M es vacio
	 */
	public boolean isEmpty() {
		return cantEntradas == 0;
	}

	/*
	 * Si M contiene una entrada e con clave igual a k, retorna el valor de v.
	 * Retorna null en caso contrario
	 */
	public V get(K key) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("get: Clave Invalida");
		return arreglo[hash(key)].get(key);
	}
	/*
	 * Si M no tiene una entrada k, agrega una entrada (k,v) y retorna null
	 * Si M tiene una entrada k, reemplaza el valor con v en e y retorna el valor viejo
	 */
	public V put(K key, V value) throws InvalidKeyException {
		float factorCargaActual;
		V valueReturn = null;
		
		if(key==null)
			throw new InvalidKeyException("Clave Invalida");
		
		factorCargaActual = (float) cantEntradas/tamanioArreglo;
		if(factorCargaActual >= factorCarga)
			reHash();
		valueReturn = arreglo[hash(key)].put(key, value);
		
		if(valueReturn == null)
			cantEntradas++;
		
		return valueReturn;
	}
	/*
	 * Devuelve True si un numero es primo.
	 * False en caso contrario.
	 */
	private boolean esPrimo(int tamano)
	{
		int contador = 2;
		  boolean primo=true;
		  while ((primo) && (contador!=(tamano/2))){
		    if (tamano % contador == 0)
		      primo = false;
		    contador++;
		  }
		  return primo;
	}
	/*
	 * Crea una tabla mas grande de tamaño 2*N y busca el siguiente primo. 
	 * Luego inserta todas las entradas de la tabla vieja en la nueva.
	 * Orden de 0(n) ya que require recorrer toda la tabla vieja.
	 */
	private void reHash()
	{
		int tamanoNuevo=(tamanioArreglo*2);
		while (!esPrimo(tamanoNuevo)){
			tamanoNuevo++;
		}
		@SuppressWarnings("unchecked")
		Map<K,V>[] arregloNuevo =(Map<K,V>[]) new  MapeoConLista[tamanoNuevo];
		for(int i = 0; i<arregloNuevo.length; i++){
			arregloNuevo[i]=new MapeoConLista<K,V>();
		}
		try{
			for (Entry<K,V> entrada: entries()){
				arregloNuevo[Math.abs(entrada.getKey().hashCode())%arregloNuevo.length].put(entrada.getKey(), entrada.getValue());
			}
		}
		catch (InvalidKeyException e) 
			{e.getStackTrace();
		}
		tamanioArreglo = tamanoNuevo;
		arreglo = arregloNuevo;
	}
	/*
	 * Remueve de M la entrada con clave k y retorna su valor.
	 * Si M no tiene entrada con clave k, retorna null.	
	 */
	public V remove(K key) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("Remove:: Clave Invalida");
		V valor = arreglo[hash(key)].remove(key);
		if(valor!=null)
			cantEntradas--;
		return valor;
	}
	/*
	 * Retorna una coleccion iterable de las claves en M.
	 */
	public Iterable<K> keys()
	{
		PositionList<K> lk = new ListaDobleEnlazada<K>();
		for(int i = 0; i < tamanioArreglo; i++)
		{
			for (K llave : arreglo[i].keys())
				lk.addLast(llave);
		}
		return lk;
	}
	/*
	 * Retorna una coleccion iterable con los valores de las claves almacenadas en M.
	 */
	public Iterable<V> values(){
		PositionList<V> lv = new ListaDobleEnlazada<V>();
		for(int i = 0; i < tamanioArreglo; i++){
			for (V valor : arreglo[i].values()){
				lv.addLast(valor);
			}
		}
		return lv;
	}
	/*
	 * Retorna una coleccion iterable con las entradas de M.
	 */
	public Iterable<Entry<K,V>> entries(){
		PositionList<Entry<K,V>> le = new ListaDobleEnlazada<Entry<K,V>>();
		for(int i = 0; i < tamanioArreglo; i++){
			for (Entry<K,V> valor : arreglo[i].entries()){
				le.addLast(valor);
			}
		}
		return le;
	}

}
