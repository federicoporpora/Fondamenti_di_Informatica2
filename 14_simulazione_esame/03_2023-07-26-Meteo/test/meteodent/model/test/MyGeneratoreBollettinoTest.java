package meteodent.model.test;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import meteodent.model.Bollettino;
import meteodent.model.MyGeneratoreBollettino;
import meteodent.model.Previsione;
import meteodent.model.ProbPioggia;
import meteodent.model.Temperatura;
import meteodent.model.TipoBollettino;

class MyGeneratoreBollettinoTest {

	@Test
	void testOK_Basic() {
		var previsioni = List.of(
				new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(10,25), Temperatura.of(21), ProbPioggia.of(25)),
				new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(12,25), Temperatura.of(24), ProbPioggia.of(25)),
				new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(16,51), Temperatura.of(22), ProbPioggia.of(71))
				);
		
		var t = new MyGeneratoreBollettino();
		Bollettino b = t.generaBollettino(new ArrayList<>(previsioni), TipoBollettino.SINTETICO);
		
		assertEquals(22, b.getTemperatura());
		assertEquals(43, b.getProbabilitaPioggia());
		assertEquals("Giove", b.getLocalita());
		assertEquals("Giornata variabile, con probabilità di pioggia del 43%\ne temperatura media di 22°C", b.getTesto());
		assertEquals(LocalDate.of(2023,10,11), b.getGiorno());
	}
	
	@Test
	void testOK_PrimaPrevisioneH00() {
		var previsioni = List.of(
				new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(0,0),   Temperatura.of(22), ProbPioggia.of(71)),
				new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(10,25), Temperatura.of(21), ProbPioggia.of(25)),
				new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(12,25), Temperatura.of(24), ProbPioggia.of(25)),
				new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(16,51), Temperatura.of(22), ProbPioggia.of(71))
				);
		
		var t = new MyGeneratoreBollettino();
		Bollettino b = t.generaBollettino(new ArrayList<>(previsioni), TipoBollettino.SINTETICO);
		
		assertEquals(22, b.getTemperatura());
		assertEquals(53, b.getProbabilitaPioggia());
		assertEquals("Giove", b.getLocalita());
		assertEquals("Giornata con piogge diffuse, con probabilità di pioggia del 53%\ne temperatura media di 22°C", b.getTesto());
		assertEquals(LocalDate.of(2023,10,11), b.getGiorno());
	}
	
	@Test
	void testOK_UltimaPrevisioneH24() {
		var previsioni = List.of(
				new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(10,25), Temperatura.of(21), ProbPioggia.of(25)),
				new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(12,25), Temperatura.of(24), ProbPioggia.of(25)),
				new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(16,51), Temperatura.of(22), ProbPioggia.of(71)),
				new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(23,59), Temperatura.of(22), ProbPioggia.of(71))
				);
		
		var t = new MyGeneratoreBollettino();
		Bollettino b = t.generaBollettino(new ArrayList<>(previsioni), TipoBollettino.SINTETICO);
		
		assertEquals(22, b.getTemperatura());
		assertEquals(43, b.getProbabilitaPioggia());
		assertEquals("Giove", b.getLocalita());
		assertEquals("Giornata variabile, con probabilità di pioggia del 43%\ne temperatura media di 22°C", b.getTesto());
		assertEquals(LocalDate.of(2023,10,11), b.getGiorno());
	}
	
	@Test
	void testOK_BasicDETTAGLIATO() {
		var previsioni = List.of(
				new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(10,25), Temperatura.of(21), ProbPioggia.of(25)),
				new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(12,25), Temperatura.of(24), ProbPioggia.of(25)),
				new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(16,51), Temperatura.of(22), ProbPioggia.of(71))
				);
		
		var t = new MyGeneratoreBollettino();
		Bollettino b = t.generaBollettino(new ArrayList<>(previsioni), TipoBollettino.DETTAGLIATO);
		
		assertEquals(22, b.getTemperatura());
		assertEquals(43, b.getProbabilitaPioggia());
		assertEquals("Giove", b.getLocalita());
		assertEquals(
				    "11/10/23, 00:00, Temperatura 21°, Prob. pioggia 25%\n"
				  + "11/10/23, 10:25, Temperatura 21°, Prob. pioggia 25%\n"
				  + "11/10/23, 12:25, Temperatura 24°, Prob. pioggia 25%\n"
				  + "11/10/23, 16:51, Temperatura 22°, Prob. pioggia 71%\n"
				  + "11/10/23, 23:59, Temperatura 22°, Prob. pioggia 71%\n"
				+
				"Giornata variabile, con probabilità di pioggia del 43%\ne temperatura media di 22°C", b.getTesto());
		assertEquals(LocalDate.of(2023,10,11), b.getGiorno());
	}
	
	@Test
	void testOK_UltimaPrevisioneH24DETTAGLIATO() {
		var previsioni = List.of(
				new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(10,25), Temperatura.of(21), ProbPioggia.of(25)),
				new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(12,25), Temperatura.of(24), ProbPioggia.of(25)),
				new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(16,51), Temperatura.of(22), ProbPioggia.of(71)),
				new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(23,59), Temperatura.of(22), ProbPioggia.of(71))
				);
		
		var t = new MyGeneratoreBollettino();
		Bollettino b = t.generaBollettino(new ArrayList<>(previsioni), TipoBollettino.DETTAGLIATO);
		
		assertEquals(22, b.getTemperatura());
		assertEquals(43, b.getProbabilitaPioggia());
		assertEquals("Giove", b.getLocalita());
		assertEquals(
				  "11/10/23, 00:00, Temperatura 21°, Prob. pioggia 25%\n"
				+ "11/10/23, 10:25, Temperatura 21°, Prob. pioggia 25%\n"
				+ "11/10/23, 12:25, Temperatura 24°, Prob. pioggia 25%\n"
				+ "11/10/23, 16:51, Temperatura 22°, Prob. pioggia 71%\n"
				+ "11/10/23, 23:59, Temperatura 22°, Prob. pioggia 71%\n"
				+
				"Giornata variabile, con probabilità di pioggia del 43%\ne temperatura media di 22°C", b.getTesto());
		assertEquals(LocalDate.of(2023,10,11), b.getGiorno());
	}
	
	//---------------------------- check fallimenti ----------------------
	
	@Test
	void testKO_ListaPrevisioniNulla() {
		var t = new MyGeneratoreBollettino();
		assertThrows(IllegalArgumentException.class, () -> t.generaBollettino(null, TipoBollettino.SINTETICO));
	}
	
	@Test
	void testKO_ListaPrevisioniVuota() {
		var t = new MyGeneratoreBollettino();
		assertThrows(IllegalArgumentException.class, () -> t.generaBollettino(Collections.emptyList(), TipoBollettino.SINTETICO));
	}
	
	@Test
	void testKO_ListaPrevisioniNonTutteStessaLocalità_AlienoInMezzo() {
		var previsioni = List.of(
				new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(16,51), Temperatura.of(22), ProbPioggia.of(71)),
				new Previsione("Marte", LocalDate.of(2023,10,11), LocalTime.of(10,25), Temperatura.of(21), ProbPioggia.of(25)),
				new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(12,25), Temperatura.of(24), ProbPioggia.of(25))
				);
		var t = new MyGeneratoreBollettino();
		assertThrows(IllegalArgumentException.class, () -> t.generaBollettino(new ArrayList<>(previsioni), TipoBollettino.SINTETICO));
	}
	
	@Test
	void testKO_ListaPrevisioniNonTutteStessaLocalità_AlienoDavanti() {
		var previsioni = List.of(
				new Previsione("Marte", LocalDate.of(2023,10,11), LocalTime.of(16,51), Temperatura.of(22), ProbPioggia.of(71)),
				new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(10,25), Temperatura.of(21), ProbPioggia.of(25)),
				new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(12,25), Temperatura.of(24), ProbPioggia.of(25))
				);
		var t = new MyGeneratoreBollettino();
		assertThrows(IllegalArgumentException.class, () -> t.generaBollettino(new ArrayList<>(previsioni), TipoBollettino.SINTETICO));
	}
	
	@Test
	void testKO_ListaPrevisioniNonTutteStessaLocalità_AlienoDietro() {
		var previsioni = List.of(
				new Previsione("Marte", LocalDate.of(2023,10,11), LocalTime.of(16,51), Temperatura.of(22), ProbPioggia.of(71)),
				new Previsione("Marte", LocalDate.of(2023,10,11), LocalTime.of(10,25), Temperatura.of(21), ProbPioggia.of(25)),
				new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(12,25), Temperatura.of(24), ProbPioggia.of(25))
				);
		var t = new MyGeneratoreBollettino();
		assertThrows(IllegalArgumentException.class, () -> t.generaBollettino(new ArrayList<>(previsioni), TipoBollettino.SINTETICO));
	}
	
	@Test
	void testKO_ListaPrevisioniNonTutteStessaData_AlienoDietro() {
		var previsioni = List.of(
				new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(16,51), Temperatura.of(22), ProbPioggia.of(71)),
				new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(10,25), Temperatura.of(21), ProbPioggia.of(25)),
				new Previsione("Giove", LocalDate.of(2023,10,10), LocalTime.of(12,25), Temperatura.of(24), ProbPioggia.of(25))
				);
		var t = new MyGeneratoreBollettino();
		assertThrows(IllegalArgumentException.class, () -> t.generaBollettino(new ArrayList<>(previsioni), TipoBollettino.SINTETICO));
	}
	
	@Test
	void testKO_ListaPrevisioniNonTutteStessaData_AlienoDavanti() {
		var previsioni = List.of(
				new Previsione("Giove", LocalDate.of(2023,10,15), LocalTime.of(16,51), Temperatura.of(22), ProbPioggia.of(71)),
				new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(10,25), Temperatura.of(21), ProbPioggia.of(25)),
				new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(12,25), Temperatura.of(24), ProbPioggia.of(25))
				);
		var t = new MyGeneratoreBollettino();
		assertThrows(IllegalArgumentException.class, () -> t.generaBollettino(new ArrayList<>(previsioni), TipoBollettino.SINTETICO));
	}
	
	@Test
	void testKO_ListaPrevisioniNonTutteStessaData_AlienoInMezzo() {
		var previsioni = List.of(
				new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(16,51), Temperatura.of(22), ProbPioggia.of(71)),
				new Previsione("Giove", LocalDate.of(2023,10,12), LocalTime.of(10,25), Temperatura.of(21), ProbPioggia.of(25)),
				new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(12,25), Temperatura.of(24), ProbPioggia.of(25))
				);
		var t = new MyGeneratoreBollettino();
		assertThrows(IllegalArgumentException.class, () -> t.generaBollettino(new ArrayList<>(previsioni), TipoBollettino.SINTETICO));
	}
	
	@Test
	void testKO_ListaPrevisioniConPiùPrevisioniPerLoStessoOrario_AlienoInFondo() {
		var previsioni = List.of(
				new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(16,51), Temperatura.of(22), ProbPioggia.of(71)),
				new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(12,25), Temperatura.of(21), ProbPioggia.of(25)),
				new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(12,25), Temperatura.of(24), ProbPioggia.of(25))
				);
		var t = new MyGeneratoreBollettino();
		assertThrows(IllegalArgumentException.class, () -> t.generaBollettino(new ArrayList<>(previsioni), TipoBollettino.SINTETICO));
	}
	
	@Test
	void testKO_ListaPrevisioniConPiùPrevisioniPerLoStessoOrario_AlienoInMezzo() {
		var previsioni = List.of(
				new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(16,51), Temperatura.of(22), ProbPioggia.of(71)),
				new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(16,51), Temperatura.of(21), ProbPioggia.of(25)),
				new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(12,25), Temperatura.of(24), ProbPioggia.of(25))
				);
		var t = new MyGeneratoreBollettino();
		assertThrows(IllegalArgumentException.class, () -> t.generaBollettino(new ArrayList<>(previsioni), TipoBollettino.SINTETICO));
	}
	
}
