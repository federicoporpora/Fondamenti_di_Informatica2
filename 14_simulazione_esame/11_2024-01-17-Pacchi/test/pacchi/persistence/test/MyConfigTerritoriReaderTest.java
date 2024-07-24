package pacchi.persistence.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.StringReader;
import org.junit.jupiter.api.Test;

import pacchi.persistence.BadFileFormatException;
import pacchi.persistence.MyConfigReader;


public class MyConfigTerritoriReaderTest {

	@Test
	void testOK_Territori() throws IOException, BadFileFormatException {
		String fakefileTerritori = 
				"Valle d'Aosta\r\n"
				+ "Piemonte\r\n"
				+ "Liguria\r\n"
				+ "Lombardia\r\n"
				+ "Trentino-Alto Adige\r\n"
				+ "Veneto\r\n"
				+ "Friuli-Venezia Giulia\r\n"
				+ "Emilia-Romagna\r\n"
				+ "Toscana\r\n"
				+ "Umbria\r\n"
				+ "Marche\r\n"
				+ "Lazio\r\n"
				+ "Abruzzo\r\n"
				+ "Molise\r\n"
				+ "Puglia\r\n"
				+ "Campania\r\n"
				+ "Basilicata\r\n"
				+ "Calabria\r\n"
				+ "Sicilia\r\n"
				+ "Sardegna";	
		
		var territori = new MyConfigReader().leggiTerritori(new StringReader(fakefileTerritori));
		assertEquals(20, territori.size());
	}

	// Misuse case non validabili, non essendoci vincoli né sul formato né sul numero delle righe
	
}
