package EjerciciosVarios;
import java.util.Iterator;
import java.util.Scanner;

import Exceptions.BoundaryViolationException;
import Exceptions.EmptyListException;
import Exceptions.InvalidPositionException;
import TDALista.ListaDoblementeEnlazada2022;
import TDALista.Position;
import TDALista.PositionList;

/**
 *  Escriba en Java un método no recursivo INVERTIR(L) que, dada una lista L, devuelve a L con sus elementos en el
	orden inverso al que estaban. Para realizar esta operación deberá utilizar ÚNICAMENTE las operaciones del TDA
	Lista visto en clase. Puede utilizar si fuera necesario una o varias listas auxiliares. Determine el orden del tiempo
	de ejecución de la solución dada.
	Ejemplo:
	l1: 		  {0,2,4,6,8,10,12}
 	invertir(l1): {12,10,8,6,4,2,0}
 */

public class Ejercicio5TP4Listas {
	public static void main (String args[]) throws EmptyListException, InvalidPositionException, BoundaryViolationException
	{
		PositionList<Integer> lista1 = new ListaDoblementeEnlazada2022<Integer>();
		
		lista1.addFirst(0);
		Position<Integer> pos=lista1.first();
		for(int i=1; i<6; i++) {
			lista1.addAfter(pos,2*i);//Coloco en la lista 1 todos los numeros pares.
			pos=lista1.next(pos);//Actualizo la posicion.
			
		}
		//Recorro las listas y las imprimo
		System.out.println("Muestro lista1");
		Iterator<Integer> it= lista1.iterator();
		System.out.print("[ ");
		while(it.hasNext())
			System.out.print(it.next()+" ");
		System.out.print("] ");
		System.out.println(" ");
		PositionList<Integer> listaInvertida = null;
		
		//Le pido al usuario que metodo quiere usar.
		System.out.println("Ingrese 1 para realizar la inversion de la lista con metodo que NO utiliza una estructura auxiliar.");
		System.out.println("Ingrese 2 para realizar la inversion de la lista con metodo que SI utiliza una estructura auxiliar.");
		int numero = 80;
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
			while(numero!=2 && numero!=1) 
			{
				numero = reader.nextInt();
				if(numero!=2 && numero!=1)
				System.out.println("Ingrese 1 o 2");
			}
		
			
		try {
			if(numero==1) {
				listaInvertida = InvertirSinEstructuraAux(lista1);
				System.out.println("Lista invertida usando el metodo 'InvertirSinEstructuraAux'");
				}
			if(numero==2) {
				listaInvertida = InvertirUsandoEstructuraAux(lista1);
			System.out.println("Lista invertida usando el metodo 'InvertirUsandoEstructuraAux'");}

			
		} catch (EmptyListException e) {
			e.printStackTrace();
			listaInvertida = null;		}
		
		//Impresion de la lista
		System.out.println("Muestro la lista invertida");
		Iterator<Integer> it2= listaInvertida.iterator();
		System.out.print("[ ");
		while(it2.hasNext()) {
			System.out.print(it2.next()+" ");
		}
		System.out.print("] ");		
				
	}
	/**
	 * Invierte la lista, sin usar una estructura auxiliar.
	 * Inserta en l los elementos en orden inverso y luego elimina lo que ya estaba en l antes de invertir.
	 * @return l invertida.
	 */
	private static <E> PositionList<E> InvertirSinEstructuraAux (PositionList<E> l) throws EmptyListException, InvalidPositionException{
		System.out.println("Tamaño de L antes de invertir: " + l.size());
		if(l.isEmpty())
			throw new EmptyListException("La lista esta vacia");
		Iterable<Position<E>> it = l.positions();
  		
		//Invierto los elementos y los remuevo. (El remove retorna el elemento que se elimina).
		for(Position<E> pos : it)
			l.addFirst(l.remove(pos));
		
		System.out.println("Tamaño de L despues de invertir: " + l.size());
		
		return l;
		
	}
	/**
	 * Invierte la lista l, usando una lista auxiliar.
	 * Pasa a una lista auxiliar el contenido de forma invertido y luego lo vuelve a pasar a l.
	 * @return l invertida. 
	 */
	private static <E> PositionList<E> InvertirUsandoEstructuraAux (PositionList<E> l) throws EmptyListException, InvalidPositionException{
		System.out.println("Tamaño de L antes de invertir: " + l.size());
		if(l.isEmpty())
			throw new EmptyListException("La lista esta vacia");
		PositionList<E> listaAUX = new ListaDoblementeEnlazada2022<E>();

		Iterator<E> posDeL= l.iterator();
		//Paso los elementos de l a una lista auxiliar, inviertiendo el orden de los elementos.
		while(posDeL.hasNext()) {
			listaAUX.addFirst(posDeL.next());
		}
		//Devuelvo los elementos a l, reemplazando los elementos anteriores.
		Iterable<Position<E>> it = listaAUX.positions();
		for(Position<E> pos : it)// Tiempo n2, ojo.
		{
			while(posDeL.hasNext())
			{
				l.set(pos,posDeL.next());
			}
		}
		
		System.out.println("Tamaño de L despues de invertir: " + l.size());
		
		return l;
		
	}

}
