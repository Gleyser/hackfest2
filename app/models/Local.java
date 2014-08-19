package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import models.exceptions.LocalInvalidoException;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;

@Entity(name = "local")
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

	@OneToMany(cascade = CascadeType.ALL)
	private List<Evento> eventos;

	public Local() {

	}

	public Local(String nome, int capacidade) throws LocalInvalidoException {
		setNome(nome);
		setCapacidade(capacidade);
		this.eventos = new ArrayList<Evento>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws LocalInvalidoException {
		if (nome.length() > 40) {
			throw new LocalInvalidoException("Nome longo");
		}
		this.nome = nome;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) throws LocalInvalidoException {
		if (capacidade < 0) {
			throw new LocalInvalidoException("Capacidade negativa");
		}
		this.capacidade = capacidade;
	}

	public List<Evento> getEventos(Evento evento) {
		return this.eventos;
	}

	public void addEvento(Evento evento) {
		this.eventos.add(evento);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}
	
	

}
