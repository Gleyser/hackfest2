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
	private final int tamanhoMaximoDoEmail = 70;
	private final int tamanhoMaximoDoNome = 70;
	private final String controleDeCaracteresDoEmail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@Id
	@GeneratedValue
	private long id;

	@NotNull
	@MaxLength(value = tamanhoMaximoDoNome)
	private String nome;

	@Email
	@NotNull
	@MaxLength(value = tamanhoMaximoDoEmail)
	private String email;

	@ManyToOne
	private Evento evento;

	public Participante() {
	}

	public Participante(String nome, String email, Evento evento)
			throws PessoaInvalidaException {
		this.nome = nome;
		this.email = email;
		this.evento = evento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws PessoaInvalidaException {
		if (nome == null)
			throw new PessoaInvalidaException("Parametro nulo");
		if (nome.length() > tamanhoMaximoDoNome)
			throw new PessoaInvalidaException("Nome longo");
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws PessoaInvalidaException {
		if (email == null)
			throw new PessoaInvalidaException("Parametro nulo");
		if (!email.matches(controleDeCaracteresDoEmail))
			throw new PessoaInvalidaException("Email inválido");
		if (email.length() > tamanhoMaximoDoEmail)
			throw new PessoaInvalidaException("Email longo");
		this.email = email;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) throws PessoaInvalidaException {
		if (evento == null)
			throw new PessoaInvalidaException("Parametro nulo");
		this.evento = evento;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmailPattern() {
		return controleDeCaracteresDoEmail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((evento == null) ? 0 : evento.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Participante other = (Participante) obj;
	
		if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	
}
