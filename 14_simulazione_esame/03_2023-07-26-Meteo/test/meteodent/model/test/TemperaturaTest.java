package meteodent.model.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import meteodent.model.Temperatura;

class TemperaturaTest {

	@Test
	void testOK() {
		var t = Temperatura.of(12);
		assertEquals(12, t.getValue());
	}
	
}
