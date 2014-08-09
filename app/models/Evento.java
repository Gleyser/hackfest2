package models;
// Testando
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import models.exceptions.EventoInvalidoException;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;

@Entity
public class Evento {
	
	private final int tamanhoMaximoDaDescricao = 450;
	private final int tamanhoMaximoDoTitulo = 40;

	@Id
	@GeneratedValue
	private long id;

	@Required
	@MaxLength(value = tamanhoMaximoDoTitulo)
	private String titulo;

	@Required
	@MaxLength(value = tamanhoMaximoDaDescricao)
	@Column(name = "CONTENT", length = tamanhoMaximoDaDescricao)
	private String descricao;

	@Temporal(value = TemporalType.DATE)
	@Required
	private Date data;

	@OneToMany(mappedBy = "evento")
	private List<Participante> participantes = new ArrayList<Participante>();

	@ElementCollection
	@Enumerated(value = EnumType.ORDINAL)
	@NotNull
	private List<Tema> temas = new ArrayList<Tema>();
	
	
	public Evento() {
	}

	public Evento(String titulo, String descricao, Date data, List<Tema> temas)
			throws EventoInvalidoException {
		this.titulo = titulo;
		this.descricao = descricao;
		this.data = data;
		this.temas = temas;		
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Date getData() {
		return data;
	}

	public long getId() {
		return id;
	}

	public Integer getTotalDeParticipantes() {
		return participantes.size();
	}

	public List<Tema> getTemas() {
		return temas;
	}

	public void setTitulo(String titulo) throws EventoInvalidoException {
		if (titulo == null)
			throw new EventoInvalidoException("Parametro nulo");
		if (titulo.length() > tamanhoMaximoDoTitulo)
			throw new EventoInvalidoException("Título longo");
		this.titulo = titulo;
	}

	public void setDescricao(String descricao) throws EventoInvalidoException {
		if (descricao == null)
			throw new EventoInvalidoException("Parametro nulo");
		if (descricao.length() > tamanhoMaximoDaDescricao)
			throw new EventoInvalidoException("Descrição longa");
		this.descricao = descricao;
	}

	public void setData(Date data) throws EventoInvalidoException {
		if (data == null)
			throw new EventoInvalidoException("Parametro nulo");
		if (data.compareTo(new Date()) < 0)
			throw new EventoInvalidoException("Data inválida");
		this.data = data;
	}

	public void setTemas(List<Tema> temas) throws EventoInvalidoException {
		if (temas == null)
			throw new EventoInvalidoException("Parametro nulo");
		if (temas.size() == 0)
			throw new EventoInvalidoException("Nenhum tema");
		this.temas = temas;
	}

	public List<Participante> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<Participante> participantes) {
		this.participantes = participantes;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((participantes == null) ? 0 : participantes.hashCode());
		result = prime * result + ((temas == null) ? 0 : temas.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		
		if (!titulo.equals(other.titulo))
			return false;
		return true;
	}
	
	
}
