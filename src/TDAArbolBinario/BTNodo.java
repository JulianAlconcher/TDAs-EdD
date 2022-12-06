package TDAArbolBinario;

public class BTNodo<E> implements Position<E>{
	
	private E elemento;
	private Position<E> hijoI;
	private Position<E> hijoD; 
	private Position<E> padre;
	
	public BTNodo(E e,Position<E> p){
		elemento=e;
		padre=p;
	}
	
	public BTNodo(E e){
		this(e,null);
	}

	public Position<E> getHijoI(){
		return hijoI;
	}
	
	public Position<E> getHijoD(){
		return hijoD;
	}
	
	public Position<E> getPadre(){
		return padre;
	}
	
	public void setHijoD(Position<E> n){
		hijoD=n;
	}
	
	public void setHijoI(Position<E> n){
		hijoI=n;
	}
	
	public void setPadre(Position<E> p){
		padre=p;
	}
	
	public E element(){
		return elemento;
	}	

	public void setElemento(E e){
		elemento=e;
	}


	
}
