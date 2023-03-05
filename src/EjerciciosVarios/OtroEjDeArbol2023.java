package EjerciciosVarios;

import Exceptions.EmptyTreeException;
import Exceptions.InvalidOperationException;
import Exceptions.InvalidPositionException;
import TDAArbol.Arbol;
import TDAArbol.Position;
import TDAArbol.Tree;
import TDAGrafoAndandoCorrectamente.Edge;
import TDAGrafoAndandoCorrectamente.GraphD;
import TDAGrafoAndandoCorrectamente.InvalidEdgeException;
import TDAGrafoAndandoCorrectamente.InvalidVertexException;
import TDAGrafoAndandoCorrectamente.Vertex;

public class OtroEjDeArbol2023 {
	
	public static void main(String args[]) {

	}
	
	public <V,E> void mayorIncidencias(GraphD<V,E> grafo) {
        int cant=0;
        int actual=0;
        Vertex<V> vertice=null;
        for(Vertex<V> v: grafo.vertices()) {
            v.setEstado(false);
        }
        
        for(Vertex<V> v: grafo.vertices()) {
            if(!v.getEstado())
                dfs(grafo,v, cant, actual, vertice);
        }
        try {
            grafo.removeVertex(vertice);
        } catch (InvalidVertexException e) {e.printStackTrace();
        }
    }
    
    private <V,E> void dfs(GraphD<V,E> grafo, Vertex<V> v, int mayor, int actual, Vertex<V> vertice) {
        v.setEstado(true);
        if(actual>mayor) {
            mayor=actual;
//             vertice.copy(v);
        }
        try {
            for(Edge<E> e: grafo.incidentEdges(v)) {
                actual++;
                Vertex<V> v1=grafo.opposite(v, e);
                if(v1.getEstado()==false)
                    dfs(grafo, v1, mayor, actual, vertice);
            }
            
        }
        
        catch (InvalidVertexException | InvalidEdgeException e) {e.printStackTrace();}
    }
        

}
