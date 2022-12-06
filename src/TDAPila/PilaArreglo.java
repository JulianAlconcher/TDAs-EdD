package TDAPila;

import Exceptions.EmptyStackException;

public class PilaArreglo<E> implements Stack<E> {

	//Atributos de instancia
	
	protected E[] pila;
	protected int n;
	
	//Constructor
	@SuppressWarnings("unchecked")
	/**
	 * Constructor 1 de la clase
	 * Inicializa el arreglo (pila) y fija n (posicion del arreglo) en 0
	 * @param l 
	 */
	public PilaArreglo(int l)
	{
		pila = (E[]) new Object[l];
		n = 0;
	}
	
	/**
	 * Constrcutor 2 de la clase
	 * Inicializa a la pila con una longitud de 20, llamando al constructor 1 con "this".
	 */
	public PilaArreglo() 
	{
		this(20);
	}
	
	//Consultas
	
	/**Retorna el tamaño de la pila
	 * n = cant de elementos.
	 */
	
	public int size() 
	{
		
		return n;
	}
	
	/**Retorna verdadero si la pila esta vacia, falso en caso contrario.
	 * @return true or false
	 */
	
	public boolean isEmpty() 
	{
		
		return (n==0);
	}

	//Comandos
	
	/**
	 * Retorna el elemento que se encuentra en el tope de la pila
	 * @return TOPE
	 * @throws EmptyStackException SI LA PILA ESTA VACIA
	 */
	
	public E top() throws EmptyStackException 
	{
		if(n==0)
			throw new EmptyStackException("Pila vacia");
		else
		{
			return pila[n-1];
		}
	}

	/**
	 * Inserta el elemento item en el tope de la pila y actualiza la posicion (n) del tope.
	 * Lanza error si la pila esta llena.
	 */
	public void push(E element) 
	{
		 if(n==pila.length)		//es decir, si n es igual a la longitud de la pila, hacemos un resize.
			this.reSize();
		 
		 pila[n] = element; 	// si ese no es el caso, asiganmos a la posicion n el elemento.
	     n++;					//aumentamos la cantidad de elementos. 
		
		
	}
	
	private void reSize()
	{
		@SuppressWarnings("unchecked")
		E [] aux = (E[]) new  Object[pila.length*2];//duplicamos el tamaño de la pila.
		for(int i=0; i<pila.length; i++)
		{
			aux[i] = pila[i];
		}
		pila = aux;
	}

	
	/**
	 * Quita el elemento del tope de la pila y lo retorna
	 * @return elemento del tope de la pila
	 * @throws EmptyStackException SI LA PILA ESTA VACIA
	 */
	public E pop() throws EmptyStackException
	{
		if(n==0)
			throw new EmptyStackException("Pila vacia");//si la pila es vacia arrojamos exepcion.
		else
		{
			E aux = pila[n-1]; 				//guardamos el elemento para retornar luego.
			pila[n-1] = null;				//asignamos nula la posicon a eliminar
			n--;							//disminuimos la cantidad de elementos	
			return aux;						//retornamos el elemento 
		} 
	}

}
