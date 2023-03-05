package EjerciciosVarios;

import TDAGrafoAndandoCorrectamente.Grafo_matriz_adyacencia;
import TDAGrafoAndandoCorrectamente.Graph;
import TDAGrafoAndandoCorrectamente.InvalidEdgeException;
import TDAGrafoAndandoCorrectamente.InvalidVertexException;
import TDAGrafoAndandoCorrectamente.Edge;
import TDALista.Position;
import TDALista.PositionList;
import TDAGrafoAndandoCorrectamente.Vertex;
public class TESTER {

	public static void main (String args[]) {
		Grafo_matriz_adyacencia<Character,Integer> grafo = new Grafo_matriz_adyacencia<Character,Integer>(8);
		
		Vertex<Character> b = grafo.insertVertex('B');
		Vertex<Character> a = grafo.insertVertex('A');
		Vertex<Character> c = grafo.insertVertex('C');
		Vertex<Character> d = grafo.insertVertex('D');
		Vertex<Character> e = grafo.insertVertex('E');
		Vertex<Character> f = grafo.insertVertex('F');
		Vertex<Character> g = grafo.insertVertex('G');
		Vertex<Character> h = grafo.insertVertex('H');

		try {
			//GRAFO NO CONEXO
			grafo.insertEdge(a, b, 1);
			grafo.insertEdge(b, c, 2);
//			grafo.insertEdge(d, c, 3);
			grafo.insertEdge(d, f, 8);
//			grafo.insertEdge(f, g, 10);
			grafo.insertEdge(a, g, 5);
			grafo.insertEdge(e, h, 9);
			grafo.insertEdge(a, e, 4);
//			grafo.insertEdge(f, h, 6);
			grafo.insertEdge(c, g, 8);
			
			//GRAFO CONEXO
//			grafo.insertEdge(a, b, 1);
//			grafo.insertEdge(b, c, 2);
//			grafo.insertEdge(d, c, 3);
//			grafo.insertEdge(d, f, 8);
//			grafo.insertEdge(f, g, 10);
//			grafo.insertEdge(a, g, 5);
//			grafo.insertEdge(e, h, 9);
//			grafo.insertEdge(a, e, 4);
//			grafo.insertEdge(f, h, 6);
//			grafo.insertEdge(c, g, 8);

		} catch (TDAGrafoAndandoCorrectamente.InvalidVertexException e1) {e1.printStackTrace();
		}

		
		System.out.println("GRAFO CARGADO CON EXITO");
////		 PositionList<Vertex<Character>> lista = grafo.HallarCaminoMinimo(grafo, a, g);
//		 
//		System.out.println("METODO EJECUTADO CON EXITO");
//		
//		for(Position<Vertex<Character>> x : lista.positions())
//			System.out.println(x.element().element() + " -");
		
		System.out.println("El grafo es conexo ?: --> " + esConexo(grafo));
		
//		System.out.println("IMPRESION EJECUTADO CON EXITO");
	}
	
	public static <V,E> boolean esConexo(Graph<V,E> g) {
		for(Vertex<V> v : g.vertices()) { v.setEstado(false);}
		
		Vertex<V> aux = g.vertices().iterator().next();
		System.out.println("Empezamos por " + aux.element() + " " );
		DFS(g,aux);
		
		
		for(Vertex<V> v : g.vertices()) {
			System.out.println("testeo a " + v.element() + " " + v.getEstado());
			if(v.getEstado()==false) {
				return false;
			}
		}
		return true;
	}
	
	private static <V,E> void DFS(Graph<V,E> g, Vertex<V> v) {
		v.setEstado(true);
		try {
			for(Edge<E> a : g.incidentEdges(v)) {
				Vertex<V> x = g.opposite(v, a);
				System.out.println("visito " + x.element());
				if(x.getEstado()==false)
					DFS(g,x);
			}
		} catch (InvalidVertexException | InvalidEdgeException e) {e.printStackTrace();}
	}
}
