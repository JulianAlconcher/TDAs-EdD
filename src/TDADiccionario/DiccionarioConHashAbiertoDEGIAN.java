package TDADiccionario;
import Exceptions.InvalidEntryException;
import Exceptions.InvalidKeyException;
import Exceptions.InvalidPositionException;
import TDALista.*;

public class DiccionarioConHashAbiertoDEGIAN<K,V> implements Dictionary<K,V>{

	protected PositionList<Entry<K,V>> A[];
	protected int buckets;
	protected int cantEntradas;
	protected final float fc = 0.9f;
	
	@SuppressWarnings("unchecked")
	public DiccionarioConHashAbiertoDEGIAN() {
		buckets = 13;
		cantEntradas = 0;
		A = (PositionList<Entry<K,V>>[]) new PositionList[buckets];
		
		for (int i = 0; i < A.length; ++i) {
			A[i] = new ListaDoblementeEnlazada2022<Entry<K,V>>();
		}
	}
	
	public int size() {
		return cantEntradas;
	}
	
	public boolean isEmpty() {
		return (cantEntradas == 0);
	}
	
	public Entry<K,V> insert(K k, V v) throws InvalidKeyException{
		
		if (k == null)
			throw new InvalidKeyException("insert: Clave invalida");
		
		//Obtengo la clave del bucket;
		int claveBucket = this.hash(k);
		
		//Creo la entrada;
		Entry<K,V> toReturn = new Entrada<K, V>(k,v);
		
		//Entro al bucket y lo agrego;
		A[claveBucket].addLast(toReturn);
		
		//Incremento la entrada;
		++cantEntradas;
		return toReturn;
	}
	
	public Entry<K,V> find (K k) throws InvalidKeyException{
		
		if(k == null)
			throw new InvalidKeyException("find: Clave invalida");
		
		int claveBucket = this.hash(k);
		
		for (Position<Entry<K,V>> p : A[claveBucket].positions()) {
			if (p.element().getKey().equals(k))
				return (p.element());
		}
		
		return null;
	}
	
	public Iterable<Entry<K,V>> findAll(K k) throws InvalidKeyException{
		
		//Checkeo la clave
		if(k == null)
			throw new InvalidKeyException("findAll: Clave invalida");
		
		//Creo la lista que va a tener las entradas;
		PositionList<Entry<K,V>> toReturn = new ListaDoblementeEnlazada2022<Entry<K,V>>();
		
		//Obtengo la clave del bucket;
		int claveBucket = this.hash(k);
		
		//Por posicion de la lista del bucket, compruebo y meto en la lista de entradas;
		for (Position<Entry<K,V>> p : A[claveBucket].positions()) {
			if (p.element().getKey().equals(k))
				toReturn.addLast(p.element());
		}
		
		return toReturn;
	}
	
	public Entry<K,V> remove (Entry<K,V> e) throws InvalidEntryException{
		
		
		if (e == null)
			throw new InvalidEntryException("remove: Entrada invalida");
		
		int claveBucket = this.hash(e.getKey());
		
		try {
			for(Position<Entry<K,V>> p : A[claveBucket].positions()) {
				if (p.element() == e) {
					Entry<K,V> toReturn = p.element();
					A[claveBucket].remove(p);
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
	
	public Iterable<Entry<K,V>> entries(){
		PositionList<Entry<K,V>> toReturn = new ListaDoblementeEnlazada2022<Entry<K, V>>();
		
		for(int i = 0; i < A.length; i++) {
			for (Position<Entry<K,V>> p : A[i].positions()) {
				toReturn.addLast(p.element());
			}
		}
		
		return toReturn;
	}
	
	private int hash(K k) {
		return (k.hashCode() % buckets);
	}
	
	
}