package TDAGrafoAndandoCorrectamente;

import TDALista.*;
import TDAMapeo.MapeoConHashAbierto2022;

public class VerticeAd<V, E> extends MapeoConHashAbierto2022<Object,Object> implements Vertex<V>{

	private V rotulo;
	private PositionList<ArcoAd<V, E>> adyacentes;
	private Position<VerticeAd<V, E>> posicionEnNodos;
	private boolean estado;

	public VerticeAd(V rotulo) {
		this.rotulo = rotulo;
		adyacentes = new ListaDoblementeEnlazada2022<ArcoAd<V, E>>();
		estado = false;
	}

	public V element() {
		return rotulo;
	}

	public PositionList<ArcoAd<V, E>> getAdyacentes() {
		return adyacentes;
	}

	public Position<VerticeAd<V, E>> getPosicion() {
		return posicionEnNodos;
	}

	public void setElement(V elem) {
		rotulo = elem;
	}

	public void setPosicion(Position<VerticeAd<V, E>> pos) {
		posicionEnNodos = pos;
	}

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}