package TDAGrafo;

import Exceptions.InvalidEdgeException;
import Exceptions.InvalidVertexException;
import TDALista.ListaDobleEnlazada;
import TDALista.PositionList;

public class Grafo<V, E> implements Graph<V, E>  {
	
	protected PositionList<Vertice<V,E>> nodos;
	protected PositionList<Arco<V,E>> arcos;
	
	public Grafo() {
		nodos = new ListaDobleEnlazada<Vertice<V,E>>();
		arcos = new ListaDobleEnlazada<Arco<V,E>>();
	}
	/*
	 * Retorna una colecci�n iterable con todos los v�rtices del grafo. 
	 */
	public Iterable<Vertex<V>> vertices() {
		PositionList<Vertex<V>> lista = new ListaDobleEnlazada<Vertex<V>>();
		for(Vertex<V> v : nodos)
			lista.addLast(v);
		return lista;
	}

	/*
	 * retorna una colecci�n iterable con todos los arcos del grafo. 
	 */
	public Iterable<Edge<E>> edges() {
		PositionList<Edge<E>> lista = new ListaDobleEnlazada<Edge<E>>();
		for(Edge<E> a : arcos) {
			lista.addLast(a);
		}
		return lista;
	}
	/*
	 * Retorna una colecci�n iterable con todos los arcos incidentes sobre un v�rtice V
	 */
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
		PositionList<Edge<E>> lista = new ListaDobleEnlazada<Edge<E>>();
		@SuppressWarnings("unchecked")
		Vertice<V,E> vert = (Vertice<V,E>) v;
		for( Edge<E> e : vert.getAdyacentes() )
		lista.addLast(e);
		return lista;
	}

	/*
	 * Retorna el otro v�rtice w del arco e=(v, w); 
	 * Ocurre un error si e no es incidente (o emergente de v). 
	 */
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {
		 if (v == null) {
	            throw new InvalidVertexException("V�rtice inv�lido");
	        }
	        if (e == null) {
	            throw new InvalidEdgeException("Arco inv�lido");
	        }
	        
	        Vertex<V> retorna;
	        @SuppressWarnings("unchecked")
			Arco<V,E> a = (Arco<V, E>) e;
	        if (v == a.getV1()) {
	            retorna = (Vertex<V>) a.getV2();
	        }
	        else {
	            if (v == a.getV2()) {
	                retorna = (Vertex<V>) a.getV1();
	            }
	            else {
	                throw new InvalidVertexException("EL v�rtice v no est� en el arco e");
	            }

	        }
	        return retorna;
	}

	/*
	 * Retorna un arreglo (de 2 componentes) conteniendo los v�rtices del arco e. 
	 */
	public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException {
		if(e == null) {
			throw new InvalidEdgeException("ARCO INVALIDO");
		}
		@SuppressWarnings("unchecked")
		Vertice<V,E>[] ret = new Vertice[1];
		@SuppressWarnings("unchecked")
		Arco<V,E> ee = (Arco<V, E>) e;
		ret[0] = ee.getV1();
		ret[1] =ee.getV2();
		return ret;
	}


	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException {
		return false;
	}

	/*
	 * Reemplaza el r�tulo del v�rtice v con x. 
	 */
	public V replace(Vertex<V> v, V x) throws InvalidVertexException {
		V ret = v.element();
		@SuppressWarnings("unchecked")
		Vertice<V,E> vv = (Vertice<V, E>) v;
		vv.setRotulo(x);
		return ret;
	}


	public Vertex<V> insertVertex(V x) {
		Vertice<V,E> v = new Vertice<V,E>(x);
		nodos.addLast(v);
		//v.setPosicionEnNodos(nodos.last());
		return v;	
		
	}


	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) throws InvalidVertexException {
		return null;
	}


	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		return null;
	}

	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
		return null;
	}

}
