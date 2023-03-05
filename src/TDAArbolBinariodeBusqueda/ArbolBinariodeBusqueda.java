package TDAArbolBinariodeBusqueda;

import java.util.Comparator;

import Exceptions.EmptyListException;
import Exceptions.EmptyPriorityQueueException;
import Exceptions.InvalidKeyException;
import TDAColaConPrioridad.ColaCP_con_heap;
import TDAColaConPrioridad.PriorityQueue;
import TDALista.ListaDoblementeEnlazada2022;
import TDALista.PositionList;

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
		if(p.getRotulo() == null) 
			return p; // LleguÃ© a un dummy
		else { // Estoy en un nodo con un dato no nulo
			int c = comparador.compare( x, p.getRotulo() );
			if( c == 0 ) 
				return p; // Lo encontrÃ© porque x = p.getRotulo()
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
		// Busco x en el ï¿½rbol para obtener el nodo p que lo contiene
		NodoABB<E> p = buscar( e );
		// Si el rï¿½tulo de p es null, p es un dummy, entonces
		// x no estï¿½ en el ï¿½rbol
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
			E aRetornar = p.getRotulo(); // salvo el rï¿½tulo a devolver
			if( p.getRight().getRotulo() == null ) { // p es hoja (pues sus hijos son dummy)
				p.setRotulo( null ); // Convierto a p en dummy haciendo nulo su rï¿½tulo
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
		else { // Si p tiene hijo izquierdo, entonces p.getRotulo() no es el mï¿½nimo.
			// El mï¿½nimo tiene que estar en el subï¿½rbol izquierdo
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
				System.out.print("IZQUIERDO ");
				mostrar(n.getLeft());
			}
			if (n.getRight().getRotulo() != null) {
				System.out.print("DERECHO ");
				mostrar(n.getRight());
			}
		}
		
	}
	
	/*
	 * Recibir un rotulo R y retornar una lista L. Buscar el nodo P que contiene R como rotulo. 
	 * Poblar a L con el listado preorden del subarbol con raiz P pero inclutendo solamente nodos internos.
	 */
	public PositionList<NodoABB<E>> retornarLista(E r){
		PositionList<NodoABB<E>> lista = new ListaDoblementeEnlazada2022<NodoABB<E>>();
		NodoABB<E> aux = buscar(r);
		if(aux!=null) {
			preOrden(aux,lista);
			return lista;
		}
		return null;
	}
	private void preOrden(NodoABB<E> r, PositionList<NodoABB<E>> l) {
		if(r.getLeft()!=null) {
			if(esHoja(r.getLeft())!=true)
				l.addLast(r.getLeft());
			preOrden(r.getLeft(),l);
		}
		if(r.getRight()!=null) {
			if(esHoja(r.getRight())!=true)
				l.addLast(r.getRight());
			preOrden(r.getRight(),l);
		}
	}
	
	/*
	 * Hacer un metodo que recorra al arbol y elimine todas las hojas del mismo
	 */
	public void podarHojas(NodoABB<E> n) {
		if(esHoja(n))
			eliminar(n.getRotulo());
		if(n.getLeft() != null) 
			podarHojas(n.getLeft());
		if(n.getRight() != null) 
			podarHojas(n.getRight());
	}
	
	private boolean esHoja(NodoABB<E> n) {
		return n.getLeft()==null && n.getRight()==null;
	}
	
	public String toStringInOrder() {
		return inorder( this.getRaiz() );
	}
	private String inorder( NodoABB<E> p ) {
		if( p.getRotulo() != null ) 
			return "(" + inorder( p.getLeft()) + p.getRotulo()+ inorder( p.getRight()) + ")";
		else return "";
	}
	
	/**
	 * Recorrer un arbol binario de busqueda de forma eficiente e insertar en una lista L todos aquellos nodos del arbol que sean menores q X de forma ascendente. 
	 */
	public PositionList<NodoABB<E>> metodo(E x){
		PositionList<NodoABB<E>> listaAsc = new ListaDoblementeEnlazada2022<NodoABB<E>>();
		if(root.getRotulo().compareTo(x)>=0) { //raiz es mayor a x 
			InOrderEnABB(root.getLeft(),x,listaAsc);
		}
		else {
//			InOrderEnABB(root.getLeft(),x,listaAsc);
			InOrderEnABB(root,x,listaAsc);
		}
		return listaAsc;
	}
	
	private void  InOrderEnABB(NodoABB<E> n, E x , PositionList<NodoABB<E>> l){
    	NodoABB<E> w = null;
    	if(n.getLeft()!=null) {
    		w = n.getLeft();
        	InOrderEnABB(w,x,l);
    	}
    	if(n.getRotulo().compareTo(x)>=0){						//n es menor x
    		 l.addLast(n);
    	}
    	else {
    		if(n.getLeft()!=null) {
        		w = n.getLeft();
            	InOrderEnABB(w,x,l);}
    	}
        if(n.getRight()!=null){
           w = n.getRight();
           InOrderEnABB(w,x,l);
        }
	}
	
	/**
	 * Recorrer un arbol binario de busqueda de forma eficiente e insertar en una lista L todos aquellos nodos del arbol que sean menores q X de forma ascendente. 
	 * USANDO UN HEAP COMO ESTRUCTURA AUX
	 */
	
	public PositionList<NodoABB<E>> metodo2(E x){
		PositionList<NodoABB<E>> listaAsc = new ListaDoblementeEnlazada2022<NodoABB<E>>();
		PriorityQueue<E,NodoABB<E>> c = new ColaCP_con_heap<E,NodoABB<E>>();
		if(root.getRotulo().compareTo(x)>=0) { //raiz es mayor a x 
			preOrden(root.getLeft(),x,listaAsc,c);
		}
		else
			preOrden(root.getRight(),x,listaAsc,c);
		
		while(!c.isEmpty()) {
			try {
				listaAsc.addLast(c.removeMin().getValue());
			} catch (EmptyPriorityQueueException e) {e.printStackTrace();
			}
		}
		return listaAsc;
	}
	
	private void preOrden(NodoABB<E> n, E x , PositionList<NodoABB<E>> l, PriorityQueue<E,NodoABB<E>> heap) {
		if(n.getRotulo().compareTo(x)>=0)
			try {
				heap.insert(n.getRotulo(), n);
			} catch (InvalidKeyException e) {e.printStackTrace();
			}
	
		if(n.getLeft()!=null)
			preOrden(n.getLeft(),x,l,heap);
		if(n.getRight()!=null)
			preOrden(n.getRight(),x,l,heap);
		
	}
	
	/*
	 * EJERCICIO 7 TP AB
	 * 	Resuelva el siguiente problema: Dados dos ABB A y B se desea construir un tercer ABB C donde
		los elementos de C son aquellos que se hallan en A y no en B. Asuma que la solución al
		problema es un método de la clase ABB y que A recibe el mensaje. Estime el orden del tiempo
		de ejecución de su solución justificando apropiadamente
	 */
	
	public ArbolBinariodeBusqueda<E> construirABB(ArbolBinariodeBusqueda<E> b){
		ArbolBinariodeBusqueda<E> c = new ArbolBinariodeBusqueda<E>();
		preOrden(root,b,c);
		return c;
	}
	
	private void preOrden(NodoABB<E> n,ArbolBinariodeBusqueda<E> b,ArbolBinariodeBusqueda<E> c) {
		if(b.buscar(n.getRotulo())==null) {
			c.expandir(n, n.getRotulo());
		}
		if(n.getLeft()!=null)
			preOrden(n.getLeft(),b,c);
		if(n.getRight()!=null)
			preOrden(n.getRight(),b,c);
	}
	
	/**
	 * 	Programe una operación llamada Ejercicio14 que forma parte de la clase árbol binario de
		búsqueda (y por lo tanto tiene acceso a la estructura interna del mismo) que recibe un rótulo
		de nodo y debe retornar el rótulo del predecesor inorder de dicho nodo. 
		
	 *	Programe todas las operaciones auxiliares de árbol binario de búsqueda que use. 
	 *	Calcule el orden del tiempo de ejecución de su solución justificando apropiadamente.
	 */	
	public E Ejercicio14(E e) throws EmptyListException {
		E ret = null;
		if(e!=root.getRotulo())
			inOrderLoco(root,e,ret);
		return ret;
	}
	
	private void inOrderLoco(NodoABB<E> n,E e,E ret) {
		if(e != n.getRotulo()) {
			NodoABB<E> w = null;
			if(n.getLeft()!=null) {
				w = n.getLeft();
				inOrderLoco(w,e,ret);
			}
			if(n.getRight()!=null) {
				w = n.getRight();
				inOrderLoco(w,e,ret);
			}
		}
		else {
			System.out.println(n.getParent().getRotulo());
			ret = n.getParent().getRotulo();
		}
		
	}

}
