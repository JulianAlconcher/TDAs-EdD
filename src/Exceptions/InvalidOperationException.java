package Exceptions;

public class InvalidOperationException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Modela la excepcion que advierte que se uso una operacion que no se puede sobre esa posicion.
	 * @param msg Es el mensaje de error que se va a mostrar
	 */
	public InvalidOperationException(String msg){
		super(msg);
	}
}
