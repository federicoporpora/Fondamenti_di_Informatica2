package cityparking.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import cityparking.controller.BadTimeFormatException;
import cityparking.controller.Controller;
import cityparking.controller.MyController;
import cityparking.model.Tariffa;
import cityparking.model.BadTimeIntervalException;
import cityparking.model.Ricevuta;


public class MyControllerTest {

	@Test
	public void testOK_0h30() throws BadTimeIntervalException, BadTimeFormatException {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);		
		Controller controller = new MyController(t);	
		Ricevuta ticket = controller.calcolaSosta(LocalDate.of(2022,8,10), "07:30", LocalDate.of(2022,8,10), "08:00");
		assertEquals(LocalDateTime.of(2022,8,10, 7,30), ticket.getInizioSosta());
		assertEquals(LocalDateTime.of(2022,8,10, 8,00), ticket.getFineSosta());
		assertEquals(2.80, ticket.getCosto(), 0.01);
	}
	
	@Test
	void testOK_0h31() throws BadTimeIntervalException, BadTimeFormatException {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		Controller controller = new MyController(t);	
		Ricevuta ticket = controller.calcolaSosta(LocalDate.of(2022,8,10), "07:30", LocalDate.of(2022,8,10), "08:01");
		assertEquals(LocalDateTime.of(2022,8,10, 7,30), ticket.getInizioSosta());
		assertEquals(LocalDateTime.of(2022,8,10, 8,01), ticket.getFineSosta());
		assertEquals(3.60, ticket.getCosto(), 0.01);
	}

	@Test
	void testOK_0h45() throws BadTimeIntervalException, BadTimeFormatException {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		Controller controller = new MyController(t);	
		Ricevuta ticket = controller.calcolaSosta(LocalDate.of(2022,8,10), "07:30", LocalDate.of(2022,8,10), "08:15");
		assertEquals(LocalDateTime.of(2022,8,10, 7,30), ticket.getInizioSosta());
		assertEquals(LocalDateTime.of(2022,8,10, 8,15), ticket.getFineSosta());
		assertEquals(3.60, ticket.getCosto(), 0.01);
	}
	
	@Test
	void testOK_0h46() throws BadTimeIntervalException, BadTimeFormatException {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		Controller controller = new MyController(t);	
		Ricevuta ticket = controller.calcolaSosta(LocalDate.of(2022,8,10), "07:30", LocalDate.of(2022,8,10), "08:16");
		assertEquals(LocalDateTime.of(2022,8,10, 7,30), ticket.getInizioSosta());
		assertEquals(LocalDateTime.of(2022,8,10, 8,16), ticket.getFineSosta());
		assertEquals(4.40, ticket.getCosto(), 0.01);
	}
	
	@Test
	void testOK_1h00() throws BadTimeIntervalException, BadTimeFormatException {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		Controller controller = new MyController(t);	
		Ricevuta ticket = controller.calcolaSosta(LocalDate.of(2022,8,10), "12:44", LocalDate.of(2022,8,10), "13:44");
		assertEquals(LocalDateTime.of(2022,8,10, 12,44), ticket.getInizioSosta());
		assertEquals(LocalDateTime.of(2022,8,10, 13,44), ticket.getFineSosta());
		assertEquals(4.40, ticket.getCosto(), 0.01);
	}

	@Test
	void testOK_2h22_conMezzaNotteInMezzo() throws BadTimeIntervalException, BadTimeFormatException {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		Controller controller = new MyController(t);	
		Ricevuta ticket = controller.calcolaSosta(LocalDate.of(2022,8,10), "23:44", LocalDate.of(2022,8,11), "02:06");
		assertEquals(LocalDateTime.of(2022,8,10, 23,44), ticket.getInizioSosta());
		assertEquals(LocalDateTime.of(2022,8,11,  2, 6), ticket.getFineSosta());
		assertEquals(9.20, ticket.getCosto(), 0.01);
	}

	@Test
	void testOK_7h30() throws BadTimeIntervalException, BadTimeFormatException {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		Controller controller = new MyController(t);	
		Ricevuta ticket = controller.calcolaSosta(LocalDate.of(2022,8,10), "07:30", LocalDate.of(2022,8,10), "15:00");
		assertEquals(LocalDateTime.of(2022,8,10, 7,30), ticket.getInizioSosta());
		assertEquals(LocalDateTime.of(2022,8,10, 15,00), ticket.getFineSosta());
		assertEquals(25.20, ticket.getCosto(), 0.01);
	}

	@Test
	void testOK_11h00_conCap() throws BadTimeIntervalException, BadTimeFormatException {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		Controller controller = new MyController(t);	
		Ricevuta ticket = controller.calcolaSosta(LocalDate.of(2022,8,10), "06:40", LocalDate.of(2022,8,10), "17:40");
		assertEquals(LocalDateTime.of(2022,8,10, 6,40), ticket.getInizioSosta());
		assertEquals(LocalDateTime.of(2022,8,10, 17,40), ticket.getFineSosta());
		assertEquals(35.00, ticket.getCosto(), 0.01);
	}

	@Test
	void testOK_10h00() throws BadTimeIntervalException, BadTimeFormatException {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		Controller controller = new MyController(t);	
		Ricevuta ticket = controller.calcolaSosta(LocalDate.of(2022,8,10), "07:40", LocalDate.of(2022,8,10), "17:40");
		assertEquals(LocalDateTime.of(2022,8,10, 7,40), ticket.getInizioSosta());
		assertEquals(LocalDateTime.of(2022,8,10, 17,40), ticket.getFineSosta());
		assertEquals(33.20, ticket.getCosto(), 0.01);
	}

	@Test
	void testOK_14h30_conCap() throws BadTimeIntervalException, BadTimeFormatException {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		Controller controller = new MyController(t);	
		Ricevuta ticket = controller.calcolaSosta(LocalDate.of(2022,8,10), "06:40", LocalDate.of(2022,8,10), "21:10");
		assertEquals(LocalDateTime.of(2022,8,10, 6,40), ticket.getInizioSosta());
		assertEquals(LocalDateTime.of(2022,8,10, 21,10), ticket.getFineSosta());
		assertEquals(35.00, ticket.getCosto(), 0.01);
	}

	@Test
	void testOK_23h30_conCap() throws BadTimeIntervalException, BadTimeFormatException {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		Controller controller = new MyController(t);	
		Ricevuta ticket = controller.calcolaSosta(LocalDate.of(2022,8,10), "06:40", LocalDate.of(2022,8,11), "06:10");
		assertEquals(LocalDateTime.of(2022,8,10, 6,40), ticket.getInizioSosta());
		assertEquals(LocalDateTime.of(2022,8,11, 6,10), ticket.getFineSosta());
		assertEquals(35.00, ticket.getCosto(), 0.01);
	}
	
	@Test
	void testOK_24h00_conCap() throws BadTimeIntervalException, BadTimeFormatException {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		Controller controller = new MyController(t);	
		Ricevuta ticket = controller.calcolaSosta(LocalDate.of(2022,8,10), "06:33", LocalDate.of(2022,8,11), "06:33");
		assertEquals(LocalDateTime.of(2022,8,10, 6,33), ticket.getInizioSosta());
		assertEquals(LocalDateTime.of(2022,8,11, 6,33), ticket.getFineSosta());
		assertEquals(35.00, ticket.getCosto(), 0.01);
	}
	
	@Test
	void testOK_24h01_conCapEOltre() throws BadTimeIntervalException, BadTimeFormatException {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		Controller controller = new MyController(t);	
		Ricevuta ticket = controller.calcolaSosta(LocalDate.of(2022,8,10), "06:33", LocalDate.of(2022,8,11), "06:34");
		assertEquals(LocalDateTime.of(2022,8,10, 6,33), ticket.getInizioSosta());
		assertEquals(LocalDateTime.of(2022,8,11, 6,34), ticket.getFineSosta());
		assertEquals(51.00, ticket.getCosto(), 0.01);
	}

	@Test
	void testOK_36h_conCapEOltre() throws BadTimeIntervalException, BadTimeFormatException {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		Controller controller = new MyController(t);	
		Ricevuta ticket = controller.calcolaSosta(LocalDate.of(2022,8,10), "06:33", LocalDate.of(2022,8,11), "18:33");
		assertEquals(LocalDateTime.of(2022,8,10, 6,33), ticket.getInizioSosta());
		assertEquals(LocalDateTime.of(2022,8,11,18,33), ticket.getFineSosta());
		assertEquals(51.00, ticket.getCosto(), 0.01);
	}

	@Test
	void testOK_36h01_conCapEOltre() throws BadTimeIntervalException, BadTimeFormatException {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		Controller controller = new MyController(t);	
		Ricevuta ticket = controller.calcolaSosta(LocalDate.of(2022,8,10), "06:33", LocalDate.of(2022,8,11), "18:34");
		assertEquals(LocalDateTime.of(2022,8,10, 6,33), ticket.getInizioSosta());
		assertEquals(LocalDateTime.of(2022,8,11,18,34), ticket.getFineSosta());
		assertEquals(67.00, ticket.getCosto(), 0.01);
	}

	@Test
	void testOK_48h_conCapEOltre() throws BadTimeIntervalException, BadTimeFormatException {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		Controller controller = new MyController(t);	
		Ricevuta ticket = controller.calcolaSosta(LocalDate.of(2022,8,10), "06:33", LocalDate.of(2022,8,12), "06:33");
		assertEquals(LocalDateTime.of(2022,8,10, 6,33), ticket.getInizioSosta());
		assertEquals(LocalDateTime.of(2022,8,12, 6,33), ticket.getFineSosta());
		assertEquals(67.00, ticket.getCosto(), 0.01);
	}
	
	@Test
	void testKO_giornoOraFineAntecedenteGiornoOraInizio() {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		Controller controller = new MyController(t);	
		assertThrows(BadTimeIntervalException.class, () -> controller.calcolaSosta(LocalDate.of(2022,8,10), "06:33", LocalDate.of(2022,8,9), "06:33"));
	}

	@Test
	void testKO_oraFineAntecedenteOraInizio() {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		Controller controller = new MyController(t);	
		assertThrows(BadTimeIntervalException.class, () -> controller.calcolaSosta(LocalDate.of(2022,8,10), "06:33", LocalDate.of(2022,8,10), "06:30"));
	}
	
	@Test
	void testKO_formatoOrarioErrato_OraInizio() {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		Controller controller = new MyController(t);	
		assertThrows(BadTimeFormatException.class, () -> controller.calcolaSosta(LocalDate.of(2022,8,10), "6:3", LocalDate.of(2022,8,10), "6:33"));
	}

	@Test
	void testKO_formatoOrarioErrato_OraFinale() {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		Controller controller = new MyController(t);	
		assertThrows(BadTimeFormatException.class, () -> controller.calcolaSosta(LocalDate.of(2022,8,10), "6:33", LocalDate.of(2022,8,10), "6:7"));
	}
}

