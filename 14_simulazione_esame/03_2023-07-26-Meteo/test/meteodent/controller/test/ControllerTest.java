package meteodent.controller.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import meteodent.model.Previsione;
import meteodent.model.ProbPioggia;
import meteodent.model.Temperatura;
import meteodent.model.TipoBollettino;
import meteodent.controller.Controller;


class ControllerTest {

	Map<String, SortedSet<Previsione>> previsioniMap = Map.of(
			"Giove", Set.of(
					new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(10,25), Temperatura.of(21), ProbPioggia.of(25)),
					new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(12,25), Temperatura.of(24), ProbPioggia.of(25)),
					new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(16,51), Temperatura.of(22), ProbPioggia.of(71))
					).stream().collect(Collectors.toCollection(() -> new TreeSet<>((Comparator.comparing(Previsione::getDataOra))))),
							
			"Marte", Set.of(
					new Previsione("Marte", LocalDate.of(2023,10,12), LocalTime.of(10,00), Temperatura.of(55), ProbPioggia.of(5)),
					new Previsione("Marte", LocalDate.of(2023,10,12), LocalTime.of(12,00), Temperatura.of(57), ProbPioggia.of(5)),
					new Previsione("Marte", LocalDate.of(2023,10,12), LocalTime.of(15,30), Temperatura.of(54), ProbPioggia.of(7))
					).stream().collect(Collectors.toCollection(() -> new TreeSet<>((Comparator.comparing(Previsione::getDataOra))))),
							
			"Luna", Set.of(
					new Previsione("Luna", LocalDate.of(2023,10,13), LocalTime.of(10,32), Temperatura.of(41), ProbPioggia.of(2)),
					new Previsione("Luna", LocalDate.of(2023,10,13), LocalTime.of(12,32), Temperatura.of(44), ProbPioggia.of(1)),
					new Previsione("Luna", LocalDate.of(2023,10,13), LocalTime.of(16,32), Temperatura.of(42), ProbPioggia.of(3)),
					new Previsione("Luna", LocalDate.of(2023,10,14), LocalTime.of(11,0), Temperatura.of(61), ProbPioggia.of(3)),
					new Previsione("Luna", LocalDate.of(2023,10,14), LocalTime.of(13,0), Temperatura.of(64), ProbPioggia.of(4)),
					new Previsione("Luna", LocalDate.of(2023,10,14), LocalTime.of(17,0), Temperatura.of(52), ProbPioggia.of(1))
					).stream().collect(Collectors.toCollection(() -> new TreeSet<>((Comparator.comparing(Previsione::getDataOra)))))
			);
	

	@Test
	void testOK_Ctor(){
		var controller = new Controller(previsioniMap);
		assertEquals(previsioniMap, controller.getMappaPrevisioni());
	}
	
	@Test
	void testOK_getListaLocalita(){
		var controller = new Controller(previsioniMap);
		assertEquals(List.of("Giove", "Luna", "Marte"), controller.getListaLocalita());
	}
	
	@Test
	void testOK_getDatePerLocalita(){
		var controller = new Controller(previsioniMap);
		assertEquals(Set.of(LocalDate.of(2023,10,11)), controller.getDatePerLocalita("Giove"));
		assertEquals(Set.of(LocalDate.of(2023,10,12)), controller.getDatePerLocalita("Marte"));
		assertEquals(Set.of(LocalDate.of(2023,10,14),LocalDate.of(2023,10,13)), controller.getDatePerLocalita("Luna"));
	}
	
	@Test
	void testOK_getPrevisioni(){
		var controller = new Controller(previsioniMap);
		assertEquals(previsioniMap, controller.getMappaPrevisioni());
		assertEquals(List.of(
				new Previsione("Luna", LocalDate.of(2023,10,13), LocalTime.of(10,32), Temperatura.of(41), ProbPioggia.of(2)),
				new Previsione("Luna", LocalDate.of(2023,10,13), LocalTime.of(12,32), Temperatura.of(44), ProbPioggia.of(1)),
				new Previsione("Luna", LocalDate.of(2023,10,13), LocalTime.of(16,32), Temperatura.of(42), ProbPioggia.of(3))
				), controller.getPrevisioni("Luna", LocalDate.of(2023,10,13)));
		assertEquals(List.of(
				new Previsione("Luna", LocalDate.of(2023,10,14), LocalTime.of(11,0), Temperatura.of(61), ProbPioggia.of(3)),
				new Previsione("Luna", LocalDate.of(2023,10,14), LocalTime.of(13,0), Temperatura.of(64), ProbPioggia.of(4)),
				new Previsione("Luna", LocalDate.of(2023,10,14), LocalTime.of(17,0), Temperatura.of(52), ProbPioggia.of(1))
				), controller.getPrevisioni("Luna", LocalDate.of(2023,10,14)));
	}
	
	@Test
	void testOK_getBollettinoGiove(){
		var controller = new Controller(previsioniMap);
		var b = controller.getBollettino("Giove", LocalDate.of(2023,10,11), TipoBollettino.SINTETICO);
		assertEquals(LocalDate.of(2023,10,11), b.getGiorno());
		assertEquals("Giove", b.getLocalita());
		assertEquals(43, b.getProbabilitaPioggia());
		assertEquals(22, b.getTemperatura());
	}
	
	@Test
	void testOK_getBollettinoMarte(){
		var controller = new Controller(previsioniMap);
		var b = controller.getBollettino("Marte", LocalDate.of(2023,10,12), TipoBollettino.SINTETICO);
		assertEquals(LocalDate.of(2023,10,12), b.getGiorno());
		assertEquals("Marte", b.getLocalita());
		assertEquals( 6, b.getProbabilitaPioggia());
		assertEquals(55, b.getTemperatura());
	}
	
	@Test
	void testOK_getBollettinoLuna1(){
		var controller = new Controller(previsioniMap);
		var b = controller.getBollettino("Luna", LocalDate.of(2023,10,14), TipoBollettino.SINTETICO);
		assertEquals(LocalDate.of(2023,10,14), b.getGiorno());
		assertEquals("Luna", b.getLocalita());
		assertEquals( 2, b.getProbabilitaPioggia());
		assertEquals(58, b.getTemperatura());
	}
	@Test
	void testOK_getBollettinoLuna2(){
		var controller = new Controller(previsioniMap);
		var b = controller.getBollettino("Luna", LocalDate.of(2023,10,13), TipoBollettino.SINTETICO);
		assertEquals(LocalDate.of(2023,10,13), b.getGiorno());
		assertEquals("Luna", b.getLocalita());
		assertEquals( 2, b.getProbabilitaPioggia());
		assertEquals(42, b.getTemperatura());
	}
	 
	
}
