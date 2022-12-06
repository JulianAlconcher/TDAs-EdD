package TDADiccionario;

import Exceptions.InvalidEntryException;
import Exceptions.InvalidKeyException;
import Exceptions.InvalidPositionException;
import TDALista.ListaDobleEnlazada;
import TDALista.Position;
import TDALista.PositionList;

public class DiccionarioConLista<K,V> implements Dictionary<K,V> {
	protected PositionList<Entry<K,V>> D;
	
	public DiccionarioConLista()
	{
		D = new ListaDobleEnlazada<Entry<K,V>>();
	}
	/*
	 * Retorna el numero de entradas de D
	 */
	public int size() {
		
		return D.size();
	}

	/*
	 * Testea si D esta vacio
	 */
	public boolean isEmpty() {
		
		return D.isEmpty();
	}

	/*
	 * Si D contiene una entrada e con clave igual a key, entonces retorna e
	 * Si no retorna null
	 * Lanza una exepcion de clave invalida
	 */
	public Entry<K, V> find(K key) throws InvalidKeyException {
		if(key==null)
			throw new InvalidKeyException("Clave Invalida");
		else{
			for(Position<Entry<K,V>> p : D.positions()){
				if(p.element().getKey().equals(key))
					return p.element();
			}
		}
			return null;
	}

	/*
	 * Retorna una coleccion iterable conteniendo todas las entradas con clave igual a key
	 */
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		PositionList<Entry<K,V>> l = new ListaDobleEnlazada<Entry<K,V>>();
		if(key==null)
			throw new InvalidKeyException("Clave Invalida");
		else {
			for(Position<Entry<K,V>> p : D.positions()){
				if(p.element().getKey().equals(key)){
					l.addLast(p.element());
				}
			} 
			return l;
		}
	}

	/*
	 * Inserta en D una entrada e con clave key y valor value y retorna la entrada e
	 */
	public Entry<K,V> insert(K key, V value) throws InvalidKeyException{
		Entry<K,V> entrada = new Entrada<K,V>(key,value);
		if(key == null){
			throw new InvalidKeyException("Clave invalida");
		}else{
			D.addLast(entrada);
			return entrada; 	
		}
	}
	/*
	 * Remueve de D la entrada e, retornando la entrada removida
	 * Ocurre un error si e no esta en D
	 */
	public Entry<K,V> remove(Entry<K,V> e) throws InvalidEntryException{	
		if(e == null){
			throw new InvalidEntryException("Entrada invalida");
		}
		else{
			for(Position<Entry<K,V>> p : D.positions()){
				if(p.element().equals(e)){
					try{
						D.remove(p);
					}catch(InvalidPositionException ex){
						ex.getStackTrace();
					}
					return e;
				}
			}
			throw new InvalidEntryException("Entrada invalida");
		}	
	}
	/*
	 * Retorna una coleccion iterable con las entradas clave-valor de D
	 */
	public Iterable<Entry<K,V>> entries(){
		ListaDobleEnlazada<Entry<K,V>> le = new ListaDobleEnlazada<Entry<K,V>>();
		if(!isEmpty()){
			for(Position<Entry<K,V>> p : D.positions()){
				le.addLast(p.element());
			}
		}
		return le;
	}
}

