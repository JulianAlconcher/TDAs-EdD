package TDAGrafoLalo;

import TDALista.Position;
import TDAMapeo.MapeoConHashAbierto2022;

public class ArcoM<V,E> extends MapeoConHashAbierto2022<Object,Object>  implements Edge<E>{
	private E rotulo;
	private Position<Edge<E>> posicionEnArcos;
	private VerticeM<V> v1,v2;
	
	public ArcoM(E rotulo, VerticeM<V> v1, VerticeM<V> v2){
		this.rotulo = rotulo;
		this.v1 = v1;
		this.v2 = v2;
	}
	
	//Setters
	public void setPosicionEnArcos(Position<Edge<E>> p) { posicionEnArcos = p; }
	public void setRotulo(E nuevo) { rotulo = nuevo; }
	public void setV1(VerticeM<V> v) {
		v1=v;
	}
	public void setV2(VerticeM<V> v) {
		v2=v;
	}
	//Getters
	public Position<Edge<E>> getPosicionEnArcos() { return posicionEnArcos; }
	public VerticeM<V> getV1() { return v1; }
	public VerticeM<V> getV2() { return v2; }
	public E element() { return rotulo; }
}
