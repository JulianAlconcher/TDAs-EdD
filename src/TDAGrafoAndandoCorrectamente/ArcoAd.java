package TDAGrafoAndandoCorrectamente;

import TDALista.Position;
import TDAMapeo.MapeoConHashAbierto2022;

public class ArcoAd<V, E> extends MapeoConHashAbierto2022<Object,Object>  implements Edge<E>{
	private E rotulo;
	private VerticeAd<V, E> sucesor, predecesor;
	private Position<ArcoAd<V, E>> posicionEnArcos, posPred, posSuces;

	public ArcoAd(E rotulo, VerticeAd<V, E> predecesor, VerticeAd<V, E> sucesor) {
		this.rotulo = rotulo;
		this.predecesor = predecesor;
		this.sucesor = sucesor;
	}

	public ArcoAd() {
		rotulo = null;
		predecesor = null;
		sucesor = null;
	}

	public E element() {
		return rotulo;
	}

	public VerticeAd<V, E> getPred() {
		return predecesor;
	}

	public VerticeAd<V, E> getSuces() {
		return sucesor;
	}

	public Position<ArcoAd<V, E>> getPosicion() {
		return posicionEnArcos;
	}
	
	public Position<ArcoAd<V, E>> getPosPred() {
		return posPred;
	}

	public Position<ArcoAd<V, E>> getPosSuces() {
		return posSuces;
	}

	public void setPred(VerticeAd<V, E> v) {
		predecesor = v;
	}

	public void setSuces(VerticeAd<V, E> v) {
		sucesor = v;
	}

	public void setElement(E elem) {
		rotulo = elem;
	}
	
	public void setPosicionEnAdyacentes(Position<ArcoAd<V, E>> p) {
		posicionEnArcos = p;
	}

	public void setPosPred(Position<ArcoAd<V, E>> p) {
		posPred = p;
	}

	public void setPosSuces(Position<ArcoAd<V, E>> s) {
		posSuces = s;
	}

	@Override
	public void setPeso(int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPeso() {
		// TODO Auto-generated method stub
		return 0;
	}
}