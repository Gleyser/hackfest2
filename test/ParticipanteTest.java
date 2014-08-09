import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Evento;
import models.Participante;
import models.Tema;
import models.exceptions.EventoInvalidoException;
import models.exceptions.PessoaInvalidaException;

import org.junit.Before;
import org.junit.Test;

public class ParticipanteTest {

	private Evento evento;
	private List<Tema> temas;
	
	@Before
	public void setUp(){
		temas = new ArrayList<>();
		temas.add(Tema.ARDUINO);
		try {
			evento = new Evento("Python na cabeça", "Vamos programar em Python!", new Date(), temas);
		} catch (EventoInvalidoException e) {
			fail();
		}
	}
	
	@Test
	public void deveCriarUmParticipante() {
		try {
			new Participante("João José da Silva", "joao_jose@mail.com", evento);
		} catch (PessoaInvalidaException e) {
			fail();
		}
	}

	@Test
	public void deveOcorrerException() {
		try {
			new Participante("João José da Silva Maria da Penha do Ultimo Socorro Pereira Lima Roberto", "joao_jose@mail.com", evento);
			fail();
		} catch (PessoaInvalidaException e) {
			assertEquals("Nome longo", e.getMessage());
		}
		try {
			new Participante("João José da Silva", "joao_jose_da_silva_maria_da_penha_do_ultimo_socorro_pereira_lima@mail.com", evento);
			fail();
		} catch (PessoaInvalidaException e) {
			assertEquals("Email longo", e.getMessage());
		}
		try {
			new Participante(null, "joao_jose@mail.com", evento);
			fail();
		} catch (PessoaInvalidaException e) {
			assertEquals("Parametro nulo", e.getMessage());
		}
		try {
			new Participante("João José da Silva", null, evento);
			fail();
		} catch (PessoaInvalidaException e) {
			assertEquals("Parametro nulo", e.getMessage());
		}
		try {
			new Participante("João José da Silva", null, null);
			fail();
		} catch (PessoaInvalidaException e) {
			assertEquals("Parametro nulo", e.getMessage());
		}
		try {
			new Participante("João José da Silva", "joao_jose_mail.com", evento);
			fail();
		} catch (PessoaInvalidaException e) {
			assertEquals("Email inválido", e.getMessage());
		}
	}
}
