package TDAMapeo;
import Exceptions.InvalidKeyException;
import TDALista.*;

public class MapeoConHashCerrado<K,V> implements Map<K,V>{
	private Entrada<K,V> [] arreglo;
    private int cantEntradas;
    private static final float factorCarga = 0.5f;
    private final Entrada<K,V> disponible = new Entrada<K,V>(null, null);
    
    @SuppressWarnings("unchecked")
	public MapeoConHashCerrado(){
    	arreglo = (Entrada<K,V> []) new Entrada[11];
    	for(int i = 0; i<arreglo.length; i++){
    		arreglo[i] = null;
    	}
    	cantEntradas = 0;
    }
    /**
	 * Computa la funcion de hash para la clave pasada por parametro.
	 * @param clave Clave a la cual se le quiere computar la funcion de hash.
	 * @return Bucket al cual pertenece la clave.
	 */
    private int hash(K clave){
		return Math.abs(clave.hashCode()) % arreglo.length;
	}
    /*
     * Retorna la cantidad de entradas.
     */
    public int size(){
    	return cantEntradas;
    }
    /*
     * Testea si M tiene elementos.
     */
    public boolean isEmpty(){
    	return cantEntradas == 0;
    }

	/*
	 * Si M contiene una entrada e con clave igual a k, retorna el valor de v.
	 * Retorna null en caso contrario
	 */
    public V get(K key)throws InvalidKeyException{
    	V value = null;
    	int bucket;
    	if(key == null){
			throw new InvalidKeyException("Clave invalida");
		}else{
			bucket = hash(key);
			while(arreglo[bucket] != null && value == null){
				if(arreglo[bucket] != disponible && arreglo[bucket].getKey().equals(key)){
					value = arreglo[bucket].getValue();
				}
				bucket = (bucket + 1) % arreglo.length;
			}
		}
    	return value;
    }
    /*
	 * Si M no tiene una entrada k, agrega una entrada (k,v) y retorna null
	 * Si M tiene una entrada k, reemplaza el valor con v en e y retorna el valor viejo
	 */
    public V put(K key, V value) throws InvalidKeyException{
    	if(key == null){
    		throw new InvalidKeyException("Clave invalida");
    	}
    	if((cantEntradas/arreglo.length) >= factorCarga){
    		reHash();
    	}
    	int bucket = hash(key);
    	Entrada<K,V> entrada = new Entrada<K,V>(key,value);
    	V valor = insertarSiEsta(entrada,bucket);
    	if(valor == null){
    		insertarNoEsta(entrada,bucket);
    		cantEntradas++;
    	}
    	return valor;
    }
    
    private V insertarSiEsta(Entrada<K,V> entrada, int bucket){
    	V valor = null;
    	int cant = 0;
    	while(arreglo[bucket] != null && valor == null && cant < arreglo.length){
    		if(arreglo[bucket] != disponible && arreglo[bucket].getKey().equals(entrada.getKey())){
    			valor = arreglo[bucket].getValue();
    			arreglo[bucket].setValue(entrada.getValue());
    		}
    		bucket = (bucket + 1) % arreglo.length;
    		cant++;
    	}
    	return valor;
	}
    
    private void insertarNoEsta(Entrada<K,V> entrada, int bucket){
    	while(arreglo[bucket] != null && arreglo[bucket] != disponible){
    		bucket = (bucket+1) % arreglo.length;
    	}
    	arreglo[bucket] = entrada;
    }
    /*
	 * Remueve de M la entrada con clave k y retorna su valor.
	 * Si M no tiene entrada con clave k, retorna null.	
	 */
    public V remove(K key) throws InvalidKeyException{
		int bucket;
		Entry<K,V> entrada = null;
		V value = null;
		if(key == null){
			throw new InvalidKeyException("Clave Invalida");
		}else{
			bucket = hash(key);
			//Busca una entrada igual a la pasada por parametro y la elimina, Si lee un disponible frena.
			while(arreglo[bucket] != null && entrada == null){
				if(arreglo[bucket] != disponible &&arreglo[bucket].getKey().equals(key)){
					entrada = arreglo[bucket];
					arreglo[bucket] = disponible;
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
    /*
	 * Crea una tabla mas grande de tama�o 2*N y busca el siguiente primo. 
	 * Luego inserta todas las entradas de la tabla vieja en la nueva.
	 * Orden de 0(n) ya que require recorrer toda la tabla vieja.
	 */
	@SuppressWarnings("unchecked")
	private void reHash(){
		Entrada<K,V> entrada;
		int bucket;
		int tamanioNuevo=(arreglo.length*2);
		while (!esPrimo(tamanioNuevo)){
			tamanioNuevo++;
		}
		Entrada<K,V>[] arregloViejo = arreglo;
		arreglo = new Entrada[tamanioNuevo];
		for(int i = 0; i < arregloViejo.length; i++){
			entrada = arregloViejo[i];
			bucket = hash(entrada.getKey());
			insertarNoEsta(entrada,bucket);
		}
	}
	/*
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
	/*
	 * Retorna una coleccion iterable de las claves en M.
	 */
    public Iterable<K> keys(){
    	PositionList<K> lk = new ListaDobleEnlazada<K>();
    	for(int i = 0; i < arreglo.length; i++){
    		if((arreglo[i] != null)&&(arreglo[i] != disponible)){
    			lk.addLast(arreglo[i].getKey());
    		}
    	}
    	return lk;
    }
    /*
	 * Retorna una coleccion iterable con los valores de las claves almacenadas en M.
	 */
    public Iterable<V> values(){
    	PositionList<V> lv = new ListaDobleEnlazada<V>();
    	for(int i = 0; i < arreglo.length; i++){
    		if((arreglo[i] != null)&&(arreglo[i] != disponible)){
    			lv.addLast(arreglo[i].getValue());
    		}
    	}
    	return lv;
    }
    /*
	 * Retorna una coleccion iterable con las entradas de M.
	 */
    public Iterable<Entry<K,V>> entries(){
    	PositionList<Entry<K,V>> le = new ListaDobleEnlazada<Entry<K,V>>();
    	for(int i = 0; i < arreglo.length; i++){
    		if((arreglo[i] != null)&&(arreglo[i] != disponible)){
    			le.addLast(arreglo[i]);
    		}
    	}
    	return le;
    }
    
}
