package EjerciciosVarios;

import java.util.Iterator;

import Exceptions.BoundaryViolationException;
import Exceptions.EmptyListException;
import Exceptions.InvalidPositionException;
import TDALista.ListaDoblementeEnlazada2022;
import TDALista.Position;
import TDALista.PositionList;
// Julian Alconcher - Estructura de Datos - 1er cuatrimestre 2022 - UNS - ahre
public class Ejercicio3Tp4Listas {
	public static void main (String args[]) throws EmptyListException, InvalidPositionException, BoundaryViolationException
	{
		PositionList<Integer> lista1 = new ListaDoblementeEnlazada2022<Integer>();
		PositionList<Integer> lista2 = new ListaDoblementeEnlazada2022<Integer>();
		
		lista1.addFirst(0);
		Position<Integer> pos=lista1.first();
		lista2.addFirst(1);
		for(int i=1; i<6; i++) {
			lista1.addAfter(pos,2*i);//Coloco en la lista 1 todos los numeros pares.
			pos=lista1.next(pos);//Actualizo la posicion.
			
		}
		for(int i=1; i<10; i++) {
			lista2.addLast(2*i+1);//Coloco en la lista 2 todos los numeros impares.
			
			
		}
		//Recorro las listas y las imprimo
		System.out.println("Muestro lista1");
		Iterator<Integer> it= lista1.iterator();
		while(it.hasNext())
			System.out.println(it.next());
		System.out.println("Muestro lista2");
		Iterator<Integer> it2= lista2.iterator();
		while(it2.hasNext())
			System.out.println(it2.next());
		
		//Luego recorro la lista intercalada usando posiciones y la imprimo.
		System.out.println("Muestro lista intercalada");
		PositionList<Integer> listaIntercalada = IntercalarUsandoPosiciones(lista1,lista2);
		Iterator<Integer> itIntercalada= listaIntercalada.iterator();
		while(itIntercalada.hasNext())
			System.out.println(itIntercalada.next());
		
	}
	
	/**
	 * Metodo que retorna true si x es menor que y.
	 * @param x primero
	 * @param y segundo
	 * @return
	 */
	public static boolean menor(int x, int y) {
		return x<y;
	}
	
	public static PositionList<Integer> IntercalarUsandoPosiciones (PositionList<Integer> l1, PositionList<Integer> l2) throws EmptyListException, InvalidPositionException, BoundaryViolationException
	{
		PositionList<Integer> aRetornar = new ListaDoblementeEnlazada2022<Integer>();
		
		Position<Integer> pos1=l1.first();//Primer elemento de la lista l1
		Position<Integer> pos2=l2.first();//Primer elemento de la lista l2
		Position<Integer> ul1=l1.last();//Ultimo elemento de la lista l1
		Position<Integer> ul2=l2.last();//Ultimo elemento de la lista l2
		
		while(pos1!=null && pos2!=null){				//Mientras que la posicion 1 y posicion 2 no sean nulas
			if(menor(pos1.element(),pos2.element())) {  //si el elemento de l1 es menor que el de l2
				aRetornar.addLast(pos1.element());	    //lo inserto en la lista de retorno
				if(pos1!=ul1)					 	    //Si la posicion de l1 es distinta a su ultimo elemento, avanzo 
					pos1=l1.next(pos1);
				else
					pos1=null;
			}
			else if(menor(pos2.element(),pos1.element())){
				aRetornar.addLast(pos2.element());
				if(pos2!=ul2)
					pos2=l2.next(pos2);
				else
					pos2=null;
			}
			else {										//Si entra aca, quiere decir que los elementos son iguales.
				aRetornar.addLast(pos1.element());		//Inserto pos 1 e inserto pos 2.
				aRetornar.addLast(pos2.element());
				if(pos1!=ul1)
					pos1=l1.next(pos1);
				else
					pos1=null;
				if(pos2!=ul2)
					pos2=l2.next(pos2);
				else
					pos2=null; 
			}
		}
				
		while(pos2!=null) {								//Si se termino l1, inserto lo que quedo de l2
			aRetornar.addLast(pos2.element());
			if(pos2!=ul2)
				pos2=l2.next(pos2);
			else
				pos2=null;
		}
		while(pos1!=null) {								//Si se termino l2, inserto lo que quedo de l1
			aRetornar.addLast(pos1.element());
			if(pos1!=ul1)
				pos1=l1.next(pos1);
			else
				pos1=null;
		}
		return aRetornar;
	}


}

