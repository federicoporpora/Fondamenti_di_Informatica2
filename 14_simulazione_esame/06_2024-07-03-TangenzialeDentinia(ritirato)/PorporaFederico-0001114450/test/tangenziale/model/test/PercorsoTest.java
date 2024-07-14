package tangenziale.model.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import tangenziale.model.Autostrada;
import tangenziale.model.Percorso;
import tangenziale.model.Tipologia;
import tangenziale.model.Tratta;

class PercorsoTest {

	private static Autostrada autostrada1, autostrada2, tangenziale;

	@BeforeAll
	public static void setup() {
		autostrada1 = new Autostrada(Tipologia.AUTOSTRADA,  "M1", new TreeMap<>(Map.of(0, "Civita", 14, "Castello", 29, "Forte", 44, "Urbello", 62, "Tangenziale")));
		autostrada2 = new Autostrada(Tipologia.AUTOSTRADA,  "M2", new TreeMap<>(Map.of(0, "Tangenziale", 16, "Castelbrutto", 39, "Cinisello", 64, "Val Duero")));
		tangenziale = new Autostrada(Tipologia.TANGENZIALE, "Tangenziale", new TreeMap<>(Map.of(0, "strada Marina", 6, "M1", 11, "Stadio", 15, "Interporto", 21, "M2", 32, "Aeroporto")));
	}
	
	@Test
	void testOK_TreTratteConTangenzialeInclusa() {
		var tratte = List.of(
			new Tratta("Civita", "Tangenziale", autostrada1),
			new Tratta("M1", "M2", tangenziale),
			new Tratta("Tangenziale", "Cinisello", autostrada2)
		);		
		var percorso = new Percorso(tratte);
		
		assertEquals(116,percorso.lunghezza());
		assertEquals(101,percorso.lunghezza(Tipologia.AUTOSTRADA));
		assertEquals(15, percorso.lunghezza(Tipologia.TANGENZIALE));
		assertEquals(116,percorso.lunghezzaUtile());
		assertEquals(116*Percorso.COSTO_KM, percorso.costo(), 0.01);
	}
	
	@Test
	void testOK_DueTratteConTangenzialeDaPagare() {
		var tratte = List.of(
				new Tratta("Civita", "Tangenziale", autostrada1),
				new Tratta("M1", "Aeroporto", tangenziale)
			);		
			var percorso = new Percorso(tratte);
			
			assertEquals(88,percorso.lunghezza());
			assertEquals(62,percorso.lunghezza(Tipologia.AUTOSTRADA));
			assertEquals(26, percorso.lunghezza(Tipologia.TANGENZIALE));
			assertEquals(88,percorso.lunghezzaUtile());
			assertEquals(88*Percorso.COSTO_KM, percorso.costo(), 0.01);
	}
	
	@Test
	void testOK_DueTratteConTangenzialeGratis() {
		var tratte = List.of(
				new Tratta("Forte", "Tangenziale", autostrada1),
				new Tratta("M1", "Aeroporto", tangenziale)
			);		
			var percorso = new Percorso(tratte);
			
			assertEquals(59,percorso.lunghezza());
			assertEquals(33,percorso.lunghezza(Tipologia.AUTOSTRADA));
			assertEquals(26, percorso.lunghezza(Tipologia.TANGENZIALE));
			assertEquals(33,percorso.lunghezzaUtile());
			assertEquals(33*Percorso.COSTO_KM, percorso.costo(), 0.01);
	}
	
	@Test
	void testOK_UnaTrattaSoloTangenziale_TuttoGratis() {
		var tratte = List.of(
				new Tratta("Stadio", "Aeroporto", tangenziale)
			);		
			var percorso = new Percorso(tratte);
			
			assertEquals(21,percorso.lunghezza());
			assertEquals(0,percorso.lunghezza(Tipologia.AUTOSTRADA));
			assertEquals(21, percorso.lunghezza(Tipologia.TANGENZIALE));
			assertEquals(0,percorso.lunghezzaUtile());
			assertEquals(0, percorso.costo(), 0.01);
	}
	
	@Test
	void testOK_UnaTrattaSoloAutostrada_APagamento() {
		var tratte = List.of(
				new Tratta("Forte", "Civita", autostrada1)
			);		
			var percorso = new Percorso(tratte);
			
			assertEquals(29,percorso.lunghezza());
			assertEquals(29,percorso.lunghezza(Tipologia.AUTOSTRADA));
			assertEquals(0, percorso.lunghezza(Tipologia.TANGENZIALE));
			assertEquals(29,percorso.lunghezzaUtile());
			assertEquals(29*Percorso.COSTO_KM, percorso.costo(), 0.01);
	}
	
	@Test
	void testKO_ArgNull() {
		assertThrows(NullPointerException.class, () -> new Percorso(null));
	}

	@Test
	void testKO_PiuDiTreTratte() {
		var tratte = List.of(
			new Tratta("Civita", "Tangenziale", autostrada1),
			new Tratta("M1", "M2", tangenziale),
			new Tratta("Tangenziale", "Cinisello", autostrada2),
			new Tratta("Cinisello", "Val Duero", autostrada2)
		);		
		assertThrows(IllegalArgumentException.class, () -> new Percorso(tratte));
	}
	
	@Test
	void testKO_PiuTratteSuStessaAutostrada() {
		var tratte = List.of(
			new Tratta("Civita", "Tangenziale", autostrada1),
			new Tratta("M1", "M1", tangenziale),
			new Tratta("Tangenziale", "Civita", autostrada1)
		);		
		assertThrows(IllegalArgumentException.class, () -> new Percorso(tratte));
	}
	
}
