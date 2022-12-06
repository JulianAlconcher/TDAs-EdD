package GrafosOTRO;

public class VerticeM<V> implements Vertex<V>{
	protected TDALista.Position<Vertex<V>> posInNodes;
	protected V element;
	protected int index;

	public VerticeM(V rotulo,int indice) {
		element = rotulo;
		index = indice;
	}
	@Override
	public V element() {
		return element;
	}

	public TDALista.Position<Vertex<V>> getPosInNodes() {
		return posInNodes;
	}

	public void setPosInNodes(TDALista.Position<Vertex<V>> posInNodes) {
		this.posInNodes = posInNodes;
	}

	public void setElement(V element) {
		this.element = element;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}