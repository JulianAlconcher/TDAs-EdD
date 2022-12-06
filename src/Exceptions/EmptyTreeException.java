package Exceptions;


public class EmptyTreeException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Modela la excepcion que advierte que el arbol esta vacio.
	 * @param msg Es el mensaje de error que se va a mostrar
	 */
	public EmptyTreeException(String msg){
		super(msg);
	}
}