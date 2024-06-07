package spesesanitarie.model.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import spesesanitarie.model.Tipologia;
import spesesanitarie.model.VoceDiSpesa;


class VoceDiSpesaTest {

	@Test
	void testOK1() {
		var voce0 = new VoceDiSpesa(Tipologia.FC, 12.0);
		var voce1 = new VoceDiSpesa(Tipologia.AS, 5.50);
		assertEquals(Tipologia.FC, voce0.getTipologia());
		assertEquals(Tipologia.AS, voce1.getTipologia());
		assertEquals(12.00, voce0.getImporto(), 0.01);
		assertEquals( 5.50, voce1.getImporto(), 0.01);
	}
	
	@Test
	void testOK2() {
		var voce0 = new VoceDiSpesa(Tipologia.LP, 170.0);
		assertEquals(Tipologia.LP, voce0.getTipologia());
		assertEquals(170.00, voce0.getImporto(), 0.01);
	}
	
	@Test
	void testKO_PrecondizioniViolate() {
		assertThrows(IllegalArgumentException.class, () -> new VoceDiSpesa(null, 12.0));
		assertThrows(IllegalArgumentException.class, () -> new VoceDiSpesa(Tipologia.FC, -2));
		assertThrows(IllegalArgumentException.class, () -> new VoceDiSpesa(Tipologia.FC, Double.NaN));
		assertThrows(IllegalArgumentException.class, () -> new VoceDiSpesa(Tipologia.FC, Double.POSITIVE_INFINITY));
		assertThrows(IllegalArgumentException.class, () -> new VoceDiSpesa(Tipologia.FC, Double.NEGATIVE_INFINITY));
	}
	
}
