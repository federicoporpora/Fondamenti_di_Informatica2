package pacchi.model.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pacchi.model.Numero;

class NumeroTest {

	@Test
	void testOK() {
		var n = new Numero(4);
		assertEquals(4, n.valore());
	}
	
	@Test
	void testKO() {
		assertThrows(IllegalArgumentException.class, () -> new Numero(-4));
	}

}
