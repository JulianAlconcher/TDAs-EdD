package TDAGrafoLalo;

import TDALista.*;
import TDAMapeo.MapeoConHashAbierto2022;


public class ArcoD<V, E> extends MapeoConHashAbierto2022<Object,Object>  implements Edge<E> {
	
	private E rotulo;
	private Position<ArcoD<V,E>> posicionEnArcos;
	private VerticeD<V,E> cola,punta;
	private Position<ArcoD<V,E>> posicionEnEmergentes, posicionEnIncidentes;
	
	public ArcoD(E rotulo, VerticeD<V, E> predecesor, VerticeD<V, E> sucesor) {
		this.rotulo = rotulo;
		setCola(predecesor);
		setPunta(sucesor);
	}
	
	@Override
	public E element() {
		return rotulo;
	}
	
	public void setRotulo(E r) {
		rotulo = r;
	}

	public Position<ArcoD<V,E>> getPosEnArcos() {
		return posicionEnArcos;
	}

	public void setPosEnArcos(Position<ArcoD<V,E>> posicionEnArcos) {
		this.posicionEnArcos = posicionEnArcos;
	}

	public VerticeD<V,E> getCola() {
		return cola;
	}

	public void setCola(VerticeD<V,E> cola) {
		this.cola = cola;
	}

	public VerticeD<V,E> getPunta() {
		return punta;
	}

	public void setPunta(VerticeD<V,E> punta) {
		this.punta = punta;
	}

	public Position<ArcoD<V,E>> getPosEnEmergentes() {
		return posicionEnEmergentes;
	}

	public void setPosEnEmergentes(Position<ArcoD<V,E>> posicionEnEmergentes) {
		this.posicionEnEmergentes = posicionEnEmergentes;
	}

	public Position<ArcoD<V,E>> getPosEnIncidentes() {
		return posicionEnIncidentes;
	}

	public void setPosEnIncidentes(Position<ArcoD<V,E>> posicionEnIncidentes) {
		this.posicionEnIncidentes = posicionEnIncidentes;
	}
}
