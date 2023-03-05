package EjerciciosVarios;

import Exceptions.EmptyListException;
import Exceptions.EmptyQueueException;
import Exceptions.InvalidKeyException;
import Exceptions.InvalidPositionException;
import TDACola.ColaEnlazada;
import TDACola.Queue;
import TDAGrafoAndandoCorrectamente.Edge;
import TDAGrafoAndandoCorrectamente.GrafoD_lista_adyacencia;
import TDAGrafoAndandoCorrectamente.Graph;
import TDAGrafoAndandoCorrectamente.GraphD;
import TDAGrafoAndandoCorrectamente.InvalidEdgeException;
import TDAGrafoAndandoCorrectamente.InvalidVertexException;
import TDAGrafoAndandoCorrectamente.Solucion;
import TDAGrafoAndandoCorrectamente.Vertex;
import TDALista.ListaDoblementeEnlazada2022;
import TDALista.Position;
import TDALista.PositionList;
import TDAMapeo.Entry;
import TDAMapeo.Map;
import TDAMapeo.MapeoConHashAbierto2022;

public class ejercicios_IMPORTANTES_DE_GRAFOS {
	
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
			g.insertEdge(vF, vB, 10f);
			g.insertEdge(vF, vG, 3.0f);
			g.insertEdge(vG, vF, 4.2f);
			g.insertEdge(vD, vF, 8.9f);
			g.insertEdge(vD, vG, 10.1f);
			g.insertEdge(vG, vE, 100f);
		} catch (InvalidVertexException e) {e.printStackTrace();
		}
	
//		Map<Vertex<Character>,Float> mapeo = metodo(g,vB);
//		for(Entry<Vertex<Character>, Float> e : mapeo.entries()) {
//			System.out.println(e.getKey().element() +" " + e.getValue());
//		}
		
		PositionList<Vertex<Character>> caminoMinimo = HallarCaminoCostoMinimoConClaseSol(g, vG, vC);
		for(Position<Vertex<Character>> e : caminoMinimo.positions()) {
			System.out.print(e.element().element() +" " );
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
	
	public static <V,E> PositionList<Vertex<V>> HallarCaminoCostoMinimo(GraphD<V,E> g,Vertex<V> origen,Vertex<V> destino){
		PositionList<Vertex<V>> caminoActual = new ListaDoblementeEnlazada2022<Vertex<V>>();
		PositionList<Vertex<V>> caminoMinimo = new ListaDoblementeEnlazada2022<Vertex<V>>();
		int costoActual = 0;
		int costoMinimo = Integer.MAX_VALUE;
		for(Vertex<V> v : g.vertices()) {
			v.setEstado(false);
			caminoMinimo.addLast(v);
		}
		try {
			HallarCaminoCostoMinimoRec(g,origen,destino,caminoActual,caminoMinimo,costoActual,costoMinimo);
		} catch (InvalidPositionException | EmptyListException e) {e.printStackTrace();
		}
		return caminoMinimo;
	}
	
	private static <V,E> void HallarCaminoCostoMinimoRec(GraphD<V,E> g,Vertex<V> origen,Vertex<V> destino,PositionList<Vertex<V>> cA,PositionList<Vertex<V>> cM,int cosA,int cosM) throws InvalidPositionException, EmptyListException {
		origen.setEstado(true);
		cA.addLast(origen);
		if(origen.equals(destino)) {
			if(cosA < cosM) {
				cosM = cosA;
				cA.clonar(cM);
			}
		}
		else {
			try {

				for(Edge<E> a : g.incidentEdges(origen)) {
					Vertex<V> x = g.opposite(origen,a);
					if(x.getEstado()==false) {
						HallarCaminoCostoMinimoRec(g,x,destino,cA,cM, cosA + a.getPeso(),cosM);}
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
	
	public static PositionList<Vertex<Character>> HallarCaminoCostoMinimoConClaseSol(GraphD<Character,Float> g,Vertex<Character> origen,Vertex<Character> destino){
		PositionList<Vertex<Character>> caminoActual = new ListaDoblementeEnlazada2022<Vertex<Character>>();
		Solucion<Vertex<Character>> sol = new Solucion<Vertex<Character>>(Float.MAX_VALUE);
		float costoActual = 0;
		for(Vertex<Character> v : g.vertices()) {
			v.setEstado(false);
			sol.getLista().addLast(v);
		}
		try {
			HallarCaminoCostoMinimoRecConClaseSol(g,origen,destino,caminoActual,costoActual,sol);
		} catch (InvalidPositionException | EmptyListException e) {e.printStackTrace();
		}

		return sol.getLista();
	}
	
	private static void HallarCaminoCostoMinimoRecConClaseSol(GraphD<Character,Float> g,Vertex<Character> origen,Vertex<Character> destino,PositionList<Vertex<Character>> cA,float cosA,Solucion<Vertex<Character>> s) throws InvalidPositionException, EmptyListException {
		origen.setEstado(true);
		cA.addLast(origen);
		if(origen.equals(destino)) {
			if(cosA < s.getCosto()) {
				s.setCosto(cosA);
				s.copy(cA);
			}
		}
		else {
			try {
				for(Edge<Float> a : g.succesorEdges(origen)) {
					Vertex<Character> x = g.opposite(origen,a);
					if(x.getEstado()==false) {
						HallarCaminoCostoMinimoRecConClaseSol(g,x,destino,cA, cosA + a.element(),s);}
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
	 * Inserta en una lista a todos los vertices con un recorrido BFS
	 */
	public <V,E> PositionList<Vertex<V>> listaConVerticesEnBFS(Graph<V,E> g){
		PositionList<Vertex<V>> lista = new ListaDoblementeEnlazada2022<Vertex<V>>();
		for(Vertex<V> v : g.vertices()) { v.setEstado(false);}
		for(Vertex<V> v : g.vertices()) {
			if(v.getEstado()==false)
				try {
					BFS(g,v,lista);
				} catch (EmptyQueueException | InvalidVertexException | InvalidEdgeException e) {e.printStackTrace();
				}
		}
		return lista;
	}
	
	private <V,E> void BFS(Graph<V,E> g,Vertex<V> v,PositionList<Vertex<V>> l) throws EmptyQueueException, InvalidVertexException, InvalidEdgeException {
		Queue<Vertex<V>> cola = new ColaEnlazada<Vertex<V>>();
		cola.enqueue(v);
		v.setEstado(true);
		while(!cola.isEmpty()) {
			Vertex<V> w = cola.dequeue();
			l.addLast(w);
			for(Edge<E> a : g.incidentEdges(w)) {
				Vertex<V> x = g.opposite(w, a);
				if(x.getEstado()==false) {
					x.setEstado(true);
					cola.enqueue(x);
				}
			}
			
		}
	}
	
//	 Dado un grafo G y un vertice v, retornar un mapeo donde las claves son los vertices
//   a distancia 2 de v y la clave es el costo del camino desde v a ese vertice 
//    
    
    public Map<Vertex<Character>, Float> distanciados(Graph<Character,Integer> g, Vertex<Character> v){
        Map<Vertex<Character>, Float> map= new MapeoConHashAbierto2022<Vertex<Character>,Float>();
        int dist=0;
        for(Vertex<Character> v1: g.vertices()) {
            v1.setEstado(false);
        }
        
        distanciadosDFS(g,v,map,dist);
        return map;
    }
    
    private void distanciadosDFS(Graph<Character,Integer> g, Vertex<Character> v,Map<Vertex<Character>, Float> map, int dist) {
        float costo=0;
        v.setEstado(true);

        try {
            if(dist!=2) {
                for(Edge<Integer> e: g.incidentEdges(v)) {
                    Vertex<Character> v1= g.opposite(v, e);
                
                    if(v1.getEstado()==false) {
                        costo= costo + e.element();
                        distanciadosDFS(g,v,map,dist++);
                }
            }
            }
            else 
                map.put(v,costo);
            
            
        } catch (InvalidVertexException | InvalidEdgeException | InvalidKeyException e) {e.printStackTrace();
        }
    }
}
	

