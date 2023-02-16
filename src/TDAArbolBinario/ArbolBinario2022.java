package TDAArbolBinario;

import java.util.Iterator;

import Exceptions.BoundaryViolationException;
import Exceptions.EmptyTreeException;
import Exceptions.InvalidOperationException;
import Exceptions.InvalidPositionException;
import TDAArbolBinariodeBusqueda.NodoABB;
import TDALista.ListaDobleEnlazada;
import TDALista.ListaDoblementeEnlazada2022;
import TDALista.PositionList;


public class ArbolBinario2022<E> implements BinaryTree<E> {

	protected Position<E> root;
	protected int size;
	
	/**
	 * Constructor de la clase ArbolBinario.
	 * Inicializa sus atributos. 
	 */
	public ArbolBinario2022() {
		root = null;
		size = 0;
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<E> iterator() {
		PositionList<E> l = new ListaDoblementeEnlazada2022<E>();
		for(Position<E> posicion : positions())
			l.addLast(posicion.element());
		return l.iterator();
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> lista = new ListaDobleEnlazada<Position<E>>();
		if(!isEmpty())
			preOrden(lista,root);
		return lista;
	}

	/**
	 * Listado preOrden de arbol.
	 * @param L
	 * @param n
	 */
	private void preOrden(PositionList<Position<E>> L,Position<E> n){
		BTNodo<E> nuevo;
		try {
			nuevo = checkPosition(n);
			L.addLast(nuevo);
			if(nuevo.getHijoI() != null)
				preOrden(L,nuevo.getHijoI());
			if(nuevo.getHijoD() != null)
				preOrden(L,nuevo.getHijoD());
		} catch (InvalidPositionException e) {e.printStackTrace();}
		
	}
	
	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		if(size==0)
			throw new InvalidPositionException("Arbol vacio");
		BTNodo<E> n = checkPosition(v);
		E aux = n.element();
		n.setElemento(e);
		return aux;
	}

	@Override
	public Position<E> root() throws EmptyTreeException {
		if(size==0)
			throw new EmptyTreeException("Arbol vacio");
		return root;
	}

	@Override
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		if(v == root)
			throw new BoundaryViolationException("v es la raiz y no tiene padre");
		BTNodo<E> n = checkPosition(v);
		return (Position<E>) n.getPadre();
	}

	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		if(v==null)
			throw new InvalidPositionException("posicion invalida");
		PositionList<Position<E>> hijos = new ListaDoblementeEnlazada2022<Position<E>>();
			try {
				if(hasLeft(v))
					hijos.addLast(left(v));
				if(hasRight(v))
					hijos.addLast(right(v));
			} catch (InvalidPositionException | BoundaryViolationException e) {e.printStackTrace();}
		return hijos;
	}

	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		if(size==0) {
			throw new InvalidPositionException("El arbol es vacio");
		}
		if(v == null) {
			throw new InvalidPositionException("Posicion invalida");
		}
		return hasLeft(v) || hasRight(v);
	}

	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		if(isEmpty())
			throw new InvalidPositionException("Arbol vacio");
		
		BTNodo<E> n = checkPosition(v);
		
		return (n.getHijoD()==null&&n.getHijoI()==null);
	}

	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		if(v==null)
			throw new InvalidPositionException("posicion invalida");
		return v == root;
	}

	@Override
	public Position<E> left(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BTNodo<E> n = checkPosition(v);
		if(n.getHijoI() == null)
			throw new BoundaryViolationException("No posee hijo izquierdo");
		return n.getHijoI();
	}

	@Override
	public Position<E> right(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BTNodo<E> n = checkPosition(v);
		if(n.getHijoD() == null)
			throw new BoundaryViolationException("No posee hijo izquierdo");
		return n.getHijoD();
	}

	@Override
	public boolean hasLeft(Position<E> v) throws InvalidPositionException {
		BTNodo<E> n = checkPosition(v);
		return n.getHijoI() != null;
	}

	@Override
	public boolean hasRight(Position<E> v) throws InvalidPositionException {
		BTNodo<E> n = checkPosition(v);
		return n.getHijoD() != null;
	}

	@Override
	public Position<E> createRoot(E r) throws InvalidOperationException {
		if(root != null)
			throw new InvalidOperationException("Operacion Invalida,el arbol ya tiene raiz");
		BTNodo<E> nodo = new BTNodo<E>(r,null);
		root = (Position<E>) nodo;
		size++;
		return (Position<E>) root;
	}

	@Override
	public Position<E> addLeft(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		BTNodo<E> nodo = checkPosition(v);
		if(nodo.getHijoI() != null)
			throw new InvalidOperationException("V ya tiene hijo izquierdo");
		BTNodo<E> nuevo = new BTNodo<E>(r,nodo);
		nodo.setHijoI(nuevo);
		size++;
		return nuevo;
	}

	@Override
	public Position<E> addRight(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		BTNodo<E> nodo = checkPosition(v);
		if(nodo.getHijoD() != null)
			throw new InvalidOperationException("V ya tiene hijo izquierdo");
		BTNodo<E> nuevo = new BTNodo<E>(r,nodo);
		nodo.setHijoD(nuevo);
		size++;
		return nuevo;
	}

	@Override
	public E remove(Position<E> v) throws InvalidOperationException, InvalidPositionException {
		if(size==0)
			throw new InvalidPositionException("Posicion invalida");
		BTNodo<E> n = checkPosition(v);
		if(hasLeft(v) && hasRight(v))
			throw new InvalidOperationException("no es posible realizar la operacion ya que tiene mas de un hijo.");
		
		BTNodo<E> padre = (BTNodo<E>) n.getPadre();
		E toReturn = n.element();
		if(n==root) {
			if(hasLeft(v))
				root = n.getHijoI();
			else
				if(hasRight(v))
					root = n.getHijoD();
				else
					root = null;
		}
		else {
			if (hasLeft(v)) {
				if (padre.getHijoI()==n) 
					padre.setHijoI(n.getHijoI());
				else 
					padre.setHijoD(n.getHijoI());
			}
			else 
				if (hasRight(v)){
					if (padre.getHijoI()==n) 
						padre.setHijoI(n.getHijoD());
					else 
						padre.setHijoD(n.getHijoD());
				}
				else{
					if (padre.getHijoI()==n) 
						padre.setHijoI(null);
					else 
						padre.setHijoD(null);
				}
			}	  
		
	 size--;
	 return toReturn;
		
	}

	@Override
	public void attach(Position<E> r, BinaryTree<E> T1, BinaryTree<E> T2) throws InvalidPositionException {
		BTNodo<E> raizLocal = checkPosition(r), hiRaizLocal, hdRaizLocal;
		Position<E> raizT1, raizT2;
		
		if (r==null || raizLocal.getHijoI() != null || raizLocal.getHijoD() != null) {
			throw new InvalidPositionException("La posicion no corresponde a un nodo hoja");
		}
		try {
		//Clonacion de T1 como subarbol izquierdo
			if (!T1.isEmpty()) {
				raizT1 = T1.root();
				hiRaizLocal = new BTNodo<E>(raizT1.element(), (BTNodo<E>) raizLocal);
				raizLocal.setHijoI(hiRaizLocal);
				clonar(hiRaizLocal, raizT1, T1);
			}
		//Clonacion de T2 como subarbol derecho
			if (!T2.isEmpty()) {
				raizT2 = T2.root();
				hdRaizLocal = new BTNodo<E>(raizT2.element(), (BTNodo<E>) raizLocal);
				raizLocal.setHijoD(hdRaizLocal);
				clonar(hdRaizLocal, raizT2, T2);
			}
			size+= T1.size() + T2.size();
		}catch(EmptyTreeException e){ 
			raizLocal.setHijoI(null); raizLocal.setHijoD(null); 
		}
	}
		
	/**
	 * Metodo privado que clona.
	 * @param padreLocal
	 * @param padreT
	 * @param t
	 */
	private void clonar(Position<E> padreLocal, Position<E> padreT, BinaryTree<E> t) {
		BTNodo<E> hiPadreLocal, hdPadreLocal;
		BTNodo<E> padreLocalN;
		try {
			padreLocalN = checkPosition(padreLocal);
			Position<E> hiPadreT, hdPadreT;
			try {
				//Si existe hijo izquierdo en T de padreT, se clona este y el subarbol a partir del hijo izquierdo de padre_t.
				if (t.hasLeft(padreT)) {
					hiPadreT = t.left(padreT);
					hiPadreLocal = new BTNodo<E>(hiPadreT.element(), (BTNodo<E>) padreLocal);
					padreLocalN.setHijoI(hiPadreLocal);
					clonar(hiPadreLocal, hiPadreT, t);
				}
				//Si existe hijo derecho en T de padreT, se clona este y el subarbol a partir del hijo derecho de padre_t.
				if (t.hasRight(padreT)) {
					hdPadreT = t.right(padreT);
					hdPadreLocal = new BTNodo<E>(hdPadreT.element(), (BTNodo<E>) padreLocal);
					padreLocalN.setHijoD(hdPadreLocal);
					clonar(hdPadreLocal, hdPadreT, t);
				}
			}catch(InvalidPositionException | BoundaryViolationException e) {
				padreLocalN.setHijoI(null); padreLocalN.setHijoD(null);
			}
		} catch (InvalidPositionException e1) {e1.printStackTrace();
		}
		
	}
	
	/**
	 * Convierte a la posicion pasada por parametro en un Nodo del arbol.
	 * @param p Es la posicion que se quiere convertir en nodo
	 * @return El nodo correspondiente a la posicion
	 * @throws InvalidPositionException Si la posicion pasada por parametro es invalida o que el arbol esta vacio.
	 */
	private BTNodo<E> checkPosition(Position<E> v) throws InvalidPositionException{
		if(size==0)
			throw new InvalidPositionException("Arbol vacio");
		if(v==null)
			throw new InvalidPositionException("Posicion invalida");
		BTNodo<E> nodo = null;
		try {
			nodo = (BTNodo<E>) v;
		}catch(ClassCastException e) {throw new InvalidPositionException("Posicion Invalida");}
		return nodo;
	}
	
	public BinaryTree<String> parse(String s) {
		BinaryTree<String> btReturn = new ArbolBinario2022<String>();
		try {
		if(!tieneOperador(s)) {
			BinaryTree<String> bt = new ArbolBinario2022<String>();
			bt.createRoot(s);
			return bt;
		}else {
			char op = ultOperador(s);
			BinaryTree<String> t1 = parse(izq(s,op));
			BinaryTree<String> t2 = parse(der(s,op));
		
			btReturn.createRoot(Character.toString(op));
			btReturn.attach(btReturn.root(),t1,t2);
		}
		}catch(InvalidOperationException e) {} catch (InvalidPositionException e) {
			e.printStackTrace();
		} catch (EmptyTreeException e) {
			e.printStackTrace();
		}
		return btReturn;
	}
	
	private boolean tieneOperador(String s) {
	boolean encontre = false;
		for(int i=0; i<s.length() && encontre==false; i++) {
			if(s.charAt(i) != ('+' | '*' | '/' | '-'))
				encontre = true;
		}
		return encontre;
		
	}
	
	private Character ultOperador(String s) {
		Character toReturn=null;
		boolean encontre = false;
		for(int i=s.length(); i>0 && encontre == false; i++) {

			if(s.charAt(i) == ('+' | '*' | '/' | '-')) {
				encontre = true;
				toReturn = s.charAt(i);}
			}
		
		return toReturn;
	}
	
	public String izq(String s,Character ult) {
		boolean encontre = false;
		String toReturn = "";
		for(int i=0; i<s.length() && encontre==false; i++) {
			toReturn = toReturn + s.charAt(i);
			if(s.charAt(i) == ult)
				encontre = true;
		}
		return toReturn;
	}
	
	public String der(String s,Character ult) {
		boolean encontre = false;
		String toReturn = "";
		for(int i=s.length(); i>0 && encontre==false; i++) {
			toReturn = toReturn + s.charAt(i);
			if(s.charAt(i) == ult)
				encontre = true;
		}
		return toReturn;
	}
	
	
	//-----------------EJERCICIO---------------------
	
	/**
	 * Escriba un m√©todo que indique si un √°rbol binario es propio [GT], es decir todo nodo tiene o bien dos hijos, o bien ning√∫n hijo. 
	 * @return
	 * @throws InvalidPositionException 
	 */
	public boolean esArbolPropio() throws InvalidPositionException {
		boolean retorno = (checkPosition(root).getHijoD()==null && checkPosition(root).getHijoI()==null) || (checkPosition(root).getHijoD()!=null && checkPosition(root).getHijoI()!=null);
		return retorno && RecorrerRec(root);
		
	}

	private boolean RecorrerRec(Position<E> posT){
		boolean retornoR = true;
		try {
			while(retornoR) {
				if (hasLeft(posT) && !hasRight(posT))
					retornoR = false;
				if (!hasLeft(posT) && hasRight(posT))
					retornoR = false;
				
				else if(hasLeft(posT)) {
					RecorrerRec(this.left(posT));
				}
				else if(hasRight(posT)) {
					RecorrerRec(this.right(posT));
				}
			}
		} catch (InvalidPositionException e) {e.printStackTrace();} catch (BoundaryViolationException e) {e.printStackTrace();
		}
		
		return retornoR;
	}
	
//    /** Usando el lenguaje de programacion Java y con total acceso a la estructura de datos agregue
//    * un metodo a la clase arbol binario de busqueda definido en (a) que resuelva  lo siguiente:
//    * 
//    * Recibir un rot R y retornar una lista L. 
//    * Buscar el nodo P que contiene R como rotulo. 
//    * 
//    * -->> ø?ø? Poblar a L con el listado preorden del subarbol con raiz P pero incluyendo solamente nodos internos. 
//    */    
//	
//   private void preOrden(PositionList<NodoABB<E>> L,NodoABB<E> n){
//       if(n.getLeft() != null) {
//           L.addLast(n);
//           preOrden(L,n.getLeft());
//       }
//       if(n.getRight() != null)
//           preOrden(L,n.getRight());
//       
//   }
//   public PositionList<NodoABB<E>> metodo(E r){
//       NodoABB<E> p = buscar(r);
//       PositionList<NodoABB<E>> lista = new ListaDoblementeEnlazada2022<NodoABB<E>>();
//       lista.addLast(p);
//       preOrden(lista,p);
//       return lista;
//   }
	
}
