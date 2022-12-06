package TDAArbolBinariodeBusqueda;

import java.util.Comparator;

public class ArbolBinariodeBusqueda<E extends Comparable<E>> {
	//Raiz del arbol
	protected NodoABB <E> root;
	
	protected Comparator <E> comparador;
	
	/**
	 * Crea un nodo dummy como raiz del arbol
	 * para dejarlo preparado para la primera insercion
	 */
	public ArbolBinariodeBusqueda(Comparator<E> comp) {
		root = new NodoABB<E>(null,null);
		comparador=comp;
	}

	public ArbolBinariodeBusqueda() {
		root = new NodoABB<E>(null , null);
		comparador = new DefaultComparator <E>();
	}
	/*
	 * Retorna la raiz del arbol
	 */
	public NodoABB<E> getRaiz(){
		return root;
	}
	
	public NodoABB<E> buscar(E x){
		return buscarAux(x,root); //Busqueda recursiva desde la raiz
	}
	
	private NodoABB<E> buscarAux( E x, NodoABB<E> p ) 
	{
		if( p.getRotulo() == null ) return p; // Llegué a un dummy
		else { // Estoy en un nodo con un dato no nulo
		int c = comparador.compare( x, p.getRotulo() );
		if( c == 0 ) return p; // Lo encontré porque x = p.getRotulo()
		else if( c < 0 ) /* Busco a izq porque x < p.getRotulo() */
		return buscarAux( x, p.getLeft() );
		else /* Busco a derecha porque x > p.getRotulo() */
		return buscarAux( x, p.getRight() );
		}
		
	}
	/**
	 * Expande un nodo DUMMY, reconvirtiendolo en un nodo con rotulo y dos hijos DUMMY
	 */
	public void expandir(NodoABB<E> p, E e) {
		p.setRotulo(e);//le cambio el rotulo al nodo dummy
		p.setLeft( new NodoABB<E>( null, p) ); // Creo dummy a izquierda
		p.setRight( new NodoABB<E>( null, p ) ); // Creo dummy a derecha
	}
	
	public void eliminar(E e) {
		// Busco x en el �rbol para obtener el nodo p que lo contiene
		NodoABB<E> p = buscar( e );
		// Si el r�tulo de p es null, p es un dummy, entonces
		// x no est� en el �rbol
		if( p.getRotulo() != null ) {
			if (p.getLeft().getRotulo() == null && p.getRight().getRotulo() == null) {//p es una hoja
				//System.out.println(p.getRotulo()+" es hoja");
				p.setRotulo(null);
			}
			else {//p no es una hoja
				if (p != root && p.getLeft().getRotulo() != null && p.getRight().getRotulo() == null) {//p solo tiene hijo izquierdo
					//System.out.println(p.getRotulo()+" solo tiene hijo izquierdo");
					if (p.getParent().getLeft() == p) {//p es el hijo izquierdo de su padre
						p.getParent().setLeft(p.getLeft());//establezco al hijo de p como hijo izquierdo del padre de p
					}
					else {//p es el hijo derecho de su padre
						if (p != root) {
							p.getParent().setRight(p.getLeft());//establezco al hijo de p como hijo derecho del padre de p
						}
						else {//si es la raiz
							E aux = root.getLeft().getRotulo();
							eliminar(aux);//borra el hijo
							root.setRotulo(aux);//la raiz queda con el rotulo del hijo				
						}
					}
					p.getLeft().setParent(p.getParent());//establezco al padre de p como padre del hijo de p
					p.setRotulo(null);//nulo en el rotulo de p
				}	
			}
		}
		else {			
			if (p != root && p.getRight().getRotulo() != null && p.getLeft().getRotulo() == null) {//p solo tiene hijo derecho
				if (p.getParent().getLeft() == p) {//p es el hijo izquierdo de su padre
						p.getParent().setLeft(p.getRight());//establezco al hijo de p como hijo izquierdo del padre de p
				}
				else {//p es el hijo derecho de su padre
					if (p != root) {
						p.getParent().setRight(p.getRight());//establezco al hijo de p como hijo derecho del padre de p
					}
					else { //si es la raiz
						E aux = root.getRight().getRotulo();
						eliminar(aux);//borra el hijo
								root.setRotulo(aux);//la raiz queda con el rotulo del hijo
					}
				}
				p.getRight().setParent(p.getParent());//establezco al padre de p como padre del hijo de p
				p.setRotulo(null);//nulo en el rotulo de p
			}					
			else {
				E aux = eliminarAux(p);
				p.setRotulo(aux);
			}
		}
	}			
	private E eliminarAux(NodoABB<E> p) {//p tiene 2 hijos
		E rotulo = null;
		//try {
			NodoABB<E> pos = p.getLeft();
			while(pos.getRight().getRotulo() != null){
				//System.out.println("AAAAAAAA "+pos.getRotulo());
				pos = pos.getRight();
			}
			 rotulo = pos.getRotulo();
			eliminar(pos.getRotulo());
	/*	}
		catch(Exception en) {
			en.printStackTrace();
		}*/
			return rotulo;
	}
		
		/*if( p.getLeft().getRotulo() == null ) { // El hijo izquierdo de p es un dummy
			E aRetornar = p.getRotulo(); // salvo el r�tulo a devolver
			if( p.getRight().getRotulo() == null ) { // p es hoja (pues sus hijos son dummy)
				p.setRotulo( null ); // Convierto a p en dummy haciendo nulo su r�tulo
				p.setLeft( null ); // y desenganchando sus dos hijos dummy
				p.setRight( null );
			} 
			else { // p solo tiene hijo derecho (xq no tiene izquierdo)
			// Engancho al padre de p con el hijo derecho de p.
			// Seguro tiene que ser el hijo derecho de su padre.
				p.getParent().setRight( p.getRight() );
				p.getRight().setParent( p.getParent() );
			}
			return aRetornar;
		} 
		else { // Si p tiene hijo izquierdo, entonces p.getRotulo() no es el m�nimo.
			// El m�nimo tiene que estar en el sub�rbol izquierdo
			return eliminarAux( p.getLeft() );
		}*/
	//}
	public void imprimir() {
		if (this.getRaiz().getRotulo() != null) {
			System.out.println(this.getRaiz().getRotulo());
		}
		if (this.getRaiz().getLeft().getRotulo() != null) {
			mostrar(this.getRaiz().getLeft());
		}
		if (this.getRaiz().getRight().getRotulo() != null) {
			mostrar(this.getRaiz().getRight());
		}
	}
	
	private void mostrar(NodoABB<E> n) {
		if (this.getRaiz().getRotulo() != null) {
			System.out.println(n.getRotulo() + " hijo de " + n.getParent().getRotulo());
			if (n.getLeft().getRotulo() != null) {
				mostrar(n.getLeft());
			}
			if (n.getRight().getRotulo() != null) {
				mostrar(n.getRight());
			}
		}
		
	}
	
}
