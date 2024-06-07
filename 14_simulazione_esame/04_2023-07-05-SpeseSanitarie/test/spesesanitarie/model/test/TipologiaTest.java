package spesesanitarie.model.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import spesesanitarie.model.Tipologia;


class TipologiaTest {

	@Test
	void testOK_Basic() {
		assertTrue(Tipologia.FC.isDetraibile());
		assertTrue(Tipologia.LP.isDetraibile());
		assertTrue(Tipologia.TK.isDetraibile());
		assertTrue(Tipologia.DM.isDetraibile());
		assertFalse(Tipologia.AS.isDetraibile());
	}
	
	@Test
	void testOK_Detraibilita() {
		assertEquals(Set.of(Tipologia.FC,Tipologia.LP,Tipologia.TK,Tipologia.DM), Tipologia.tipologieDetraibili());
		assertEquals(Set.of(Tipologia.AS), Tipologia.tipologieNonDetraibili());
		
		assertEquals(Set.of(Tipologia.LP,Tipologia.TK,Tipologia.FC,Tipologia.DM), Tipologia.tipologieDetraibili());
		assertEquals(Set.of(Tipologia.AS), Tipologia.tipologieNonDetraibili());
		
		assertEquals(Set.of(Tipologia.DM,Tipologia.LP,Tipologia.TK,Tipologia.FC), Tipologia.tipologieDetraibili());
		assertEquals(Set.of(Tipologia.AS), Tipologia.tipologieNonDetraibili());
	}
	
}
