package GrafosOTRO;

import TDALista.Position;

public interface Vertex<V> extends Position<V>{

	void setEstado(boolean b);

	boolean getEstado();

}