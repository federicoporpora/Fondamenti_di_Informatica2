package cityparking.model.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import cityparking.model.Tariffa;

class TariffaTest {

	@Test
	void testOK() {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		assertEquals(Duration.ofMinutes(15), t.getDurataUnita());
		assertEquals( 2.00, t.getCostoIniziale(), 0.01);
		assertEquals( 0.80, t.getCostoUnitaSuccessive(), 0.01);
		assertEquals(35.00, t.getCapGiornaliero(), 0.01);
		assertEquals(16.00, t.getOltre(), 0.01);
	}

	@Test
	void testKO_durationNull() {
		assertThrows(IllegalArgumentException.class, () -> new Tariffa(null, 2.00, 0.80, 35.00, 16.00));
	}

	@Test
	void testKO_durationZero() {
		assertThrows(IllegalArgumentException.class, () -> new Tariffa(Duration.ZERO, 2.00, 0.80, 35.00, 16.00));
	}

	@Test
	void testKO_durationNegative() {
		assertThrows(IllegalArgumentException.class, () -> new Tariffa(Duration.ofMinutes(-10), 2.00, 0.80, 35.00, 16.00));
	}
	
	@Test
	void testKO_costoPrimaUnitaNegativo() {
		assertThrows(IllegalArgumentException.class, () -> new Tariffa(Duration.ofMinutes(15), -1.00, 0.80, 35.00, 16.00));
	}

	@Test
	void testKO_costoPrimaUnitaIllegale() {
		assertThrows(IllegalArgumentException.class, () -> new Tariffa(Duration.ofMinutes(15), Double.NaN, 0.80, 35.00, 16.00));
	}

	@Test
	void testKO_costoAltreUnitaNegativo() {
		assertThrows(IllegalArgumentException.class, () -> new Tariffa(Duration.ofMinutes(15), 2.00, -0.80, 35.00, 16.00));
	}

	@Test
	void testKO_costoAltreUnitaIllegale() {
		assertThrows(IllegalArgumentException.class, () -> new Tariffa(Duration.ofMinutes(15), 2.00, Double.NaN, 35.00, 16.00));
	}

	@Test
	void testKO_costoCapNegativo() {
		assertThrows(IllegalArgumentException.class, () -> new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, -35.00, 16.00));
	}

	@Test
	void testKO_costoCapIllegale() {
		assertThrows(IllegalArgumentException.class, () -> new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, Double.NaN, 16.00));
	}

	@Test
	void testKO_costoOltreNegativo() {
		assertThrows(IllegalArgumentException.class, () -> new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, -16.00));
	}

	@Test
	void testKO_costoOltreIllegale() {
		assertThrows(IllegalArgumentException.class, () -> new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, Double.NaN));
	}


}
