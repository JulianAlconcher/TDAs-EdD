package TDAGrafo;

public class Arco<V, E> implements Edge<E> {
	
	protected TDALista.Position<Arco<V, E>> posicionEnArcos;
	protected Vertice<V,E> v1,v2;
	protected E rotulo;
	protected TDALista.Position<Arco<V,E>> posicionEnLv2,posicionEnLv1;
	;

	public Arco(Vertice<V, E> v1, Vertice<V, E> v2, E rotulo) {
		this.v1 = v1;
		this.v2 = v2;
		this.rotulo = rotulo;
	}

	public TDALista.Position<Arco<V, E>> getPosicionEnArcos() {
		return posicionEnArcos;
	}

	public void setPosicionEnArcos(TDALista.Position<Arco<V, E>> position) {
		this.posicionEnArcos = position;
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

	public E getRotulo() {
		return rotulo;
	}

	public void setRotulo(E rotulo) {
		this.rotulo = rotulo;
	}

	public TDALista.Position<Arco<V,E>> getPosicionEnLv1() {
		return posicionEnLv1;
	}

	public void setPosicionEnLv1(TDALista.Position<Arco<V, E>> position) {
		this.posicionEnLv1 = position;
	}

	public TDALista.Position<Arco<V,E>> getPosicionEnLv2() {
		return posicionEnLv2;
	}

	public void setPosicionEnLv2(TDALista.Position<Arco<V,E>> posicionEnLv2) {
		this.posicionEnLv2 = posicionEnLv2;
	}

	@Override
	public E element() {
		// TODO Auto-generated method stub
		return null;
	}



}
