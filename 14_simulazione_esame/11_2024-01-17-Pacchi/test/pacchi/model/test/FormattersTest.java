package pacchi.model.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import pacchi.model.Formatters;

class FormattersTest {

	@Test
	void testOK_NumberFormatter() {
		assertEquals("5.000", Formatters.valueFormatter.format(5000));
	}
	
	@Test
	void testOK_PriceFormatter() {
		assertEquals("8\u00A0€", Formatters.priceFormatter.format(7.90));
	}

	@Test
	void testOK_NumberFormatter_Parse() {
		assertEquals(5000, Formatters.parse("5.000"));
	}
	
	@Test
	void testKO_NumberFormatter_Parse_Caso1() {
		assertThrows(IllegalArgumentException.class, () -> Formatters.parse("5-000"));
	}
	
	@Test
	void testKO_NumberFormatter_Parse_Caso2() {
		assertThrows(IllegalArgumentException.class, () -> Formatters.parse("5000a"));
	}

}
