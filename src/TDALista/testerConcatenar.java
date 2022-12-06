package TDALista;
/**
 * Tester que se encarga de testear si el metodo concatenar en TDALista es correcto. 
 */
public class testerConcatenar {

	public static void main(String[] args) {
		
		PositionList<Integer> l1 = new ListaDoblementeEnlazada2022<Integer>();
		PositionList<Integer> l2 = new ListaDoblementeEnlazada2022<Integer>();
		
		l1.addLast(2);
		l1.addLast(2);
		l1.addLast(2);
		l1.addLast(2);
		
		l2.addLast(4);
		l2.addLast(4);
		l2.addLast(4);
		l2.addLast(4);
		System.out.println("tamaño de l1 antes de concatenar: " + l1.size());
		System.out.println("imprimo l1: ");
		for(Position<Integer> elem: l1.positions()) {
			System.out.println(elem.element() + " ");
		}
		System.out.println("imprimo l2: ");
		System.out.println(" ");
		for(Position<Integer> elem: l2.positions()) {
			System.out.println(elem.element() + " ");
		}
		System.out.println("imprimo lista concatenada: ");
		((ListaDoblementeEnlazada2022<Integer>) l1).concatenar(l2);
		System.out.println(" ");
		for(Position<Integer> elem: l1.positions()) {
			System.out.println(elem.element() + " ");
		}
		
		System.out.println("tamaño de l1 DESPUES de concatenar: " + l1.size());
	}

}
