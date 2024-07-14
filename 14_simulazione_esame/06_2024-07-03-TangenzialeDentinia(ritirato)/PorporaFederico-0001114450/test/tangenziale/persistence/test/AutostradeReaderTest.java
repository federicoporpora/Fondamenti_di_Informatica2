package tangenziale.persistence.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.StringReader;
import tangenziale.persistence.BadFileFormatException;
import tangenziale.persistence.MyAutostradeReader;


public class AutostradeReaderTest {

	@Test
	public void testOK_SoloTangenziale() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Tangenziale\n"
				  + "0	M2\n"
				  + "4	via dei colli\n"
				  + "9	M1\n"
				  + "13	Aeroporto\n"
				  + "19	M3\n"
				  + "23	Fiera\n"
				  + "26	Stadio\n"
				  + "32	M4\n"
				  + "END";
		var mappaAutostrade = new MyAutostradeReader().leggiAutostrade(new StringReader(fakefile));
		assertEquals(1, mappaAutostrade.size());
	}	
	
	@Test
	public void testOK() throws IOException, BadFileFormatException {
		String fakefile = 
				    "M3\n"
				  + "45	Petruvia Nord\n"
				  + "40	Petruvia Sud\n"
				  + "25	Aldino\n"
				  + "1	Dentinia Ippodromo\n"
				  + "0	Dentinia Tangenziale\n"
				  + "END\n"
				  + "\n"
				  + "M4\n"
				  + "0	Dentinia Tangenziale\n"
				  + "24	Castelbello\n"
				  + "37	Molla\n"
				  + "50	Forte\n"
				  + "77	Ripalta\n"
				  + "END\n"
				  + "\n"
				  + "M1\n"
				  + "85	Montelusa Centro\n"
				  + "78	Montelusa Z.I.\n"
				  + "48	Vigata ovest\n"
				  + "42	Vigata centro\n"
				  + "37	Vigata est\n"
				  + "12	Dentinia Aeroporto\n"
				  + "0	Dentinia Tangenziale\n"
				  + "END\n"
				  + "\n"
				  + "M2\n"
				  + "0	Dentinia Tangenziale\n"
				  + "18	Sasso del Moro\n"
				  + "39	Val di Mezzo\n"
				  + "49	Val di Sopra\n"
				  + "76	Val d'Altrove\n"
				  + "END\n"
				  + "\n"
				  + "Tangenziale\n"
				  + "0	M2\n"
				  + "4	via dei colli\n"
				  + "9	M1\n"
				  + "13	Aeroporto\n"
				  + "19	M3\n"
				  + "23	Fiera\n"
				  + "26	Stadio\n"
				  + "32	M4\n"
				  + "END";
		
		var mappaAutostrade = new MyAutostradeReader().leggiAutostrade(new StringReader(fakefile));
		
		assertNotNull(mappaAutostrade.get("M1"));
		assertNotNull(mappaAutostrade.get("M2"));
		assertNotNull(mappaAutostrade.get("M3"));
		assertNotNull(mappaAutostrade.get("M4"));
		assertNotNull(mappaAutostrade.get("Tangenziale"));
		assertNull(mappaAutostrade.get("M8"));
		assertNull(mappaAutostrade.get("Autostrada"));
		assertNull(mappaAutostrade.get("Tangenziale Dentinia"));
	}
	
	
	@Test
	public void testKO_TangenzialeMancante() throws IOException, BadFileFormatException {
		String fakefile = 
				  "M3\n"
				  + "45	Petruvia Nord\n"
				  + "40	Petruvia Sud\n"
				  + "25	Aldino\n"
				  + "1	Dentinia Ippodromo\n"
				  + "0	Dentinia Tangenziale\n"
				  + "END\n";
		assertThrows(BadFileFormatException.class, () -> new MyAutostradeReader().leggiAutostrade(new StringReader(fakefile)));
	}
	
	@Test
	public void testKO_FineFileInattesa_MancaEND() throws IOException, BadFileFormatException {
		String fakefile = 
				  "M3\n"
				  + "45	Petruvia Nord\n"
				  + "40	Petruvia Sud\n"
				  + "25	Aldino\n"
				  + "1	Dentinia Ippodromo\n"
				  + "0	Dentinia Tangenziale\n";
		assertThrows(BadFileFormatException.class, () -> new MyAutostradeReader().leggiAutostrade(new StringReader(fakefile)));
	}
	
	@Test
	public void testKO_FineFileInattesa_MancaQuasiTutto() throws IOException, BadFileFormatException {
		String fakefile = 
				  "M3\n";
		assertThrows(BadFileFormatException.class, () -> new MyAutostradeReader().leggiAutostrade(new StringReader(fakefile)));
	}
	
	@Test
	public void testKO_FineFileInattesa_FileVuoto() throws IOException, BadFileFormatException {
		String fakefile = "";
		assertThrows(BadFileFormatException.class, () -> new MyAutostradeReader().leggiAutostrade(new StringReader(fakefile)));
	}
	
	@Test
	public void testKO_RigaErrataSenzaTabulazioni() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Tangenziale\n"
				  + "0 M2\n"				// qui!
				  + "4	via dei colli\n"
				  + "9	M1\n"
				  + "13	Aeroporto\n"
				  + "19	M3\n"
				  + "23	Fiera\n"
				  + "26	Stadio\n"
				  + "32	M4\n"
				  + "END";	
		assertThrows(BadFileFormatException.class, () -> new MyAutostradeReader().leggiAutostrade(new StringReader(fakefile)));
	}
	
	@Test
	public void testKO_RigaErrataKmNonInt() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Tangenziale\n"
				  + "0	M2\n"
				  + "4.2	via dei colli\n" // km errati qui
				  + "9	M1\n"
				  + "13	Aeroporto\n"
				  + "19	M3\n"
				  + "23	Fiera\n"
				  + "26	Stadio\n"
				  + "32	M4\n"
				  + "END";	
		assertThrows(BadFileFormatException.class, () -> new MyAutostradeReader().leggiAutostrade(new StringReader(fakefile)));
	}
	
	@Test
	public void testKO_RigaErrataKmNonNumero() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Tangenziale\n"
				  + "0	M2\n"
				  + "a4	via dei colli\n" // km errati qui
				  + "9	M1\n"
				  + "13	Aeroporto\n"
				  + "19	M3\n"
				  + "23	Fiera\n"
				  + "26	Stadio\n"
				  + "32	M4\n"
				  + "END";	
		assertThrows(BadFileFormatException.class, () -> new MyAutostradeReader().leggiAutostrade(new StringReader(fakefile)));
	}
	
	@Test
	public void testKO_RigaErrataKmMancanti() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Tangenziale\n"
				  + "0	M2\n"
				  + "	via dei colli\n" // km errati qui
				  + "9	M1\n"
				  + "13	Aeroporto\n"
				  + "19	M3\n"
				  + "23	Fiera\n"
				  + "26	Stadio\n"
				  + "32	M4\n"
				  + "END";	
		assertThrows(BadFileFormatException.class, () -> new MyAutostradeReader().leggiAutostrade(new StringReader(fakefile)));
	}
	
	@Test
	public void testKO_RigaErrataNomeCaselloMancante() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Tangenziale\n"
				  + "0	M2\n"
				  + "4\n" // casello mancante qui
				  + "9	M1\n"
				  + "13	Aeroporto\n"
				  + "19	M3\n"
				  + "23	Fiera\n"
				  + "26	Stadio\n"
				  + "32	M4\n"
				  + "END";	
		assertThrows(BadFileFormatException.class, () -> new MyAutostradeReader().leggiAutostrade(new StringReader(fakefile)));
	}
	
	@Test
	public void testKO_RigaErrataNomeCaselloDuplicato() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Tangenziale\n"
				  + "0	M2\n"
				  + "4	M2\n" // casello duplicato qui
				  + "9	M1\n"
				  + "13	Aeroporto\n"
				  + "19	M3\n"
				  + "23	Fiera\n"
				  + "26	Stadio\n"
				  + "32	M4\n"
				  + "END";	
		assertThrows(BadFileFormatException.class, () -> new MyAutostradeReader().leggiAutostrade(new StringReader(fakefile)));
	}
	
	@Test
	public void testKO_RigaErrataKmDuplicato() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Tangenziale\n"
				  + "0	M2\n"
				  + "4	via dei colli\n"
				  + "4	M1\n"	// km duplicati qui
				  + "13	Aeroporto\n"
				  + "19	M3\n"
				  + "23	Fiera\n"
				  + "26	Stadio\n"
				  + "32	M4\n"
				  + "END";	
		assertThrows(BadFileFormatException.class, () -> new MyAutostradeReader().leggiAutostrade(new StringReader(fakefile)));
	}
	
	@Test
	public void testKO_ReaderNullo() throws IOException, BadFileFormatException {
		assertThrows(NullPointerException.class, () -> new MyAutostradeReader().leggiAutostrade(null));
	}
	
	@Test
	public void testKO_DoppiaTangenziale() throws IOException, BadFileFormatException {
		String fakefile = 
				  "Tangenziale\n"
				  + "0	M2\n"
				  + "4	via dei colli\n"
				  + "9	M1\n"
				  + "13	Aeroporto\n"
				  + "19	M3\n"
				  + "23	Fiera\n"
				  + "26	Stadio\n"
				  + "32	M4\n"
				  + "END"
				  +
				  "Tangenziale\n"
				  + "0	M2\n"
				  + "4	via dei colli\n"
				  + "9	M1\n"
				  + "13	Aeroporto\n"
				  + "19	M3\n"
				  + "23	Fiera\n"
				  + "26	Stadio\n"
				  + "32	M4\n"
				  + "END";	
		assertThrows(BadFileFormatException.class, () -> new MyAutostradeReader().leggiAutostrade(new StringReader(fakefile)));
	}
	
}
