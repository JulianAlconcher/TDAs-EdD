package GrafosOTRO;

import TDALista.*;
import Exceptions.*;

public class Grafo_matriz_adyacencia<V,E> implements Graph<V,E> {
	//Lista de vertices
	protected PositionList<Vertex<V>> nodes;
	//Lista de arcos
	protected PositionList<Edge<E>> edges;
	//Matriz
	protected Edge<E> [][] matriz;
	//Cantidad de vertices
	protected int cant;
	
	/**
	 * Constructor de la clase 
	 * @param n
	 */
	@SuppressWarnings("unchecked")
	public Grafo_matriz_adyacencia(int n) {
		nodes = new ListaDoblementeEnlazada2022<Vertex<V>>();
		edges = new ListaDoblementeEnlazada2022<Edge<E>>();
		matriz = (Edge<E>[][]) new ArcoM[n][n];
		cant = 0;
	}
	
	public Grafo_matriz_adyacencia() {
		this(10);
	}
	
	@Override
	public Iterable<Vertex<V>> vertices() {
		ListaDoblementeEnlazada2022<Vertex<V>> aRetornar= new ListaDoblementeEnlazada2022<Vertex<V>>();
		for(Vertex<V> v: nodes)
			aRetornar.addLast(v);
		return aRetornar;
	}

	@Override
	public Iterable<Edge<E>> edges() {
		ListaDoblementeEnlazada2022<Edge<E>> aRetornar = new ListaDoblementeEnlazada2022<Edge<E>>();
		for(Edge<E> e: edges)
			aRetornar.addLast(e);
		return aRetornar;
	}

	@Override
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
		VerticeM<V> n = checkVertex(v);		
		ListaDoblementeEnlazada2022<Edge<E>> aRetornar = new ListaDoblementeEnlazada2022<Edge<E>>();
		for(int i=0; i<matriz[0].length; i++)
			if(matriz[i][n.getIndex()]!=null)
				aRetornar.addLast(matriz[i][n.getIndex()]);
		return aRetornar;
	}

	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {
		VerticeM<V> ver = checkVertex(v);
		ArcoM<V,E> edge = checkEdge(e);
		Vertex<V> toReturn;
		if(edge.getV1().equals(ver))
			toReturn = edge.getV2();
		else
			if(edge.getV2().equals(ver))
				toReturn = edge.getV1();
			else
				throw new InvalidEdgeException("El arco no es valido");
		return toReturn;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException {
		ArcoM<V,E> arc = checkEdge(e);
		Vertex<V>[] toReturn = (Vertex<V>[]) new Vertex[2];
		toReturn[0] = arc.getV1();
		toReturn[1] = arc.getV2();
		return toReturn;
	}

	@Override
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException {
		VerticeM<V> v1 = checkVertex(v);
		VerticeM<V> v2 = checkVertex(w);
		return matriz[v1.getIndex()][v2.getIndex()]!=null;
	}

	@Override
	public V replace(Vertex<V> v, V x) throws InvalidVertexException {
		VerticeM<V> v1 = checkVertex(v);
		V toReturn = v1.element();
		v1.setElement(x);
		return toReturn;
	}

	@Override
	public Vertex<V> insertVertex(V x) {
		if(cant==matriz[0].length)
			resize();
		VerticeM<V> v = new VerticeM<V>(x,cant++);
		try {
			nodes.addLast(v);
			v.setPosInNodes(nodes.last());
		} catch (EmptyListException e) {
			e.printStackTrace();
		}
		return v;
	}

	private void resize() {
		// TODO Auto-generated method stub  
	}

	@Override
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) throws InvalidVertexException {
		VerticeM<V> v1 = checkVertex(v);
		VerticeM<V> v2 = checkVertex(w);
		ArcoM<V,E> newEdge = new ArcoM<V,E>(e,v1,v2);
		try {
			edges.addLast(newEdge);
			newEdge.setPosInEdges(edges.last());
			matriz[v1.getIndex()][v2.getIndex()] = newEdge;
			matriz[v2.getIndex()][v1.getIndex()] = newEdge;		
		} catch (EmptyListException e1) {
			e1.printStackTrace();
		}
		return newEdge;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		VerticeM<V> v1 = checkVertex(v);
		V toReturn = v1.element();
		ArcoM<V,E> aux;
		try {
			for(int i=0;i<matriz.length;i++) 
				if(matriz[i][v1.getIndex()]!=null) {
					aux = (ArcoM<V, E>) matriz[i][v1.getIndex()];
					edges.remove(aux.getPosInEdges());
					matriz[i][v1.getIndex()]=null;
					matriz[v1.getIndex()][i]=null;
				}
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}
		v1.setElement(null);
		return toReturn;
	}

	@Override
	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
		ArcoM<V,E> edge = checkEdge(e);
		VerticeM<V> v1 = edge.getV1();
		VerticeM<V> v2 = edge.getV2();
		E toReturn = edge.element();
		try {
			matriz[v1.getIndex()][v2.getIndex()] = null;
			matriz[v2.getIndex()][v1.getIndex()] = null;	
			edges.remove(edge.getPosInEdges());
		} catch (InvalidPositionException e1) {
			e1.printStackTrace();
		}
		
		return toReturn;
	}

	private VerticeM<V> checkVertex(Vertex<V> v) throws InvalidVertexException{
		VerticeM<V> toReturn = null;
		if(v == null || v.element() == null)
			throw new InvalidVertexException("El vertice es invalido");
		try {
			toReturn = (VerticeM<V>) v;
		}
		catch(ClassCastException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
	@SuppressWarnings("unchecked")
	private ArcoM<V, E> checkEdge(Edge<E> e) throws InvalidEdgeException{
		ArcoM<V, E> toReturn = null;
		if(e == null)
			throw new InvalidEdgeException("El arco es invalido");
		try {
			toReturn = (ArcoM<V, E>) e;
		}
		catch(ClassCastException ex) {
			ex.printStackTrace();
		}
		return toReturn;
	}
}