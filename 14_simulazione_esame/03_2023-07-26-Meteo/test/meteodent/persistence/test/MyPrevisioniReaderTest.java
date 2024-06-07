package meteodent.persistence.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import meteodent.model.ProbPioggia;
import meteodent.model.Temperatura;
import meteodent.persistence.BadFileFormatException;
import meteodent.persistence.MyPrevisioniReader;


public class MyPrevisioniReaderTest {

	@Test
	void testOK() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Bologna, 12/07/22, 02:00, 18°, 91%\n"
				+ "Bologna, 12/07/22, 05:00, 16°, 92%\n"
				+ "Bologna, 12/07/22, 08:00, 20°, 87%\n"
				+ "Bologna, 12/07/22, 11:00, 24°, 64%\n"
				+ "Bologna, 12/07/22, 14:00, 27°, 78%\n"
				+ "Bologna, 12/07/22, 17:00, 15°, 58%\n"
				+ "Bologna, 12/07/22, 20:00, 16°, 58%\n"
				+ "Bologna, 12/07/22, 23:00, 16°, 58%\n"
				+ "Bologna, 14/07/22, 11:00, 24°, 64%\n"
				+ "Bologna, 14/07/22, 14:00, 27°, 78%\n"
				+ "Bologna, 14/07/22, 17:00, 15°, 58%\n"
				+ "Bologna, 14/07/22, 20:00, 16°, 58%\n"
				+ "Bologna, 14/07/22, 23:00, 16°, 58%\n"
				+ "Ferrara, 16/07/22, 02:00, 18°, 91%\n"
				+ "Ferrara, 16/07/22, 10:00, 16°, 92%\n"
				+ "Ferrara, 16/07/22, 18:00, 20°, 87%\n"
				+ "Ferrara, 18/07/22, 05:00, 18°, 91%\n"
				+ "Ferrara, 18/07/22, 15:00, 16°, 92%\n"
				+ "Ferrara, 18/07/22, 22:00, 20°, 87%\n"
				+ "Reggio Emilia, 11/07/22, 02:00, -1°, 21%\n"
				+ "Reggio Emilia, 11/07/22, 10:00, -1°, 32%\n"
				+ "Reggio Emilia, 11/07/22, 18:00, 6°, 50%\n"
				+ "Reggio Emilia, 13/07/22, 05:00, 20°, 91%\n"
				+ "Reggio Emilia, 13/07/22, 15:00, 34°, 5%\n"
				+ "Reggio Emilia, 13/07/22, 22:00, 25°, 1%\n"
				+ "Ravenna, 12/07/22, 02:00, 25°, 11%\n"
				+ "Ravenna, 12/07/22, 10:00, 35°, 4%\n"
				+ "Ravenna, 12/07/22, 18:00, 20°, 5%\n"
				+ "Ravenna, 15/07/22, 05:00, 20°, 10%\n"
				+ "Ravenna, 15/07/22, 15:00, 34°, 1%\n"
				+ "Ravenna, 15/07/22, 22:00, 25°, 23%"
				  ;
		var previsioniMap = new MyPrevisioniReader().leggiPrevisioni(new StringReader(fakefile));
		assertEquals( 4, previsioniMap.size()); 			// numero di righe della mappa = numero di città
		var sortedSetBO = previsioniMap.get("Bologna");
		assertEquals(13, sortedSetBO.size());				// numero di previsioni per questa città (cardinalità sortedset)
		var sortedSetFE = previsioniMap.get("Ferrara");
		assertEquals( 6, sortedSetFE.size());				// numero di previsioni per questa città (cardinalità sortedset)
		var sortedSetRE = previsioniMap.get("Reggio Emilia");
		assertEquals( 6, sortedSetRE.size());				// numero di previsioni per questa città (cardinalità sortedset)
		var sortedSetRA = previsioniMap.get("Ravenna");
		assertEquals( 6, sortedSetRA.size());				// numero di previsioni per questa città (cardinalità sortedset)
		
		// check ordinamento sulle singole città
		var firstBO = sortedSetBO.first();
		assertEquals(ProbPioggia.of(91), firstBO.getProbabilitaPioggia());
		assertEquals(Temperatura.of(18), firstBO.getTemperatura());
		assertEquals("Bologna", firstBO.getLocalita());
		assertEquals(LocalDateTime.of(2022,7,12,2,0), firstBO.getDataOra());
		var lastBO = sortedSetBO.last();
		assertEquals(ProbPioggia.of(58), lastBO.getProbabilitaPioggia());
		assertEquals(Temperatura.of(16), lastBO.getTemperatura());
		assertEquals("Bologna", lastBO.getLocalita());
		assertEquals(LocalDateTime.of(2022,7,14,23,0), lastBO.getDataOra());

		var firstFE = sortedSetFE.first();
		assertEquals(ProbPioggia.of(91), firstFE.getProbabilitaPioggia());
		assertEquals(Temperatura.of(18), firstFE.getTemperatura());
		assertEquals("Ferrara", firstFE.getLocalita());
		assertEquals(LocalDateTime.of(2022,7,16,2,0), firstFE.getDataOra());
		var lastFE = sortedSetFE.last();
		assertEquals(ProbPioggia.of(87), lastFE.getProbabilitaPioggia());
		assertEquals(Temperatura.of(20), lastFE.getTemperatura());
		assertEquals("Ferrara", lastFE.getLocalita());
		assertEquals(LocalDateTime.of(2022,7,18,22,0), lastFE.getDataOra());
		
		var firstRE = sortedSetRE.first();
		assertEquals(ProbPioggia.of(21), firstRE.getProbabilitaPioggia());
		assertEquals(Temperatura.of(-1), firstRE.getTemperatura());
		assertEquals("Reggio Emilia", firstRE.getLocalita());
		assertEquals(LocalDateTime.of(2022,7,11,2,0), firstRE.getDataOra());
		var lastRE = sortedSetRE.last();
		assertEquals(ProbPioggia.of(1), lastRE.getProbabilitaPioggia());
		assertEquals(Temperatura.of(25), lastRE.getTemperatura());
		assertEquals("Reggio Emilia", lastRE.getLocalita());
		assertEquals(LocalDateTime.of(2022,7,13,22,0), lastRE.getDataOra());
		
		var firstRA = sortedSetRA.first();
		assertEquals(ProbPioggia.of(11), firstRA.getProbabilitaPioggia());
		assertEquals(Temperatura.of(25), firstRA.getTemperatura());
		assertEquals("Ravenna", firstRA.getLocalita());
		assertEquals(LocalDateTime.of(2022,7,12,2,0), firstRA.getDataOra());
		var lastRA = sortedSetRA.last();
		assertEquals(ProbPioggia.of(23), lastRA.getProbabilitaPioggia());
		assertEquals(Temperatura.of(25), lastRA.getTemperatura());
		assertEquals("Ravenna", lastRA.getLocalita());
		assertEquals(LocalDateTime.of(2022,7,15,22,0), lastRA.getDataOra());
	}
	
	@Test
	void testKO_NullReader() throws IOException, BadFileFormatException {
		assertThrows(IllegalArgumentException.class, () -> new MyPrevisioniReader().leggiPrevisioni(null));
	}
	
	// ------- check fallimenti -------
	
	@Test
	void testKO_WrongNumberOfItemsInFirstLine_MissingCommaBetweenItems1And2() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Ferrara 16/07/22, 02:00, 18°, 91%\n"
				+ "Ferrara, 16/07/22, 10:00, 16°, 92%\n"
				+ "Ferrara, 16/07/22, 18:00, 20°, 87%\n"
				+ "Ferrara, 18/07/22, 05:00, 18°, 91%\n"
				+ "Ferrara, 18/07/22, 15:00, 16°, 92%\n"
				+ "Ferrara, 18/07/22, 22:00, 20°, 87%\n"
				  ;
		assertThrows(BadFileFormatException.class, () -> new MyPrevisioniReader().leggiPrevisioni(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_WrongNumberOfItemsInFirstLine_MissingCommaBetweenItems2And3() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Ferrara, 16/07/22 02:00, 18°, 91%\n"
				+ "Ferrara, 16/07/22, 10:00, 16°, 92%\n"
				+ "Ferrara, 16/07/22, 18:00, 20°, 87%\n"
				+ "Ferrara, 18/07/22, 05:00, 18°, 91%\n"
				+ "Ferrara, 18/07/22, 15:00, 16°, 92%\n"
				+ "Ferrara, 18/07/22, 22:00, 20°, 87%\n"
				  ;
		assertThrows(BadFileFormatException.class, () -> new MyPrevisioniReader().leggiPrevisioni(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_WrongNumberOfItemsInFirstLine_MissingCommaBetweenItems4And5() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Ferrara, 16/07/22, 02:00, 18° 91%\n"
				+ "Ferrara, 16/07/22, 10:00, 16°, 92%\n"
				+ "Ferrara, 16/07/22, 18:00, 20°, 87%\n"
				+ "Ferrara, 18/07/22, 05:00, 18°, 91%\n"
				+ "Ferrara, 18/07/22, 15:00, 16°, 92%\n"
				+ "Ferrara, 18/07/22, 22:00, 20°, 87%\n"
				  ;
		assertThrows(BadFileFormatException.class, () -> new MyPrevisioniReader().leggiPrevisioni(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_WrongNumberOfItemsInFirstLine_ExtraComma() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Ferrara, 16/07/22,, 02:00, 18°, 91%\n"
				+ "Ferrara, 16/07/22, 10:00, 16°, 92%\n"
				+ "Ferrara, 16/07/22, 18:00, 20°, 87%\n"
				+ "Ferrara, 18/07/22, 05:00, 18°, 91%\n"
				+ "Ferrara, 18/07/22, 15:00, 16°, 92%\n"
				+ "Ferrara, 18/07/22, 22:00, 20°, 87%\n"
				  ;
		assertThrows(BadFileFormatException.class, () -> new MyPrevisioniReader().leggiPrevisioni(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_WrongNumberOfItemsInFirstLine_ExtraCommaAndItem() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Ferrara, 16/07/22, 02:00, 18°, 16°, 91%\n"
				+ "Ferrara, 16/07/22, 10:00, 16°, 92%\n"
				+ "Ferrara, 16/07/22, 18:00, 20°, 87%\n"
				+ "Ferrara, 18/07/22, 05:00, 18°, 91%\n"
				+ "Ferrara, 18/07/22, 15:00, 16°, 92%\n"
				+ "Ferrara, 18/07/22, 22:00, 20°, 87%\n"
				  ;
		assertThrows(BadFileFormatException.class, () -> new MyPrevisioniReader().leggiPrevisioni(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_WrongNumberOfItemsInOtherLine_MissingCommaBetweenItems1And2() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Ferrara, 16/07/22, 02:00, 18°, 91%\n"
				+ "Ferrara, 16/07/22, 10:00, 16°, 92%\n"
				+ "Ferrara 16/07/22, 18:00, 20°, 87%\n"
				+ "Ferrara, 18/07/22, 05:00, 18°, 91%\n"
				+ "Ferrara, 18/07/22, 15:00, 16°, 92%\n"
				+ "Ferrara, 18/07/22, 22:00, 20°, 87%\n"
				  ;
		assertThrows(BadFileFormatException.class, () -> new MyPrevisioniReader().leggiPrevisioni(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_WrongNumberOfItemsInOtherLine_MissingCommaBetweenItems3And4() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Ferrara, 16/07/22, 02:00, 18°, 91%\n"
				+ "Ferrara, 16/07/22, 10:00 16°, 92%\n"
				+ "Ferrara, 16/07/22, 18:00, 20°, 87%\n"
				+ "Ferrara, 18/07/22, 05:00, 18°, 91%\n"
				+ "Ferrara, 18/07/22, 15:00, 16°, 92%\n"
				+ "Ferrara, 18/07/22, 22:00, 20°, 87%\n"
				  ;
		assertThrows(BadFileFormatException.class, () -> new MyPrevisioniReader().leggiPrevisioni(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_WrongNumberOfItemsInOtherLine_MissingCommaBetweenItems4And5() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Ferrara, 16/07/22, 02:00, 18°, 91%\n"
				+ "Ferrara, 16/07/22, 10:00, 16°, 92%\n"
				+ "Ferrara, 16/07/22, 18:00, 20°, 87%\n"
				+ "Ferrara, 18/07/22, 05:00, 18°, 91%\n"
				+ "Ferrara, 18/07/22, 15:00, 16°, 92%\n"
				+ "Ferrara, 18/07/22, 22:00, 20° 87%\n"
				  ;
		assertThrows(BadFileFormatException.class, () -> new MyPrevisioniReader().leggiPrevisioni(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_WrongNumberOfItemsInOtherLine_ExtraComma() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Ferrara, 16/07/22, 02:00, 18°, 91%\n"
				+ "Ferrara, 16/07/22, 10:00, 16°, 92%\n"
				+ "Ferrara, 16/07/22, 18:00, 20°, 87%\n"
				+ "Ferrara, 18/07/22, 05:00, 18°, 91%\n"
				+ "Ferrara, 18/07/22, ,15:00, 16°, 92%\n"
				+ "Ferrara, 18/07/22, 22:00, 20°, 87%\n"
				  ;
		assertThrows(BadFileFormatException.class, () -> new MyPrevisioniReader().leggiPrevisioni(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_WrongNumberOfItemsInOtherLine_ExtraCommaAndItem() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Ferrara, 16/07/22, 02:00, 18°, 91%\n"
				+ "Ferrara, 16/07/22, 10:00, 16°, 92%\n"
				+ "Ferrara, 16/07/22, 18:00, 20°, 87%\n"
				+ "Ferrara, 18/07/22, 05:00, 18°, 91%\n"
				+ "Ferrara, 18/07/22, 15:00, 16°, 92%\n"
				+ "Ferrara, 18/07/22, 22:00, 20°, u, 87%\n"
				  ;
		assertThrows(BadFileFormatException.class, () -> new MyPrevisioniReader().leggiPrevisioni(new StringReader(fakefile)));
	}
	
	//--------
	
	@Test
	void testKO_MissingLocalitaInFirstLine() throws IOException, BadFileFormatException {
		String fakefile = 
				  "16/07/22, 02:00, 18°, 91%\n"
				+ "Ferrara, 16/07/22, 10:00, 16°, 92%\n"
				+ "Ferrara, 16/07/22, 18:00, 20°, 87%\n"
				+ "Ferrara, 18/07/22, 05:00, 18°, 91%\n"
				+ "Ferrara, 18/07/22, 15:00, 16°, 92%\n"
				+ "Ferrara, 18/07/22, 22:00, 20°, 87%\n"
				  ;
		assertThrows(BadFileFormatException.class, () -> new MyPrevisioniReader().leggiPrevisioni(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_MissingLocalitaInOtherLine() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Ferrara, 16/07/22, 02:00, 18°, 91%\n"
				+ "Ferrara, 16/07/22, 10:00, 16°, 92%\n"
				+ "Ferrara, 16/07/22, 18:00, 20°, 87%\n"
				+ "Ferrara, 18/07/22, 05:00, 18°, 91%\n"
				+ ", 18/07/22, 15:00, 16°, 92%\n"
				+ "Ferrara, 18/07/22, 22:00, 20°, 87%\n"
				  ;
		assertThrows(BadFileFormatException.class, () -> new MyPrevisioniReader().leggiPrevisioni(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_MissingDate() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Ferrara, 16/07/22, 02:00, 18°, 91%\n"
				+ "Ferrara, 16/07/22, 10:00, 16°, 92%\n"
				+ "Ferrara, 16/07/22, 18:00, 20°, 87%\n"
				+ "Ferrara, 05:00, 18°, 91%\n"
				+ "Ferrara, 18/07/22, 15:00, 16°, 92%\n"
				+ "Ferrara, 18/07/22, 22:00, 20°, 87%\n"
				  ;
		assertThrows(BadFileFormatException.class, () -> new MyPrevisioniReader().leggiPrevisioni(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_WrongDateInThirdLine() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Ferrara, 16/07/22, 02:00, 18°, 91%\n"
				+ "Ferrara, 16/07/22, 10:00, 16°, 92%\n"
				+ "Ferrara, 41/07/22, 18:00, 20°, 87%\n"
				+ "Ferrara, 18/07/22, 05:00, 18°, 91%\n"
				+ "Ferrara, 18/07/22, 15:00, 16°, 92%\n"
				+ "Ferrara, 18/07/22, 22:00, 20°, 87%\n"
				  ;
		assertThrows(BadFileFormatException.class, () -> new MyPrevisioniReader().leggiPrevisioni(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_WrongDateInThirdLine_Month() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Ferrara, 16/07/22, 02:00, 18°, 91%\n"
				+ "Ferrara, 16/07/22, 10:00, 16°, 92%\n"
				+ "Ferrara, 16/13/22, 18:00, 20°, 87%\n"
				+ "Ferrara, 18/07/22, 05:00, 18°, 91%\n"
				+ "Ferrara, 18/07/22, 15:00, 16°, 92%\n"
				+ "Ferrara, 18/07/22, 22:00, 20°, 87%\n"
				  ;
		assertThrows(BadFileFormatException.class, () -> new MyPrevisioniReader().leggiPrevisioni(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_WrongDateInFourthLine() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Ferrara, 16/07/22, 02:00, 18°, 91%\n"
				+ "Ferrara, 16/07/22, 10:00, 16°, 92%\n"
				+ "Ferrara, 16/07/22, 18:00, 20°, 87%\n"
				+ "Ferrara, 18/-7/22, 05:00, 18°, 91%\n"
				+ "Ferrara, 18/07/22, 15:00, 16°, 92%\n"
				+ "Ferrara, 18/07/22, 22:00, 20°, 87%\n"
				  ;
		assertThrows(BadFileFormatException.class, () -> new MyPrevisioniReader().leggiPrevisioni(new StringReader(fakefile)));
	}
	
	//------
	
	@Test
	void testKO_WrongTimeInFourthLine() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Ferrara, 16/07/22, 02:00, 18°, 91%\n"
				+ "Ferrara, 16/07/22, 10:00, 16°, 92%\n"
				+ "Ferrara, 16/07/22, 18:00, 20°, 87%\n"
				+ "Ferrara, 18/07/22, 05.00, 18°, 91%\n"
				+ "Ferrara, 18/07/22, 15:00, 16°, 92%\n"
				+ "Ferrara, 18/07/22, 22:00, 20°, 87%\n"
				  ;
		assertThrows(BadFileFormatException.class, () -> new MyPrevisioniReader().leggiPrevisioni(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_WrongTimeInFirstLine() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Ferrara, 16/07/22, 32:00, 18°, 91%\n"
				+ "Ferrara, 16/07/22, 10:00, 16°, 92%\n"
				+ "Ferrara, 16/07/22, 18:00, 20°, 87%\n"
				+ "Ferrara, 18/07/22, 05:00, 18°, 91%\n"
				+ "Ferrara, 18/07/22, 15:00, 16°, 92%\n"
				+ "Ferrara, 18/07/22, 22:00, 20°, 87%\n"
				  ;
		assertThrows(BadFileFormatException.class, () -> new MyPrevisioniReader().leggiPrevisioni(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_WrongTimeInThirdLine() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Ferrara, 16/07/22, 02:00, 18°, 91%\n"
				+ "Ferrara, 16/07/22, 10:00, 16°, 92%\n"
				+ "Ferrara, 16/07/22, 18:61, 20°, 87%\n"
				+ "Ferrara, 18/07/22, 05:00, 18°, 91%\n"
				+ "Ferrara, 18/07/22, 15:00, 16°, 92%\n"
				+ "Ferrara, 18/07/22, 22:00, 20°, 87%\n"
				  ;
		assertThrows(BadFileFormatException.class, () -> new MyPrevisioniReader().leggiPrevisioni(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_WrongTimeInSecondLine() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Ferrara, 16/07/22, 02:00, 18°, 91%\n"
				+ "Ferrara, 16/07/22, uuuuu, 16°, 92%\n"
				+ "Ferrara, 16/07/22, 18:00, 20°, 87%\n"
				+ "Ferrara, 18/07/22, 05:00, 18°, 91%\n"
				+ "Ferrara, 18/07/22, 15:00, 16°, 92%\n"
				+ "Ferrara, 18/07/22, 22:00, 20°, 87%\n"
				  ;
		assertThrows(BadFileFormatException.class, () -> new MyPrevisioniReader().leggiPrevisioni(new StringReader(fakefile)));
	}
	//------
	
	@Test
	void testKO_WrongTempInFourthLine_MissingDegreeSymbol() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Ferrara, 16/07/22, 02:00, 18°, 91%\n"
				+ "Ferrara, 16/07/22, 10:00, 16°, 92%\n"
				+ "Ferrara, 16/07/22, 18:00, 20°, 87%\n"
				+ "Ferrara, 18/07/22, 05.00, 18, 91%\n"
				+ "Ferrara, 18/07/22, 15:00, 16°, 92%\n"
				+ "Ferrara, 18/07/22, 22:00, 20°, 87%\n"
				  ;
		assertThrows(BadFileFormatException.class, () -> new MyPrevisioniReader().leggiPrevisioni(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_WrongTempInFourthLine_NotAnIntNumber() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Ferrara, 16/07/22, 02:00, 18°, 91%\n"
				+ "Ferrara, 16/07/22, 10:00, 16°, 92%\n"
				+ "Ferrara, 16/07/22, 18:00, 20°, 87%\n"
				+ "Ferrara, 18/07/22, 05.00, a2°, 91%\n"
				+ "Ferrara, 18/07/22, 15:00, 16°, 92%\n"
				+ "Ferrara, 18/07/22, 22:00, 20°, 87%\n"
				  ;
		assertThrows(BadFileFormatException.class, () -> new MyPrevisioniReader().leggiPrevisioni(new StringReader(fakefile)));
	}
		
	@Test
	void testKO_WrongTempInFourthLine_RealNumber() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Ferrara, 16/07/22, 02:00, 18°, 91%\n"
				+ "Ferrara, 16/07/22, 10:00, 16°, 92%\n"
				+ "Ferrara, 16/07/22, 18:00, 20°, 87%\n"
				+ "Ferrara, 18/07/22, 05.00, 22.3°, 91%\n"
				+ "Ferrara, 18/07/22, 15:00, 16°, 92%\n"
				+ "Ferrara, 18/07/22, 22:00, 20°, 87%\n"
				  ;
		assertThrows(BadFileFormatException.class, () -> new MyPrevisioniReader().leggiPrevisioni(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_WrongProbInFourthLine_MissingPercentSymbol() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Ferrara, 16/07/22, 02:00, 18°, 91%\n"
				+ "Ferrara, 16/07/22, 10:00, 16°, 92%\n"
				+ "Ferrara, 16/07/22, 18:00, 20°, 87%\n"
				+ "Ferrara, 18/07/22, 05.00, 18°, 91\n"
				+ "Ferrara, 18/07/22, 15:00, 16°, 92%\n"
				+ "Ferrara, 18/07/22, 22:00, 20°, 87%\n"
				  ;
		assertThrows(BadFileFormatException.class, () -> new MyPrevisioniReader().leggiPrevisioni(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_WrongProbInFourthLine_NotAnIntNumber() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Ferrara, 16/07/22, 02:00, 18°, 91%\n"
				+ "Ferrara, 16/07/22, 10:00, 16°, 92%\n"
				+ "Ferrara, 16/07/22, 18:00, 20°, 87%\n"
				+ "Ferrara, 18/07/22, 05.00, 22°, a1%\n"
				+ "Ferrara, 18/07/22, 15:00, 16°, 92%\n"
				+ "Ferrara, 18/07/22, 22:00, 20°, 87%\n"
				  ;
		assertThrows(BadFileFormatException.class, () -> new MyPrevisioniReader().leggiPrevisioni(new StringReader(fakefile)));
	}
		
	@Test
	void testKO_WrongProbInFourthLine_RealNumber() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Ferrara, 16/07/22, 02:00, 18°, 91%\n"
				+ "Ferrara, 16/07/22, 10:00, 16°, 92%\n"
				+ "Ferrara, 16/07/22, 18:00, 20°, 87%\n"
				+ "Ferrara, 18/07/22, 05.00, 22°, 91.1%\n"
				+ "Ferrara, 18/07/22, 15:00, 16°, 92%\n"
				+ "Ferrara, 18/07/22, 22:00, 20°, 87%\n"
				  ;
		assertThrows(BadFileFormatException.class, () -> new MyPrevisioniReader().leggiPrevisioni(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_WrongProbInFourthLine_NegNumber() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Ferrara, 16/07/22, 02:00, 18°, 91%\n"
				+ "Ferrara, 16/07/22, 10:00, 16°, 92%\n"
				+ "Ferrara, 16/07/22, 18:00, 20°, 87%\n"
				+ "Ferrara, 18/07/22, 05.00, 22°, 91.1%\n"
				+ "Ferrara, 18/07/22, 15:00, 16°, 92%\n"
				+ "Ferrara, 18/07/22, 22:00, 20°, 87%\n"
				  ;
		assertThrows(BadFileFormatException.class, () -> new MyPrevisioniReader().leggiPrevisioni(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_WrongProbInFourthLine_LessThanZero() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Ferrara, 16/07/22, 02:00, 18°, 91%\n"
				+ "Ferrara, 16/07/22, 10:00, 16°, 92%\n"
				+ "Ferrara, 16/07/22, 18:00, 20°, 87%\n"
				+ "Ferrara, 18/07/22, 05.00, 22°, -1%\n"
				+ "Ferrara, 18/07/22, 15:00, 16°, 92%\n"
				+ "Ferrara, 18/07/22, 22:00, 20°, 87%\n"
				  ;
		assertThrows(BadFileFormatException.class, () -> new MyPrevisioniReader().leggiPrevisioni(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_WrongProbInFourthLine_MoreThan100() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Ferrara, 16/07/22, 02:00, 18°, 91%\n"
				+ "Ferrara, 16/07/22, 10:00, 16°, 92%\n"
				+ "Ferrara, 16/07/22, 18:00, 20°, 87%\n"
				+ "Ferrara, 18/07/22, 05.00, 22°, 101%\n"
				+ "Ferrara, 18/07/22, 15:00, 16°, 92%\n"
				+ "Ferrara, 18/07/22, 22:00, 20°, 87%\n"
				  ;
		assertThrows(BadFileFormatException.class, () -> new MyPrevisioniReader().leggiPrevisioni(new StringReader(fakefile)));
	}
	
	
	
}
