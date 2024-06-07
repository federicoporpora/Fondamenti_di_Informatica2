package meteodent.model.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import meteodent.model.SintesiGiornata;


class SintesiGiornataTest {
	
	@Test
	void testOK_Sereno() {
		var t = SintesiGiornata.SERENO;
		assertEquals(5, t.getValue());
		assertEquals("serena", t.getTesto());
		assertEquals("Giornata serena, con probabilità di pioggia del 5%", SintesiGiornata.getTestoAnnuncio(5));
	}
	
	@Test
	void testOK_QuasiSereno() {
		var t = SintesiGiornata.QUASISERENO;
		assertEquals(10, t.getValue());
		assertEquals("quasi serena", t.getTesto());
		assertEquals("Giornata quasi serena, con probabilità di pioggia del 10%", SintesiGiornata.getTestoAnnuncio(10));
	}
	
	@Test
	void testOK_PioggeSparse() {
		var t = SintesiGiornata.PIOGGESPARSE;
		assertEquals(25, t.getValue());
		assertEquals("con possibili piogge sparse", t.getTesto());
		assertEquals("Giornata con possibili piogge sparse, con probabilità di pioggia del 25%", SintesiGiornata.getTestoAnnuncio(25));
	}
	
	@Test
	void Variabile() {
		var t = SintesiGiornata.VARIABILE;
		assertEquals(50, t.getValue());
		assertEquals("variabile", t.getTesto());
		assertEquals("Giornata variabile, con probabilità di pioggia del 50%", SintesiGiornata.getTestoAnnuncio(50));
}
	
	@Test
	void testOK_PioggeDiffuse() {
		var t = SintesiGiornata.PIOGGEDIFFUSE;
		assertEquals(65, t.getValue());
		assertEquals("con piogge diffuse", t.getTesto());
		assertEquals("Giornata con piogge diffuse, con probabilità di pioggia del 65%", SintesiGiornata.getTestoAnnuncio(65));
	}
		
	@Test
	void testOK_PioggiaTanta() {
		var t = SintesiGiornata.PIOGGIA;
		assertEquals(80, t.getValue());
		assertEquals("con piogge", t.getTesto());
		assertEquals("Giornata con piogge, con probabilità di pioggia del 80%", SintesiGiornata.getTestoAnnuncio(80));
	}
	
	@Test
	void testOK_PioggeInsistenti() {
		var t = SintesiGiornata.PIOGGEINSISTENTI;
		assertEquals(100, t.getValue());
		assertEquals("con piogge insistenti e generalizzate", t.getTesto());
		assertEquals("Giornata con piogge insistenti e generalizzate, con probabilità di pioggia del 100%", SintesiGiornata.getTestoAnnuncio(100));
	}
	
}
