package EjerciciosVarios;

import TDAGrafoAndandoCorrectamente.Graph;
import TDAGrafoAndandoCorrectamente.Vertex;
import TDALista.ListaDoblementeEnlazada2022;
import TDALista.PositionList;
import TDAMapeo.Map;
import TDAMapeo.MapeoConHashAbierto2022;

/**
 * Escriba un procedimiento que reciba un grafo G y compute un mapeo m tal que
 * para cada vertice de g, m.get(v) devuelve la cantidad de vertices de g sin contar repetidos que se encuentran a distancia 2 de v. 
 * La distancia entre dos vertices se define por la cantidad de arcos del camino mas corto que los une. 
 */
public class OtroEjDeGrafos {

	public <V,E> Map<Vertex<V>,Integer> metodo(Graph<V,E> g){
		Map<Vertex<V>,Integer> mapeo = new MapeoConHashAbierto2022<Vertex<V>,Integer>();
		PositionList<Vertex<V>> listaA = new ListaDoblementeEnlazada2022<Vertex<V>>();
		PositionList<Vertex<V>> listaM = new ListaDoblementeEnlazada2022<Vertex<V>>();
		for(Vertex<V> v : g.vertices()) {
			v.setEstado(false);
			listaM.addLast(v);
		}
		for(Vertex<V> v : g.vertices()) {
			if(v.getEstado()==false)
				DFSRec(g,v,mapeo,listaA,listaM);
		}
		return mapeo;
	}
	
	private <V,E> void DFSRec(Graph<V,E> g,Vertex<V> v, Map<Vertex<V>,Integer> mapeo,PositionList<Vertex<V>> lA,PositionList<Vertex<V>> lM) {
		
	}
	
//	private <V,E> boolean aDistanciaDos(Graph<V,E> g,Vertex<V> v) {
//		
//	}
}
