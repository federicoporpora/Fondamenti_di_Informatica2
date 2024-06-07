package dentburger.model.test;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import dentburger.model.Formatters;

class FormattersTest {

	@Test
	void testOK_DateTimeFormatter() {
		var t = LocalDateTime.of(2023, 10, 11, 12, 30);
		assertEquals("11/10/23, 12:30", Formatters.datetimeFormatter.format(t));
	}
	
	@Test
	void testOK_PriceFormatter() {
		assertEquals("7,90\u00A0€", Formatters.priceFormatter.format(7.90));
	}

}
