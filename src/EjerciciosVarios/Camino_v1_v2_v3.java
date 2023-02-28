package EjerciciosVarios;

import Exceptions.EmptyListException;
import Exceptions.InvalidEdgeException;
import Exceptions.InvalidPositionException;
import Exceptions.InvalidVertexException;
import GrafosOTRO.Edge;
import GrafosOTRO.Graph;
import GrafosOTRO.Vertex;
import TDALista.ListaDoblementeEnlazada2022;
import TDALista.PositionList;

// Dado un grafo G, y tres vértices v1, v2 y v3 se desea hallar un camino de v1 a v3 que pase por v2. 

public class Camino_v1_v2_v3 {

	public <V,E> PositionList<Vertex<V>> encontrarCamino(Graph<V,E> g, Vertex<V> v1,Vertex<V> v2,Vertex<V> v3){
		PositionList<Vertex<V>> camino = new ListaDoblementeEnlazada2022<Vertex<V>>();
		for(Vertex<V> v : g.vertices())
			v.setEstado(false);
		boolean exito = encontrarRec(g,v1,v2,v3,camino);
		if(exito)
			return camino;
		else
			return null;
	}
	
	private <V,E> boolean encontrarRec(Graph<V,E> g, Vertex<V> origen,Vertex<V> medio,Vertex<V> destino,PositionList<Vertex<V>> l) {
		try {
			origen.setEstado(true);
			for(Edge<E> arcos : g.incidentEdges(origen)) {
				Vertex<V> x = g.opposite(origen, arcos);
				if(x.getEstado() == false) {
					l.addLast(x);
					if(encontrarRec(g,origen,x,destino,l))
						return true;
					else
						l.remove(l.last());
				}
				
				if(origen.equals(medio)) {
					l.addLast(medio);
					if(encontrarRec(g,medio,x,destino,l))
						return true;
					else {
						l.remove(l.last());
						return false;
					}
				}else {
					if(origen.equals(destino)) {
						l.addLast(destino);
						return true;
					}
				}
				
			}
		} catch (InvalidVertexException | InvalidEdgeException | InvalidPositionException | EmptyListException e) {e.printStackTrace();}
		
		return false;
	}
}
