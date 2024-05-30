package oroscopo.model.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.Set;
import org.junit.jupiter.api.Test;
import oroscopo.model.Previsione;
import oroscopo.model.SegnoZodiacale;


public class PrevisioneTest {

	@Test
	public void testCtorAuxOK() {
		var p = new Previsione("bella", 8);
		assertEquals("bella", p.getPrevisione());
		assertEquals(8, p.getValore());
		for (SegnoZodiacale s : SegnoZodiacale.values())
			assertTrue(p.validaPerSegno(s));
	}
	
	@Test
	public void testCtorOK0() {
		var p = new Previsione("bella", 8, Collections.emptySet());
		assertEquals("bella", p.getPrevisione());
		assertEquals(8, p.getValore());
		for (SegnoZodiacale s : SegnoZodiacale.values())
			assertFalse(p.validaPerSegno(s));
	}
	
	@Test
	public void testCtorOK1() {
		var p = new Previsione("bella", 8, Set.of(SegnoZodiacale.SAGITTARIO));
		assertEquals("bella", p.getPrevisione());
		assertEquals(8, p.getValore());
		for (SegnoZodiacale s : SegnoZodiacale.values())
			switch(s) {
			case SAGITTARIO -> assertTrue(p.validaPerSegno(s));
			default -> assertFalse(p.validaPerSegno(s));
			}
	}
		
	@Test
	public void testCtorOK2() {
		var p = new Previsione("bella", 8, Set.of(SegnoZodiacale.PESCI, SegnoZodiacale.BILANCIA));
		assertEquals("bella", p.getPrevisione());
		assertEquals(8, p.getValore());
		for (SegnoZodiacale s : SegnoZodiacale.values())
			switch(s) {
			case PESCI, BILANCIA -> assertTrue(p.validaPerSegno(s));
			default -> assertFalse(p.validaPerSegno(s));
			}
	}
	
	@Test
	public void testCtorKO_ValoreNegativo() {
		assertThrows(IllegalArgumentException.class, () -> new Previsione("brutta", -1, Set.of(SegnoZodiacale.PESCI, SegnoZodiacale.BILANCIA)));
	}

	@Test
	public void testCtorKO_TestoNullo() {
		assertThrows(NullPointerException.class, () -> new Previsione(null, 8, Set.of(SegnoZodiacale.PESCI, SegnoZodiacale.BILANCIA)));
	}

	@Test
	public void testCtorKO_TestoVuoto() {
		assertThrows(IllegalArgumentException.class, () -> new Previsione("", 8, Set.of(SegnoZodiacale.PESCI, SegnoZodiacale.BILANCIA)));
	}
	
	@Test
	public void testCtorKO_SetNullo() {
		assertThrows(NullPointerException.class, () -> new Previsione("brutta", 8, null));
	}

	@Test
	public void testEqualsHashCodeOK() {
		var p1 = new Previsione("bella", 8);
		var p2 = new Previsione("bella", 8);
		assertEquals(p1,p2);
		assertEquals(p1.hashCode(),p2.hashCode());
	}
	
	@Test
	public void testEqualsHashCodeOK3Arg() {
		var p1 = new Previsione("bella", 8, Set.of(SegnoZodiacale.PESCI, SegnoZodiacale.BILANCIA));
		var p2 = new Previsione("bella", 8, Set.of(SegnoZodiacale.BILANCIA, SegnoZodiacale.PESCI));
		assertEquals(p1,p2);
		assertEquals(p1.hashCode(),p2.hashCode());
	}
	
	@Test
	public void testEqualsHashCodeKO3Arg() {
		var p1 = new Previsione("bella", 8, Set.of(SegnoZodiacale.PESCI, SegnoZodiacale.BILANCIA));
		var p2 = new Previsione("bella", 8, Set.of(SegnoZodiacale.ARIETE, SegnoZodiacale.PESCI));
		assertNotEquals(p1,p2);
		assertNotEquals(p1.hashCode(),p2.hashCode());
	}
	
	@Test
	public void testEqualsHashCodeKO2ArgWrong2nd() {
		var p1 = new Previsione("bella", 8);
		var p2 = new Previsione("bella", 7);
		assertNotEquals(p1,p2);
		assertNotEquals(p1.hashCode(),p2.hashCode());
	}
	
	@Test
	public void testEqualsHashCodeKO2ArgWrong1st() {
		var p1 = new Previsione("bella", 8);
		var p2 = new Previsione("Bella", 8);
		assertNotEquals(p1,p2);
		assertNotEquals(p1.hashCode(),p2.hashCode());
	}
	
}