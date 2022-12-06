package TDACola;

import Exceptions.EmptyQueueException;

public class ColaConArregloCircular <E> implements Queue <E>
{
    //Es el arreglo donde se van a guardar los datos de la cola.
	protected E[] q;
	
	//Posicion del primer elemento de la cola.
    protected int f;
    
    //Posicion del proximo elemento a encolar.
    protected int r;
   
    //Constructor: crea una cola vacio con un arreglo circular.
    @SuppressWarnings("unchecked")
	public ColaConArregloCircular()
    {
    	q = (E[]) new Object[10];
    	f = 0;
    	r = 0;
    }
    
    /* Consulta que retorna el tamaño de la cola. 
	 * @return el tamaño de la cola.
	 */
	public int size() 
	{
		return ((q.length - f + r) % q.length);
	}

	/* Consulta que retorna TRUE si la cola es vacia. FALSE en caso de que no lo este.
	 * @return True si la cola es vacia, False en caso contrario. 
	 */
	public boolean isEmpty() 
	{
		return f == r;
	}
    
	/*
	 * @return Retorna el elemento del frente de la cola. 
	 * Si la cola está vacía se produce un error. 
	 */
	public E front() throws EmptyQueueException 
	{
		if(f==r) {
			throw new EmptyQueueException("Cola vacía.");
		}
		else {
			return q[f];
		}
	}
    
	/**
     * Inserta el elemento e en el rabo de la cola
     */
	public void enqueue(E element) 
	{
		if(size() == q.length-1) 
		{
			E[] aux = copiar(f);
			r = size();
			f = 0;
			q = aux;
		}
			q[r] = element;
			r = (r + 1) % q.length;
	}
	
	/**
	 * Duplica el tamaño del arreglo de la cola circular, e inserta en el los elementos que contenia 
	 * anteriormente
	 * @param start Posicion en el arreglo, a partir del cual se realizara la copia de los elementos.
	 * @return Arreglo de elementos con el doble de tamaño que el que se tenia anteriormente.
	 */
	@SuppressWarnings("unchecked")
	private E[] copiar(int start) {
		int j = 0;
		E[] aux = (E[]) new Object[q.length*2];
		
		for(int i = f;!(start == r);i++) {
			start = i % q.length;
			aux[j++] = q[start];
		}
		return aux;
	}
   /**
    * Elimina el elemento del frente de la cola y lo retorna. 
    * Si la cola está vacía se produce un error
    * @return elemento del frente
    */ 
	public E dequeue() throws EmptyQueueException 
	{
		if( isEmpty()) {
			throw new EmptyQueueException("Cola vacía.");
		}
		else {
			E aux = q[f];
			q[f] = null;
			f = (f + 1) % (q.length);
			return aux;
		} 
	}
	
}
 

