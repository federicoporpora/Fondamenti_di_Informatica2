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

class StatoPartitaTest2 {
	
	// collauda la parte "dinamica" dello stato partita, ossia l'apertura pacchi

	@Test
	void testOK_Iniziale() { //uguale a quello di StatoPartitaTest1
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
	void testOK_ApriPacco() {
		var pacchi = new HashSet<Pacco>(Set.of(
					new Pacco(new Territorio("Dentinia"),  new Numero(5), new Valore(100000)),
					new Pacco(new Territorio("Topolinia"), new Numero(3), new Valore(10000)),
					new Pacco(new Territorio("Paperopoli"), new Numero(2), new Valore(10))
					));
		var concorrente = new Pacco(new Territorio("Alaska"), new Numero(1), new Valore(1));	
		var stato = new StatoPartita(pacchi, concorrente);
		
		//--- apro un pacco --> ne restano solo 2
		
		var paccoDaAprire = pacchi.iterator().next();
		stato.apriPacco(paccoDaAprire);
		
		assertTrue(stato.getPacchiAperti().contains(paccoDaAprire));
		assertNotEquals(pacchi, stato.getPacchiDaAprire());
		assertEquals(concorrente, stato.getPaccoConcorrente());
		assertEquals(pacchi.size()-1, stato.quantiPacchiDaAprire());
		
		pacchi.remove(paccoDaAprire);
		assertEquals(pacchi.size(), stato.quantiPacchiDaAprire());
		//assertEquals(pacchi.stream().map(Pacco::premio).mapToInt(Valore::valore).average().getAsDouble(), stato.media(), 0.01);
		
		//--- apro un altro pacco  --> ne resta solo 1
		
		paccoDaAprire = pacchi.iterator().next();
		stato.apriPacco(paccoDaAprire);
		
		assertTrue(stato.getPacchiAperti().contains(paccoDaAprire));
		assertNotEquals(pacchi, stato.getPacchiDaAprire());
		assertEquals(concorrente, stato.getPaccoConcorrente());
		assertEquals(pacchi.size()-1, stato.quantiPacchiDaAprire());
		
		pacchi.remove(paccoDaAprire);
		assertEquals(pacchi.size(), stato.quantiPacchiDaAprire());
		//assertEquals(pacchi.stream().map(Pacco::premio).mapToInt(Valore::valore).average().getAsDouble(), stato.media(), 0.01);
		
		//--- apro l'ultimo pacco  --> non ne resta più nessuno
		
		paccoDaAprire = pacchi.iterator().next();
		stato.apriPacco(paccoDaAprire);
		
		assertTrue(stato.getPacchiAperti().contains(paccoDaAprire));
		assertNotEquals(pacchi, stato.getPacchiDaAprire());
		assertEquals(concorrente, stato.getPaccoConcorrente());
		assertEquals(0, stato.quantiPacchiDaAprire());
		
		pacchi.remove(paccoDaAprire);
		assertEquals(pacchi.size(), stato.quantiPacchiDaAprire());
		assertTrue(pacchi.stream().map(Pacco::premio).mapToInt(Valore::valore).average().isEmpty());
	}

	@Test
	void testKO_ApriPaccoDelConcorrente() {
		var pacchi = new HashSet<Pacco>(Set.of(
					new Pacco(new Territorio("Dentinia"),  new Numero(5), new Valore(100000)),
					new Pacco(new Territorio("Topolinia"), new Numero(3), new Valore(10000)),
					new Pacco(new Territorio("Paperopoli"), new Numero(2), new Valore(10))
					));
		var concorrente = new Pacco(new Territorio("Alaska"), new Numero(1), new Valore(1));	
		var stato = new StatoPartita(pacchi, concorrente);
		
		//--- apro un pacco che non è in lista --> addirittura quello del concorrente --> impossibile!
		
		var paccoDaAprire = concorrente; // IMPOSSIBILE!!
		assertThrows(IllegalArgumentException.class, () -> stato.apriPacco(paccoDaAprire));
	}
	
	@Test
	void testKO_ApriPaccoNonEsistente() {
		var pacchi = new HashSet<Pacco>(Set.of(
					new Pacco(new Territorio("Dentinia"),  new Numero(5), new Valore(100000)),
					new Pacco(new Territorio("Topolinia"), new Numero(3), new Valore(10000)),
					new Pacco(new Territorio("Paperopoli"), new Numero(2), new Valore(10))
					));
		var concorrente = new Pacco(new Territorio("Alaska"), new Numero(1), new Valore(1));	
		var stato = new StatoPartita(pacchi, concorrente);
		
		//--- apro un pacco che non è in lista --> impossibile!
		
		var paccoDaAprire = new Pacco(new Territorio("California"), new Numero(7), new Valore(20000));
		assertThrows(IllegalArgumentException.class, () -> stato.apriPacco(paccoDaAprire));
	}
	
	@Test
	void testKO_ApriPaccoNullo() {
		var pacchi = new HashSet<Pacco>(Set.of(
					new Pacco(new Territorio("Dentinia"),  new Numero(5), new Valore(100000)),
					new Pacco(new Territorio("Topolinia"), new Numero(3), new Valore(10000)),
					new Pacco(new Territorio("Paperopoli"), new Numero(2), new Valore(10))
					));
		var concorrente = new Pacco(new Territorio("Alaska"), new Numero(1), new Valore(1));	
		var stato = new StatoPartita(pacchi, concorrente);
		
		//--- apro un pacco nullo --> impossibile!
		
		assertThrows(IllegalArgumentException.class, () -> stato.apriPacco(null));
	}
}
