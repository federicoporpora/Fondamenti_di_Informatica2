package cruciverba.persistence.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.StringReader;

import cruciverba.persistence.SchemaReader;
import cruciverba.persistence.BadFileFormatException;
import cruciverba.persistence.MySchemaReader;


public class MySchemaReaderTest {
	
	@Test
	public void testOK() throws BadFileFormatException, IOException {
		String fakeFile = 
				  "LI-AV-NOLI--VLAD-\r\n"
				+ "EST-ALEA-RETE-SIM\r\n"
				+ "MAI-JET---VERBANO\r\n"
				+ "-ACCONTENTARSI-OR\r\n"
				+ "EC-ENTUSIASMARE-S\r\n"
				+ "A-ATTANAGLIATO-ME\r\n"
				+ "-INT-MOLECOLA-LA-\r\n"
				+ "CATONE-TRONI-SEGA\r\n"
				+ "OSE-INIA--E-SUGAR\r\n"
				+ "CI-ALTERCO-ANSA-G\r\n"
				+ "O-ALOE-ENOTECA-AO";
		SchemaReader myReader = new MySchemaReader(new StringReader(fakeFile));
		var schema = myReader.leggiSchema();
		assertEquals(11, schema.getNumRighe());
		assertEquals(17, schema.getNumColonne());
	}
	
	@Test
	public void testOK_ParoleLette() throws BadFileFormatException, IOException {
		String fakeFile = 
				  "LI-AV-NOLI--VLAD-\r\n"
				+ "EST-ALEA-RETE-SIM\r\n"
				+ "MAI-JET---VERBANO\r\n"
				+ "-ACCONTENTARSI-OR\r\n"
				+ "EC-ENTUSIASMARE-S\r\n"
				+ "A-ATTANAGLIATO-ME\r\n"
				+ "-INT-MOLECOLA-LA-\r\n"
				+ "CATONE-TRONI-SEGA\r\n"
				+ "OSE-INIA--E-SUGAR\r\n"
				+ "CI-ALTERCO-ANSA-G\r\n"
				+ "O-ALOE-ENOTECA-AO";
		SchemaReader myReader = new MySchemaReader(new StringReader(fakeFile));
		var schema = myReader.leggiSchema();
		assertArrayEquals(new String[] {"LEM", "EA", "COCO"}, schema.paroleInColonna(0));
		assertArrayEquals(new String[] {"ISAAC", "IASI"}, schema.paroleInColonna(1));
		assertArrayEquals(new String[] {"TIC", "ANTE", "A"}, schema.paroleInColonna(2));
		assertArrayEquals(new String[] {"A", "CETTO", "AL"}, schema.paroleInColonna(3));
		assertArrayEquals(new String[] {"VAJONT", "NILO"}, schema.paroleInColonna(4));
		assertArrayEquals(new String[] {"LENTAMENTE"}, schema.paroleInColonna(5));
		assertArrayEquals(new String[] {"NETTUNO", "IE"}, schema.paroleInColonna(6));
		assertArrayEquals(new String[] {"OA", "ESALTARE"}, schema.paroleInColonna(7));
		assertArrayEquals(new String[] {"L", "NIGER", "CN"}, schema.paroleInColonna(8));
		assertArrayEquals(new String[] {"IR", "TALCO", "OO"}, schema.paroleInColonna(9));
		assertArrayEquals(new String[] {"EVASIONE", "T"}, schema.paroleInColonna(10));
		assertArrayEquals(new String[] {"TERMALI", "AE"}, schema.paroleInColonna(11));
		assertArrayEquals(new String[] {"VERSATA", "SNC"}, schema.paroleInColonna(12));
		assertArrayEquals(new String[] {"L", "BIRO", "SUSA"}, schema.paroleInColonna(13));
		assertArrayEquals(new String[] {"ASA", "E", "LEGA"}, schema.paroleInColonna(14));
		assertArrayEquals(new String[] {"DINO", "MAGA", "A"}, schema.paroleInColonna(15));
		assertArrayEquals(new String[] {"MORSE", "ARGO"}, schema.paroleInColonna(16));
	}
		
	@Test
	public void testKO_UnaRigaPiuLunga() throws BadFileFormatException, IOException {
		String fakeFile = 
				  "LI-AV-NOLI--VLADIMIRO-\r\n"
				+ "EST-ALEA-RETE-SIM\r\n"
				+ "MAI-JET---VERBANO\r\n"
				+ "-ACCONTENTARSI-OR\r\n"
				+ "EC-ENTUSIASMARE-S\r\n"
				+ "A-ATTANAGLIATO-ME\r\n"
				+ "-INT-MOLECOLA-LA-\r\n"
				+ "CATONE-TRONI-SEGA\r\n"
				+ "OSE-INIA--E-SUGAR\r\n"
				+ "CI-ALTERCO-ANSA-G\r\n"
				+ "O-ALOE-ENOTECA-AO";
		assertThrows(BadFileFormatException.class, () -> new MySchemaReader(new StringReader(fakeFile)).leggiSchema());
	}


	@Test
	public void testKO_UnaRigaPiuCorta() throws BadFileFormatException, IOException {
		String fakeFile = 
				  "LI-AV-NOLI--VLADI-\r\n"
				+ "EST-ALEA-RETE-SIM\r\n"
				+ "MAI-JET--VERBANO\r\n"
				+ "-ACCONTENTARSI-OR\r\n"
				+ "EC-ENTUSIASMARE-S\r\n"
				+ "A-ATTANAGLIATO-ME\r\n"
				+ "-INT-MOLECOLA-LA-\r\n"
				+ "CATONE-TRONI-SEGA\r\n"
				+ "OSE-INIA--E-SUGAR\r\n"
				+ "CI-ALTERCO-ANSA-G\r\n"
				+ "O-ALOE-ENOTECA-AO";
		assertThrows(BadFileFormatException.class, () -> new MySchemaReader(new StringReader(fakeFile)).leggiSchema());
	}
}
