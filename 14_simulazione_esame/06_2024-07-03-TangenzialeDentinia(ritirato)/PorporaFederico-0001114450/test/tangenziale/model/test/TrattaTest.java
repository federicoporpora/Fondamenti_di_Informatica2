package tangenziale.model.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import tangenziale.model.Autostrada;
import tangenziale.model.Tipologia;
import tangenziale.model.Tratta;

class TrattaTest {

	private static Autostrada autostrada1, autostrada2, tangenziale;

	@BeforeAll
	public static void setup() {
		autostrada1 = new Autostrada(Tipologia.AUTOSTRADA,  "M1", new TreeMap<>(Map.of(0, "Civita", 14, "Castello", 29, "Forte", 44, "Urbello", 62, "Tangenziale")));
		autostrada2 = new Autostrada(Tipologia.AUTOSTRADA,  "M2", new TreeMap<>(Map.of(0, "Tangenziale", 16, "Castelbrutto", 39, "Cinisello", 64, "Val Duero")));
		tangenziale = new Autostrada(Tipologia.TANGENZIALE, "Tangenziale", new TreeMap<>(Map.of(0, "strada Marina", 6, "M1", 11, "Stadio", 15, "Interporto", 21, "M2", 32, "Aeroporto")));
	}
	
	
	@Test
	void testOK() {
		var tratta1 = new Tratta("Civita", "Tangenziale", autostrada1);
		var tratta2 = new Tratta("M1", "M2", tangenziale);
		var tratta3 = new Tratta("Tangenziale", "Cinisello", autostrada2);
		assertEquals(62, tratta1.km());
		assertEquals(15, tratta2.km());
		assertEquals(39, tratta3.km());
		assertEquals("Civita", 		tratta1.inizio());
		assertEquals("Tangenziale", tratta1.fine());
		assertEquals("M1", tratta2.inizio());
		assertEquals("M2", tratta2.fine());
		assertEquals("Tangenziale", tratta3.inizio());
		assertEquals("Cinisello", 	tratta3.fine());
	}
	
	@Test
	void testKO_NullInizio() {
		assertThrows(NullPointerException.class, () -> new Tratta(null, "Tangenziale", autostrada1));
	}
	
	@Test
	void testKO_NullFine() {
		assertThrows(NullPointerException.class, () -> new Tratta("Civita", null, autostrada1));
	}
	
	@Test
	void testKO_NullAutostrada() {
		assertThrows(NullPointerException.class, () -> new Tratta("Civita", "Tangenziale", null));
	}
	
	@Test
	void testKO_InizioVuoto() {
		assertThrows(IllegalArgumentException.class, () -> new Tratta(" ", "Tangenziale", autostrada1));
	}
	
	@Test
	void testKO_FineVuoto() {
		assertThrows(IllegalArgumentException.class, () -> new Tratta("Civita", " ", autostrada1));
	}

}
