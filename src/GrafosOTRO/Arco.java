package GrafosOTRO;

public class Arco<V, E> implements Edge<E> {
	protected E element;
	protected Vertice<V,E> v1, v2;
	protected TDALista.Position<Arco<V,E>> posicionEnArcos,posicionEnlv1,posicionEnlv2;
	
	public Arco(E rotulo, Vertice<V,E> v1, Vertice<V,E> v2) {
		this.v1 = v1;
		this.v2 = v2;
		element = rotulo;
	}
	@Override
	public E element() {
		return null;
	}
	public void setElement(E element) {
		this.element = element;
	}
	public Vertice<V, E> getV1() {
		return v1;
	}
	public void setV1(Vertice<V, E> v1) {
		this.v1 = v1;
	}
	public Vertice<V, E> getV2() {
		return v2;
	}
	public void setV2(Vertice<V, E> v2) {
		this.v2 = v2;
	}
	public TDALista.Position<Arco<V, E>> getPosicionEnArcos() {
		return posicionEnArcos;
	}
	public void setPosicionEnArcos(TDALista.Position<Arco<V, E>> posicionEnArcos) {
		this.posicionEnArcos = posicionEnArcos;
	}
	public TDALista.Position<Arco<V, E>> getPosicionEnlv1() {
		return posicionEnlv1;
	}
	public void setPosicionEnlv1(TDALista.Position<Arco<V, E>> posicionEnlv1) {
		this.posicionEnlv1 = posicionEnlv1;
	}
	public TDALista.Position<Arco<V, E>> getPosicionEnlv2() {
		return posicionEnlv2;
	}
	public void setPosicionEnlv2(TDALista.Position<Arco<V, E>> posicionEnlv2) {
		this.posicionEnlv2 = posicionEnlv2;
	}

}