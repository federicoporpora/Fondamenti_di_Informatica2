package spesesanitarie.model.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import spesesanitarie.model.Tipologia;
import spesesanitarie.model.VoceDiSpesa;
import spesesanitarie.model.DocumentoDiSpesa;


class DocumentoDiSpesaTest {

	@Test
	void testOK1() {
		var spesa = new DocumentoDiSpesa(LocalDate.of(2023,5,12), "Farmacia", 17.50, List.of(
				new VoceDiSpesa(Tipologia.FC, 12.0), new VoceDiSpesa(Tipologia.AS, 5.50))
				);
		assertEquals(LocalDate.of(2023,5,12), spesa.getData());
		assertEquals("Farmacia", spesa.getEmittente());
		assertEquals(17.50, spesa.getImporto(), 0.01);
		var voce0 = spesa.getVoci().get(0);
		var voce1 = spesa.getVoci().get(1);
		assertEquals(Tipologia.FC, voce0.getTipologia());
		assertEquals(Tipologia.AS, voce1.getTipologia());
		assertEquals(12.00, voce0.getImporto(), 0.01);
		assertEquals( 5.50, voce1.getImporto(), 0.01);
	}
	
	@Test
	void testOK2() {
		var spesa = new DocumentoDiSpesa(LocalDate.of(2023,5,12), "Medico", 170.00, List.of(
				new VoceDiSpesa(Tipologia.LP, 170.0))
				);
		assertEquals(LocalDate.of(2023,5,12), spesa.getData());
		assertEquals("Medico", spesa.getEmittente());
		assertEquals(170, spesa.getImporto(), 0.01);
		var voce0 = spesa.getVoci().get(0);
		assertEquals(Tipologia.LP, voce0.getTipologia());
		assertEquals(170.00, voce0.getImporto(), 0.01);
	}
	
	@Test
	void testKO_ImportoNonCollima() {
		assertThrows(IllegalArgumentException.class, () -> 
			new DocumentoDiSpesa(LocalDate.of(2023,5,12), "Medico", 172.00, List.of(new VoceDiSpesa(Tipologia.LP, 170.0)))
			);
		assertThrows(IllegalArgumentException.class, () -> 
			new DocumentoDiSpesa(LocalDate.of(2023,5,12), "Farmacia", 17.00, List.of(
				new VoceDiSpesa(Tipologia.FC, 12.0), new VoceDiSpesa(Tipologia.AS, 5.50)))
		);
	}
	
	@Test
	void testKO_PrecondizioniViolate() {
		assertThrows(IllegalArgumentException.class, () -> 
			new DocumentoDiSpesa(null, "Farmacia", 17.00, List.of(
					new VoceDiSpesa(Tipologia.FC, 12.0), new VoceDiSpesa(Tipologia.AS, 5.50)))
			);
		assertThrows(IllegalArgumentException.class, () -> 
			new DocumentoDiSpesa(LocalDate.of(2023,5,12), null, 17.00, List.of(
					new VoceDiSpesa(Tipologia.FC, 12.0), new VoceDiSpesa(Tipologia.AS, 5.50)))
			);
		assertThrows(IllegalArgumentException.class, () -> 
			new DocumentoDiSpesa(LocalDate.of(2023,5,12), "Farmacia", -3, List.of(
					new VoceDiSpesa(Tipologia.FC, 12.0), new VoceDiSpesa(Tipologia.AS, 5.50)))
			);
		assertThrows(IllegalArgumentException.class, () -> 
			new DocumentoDiSpesa(LocalDate.of(2023,5,12), "Farmacia", Double.NaN, List.of(
				new VoceDiSpesa(Tipologia.FC, 12.0), new VoceDiSpesa(Tipologia.AS, 5.50)))
			);
		assertThrows(IllegalArgumentException.class, () -> 
			new DocumentoDiSpesa(LocalDate.of(2023,5,12), "Farmacia", Double.POSITIVE_INFINITY, List.of(
				new VoceDiSpesa(Tipologia.FC, 12.0), new VoceDiSpesa(Tipologia.AS, 5.50)))
			);
		assertThrows(IllegalArgumentException.class, () -> 
			new DocumentoDiSpesa(LocalDate.of(2023,5,12), "Farmacia", Double.NEGATIVE_INFINITY, List.of(
				new VoceDiSpesa(Tipologia.FC, 12.0), new VoceDiSpesa(Tipologia.AS, 5.50)))
			);
		assertThrows(IllegalArgumentException.class, () -> 
			new DocumentoDiSpesa(LocalDate.of(2023,5,12), "Farmacia", 17.00, null)
			);
		assertThrows(IllegalArgumentException.class, () -> 
			new DocumentoDiSpesa(LocalDate.of(2023,5,12), "Farmacia", 17.00, Collections.emptyList())
			);
	}
	
	@Test
	void testOK_ContieneVoce1() {
		var spesa = new DocumentoDiSpesa(LocalDate.of(2023,5,12), "Farmacia", 17.50, List.of(
				new VoceDiSpesa(Tipologia.FC, 12.0), new VoceDiSpesa(Tipologia.AS, 5.50))
				);
		assertTrue(spesa.contieneVoce(Tipologia.FC));
		assertTrue(spesa.contieneVoce(Tipologia.AS));
		assertFalse(spesa.contieneVoce(Tipologia.LP));
		assertFalse(spesa.contieneVoce(Tipologia.TK));
		assertFalse(spesa.contieneVoce(Tipologia.DM));
	}

	@Test
	void testOK_ContieneVoce2() {
		var spesa = new DocumentoDiSpesa(LocalDate.of(2023,5,12), "Farmacia", 38.50, List.of(
				new VoceDiSpesa(Tipologia.FC, 12.0), new VoceDiSpesa(Tipologia.AS, 5.50),
				new VoceDiSpesa(Tipologia.FC, 3.50), new VoceDiSpesa(Tipologia.DM, 8.50),
				new VoceDiSpesa(Tipologia.TK, 6.20), new VoceDiSpesa(Tipologia.AS, 2.80))
				);
		assertTrue(spesa.contieneVoce(Tipologia.FC));
		assertTrue(spesa.contieneVoce(Tipologia.AS));
		assertFalse(spesa.contieneVoce(Tipologia.LP));
		assertTrue(spesa.contieneVoce(Tipologia.TK));
		assertTrue(spesa.contieneVoce(Tipologia.DM));
	}
	
	@Test
	void testOK_ContieneVoce3() {
		var spesa = new DocumentoDiSpesa(LocalDate.of(2023,5,12), "Medico", 170.00, List.of(
				new VoceDiSpesa(Tipologia.LP, 170.0))
				);
		assertFalse(spesa.contieneVoce(Tipologia.FC));
		assertFalse(spesa.contieneVoce(Tipologia.AS));
		assertTrue(spesa.contieneVoce(Tipologia.LP));
		assertFalse(spesa.contieneVoce(Tipologia.TK));
		assertFalse(spesa.contieneVoce(Tipologia.DM));
	}
}
