package spesesanitarie.controller.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;

import spesesanitarie.controller.Controller;
import spesesanitarie.model.DocumentoDiSpesa;
import spesesanitarie.model.Tipologia;
import spesesanitarie.model.VoceDiSpesa;


class ControllerTest {

	@Test
	void testOK_Ctor() {
		var spesa1 = new DocumentoDiSpesa(LocalDate.of(2023,5,12), "Farmacia", 17.50, List.of(
				new VoceDiSpesa(Tipologia.FC, 12.0), new VoceDiSpesa(Tipologia.AS, 5.50))
				);
		var spesa2 = new DocumentoDiSpesa(LocalDate.of(2023,5,12), "Medico", 170.00, List.of(
				new VoceDiSpesa(Tipologia.LP, 170.0))
				);
		var spesa3 = new DocumentoDiSpesa(LocalDate.of(2023,5,12), "Farmacia", 38.50, List.of(
				new VoceDiSpesa(Tipologia.FC, 12.0), new VoceDiSpesa(Tipologia.AS, 5.50),
				new VoceDiSpesa(Tipologia.FC, 3.50), new VoceDiSpesa(Tipologia.DM, 8.50),
				new VoceDiSpesa(Tipologia.TK, 6.20), new VoceDiSpesa(Tipologia.AS, 2.80))
				);
		var controller = new Controller(List.of(spesa1,spesa2,spesa3));
		assertEquals(List.of(spesa1,spesa2,spesa3), controller.getSpese());
		assertEquals(List.of(Tipologia.FC, Tipologia.DM, Tipologia.TK, Tipologia.LP, Tipologia.AS), controller.getTipologie());
	}
	
	@Test
	void testOK1_Somma() {
		var spesa1 = new DocumentoDiSpesa(LocalDate.of(2023,5,12), "Farmacia", 17.50, List.of(
				new VoceDiSpesa(Tipologia.FC, 12.0), new VoceDiSpesa(Tipologia.AS, 5.50))
				);
		var spesa2 = new DocumentoDiSpesa(LocalDate.of(2023,5,12), "Medico", 170.00, List.of(
				new VoceDiSpesa(Tipologia.LP, 170.0))
				);
		var spesa3 = new DocumentoDiSpesa(LocalDate.of(2023,5,12), "Farmacia", 38.50, List.of(
				new VoceDiSpesa(Tipologia.FC, 12.0), new VoceDiSpesa(Tipologia.AS, 5.50),
				new VoceDiSpesa(Tipologia.FC, 3.50), new VoceDiSpesa(Tipologia.DM, 8.50),
				new VoceDiSpesa(Tipologia.TK, 6.20), new VoceDiSpesa(Tipologia.AS, 2.80))
				);
		var controller = new Controller(List.of(spesa1,spesa2,spesa3));
		
		assertEquals( 13.80, controller.totaleSpesePer(Tipologia.AS), 0.01);
		assertEquals(  8.50, controller.totaleSpesePer(Tipologia.DM), 0.01);
		assertEquals( 27.50, controller.totaleSpesePer(Tipologia.FC), 0.01);
		assertEquals(170.00, controller.totaleSpesePer(Tipologia.LP), 0.01);
		assertEquals(  6.20, controller.totaleSpesePer(Tipologia.TK), 0.01);
	}
	
	@Test
	void testOK1_Filtra() {
		var spesa1 = new DocumentoDiSpesa(LocalDate.of(2023,5,12), "Farmacia", 17.50, List.of(
				new VoceDiSpesa(Tipologia.FC, 12.0), new VoceDiSpesa(Tipologia.AS, 5.50))
				);
		var spesa2 = new DocumentoDiSpesa(LocalDate.of(2023,5,12), "Medico", 170.00, List.of(
				new VoceDiSpesa(Tipologia.LP, 170.0))
				);
		var spesa3 = new DocumentoDiSpesa(LocalDate.of(2023,5,12), "Farmacia", 38.50, List.of(
				new VoceDiSpesa(Tipologia.FC, 12.0), new VoceDiSpesa(Tipologia.AS, 5.50),
				new VoceDiSpesa(Tipologia.FC, 3.50), new VoceDiSpesa(Tipologia.DM, 8.50),
				new VoceDiSpesa(Tipologia.TK, 6.20), new VoceDiSpesa(Tipologia.AS, 2.80))
				);
		var controller = new Controller(List.of(spesa1,spesa2,spesa3));
		
		assertEquals(List.of(spesa1,spesa3), controller.filtraPer(Tipologia.AS));
		assertEquals(List.of(spesa3), controller.filtraPer(Tipologia.DM));
		assertEquals(List.of(spesa1,spesa3), controller.filtraPer(Tipologia.FC));
		assertEquals(List.of(spesa2), controller.filtraPer(Tipologia.LP));
		assertEquals(List.of(spesa3), controller.filtraPer(Tipologia.TK));
	}
	
	@Test
	void testOK2_Filtra() {
		var spesa1 = new DocumentoDiSpesa(LocalDate.of(2023,5,12), "Farmacia", 19.70, List.of(
				new VoceDiSpesa(Tipologia.FC, 12.0), new VoceDiSpesa(Tipologia.AS, 5.50), new VoceDiSpesa(Tipologia.TK, 2.20))
				);
		var spesa2 = new DocumentoDiSpesa(LocalDate.of(2023,5,12), "Medico", 173.50, List.of(
				new VoceDiSpesa(Tipologia.LP, 170.0), new VoceDiSpesa(Tipologia.DM, 3.50))
				);
		var spesa3 = new DocumentoDiSpesa(LocalDate.of(2023,5,12), "Farmacia", 38.50, List.of(
				new VoceDiSpesa(Tipologia.FC, 12.0), new VoceDiSpesa(Tipologia.AS, 5.50),
				new VoceDiSpesa(Tipologia.FC, 3.50), new VoceDiSpesa(Tipologia.DM, 8.50),
				new VoceDiSpesa(Tipologia.TK, 6.20), new VoceDiSpesa(Tipologia.AS, 2.80))
				);
		var controller = new Controller(List.of(spesa1,spesa2,spesa3));
		
		assertEquals(List.of(spesa1,spesa3), controller.filtraPer(Tipologia.AS));
		assertEquals(List.of(spesa2,spesa3), controller.filtraPer(Tipologia.DM));
		assertEquals(List.of(spesa1,spesa3), controller.filtraPer(Tipologia.FC));
		assertEquals(List.of(spesa2), controller.filtraPer(Tipologia.LP));
		assertEquals(List.of(spesa1,spesa3), controller.filtraPer(Tipologia.TK));
	}
	
	@Test
	void testOK_Detraibili() {
		var spesa1 = new DocumentoDiSpesa(LocalDate.of(2023,5,12), "Farmacia", 19.70, List.of(
				new VoceDiSpesa(Tipologia.FC, 12.0), new VoceDiSpesa(Tipologia.AS, 5.50), new VoceDiSpesa(Tipologia.TK, 2.20))
				);
		var spesa2 = new DocumentoDiSpesa(LocalDate.of(2023,5,12), "Medico", 173.50, List.of(
				new VoceDiSpesa(Tipologia.LP, 170.0), new VoceDiSpesa(Tipologia.DM, 3.50))
				);
		var spesa3 = new DocumentoDiSpesa(LocalDate.of(2023,5,12), "Farmacia", 38.50, List.of(
				new VoceDiSpesa(Tipologia.FC, 12.0), new VoceDiSpesa(Tipologia.AS, 5.50),
				new VoceDiSpesa(Tipologia.FC, 3.50), new VoceDiSpesa(Tipologia.DM, 8.50),
				new VoceDiSpesa(Tipologia.TK, 6.20), new VoceDiSpesa(Tipologia.AS, 2.80))
				);
		var controller = new Controller(List.of(spesa1,spesa2,spesa3));

		assertEquals(217.90, controller.totaleSpeseDetraibili());
		assertEquals( 13.80, controller.totaleSpeseNonDetraibili());
	}
	
}
