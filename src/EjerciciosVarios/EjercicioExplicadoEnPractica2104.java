package EjerciciosVarios;
import java.util.Iterator;

import Exceptions.EmptyQueueException;
import Exceptions.EmptyStackException;
import TDACola.ColaEnlazada;
import TDACola.Queue;
import TDALista.PositionList;
import TDAPila.PilaConEnlaces;
import TDAPila.Stack;



//Ejercico de ejemplo explicado en clase practica jueves 21.04.2022

/** Dada dos listas l1 y l2, retornar verdadero si l1 esta compuesta por las componentes de l2
en el mismo orden seguidos de las componentes de l2 en orden inverso.
	a) Escriba un metodo que reciba como parametro a L1 y L2. 
*/
// Como nos pide que hagamos un metodo que reciba como parametro a las dos listas, 
// decimos que estamos FUERA de la estructura.
public class EjercicioExplicadoEnPractica2104<E> {
	public boolean respetaFormato(PositionList<E> l1, PositionList<E> l2){
		boolean respeta = l1.size()==l2.size();
		Stack<E> pila = new PilaConEnlaces<E>();
		Queue<E> cola = new ColaEnlazada<E>();
		Iterator<E> it = l2.iterator();
		try {
			for(E elem : l2) {
				pila.push(elem);
				cola.enqueue(elem);
			}
			while(it.hasNext() && respeta){
				if(!cola.isEmpty()) {
					respeta = cola.dequeue().equals(it.next());
				}
				else if(!pila.isEmpty()) {
					respeta = pila.pop().equals(it.next());
				}
			}
		}catch(EmptyStackException | EmptyQueueException e) {
			respeta = false;
		}
		return respeta;
	}
}
