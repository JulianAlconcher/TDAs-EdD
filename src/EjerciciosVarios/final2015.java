package EjerciciosVarios;

import Exceptions.EmptyTreeException;
import Exceptions.InvalidKeyException;
import Exceptions.InvalidPositionException;
import TDAArbol.Position;
import TDAArbol.Tree;
import TDAMapeo.Map;
import TDAMapeo.MapeoConHashAbierto2022;

/*
 * 	Escribir un metodo que al recibir un arbol, devuelva un mapeo donde las
	claves son los rotulos del arbol, y los valores la profundidad + la cantidad de
	hijos de cada nodo
 */
public class final2015 {
	public <E> Map<E,Integer> retornarMapeo(Tree<E> t) throws EmptyTreeException{
		Map<E,Integer> m = new MapeoConHashAbierto2022<E,Integer>();
		recorridoDelArbol(t,t.root(),0,m);
		return m;
	}
	
	public <E> void recorridoDelArbol(Tree<E> t, Position<E> p,int prof,Map<E,Integer> m) {
		int cantHijos = 0;
		try {
			
			for(Position<E> h : t.children(p)) {
				cantHijos++;
				recorridoDelArbol(t,h,prof++,m);
			}
			
			m.put(p.element(), prof+cantHijos);
			
		} catch (InvalidPositionException | InvalidKeyException e) {e.printStackTrace();
		}
	}
}
