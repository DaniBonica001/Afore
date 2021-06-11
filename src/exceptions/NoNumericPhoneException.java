package exceptions;

public class NoNumericPhoneException extends Exception{
	/*
	 Esta clase de excepciones va a ir en todos los crear que tengan telefonos como verificacion para no tener errores
	 */
	private static final long serialVersionUID = 1L;

	public NoNumericPhoneException(){
		super("Existe un error, el numero de telefono debe ser numerico");
	}
}

