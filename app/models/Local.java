package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;

import models.exceptions.LocalInvalidoException;

@Entity
public class Local {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Required
	@MaxLength(value = 40)
	private String nome;
	
	@Required
	@Column
	private int capacidade;
	
	public Local() {
		
	}
	
	public Local(String nome, int capacidade) throws LocalInvalidoException {
		setNome(nome);
		setCapacidade(capacidade);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws LocalInvalidoException {
		if (nome.length() > 40){
			throw new LocalInvalidoException("Nome longo");
		}
		this.nome = nome;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) throws LocalInvalidoException {
		if (capacidade < 0){
			throw new LocalInvalidoException("Capacidade negativa");
		}
		this.capacidade = capacidade;
	}
	
	
	
	

}
