package TDAGrafoLalo;

import java.util.Iterator;

import Exceptions.EmptyListException;
import Exceptions.EmptyQueueException;
import Exceptions.InvalidPositionException;
import TDACola.*;
import TDALista.*;

public class Grafo_lista_adyacencia<V, E> implements Graph<V, E> {

	protected PositionList<VerticeAd<V, E>> nodos;
	protected PositionList<ArcoAd<V, E>> arcos;

	public Grafo_lista_adyacencia() {
		nodos = new ListaDoblementeEnlazada2022<VerticeAd<V, E>>();
		arcos = new ListaDoblementeEnlazada2022<ArcoAd<V, E>>();
	}

	public Iterable<Vertex<V>> vertices() {
		PositionList<Vertex<V>> lista = new ListaDoblementeEnlazada2022<Vertex<V>>();
		for (VerticeAd<V, E> pos : nodos) {
			lista.addLast(pos);
		}
		return lista;
	}

	public Iterable<Edge<E>> edges() {
		PositionList<Edge<E>> lista = new ListaDoblementeEnlazada2022<Edge<E>>();
		for (ArcoAd<V, E> pos : arcos) {
			lista.addLast(pos);
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	public Iterable<Edge<E>> incidentEdges(Vertex<V> vert) throws InvalidVertexException {
		if (vert == null)
			throw new InvalidVertexException("Vertice invalido");
		PositionList<Edge<E>> lista = new ListaDoblementeEnlazada2022<Edge<E>>();
		VerticeAd<V, E> vertice = (VerticeAd<V, E>) vert;
		for (ArcoAd<V, E> arco : vertice.getAdyacentes()) {
			lista.addLast(arco);
		}
		return lista;
	}
	@SuppressWarnings("unchecked")
	public Iterable<Edge<E>> emergentEdges(Vertex<V> v) throws InvalidVertexException {
		if (v == null)
			throw new InvalidVertexException("Vertice invalido");
		PositionList<Edge<E>> lista = new ListaDoblementeEnlazada2022<Edge<E>>();
		VerticeAd<V, E> vertice = (VerticeAd<V, E>) v;
		for (ArcoAd<V, E> arco : vertice.getAdyacentes()) {
			lista.addLast(arco);
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	public Vertex<V> opposite(Vertex<V> vert, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {
		if (vert == null)
			throw new InvalidVertexException("Vertice invalido");
		if (e == null)
			throw new InvalidEdgeException("Arco invalido");
		Vertex<V> salida = null;
		ArcoAd<V, E> arco = (ArcoAd<V, E>) e;
		if (arco.getSuces() == vert)
			salida = arco.getPred();
		else {
			if (arco.getPred() == vert)
				salida = arco.getSuces();
			else
				throw new InvalidEdgeException("Ninguno de los extremos coincide con el vertice");
		}
		return salida;
	}

	@SuppressWarnings("unchecked")
	public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException {
		VerticeAd<V, E>[] salida = (VerticeAd<V, E>[]) new VerticeAd[2];
		if (e == null)
			throw new InvalidEdgeException("Arco invalido");
		ArcoAd<V, E> arco = (ArcoAd<V, E>) e;
		salida[0] = arco.getPred();
		salida[1] = arco.getSuces();
		return salida;
	}

	@SuppressWarnings("unchecked")
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException {
		if (v == null || w == null)
			throw new InvalidVertexException("Vertice invalido");
		boolean salida = false;
		ArcoAd<V, E> a;
		VerticeAd<V,E> vv = (VerticeAd<V,E>)v;
		Iterator<ArcoAd<V,E>> itArcos = vv.getAdyacentes().iterator();
		while (itArcos.hasNext() && !salida) {
			a = itArcos.next();
			if (a.getPred() == v && a.getSuces() == w)
				salida = true;
			else if (a.getPred() == w && a.getSuces() == v)
				salida = true;
		}
		return salida;
	}

	@SuppressWarnings("unchecked")
	public V replace(Vertex<V> v, V x) throws InvalidVertexException {
		if (v == null)
			throw new InvalidVertexException("Vertice invalido");
		V salida = null;
		VerticeAd<V, E> vertice = (VerticeAd<V, E>) v;
		salida = vertice.element();
		vertice.setElement(x);
		return salida;
	}

	public Vertex<V> insertVertex(V x) {
		VerticeAd<V, E> nuevo = new VerticeAd<V, E>(x);
		nodos.addLast(nuevo);
		try {
			nuevo.setPosicion(nodos.last());
		} catch (EmptyListException e) {
			System.out.println(e.getMessage());
		}
		return nuevo;
	}

	@SuppressWarnings("unchecked")
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) throws InvalidVertexException {
		if (v == null || w == null)
			throw new InvalidVertexException("Vertice invalido");
		VerticeAd<V, E> vv = (VerticeAd<V, E>) v;
		VerticeAd<V, E> ww = (VerticeAd<V, E>) w;
		ArcoAd<V, E> nuevo = new ArcoAd<V, E>(e, vv, ww);
		try {
			vv.getAdyacentes().addLast(nuevo);
			nuevo.setPosPred(vv.getAdyacentes().last());
			ww.getAdyacentes().addLast(nuevo);
			nuevo.setPosSuces(ww.getAdyacentes().last());
			arcos.addLast(nuevo);
			nuevo.setPosicionEnAdyacentes(arcos.last());
		} catch (EmptyListException e1) {
			System.out.println(e1.getMessage());
		}
		return nuevo;
	}

	@SuppressWarnings("unchecked")
	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		if (v == null)
			throw new InvalidVertexException("Vertice invalido");
		V removed = v.element();
		VerticeAd<V, E> vv = (VerticeAd<V, E>) v;
		try {
			for (ArcoAd<V, E> arco : vv.getAdyacentes()) {
				arcos.remove(arco.getPosicion());
				arco.getPred().getAdyacentes().remove(arco.getPosPred());
				arco.getSuces().getAdyacentes().remove(arco.getPosSuces());
			}
			nodos.remove(vv.getPosicion());
		} catch (InvalidPositionException e) {
			System.out.println(e.getMessage());
		}
		return removed;
	}

	@SuppressWarnings("unchecked")
	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
		if (e == null)
			throw new InvalidEdgeException("Arco invalido");
		E removed = e.element();
		ArcoAd<V, E> arco = (ArcoAd<V, E>) e;
		try {
			arcos.remove(arco.getPosicion());
			arco.getPred().getAdyacentes().remove(arco.getPosPred());
			arco.getSuces().getAdyacentes().remove(arco.getPosSuces());
		} catch (InvalidPositionException e1) {
			System.out.println(e1.getMessage());
		}
		return removed;
	}
	
	public void DFSShell() {
		for (Vertex<V> ver : vertices())
			ver.setEstado(false);
		for (Vertex<V> v1 : vertices())
			if (v1.getEstado() == false)
				DFS(v1);
	}

	private void DFS(Vertex<V> v) {
		try {
			System.out.println(v.element());
			v.setEstado(true);
			Iterable<Edge<E>> adyacentes = incidentEdges(v);
			for (Edge<E> e : adyacentes) {
				Vertex<V> w = opposite(v, e);
				if (w.getEstado() == false)
					DFS(w);
			}
		} catch (InvalidVertexException | InvalidEdgeException e) {
			e.printStackTrace();
		}

	}
	
	public void BFSShell() {
		for (Vertex<V> ver : vertices())
			ver.setEstado(false);
		for (Vertex<V> v1 : vertices())
			if (v1.getEstado() == false)
				BFS(v1);
	}
	private void BFS( Vertex<V> v)  {
		try {
			Queue<Vertex<V>> cola = (Queue<Vertex<V>>) new ColaConArregloCircular<Vertex<V>>();
			cola.enqueue(v);
			while (!cola.isEmpty()) {
				Vertex<V> w;
			
					w = cola.dequeue();
				
				System.out.println(w.element());
				w.setEstado(true);
				for (Edge<E> e : incidentEdges(w)) {
					Vertex<V> x = opposite(w, e);
					if (x.getEstado() == false) {
						cola.enqueue(x);
						x.setEstado(true);
					}
				}
			}
		} catch (EmptyQueueException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidVertexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidEdgeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
