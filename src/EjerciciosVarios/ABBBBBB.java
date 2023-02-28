package EjerciciosVarios;

import TDAArbolBinariodeBusqueda.ArbolBinariodeBusqueda;
import TDAArbolBinariodeBusqueda.NodoABB;

/*
 * Resuelva el siguiente problema: 
 * Dados dos ABB A y B se desea construir un tercer ABB C donde los elementos de C son aquellos que se hallan en A y no en B. 
 * 
 * Asuma que la solucién al problema es un método de la clase ABB y que A recibe el mensaje. 
 * Estime el orden del tiempo de ejecucién de su solucidn justificando apropiadamente.
 */

/*
 * Recorremos ambos arboles 
 * 	si el elem esta en A y NO esta en B
 * 		colocamos en el arbol nuevo
 */
public class ABBBBBB {

	public ArbolBinariodeBusqueda<Integer> construirABB(ArbolBinariodeBusqueda<Integer> a,ArbolBinariodeBusqueda<Integer> b){
		ArbolBinariodeBusqueda<Integer> ret = new ArbolBinariodeBusqueda<Integer>();
		
		preOrden(ret,a,b,a.getRaiz());
		return ret;
		
	}
	
	public void preOrden(ArbolBinariodeBusqueda<Integer> r,ArbolBinariodeBusqueda<Integer> a,ArbolBinariodeBusqueda<Integer> b,NodoABB<Integer> n) {
		if(n.getLeft()!=null) {
			NodoABB<Integer> enc = a.buscar(n.getLeft().getRotulo());
			NodoABB<Integer> encB = b.buscar(n.getLeft().getRotulo());
			if(enc!=null && encB==null) {
				if(r.getRaiz()==null)
					r.expandir(null, enc.getRotulo());
				else
					r.expandir(n, enc.getRotulo());
			}
			preOrden(r,a,b,n.getLeft());
		}
		if(n.getRight()!=null) {
			a.buscar(n.getRight().getRotulo());
			preOrden(r,a,b,n.getRight());
		}
	}
	

}
