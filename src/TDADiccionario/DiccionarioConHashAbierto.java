package TDADiccionario;
import Exceptions.InvalidEntryException;
import Exceptions.InvalidKeyException;
import Exceptions.InvalidPositionException;
import TDALista.ListaDobleEnlazada;
import TDALista.Position;
import TDALista.PositionList;

public class DiccionarioConHashAbierto<K,V> implements Dictionary<K,V>{
	
	protected PositionList<Entry<K,V>> [] arreglo;
	protected int cantEntradas;
	protected int buckets;
	protected static final float factorCarga = 0.9f;
	
	@SuppressWarnings("unchecked")
	public DiccionarioConHashAbierto(){
		buckets=13;
		cantEntradas = 0;
		arreglo = (PositionList<Entry<K,V>> []) new PositionList[buckets];
		for(int i = 0; i < arreglo.length; i++){
			arreglo[i] = new ListaDobleEnlazada<Entry<K,V>>();
		}
	}
	
	private int hash(K clave){
		return (clave.hashCode() % buckets);
	}
	/*
	 * Retorna el numero de entradas de D
	 */
	public int size(){
		return cantEntradas;
	}
	/*
	 * Testea si D esta vacio
	 */
	public boolean isEmpty(){
		return cantEntradas == 0;
	}
	/*
	 * Si D contiene una entrada e con clave igual a key, entonces retorna e.
	 * Caso contrario retorna null.
	 */
	public Entry<K,V> find(K key) throws InvalidKeyException{
		if(key == null)
			throw new InvalidKeyException("find: Clave invalida");
		
		int claveBucket = this.hash(key);
		
		for (Position<Entry<K,V>> p : arreglo[claveBucket].positions()) {
			if (p.element().getKey().equals(key))
				return (p.element());
		}
		
		return null;
	}
	
	/*
	 * Retorna una coleccion iterable con las entrades clave-valor de d.
	 */
	@SuppressWarnings("rawtypes")
	public Iterable<Entry<K,V>> entries(){
		@SuppressWarnings("unchecked")
		PositionList<Entry<K,V>> toReturn = new ListaDobleEnlazada();
		
		for(int i = 0; i < arreglo.length; i++) {
			for (Position<Entry<K,V>> p : arreglo[i].positions()) {
				toReturn.addLast(p.element());
			}
		}
		
		return toReturn;
	}
	
	/*
	 * Inserta en D una entrada e con clave key y valor value y retorna la entrada e.
	 */
	public Entry<K,V> insert(K key, V value) throws InvalidKeyException{
		if (key == null)
			throw new InvalidKeyException("insert: Clave invalida");
		
		//Obtengo la clave del bucket;
		int claveBucket = this.hash(key);
		
		//Creo la entrada;
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Entry<K,V> toReturn = new Entrada(key,value);
		
		//Entro al bucket y lo agrego;
		arreglo[claveBucket].addLast(toReturn);
		
		//Incremento la entrada;
		++cantEntradas;
		
		return toReturn;
	}
	/*
	 * Remueve de D la entrada e, retornando la entrada removida
	 * Ocurre un error si e no está en D.
	 */
	public Entry<K,V> remove(Entry<K,V> e) throws InvalidEntryException{

		if (e == null)
			throw new InvalidEntryException("remove: Entrada invalida");
		
		int claveBucket = this.hash(e.getKey());
		
		try {
			for(Position<Entry<K,V>> p : arreglo[claveBucket].positions()) {
				if (p.element() == e) {
					Entry<K,V> toReturn = p.element();
					arreglo[claveBucket].remove(p);
					--cantEntradas;
					return toReturn;
				}
			}
		}
		catch(InvalidPositionException x) {
			throw new InvalidEntryException("error");
		}
		
		throw new InvalidEntryException("remove: No existe esa entrada");
	}
	
	/*
	private void reHash (){
		int tamanoNuevo=(buckets*2);
		while (!esPrimo(tamanoNuevo)){
			tamanoNuevo++;
		}
		Dictionary<K,V>[] arregloNuevo =(Dictionary<K,V>[]) new DiccionarioConLista[tamanoNuevo];
		for(int i = 0; i<arregloNuevo.length; i++){
			arregloNuevo[i]=new DiccionarioConLista<K,V>();
		}
		try{
			for (Entry<K,V> entrada: entries()){
				arregloNuevo[Math.abs(entrada.getKey().hashCode())%arregloNuevo.length].insert(entrada.getKey(), entrada.getValue());
			}
		}
		catch (InvalidKeyException e) 
			{e.getStackTrace();
		}
		buckets = tamanoNuevo;
		arreglo = arregloNuevo;
	}
	
	*/
	
	@SuppressWarnings("unused")
	private boolean esPrimo(int tamano){
		int contador = 2;
		  boolean primo=true;
		  while ((primo) && (contador!=(tamano/2))){
		    if (tamano % contador == 0)
		      primo = false;
		    contador++;
		  }
		  return primo;
	}
	
	public Iterable<V> eliminarTodas(K key) throws InvalidKeyException {
		TDALista.PositionList<V> le = new ListaDobleEnlazada<V>();
		try {
			if(key == null){
				throw new InvalidKeyException("CLAVE INVALIDA");
			}
			Iterable<Entry<K,V>> entradas = findAll(key);
			for(Entry<K,V> ent : entradas) {
				le.addLast(remove(ent).getValue());
			}
		} catch(InvalidKeyException | InvalidEntryException e1) {
			e1.printStackTrace();
		}
		return le;
	}
	
	public Iterable<Entry<K,V>> findAll(K key) throws InvalidKeyException{
		//Checkeo la clave
		if(key == null)
			throw new InvalidKeyException("findAll: Clave invalida");
				
		//Creo la lista que va a tener las entradas;
		PositionList<Entry<K,V>> toReturn = new ListaDobleEnlazada<Entry<K,V>>();
				
		//Obtengo la clave del bucket;
		int claveBucket = this.hash(key);
				
		//Por posicion de la lista del bucket, compruebo y meto en la lista de entradas;
		for (Position<Entry<K,V>> p : arreglo[claveBucket].positions()) {
				if (p.element().getKey().equals(key))
					toReturn.addLast(p.element());
		}
				
		return toReturn;
	}
	/*
	private Map<K,Integer> MetodoPilaEliminaDiccionario(PilaConEnlaces<K> p) throws InvalidKeyException, EmptyStackException, InvalidEntryException
	{
		Map<K,Integer> m1 = new MapeoConLista<K,Integer>();
		PositionList<V> le = new ListaDobleEnlazada<V>();
		int contador=0;
		
		while(!(p.isEmpty())) {
		K ClaveAEliminar = p.pop();
		
		if(ClaveAEliminar == null)
			throw new InvalidKeyException("Clave Invalida");
		else 
		{
			Iterable<Entry<K,V>> entradas = findAll(ClaveAEliminar);
			for(Entry<K,V> ent : entradas) {
				le.addLast(remove(ent).getValue());
				contador++;
			}
			m1.put(ClaveAEliminar,contador);
		}
		
		}
		return m1;
	}
	*/
	
}
