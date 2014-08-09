package models.exceptions;

public class PessoaInvalidaException extends Exception {

	private static final long serialVersionUID = 6723006878549127637L;

	public PessoaInvalidaException(String mensagem) {
		super(mensagem);
	}
}
