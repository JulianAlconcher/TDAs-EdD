package TDAGrafoLalo;

import TDALista.*;
import TDAMapeo.MapeoConHashAbierto2022;

public class VerticeD<V, E>extends MapeoConHashAbierto2022<Object,Object> implements Vertex<V> {
	
	private Position<VerticeD<V, E>> posicionEnNodos;
	private V rotulo;
	private PositionList<ArcoD<V,E>> emergentes , incidentes;
	private boolean estado;
	
	public VerticeD(V rotulo) {
		this.rotulo = rotulo;
		setEmergentes(new ListaDoblementeEnlazada2022<ArcoD<V, E>>());
		setIncidentes(new ListaDoblementeEnlazada2022<ArcoD<V, E>>());
		estado = false;
	}
	
	
	@Override
	public V element() {
		return rotulo;
	}
	
	public void setRotulo(V e) {
		rotulo = e;
	}
	@Override
	public void setEstado(boolean e) {
		estado = e;
	}
	@Override
	public boolean getEstado() {
		return estado;
	}

	public PositionList<ArcoD<V,E>> getEmergentes() {
		return emergentes;
	}
	
	public void setEmergentes(PositionList<ArcoD<V,E>> emergentes) {
		this.emergentes = emergentes;
	}


	public Position<VerticeD<V, E>> getPosEnNodos() {
		return posicionEnNodos;
	}


	public void setPosEnNodos(Position<VerticeD<V, E>> posicionEnNodos) {
		this.posicionEnNodos = posicionEnNodos;
	}


	public PositionList<ArcoD<V,E>> getIncidentes() {
		return incidentes;
	}

	public void setIncidentes(PositionList<ArcoD<V,E>> incidentes) {
		this.incidentes = incidentes;
	}
	
	
}
