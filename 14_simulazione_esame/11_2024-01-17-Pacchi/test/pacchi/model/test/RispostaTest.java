package pacchi.model.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import pacchi.model.Risposta;
import pacchi.model.Valore;


class RispostaTest {

	@Test
	void testOK() {
		var n = new Risposta(new Valore(400000));
		assertEquals(400000, n.offerta().valore());
	}
	
	// non occorre testKO per valori negativi perché già catturati da Valore

}
