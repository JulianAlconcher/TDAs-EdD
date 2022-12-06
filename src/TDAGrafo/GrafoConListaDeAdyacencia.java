	package TDAGrafo;

import java.util.Iterator;

import Exceptions.EmptyListException;
import Exceptions.EmptyQueueException;
import Exceptions.InvalidEdgeException;
import Exceptions.InvalidKeyException;
import Exceptions.InvalidPositionException;
import Exceptions.InvalidVertexException;
import TDACola.ColaEnlazada;
import TDACola.Queue;
import TDALista.ListaDoblementeEnlazada2022;
import TDALista.PositionList;
import TDAMapeo.Map;
import TDAMapeo.MapeoConHashAbierto2022;

public class GrafoConListaDeAdyacencia<V,E> implements Graph<V,E> {

	protected PositionList<Vertice<V,E>> vertices;
	protected PositionList<Arco<V,E>> arcos;
	
	public GrafoConListaDeAdyacencia() {
		vertices = new ListaDoblementeEnlazada2022<Vertice<V,E>>();
		arcos = new ListaDoblementeEnlazada2022<Arco<V,E>>();
	}
	
	@Override
	public Iterable<Vertex<V>> vertices() {
		PositionList<Vertex<V>> aRetornar = new ListaDoblementeEnlazada2022<Vertex<V>>();
		for(Vertex<V> pos : vertices)
			aRetornar.addLast(pos);
		return aRetornar;
	}

	@Override
	public Iterable<Edge<E>> edges() {
		PositionList<Edge<E>> aRetornar = new ListaDoblementeEnlazada2022<Edge<E>>();
		for(Edge<E> pos : arcos)
			aRetornar.addLast(pos);
		return aRetornar;
		
	}

	@Override
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
		Vertice<V,E> n = checkVertex(v);
		PositionList<Edge<E>> toReturn = new ListaDoblementeEnlazada2022<Edge<E>>();
		for(Edge<E> pos : n.getAdyacentes())
			toReturn.addLast(pos);
		return toReturn;
	}

	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {
		Vertice<V,E> n = checkVertex(v);
		Arco<V,E> a = checkEdge(e);
		Vertex<V> toReturn = null;
		if(a.getV1().equals(n))
			toReturn = a.getV2();
		else if(a.getV2().equals(n))
			toReturn = a.getV1();
		else
			throw new InvalidEdgeException("El arco no es valido");
		
		return toReturn;
	}

	@Override
	public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException {
		Arco<V,E> arc = checkEdge(e);
		@SuppressWarnings("unchecked")
		Vertex<V>[] toReturn = new Vertex[2];
		toReturn[0] = arc.getV1();
		toReturn[1] = arc.getV2();
		return null;
	}

	@Override
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException {
		Vertice<V,E> v1 = checkVertex(v);
		Vertice<V,E> v2 = checkVertex(w);
		boolean encontre = false;
		Iterator<Arco<V,E>> ad = v1.getAdyacentes().iterator();
		while(ad.hasNext() && !encontre)
			encontre = ad.next().getV2().equals(v2);
		return encontre;
	}

	@Override
	public V replace(Vertex<V> v, V x) throws InvalidVertexException {
		Vertice<V,E> v1 = checkVertex(v);
		V toReturn = v1.element();
		v1.setRotulo(x);
		return toReturn;
	}

	@Override
	public Vertex<V> insertVertex(V x) {
		Vertice<V,E> v = new Vertice<V,E>(x);
		try {
			vertices.addLast(v);
			v.setPosicionEnListaVertices(vertices.last());
		}catch(EmptyListException e) {e.getMessage();}
		
		return v;
	}

	@Override
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) throws InvalidVertexException {
        Vertice<V,E> v1 = checkVertex(v);
        Vertice<V,E> v2 = checkVertex(w);
        Arco<V,E> edge = new Arco<V,E>(v2,v1,e);
        try {
            arcos.addLast(edge);
            v1.getAdyacentes().addLast(edge);
            v2.getAdyacentes().addLast(edge);
            
            edge.setPosicionEnArcos(arcos.last());
            edge.setPosicionEnLv1(v1.getAdyacentes().last());
            edge.setPosicionEnLv2(v2.getAdyacentes().last());
        } catch (EmptyListException e1) {
            e1.printStackTrace();
        }
        return edge;
    }

	@Override
	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		Vertice<V,E> n = checkVertex(v);
		V toReturn = v.element();
		try {
			for(Arco<V,E> p : n.getAdyacentes()) {
				p.getV1().getAdyacentes().remove(p.getPosicionEnLv1());
				p.getV2().getAdyacentes().remove(p.getPosicionEnLv2());
				arcos.remove(p.getPosicionEnArcos());
			}
			vertices.remove(n.getPosicionEnListaVertices());
		}catch(InvalidPositionException e) {e.getMessage();}
		
		n.setPosicionEnListaVertices(null);
		n.setRotulo(null);
		n = null;
		
		return toReturn;
	}

	@Override
	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
		Arco<V,E> n = checkEdge(e);
		E toReturn = null;
		toReturn = e.element();
		try {
			n.getV2().getAdyacentes().remove(n.getPosicionEnLv2());
			n.getV1().getAdyacentes().remove(n.getPosicionEnLv1());
			arcos.remove(n.getPosicionEnArcos());
		} catch (InvalidPositionException e1) {e1.printStackTrace();}
	
		return toReturn;
	}
 
	/**
	 * Metodo privado que cheque que el vertice pasado por parametro sea valido.
	 * @throws InvalidEdgeException
	 */
	@SuppressWarnings("unchecked")
	private Vertice<V,E> checkVertex(Vertex<V> v) throws InvalidVertexException{
		Vertice<V, E> toReturn = null;
		if(v == null )
			throw new InvalidVertexException("El vertice es invalido");
		try {
			toReturn = (Vertice<V,E>) v;
		}catch(ClassCastException e) {
			e.getMessage();
		}
		return toReturn;
	}
	/**
	 * Metodo privado que chequea que el arco pasado por parametro sea valido.
	 * @throws InvalidEdgeException
	 */
	@SuppressWarnings("unchecked")
	private Arco<V, E> checkEdge(Edge<E> e) throws InvalidEdgeException{
		Arco<V, E> toReturn = null;
		if(e == null)
			throw new InvalidEdgeException("El arco es invalido");
		try {
			toReturn = (Arco<V, E>) e;
		}catch(ClassCastException ex) {ex.printStackTrace();}
		return toReturn;
	}
	
	
	public  void DFSShell() {
		Map<Vertex<V>,Boolean> aux = new MapeoConHashAbierto2022<Vertex<V>,Boolean>();
		
			try {
				for (Vertex<V> ver : vertices())
					aux.put(ver,false);
				for (Vertex<V> v1 : vertices())
					if (aux.get(v1) == false)
						DFS(v1, aux);
			} catch (InvalidKeyException e) {e.printStackTrace();
			}
	}

	private void DFS(Vertex<V> vert, Map<Vertex<V>,Boolean> m ) {
		try {
			Vertice<V,E> ver = checkVertex(vert);
			m.put(vert,true);
			for (Edge<E> e : ver.getAdyacentes()) {
				Vertex<V> w = opposite(vert, e);
				if (m.get(w) == false)
					DFS(w, m);
			}
		} catch (InvalidVertexException | InvalidEdgeException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e1) {e1.printStackTrace();}

	}
	
	public void BFSShell() {
		Map<Vertex<V>,Boolean> visit = new MapeoConHashAbierto2022<Vertex<V>,Boolean>();
		
			try {
				for (Vertex<V> vert : vertices())
					visit.put(vert, false);
				for (Vertex<V> vert : vertices())
					if (visit.get(vert) == false)
						BFS(visit, vert);
			} catch (InvalidKeyException e) {e.printStackTrace();
			}
	}

	private void BFS(Map<Vertex<V>,Boolean> m , Vertex<V> v) {
		try {
			Queue<Vertex<V>> cola = new ColaEnlazada<Vertex<V>>();
			cola.enqueue(v);
			while (!cola.isEmpty()) {
				Vertex<V> w = cola.dequeue();
				m.put(v, true);
				for (Edge<E> e : incidentEdges(w)) {
					Vertex<V> x = opposite(w, e);
					if (m.get(x) == false) {
						cola.enqueue(x);
						m.put(x, true);
					}
				}
			}
		} catch (InvalidEdgeException | InvalidVertexException | EmptyQueueException |InvalidKeyException e1) {e1.printStackTrace();}

	}
	
	
	
}
