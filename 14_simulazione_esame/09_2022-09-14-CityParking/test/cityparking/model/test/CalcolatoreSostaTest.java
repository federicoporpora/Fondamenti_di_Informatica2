package cityparking.model.test;

import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import cityparking.model.BadTimeIntervalException;
import cityparking.model.MyCalcolatoreSosta;
import cityparking.model.Tariffa;
import cityparking.model.Ricevuta;

class TicketcalcTest {
	
	@Test
	void testOK_0h30() throws BadTimeIntervalException {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		var calc = new MyCalcolatoreSosta(t);
		Ricevuta ticket = calc.calcola(LocalDateTime.of(2022,8,10, 7,30), LocalDateTime.of(2022,8,10, 8,00));
		assertEquals(LocalDateTime.of(2022,8,10, 7,30), ticket.getInizioSosta());
		assertEquals(LocalDateTime.of(2022,8,10, 8,00), ticket.getFineSosta());
		// 2,00+0,80
		assertEquals(2.80, ticket.getCosto(), 0.01);
	}

	@Test
	void testOK_0h31() throws BadTimeIntervalException {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		var calc = new MyCalcolatoreSosta(t);
		Ricevuta ticket = calc.calcola(LocalDateTime.of(2022,8,10, 7,30), LocalDateTime.of(2022,8,10, 8,01));
		assertEquals(LocalDateTime.of(2022,8,10, 7,30), ticket.getInizioSosta());
		assertEquals(LocalDateTime.of(2022,8,10, 8,01), ticket.getFineSosta());
		// 2,00+0,80*2
		assertEquals(3.60, ticket.getCosto(), 0.01);
	}

	@Test
	void testOK_0h45() throws BadTimeIntervalException {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		var calc = new MyCalcolatoreSosta(t);
		Ricevuta ticket = calc.calcola(LocalDateTime.of(2022,8,10, 7,30), LocalDateTime.of(2022,8,10, 8,15));
		assertEquals(LocalDateTime.of(2022,8,10, 7,30), ticket.getInizioSosta());
		assertEquals(LocalDateTime.of(2022,8,10, 8,15), ticket.getFineSosta());
		// 2,00+0,80*2
		assertEquals(3.60, ticket.getCosto(), 0.01);
	}
	
	@Test
	void testOK_0h46() throws BadTimeIntervalException {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		var calc = new MyCalcolatoreSosta(t);
		Ricevuta ticket = calc.calcola(LocalDateTime.of(2022,8,10, 7,30), LocalDateTime.of(2022,8,10, 8,16));
		assertEquals(LocalDateTime.of(2022,8,10, 7,30), ticket.getInizioSosta());
		assertEquals(LocalDateTime.of(2022,8,10, 8,16), ticket.getFineSosta());
		// 2,00+0,80*3
		assertEquals(4.40, ticket.getCosto(), 0.01);
	}
	
	@Test
	void testOK_1h00() throws BadTimeIntervalException {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		var calc = new MyCalcolatoreSosta(t);
		Ricevuta ticket = calc.calcola(LocalDateTime.of(2022,8,10, 12,44), LocalDateTime.of(2022,8,10, 13,44));
		assertEquals(LocalDateTime.of(2022,8,10, 12,44), ticket.getInizioSosta());
		assertEquals(LocalDateTime.of(2022,8,10, 13,44), ticket.getFineSosta());
		// 2,00+2,40
		assertEquals(4.40, ticket.getCosto(), 0.01);
	}

	@Test
	void testOK_2h22_conMezzaNotteInMezzo() throws BadTimeIntervalException {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		var calc = new MyCalcolatoreSosta(t);
		Ricevuta ticket = calc.calcola(LocalDateTime.of(2022,8,10, 23,44), LocalDateTime.of(2022,8,11, 2,6));
		assertEquals(LocalDateTime.of(2022,8,10, 23,44), ticket.getInizioSosta());
		assertEquals(LocalDateTime.of(2022,8,11,  2, 6), ticket.getFineSosta());
		// (2,00-0,80) + 2*3,20 + 2*0,80 = 9,20
		assertEquals(9.20, ticket.getCosto(), 0.01);
	}

	@Test
	void testOK_7h30() throws BadTimeIntervalException {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		var calc = new MyCalcolatoreSosta(t);
		Ricevuta ticket = calc.calcola(LocalDateTime.of(2022,8,10, 7,30), LocalDateTime.of(2022,8,10, 15,00));
		assertEquals(LocalDateTime.of(2022,8,10, 7,30), ticket.getInizioSosta());
		assertEquals(LocalDateTime.of(2022,8,10, 15,00), ticket.getFineSosta());
		// 7h30 = 7*3,20 + 1*1,60 + (2,00-0,80)
		assertEquals(25.20, ticket.getCosto(), 0.01);
	}

	@Test
	void testOK_11h00_conCap() throws BadTimeIntervalException {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		var calc = new MyCalcolatoreSosta(t);
		Ricevuta ticket = calc.calcola(LocalDateTime.of(2022,8,10, 6,40), LocalDateTime.of(2022,8,10, 17,40));
		assertEquals(LocalDateTime.of(2022,8,10, 6,40), ticket.getInizioSosta());
		assertEquals(LocalDateTime.of(2022,8,10, 17,40), ticket.getFineSosta());
		// 11*3,20 + (2,00-0,80) = 36,40 --> scatta il cap a 35,00
		assertEquals(35.00, ticket.getCosto(), 0.01);
	}

	@Test
	void testOK_10h00() throws BadTimeIntervalException {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		var calc = new MyCalcolatoreSosta(t);
		Ricevuta ticket = calc.calcola(LocalDateTime.of(2022,8,10, 7,40), LocalDateTime.of(2022,8,10, 17,40));
		assertEquals(LocalDateTime.of(2022,8,10, 7,40), ticket.getInizioSosta());
		assertEquals(LocalDateTime.of(2022,8,10, 17,40), ticket.getFineSosta());
		// 10*3,20 + (2,00-0,80) = 33,20
		assertEquals(33.20, ticket.getCosto(), 0.01);
	}

	@Test
	void testOK_14h30_conCap() throws BadTimeIntervalException {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		var calc = new MyCalcolatoreSosta(t);
		Ricevuta ticket = calc.calcola(LocalDateTime.of(2022,8,10, 6,40), LocalDateTime.of(2022,8,10, 21,10));
		assertEquals(LocalDateTime.of(2022,8,10, 6,40), ticket.getInizioSosta());
		assertEquals(LocalDateTime.of(2022,8,10, 21,10), ticket.getFineSosta());
		// scatta il cap
		assertEquals(35.00, ticket.getCosto(), 0.01);
	}

	@Test
	void testOK_23h30_conCap() throws BadTimeIntervalException {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		var calc = new MyCalcolatoreSosta(t);
		Ricevuta ticket = calc.calcola(LocalDateTime.of(2022,8,10, 6,40), LocalDateTime.of(2022,8,11, 6,10));
		assertEquals(LocalDateTime.of(2022,8,10, 6,40), ticket.getInizioSosta());
		assertEquals(LocalDateTime.of(2022,8,11, 6,10), ticket.getFineSosta());
		// scatta il cap
		assertEquals(35.00, ticket.getCosto(), 0.01);
	}
	
	@Test
	void testOK_24h00_conCap() throws BadTimeIntervalException {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		var calc = new MyCalcolatoreSosta(t);
		Ricevuta ticket = calc.calcola(LocalDateTime.of(2022,8,10, 6,33), LocalDateTime.of(2022,8,11, 6,33));
		assertEquals(LocalDateTime.of(2022,8,10, 6,33), ticket.getInizioSosta());
		assertEquals(LocalDateTime.of(2022,8,11, 6,33), ticket.getFineSosta());
		// scatta il cap
		assertEquals(35.00, ticket.getCosto(), 0.01);
	}
	
	@Test
	void testOK_24h01_conCapEOltre() throws BadTimeIntervalException {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		var calc = new MyCalcolatoreSosta(t);
		Ricevuta ticket = calc.calcola(LocalDateTime.of(2022,8,10, 6,33), LocalDateTime.of(2022,8,11, 6,34));
		assertEquals(LocalDateTime.of(2022,8,10, 6,33), ticket.getInizioSosta());
		assertEquals(LocalDateTime.of(2022,8,11, 6,34), ticket.getFineSosta());
		// scatta il cap 35.00 + oltre 16.00
		assertEquals(51.00, ticket.getCosto(), 0.01);
	}

	@Test
	void testOK_36h_conCapEOltre() throws BadTimeIntervalException {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		var calc = new MyCalcolatoreSosta(t);
		Ricevuta ticket = calc.calcola(LocalDateTime.of(2022,8,10, 6,33), LocalDateTime.of(2022,8,11, 18,33));
		assertEquals(LocalDateTime.of(2022,8,10, 6,33), ticket.getInizioSosta());
		assertEquals(LocalDateTime.of(2022,8,11,18,33), ticket.getFineSosta());
		// scatta il cap 35.00 + oltre 16.00
		assertEquals(51.00, ticket.getCosto(), 0.01);
	}

	@Test
	void testOK_36h01_conCapEOltre() throws BadTimeIntervalException {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		var calc = new MyCalcolatoreSosta(t);
		Ricevuta ticket = calc.calcola(LocalDateTime.of(2022,8,10, 6,33), LocalDateTime.of(2022,8,11, 18,34));
		assertEquals(LocalDateTime.of(2022,8,10, 6,33), ticket.getInizioSosta());
		assertEquals(LocalDateTime.of(2022,8,11,18,34), ticket.getFineSosta());
		// scatta il cap 35.00 + oltre 16.00*2
		assertEquals(67.00, ticket.getCosto(), 0.01);
	}

	@Test
	void testOK_48h_conCapEOltre() throws BadTimeIntervalException {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		var calc = new MyCalcolatoreSosta(t);
		Ricevuta ticket = calc.calcola(LocalDateTime.of(2022,8,10, 6,33), LocalDateTime.of(2022,8,12, 6,33));
		assertEquals(LocalDateTime.of(2022,8,10, 6,33), ticket.getInizioSosta());
		assertEquals(LocalDateTime.of(2022,8,12, 6,33), ticket.getFineSosta());
		// scatta il cap 35.00 + oltre 16.00*2
		assertEquals(67.00, ticket.getCosto(), 0.01);
	}
	
	@Test
	void testKO_OrarioFinaleAntecedenteOrarioIniziale_GiorniDiversi() {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		var calc = new MyCalcolatoreSosta(t);
		assertThrows(BadTimeIntervalException.class, () -> calc.calcola(LocalDateTime.of(2022,8,12, 6,33), LocalDateTime.of(2022,8,10, 6,33)));
	}

	@Test
	void testKO_OrarioFinaleAntecedenteOrarioIniziale_StessoGiornoMaOrariDiversi() {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		var calc = new MyCalcolatoreSosta(t);
		assertThrows(BadTimeIntervalException.class, () -> calc.calcola(LocalDateTime.of(2022,8,10, 6,33), LocalDateTime.of(2022,8,10, 6,30)));
	}

}