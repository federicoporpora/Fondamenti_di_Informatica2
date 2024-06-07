package meteodent.model.test;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import meteodent.model.Formatters;


class FormattersTest {

	@Test
	void testOK_ItDateFormatter() {
		var t = LocalDate.of(2023, 10, 11);
		assertEquals("11/10/2023", Formatters.itDateFormatter.format(t));
	}
	
	@Test
	void testOK_DateFormatter() {
		var t = LocalDate.of(2023, 10, 11);
		assertEquals("11/10/23", Formatters.dateFormatter.format(t));
	}

	@Test
	void testOK_DateTimeFormatter() {
		var t = LocalDateTime.of(2023, 10, 11, 16, 51);
		assertEquals("11/10/23, 16:51", Formatters.datetimeFormatter.format(t));
	}

}
