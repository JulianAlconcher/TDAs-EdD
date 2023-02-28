package EjerciciosVarios;

import TDAGrafoAndandoCorrectamente.Grafo_matriz_adyacencia;
import TDAGrafoAndandoCorrectamente.Edge;
import TDALista.Position;
import TDALista.PositionList;
import TDAGrafoAndandoCorrectamente.Vertex;
public class TESTER {

	public static void main (String args[]) {
		Grafo_matriz_adyacencia<Character,Integer> grafo = new Grafo_matriz_adyacencia<Character,Integer>(8);
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
//			grafo.insertEdge(a, g, 5);
			grafo.insertEdge(e, h, 9);
			grafo.insertEdge(a, e, 4);
			grafo.insertEdge(f, h, 6);
			grafo.insertEdge(c, g, 8);

		} catch (TDAGrafoAndandoCorrectamente.InvalidVertexException e1) {e1.printStackTrace();
		}

		
		System.out.println("GRAFO CARGADO CON EXITO");
		 PositionList<Vertex<Character>> lista = grafo.HallarCaminoMinimo(grafo, a, g);
		 
		System.out.println("METODO EJECUTADO CON EXITO");
		
		for(Position<Vertex<Character>> x : lista.positions())
			System.out.println(x.element().element() + " -");
		
		System.out.println("IMPRESION EJECUTADO CON EXITO");
	}
}
