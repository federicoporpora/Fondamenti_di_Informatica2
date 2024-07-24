package pacchi.model.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pacchi.model.Valore;


class ValoreTest {

	@Test
	void testOK() {
		var n = new Valore(400000);
		assertEquals(400000, n.valore());
	}
	
	@Test
	void testKO() {
		assertThrows(IllegalArgumentException.class, () -> new Valore(-4));
	}

}
