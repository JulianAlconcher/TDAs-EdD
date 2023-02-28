package TDAArbolBinariodeBusqueda;

import TDALista.Position;
import TDALista.PositionList;

public class TESTER {

	public static void main(String[] args) {
		
		ArbolBinariodeBusqueda<Integer> a = new ArbolBinariodeBusqueda<Integer>();
		NodoABB<Integer> r = a.getRaiz();
		a.expandir(r, 40);
		
		NodoABB<Integer> p1 = a.buscar(40);
		a.expandir(r.getLeft(), 30);
		a.expandir(r.getRight(), 60);
		p1.getLeft().setParent(r);
		
		NodoABB<Integer> p3 = a.buscar(30);
		a.expandir(p3.getLeft(), 10);
		a.expandir(p3.getRight(), 35);
		p3.getLeft().setParent(p3);
		p3.getRight().setParent(p3);
		
		NodoABB<Integer> p4 = a.buscar(10);
		a.expandir(p4.getLeft(), 9);
		a.expandir(p4.getRight(), 11);
		p4.getLeft().setParent(p4);
		p4.getRight().setParent(p4);
		
		NodoABB<Integer> p5 = a.buscar(60);
		a.expandir(p5.getRight(), 70);
		p5.getRight().setParent(p5);
		
		NodoABB<Integer> p6 = a.buscar(70);
		a.expandir(p6.getLeft(), 65);
		a.expandir(p6.getRight(), 99);
		p6.getLeft().setParent(p6);
		p6.getRight().setParent(p6);
		
		
		PositionList<NodoABB<Integer>> l = a.metodo2(40);
		for(Position<NodoABB<Integer>> e : l.positions()) {
			System.out.println("-> " + e.element().getRotulo());
		}
		
	}

}
