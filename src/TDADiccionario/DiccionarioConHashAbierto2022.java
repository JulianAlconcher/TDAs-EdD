package TDADiccionario;

import Exceptions.InvalidEntryException;
import Exceptions.InvalidKeyException;
import Exceptions.InvalidPositionException;
import TDALista.Position;
import TDALista.ListaDoblementeEnlazada2022;
import TDALista.PositionList;

/**
 * Diccionario implementado con hash abierto usando un arreglo de position list
 * @author Julian Alconcher
 *
 * @param <K>
 * @param <V>
 */
public class DiccionarioConHashAbierto2022<K,V> implements Dictionary<K,V> {
	protected PositionList<Entry<K,V>> [] arreglo;
	protected int cantEntradas;
	protected int cubetas;
	protected static final float factorCarga = 0.9f;
	
	@SuppressWarnings("unchecked")
	public DiccionarioConHashAbierto2022() {
		cubetas = 13;
		cantEntradas = 0;
		arreglo = (PositionList<Entry<K,V>> []) new PositionList[cubetas];
		for(int i=0; i<arreglo.length; i++)
			arreglo[i] = new ListaDoblementeEnlazada2022<Entry<K,V>>();
	}

	/**
	 * Metodo privado que invoca a checkKey y devuelve la cubeta correspondiente para la clave pasada por parametro
	 * @param clave
	 * @return
	 * @throws InvalidKeyException, propagada de checkKey();
	 */
	private int hashThisKey(K clave) {
		return Math.abs(clave.hashCode() % cubetas);
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
	public Entry<K, V> find(K key) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("La clave es invalida, find");
		int claveCubeta = this.hashThisKey(key);
		
		for(Position<Entry<K,V>> pos : arreglo[claveCubeta].positions()) {
			if(pos.element().getKey().equals(key))
					return (pos.element());}
		
		return null;
	}

	@Override
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("La clave es invalida, FindAll");
		//Creo la lista a devolver
		TDALista.PositionList<Entry<K,V>> toReturn = new ListaDoblementeEnlazada2022<Entry<K,V>>();
		//Obtengo la clave de la cubeta
		int claveCubeta = this.hashThisKey(key);
		//Recorro el diccionario, y agrego las claves que sean "key"
		for(Position<Entry<K,V>> pos : arreglo[claveCubeta].positions()) {
			if(pos.element().getKey().equals(key))
				toReturn.addLast(pos.element());
		}
		return toReturn;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("la clave es invalida, insert");
		//Obtengo la clave de la cubeta
		int claveCubeta = this.hashThisKey(key);
		//Creo la entrada a insertar y a devolver
		@SuppressWarnings("rawtypes")
		Entry<K,V> toReturn = new Entrada(key,value);
		//Inserto en la cubeta, a lo ultimo la entrada a insertar
		arreglo[claveCubeta].addLast(toReturn);
		//Aumento el tamaño
		cantEntradas++;
		if(cantEntradas/cubetas > factorCarga)
			reHash();
		//Retorno lo insertado
		return toReturn;
	}

	@Override
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		if(e==null)
			throw new InvalidEntryException("La entrada es invalida, remove");
		
		int claveCubeta = this.hashThisKey(e.getKey());
		
		try {
			for(Position<Entry<K,V>> pos : arreglo[claveCubeta].positions()) {
				if(pos.element() == e) {
					Entry<K,V> toReturn = pos.element();
					arreglo[claveCubeta].remove(pos);
					cantEntradas--;
					return toReturn;
				}
			}
		}catch(InvalidPositionException x) {throw new InvalidEntryException("error");}
		
		throw new InvalidEntryException("no existe esa entrada, remove");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Entry<K, V>> entries() {
		@SuppressWarnings("rawtypes")
		PositionList<Entry<K,V>> le = new ListaDoblementeEnlazada2022();
		for(int i=0; i<arreglo.length; i++) {
			for(Position<Entry<K,V>> pos : arreglo[i].positions()) {
				le.addLast(pos.element());
			}
		}
		return le;
	}

	public Iterable<V> eliminarTodas(K key){ 
		PositionList<V> retorno = new ListaDoblementeEnlazada2022<V>();
			for(Position<Entry<K,V>> en : arreglo[hashThisKey(key)].positions()) {
				if(en.element().getKey().equals(key))
					try {
						retorno.addLast(arreglo[hashThisKey(key)].remove(en).getValue());
					} catch (InvalidPositionException e) {
						e.printStackTrace();
					}
			}
		
		return retorno;
	}

	/**
	 * Metodo privado que REHASHEA
	 * @param num
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	private void reHash() {
		Iterable<Entry<K,V>> entradas = entries();
		cubetas = cubetas*2;
		while(!esPrimo(cubetas)) {
			cubetas++;
		}
		cantEntradas = 0;
		arreglo = new PositionList[cubetas];
		
		for(int i=0; i<cubetas; i++) {
			arreglo[i] = new ListaDoblementeEnlazada2022<Entry<K,V>>();
		}
		try 
		{
		for(Entry<K,V> e : entradas) {
			this.insert(e.getKey(),e.getValue());
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
