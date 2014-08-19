package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import models.exceptions.EventoInvalidoException;
import models.exceptions.LocalInvalidoException;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;

@Entity(name = "evento")
public class Evento {

	@Id
	@GeneratedValue
	private long id;

	@Required
	@MaxLength(value = 40)
	private String titulo;

	@Required
	@MaxLength(value = 450)
	@Column(name = "CONTENT", length = 450)
	private String descricao;

	@Temporal(value = TemporalType.DATE)
	@Required
	private Date data;

	@OneToMany
	private List<Participante> participantes = new ArrayList<Participante>();

	@ElementCollection
	@Enumerated(value = EnumType.ORDINAL)
	@NotNull
	private List<Tema> temas = new ArrayList<Tema>();
	
	@JoinColumn
	@ManyToOne(cascade=CascadeType.ALL)
	private Local local;

	public Evento() {
	}

	public Evento(String titulo, String descricao, Date data, List<Tema> temas, String local)
			throws EventoInvalidoException, LocalInvalidoException {
		setTitulo(titulo);
		setDescricao(descricao);
		setData(data);
		setTemas(temas);
		setLocal(local);
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
		if (titulo == null){
			throw new EventoInvalidoException("Parametro nulo");
			}
			
		if (titulo.length() > 40){
			throw new EventoInvalidoException("Título longo");
			}
			
		this.titulo = titulo;
	}

	public void setDescricao(String descricao) throws EventoInvalidoException {
		if (descricao == null){
			throw new EventoInvalidoException("Parametro nulo");
			}
		
		if (descricao.length() > 450){
			throw new EventoInvalidoException("Descrição longa");
			}
			
		this.descricao = descricao;
	}

	public void setData(Date data) throws EventoInvalidoException {
		if (data == null){
			throw new EventoInvalidoException("Parametro nulo");
			}
			
		if (data.compareTo(new Date()) < 0){
			throw new EventoInvalidoException("Data inválida");
			}
			
		this.data = data;
	}

	public void setTemas(List<Tema> temas) throws EventoInvalidoException {
		if (temas == null){
			throw new EventoInvalidoException("Parametro nulo");
			}
			
		if (data == null){
			throw new EventoInvalidoException("Parametro nulo");
			}	
			
		if (data.compareTo(new Date()) < 0)
			throw new EventoInvalidoException("Data inválida");
			}
		

	public void setLocal(String local) throws LocalInvalidoException{		
		this.local = new Local(local, 25);
	}

	public Local getLocal() {
		return local;
	}
	
	
}



			
