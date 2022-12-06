package TDAGrafo;

import TDALista.ListaDoblementeEnlazada2022;
import TDALista.PositionList;
import TDAMapeo.MapeoConHashAbierto;

public class Vertice<V,E> extends MapeoConHashAbierto<Object,Object> implements Vertex<V> {
	
	protected TDALista.Position<Vertice<V, E>> posicionEnListaVertices;
	protected V rotulo;
	protected PositionList<Arco<V,E>> adyacentes;
	
	public Vertice(V rotulo) {
		this.rotulo = rotulo;
		adyacentes = new ListaDoblementeEnlazada2022<Arco<V,E>>();
	}

	public TDALista.Position<Vertice<V, E>> getPosicionEnListaVertices() {
		return posicionEnListaVertices;
	}

	public void setPosicionEnListaVertices(TDALista.Position<Vertice<V, E>> posicionEnListaVertices) {
		this.posicionEnListaVertices = posicionEnListaVertices;
	}

	public V getRotulo() {
		return rotulo;
	}

	public void setRotulo(V rotulo) {
		this.rotulo = rotulo;
	}

	public PositionList<Arco<V, E>> getAdyacentes() {
		return adyacentes;
	}

	public void setAdyacentes(PositionList<Arco<V, E>> adyacentes) {
		this.adyacentes = adyacentes;
	}

	@Override
	public V element() {
		// XXX Auto-generated method stub
		return null;
	}

}
