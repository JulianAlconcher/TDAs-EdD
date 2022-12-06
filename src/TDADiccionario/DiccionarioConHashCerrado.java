package TDADiccionario;
import Exceptions.InvalidEntryException;
import Exceptions.InvalidKeyException;
import TDALista.*;

public class DiccionarioConHashCerrado<K,V> implements Dictionary<K,V>{
	/**
	 * Arreglo de entradas que reprecenta la tabla de hash. 
	 */
	protected Entrada<K,V>[] arreglo;
	/**
	 * Cantidad actual de entradas.
	 */
	protected int cantEntradas;
	/**
	 * Factor de carga de la tabla de hash.
	 */
	protected final float factorCarga = 0.5f;
	/**
	 * Entrada que reprecenta cuando un bucket fue ocupado por una entrada removida.
	 */
	protected final Entrada<K,V> Disponible = new Entrada<K,V>(null,null);
	
	/**
	 * Crea una nueva tabla de hash.
	 */
	@SuppressWarnings("unchecked")
	public DiccionarioConHashCerrado(){
		cantEntradas = 0;
		arreglo = new Entrada[11];
		for(int i = 0; i < arreglo.length; i++){
			arreglo[i] = null;
		}
	}
	
	/**
	 * Computa la funcion de hash para la clave pasada por parametro.
	 * @param clave Clave a la cual se le quiere computar la funcion de hash.
	 * @return Bucket al cual pertenece la clave.
	 */
	private int hash(K clave){
		return Math.abs(clave.hashCode()) % arreglo.length;
	}
	

	public int size(){
		return cantEntradas;
	}
	

	public boolean isEmpty(){
		return cantEntradas == 0;
	}
	

	public Entry<K,V> find(K key) throws InvalidKeyException{
		int bucket;
		Entry<K,V> entrada = null;
		if(key == null){
			throw new InvalidKeyException("Clave Invalida");	
		}else {
			//Busca en la tabla la primera entrada con la misma key, Si lee un disponible frena
			bucket = hash(key);
			while(arreglo[bucket] != null && entrada == null){
				if(arreglo[bucket] != Disponible && arreglo[bucket].getKey().equals(key)){
					entrada = arreglo[bucket];
				}
				bucket = (bucket + 1) % arreglo.length;
			}
		}
		return entrada;
	}


	public Entry<K,V> insert(K key, V value) throws InvalidKeyException{
		Entrada<K,V> entrada;
		int bucket;
		if(key == null){
			throw new  InvalidKeyException("Clave invalida");
		}else {
			//Si el factor de carga actual supera al establecido redimenciona la tabla.
			if((cantEntradas/arreglo.length) >= factorCarga){
				reHash();
			}
			bucket = hash(key);
			entrada = new Entrada<K,V>(key,value);
			insertar(entrada,bucket);
			cantEntradas++;
			return entrada;
		}
	}
	
	/**
	 * Inserta segunda la politica de la tabla de hash cerrada en el arreglo que reprecenta la tabla.
	 * @param entrada La nueva entrada que se va a insertar.
	 * @param bucket La posicion donde se va a insertar la entrada
	 */
	private void insertar(Entrada<K,V> entrada, int bucket){
		//Si lee un nulo o disponible incerta, de lo contrario avanza de bucket.
		if(arreglo[bucket] == null || arreglo[bucket] == Disponible){
			arreglo[bucket] = entrada;
		}else{
			bucket = (bucket + 1) % arreglo.length;
			while(arreglo[bucket] != null && arreglo[bucket] != Disponible){
				bucket = (bucket + 1) % arreglo.length;
			}
			arreglo[bucket] = entrada;
		}
	}
	
	/**
	 * Redimenciona el arreglo de la tabla de hash actual.
	 */
	@SuppressWarnings("unchecked")
	private void reHash(){
		Entrada<K,V> entrada;
		int bucket;
		int tamanioNuevo=(arreglo.length*2);
		//Busca el proximo primo a tama�onuevo.
		while (!esPrimo(tamanioNuevo)){
			tamanioNuevo++;
		}
		//Copia el arreglo acual en uno nuevo y al actual lo cambia por uno mas grande.
		Entrada<K,V>[] arregloViejo = arreglo;
		arreglo = new Entrada[tamanioNuevo];
		//Inserta todas las entradas otra vez en el arreglo original.
		for(int i = 0; i < arregloViejo.length; i++){
			entrada = arregloViejo[i];
			if(entrada != null && entrada != Disponible){
				bucket = hash(entrada.getKey());
				insertar(entrada,bucket);
			}
		}
	}
	
	/**
	 * Examina si el numero pasado por parametro es un primo.
	 * @param tama�o Numero el cual se quiere saber si es primo.
	 * @return Devuelve true si el numero es primo y false en el caso contrario
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
	

	public Entry<K,V> remove(Entry<K,V> e) throws InvalidEntryException{
		int bucket;
		Entry<K,V> entrada = null;
		if(e == null){
			throw new InvalidEntryException("Clave Invalida");
		}else{
			bucket = hash(e.getKey());
			//Busca una entrada igual a la pasada por parametro y la elimina, Si lee un disponible frena.
			while(arreglo[bucket] != null && entrada == null){
				if(arreglo[bucket] != Disponible &&arreglo[bucket].getKey().equals(e.getKey()) && arreglo[bucket].getValue().equals(e.getValue())){
					entrada = arreglo[bucket];
					arreglo[bucket] = Disponible;
				}else{
					bucket = (bucket + 1) % arreglo.length;
				}
			}
			if(entrada == null){
				throw new InvalidEntryException("Clave Invalida");
			}else {
				cantEntradas--;
			}
			return entrada;
		}
	}
	

	public Iterable<Entry<K,V>> findAll(K key) throws InvalidKeyException{
		PositionList<Entry<K,V>> l = new ListaDobleEnlazada<Entry<K,V>>();
		if(key == null){
			throw new InvalidKeyException("Clave invalida");
		}else{
				for(int i = 0; i < arreglo.length; i++){
					if(arreglo[i] != null && arreglo[i] != Disponible && arreglo[i].getKey().equals(key)){
						l.addLast(arreglo[i]);
					}
				}
			return l;
		}
	}
	

	public Iterable<Entry<K,V>> entries(){
		PositionList<Entry<K,V>> l = new ListaDobleEnlazada<Entry<K,V>>();
			for(int i = 0; i < arreglo.length; i++){
				if(arreglo[i] != null && arreglo[i] != Disponible){
					l.addLast(arreglo[i]);
				}
			}
		return l;
	}
}

