package TDAGrafoAndandoCorrectamente;



import Exceptions.EmptyListException;
import Exceptions.InvalidKeyException;
import Exceptions.InvalidPositionException;
import TDALista.*;
import TDAMapeo.Map;
import TDAMapeo.MapeoConHashAbierto2022;

public class Grafo_matriz_adyacencia<V,E> implements Graph<V,E> {
	
	protected PositionList<Vertex<V>> vertices;
	protected PositionList<Edge<E>> arcos;
	protected Edge<E> [][] matriz;
	protected int cantidadVertices;
	
	
	@SuppressWarnings("unchecked")
	public Grafo_matriz_adyacencia( int n ){ // Recibe el tamaño de la matriz
		vertices = new ListaDoblementeEnlazada2022<Vertex<V>>();
		arcos = new ListaDoblementeEnlazada2022<Edge<E>>();
		matriz = (Edge<E> [][]) new ArcoM[n][n];
		cantidadVertices = 0;
	}
	
	public Iterable<Vertex<V>> vertices() {
		PositionList<Vertex<V>> lista = new ListaDoblementeEnlazada2022<Vertex<V>>();
		for (Vertex<V> v : vertices) {
			lista.addLast(v);
		}

		return lista;
	}

	public Iterable<Edge<E>> edges() {
		PositionList<Edge<E>> lista = new ListaDoblementeEnlazada2022<Edge<E>>();
		for (Edge<E> e : arcos) {
			lista.addLast(e);
		}

		return lista;
	}

	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {

		if (v == null)
			throw new InvalidVertexException("Vertice invalido");
		VerticeM<V> vv = (VerticeM<V>) v;
		int fila = vv.getIndice();
		PositionList<Edge<E>> lista = new ListaDoblementeEnlazada2022<Edge<E>>();
		for (int col = 0; col < cantidadVertices; col++)
			if (matriz[fila][col] != null)
				lista.addLast(matriz[fila][col]);

		return lista;
	}

	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {

		if (v == null)
			throw new InvalidVertexException("Vertice invalido");
		@SuppressWarnings("unused")
		VerticeM<V> vv = (VerticeM<V>) v;
		if (e == null)
			throw new InvalidEdgeException("Arco invalido");
		@SuppressWarnings("unchecked")
		ArcoM<V, E> ee = (ArcoM<V, E>) e;
		Vertex<V> salida = null;
		if (ee.getV1() == v)
			salida = ee.getV2();
		else {
			if (ee.getV2() == v)
				salida = ee.getV1();
			else
				throw new InvalidEdgeException("Ninguno de los extremos coincide con el vertice");
		}
		return salida;
	}

	@SuppressWarnings("unchecked")
	public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException {
		if (e == null)
			throw new InvalidEdgeException("Arco invalido");
		Vertex<V>[] salida = (VerticeM<V>[]) new VerticeM[2];
		ArcoM<V, E> ee = (ArcoM<V, E>) e;
		salida[0] = ee.getV1();
		salida[1] = ee.getV2();

		return salida;
	}

	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException {

		if (v == null || w == null)
			throw new InvalidVertexException("Vertice invalido");
		VerticeM<V> vv = (VerticeM<V>) v;
		VerticeM<V> ww = (VerticeM<V>) w;
		int i = vv.getIndice();
		int j = ww.getIndice();

		return matriz[i][j] != null;
	}

	public V replace(Vertex<V> v, V x) throws InvalidVertexException {
		if (v == null)
			throw new InvalidVertexException("Vertice invalido");

		VerticeM<V> vv = (VerticeM<V>) v;
		V removed = v.element();
		vv.setRotulo(x);

		return removed;
	}

	public Vertex<V> insertVertex(V x) {
		VerticeM<V> vv = new VerticeM<V>(x, cantidadVertices++);
		vertices.addLast(vv);
		try {
			vv.setPosicionEnVertices(vertices.last());

		} catch (EmptyListException e) {
			System.out.println(e.getMessage());
		}

		return vv;
	}

	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) throws InvalidVertexException {

		if (v == null || w == null)
			throw new InvalidVertexException("Vertice invalido");
		VerticeM<V> vv = (VerticeM<V>) v;
		VerticeM<V> ww = (VerticeM<V>) w;
		int fila = vv.getIndice();
		int col = ww.getIndice();
		ArcoM<V, E> arco = new ArcoM<V, E>(e, vv, ww);
		matriz[fila][col] = matriz[col][fila] = arco;
		arcos.addLast(arco);
		try {
			arco.setPosicionEnArcos(arcos.last());
		} catch (EmptyListException exc) {
			System.out.println(exc.getMessage());
		}

		return arco;
	}

	@SuppressWarnings("unchecked")
	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		if (v == null)
			throw new InvalidVertexException("Vertice invalido");
		VerticeM<V> vv = (VerticeM<V>) v;
		V removed = v.element();
		int fila = vv.getIndice();
		try {
			for (int col = 0; col < cantidadVertices; col++) {
				if (matriz[fila][col] != null) {
					ArcoM<V, E> arc = (ArcoM<V, E>) matriz[fila][col];
					arcos.remove(arc.getPosicionEnArcos());
					matriz[fila][col] = matriz[col][fila] = null;
				}
			}

			vertices.remove(vv.getPosicionEnVertices());
			cantidadVertices--;

		} catch (InvalidPositionException e) {
			System.out.println(e.getMessage());
		}

		return removed;
	}

	@SuppressWarnings("unchecked")
	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
		if (e == null)
			throw new InvalidEdgeException("Arco invalido");

		ArcoM<V, E> ee = (ArcoM<V, E>) e;
		E removed = e.element();
		int fila = ee.getV1().getIndice();
		int col = ee.getV2().getIndice();
		matriz[fila][col] = matriz[col][fila] = null;
		try {
			arcos.remove(ee.getPosicionEnArcos());
			ee.setRotulo(null);
			ee.setPosicionEnArcos(null);
			ee.setV1(null);
			ee.setV2(null);
		} catch (InvalidPositionException exc) {
			System.out.println(exc.getMessage());
		}

		return removed;
	}
	
	public PositionList<Vertex<V>> HallarCaminoMinimo(Graph<V,E> g,Vertex<V> origen,Vertex<V> destino){
		PositionList<Vertex<V>> caminoActual = new ListaDoblementeEnlazada2022<Vertex<V>>();
		PositionList<Vertex<V>> caminoMinimo = new ListaDoblementeEnlazada2022<Vertex<V>>();
		for(Vertex<V> v : g.vertices()) {
			v.setEstado(false);
			caminoMinimo.addLast(v);
		}
		try {
			HallarCaminoMinimoRec(g,origen,destino,caminoActual,caminoMinimo);
		} catch (InvalidPositionException | EmptyListException e) {e.printStackTrace();
		}
		return caminoMinimo;
	}
	
	private void HallarCaminoMinimoRec(Graph<V,E> g,Vertex<V> origen,Vertex<V> destino,PositionList<Vertex<V>> cA,PositionList<Vertex<V>> cM) throws InvalidPositionException, EmptyListException {
		origen.setEstado(true);
		cA.addLast(origen);
		if(origen.equals(destino)) {
			if(cA.size() < cM.size()) {
				System.out.println("HAGO SWAP");
				cA.clonar(cM);
			}
		}
		else {
			try {

				for(Edge<E> a : g.incidentEdges(origen)) {
					Vertex<V> x = g.opposite(origen, a);
					if(x.getEstado()==false)
						HallarCaminoMinimoRec(g,x,destino,cA,cM);
				}
			} catch (InvalidVertexException | InvalidEdgeException e) {e.printStackTrace();
			}
		}
		try {
			cA.remove(cA.last());
			origen.setEstado(false);
		} catch (InvalidPositionException | EmptyListException e) {e.printStackTrace();
		}

	}
	

	
	/*
	 * 		d)Agregue una operación a la clase Grafo<V,E> definida en (c) que dado un vértice v1 haga un recorrido DFS (
			Depth First Search – Búsqueda en profundidad ) comenzando en el vértice v1 y retorne un mapeo cuyas claves
			serán los rótulos de los vértices del grafo y el valor asociado será la cantidad de arcos adyacentes a cada vértice.
			
			En caso que el grafo tenga rótulos repetidos, debe almacenar en el mapeo únicamente aquel vértice que tenga
			mayor cantidad de arcos adyacentes. Resolver el problema en Java, recuerde que esta agregando un método a la
			clase Grafo, por lo tanto tiene total acceso a su estructura.
	 */
	
	public Map<V,Integer> metodoD(Vertex<V> v1){
		Map<V,Integer> mapeo = new MapeoConHashAbierto2022<V,Integer>();
		for(Vertex<V> v : vertices) {v.setEstado(false);}
		DFS(v1,mapeo);
		return mapeo;
	}
	
	private void DFS(Vertex<V> v1,Map<V,Integer> m) {
		v1.setEstado(true);
		int cantAdy = 0;
		try {
				
			for(Edge<E> arc : incidentEdges(v1)) {
				cantAdy++;
				Vertex<V> x = opposite(v1,arc);
				if(x.getEstado()==false)
					DFS(x,m);
			}
			
			if(m.get(v1.element())==null)
				m.put(v1.element(), cantAdy);
			else {
				Integer aux = m.get(v1.element());
				if(cantAdy>aux)
					m.put(v1.element(), cantAdy);
			}
		
		} catch (InvalidVertexException | InvalidEdgeException | InvalidKeyException e) {e.printStackTrace();}
	}
	

}