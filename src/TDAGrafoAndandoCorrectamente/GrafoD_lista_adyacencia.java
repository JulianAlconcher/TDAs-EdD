package TDAGrafoAndandoCorrectamente;

import java.util.Iterator;

import TDALista.ListaDoblementeEnlazada2022;
import TDALista.PositionList;
import Exceptions.*;

public class GrafoD_lista_adyacencia<V,E> implements GraphD<V,E> {
	
	protected PositionList<VerticeD<V, E>> nodos;
	protected PositionList<ArcoD<V, E>> arcos;

	public GrafoD_lista_adyacencia() {
		nodos = new ListaDoblementeEnlazada2022<VerticeD<V, E>>();
		arcos = new ListaDoblementeEnlazada2022<ArcoD<V, E>>();
	}
	
	public Iterable<Vertex<V>> vertices() {
		PositionList<Vertex<V>> lista = new ListaDoblementeEnlazada2022<Vertex<V>>();
		for (VerticeD<V, E> pos : nodos) {
			lista.addLast(pos);
		}
		return lista;
	}

	public Iterable<Edge<E>> edges() {
		PositionList<Edge<E>> lista = new ListaDoblementeEnlazada2022<Edge<E>>();
		for (ArcoD<V, E> pos : arcos) {
			lista.addLast(pos);
		}
		return lista;
	}

	public Iterable<Edge<E>> incidentEdges(Vertex<V> vert) throws InvalidVertexException {
		if (vert == null)
			throw new InvalidVertexException("Vertice invalido");
		PositionList<Edge<E>> lista = new ListaDoblementeEnlazada2022<Edge<E>>();
		VerticeD<V, E> vertice = (VerticeD<V, E>) vert;
		for (ArcoD<V, E> arco : vertice.getIncidentes()) {
			lista.addLast(arco);
		}
		return lista;
	}

	public Vertex<V> opposite(Vertex<V> vert, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {
		if (vert == null)
			throw new InvalidVertexException("Vertice invalido");
		if (e == null)
			throw new InvalidEdgeException("Arco invalido");
		Vertex<V> salida = null;
		ArcoD<V, E> arco = (ArcoD<V, E>) e;
		if (arco.getCola() == vert)
			salida = arco.getPunta();
		else {
			if (arco.getPunta() == vert)
				salida = arco.getCola();
			else
				throw new InvalidEdgeException("Ninguno de los extremos coincide con el vertice");
		}
		return salida;
	}

	public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException {
		VerticeAd<V, E>[] salida = (VerticeAd<V, E>[]) new VerticeAd[2];
		if (e == null)
			throw new InvalidEdgeException("Arco invalido");
		ArcoAd<V, E> arco = (ArcoAd<V, E>) e;
		salida[0] = arco.getPred();
		salida[1] = arco.getSuces();
		return salida;
	}

	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException {
		if (v == null || w == null)
			throw new InvalidVertexException("Vertice invalido");
		boolean salida = false;
		ArcoD<V, E> a;
		VerticeD<V,E> vv = (VerticeD<V,E>)v;
		Iterator<ArcoD<V,E>> itArcos = vv.getEmergentes().iterator();
		while (itArcos.hasNext() && !salida) {
			a = itArcos.next();
			if (a.getCola() == v && a.getPunta() == w)
				salida = true;
		}
		return salida;
	}

	public V replace(Vertex<V> v, V x) throws InvalidVertexException {
		if (v == null)
			throw new InvalidVertexException("Vertice invalido");
		V salida = null;
		VerticeD<V, E> vertice = (VerticeD<V, E>) v;
		salida = vertice.element();
		vertice.setRotulo(x);
		return salida;
	}

	public Vertex<V> insertVertex(V x) {
		VerticeD<V, E> nuevo = new VerticeD<V, E>(x);
		nodos.addLast(nuevo);
		try {
			nuevo.setPosEnNodos(nodos.last());
		} catch (EmptyListException e) {
			System.out.println(e.getMessage());
		}
		return nuevo;
	}

	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) throws InvalidVertexException {
		if (v == null || w == null)
			throw new InvalidVertexException("Vertice invalido");
		VerticeD<V, E> vv = (VerticeD<V, E>) v;
		VerticeD<V, E> ww = (VerticeD<V, E>) w;
		ArcoD<V, E> nuevo = new ArcoD<V, E>(e, vv, ww);
		try {
			vv.getEmergentes().addLast(nuevo);
			nuevo.setPosEnEmergentes(vv.getEmergentes().last());
			ww.getIncidentes().addLast(nuevo);
			nuevo.setPosEnIncidentes(ww.getIncidentes().last());
			arcos.addLast(nuevo);
			nuevo.setPosEnArcos(arcos.last());
		} catch (EmptyListException e1) {
			System.out.println(e1.getMessage());
		}
		return nuevo;
	}

	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		if (v == null)
			throw new InvalidVertexException("Vertice invalido");
		V removed = v.element();
		VerticeD<V, E> vv = (VerticeD<V, E>) v;
		try {
			for (ArcoD<V, E> arco : vv.getEmergentes()) {
				arcos.remove(arco.getPosEnEmergentes());
				arco.getCola().getEmergentes().remove(arco.getPosEnEmergentes());
				arco.getPunta().getIncidentes().remove(arco.getPosEnIncidentes());
			}
			for (ArcoD<V, E> arco : vv.getIncidentes()) {
				arcos.remove(arco.getPosEnIncidentes());
				arco.getCola().getEmergentes().remove(arco.getPosEnEmergentes());
				arco.getPunta().getIncidentes().remove(arco.getPosEnIncidentes());
			}
			nodos.remove(vv.getPosEnNodos());
		} catch (InvalidPositionException e) {
			System.out.println(e.getMessage());
		}
		return removed;
	}

	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
		if (e == null)
			throw new InvalidEdgeException("Arco invalido");
		E removed = e.element();
		ArcoD<V, E> arco = (ArcoD<V, E>) e;
		try {
			arcos.remove(arco.getPosEnArcos());
			arco.getCola().getEmergentes().remove(arco.getPosEnEmergentes());
			arco.getPunta().getIncidentes().remove(arco.getPosEnIncidentes());
		} catch (InvalidPositionException e1) {
			System.out.println(e1.getMessage());
		}
		return removed;
	}

	@Override
	public Iterable<Edge<E>> succesorEdges(Vertex<V> v) throws InvalidVertexException {
		if (v == null)
			throw new InvalidVertexException("Vertice invalido");
		PositionList<Edge<E>> lista = new ListaDoblementeEnlazada2022<Edge<E>>();
		VerticeD<V, E> vertice = (VerticeD<V, E>) v;
		for (ArcoD<V, E> arco : vertice.getEmergentes()) {
			lista.addLast(arco);
		}
		return lista;
	}
	

}
