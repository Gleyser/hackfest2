package controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import models.Evento;
import models.Participante;
import models.Tema;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import models.exceptions.EventoInvalidoException;
import models.exceptions.PessoaInvalidaException;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {
	
	private static boolean criouEventosFake = false;
	private static GenericDAO dao = new GenericDAOImpl();

	@Transactional
    public static Result index(){
		if (!criouEventosFake){
			List<Evento> eventos = criarEventosFakes();
			criarParticipacoesFake(eventos);

			criouEventosFake = true;
		}
        return ok(index.render());
    }

	public static GenericDAO getDao(){
		return dao;
	}

	private static List<Evento> criarEventosFakes() {
		try {
			List<Evento> eventos = new ArrayList<Evento>();
			Evento evento;
			Calendar calendar;
	
			List<Tema> temas = new ArrayList<Tema>();
			
			temas.add(Tema.DESAFIOS);
			temas.add(Tema.PROGRAMACAO);
			
			calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_WEEK, 7);
			
			evento = new Evento("Python na mente e coração", "Neste evento iremos debater e propor soluções para novas releases.", calendar.getTime(), temas);
			eventos.add(evento);
			criarEvento(evento);
			
			temas = new ArrayList<Tema>();
			temas.add(Tema.ARDUINO);
			temas.add(Tema.ELETRONICA);
			
			calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_WEEK, 3);

			evento = new Evento("Luta de robôs", "Traga seu robô feito em arduino e traga para competir com outros.", calendar.getTime(), temas);
			eventos.add(evento);
			criarEvento(evento);
	
			temas = new ArrayList<Tema>();
			temas.add(Tema.DESAFIOS);
			temas.add(Tema.PROGRAMACAO);
			
			calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, 1);

			evento = new Evento("IV Olímpiadas de programação da UFCG", "Traga sua equipe e venha competir nessa maratona de programação.", calendar.getTime(), temas);
			eventos.add(evento);
			criarEvento(evento);
	
			temas = new ArrayList<Tema>();
			temas.add(Tema.DESAFIOS);
			temas.add(Tema.PROGRAMACAO);
			
			calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_WEEK, 12);

			evento = new Evento("II Encontro para programadores de Python", "O encontro contará com a participação de um de seus fundadores, inúmeras palestras e maratonas. Não percam!!", calendar.getTime(), temas);
			eventos.add(evento);
			criarEvento(evento);
	
			temas = new ArrayList<Tema>();
			temas.add(Tema.PROGRAMACAO);
			temas.add(Tema.DESAFIOS);
			
			calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, 2);
			calendar.add(Calendar.DAY_OF_WEEK, 3);

			evento = new Evento("III Semana da Computação Verde", "Exiba sua proposta para uma computação mais verde e concorra a diversos prêmios", calendar.getTime(), temas);
			eventos.add(evento);
			criarEvento(evento);
	
			temas = new ArrayList<Tema>();
			temas.add(Tema.PROGRAMACAO);
			temas.add(Tema.WEB);
			
			calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_WEEK, 17);

			evento = new Evento("Web em foco", "Este evento contará com a participação de um dos fundadores da Web, e juntos iremos compartilhar diversas dicas e boas práticas nessa vasta área.", calendar.getTime(), temas);
			eventos.add(evento);
			criarEvento(evento);
	
			temas = new ArrayList<Tema>();
			temas.add(Tema.ELETRONICA);
			temas.add(Tema.ARDUINO);
			
			calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_WEEK, 5);

			evento = new Evento("Minicurso Arduino", "Evento destinado a alunos de LOAC, caso sobre vagas iremos disponibilizar em breve", calendar.getTime(), temas);
			eventos.add(evento);
			criarEvento(evento);
	
			temas = new ArrayList<Tema>();
			temas.add(Tema.ELETRONICA);
			temas.add(Tema.ARDUINO);
			
			calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_WEEK, 21);

			evento = new Evento("Curto circuito", "Evento sobre circuitos eletrônicos, venha dar curto em seus circuitos também!!", calendar.getTime(), temas);
			eventos.add(evento);
			criarEvento(evento);
	
			temas = new ArrayList<Tema>();
			temas.add(Tema.DESAFIOS);
			
			calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_WEEK, 15);

			evento = new Evento("VI Encontro de Docentes de CC", "Evento para debatermos propostas e soluções para os problemas enfrentados pelos alunos de CC.", calendar.getTime(), temas);
			eventos.add(evento);
			criarEvento(evento);

			temas = new ArrayList<Tema>();
			temas.add(Tema.PROGRAMACAO);
			temas.add(Tema.DESAFIOS);
			
			calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_WEEK, 8);

			evento = new Evento("Café com Java", "Curso destinado apenas a alunos cursando a disciplina LP2.", calendar.getTime(), temas);
			eventos.add(evento);
			criarEvento(evento);
			
			return eventos;
		} catch (EventoInvalidoException _) {
			return null;
		}
	}
	
	private static void criarParticipacoesFake(List<Evento> eventos) {
		Random rnd = new Random();
		try {
			criarParticipacao(new Participante("Alberto Leça", "alberto_leca@mail.com", eventos.get(rnd.nextInt(3))));
			criarParticipacao(new Participante("Alberto Leça", "alberto_leca@mail.com", eventos.get(rnd.nextInt(3))));
			criarParticipacao(new Participante("Alberto Leça", "alberto_leca@mail.com", eventos.get(rnd.nextInt(3))));
			criarParticipacao(new Participante("Belmifer Linares", "belmifer_linares@mail.com", eventos.get(rnd.nextInt(3))));
			criarParticipacao(new Participante("Belmifer Linares", "belmifer_linares@mail.com", eventos.get(rnd.nextInt(3))));
			criarParticipacao(new Participante("Célia Rúa", "celia_rua@mail.com", eventos.get(rnd.nextInt(3))));
			criarParticipacao(new Participante("Deolindo Castello Branco", "deolindo_castello@mail.com", eventos.get(rnd.nextInt(3))));
			criarParticipacao(new Participante("Doroteia Pasos", "doroteia_passos@mail.com", eventos.get(rnd.nextInt(3))));
			criarParticipacao(new Participante("Eugénio Palhares", "eugenio_palhares@mail.com", eventos.get(rnd.nextInt(3))));
			criarParticipacao(new Participante("Fausto Furtado", "fausto_furtado@mail.com", eventos.get(rnd.nextInt(3))));
			criarParticipacao(new Participante("Filipa Leiria", "filipa_leiria@mail.com", eventos.get(rnd.nextInt(3))));
			criarParticipacao(new Participante("Leonilde Figueiredo", "leonilde_figueiredo@mail.com", eventos.get(rnd.nextInt(3))));
			criarParticipacao(new Participante("Pascoal Caldeira", "pascoal_caldeira@mail.com", eventos.get(rnd.nextInt(3))));
			criarParticipacao(new Participante("Paula Lousado", "paula_lousado@mail.com", eventos.get(rnd.nextInt(3))));
			criarParticipacao(new Participante("Quitério Galindo","quiterio_galindo@mail.com", eventos.get(rnd.nextInt(3))));
			criarParticipacao(new Participante("Rosa Varejão", "rosa_varejao@mail.com", eventos.get(rnd.nextInt(3))));
			criarParticipacao(new Participante("Sonia Gabeira", "sonia_gabeira@mail.com", eventos.get(rnd.nextInt(3))));
			criarParticipacao(new Participante("Érico Albuquerque", "erico_albuquerque@mail.com", eventos.get(rnd.nextInt(3))));
			criarParticipacao(new Participante("Érico Albuquerque", "erico_albuquerque@mail.com", eventos.get(rnd.nextInt(3))));
			criarParticipacao(new Participante("Tairine Reis", "tairine_reis@mail.com", eventos.get(rnd.nextInt(3))));
		} catch (PessoaInvalidaException _) { }
	}
	
	@Transactional
	private static void criarEvento(Evento evento) {
		dao.persist(evento);
		dao.merge(evento);
		dao.flush();
	}
	
	@Transactional
	private static void criarParticipacao(Participante participante) {
		dao.persist(participante);
		dao.flush();
	}

}
