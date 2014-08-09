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
				
			List<Tema> temas = new ArrayList<Tema>();
			
			temas.add(Tema.DESAFIOS);
			temas.add(Tema.PROGRAMACAO);
			auxiliar("Python na mente e coração", "Neste evento iremos debater e propor soluções para novas releases.", 7, temas, eventos);
									
			temas = new ArrayList<Tema>();
			temas.add(Tema.ARDUINO);
			temas.add(Tema.ELETRONICA);
			auxiliar("Luta de robôs", "Traga seu robô feito em arduino e traga para competir com outros.", 3, temas, eventos);
			
			temas = new ArrayList<Tema>();
			temas.add(Tema.DESAFIOS);
			temas.add(Tema.PROGRAMACAO);
			auxiliar("IV Olímpiadas de programação da UFCG", "Traga sua equipe e venha competir nessa maratona de programação.", 1, temas, eventos);
			
			temas = new ArrayList<Tema>();
			temas.add(Tema.DESAFIOS);
			temas.add(Tema.PROGRAMACAO);
			auxiliar("II Encontro para programadores de Python", "O encontro contará com a participação de um de seus fundadores, inúmeras palestras e maratonas. Não percam!!", 12, temas, eventos);
			
	
			temas = new ArrayList<Tema>();
			temas.add(Tema.PROGRAMACAO);
			temas.add(Tema.DESAFIOS);
			auxiliar("III Semana da Computação Verde", "Exiba sua proposta para uma computação mais verde e concorra a diversos prêmios", 3, temas, eventos);
			
			temas = new ArrayList<Tema>();
			temas.add(Tema.PROGRAMACAO);
			temas.add(Tema.WEB);
			auxiliar("Web em foco", "Este evento contará com a participação de um dos fundadores da Web, e juntos iremos compartilhar diversas dicas e boas práticas nessa vasta área.",17, temas, eventos);
			
			temas = new ArrayList<Tema>();
			temas.add(Tema.ELETRONICA);
			temas.add(Tema.ARDUINO);
			auxiliar("Minicurso Arduino", "Evento destinado a alunos de LOAC, caso sobre vagas iremos disponibilizar em breve", 5, temas, eventos);
				
			temas = new ArrayList<Tema>();
			temas.add(Tema.ELETRONICA);
			temas.add(Tema.ARDUINO);
			auxiliar("Curto circuito", "Evento sobre circuitos eletrônicos, venha dar curto em seus circuitos também!!", 21, temas, eventos);
				
			temas = new ArrayList<Tema>();
			temas.add(Tema.DESAFIOS);
			auxiliar("VI Encontro de Docentes de CC", "Evento para debatermos propostas e soluções para os problemas enfrentados pelos alunos de CC.", 15, temas, eventos);
			
			temas = new ArrayList<Tema>();
			temas.add(Tema.PROGRAMACAO);
			temas.add(Tema.DESAFIOS);
			auxiliar("Café com Java", "Curso destinado apenas a alunos cursando a disciplina LP2.", 8, temas, eventos);
						
			return eventos;
		} catch (EventoInvalidoException e) {
			return null;
		}
	}
	
	private static void auxiliar(String tema, String descricao, int dia, List<Tema> temas, List<Evento> eventos) throws EventoInvalidoException{
		Calendar calendar;
		calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_WEEK, dia);
		Evento evento = new Evento(tema, descricao, calendar.getTime(), temas);
		eventos.add(evento);
		criarEvento(evento);
		
	}
	
	private static void criarParticipacoesFake(List<Evento> eventos) {
		Random rnd = new Random();
		try {
			int numDeRandon = 3;
			criarParticipacao(new Participante("Alberto Leça", "alberto_leca@mail.com", eventos.get(rnd.nextInt(numDeRandon))));
			criarParticipacao(new Participante("Alberto Leça", "alberto_leca@mail.com", eventos.get(rnd.nextInt(numDeRandon))));
			criarParticipacao(new Participante("Alberto Leça", "alberto_leca@mail.com", eventos.get(rnd.nextInt(numDeRandon))));
			criarParticipacao(new Participante("Belmifer Linares", "belmifer_linares@mail.com", eventos.get(rnd.nextInt(numDeRandon))));
			criarParticipacao(new Participante("Belmifer Linares", "belmifer_linares@mail.com", eventos.get(rnd.nextInt(numDeRandon))));
			criarParticipacao(new Participante("Célia Rúa", "celia_rua@mail.com", eventos.get(rnd.nextInt(numDeRandon))));
			criarParticipacao(new Participante("Deolindo Castello Branco", "deolindo_castello@mail.com", eventos.get(rnd.nextInt(numDeRandon))));
			criarParticipacao(new Participante("Doroteia Pasos", "doroteia_passos@mail.com", eventos.get(rnd.nextInt(numDeRandon))));
			criarParticipacao(new Participante("Eugénio Palhares", "eugenio_palhares@mail.com", eventos.get(rnd.nextInt(numDeRandon))));
			criarParticipacao(new Participante("Fausto Furtado", "fausto_furtado@mail.com", eventos.get(rnd.nextInt(numDeRandon))));
			criarParticipacao(new Participante("Filipa Leiria", "filipa_leiria@mail.com", eventos.get(rnd.nextInt(numDeRandon))));
			criarParticipacao(new Participante("Leonilde Figueiredo", "leonilde_figueiredo@mail.com", eventos.get(rnd.nextInt(numDeRandon))));
			criarParticipacao(new Participante("Pascoal Caldeira", "pascoal_caldeira@mail.com", eventos.get(rnd.nextInt(numDeRandon))));
			criarParticipacao(new Participante("Paula Lousado", "paula_lousado@mail.com", eventos.get(rnd.nextInt(numDeRandon))));
			criarParticipacao(new Participante("Quitério Galindo","quiterio_galindo@mail.com", eventos.get(rnd.nextInt(numDeRandon))));
			criarParticipacao(new Participante("Rosa Varejão", "rosa_varejao@mail.com", eventos.get(rnd.nextInt(numDeRandon))));
			criarParticipacao(new Participante("Sonia Gabeira", "sonia_gabeira@mail.com", eventos.get(rnd.nextInt(numDeRandon))));
			criarParticipacao(new Participante("Érico Albuquerque", "erico_albuquerque@mail.com", eventos.get(rnd.nextInt(numDeRandon))));
			criarParticipacao(new Participante("Érico Albuquerque", "erico_albuquerque@mail.com", eventos.get(rnd.nextInt(numDeRandon))));
			criarParticipacao(new Participante("Tairine Reis", "tairine_reis@mail.com", eventos.get(rnd.nextInt(numDeRandon))));
		} catch (PessoaInvalidaException e) { }
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
