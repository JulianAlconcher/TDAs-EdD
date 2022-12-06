package GrafosOTRO;


import TDALista.ListaDoblementeEnlazada2022;
import TDALista.PositionList;

public class Vertice<V, E> implements Vertex<V>{
	protected V element;
	protected TDALista.Position<Vertice<V,E>> positionInNodes;
	protected PositionList<Arco<V,E>> adjacentList;
	
	public Vertice(V rotulo) {
		element = rotulo;
		positionInNodes = null;
		adjacentList = new ListaDoblementeEnlazada2022<Arco<V,E>>();
	}
	@Override
	public V element() {
		return element;
	}
	
	public PositionList<Arco<V,E>> getAdyacentes(){
		return adjacentList;
	}
	
	public TDALista.Position<Vertice<V,E>> getPosicionEnNodos(){
		return positionInNodes;
	}
	
	public void setElement(V rotulo) {
		element = rotulo;
	}
	
	public void setPosicionEnNodos(TDALista.Position<Vertice<V, E>> position) {
		positionInNodes = position;
	}
}