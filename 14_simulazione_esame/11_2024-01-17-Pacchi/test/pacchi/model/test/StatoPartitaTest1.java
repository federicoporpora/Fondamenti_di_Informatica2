package pacchi.model.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import pacchi.model.Numero;
import pacchi.model.Pacco;
import pacchi.model.Valore;
import pacchi.model.StatoPartita;
import pacchi.model.Territorio;

class StatoPartitaTest1 {
	
	// collauda la parte "statica" dello stato partita, ossia la sua corretta configurazione iniziale

	@Test
	void testOK() {
		var pacchi = new HashSet<Pacco>(Set.of(
					new Pacco(new Territorio("Dentinia"),  new Numero(5), new Valore(100000)),
					new Pacco(new Territorio("Topolinia"), new Numero(3), new Valore(10000)),
					new Pacco(new Territorio("Paperopoli"), new Numero(2), new Valore(10))
					));
		var concorrente = new Pacco(new Territorio("Alaska"), new Numero(1), new Valore(1));
				
		var stato = new StatoPartita(pacchi, concorrente);
		assertEquals(pacchi, stato.getPacchiDaAprire());
		assertEquals(concorrente, stato.getPaccoConcorrente());
		assertEquals(pacchi.size(), stato.quantiPacchiDaAprire());
		//assertEquals(pacchi.stream().map(Pacco::premio).mapToInt(Valore::valore).average().getAsDouble(), stato.media(), 0.01);
	}
	
	@Test
	void testKO_PaccoConcorrenteAncheInLista_Identicamente() {
		var pacchi = new HashSet<Pacco>(Set.of(
					new Pacco(new Territorio("Dentinia"),  new Numero(5), new Valore(100000)),
					new Pacco(new Territorio("Topolinia"), new Numero(3), new Valore(10000)),
					new Pacco(new Territorio("Paperopoli"), new Numero(2), new Valore(10))
					));
		var concorrente = new Pacco(new Territorio("Dentinia"), new Numero(5), new Valore(100000));
		assertThrows(IllegalArgumentException.class, () -> new StatoPartita(pacchi, concorrente));
	}
	
	@Test
	void testKO_PaccoConcorrenteAncheInListaPerNome() {
		var pacchi = new HashSet<Pacco>(Set.of(
					new Pacco(new Territorio("Dentinia"),  new Numero(5), new Valore(100000)),
					new Pacco(new Territorio("Topolinia"), new Numero(3), new Valore(10000)),
					new Pacco(new Territorio("Paperopoli"), new Numero(2), new Valore(10))
					));
		var concorrente = new Pacco(new Territorio("Dentinia"), new Numero(7), new Valore(70000));
		assertThrows(IllegalArgumentException.class, () -> new StatoPartita(pacchi, concorrente));
	}
	
	@Test
	void testKO_PaccoConcorrenteAncheInListaPerNumero() {
		var pacchi = new HashSet<Pacco>(Set.of(
					new Pacco(new Territorio("Dentinia"),  new Numero(5), new Valore(100000)),
					new Pacco(new Territorio("Topolinia"), new Numero(3), new Valore(10000)),
					new Pacco(new Territorio("Paperopoli"), new Numero(2), new Valore(10))
					));
		var concorrente = new Pacco(new Territorio("Alabama"), new Numero(2), new Valore(200000));
		assertThrows(IllegalArgumentException.class, () -> new StatoPartita(pacchi, concorrente));
	}
	
	@Test
	void testKO_PaccoConcorrenteAncheInListaPerPremio() {
		var pacchi = new HashSet<Pacco>(Set.of(
					new Pacco(new Territorio("Dentinia"),  new Numero(5), new Valore(100000)),
					new Pacco(new Territorio("Topolinia"), new Numero(3), new Valore(10000)),
					new Pacco(new Territorio("Paperopoli"), new Numero(2), new Valore(10))
					));
		var concorrente = new Pacco(new Territorio("Alabama"), new Numero(7), new Valore(10));
		assertThrows(IllegalArgumentException.class, () -> new StatoPartita(pacchi, concorrente));
	}
	@Test
	void testKO_PaccoDuplicatoInLista_StessoNome() {
		var pacchi = new HashSet<Pacco>(Set.of(
					new Pacco(new Territorio("Dentinia"),  new Numero(5), new Valore(100000)),
					new Pacco(new Territorio("Dentinia"), new Numero(3), new Valore(10000)),
					new Pacco(new Territorio("Paperopoli"), new Numero(2), new Valore(10))
					));
		var concorrente = new Pacco(new Territorio("Alaska"), new Numero(1), new Valore(1));
		assertThrows(IllegalArgumentException.class, () -> new StatoPartita(pacchi, concorrente));
	}
	
	@Test
	void testKO_PaccoDuplicatoInLista_StessoNumero() {
		var pacchi = new HashSet<Pacco>(Set.of(
					new Pacco(new Territorio("Dentinia"),  new Numero(5), new Valore(100000)),
					new Pacco(new Territorio("Topolinia"), new Numero(5), new Valore(10000)),
					new Pacco(new Territorio("Paperopoli"), new Numero(2), new Valore(10))
					));
		var concorrente = new Pacco(new Territorio("Alaska"), new Numero(1), new Valore(1));
		assertThrows(IllegalArgumentException.class, () -> new StatoPartita(pacchi, concorrente));
	}
	
	@Test
	void testKO_PaccoDuplicatoInLista_StessoValore() {
		var pacchi = new HashSet<Pacco>(Set.of(
					new Pacco(new Territorio("Dentinia"),  new Numero(5), new Valore(100000)),
					new Pacco(new Territorio("Topolinia"), new Numero(3), new Valore(10000)),
					new Pacco(new Territorio("Paperopoli"), new Numero(2), new Valore(10000))
					));
		var concorrente = new Pacco(new Territorio("Alaska"), new Numero(1), new Valore(1));
		assertThrows(IllegalArgumentException.class, () -> new StatoPartita(pacchi, concorrente));
	}



}
