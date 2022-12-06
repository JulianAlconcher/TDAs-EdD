package TDALista;

import java.util.Iterator;

import Exceptions.BoundaryViolationException;
import Exceptions.EmptyListException;
import Exceptions.InvalidPositionException;

public class iteradorMiLista<E> implements Iterator<E> {
	
	protected PositionList<E> lista; // Lista a iterar;
	protected Position<E> cursor; //Posicion del elemento corriente;

	public iteradorMiLista(PositionList<E> l) {
		lista = l;

		try {
			cursor = lista.first();
		} catch (EmptyListException e) {
			cursor = null;
		}			
	}
	
	//Si esta en la ultima posicion, retorna falso;
	public boolean hasNext() {
		return (cursor != null);
	}

	public E next() {
				
		E toReturn = cursor.element(); //Devuelvo y avanzo
		
		try {
			if (cursor == lista.last())
				cursor = null;
			else
				cursor = lista.next(cursor);
		}
		catch (EmptyListException | BoundaryViolationException | InvalidPositionException e) {
			cursor = null;
		}
		
		return toReturn;
	}
	
}
