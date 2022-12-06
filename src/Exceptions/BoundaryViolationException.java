package Exceptions;

public class BoundaryViolationException extends Exception {
	/**
	 * Violacion de Limites
	 */
	private static final long serialVersionUID = 1L;

	public BoundaryViolationException (String msg)
	{
		super(msg);
	}
}
