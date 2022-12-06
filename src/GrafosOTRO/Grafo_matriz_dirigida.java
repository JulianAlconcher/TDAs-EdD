package GrafosOTRO;
/*
import TDALista.*;
import Exceptions.*;
import TDALista.PositionList;

public class Grafo_matriz_dirigida<V, E> implements GraphD<V, E> {

	protected PositionList<VerticeM<V>> vertices;
	protected PositionList<ArcoM<V, E>> arcos;
	protected ArcoM<V, E>[][] matriz;
	protected int cantVertices;
/*
@SuppressWarnings("unchecked")
public Grafo_matriz_dirigida(int n) {
		vertices = new ListaDoblementeEnlazada2022<VerticeM<V>>();
		arcos = new ListaDoblementeEnlazada2022<ArcoM<V, E>>();
		matriz = new ArcoM[n][n];
		cantVertices = 0;
	}

	public Grafo_matriz_dirigida() {
		this(40);
	}
	@Override
	public Iterable<Vertex<V>> vertices() {
	PositionList<Vertex<V>> coleccion = new ListaDoblementeEnlazada2022<Vertex<V>>();

		for (Vertex<V> vertex : vertices)
			coleccion.addLast(vertex);

		return coleccion;
	}

	@Override
	public Iterable<Edge<E>> edges() {
		PositionList<Edge<E>> listaEdge = new ListaDoblementeEnlazada2022<Edge<E>>();

		for (Edge<E> edge : arcos)
			listaEdge.addLast(edge);

		return listaEdge;
	}

	@Override
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
		PositionList<Edge<E>> incidentes = new ListaDoblementeEnlazada2022<Edge<E>>();
		VerticeM<V> vertice = checkVertex(v);
		int col = vertice.getIndex();

		for (int f = 0; f < vertices.size(); f++) {
			if (matriz[f][col] != null)
				incidentes.addLast(matriz[f][col]);
	}

		return incidentes;
	}

	@Override
	public Iterable<Edge<E>> succesorEdges(Vertex<V> v) throws InvalidVertexException {
		PositionList<Edge<E>> emergentes = new ListaDoblementeEnlazada2022<Edge<E>>();
		VerticeM<V> vertice = checkVertex(v);
		int f = vertice.getIndex();

		for (int c = 0; c < vertices.size(); c++) {
			if (matriz[f][c] != null)
				emergentes.addLast(matriz[f][c]);
		}

		return emergentes;
	}

@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {
	VerticeM<V> retorno = null, vertex = checkVertex(v);
		ArcoM<V, E> edge = checkEdges(e);

		if (edge.getV1() == vertex)
			retorno = edge.getV2();
		else
			throw new InvalidEdgeException("oppositeeee");

		return retorno;
	}

	@Override
	public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException {
		Vertex<V>[] arreglo = (Vertex<V>[]) new Vertice[2];
	ArcoM<V, E> arco = checkEdges(e);
		arreglo[0] = arco.getV1(); // origen
		arreglo[1] = arco.getV2(); // destino
		return arreglo;
	}

	@Override
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException {
	VerticeM<V> vertexV = checkVertex(v);
		VerticeM<V> vertexW = checkVertex(w);
		int f = vertexV.getIndex();
		int c = vertexW.getIndex();

	return matriz[f][c] != null;
	}

	@Override
	public V replace(Vertex<V> v, V x) throws InvalidVertexException {
		V valorRetorno = null;

		VerticeM<V> vertice = checkVertex(v);
		valorRetorno = vertice.element();
		vertice.setElement(x);

		return valorRetorno;
	}

	@Override
	public Vertex<V> insertVertex(V x) {
		VerticeM<V> vertex = null;
		try {
			vertex = new VerticeM<V>(x, vertices.size());
			cantVertices += 1;
			vertices.addLast(vertex);
			vertex.setPosInNodes(vertices.last());
		} catch (EmptyListException e) {
			e.printStackTrace();
		}
		return vertex;
	}

	@Override
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) throws InvalidVertexException {
		VerticeM<V> vertexV = checkVertex(v);
		VerticeM<V> vertexW = checkVertex(w);
		int f = vertexV.getIndex();
	int c = vertexW.getIndex();
		ArcoM<V, E> edge = new ArcoM<V, E>(e, vertexV, vertexW);
		try {
			if (cantVertices == matriz.length - 5)
				redimensionar();

			matriz[f][c] = edge;

			arcos.addLast(edge);

			edge.setPosInEdges(arcos.last());
		} catch (EmptyListException e1) {
			e1.printStackTrace();
		}

		return edge;
	}

	private void redimensionar() {
		ArcoM<V, E>[][] matriz_anterior = matriz;
		matriz = new ArcoM[matriz_anterior.length * 2][matriz_anterior[0].length * 2];

		for (int fila = 0; fila < matriz_anterior.length; fila++)
			for (int col = 0; col < matriz_anterior[0].length; col++)
				if (matriz_anterior[fila][col] != null)
					matriz[fila][col] = matriz_anterior[fila][col];
	}

	@Override
	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		V retorno = null;
		try {
			VerticeM<V> vertice = checkVertex(v);
			int c = vertice.getIndex();
			retorno = vertice.element();
			for (int f = 0; f < matriz.length; f++) {
				if (matriz[f][c] != null)
					removeEdge(matriz[f][c]);
				if (matriz[c][f] != null)
					removeEdge(matriz[c][f]);
			}

			vertices.remove(vertice.getPosInNodes());
		} catch (InvalidPositionException | InvalidEdgeException e) {
			e.printStackTrace();
		}
		return retorno;
	}
	@Override
	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
	ArcoM<V, E> edge = checkEdges(e);
	E elemRetorno = edge.element();
		try {
			int f = edge.getV1().getIndex();
			int c = edge.getV2().getIndex();

			matriz[f][c] = null;

			arcos.remove(edge.getPosInEdges());

		} catch (InvalidPositionException e1) {
			e1.printStackTrace();
		}

		return elemRetorno;
	}

	@SuppressWarnings("unchecked")
	private ArcoM<V, E> checkEdges(Edge<E> e) throws InvalidEdgeException {
		if (e == null)
			throw new InvalidEdgeException("El arco es invalido");
		return (ArcoM<V, E>) e;
	}

	private VerticeM<V> checkVertex(Vertex<V> v) throws InvalidVertexException {
		if (v == null)
			throw new InvalidVertexException("El vertice es invalido.");
		return (VerticeM<V>) v;
	}
*/
