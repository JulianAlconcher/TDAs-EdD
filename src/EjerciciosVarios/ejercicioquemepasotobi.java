package EjerciciosVarios;

import Exceptions.InvalidKeyException;
import TDADiccionario.DiccionarioConHashAbierto2022;
import TDADiccionario.Dictionary;
import TDAGrafoAndandoCorrectamente.Edge;
import TDAGrafoAndandoCorrectamente.GrafoD_lista_adyacencia;
import TDAGrafoAndandoCorrectamente.GraphD;
import TDAGrafoAndandoCorrectamente.InvalidEdgeException;
import TDAGrafoAndandoCorrectamente.InvalidVertexException;
import TDAGrafoAndandoCorrectamente.Vertex;
import TDALista.ListaDoblementeEnlazada2022;
import TDALista.Position;
import TDALista.PositionList;
import TDAMapeo.Entry;
import TDAMapeo.Map;

public class ejercicioquemepasotobi {


    public static void main(String args[]) {
    	GraphD<Character,Integer> grafo = new GrafoD_lista_adyacencia<Character,Integer>();
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
//				grafo.insertEdge(e, h, 9);
				grafo.insertEdge(a, e, 4);
				grafo.insertEdge(f, h, 6);
				grafo.insertEdge(c, g, 8);
				grafo.insertEdge(f, e, 8);
			} catch (InvalidVertexException e1) {e1.printStackTrace();
			}

		Dictionary<Vertex<Character>, PositionList<Vertex<Character>>> di;
		try {
			di = retornarDiccionario(grafo);
			System.out.println("size: " + di.size());
			for(TDADiccionario.Entry<Vertex<Character>, PositionList<Vertex<Character>>> en : di.entries()) {
				System.out.print("NODO: " + en.getKey().element() + " --> ");
			
				for(Position<Vertex<Character>> ent : en.getValue().positions()) {
					System.out.print(ent.element().element() + ", ");
				}
			
				System.out.println("");
			}
		} catch (Exception e1) {e1.printStackTrace();
		}

    }
    /*metodo que reciba un grafo dirigido G y retorne un diccionario 
     * en el cual debe para cada vertice v del grafo, devolver las hojas alcanzables
     * de v.(una hoja en un grafo dirigido es un vértice sin adyacentes)
     *      */
    

    
    /* 
     * MetodoRetornarDiccionario ( Grafo: G)
     * creo el diccionario 
     * para todos los vertices del grafo
     * los marco como no visitado
     * ahora para cada vertice v del grafo
     * si no esta visitado 
     * RecorridoRec(Vertice v)
     * 
     * 
     * RecorridoRec(Grafo: G ; Vertice : v)
     * seteo el vertice en visitado
     * pido los vertices emergentes de v
     * para cada vertice de v
     *    si es una hoja 
     *    inserta a v en una lista
     * si la lista no es vacia inserta v y lista en el diccionario
     * RecorridoRec(cada vertice de v)
     */
	
	
    
    public static <V,E> Dictionary<Vertex<V>,PositionList<Vertex<V>>> retornarDiccionario(GraphD<V,E> g){
        Dictionary<Vertex<V>,PositionList<Vertex<V>>> diccionario = new DiccionarioConHashAbierto2022<Vertex<V>,PositionList<Vertex<V>>>();
        for(Vertex<V> v : g.vertices()) {
            v.setEstado(false);
        }
        for(Vertex<V> v: g.vertices()) {
            if(v.getEstado()==false) {
                RecorridoRec(g,v,diccionario);
            }
        }
        return diccionario;
        
    }
    
    public static <V,E> void RecorridoRec(GraphD<V,E> g,Vertex<V> v,Dictionary<Vertex<V>,PositionList<Vertex<V>>> diccionario) {
    	PositionList<Vertex<V>> listaAdy = new ListaDoblementeEnlazada2022<Vertex<V>>();
    	v.setEstado(true);
        try {
			for(Edge<E> emer : g.succesorEdges(v)) {
				Vertex<V> x = g.opposite(v, emer);
				if(esHoja(x,g)) 
					listaAdy.addLast(x);
			
				if(x.getEstado()==false)
					RecorridoRec(g,x,diccionario);

			}
			if(listaAdy.size()>0)
				diccionario.insert(v, listaAdy);
			
		} catch (InvalidVertexException | InvalidEdgeException | InvalidKeyException e) {e.printStackTrace();}
    }
     
    @SuppressWarnings("unused")
	public static <V,E> boolean esHoja(Vertex<V> h,GraphD<V,E> g) {
    	int cont = 0;
    	try {
			for(Edge<E> emer : g.succesorEdges(h)) 
				cont++;
		} catch (InvalidVertexException e) {e.printStackTrace();
		}
		return cont==0;
    }
        
        
        
        
    
}
