package pacchi.persistence.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.StringReader;
import org.junit.jupiter.api.Test;

import pacchi.persistence.BadFileFormatException;
import pacchi.persistence.MyConfigReader;


public class MyConfigPremiReaderTest {
	
	@Test
	void testOK_Premi() throws IOException, BadFileFormatException {
		String fakefilePremi = 
				"PREMI BASSI: 0, 1, 5, 10, 20, 50, 75, 100, 200, 500\r\n" +
				"PREMI ALTI:  5.000, 10.000, 15.000, 20.000, 30.000, 50.000, 75.000, 100.000, 200.000, 300.000";
		var premi = new MyConfigReader().leggiPremi(new StringReader(fakefilePremi));
		assertEquals(20, premi.size());
	}
	
	@Test
	void testKO_PremiBassi_ValoreNegativo() throws IOException, BadFileFormatException {
		String fakefilePremi = 
				"PREMI BASSI: 0, -1, 5, 10, 20, 50, 75, 100, 200, 500\r\n" +
				"PREMI ALTI:  5.000, 10.000, 15.000, 20.000, 30.000, 50.000, 75.000, 100.000, 200.000, 300.000";
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader().leggiPremi(new StringReader(fakefilePremi)));
	}
	
	@Test
	void testKO_PremiAlti_ValoreNegativo() throws IOException, BadFileFormatException {
		String fakefilePremi = 
				"PREMI BASSI: 0, 1, 5, 10, 20, 50, 75, 100, 200, 500\r\n" +
				"PREMI ALTI:  5.000, -10.000, 15.000, 20.000, 30.000, 50.000, 75.000, 100.000, 200.000, 300.000";
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader().leggiPremi(new StringReader(fakefilePremi)));
	}
	
	@Test
	void testKO_PremiBassi_ValoreAssurdo() throws IOException, BadFileFormatException {
		String fakefilePremi = 
				"PREMI BASSI: 0, a, 5, 10, 20, 50, 75, 100, 200, 500\r\n" +
				"PREMI ALTI:  5.000, 10.000, 15.000, 20.000, 30.000, 50.000, 75.000, 100.000, 200.000, 300.000";
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader().leggiPremi(new StringReader(fakefilePremi)));
	}
	
	@Test
	void testKO_PremiAlti_ValoreAssurdo() throws IOException, BadFileFormatException {
		String fakefilePremi = 
				"PREMI BASSI: 0, 1, 5, 10, 20, 50, 75, 100, 200, 500\r\n" +
				"PREMI ALTI:  5.000, a10.000, 15.000, 20.000, 30.000, 50.000, 75.000, 100.000, 200.000, 300.000";
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader().leggiPremi(new StringReader(fakefilePremi)));
	}
	
	@Test
	void testKO_PremiAlti_SeparatoreErrato() throws IOException, BadFileFormatException {
		String fakefilePremi = 
				"PREMI BASSI: 0, 1, 5, 10, 20, 50, 75, 100, 200, 500\r\n" +
				"PREMI ALTI:  5.000; 10.000, 15.000, 20.000, 30.000, 50.000, 75.000, 100.000, 200.000, 300.000";
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader().leggiPremi(new StringReader(fakefilePremi)));
	}
	
	@Test
	void testKO_PremiBassi_SeparatoreErrato() throws IOException, BadFileFormatException {
		String fakefilePremi = 
				"PREMI BASSI: 0, 1\t 5, 10, 20, 50, 75, 100, 200, 500\r\n" +
				"PREMI ALTI:  5.000, 10.000, 15.000, 20.000, 30.000, 50.000, 75.000, 100.000, 200.000, 300.000";
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader().leggiPremi(new StringReader(fakefilePremi)));
	}
	
	@Test
	void testKO_Premi_AltiBassiInDiversaQuantita_MancaUnBasso() throws IOException, BadFileFormatException {
		String fakefilePremi = 
				"PREMI BASSI: 0, 1, 5, 20, 50, 75, 100, 200, 500\r\n" +
				"PREMI ALTI:  5.000, 10.000, 15.000, 20.000, 30.000, 50.000, 75.000, 100.000, 200.000, 300.000";
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader().leggiPremi(new StringReader(fakefilePremi)));
	}
	
	@Test
	void testKO_Premi_AltiBassiInDiversaQuantita_MancaUnAlto() throws IOException, BadFileFormatException {
		String fakefilePremi = 
				"PREMI BASSI: 0, 1, 5, 10, 20, 50, 75, 100, 200, 500\r\n" +
				"PREMI ALTI:  10.000, 15.000, 20.000, 30.000, 50.000, 75.000, 100.000, 200.000, 300.000";
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader().leggiPremi(new StringReader(fakefilePremi)));
	}
	
	@Test
	void testKO_PremiBassi_MancaHeader() throws IOException, BadFileFormatException {
		String fakefilePremi = 
				"0, a, 5, 10, 20, 50, 75, 100, 200, 500\r\n" +
				"PREMI ALTI:  5.000, 10.000, 15.000, 20.000, 30.000, 50.000, 75.000, 100.000, 200.000, 300.000";
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader().leggiPremi(new StringReader(fakefilePremi)));
	}
	
	@Test
	void testKO_PremiBassi_ErratoHeader_Caso1() throws IOException, BadFileFormatException {
		String fakefilePremi = 
				"PREMI bassi: 0, a, 5, 10, 20, 50, 75, 100, 200, 500\r\n" +
				"PREMI ALTI:  5.000, 10.000, 15.000, 20.000, 30.000, 50.000, 75.000, 100.000, 200.000, 300.000";
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader().leggiPremi(new StringReader(fakefilePremi)));
	}
	
	@Test
	void testKO_PremiBassi_ErratoHeader_Caso2() throws IOException, BadFileFormatException {
		String fakefilePremi = 
				"PREMIBASSI: 0, a, 5, 10, 20, 50, 75, 100, 200, 500\r\n" +
				"PREMI ALTI:  5.000, 10.000, 15.000, 20.000, 30.000, 50.000, 75.000, 100.000, 200.000, 300.000";
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader().leggiPremi(new StringReader(fakefilePremi)));
	}
	
	@Test
	void testKO_PremiBassi_ErratoHeader_MancaSep() throws IOException, BadFileFormatException {
		String fakefilePremi = 
				"PREMIBASSI 0, a, 5, 10, 20, 50, 75, 100, 200, 500\r\n" +
				"PREMI ALTI:  5.000, 10.000, 15.000, 20.000, 30.000, 50.000, 75.000, 100.000, 200.000, 300.000";
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader().leggiPremi(new StringReader(fakefilePremi)));
	}
	
	@Test
	void testKO_PremiAlti_ErratoHeader_Caso1() throws IOException, BadFileFormatException {
		String fakefilePremi = 
				"PREMI BASSI: 0, a, 5, 10, 20, 50, 75, 100, 200, 500\r\n" +
				"PREMI ULTI:  5.000, 10.000, 15.000, 20.000, 30.000, 50.000, 75.000, 100.000, 200.000, 300.000";
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader().leggiPremi(new StringReader(fakefilePremi)));
	}
	
	@Test
	void testKO_PremiAlti_ErratoHeader_Caso2() throws IOException, BadFileFormatException {
		String fakefilePremi = 
				"PREMI BASSI: 0, a, 5, 10, 20, 50, 75, 100, 200, 500\r\n" +
				"PREMI-ALTI:  5.000, 10.000, 15.000, 20.000, 30.000, 50.000, 75.000, 100.000, 200.000, 300.000";
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader().leggiPremi(new StringReader(fakefilePremi)));
	}
	
	@Test
	void testKO_PremiAlti_ErratoHeader_MancaSep() throws IOException, BadFileFormatException {
		String fakefilePremi = 
				"PREMI BASSI: 0, a, 5, 10, 20, 50, 75, 100, 200, 500\r\n" +
				"PREMI ALTI\t 5.000, 10.000, 15.000, 20.000, 30.000, 50.000, 75.000, 100.000, 200.000, 300.000";
		assertThrows(BadFileFormatException.class, () -> new MyConfigReader().leggiPremi(new StringReader(fakefilePremi)));
	}

}
