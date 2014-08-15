package models.exceptions;

public class LocalInvalidoException extends Exception{
	
	private static final long serialVersionUID = 6723005878549627687L;
	
	public LocalInvalidoException(String mensagem) {
		super(mensagem);
	}
}
