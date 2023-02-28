package TDAGrafo;

import Exceptions.*;
import TDALista.PositionList;
import TDAMapeo.Map;
import TDAMapeo.MapeoConHashAbierto2022;

public class DFSPinchado<V,E> {

	/**
     * DFS pinchado (s-t path search)
     * Permite hallar un camino en el digrafo G entre Origen y Destino y retorna el camino hallado en Camino (el cual es una lista vacía al principio).
     * Si encontró un camino, retorna verdadero, en caso contrario retorna falso.
     * @param grafo
     * @param origen
     * @param destino
     * @param camino: Lista vacia
     * @return true si y solo si encuentro el camino, sino false.
     */
    public boolean DFSPinchadoShell(Graph<V,E> grafo, Vertex<V> origen, Vertex<V> destino, PositionList<Vertex<V>> camino) {
        Map<Vertex<V>,Boolean> mapeo = new MapeoConHashAbierto2022<Vertex<V>,Boolean>();
        for(Vertex<V> vert : grafo.vertices())
            try {
                mapeo.put(vert, false);
            } catch (InvalidKeyException e) {e.printStackTrace();
            }
        return DFSPinchadoRec(grafo,origen,destino,camino,mapeo);        
    }
    
    private boolean DFSPinchadoRec(Graph<V,E> grafo, Vertex<V> origen, Vertex<V> destino, PositionList<Vertex<V>> camino,Map<Vertex<V>,Boolean> mapeo) {
        try {
            mapeo.put(origen, true);
            camino.addLast(origen);
            if(origen == destino)
                return true;
            else {
                boolean encontre = false;
                for(Edge<E> e : grafo.incidentEdges(origen)) {
                    Vertex<V> w = grafo.opposite(origen, e);
                    if(mapeo.get(w) == false) {
                        encontre = DFSPinchadoRec(grafo,w,destino,camino,mapeo);
                        if(encontre)
                            return true;
                    }
                }
            }
            camino.remove(camino.last());
            return false;
        } catch (InvalidEdgeException | InvalidVertexException | InvalidPositionException | EmptyListException | InvalidKeyException e) {e.printStackTrace();
        }
        return false;
    }
}

