package meteodent.model.test;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import meteodent.model.ProbPioggia;
import meteodent.model.Temperatura;
import meteodent.model.Previsione;


class PrevisioneTest {

	@Test
	void testOK() {
		var t = new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(16,51), Temperatura.of(22), ProbPioggia.of(71));
		assertEquals(ProbPioggia.of(71), t.getProbabilitaPioggia());
		assertEquals(Temperatura.of(22), t.getTemperatura());
		assertEquals("Giove", t.getLocalita());
		assertEquals(LocalDateTime.of(2023,10,11,16,51), t.getDataOra());
		assertEquals("11/10/23, 16:51, Temperatura 22°, Prob. pioggia 71%", t.toString());
	}
	
	@Test
	void testKO_ProbPioggiaNeg() {
		assertThrows(IllegalArgumentException.class, () -> new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(16,51), Temperatura.of(22), ProbPioggia.of(-71)));
	}
	
	@Test
	void testKO_ProbPioggiaMoreThan100() {
		assertThrows(IllegalArgumentException.class, () -> new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(16,51), Temperatura.of(22), ProbPioggia.of(171)));
	}
	
	@Test
	void testKO_NullDate() {
		assertThrows(IllegalArgumentException.class, () -> new Previsione("Giove", null, LocalTime.of(16,51), Temperatura.of(22), ProbPioggia.of(71)));
	}
	@Test
	void testKO_NullTime() {
		assertThrows(IllegalArgumentException.class, () -> new Previsione("Giove", LocalDate.of(2023,10,11), null, Temperatura.of(22), ProbPioggia.of(71)));
	}
	
	@Test
	void testKO_NullLoc() {
		assertThrows(IllegalArgumentException.class, () -> new Previsione(null, LocalDate.of(2023,10,11), LocalTime.of(16,51), Temperatura.of(22), ProbPioggia.of(71)));
	}
	
	@Test
	void testKO_NullTemp() {
		assertThrows(IllegalArgumentException.class, () -> new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(16,51), null, ProbPioggia.of(71)));
	}

	@Test
	void testKO_NullProbPioggia() {
		assertThrows(IllegalArgumentException.class, () -> new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(16,51), Temperatura.of(22), null));
	}
	
	@Test
	void testOK_Equals(){
		assertEquals(List.of(
					new Previsione("Luna", LocalDate.of(2023,10,13), LocalTime.of(10,32), Temperatura.of(41), ProbPioggia.of(2)),
					new Previsione("Luna", LocalDate.of(2023,10,13), LocalTime.of(12,32), Temperatura.of(44), ProbPioggia.of(1)),
					new Previsione("Luna", LocalDate.of(2023,10,13), LocalTime.of(16,32), Temperatura.of(42), ProbPioggia.of(3))
				), 
				List.of(
					new Previsione("Luna", LocalDate.of(2023,10,13), LocalTime.of(10,32), Temperatura.of(41), ProbPioggia.of(2)),
					new Previsione("Luna", LocalDate.of(2023,10,13), LocalTime.of(12,32), Temperatura.of(44), ProbPioggia.of(1)),
					new Previsione("Luna", LocalDate.of(2023,10,13), LocalTime.of(16,32), Temperatura.of(42), ProbPioggia.of(3))
				));
	}

	@Test
	void testOK_Hashcode(){
		assertEquals(List.of(
					new Previsione("Luna", LocalDate.of(2023,10,13), LocalTime.of(10,32), Temperatura.of(41), ProbPioggia.of(2)),
					new Previsione("Luna", LocalDate.of(2023,10,13), LocalTime.of(12,32), Temperatura.of(44), ProbPioggia.of(1)),
					new Previsione("Luna", LocalDate.of(2023,10,13), LocalTime.of(16,32), Temperatura.of(42), ProbPioggia.of(3))
				).hashCode(), 
				List.of(
					new Previsione("Luna", LocalDate.of(2023,10,13), LocalTime.of(10,32), Temperatura.of(41), ProbPioggia.of(2)),
					new Previsione("Luna", LocalDate.of(2023,10,13), LocalTime.of(12,32), Temperatura.of(44), ProbPioggia.of(1)),
					new Previsione("Luna", LocalDate.of(2023,10,13), LocalTime.of(16,32), Temperatura.of(42), ProbPioggia.of(3))
				).hashCode()
			);
	}
}
