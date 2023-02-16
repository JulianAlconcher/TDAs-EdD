package EjerciciosVarios;

import TDADiccionario.DiccionarioConHashAbierto2022;
import TDADiccionario.Dictionary;
import TDAGrafoAndandoCorrectamente.*;
import TDAGrafoAndandoCorrectamente.InvalidEdgeException;
import TDAGrafoAndandoCorrectamente.InvalidVertexException;
import Exceptions.*;


public class HojasAlcanzables {

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
}
