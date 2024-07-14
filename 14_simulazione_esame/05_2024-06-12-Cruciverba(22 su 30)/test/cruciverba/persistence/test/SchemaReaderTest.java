package cruciverba.persistence.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

import cruciverba.persistence.SchemaReader;
import cruciverba.persistence.BadFileFormatException;
import cruciverba.persistence.ElementoRiga;


public class SchemaReaderTest {
	
	@Test
	public void testOK_CercaParoleInRiga() throws BadFileFormatException, IOException {
		String[] righe = { "OSE-INIA-E-A", "ALFA-AL-APPO", "ALFA-E-BETA-E", "AL-E---E-A--E--AL"};
		
		assertEquals(List.of(
				new ElementoRiga("OSE", 0),
				new ElementoRiga("INIA", 4),
				new ElementoRiga("E", 9),
				new ElementoRiga("A", 11)
				),  SchemaReader.elementiRiga(righe[0]));
		
		assertEquals(List.of(
				new ElementoRiga("ALFA", 0),
				new ElementoRiga("E", 5),
				new ElementoRiga("BETA", 7),
				new ElementoRiga("E", 12)
				),  SchemaReader.elementiRiga(righe[2]));
		
		assertEquals(List.of(
				new ElementoRiga("AL", 0),
				new ElementoRiga("E", 3),
				new ElementoRiga("E", 7),
				new ElementoRiga("A", 9),
				new ElementoRiga("E", 12),
				new ElementoRiga("AL", 15)
				),  SchemaReader.elementiRiga(righe[3]));
	
	}
	
}
