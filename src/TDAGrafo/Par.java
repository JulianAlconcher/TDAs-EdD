package TDAGrafo;

import TDALista.ListaDobleEnlazada;
import TDALista.PositionList;

public class Par<V> {

    protected float peso; 
    protected PositionList<Vertex<V>> caminoMin; 

    public Par() {

        peso = 0;
        caminoMin = new ListaDobleEnlazada<Vertex<V>>();
    }

    public void setPeso(float p) {

        peso = p;
    }

    public void setCamino(ListaDobleEnlazada<Vertex<V>> lista) {

        caminoMin = lista;
    }

    public float getPeso() {

        return peso;
    }

    public PositionList<Vertex<V>> getCaminoMin(){

        return caminoMin;
    }
}