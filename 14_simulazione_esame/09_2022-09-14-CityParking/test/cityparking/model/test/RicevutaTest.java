package cityparking.model.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import cityparking.model.Ricevuta;

public class RicevutaTest {

	@Test
	public void testOK() {
		// inizio 10/8/22 h7:30, fine stesso giorno h15
		Ricevuta tk = new Ricevuta(LocalDateTime.of(2022,8,10, 7,30), LocalDateTime.of(2022,8,10, 15,00), 3.50);
		assertEquals(3.50, tk.getCosto(), 0.02);
		// NB: lo spazio prima/dopo il simbolo di valuta è il non-breakable space (codice 160 = \u00A0), 
		//     non lo spazio classico (codice 32)
		assertEquals("3,50\u00A0€", tk.getCostoAsString());
		System.out.println("Ticket test --" + tk);
	}

	@Test
	void testKO_inizioNull() {
		assertThrows(IllegalArgumentException.class, 
				() -> new Ricevuta(null, LocalDateTime.of(2022,8,10, 15,00), 3.50));
	}

	@Test
	void testKO_fineNull() {
		assertThrows(IllegalArgumentException.class, 
				() -> new Ricevuta(LocalDateTime.of(2022,8,10, 7,30), null, 3.50));
	}

	@Test
	void testKO_costoIllegale() {
		assertThrows(IllegalArgumentException.class, 
				() -> new Ricevuta(LocalDateTime.of(2022,8,10, 7,30), LocalDateTime.of(2022,8,10, 15,00), Double.NaN));
	}

}
