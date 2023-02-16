package EjerciciosVarios;

import Exceptions.InvalidEdgeException;
import Exceptions.InvalidKeyException;
import Exceptions.InvalidVertexException;
import TDADiccionario.DiccionarioConHashAbierto2022;
import TDADiccionario.Dictionary;
import TDADiccionario.Entry;
import TDAGrafo.Edge;
import TDAGrafo.Graph;
import TDAGrafo.GraphD;
import TDAGrafo.Vertex;
import TDAGrafoAndandoCorrectamente.GrafoD_lista_adyacencia;
import TDAMapeo.Map;
import TDAMapeo.MapeoConHashAbierto2022;

/**
 * Escriba un metodo que reciba un grafo dirigido G y retorne un dicccionario. El diccionario
 * debe para cada vertice v del grafo, devolver las hojas alcanzables de v. Una hoja en un grafo dirigido es un vertice sin adyacentes. 
 * Use solamente los TDAs de la teoriia y programe cualquier metodo aux. Se asumen los TDAs implementados. 
 * 
 * Def: Vértices adyacentes: un vértice v2 es adyacente a otro vértice v1 si existe un arco a1 que tiene por origen a v1 y por destino v2.
 *
 */
public class ej1FinalMarzoFran {
	
	public static void main(String args[]){
		GraphD<Integer, String> grafo = (GraphD<Integer, String>) new GrafoD_lista_adyacencia<Integer, String>();

		Vertex<Integer> v1 = grafo.insertVertex(1);
		Vertex<Integer> v2 = grafo.insertVertex(2);
		Vertex<Integer> v3 = grafo.insertVertex(3);
		Vertex<Integer> v4 = grafo.insertVertex(4);
		Vertex<Integer> v5 = grafo.insertVertex(5);
		Vertex<Integer> v6 = grafo.insertVertex(6);
		Vertex<Integer> v7 = grafo.insertVertex(7);
		
		// uno los vertices los arcos
		try {
			grafo.insertEdge(v1, v2, "a");
			grafo.insertEdge(v2, v3, "b");
			grafo.insertEdge(v3, v4, "g");
			grafo.insertEdge(v1, v4, "f");
			grafo.insertEdge(v3, v6, "d");
			grafo.insertEdge(v3, v5, "c");
			grafo.insertEdge(v7, v2, "h");
		} catch (InvalidVertexException e) {e.printStackTrace();}
		System.out.println("Imprimo Grafo completo");
		for(Vertex<Integer> v: grafo.vertices() ) {
			System.out.println("--> " + v.element());
		}
		
		System.out.println("Ejecutamos metodo...");
		
		Dictionary<Vertex<Integer>,Vertex<Integer>> d = metodo(grafo);
		System.out.println("El tamanio del diccionario es: " + d.size());
		for(Entry<Vertex<Integer>, Vertex<Integer>> e : d.entries()) {
			System.out.println(e);
		}
	}
		
	/**
	 * Escriba un metodo que reciba un grafo dirigido G y retorne un dicccionario. 
	 * El diccionario debe para cada vertice v del grafo, devolver las hojas alcanzables de v. Una hoja en un grafo dirigido es un vertice sin adyacentes. 
	 * Use solamente los TDAs de la teoriia y programe cualquier metodo aux. Se asumen los TDAs implementados. 
	 * 
	 * El diccionario tendria que devolver: (2,1),(4,1),(4,7)
	 * 
	 * Def: Vértices adyacentes: un vértice v2 es adyacente a otro vértice v1 si existe un arco a1 que tiene por origen a v1 y por destino v2.
	 *
	 */
	
	//Succesor/Emergente: Flechas que salen del nodo 
	public static <V,E> Dictionary<Vertex<V>,Vertex<V>> metodo(GraphD<V,E> g){
		Dictionary<Vertex<V>,Vertex<V>> retorno = new DiccionarioConHashAbierto2022<Vertex<V>,Vertex<V>>();
		for(Vertex<V> v: g.vertices()) {
			try {
				for(Edge<E> v1: g.succesorEdges(v)) { 
					System.out.println("Recorro arco de mi vertice");
					Vertex<V> x = g.opposite(v, v1);
					if(esHoja(g,x)) {
						retorno.insert(v, x);
					}
				}
			} catch (InvalidVertexException | InvalidEdgeException | InvalidKeyException e) {e.printStackTrace();
			}
		}
		
		return retorno;
	}
	
	public static <V,E> boolean esHoja(GraphD<V,E> g, Vertex<V> v) {
		System.out.println("Entro a esHoja");
		boolean retorno = true;
		int contador = 0;
		try {
			for(Edge<E> v1:g.succesorEdges(v)) {
				contador++;
			}
			if(contador!=0)
				retorno = false;
		} catch (InvalidVertexException e) {e.printStackTrace();
		}
		return retorno;
	}
	
	/**
	 * Escribir un metodo que recibe un grafo dirigido G y un entero K, cuyos rotulos de vertice son de tipo generico y los arcos contienen con pesos de tipo generico.
	 * 
	 * El metodo debe construir un mapeo M de tal forma que para cada vertie v de G, el mapeo M retorna como imagen de V al conjunto de los vertices W alcanzables desde V mediante un
	 * recorrido en profundidad (DFS) pero tal que W tiene grado exactamente igual a K. 
	 */
	
	public <V,E> Map<V,E> metodo2(Graph<V,E> g, int k) {
		Map<V,E> mapeo = new MapeoConHashAbierto2022<V,E>();
		
		
		return mapeo;
	}
	
	/**
	 * Escriba un metodo que reciba un grafo dirigido G y un entero K, cuyos rotulos de vertice son de tipo generico y los arcos contienen con pesos de tipo generico.
	 * El metodo debe construir un mapeo M de tal forma que para cada vertice V de G, el mapeo M retorna como imagen de V al conjunto de los vertices W alcanzables desde V
	 * mediante un recorrido DFS pero tal que W tiene grado exactamente igual a K. Resuelvo el problema exclusivamente en terminos de los TDAs dados en la teoria. 
	 */
	
	

}
