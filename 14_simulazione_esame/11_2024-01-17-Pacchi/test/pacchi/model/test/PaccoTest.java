package pacchi.model.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pacchi.model.Numero;
import pacchi.model.Pacco;
import pacchi.model.Valore;
import pacchi.model.Territorio;

class PaccoTest {

	@Test
	void testOK() {
		var p = new Pacco(new Territorio("Dentinia"), new Numero(5), new Valore(100000));
		assertEquals("Dentinia", p.territorio().nome());
		assertEquals(5, p.numero().valore());
		assertEquals(100000, p.premio().valore());
	}
	
	@Test
	void testOK_Equals() {
		var p1 = new Pacco(new Territorio("Dentinia"), new Numero(5), new Valore(100000));
		var p2 = new Pacco(new Territorio("Dentinia"), new Numero(15), new Valore(100000));
		var p3 = new Pacco(new Territorio("DentOnia"), new Numero(5), new Valore(100000));
		var p4 = new Pacco(new Territorio("Dentinia"), new Numero(5), new Valore(20000));
		assertEquals(p1, p1);
		assertNotEquals(p1, p2);
		assertNotEquals(p1, p3);
		assertNotEquals(p1, p4);
	}
	
	@Test
	void testKO_TerritorioNull() {
		assertThrows(IllegalArgumentException.class, () -> new Pacco(null, new Numero(5), new Valore(100000)));
	}

	@Test
	void testKO_NumeroNull() {
		assertThrows(IllegalArgumentException.class, () -> new Pacco(new Territorio("Dentinia"), null, new Valore(100000)));
	}

	@Test
	void testKO_PremioNull() {
		assertThrows(IllegalArgumentException.class, () -> new Pacco(new Territorio("Dentinia"), new Numero(5), null));
	}

}
