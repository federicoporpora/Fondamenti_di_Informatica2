package spesesanitarie.persistence.test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import spesesanitarie.model.Tipologia;
import spesesanitarie.persistence.BadFileFormatException;
import spesesanitarie.persistence.MySpeseReader;


public class MySpeseSanitarieReaderTest {

	@Test
	void testOK() throws IOException {
		String fakefile =
				  "07/10/2022;Struttura privata;1;€ 130,00\n"
				+ "LP;€ 130,00\n"
				+ "21/10/2022;Medico;1;€ 100,00\n"
				+ "LP;€ 100,00\n"
				+ "24/10/2022;Farmacia;5;€ 28,05\n"
				+ "TK;€ 5,11\n"
				+ "TK;€ 5,00\n"
				+ "FC;€ 10,71\n"
				+ "AS;€ 4,23\n"
				+ "TK;€ 3,00\n"
				+ "25/10/2022;Farmacia;2;€ 14,85\n"
				+ "FC;€ 7,83\n"
				+ "AS;€ 7,02\n"
				+ "03/11/2022;Farmacia;1;€ 9,67\n"
				+ "FC;€ 9,67\n"
				+ "08/11/2022;Farmacia;3;€ 23,97\n"
				+ "DM;€ 2,52\n"
				+ "AS;€ 12,90\n"
				+ "FC;€ 8,55\n"
				+ "22/11/2022;AUSL;1;€ 72,00\n"
				+ "LP;€ 72,00\n"
				+ "23/11/2022;Farmacia;3;€ 9,78\n"
				+ "TK;€ 3,00\n"
				+ "TK;€ 2,61\n"
				+ "FC;€ 4,17\n"
				+ "09/12/2022;Farmacia;1;€ 26,50\n"
				+ "AS;€ 26,50\n"
				+ "15/12/2022;Struttura privata;1;€ 21,00\n"
				+ "TK;€ 21,00\n"
				+ "21/12/2022;Farmacia;2;€ 5,61\n"
				+ "TK;€ 2,61\n"
				+ "TK;€ 3,00\n"
				+ "23/12/2022;Farmacia;2;€ 27,81\n"
				+ "FC;€ 6,39\n"
				+ "FC;€ 21,42\n"
				+ "27/12/2022;Parafarmacia;1;€ 1,80\n"
				+ "DM;€ 1,80\n"
				+ "";
		var spese = new MySpeseReader().leggiSpese(new StringReader(fakefile));
		assertEquals(13, spese.size());
		//
		var spesa2 = spese.get(2);
		assertEquals(LocalDate.of(2022,10,24), spesa2.getData());
		assertEquals(28.05, spesa2.getImporto(), 0.01);
		assertEquals("Farmacia", spesa2.getEmittente());
		var vociSpesa2 = spesa2.getVoci();
		assertEquals(Tipologia.TK, vociSpesa2.get(0).getTipologia()); assertEquals( 5.11, vociSpesa2.get(0).getImporto(), 0.01);
		assertEquals(Tipologia.TK, vociSpesa2.get(1).getTipologia()); assertEquals( 5.00, vociSpesa2.get(1).getImporto(), 0.01);
		assertEquals(Tipologia.FC, vociSpesa2.get(2).getTipologia()); assertEquals(10.71, vociSpesa2.get(2).getImporto(), 0.01);
		assertEquals(Tipologia.AS, vociSpesa2.get(3).getTipologia()); assertEquals( 4.23, vociSpesa2.get(3).getImporto(), 0.01);
		assertEquals(Tipologia.TK, vociSpesa2.get(4).getTipologia()); assertEquals( 3.00, vociSpesa2.get(4).getImporto(), 0.01);
		//
		var spesa6 = spese.get(6);
		assertEquals(LocalDate.of(2022,11,22), spesa6.getData());
		assertEquals(72.00, spesa6.getImporto(), 0.01);
		assertEquals("AUSL", spesa6.getEmittente());
		var vociSpesa6 = spesa6.getVoci();
		assertEquals(Tipologia.LP, vociSpesa6.get(0).getTipologia()); assertEquals(72.00, vociSpesa6.get(0).getImporto(), 0.01);
		//
		var spesa12 = spese.get(12);
		assertEquals(LocalDate.of(2022,12,27), spesa12.getData());
		assertEquals(1.80, spesa12.getImporto(), 0.01);
		assertEquals("Parafarmacia", spesa12.getEmittente());
		var vociSpesa12 = spesa12.getVoci();
		assertEquals(Tipologia.DM, vociSpesa12.get(0).getTipologia()); assertEquals(1.80, vociSpesa12.get(0).getImporto(), 0.01);
	}
	
	@Test
	void testKO_MissingDate() throws IOException {
		String fakefile =
				  "07/10/2022;Struttura privata;1;€ 130,00\n"
				+ "LP;€ 130,00\n"
				+ "Medico;1;€ 100,00\n" // here
				+ "LP;€ 100,00\n"
				+ "24/10/2022;Farmacia;5;€ 28,05\n"
				+ "TK;€ 5,11\n"
				+ "TK;€ 5,00\n"
				+ "FC;€ 10,71\n"
				+ "AS;€ 4,23\n"
				+ "TK;€ 3,00\n"
				+ "";
		assertThrows(BadFileFormatException.class, () -> new MySpeseReader().leggiSpese(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_MissingEmitter() throws IOException {
		String fakefile =
				  "07/10/2022;1;€ 130,00\n" // here
				+ "LP;€ 130,00\n"
				+ "21/10/2022;Medico;1;€ 100,00\n"
				+ "LP;€ 100,00\n"
				+ "24/10/2022;Farmacia;5;€ 28,05\n"
				+ "TK;€ 5,11\n"
				+ "TK;€ 5,00\n"
				+ "FC;€ 10,71\n"
				+ "AS;€ 4,23\n"
				+ "TK;€ 3,00\n"
				+ "";
		assertThrows(BadFileFormatException.class, () -> new MySpeseReader().leggiSpese(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_MissingNumberOfItems() throws IOException {
		String fakefile =
				  "07/10/2022;Struttura privata;1;€ 130,00\n"
				+ "LP;€ 130,00\n"
				+ "21/10/2022;Medico;€ 100,00\n" // here
				+ "LP;€ 100,00\n"
				+ "24/10/2022;Farmacia;5;€ 28,05\n"
				+ "TK;€ 5,11\n"
				+ "TK;€ 5,00\n"
				+ "FC;€ 10,71\n"
				+ "AS;€ 4,23\n"
				+ "TK;€ 3,00\n"
				+ "";
		assertThrows(BadFileFormatException.class, () -> new MySpeseReader().leggiSpese(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_MissingPrice() throws IOException {
		String fakefile =
				  "07/10/2022;Struttura privata;1\n" // here
				+ "LP;€ 130,00\n"
				+ "21/10/2022;Medico;1;€ 100,00\n"
				+ "LP;€ 100,00\n"
				+ "24/10/2022;Farmacia;5;€ 28,05\n"
				+ "TK;€ 5,11\n"
				+ "TK;€ 5,00\n"
				+ "FC;€ 10,71\n"
				+ "AS;€ 4,23\n"
				+ "TK;€ 3,00\n"
				+ "";
		assertThrows(BadFileFormatException.class, () -> new MySpeseReader().leggiSpese(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_NoCurrencySymbol() throws IOException {
		String fakefile =
				  "07/10/2022;Struttura privata;1;€ 130,00\n"
				+ "LP;€ 130,00\n"
				+ "21/10/2022;Medico;1;100,00\n" // here
				+ "LP;€ 100,00\n"
				+ "24/10/2022;Farmacia;5;€ 28,05\n"
				+ "TK;€ 5,11\n"
				+ "TK;€ 5,00\n"
				+ "FC;€ 10,71\n"
				+ "AS;€ 4,23\n"
				+ "TK;€ 3,00\n"
				+ "";
		assertThrows(BadFileFormatException.class, () -> new MySpeseReader().leggiSpese(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_WrongPrice() throws IOException {
		String fakefile =
				  "07/10/2022;Struttura privata;1;€ a,00\n" // here
				+ "LP;€ 130,00\n"
				+ "21/10/2022;Medico;1;€ 100,00\n"
				+ "LP;€ 100,00\n"
				+ "24/10/2022;Farmacia;5;€ 28,05\n"
				+ "TK;€ 5,11\n"
				+ "TK;€ 5,00\n"
				+ "FC;€ 10,71\n"
				+ "AS;€ 4,23\n"
				+ "TK;€ 3,00\n"
				+ "";
		assertThrows(BadFileFormatException.class, () -> new MySpeseReader().leggiSpese(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_NegativeNumberOfItems() throws IOException {
		String fakefile =
				  "07/10/2022;Struttura privata;-1;€ 130,00\n" // here
				+ "LP;€ 130,00\n"
				+ "21/10/2022;Medico;1;€ 100,00\n"
				+ "LP;€ 100,00\n"
				+ "24/10/2022;Farmacia;5;€ 28,05\n"
				+ "TK;€ 5,11\n"
				+ "TK;€ 5,00\n"
				+ "FC;€ 10,71\n"
				+ "AS;€ 4,23\n"
				+ "TK;€ 3,00\n"
				+ "";
		assertThrows(BadFileFormatException.class, () -> new MySpeseReader().leggiSpese(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_NegativeNumberOfRows() throws IOException {
		String fakefile =
				  "07/10/2022;Struttura privata;1;€ 130,00\n"
				+ "LP;€ 130,00\n"
				+ "21/10/2022;Medico;1;€ 100,00\n"
				+ "LP;€ 100,00\n"
				+ "24/10/2022;Farmacia;5;€ 28,05\n" // missing line
				+ "TK;€ 5,00\n"
				+ "FC;€ 10,71\n"
				+ "AS;€ 4,23\n"
				+ "TK;€ 3,00\n"
				+ "";
		assertThrows(BadFileFormatException.class, () -> new MySpeseReader().leggiSpese(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_WrongNumberOfItems() throws IOException {
		String fakefile =
				  "07/10/2022;Struttura privata;2;€ 130,00\n" // here
				+ "LP;€ 130,00\n"
				+ "21/10/2022;Medico;1;€ 100,00\n"
				+ "LP;€ 100,00\n"
				+ "24/10/2022;Farmacia;5;€ 28,05\n"
				+ "TK;€ 5,11\n"
				+ "TK;€ 5,00\n"
				+ "FC;€ 10,71\n"
				+ "AS;€ 4,23\n"
				+ "TK;€ 3,00\n"
				+ "";
		assertThrows(BadFileFormatException.class, () -> new MySpeseReader().leggiSpese(new StringReader(fakefile)));
	}
	
	
	@Test
	void testKO_InexistentTypology() throws IOException {
		String fakefile =
				  "07/10/2022;Struttura privata;1;€ 130,00\n"
				+ "XP;€ 130,00\n" // here
				+ "21/10/2022;Medico;1;€ 100,00\n"
				+ "LP;€ 100,00\n"
				+ "24/10/2022;Farmacia;5;€ 28,05\n"
				+ "TK;€ 5,11\n"
				+ "TK;€ 5,00\n"
				+ "FC;€ 10,71\n"
				+ "AS;€ 4,23\n"
				+ "TK;€ 3,00\n"
				+ "";
		assertThrows(BadFileFormatException.class, () -> new MySpeseReader().leggiSpese(new StringReader(fakefile)));
	}
	
}
