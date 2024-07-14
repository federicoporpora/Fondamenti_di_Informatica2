package cityparking.persistence.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.time.Duration;

import org.junit.jupiter.api.Test;

import cityparking.model.*;
import cityparking.persistence.*;

public class MyTariffeReaderTest {

	@Test
	public void testOK() throws IOException, BadFileFormatException {
		String fakeFile =
				  "Durata unità = 15m\r\n"
				+ "Costo iniziale = 2,00\u00A0€\r\n"
				+ "Unità successive = 0,80\u00A0€\r\n"
				+ "Cap giornaliero = 35,00\u00A0€\r\n"
				+ "12h successive = 16,00\u00A0€\r\n";
		Reader fakeRdr = new StringReader(fakeFile);
		MyTariffaReader rdr = new MyTariffaReader();
		Tariffa t = rdr.leggiTariffa(fakeRdr);
		assertEquals(Duration.ofMinutes(15), t.getDurataUnita());
		assertEquals( 2.00, t.getCostoIniziale(), 0.01);
		assertEquals( 0.80, t.getCostoUnitaSuccessive(), 0.01);
		assertEquals(35.00, t.getCapGiornaliero(), 0.01);
		assertEquals(16.00, t.getOltre(), 0.01);
	}
	
	@Test
	public void testKO_MissingEqualSignAtLine1() throws IOException, BadFileFormatException {
		String fakeFile =
				  "Durata unità 15m\r\n"
				+ "Costo iniziale = 2,00\u00A0€\r\n"
				+ "Unità successive = 0,80\u00A0€\r\n"
				+ "Cap giornaliero = 35,00\u00A0€\r\n"
				+ "12h successive = 16,00\u00A0€\r\n";
		Reader fakeRdr = new StringReader(fakeFile);
		MyTariffaReader rdr = new MyTariffaReader();
		assertThrows(BadFileFormatException.class, () -> rdr.leggiTariffa(fakeRdr));
	}	

	@Test
	public void testKO_MissingEqualSignAtLine2() throws IOException, BadFileFormatException {
		String fakeFile =
				  "Durata unità = 15m\r\n"
				+ "Costo iniziale 2,00\u00A0€\r\n"
				+ "Unità successive = 0,80\u00A0€\r\n"
				+ "Cap giornaliero = 35,00\u00A0€\r\n"
				+ "12h successive = 16,00\u00A0€\r\n";
		Reader fakeRdr = new StringReader(fakeFile);
		MyTariffaReader rdr = new MyTariffaReader();
		assertThrows(BadFileFormatException.class, () -> rdr.leggiTariffa(fakeRdr));
	}	
	@Test
	public void testKO_MissingEqualSignAtLine3() throws IOException, BadFileFormatException {
		String fakeFile =
				  "Durata unità =15m\r\n"
				+ "Costo iniziale = 2,00\u00A0€\r\n"
				+ "Unità successive / 0,80\u00A0€\r\n"
				+ "Cap giornaliero = 35,00\u00A0€\r\n"
				+ "12h successive = 16,00\u00A0€\r\n";
		Reader fakeRdr = new StringReader(fakeFile);
		MyTariffaReader rdr = new MyTariffaReader();
		assertThrows(BadFileFormatException.class, () -> rdr.leggiTariffa(fakeRdr));
	}	
	@Test
	public void testKO_MissingEqualSignAtLine4() throws IOException, BadFileFormatException {
		String fakeFile =
				  "Durata unità = 15m\r\n"
				+ "Costo iniziale = 2,00\u00A0€\r\n"
				+ "Unità successive = 0,80\u00A0€\r\n"
				+ "Cap giornaliero \\ 35,00\u00A0€\r\n"
				+ "12h successive = 16,00\u00A0€\r\n";
		Reader fakeRdr = new StringReader(fakeFile);
		MyTariffaReader rdr = new MyTariffaReader();
		assertThrows(BadFileFormatException.class, () -> rdr.leggiTariffa(fakeRdr));
	}	
	
	@Test
	public void testKO_MissingEqualSignAtLine5() throws IOException, BadFileFormatException {
		String fakeFile =
				  "Durata unità = 15m\r\n"
				+ "Costo iniziale = 2,00\u00A0€\r\n"
				+ "Unità successive = 0,80\u00A0€\r\n"
				+ "Cap giornaliero = 35,00\u00A0€\r\n"
				+ "12h successive \t 16,00\u00A0€\r\n";
		Reader fakeRdr = new StringReader(fakeFile);
		MyTariffaReader rdr = new MyTariffaReader();
		assertThrows(BadFileFormatException.class, () -> rdr.leggiTariffa(fakeRdr));
	}	

	@Test
	public void testKO_WrongDescrAtLine1() throws IOException, BadFileFormatException {
		String fakeFile =
				  "Durataunità = 15m\r\n"
				+ "Costo iniziale = 2,00\u00A0€\r\n"
				+ "Unità successive = 0,80\u00A0€\r\n"
				+ "Cap giornaliero = 35,00\u00A0€\r\n"
				+ "12h successive = 16,00\u00A0€\r\n";
		Reader fakeRdr = new StringReader(fakeFile);
		MyTariffaReader rdr = new MyTariffaReader();
		assertThrows(BadFileFormatException.class, () -> rdr.leggiTariffa(fakeRdr));
	}	

	@Test
	public void testKO_WrongDescrAtLine2() throws IOException, BadFileFormatException {
		String fakeFile =
				  "Durata unità = 15m\r\n"
				+ "Unità iniziale = 2,00\u00A0€\r\n"
				+ "Unità successive = 0,80\u00A0€\r\n"
				+ "Cap giornaliero = 35,00\u00A0€\r\n"
				+ "12h successive = 16,00\u00A0€\r\n";
		Reader fakeRdr = new StringReader(fakeFile);
		MyTariffaReader rdr = new MyTariffaReader();
		assertThrows(BadFileFormatException.class, () -> rdr.leggiTariffa(fakeRdr));
	}	
	@Test
	public void testKO_WrongDescrAtLine3() throws IOException, BadFileFormatException {
		String fakeFile =
				  "Durata unità = 15m\r\n"
				+ "Costo iniziale = 2,00\u00A0€\r\n"
				+ "Unita successive = 0,80\u00A0€\r\n"
				+ "Cap giornaliero = 35,00\u00A0€\r\n"
				+ "12h successive = 16,00\u00A0€\r\n";
		Reader fakeRdr = new StringReader(fakeFile);
		MyTariffaReader rdr = new MyTariffaReader();
		assertThrows(BadFileFormatException.class, () -> rdr.leggiTariffa(fakeRdr));
	}	
	@Test
	public void testKO_WrongDescrAtLine4() throws IOException, BadFileFormatException {
		String fakeFile =
				  "Durata unità = 15m\r\n"
				+ "Costo iniziale = 2,00\u00A0€\r\n"
				+ "Unità successive = 0,80\u00A0€\r\n"
				+ "Tetto giornaliero = 35,00\u00A0€\r\n"
				+ "12h successive = 16,00\u00A0€\r\n";
		Reader fakeRdr = new StringReader(fakeFile);
		MyTariffaReader rdr = new MyTariffaReader();
		assertThrows(BadFileFormatException.class, () -> rdr.leggiTariffa(fakeRdr));
	}	
	
	@Test
	public void testKO_WrongDescrAtLine5() throws IOException, BadFileFormatException {
		String fakeFile =
				  "Durata unità = 15m\r\n"
				+ "Costo iniziale = 2,00\u00A0€\r\n"
				+ "Unità successive = 0,80\u00A0€\r\n"
				+ "Cap giornaliero = 35,00\u00A0€\r\n"
				+ "12 ore successive = 16,00\u00A0€\r\n";
		Reader fakeRdr = new StringReader(fakeFile);
		MyTariffaReader rdr = new MyTariffaReader();
		assertThrows(BadFileFormatException.class, () -> rdr.leggiTariffa(fakeRdr));
	}	

	@Test
	public void testKO_WrongDurationAtLine1_MissingFinalM() throws IOException, BadFileFormatException {
		String fakeFile =
				  "Durata unità = 15\r\n"
				+ "Costo iniziale = 2,00\u00A0€\r\n"
				+ "Unità successive = 0,80\u00A0€\r\n"
				+ "Cap giornaliero = 35,00\u00A0€\r\n"
				+ "12h successive = 16,00\u00A0€\r\n";
		Reader fakeRdr = new StringReader(fakeFile);
		MyTariffaReader rdr = new MyTariffaReader();
		assertThrows(BadFileFormatException.class, () -> rdr.leggiTariffa(fakeRdr));
	}	
	@Test
	public void testKO_WrongDurationAtLine1_Zero() throws IOException, BadFileFormatException {
		String fakeFile =
				  "Durata unità = 0M\r\n"
				+ "Costo iniziale = 2,00\u00A0€\r\n"
				+ "Unità successive = 0,80\u00A0€\r\n"
				+ "Cap giornaliero = 35,00\u00A0€\r\n"
				+ "12h successive = 16,00\u00A0€\r\n";
		Reader fakeRdr = new StringReader(fakeFile);
		MyTariffaReader rdr = new MyTariffaReader();
		assertThrows(BadFileFormatException.class, () -> rdr.leggiTariffa(fakeRdr));
	}
	
	@Test
	public void testKO_WrongDurationAtLine1_Negative() throws IOException, BadFileFormatException {
		String fakeFile =
				  "Durata unità = -5m\r\n"
				+ "Costo iniziale = 2,00\u00A0€\r\n"
				+ "Unità successive = 0,80\u00A0€\r\n"
				+ "Cap giornaliero = 35,00\u00A0€\r\n"
				+ "12h successive = 16,00\u00A0€\r\n";
		Reader fakeRdr = new StringReader(fakeFile);
		MyTariffaReader rdr = new MyTariffaReader();
		assertThrows(BadFileFormatException.class, () -> rdr.leggiTariffa(fakeRdr));
	}	

	@Test
	public void testKO_WrongDurationAtLine1_MissingValue() throws IOException, BadFileFormatException {
		String fakeFile =
				  "Durata unità = \r\n"
				+ "Costo iniziale = 2,00\u00A0€\r\n"
				+ "Unità successive = 0,80\u00A0€\r\n"
				+ "Cap giornaliero = 35,00\u00A0€\r\n"
				+ "12h successive = 16,00\u00A0€\r\n";
		Reader fakeRdr = new StringReader(fakeFile);
		MyTariffaReader rdr = new MyTariffaReader();
		assertThrows(BadFileFormatException.class, () -> rdr.leggiTariffa(fakeRdr));
	}	

	@Test
	public void testKO_MissingValueAtLine2() throws IOException, BadFileFormatException {
		String fakeFile =
				  "Durata unità = 15m\r\n"
				+ "Costo iniziale = \r\n"
				+ "Unità successive = 0,80\u00A0€\r\n"
				+ "Cap giornaliero = 35,00\u00A0€\r\n"
				+ "12h successive = 16,00\u00A0€\r\n";
		Reader fakeRdr = new StringReader(fakeFile);
		MyTariffaReader rdr = new MyTariffaReader();
		assertThrows(BadFileFormatException.class, () -> rdr.leggiTariffa(fakeRdr));
	}
	
	@Test
	public void testKO_MissingValueAtLine3() throws IOException, BadFileFormatException {
		String fakeFile =
				  "Durata unità = 15m\r\n"
				+ "Costo iniziale = 2,00\u00A0€\r\n"
				+ "Unità successive = \r\n"
				+ "Cap giornaliero = 35,00\u00A0€\r\n"
				+ "12h successive = 16,00\u00A0€\r\n";
		Reader fakeRdr = new StringReader(fakeFile);
		MyTariffaReader rdr = new MyTariffaReader();
		assertThrows(BadFileFormatException.class, () -> rdr.leggiTariffa(fakeRdr));
	}	

	@Test
	public void testKO_MissingValueAtLine4() throws IOException, BadFileFormatException {
		String fakeFile =
				  "Durata unità = 15m\r\n"
				+ "Costo iniziale = 2,00\u00A0€\r\n"
				+ "Unità successive = 0,80\u00A0€\r\n"
				+ "Cap giornaliero = \r\n"
				+ "12h successive = 16,00\u00A0€\r\n";
		Reader fakeRdr = new StringReader(fakeFile);
		MyTariffaReader rdr = new MyTariffaReader();
		assertThrows(BadFileFormatException.class, () -> rdr.leggiTariffa(fakeRdr));
	}	

	@Test
	public void testKO_MissingValueAtLine5() throws IOException, BadFileFormatException {
		String fakeFile =
				  "Durata unità = 15m\r\n"
				+ "Costo iniziale = 2,00\u00A0€\r\n"
				+ "Unità successive = 0,80\u00A0€\r\n"
				+ "Cap giornaliero = 35,00\u00A0€\r\n"
				+ "12h successive = \r\n";
		Reader fakeRdr = new StringReader(fakeFile);
		MyTariffaReader rdr = new MyTariffaReader();
		assertThrows(BadFileFormatException.class, () -> rdr.leggiTariffa(fakeRdr));
	}

	@Test
	public void testKO_MissingEuroAtLine2() throws IOException, BadFileFormatException {
		String fakeFile =
				  "Durata unità = 15m\r\n"
				+ "Costo iniziale = 2,00\r\n"
				+ "Unità successive = 0,80\u00A0€\r\n"
				+ "Cap giornaliero = 35,00\u00A0€\r\n"
				+ "12h successive = 16,00\u00A0€\r\n";
		Reader fakeRdr = new StringReader(fakeFile);
		MyTariffaReader rdr = new MyTariffaReader();
		assertThrows(BadFileFormatException.class, () -> rdr.leggiTariffa(fakeRdr));
	}

	@Test
	public void testKO_MissingEuroAtLine3() throws IOException, BadFileFormatException {
		String fakeFile =
				  "Durata unità = 15m\r\n"
				+ "Costo iniziale = 2,00\u00A0€\r\n"
				+ "Unità successive = 0,80\r\n"
				+ "Cap giornaliero = 35,00\u00A0€\r\n"
				+ "12h successive = 16,00\u00A0€\r\n";
		Reader fakeRdr = new StringReader(fakeFile);
		MyTariffaReader rdr = new MyTariffaReader();
		assertThrows(BadFileFormatException.class, () -> rdr.leggiTariffa(fakeRdr));
	}
	@Test
	public void testKO_MissingEuroAtLine4() throws IOException, BadFileFormatException {
		String fakeFile =
				  "Durata unità = 15m\r\n"
				+ "Costo iniziale = 2,00\u00A0€\r\n"
				+ "Unità successive = 0,80\u00A0€\r\n"
				+ "Cap giornaliero = 35,00\r\n"
				+ "12h successive = 16,00\u00A0€\r\n";
		Reader fakeRdr = new StringReader(fakeFile);
		MyTariffaReader rdr = new MyTariffaReader();
		assertThrows(BadFileFormatException.class, () -> rdr.leggiTariffa(fakeRdr));
	}

	@Test
	public void testKO_MissingEuroAtLine5() throws IOException, BadFileFormatException {
		String fakeFile =
				  "Durata unità = 15m\r\n"
				+ "Costo iniziale = 2,00\u00A0€\r\n"
				+ "Unità successive = 0,80\u00A0€\r\n"
				+ "Cap giornaliero = 35,00\u00A0€\r\n"
				+ "12h successive = 16,00\r\n";
		Reader fakeRdr = new StringReader(fakeFile);
		MyTariffaReader rdr = new MyTariffaReader();
		assertThrows(BadFileFormatException.class, () -> rdr.leggiTariffa(fakeRdr));
	}
	
	@Test
	public void testKO_WrongSpaceBeforeEuroAtLine2() throws IOException, BadFileFormatException {
		String fakeFile =
				  "Durata unità = 15m\r\n"
				+ "Costo iniziale = 2,00 €\r\n"
				+ "Unità successive = 0,80\u00A0€\r\n"
				+ "Cap giornaliero = 35,00\u00A0€\r\n"
				+ "12h successive = 16,00\u00A0€\r\n";
		Reader fakeRdr = new StringReader(fakeFile);
		MyTariffaReader rdr = new MyTariffaReader();
		assertThrows(BadFileFormatException.class, () -> rdr.leggiTariffa(fakeRdr));
	}

	@Test
	public void testKO_WrongSpaceBeforeEuroAtLine3() throws IOException, BadFileFormatException {
		String fakeFile =
				  "Durata unità = 15m\r\n"
				+ "Costo iniziale = 2,00\u00A0€\r\n"
				+ "Unità successive = 0,80 €\r\n"
				+ "Cap giornaliero = 35,00\u00A0€\r\n"
				+ "12h successive = 16,00\u00A0€\r\n";
		Reader fakeRdr = new StringReader(fakeFile);
		MyTariffaReader rdr = new MyTariffaReader();
		assertThrows(BadFileFormatException.class, () -> rdr.leggiTariffa(fakeRdr));
	}
	@Test
	public void testKO_WrongSpaceBeforeEuroAtLine4() throws IOException, BadFileFormatException {
		String fakeFile =
				  "Durata unità = 15m\r\n"
				+ "Costo iniziale = 2,00\u00A0€\r\n"
				+ "Unità successive = 0,80\u00A0€\r\n"
				+ "Cap giornaliero = 35,00 €\r\n"
				+ "12h successive = 16,00\u00A0€\r\n";
		Reader fakeRdr = new StringReader(fakeFile);
		MyTariffaReader rdr = new MyTariffaReader();
		assertThrows(BadFileFormatException.class, () -> rdr.leggiTariffa(fakeRdr));
	}

	@Test
	public void testKO_WrongSpaceBeforeEuroAtLine5() throws IOException, BadFileFormatException {
		String fakeFile =
				  "Durata unità = 15m\r\n"
				+ "Costo iniziale = 2,00\u00A0€\r\n"
				+ "Unità successive = 0,80\u00A0€\r\n"
				+ "Cap giornaliero = 35,00\u00A0€\r\n"
				+ "12h successive = 16,00 €\r\n";
		Reader fakeRdr = new StringReader(fakeFile);
		MyTariffaReader rdr = new MyTariffaReader();
		assertThrows(BadFileFormatException.class, () -> rdr.leggiTariffa(fakeRdr));
	}
	
	public void testKO_WrongEuroValueAtLine2() throws IOException, BadFileFormatException {
		String fakeFile =
				  "Durata unità = 15m\r\n"
				+ "Costo iniziale = 2.00\u00A0€\r\n"
				+ "Unità successive = 0,80\u00A0€\r\n"
				+ "Cap giornaliero = 35,00\u00A0€\r\n"
				+ "12h successive = 16,00\u00A0€\r\n";
		Reader fakeRdr = new StringReader(fakeFile);
		MyTariffaReader rdr = new MyTariffaReader();
		Tariffa t = rdr.leggiTariffa(fakeRdr);
		assertEquals(Duration.ofMinutes(15), t.getDurataUnita());
		assertEquals( 200, t.getCostoIniziale(), 0.01); // !!!!
		assertEquals( 0.80, t.getCostoUnitaSuccessive(), 0.01);
		assertEquals(35.00, t.getCapGiornaliero(), 0.01);
		assertEquals(16.00, t.getOltre(), 0.01);
	}

	@Test
	public void testKO_WrongEuroValueAtLine3() throws IOException, BadFileFormatException {
		String fakeFile =
				  "Durata unità = 15m\r\n"
				+ "Costo iniziale = 2,00\u00A0€\r\n"
				+ "Unità successive = 0.80\u00A0€\r\n"
				+ "Cap giornaliero = 35,00\u00A0€\r\n"
				+ "12h successive = 16,00\u00A0€\r\n";
		Reader fakeRdr = new StringReader(fakeFile);
		MyTariffaReader rdr = new MyTariffaReader();
		Tariffa t = rdr.leggiTariffa(fakeRdr);
		assertEquals(Duration.ofMinutes(15), t.getDurataUnita());
		assertEquals( 2.00, t.getCostoIniziale(), 0.01);
		assertEquals( 80, t.getCostoUnitaSuccessive(), 0.01); // !!!!
		assertEquals(35.00, t.getCapGiornaliero(), 0.01);
		assertEquals(16.00, t.getOltre(), 0.01);
	}
	
	@Test
	public void testKO_WrongEuroValueEuroAtLine4() throws IOException, BadFileFormatException {
		String fakeFile =
				  "Durata unità = 15m\r\n"
				+ "Costo iniziale = 2,00\u00A0€\r\n"
				+ "Unità successive = 0,80\u00A0€\r\n"
				+ "Cap giornaliero = 35.00\u00A0€\r\n"
				+ "12h successive = 16,00\u00A0€\r\n";
		Reader fakeRdr = new StringReader(fakeFile);
		MyTariffaReader rdr = new MyTariffaReader();
		Tariffa t = rdr.leggiTariffa(fakeRdr);
		assertEquals(Duration.ofMinutes(15), t.getDurataUnita());
		assertEquals( 2.00, t.getCostoIniziale(), 0.01);
		assertEquals( 0.80, t.getCostoUnitaSuccessive(), 0.01);
		assertEquals(3500, t.getCapGiornaliero(), 0.01); // !!!!
		assertEquals(16.00, t.getOltre(), 0.01);
	}

	@Test
	public void testKO_WrongEuroValueAtLine5() throws IOException, BadFileFormatException {
		String fakeFile =
				  "Durata unità = 15m\r\n"
				+ "Costo iniziale = 2,00\u00A0€\r\n"
				+ "Unità successive = 0,80\u00A0€\r\n"
				+ "Cap giornaliero = 35,00\u00A0€\r\n"
				+ "12h successive = 16.00\u00A0€\r\n";
		Reader fakeRdr = new StringReader(fakeFile);
		MyTariffaReader rdr = new MyTariffaReader();
		Tariffa t = rdr.leggiTariffa(fakeRdr);
		assertEquals(Duration.ofMinutes(15), t.getDurataUnita());
		assertEquals( 2.00, t.getCostoIniziale(), 0.01);
		assertEquals( 0.80, t.getCostoUnitaSuccessive(), 0.01);
		assertEquals(35.00, t.getCapGiornaliero(), 0.01);
		assertEquals(1600, t.getOltre(), 0.01); // !!!!
	}
	
}
