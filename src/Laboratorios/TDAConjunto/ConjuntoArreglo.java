package Laboratorios.TDAConjunto;

public class ConjuntoArreglo<E> implements Conjunto<E> {

	protected int tamanio;
	protected E [] s;
	
	@SuppressWarnings("unchecked")
	public ConjuntoArreglo(int max)
	{
		s =  (E[]) new ConjuntoArreglo[max];
		tamanio = 0;
		
	}
	
	public int size() {
		return tamanio;
	}

	//A que se refiere este metodo?
	public int capacity() {
		return tamanio;
	}

	
	public boolean isEmpty() {
		return tamanio == 0;
	}

	
	public E get(int i) throws ConjuntoVacioException {
	if(0<=i && i<tamanio)
		return s[i];
	else
		throw new ConjuntoVacioException("La posicion es invalida.");
	}

	
	public void put(E elem) {
		s[tamanio] = elem;
		tamanio++;
	}

	
	public boolean pertenece(E elem) {
		boolean encontre = false;
		for(int i=0; i<tamanio && encontre; i++)
		{
			if(s[i] == elem)
				encontre=true;
		}
		return encontre;
	}

	//no entiendo
	public Conjunto<E> intersection(Conjunto<E> c) {
		// TODO Auto-generated method stub
		return null;
	}

}
