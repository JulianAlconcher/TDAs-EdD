package Exceptions;

public class InvalidPositionException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Modela la excepcion que advierte que se uso una posicion que no esta en la lista
	 */
	public InvalidPositionException(String msg) {
		super(msg);
	}
}
