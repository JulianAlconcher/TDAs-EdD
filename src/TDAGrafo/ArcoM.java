package TDAGrafo;


public class ArcoM<V,E> implements Edge<E> {
	protected TDALista.Position<Edge<E>> posInEdges;
	protected VerticeM<V> v1,v2;
	protected E element;
	
	
	public ArcoM(E rotulo,VerticeM<V> v1,VerticeM<V> v2) {
		element = rotulo;
		this.v1 = v1;
		this.v2 = v2;
	}
	@Override
	public E element() {
		return element;
	}
	public TDALista.Position<Edge<E>> getPosInEdges() {
		return posInEdges;
	}
	public void setPosInEdges(TDALista.Position<Edge<E>> posInEdges) {
		this.posInEdges = posInEdges;
	}
	public VerticeM<V> getV1() {
		return v1;
	}
	public void setV1(VerticeM<V> v1) {
		this.v1 = v1;
	}
	public VerticeM<V> getV2() {
		return v2;
	}
	public void setV2(VerticeM<V> v2) {
		this.v2 = v2;
	}
	public void setElement(E element) {
		this.element = element;
	}

}