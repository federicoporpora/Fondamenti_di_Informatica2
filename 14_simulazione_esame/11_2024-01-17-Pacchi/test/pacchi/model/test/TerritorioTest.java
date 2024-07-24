package pacchi.model.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pacchi.model.Territorio;


class TerritorioTest {

	@Test
	void testOK() {
		var t = new Territorio("Dentinia");
		assertEquals("Dentinia", t.nome());
	}
	
	@Test
	void testKO_Blank1() {
		assertThrows(IllegalArgumentException.class, () -> new Territorio(" "));
	}

	@Test
	void testKO_Blank2() {
		assertThrows(IllegalArgumentException.class, () -> new Territorio(" \t"));
	}
	
	@Test
	void testKO_Blank3() {
		assertThrows(IllegalArgumentException.class, () -> new Territorio("\t"));
	}
	
	@Test
	void testKO_Null() {
		assertThrows(IllegalArgumentException.class, () -> new Territorio(null));
	}

}
