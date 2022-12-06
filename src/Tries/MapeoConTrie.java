package Tries;

import Exceptions.InvalidKeyException;
import TDAMapeo.Entry;
import TDAMapeo.Map;

public class MapeoConTrie<E> implements Map<String,E>{
	
	protected NodoTrie<E> root;
	
	//Clase anidada estatica:
	/*
	public static class InvalidKeyException extends Exception
	{
		public InvalidKeyException( String msg )
		{
			super( msg );
		}
	}
	*/
	public MapeoConTrie() {
		root = new NodoTrie<E>(null);
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E get(String key) throws InvalidKeyException {
		return getaux( key, 0, key.length(), root );
	}

	private E getaux( String clave, int i, int n, NodoTrie<E> p )
	{
		if( i == n ) 
			return root.getImagen();
		else { 
			int indice = (int) clave.charAt(i) - (int) 'a';
			if( p.getHijo(indice) == null ) 
				return null;
			return getaux( clave, i+1, n, p.getHijo(indice) );
		}
	}
	
	@Override
	public E put(String key, E value) throws InvalidKeyException {
		return putaux( key, value, 0, key.length(), root );
	}
	/**
	 * Insertar el string que comienza en i a partir del nodo p:
	 * @return
	 */
	private E putaux( String clave, E valor, int i, int n, NodoTrie<E> p ) {
		if( i < n ) { 
		int indice = ((int) clave.charAt(i)) - ((int) 'a');
		if ( p.getHijo(indice) == null )
			p.setHijo( indice, new NodoTrie<E>(p) );
			return putaux( clave, valor, i+1, n, p.getHijo(indice) );
		}
		else { 
			E imagenVieja = p.getImagen(); 
			p.setImagen( valor ); 
			return imagenVieja;
		}
	}
	
	@Override
	public E remove(String key) throws InvalidKeyException {
		// Busco clave a partir de carácter 0 y árbol a partir de nodo raíz.
		return removeaux( key, 0, key.length(), root, 0 );
	}

	private E removeaux( String clave, int i, int n, NodoTrie<E> p, int indiceP ) throws InvalidKeyException {
		E toRet = null;
		if( i == n ) {
			if( p.getImagen() == null ) 
				throw new InvalidKeyException( "Clave inexistente ");
			toRet = root.getImagen();
			root.setImagen( null ); 
		} else { 
			int indice = (int) clave.charAt(i) - (int) 'a';
			if( p.getHijo(indice) == null )
				throw new InvalidKeyException( "Clave inexistente ");
			toRet = removeaux( clave, i+1, n, p.getHijo( indice ), indice );
		}
		return toRet;
	}
	@Override
	public Iterable<String> keys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<E> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Entry<String, E>> entries() {
		// TODO Auto-generated method stub
		return null;
	}

}
