package EjerciciosVarios;

import Exceptions.InvalidKeyException;
import TDAGrafoAndandoCorrectamente.Edge;
import TDAGrafoAndandoCorrectamente.GrafoD_lista_adyacencia;
import TDAGrafoAndandoCorrectamente.Grafo_lista_adyacencia;
import TDAGrafoAndandoCorrectamente.Grafo_matriz_adyacencia;
import TDAGrafoAndandoCorrectamente.Graph;
import TDAGrafoAndandoCorrectamente.GraphD;
import TDAGrafoAndandoCorrectamente.InvalidEdgeException;
import TDAGrafoAndandoCorrectamente.InvalidVertexException;
import TDAGrafoAndandoCorrectamente.Vertex;
import TDALista.ListaDoblementeEnlazada2022;
import TDALista.Position;
import TDALista.PositionList;
import TDAMapeo.Entry;
import TDAMapeo.Map;
import TDAMapeo.MapeoConHashAbierto2022;

/*
 * Escriba un metodo que recibe un grafo dirigido G y un entero K, 
 * cuyos rotulos de vertice son de tipo generico y los arcos contienen con pesos de tipo generico.
 * El metodo debe construir un mapeo M de tal forma que para cada vertice V de G, 
 * el mapeo M retorna como imagen de V al conjunto de los vertices W alcanzables desde V 
 * mediante un recorrido DFS de tal forma que W tiene grado exactamente igual K.
 * 
 */
public class ejercicioFRAN {
	
	public static void main(String args[]) {
		GraphD<Character,Integer> grafo = new GrafoD_lista_adyacencia<Character,Integer>();
		Vertex<Character> a = grafo.insertVertex('A');
		Vertex<Character> b = grafo.insertVertex('B');
		Vertex<Character> c = grafo.insertVertex('C');
		Vertex<Character> d = grafo.insertVertex('D');
		Vertex<Character> e = grafo.insertVertex('E');
		Vertex<Character> f = grafo.insertVertex('F');
		Vertex<Character> g = grafo.insertVertex('G');
		Vertex<Character> h = grafo.insertVertex('H');


			try {
				grafo.insertEdge(a, b, 1);
				grafo.insertEdge(b, c, 2);
				grafo.insertEdge(d, c, 3);
				grafo.insertEdge(d, f, 8);
				grafo.insertEdge(f, g, 10);
				grafo.insertEdge(e, h, 9);
				grafo.insertEdge(a, e, 4);
				grafo.insertEdge(f, h, 6);
				grafo.insertEdge(c, g, 8);
			} catch (InvalidVertexException e1) {e1.printStackTrace();
			}

		Map<Vertex<Character>,PositionList<Vertex<Character>>> m = construirMetodo(grafo,2);
		System.out.println(m.size());
		for(Entry<Vertex<Character>,PositionList<Vertex<Character>>> en : m.entries()) {
			System.out.print("NODO: " + en.getKey().element() + " --> ");
			for(Position<Vertex<Character>> ent : en.getValue().positions()) {
				System.out.print(ent.element().element() + ", ");
			}
			System.out.println("");
		}
	}
	
	public static <V,E> Map<Vertex<V>,PositionList<Vertex<V>>> construirMetodo(GraphD<V,E> g,int K){
		Map<Vertex<V>,PositionList<Vertex<V>>> mapeo = new MapeoConHashAbierto2022<Vertex<V>,PositionList<Vertex<V>>>();
		for(Vertex<V> v:g.vertices())
			v.setEstado(false);
		for(Vertex<V> v:g.vertices()) {
			if(v.getEstado()==false)
				DFS(g,v,mapeo,K);
		}
		return mapeo;
	}
	
	private static <V,E> void DFS(GraphD<V,E> g,Vertex<V> v,Map<Vertex<V>,PositionList<Vertex<V>>> m,int K) {
		int cantidadAdyacentes = 0;
		PositionList<Vertex<V>> listaDeAdyacentes = new ListaDoblementeEnlazada2022<Vertex<V>>();
		v.setEstado(true);
		try {
			for(Edge<E> a : g.succesorEdges(v)) {
				Vertex<V> x = g.opposite(v, a);
				listaDeAdyacentes.addLast(x);
				cantidadAdyacentes++;
				if(v.getEstado()==false) 
					DFS(g,x,m,K);
			}
			if(cantidadAdyacentes==K) {
                m.put(v, listaDeAdyacentes);
                cantidadAdyacentes = 0;
             }
			
		} catch (InvalidVertexException | InvalidEdgeException | InvalidKeyException e) {e.printStackTrace();
		}
	}
}
