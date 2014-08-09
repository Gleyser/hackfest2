package models.exceptions;


public class EventoInvalidoException extends Exception {

	private static final long serialVersionUID = 704300588187712921L;

	public EventoInvalidoException(String mensagem) {
		super(mensagem);
	}
}
