package TDAGrafo;

import Exceptions.*;
import TDALista.*;
import TDAMapeo.Map;
import TDAMapeo.MapeoConHashAbierto2022;

public class GrafoMatrizDeAdyacencia<V,E> implements Graph<V,E> {

	protected PositionList<Vertex<V>> vertices;
	protected PositionList<Edge<E>> arcos;
	protected Edge<E> [][] matriz;
	protected int cant;
	
	/**
	 * Inicializa un nuevo grafo.
	 * @param n
	 */
	@SuppressWarnings("unchecked")
	public GrafoMatrizDeAdyacencia(int n) {
		vertices = new ListaDoblementeEnlazada2022<Vertex<V>>();
		arcos = new ListaDoblementeEnlazada2022<Edge<E>>();
		matriz = (Edge<E>[][]) new ArcoM[n][n];
		cant = 0;
	}
	
	@Override
	public Iterable<Vertex<V>> vertices() {
		PositionList<Vertex<V>> aRetornar = new ListaDoblementeEnlazada2022<Vertex<V>>();
		for(Vertex<V> v : vertices)
			aRetornar.addLast(v);
		return aRetornar;
	}

	@Override
	public Iterable<Edge<E>> edges() {
		PositionList<Edge<E>> aRetornar = new ListaDoblementeEnlazada2022<Edge<E>>();
		for(Edge<E> e : arcos)
			aRetornar.addLast(e);
		return aRetornar;
	}

	@Override
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
		VerticeM<V> n = checkVertex(v);
		PositionList<Edge<E>> toReturn = new ListaDoblementeEnlazada2022<Edge<E>>();
		for(int i=0; i<matriz[0].length; i++) {
			if(matriz[i][n.getIndex()]!=null)
				toReturn.addLast(matriz[i][n.getIndex()]);
		}
		return toReturn;
	}

	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {
		VerticeM<V> n = checkVertex(v);
		ArcoM<V,E> a = checkEdge(e);
		Vertex<V> toReturn = null;
		if(a.getV1().equals(n))
			toReturn = (Vertex<V>) a.getV2();
		else if(a.getV2().equals(n))
			toReturn = (Vertex<V>) a.getV1();
		return toReturn;
	}

	@Override
	public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException {
		@SuppressWarnings("unchecked")
		Vertex<V>[] toReturn = (Vertex<V>[]) new Vertex[2];
		ArcoM<V,E> a = checkEdge(e);
		
		toReturn[0] = a.getV1();
		toReturn[1] = a.getV2();
		
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
		V toReturn = v.element();
		VerticeM<V> v1 = checkVertex(v);
		v1.setElement(x);
		
		return toReturn;
	}

	@Override
	public Vertex<V> insertVertex(V x) {
		VerticeM<V> nuevo = new VerticeM<V>(x,cant++);
		if(cant==matriz[0].length)
			resize();
		
		vertices.addLast(nuevo);
		try {
			nuevo.setPosInNodes(vertices.last());
		} catch (EmptyListException e) {e.printStackTrace();}
		return nuevo;
	}

	public void resize() {
	}
	
	
	@Override
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) throws InvalidVertexException {
		VerticeM<V> v1 = checkVertex(v);
		VerticeM<V> v2 = checkVertex(w);
		ArcoM<V,E> nuevo = new ArcoM<V,E>(e,v2,v1);
		arcos.addLast(nuevo);
		try {
			nuevo.setPosInEdges(arcos.last());
		} catch (EmptyListException e1) {e1.printStackTrace();
		matriz[v1.getIndex()][v2.getIndex()] = nuevo;
		matriz[v2.getIndex()][v1.getIndex()] = nuevo;
		}
		return nuevo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		VerticeM<V> v1 = checkVertex(v);
		ArcoM<V,E> aux = null;
		try {
		for(int i=0; i<matriz.length; i++) {
			if(matriz[i][v1.getIndex()] !=null) {
				aux = (ArcoM<V, E>) matriz[i][v1.getIndex()];
				arcos.remove(aux.getPosInEdges());
				matriz[i][v1.getIndex()] = null;
				matriz[v1.getIndex()][i] = null;
			}
		}
		} catch (InvalidPositionException e1) {e1.printStackTrace();}
		return (V) aux.element();
	}

	@Override
	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
		ArcoM<V,E> arc = checkEdge(e);
		E toReturn = arc.element;
		VerticeM<V> v1 =arc.getV1();
		VerticeM<V> v2 = arc.getV2();
		matriz[v1.getIndex()][v2.getIndex()] = null;
		matriz[v2.getIndex()][v1.getIndex()] = null;
		try {
			arcos.remove(arc.getPosInEdges());
		} catch (InvalidPositionException e1) {e1.printStackTrace();}
		return toReturn;
	}
	/**
	 * Chequea que el vertice pasado por parametro sea valido.
	 * @param v
	 * @return
	 * @throws InvalidVertexException si el vertice es invalido.
	 */
	private VerticeM<V> checkVertex(Vertex<V> v) throws InvalidVertexException{
		VerticeM<V> toReturn = null;
		if(v==null || v.element()==null)
			throw new InvalidVertexException("El vertice no es valido");
		try {
			toReturn = (VerticeM<V>) v;
		}catch(ClassCastException e) {e.getMessage();}
		
		return toReturn;
	}
	
	public int costoDelCaminoEntreV1YV2(Vertex<V> v, Vertex<V> w) {
		int costo = 0;
		DFSShell();
		return costo;
		
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
			VerticeM<V> ver = checkVertex(vert);
			m.put(vert,true);
			for(int i=0; i<cant; i++) {
				if(matriz[i][ver.getIndex()] != null) {
					Vertex<V> w = opposite(vert, matriz[i][ver.getIndex()]);
					if (m.get(w) == false)
						DFS(w, m);
				}
			}
		} catch (InvalidVertexException | InvalidEdgeException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e1) {e1.printStackTrace();}

	}

	/**
	 * Chequea que el arco pasado por parametro sea valido.
	 * @param e
	 * @return
	 * @throws InvalidEdgeException si el arco es invalido.
	 */
	@SuppressWarnings("unchecked")
	private ArcoM<V,E> checkEdge(Edge<E> e) throws InvalidEdgeException{
		ArcoM<V,E> toReturn = null;
		if(e==null || e.element()==null)
			throw new InvalidEdgeException("El vertice no es valido");
		try {
			toReturn = (ArcoM<V,E>) e;
		}catch(ClassCastException f) {f.getMessage();}
		
		return toReturn;
	}
}
