package GrafosOTRO;

import java.util.Iterator;

import TDALista.*;
import Exceptions.*;

public class GrafoD_lista_adyacencia<V, E> implements GraphD<V, E> {
	protected PositionList<Vertice<V,E>> nodes;
	protected PositionList<Arco<V,E>> edges;
	
	public GrafoD_lista_adyacencia() {
		nodes = new ListaDoblementeEnlazada2022<Vertice<V,E>>();
		edges = new ListaDoblementeEnlazada2022<Arco<V,E>>();
	}
	@Override
	public Iterable<Vertex<V>> vertices() {
		ListaDoblementeEnlazada2022<Vertex<V>> iterable= new ListaDoblementeEnlazada2022<Vertex<V>>();
		for(Vertex<V> v: nodes)
			iterable.addLast(v);
		return iterable;
	}

	@Override
	public Iterable<Edge<E>> edges() {
		ListaDoblementeEnlazada2022<Edge<E>> iterable = new ListaDoblementeEnlazada2022<Edge<E>>();
		for(Edge<E> e: edges)
			iterable.addLast(e);
		return iterable;
	}

	@Override
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {  
		Vertice<V,E> v1 = checkVertex(v);
		ListaDoblementeEnlazada2022<Edge<E>> iterable = new ListaDoblementeEnlazada2022<Edge<E>>();
		for(Arco<V,E> e: v1.getAdyacentes()) {
			if(e.getV2().equals(v1))
				iterable.addLast(e);
		}
		return iterable;
	}

	@Override
	public Iterable<Edge<E>> succesorEdges(Vertex<V> v) throws InvalidVertexException {
		Vertice<V,E> v1 = checkVertex(v);
		ListaDoblementeEnlazada2022<Edge<E>> iterable = new ListaDoblementeEnlazada2022<Edge<E>>();
		for(Arco<V,E> e: v1.getAdyacentes()) {
			if(e.getV1().equals(v1))
				iterable.addLast(e);
		}
		return iterable;
	}

	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {
		Vertice<V,E> ver = checkVertex(v);
		Arco<V,E> arco = checkEdge(e);
		Vertex<V> toReturn;
		if(arco.getV1().equals(ver))
			toReturn = arco.getV2();
		else
			throw new InvalidEdgeException("Ela arco no es valido");
		return toReturn;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException {
		Arco<V,E> arc = checkEdge(e);
		Vertex<V>[] toReturn = (Vertex<V>[]) new Vertex[2];
		toReturn[0] = arc.getV1();
		toReturn[1] = arc.getV2();
		return null;
	}

	@Override
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException {
		Vertice<V,E> v1 = checkVertex(v);
		Vertice<V,E> v2 = checkVertex(w);
		boolean found = false;
		Iterator<Arco<V, E>> iterator = v1.getAdyacentes().iterator();
		while(!found && iterator.hasNext()) {
			Arco<V,E> current = iterator.next();
			found = current.getV1().equals(v2) || current.getV2().equals(v2);
		}
		return found;
	}

	@Override
	public V replace(Vertex<V> v, V x) throws InvalidVertexException {
		Vertice<V,E> v1 = checkVertex(v);
		V toReturn = v1.element();
		v1.setElement(x);
		return toReturn;
	}

	@Override
	public Vertex<V> insertVertex(V x) {
		Vertice<V,E> v = new Vertice<V,E>(x);
		try {
			nodes.addLast(v);
			v.setPosicionEnNodos(nodes.last());
		} catch (EmptyListException e) {
			e.printStackTrace();
		}
		return v;
	}

	@Override
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) throws InvalidVertexException {
		Vertice<V,E> v1 = checkVertex(v);
		Vertice<V,E> v2 = checkVertex(w);
		Arco<V,E> edge = new Arco<V,E>(e,v1,v2);
		try {
			edges.addLast(edge);
			v1.getAdyacentes().addLast(edge);
			v2.getAdyacentes().addLast(edge);
			edge.setPosicionEnArcos(edges.last());
			edge.setPosicionEnlv1(v1.getAdyacentes().last());
			edge.setPosicionEnlv1(v2.getAdyacentes().last());
		} catch (EmptyListException e1) {
			e1.printStackTrace();
		}
		return edge;
	}

	@Override
	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		Vertice<V,E> v1 = checkVertex(v);
		V toReturn = null;
		try {
			for(Arco<V,E> e: v1.getAdyacentes()) {
				if(e.getV1().equals(v1)) {
					v1.getAdyacentes().remove(e.posicionEnlv1);
					e.getV2().getAdyacentes().remove(e.posicionEnlv2);
				}
				else {
					v1.getAdyacentes().remove(e.posicionEnlv2);
					e.getV2().getAdyacentes().remove(e.posicionEnlv1);
				}
			}
			toReturn = v1.element();
			nodes.remove(v1.getPosicionEnNodos());
			v1.setElement(null);
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}
		return toReturn;
	}

	@Override
	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
		Arco<V,E> arco = checkEdge(e);
		E toReturn = arco.element();
		try {
			arco.getV1().getAdyacentes().remove(arco.getPosicionEnlv1());
			arco.getV2().getAdyacentes().remove(arco.getPosicionEnlv2());
			edges.remove(arco.getPosicionEnArcos());
		} catch (InvalidPositionException e1) {
			e1.printStackTrace();
		}
		return toReturn;
	}

	@SuppressWarnings("unchecked")
	private Vertice<V, E> checkVertex(Vertex<V> v) throws InvalidVertexException{
		Vertice<V, E> toReturn = null;
		if(v == null || v.element() == null)
			throw new InvalidVertexException("El vertice es invalido");
		try {
			toReturn = (Vertice<V, E>) v;
		}
		catch(ClassCastException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
	@SuppressWarnings("unchecked")
	private Arco<V, E> checkEdge(Edge<E> e) throws InvalidEdgeException{
		Arco<V, E> toReturn = null;
		if(e == null)
			throw new InvalidEdgeException("El arco es invalido");
		try {
			toReturn = (Arco<V, E>) e;
		}
		catch(ClassCastException ex) {
			ex.printStackTrace();
		}
		return toReturn;
	}
}