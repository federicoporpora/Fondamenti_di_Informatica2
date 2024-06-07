package meteodent.model.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import meteodent.model.ProbPioggia;


class ProbPioggiaTest {

	@Test
	void testOK() {
		var t = ProbPioggia.of(71);
		assertEquals(71, t.getValue());
	}
	
	@Test
	void testKO_Neg() {
		assertThrows(IllegalArgumentException.class, () -> ProbPioggia.of(-71));
	}
	@Test
	void testKO_MoreThan100() {
		assertThrows(IllegalArgumentException.class, () -> ProbPioggia.of(101));
	}
	
}
