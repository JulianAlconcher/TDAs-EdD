package EjerciciosVarios;

import Exceptions.InvalidKeyException;
import TDAGrafoAndandoCorrectamente.Edge;
import TDAGrafoAndandoCorrectamente.GrafoD_lista_adyacencia;
import TDAGrafoAndandoCorrectamente.GraphD;
import TDAGrafoAndandoCorrectamente.InvalidEdgeException;
import TDAGrafoAndandoCorrectamente.InvalidVertexException;
import TDAGrafoAndandoCorrectamente.Vertex;
import TDAMapeo.Entry;
import TDAMapeo.Map;
import TDAMapeo.MapeoConHashAbierto2022;

public class ejercicioGrafoFinal2023 {
	
	public static void main(String args[]) {
		GraphD<Character,Float> g = new GrafoD_lista_adyacencia<Character,Float>();
		Vertex<Character> vA = g.insertVertex('A');
		Vertex<Character> vB = g.insertVertex('B');
		Vertex<Character> vC = g.insertVertex('C');
		Vertex<Character> vD = g.insertVertex('D');
		Vertex<Character> vE = g.insertVertex('E');
		Vertex<Character> vF = g.insertVertex('F');
		Vertex<Character> vG = g.insertVertex('G');
		
		try {
			g.insertEdge(vC, vA, 1f);
			g.insertEdge(vC, vB, 2f);
			g.insertEdge(vB, vE, 4.3f);
			g.insertEdge(vE, vC, 8.2f);
			g.insertEdge(vB, vD, 3f);
			g.insertEdge(vB, vF, 2f);
			g.insertEdge(vF, vG, 3.0f);
			g.insertEdge(vG, vF, 4.2f);
			g.insertEdge(vD, vF, 8.9f);
			g.insertEdge(vD, vG, 10.1f);
		} catch (InvalidVertexException e) {e.printStackTrace();
		}
		Map<Vertex<Character>,Float> mapeo = metodo(g,vB);
		for(Entry<Vertex<Character>, Float> e : mapeo.entries()) {
			System.out.println(e.getKey().element() +" " + e.getValue());
		}
	}

	
	public static <V> Map<Vertex<V>,Float> metodo(GraphD<V,Float> g, Vertex<V> v){
		Map<Vertex<V>,Float> mapeo = new MapeoConHashAbierto2022<Vertex<V>,Float>();
		for(Vertex<V> ver : g.vertices()) {ver.setEstado(false);}
		DFS(g,v,mapeo);
		return mapeo;
	}
	private static <V,E> void DFS(GraphD<V,Float> g, Vertex<V> v,Map<Vertex<V>,Float> mapeo) {
		v.setEstado(true);
		float suma = 0;
		try {
			for(Edge<Float> a : g.succesorEdges(v)) {
				suma += a.element();
				Vertex<V> x = g.opposite(v, a);
				if(x.getEstado()==false) { 
					DFS(g,x,mapeo);	
				}
				
				mapeo.put(x, suma);
			}
		} catch (InvalidVertexException | InvalidEdgeException | InvalidKeyException e) {e.printStackTrace();
		}
	}
	
}
