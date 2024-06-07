package meteodent.model.test;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import meteodent.model.Bollettino;

class BollettinoTest {

	@Test
	void testOK() {
		var t = new Bollettino(LocalDate.of(2023,10,11), "Giove", 12, 22, "testo");
		assertEquals(22, t.getTemperatura());
		assertEquals(12, t.getProbabilitaPioggia());
		assertEquals("Giove", t.getLocalita());
		assertEquals("testo", t.getTesto());
		assertEquals(LocalDate.of(2023,10,11), t.getGiorno());
		assertEquals("Previsioni per il giorno 11/10/23 a Giove\ntesto", t.toString());
	}
	
	@Test
	void testKO_ProbPioggiaNeg() {
		assertThrows(IllegalArgumentException.class, () -> new Bollettino(LocalDate.of(2023,10,11), "Giove", -12, 22, "testo"));
	}
	
	@Test
	void testKO_ProbPioggiaMoreThan100() {
		assertThrows(IllegalArgumentException.class, () -> new Bollettino(LocalDate.of(2023,10,11), "Giove", 112, 22, "testo"));
	}
	
	@Test
	void testKO_NullDate() {
		assertThrows(IllegalArgumentException.class, () -> new Bollettino(null, "Giove", 112, 22, "testo"));
	}
	@Test
	void testKO_NullLoc() {
		assertThrows(IllegalArgumentException.class, () -> new Bollettino(LocalDate.of(2023,10,11), null, 112, 22, "testo"));
	}
	
	@Test
	void testKO_NullTesto() {
		assertThrows(IllegalArgumentException.class, () -> new Bollettino(LocalDate.of(2023,10,11), "Giove", 112, 22, null));
	}
	
}
