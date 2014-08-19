package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import models.exceptions.PessoaInvalidaException;

import org.hibernate.validator.constraints.Email;

import play.data.validation.Constraints.MaxLength;

@Entity
public class Participante {
	
	private final int tamMaximo = 70;

	private final String email_patern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@Id
	@GeneratedValue
	private long id;

	@NotNull
	@MaxLength(value = 70)
	private String nome;

	@Email
	@NotNull
	@MaxLength(value = 70)
	private String email;

	@ManyToOne
	private Evento evento;

	public Participante() {
	}

	public Participante(String nome, String email, Evento evento)
			throws PessoaInvalidaException {
		this.nome = nome;
		this.email =email;
		this.evento = evento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws PessoaInvalidaException {
		if (nome == null){
			throw new PessoaInvalidaException("Parametro nulo");
		}
			
		if (nome.length() > tamMaximo){
			throw new PessoaInvalidaException("Nome longo");
		}
			
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws PessoaInvalidaException {
		if (email == null){
			throw new PessoaInvalidaException("Parametro nulo");
		}
			
		if (!email.matches(email_patern)){
			throw new PessoaInvalidaException("Email inválido");
		}
			
		if (email.length() > tamMaximo){
			throw new PessoaInvalidaException("Email longo");
		}
			
		this.email = email;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) throws PessoaInvalidaException {
		if (evento == null){
			throw new PessoaInvalidaException("Parametro nulo");
		}
			
		this.evento = evento;
	}
}
