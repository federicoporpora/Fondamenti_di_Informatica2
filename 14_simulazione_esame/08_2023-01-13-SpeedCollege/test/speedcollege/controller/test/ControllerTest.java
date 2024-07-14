package speedcollege.controller.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import speedcollege.controller.Controller;
import speedcollege.model.AttivitaFormativa;
import speedcollege.model.Attualizzatore;
import speedcollege.model.Carriera;
import speedcollege.model.Esame;
import speedcollege.model.Voto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


class ControllerTest {

	Carriera carriera, carriera2;
	Attualizzatore attualizzatore;
	Controller controller;
	
	@Test
	void testOK_Ctor() {
		carriera = new Carriera();
		AttivitaFormativa af1 = new AttivitaFormativa(28006, "Fondamenti di Informatica T-2", 12);
		Esame esameFondT2 = new Esame(af1, LocalDate.of(2020, 7, 1), Voto.DICIOTTO);
		carriera.inserisci(esameFondT2);
		AttivitaFormativa af2 = new AttivitaFormativa(27993, "Analisi Matematica T-2", 6);
		Esame esameAnalisi2 = new Esame(af2, LocalDate.of(2020, 7, 1), Voto.VENTICINQUE);
		carriera.inserisci(esameAnalisi2);
		AttivitaFormativa af3 = new AttivitaFormativa(27991, "Analisi Matematica T-1", 9);
		Esame esameAnalisi1 = new Esame(af3, LocalDate.of(2020, 7, 1), Voto.TRENTAELODE);
		carriera.inserisci(esameAnalisi1);
		
		controller = new Controller(new TreeMap<>(Map.of("carriera fake", carriera)));
		assertEquals(carriera, controller.getCarriera("carriera fake"));
	}
	
	@Test
	void testKO_Ctor_NullMap() {
		assertThrows(IllegalArgumentException.class, () -> new Controller(null));
	}

	@Test
	void testKO_Ctor_EmptyMap() {
		assertThrows(IllegalArgumentException.class, () -> new Controller(new TreeMap<String,Carriera>()));
	}
	
	
	@Test
	void testOK_getSetCarriera() {
		carriera = new Carriera();
		AttivitaFormativa af1 = new AttivitaFormativa(28006, "Fondamenti di Informatica T-2", 12);
		Esame esameFondT2 = new Esame(af1, LocalDate.of(2020, 7, 1), Voto.DICIOTTO);
		carriera.inserisci(esameFondT2);
		AttivitaFormativa af2 = new AttivitaFormativa(27993, "Analisi Matematica T-2", 6);
		Esame esameAnalisi2 = new Esame(af2, LocalDate.of(2020, 7, 1), Voto.VENTICINQUE);
		carriera.inserisci(esameAnalisi2);
		AttivitaFormativa af3 = new AttivitaFormativa(27991, "Analisi Matematica T-1", 9);
		Esame esameAnalisi1 = new Esame(af3, LocalDate.of(2020, 7, 1), Voto.TRENTAELODE);
		carriera.inserisci(esameAnalisi1);
		
		carriera2 = new Carriera();
		Esame esameFondT2bis = new Esame(af1, LocalDate.of(2020, 7, 1), Voto.VENTITRE);
		carriera2.inserisci(esameFondT2bis);
		Esame esameAnalisi2bis = new Esame(af2, LocalDate.of(2020, 7, 1), Voto.TRENTA);
		carriera2.inserisci(esameAnalisi2bis);
		Esame esameAnalisi1bis = new Esame(af3, LocalDate.of(2020, 7, 1), Voto.RESPINTO);
		carriera2.inserisci(esameAnalisi1bis);
		
		controller = new Controller(new TreeMap<>(Map.of(
				"carriera fake1", carriera, "carriera fake2", carriera2 )));
		assertEquals(carriera, controller.getCarriera("carriera fake1"));
		assertEquals(carriera2, controller.getCarriera("carriera fake2"));
		assertEquals(List.of("carriera fake1", "carriera fake2"), controller.getListaIdCarriere());
		
		controller.setCarriera("carriera fake2");
	}

	@Test
	void testOK_mediaPesataCarriera1() {
		carriera = new Carriera();
		AttivitaFormativa af1 = new AttivitaFormativa(28006, "Fondamenti di Informatica T-2", 12);
		Esame esameFondT2 = new Esame(af1, LocalDate.of(2020, 7, 1), Voto.DICIOTTO);
		carriera.inserisci(esameFondT2);
		AttivitaFormativa af2 = new AttivitaFormativa(27993, "Analisi Matematica T-2", 6);
		Esame esameAnalisi2 = new Esame(af2, LocalDate.of(2020, 7, 1), Voto.VENTICINQUE);
		carriera.inserisci(esameAnalisi2);
		AttivitaFormativa af3 = new AttivitaFormativa(27991, "Analisi Matematica T-1", 9);
		Esame esameAnalisi1 = new Esame(af3, LocalDate.of(2020, 7, 1), Voto.TRENTAELODE);
		carriera.inserisci(esameAnalisi1);
		
		controller = new Controller(new TreeMap<>(Map.of("carriera fake1", carriera)));
		controller.setCarriera("carriera fake1");
		assertEquals(23.55, controller.getMediaPesata(), 0.01);
	}
	
	@Test
	void testOK_mediaPesataCarriera2() {
		carriera = new Carriera();
		AttivitaFormativa af1 = new AttivitaFormativa(28006, "Fondamenti di Informatica T-2", 12);
		Esame esameFondT2 = new Esame(af1, LocalDate.of(2020, 7, 1), Voto.DICIOTTO);
		carriera.inserisci(esameFondT2);
		AttivitaFormativa af2 = new AttivitaFormativa(27993, "Analisi Matematica T-2", 6);
		Esame esameAnalisi2 = new Esame(af2, LocalDate.of(2020, 7, 1), Voto.VENTICINQUE);
		carriera.inserisci(esameAnalisi2);
		AttivitaFormativa af3 = new AttivitaFormativa(27991, "Analisi Matematica T-1", 9);
		Esame esameAnalisi1 = new Esame(af3, LocalDate.of(2020, 7, 1), Voto.TRENTAELODE);
		carriera.inserisci(esameAnalisi1);

		carriera2 = new Carriera();
		Esame esameFondT2bis = new Esame(af1, LocalDate.of(2020, 7, 1), Voto.VENTITRE);
		carriera2.inserisci(esameFondT2bis);
		Esame esameAnalisi2bis = new Esame(af2, LocalDate.of(2020, 7, 1), Voto.TRENTA);
		carriera2.inserisci(esameAnalisi2bis);
		Esame esameAnalisi1bis = new Esame(af3, LocalDate.of(2020, 7, 1), Voto.RESPINTO);
		carriera2.inserisci(esameAnalisi1bis);
		
		controller = new Controller(new TreeMap<>(
				Map.of("carriera fake1", carriera,"carriera fake2", carriera2)));
		
		controller.setCarriera("carriera fake1");
		assertEquals(23.55, controller.getMediaPesata(), 0.01);
		assertEquals(27.0, controller.getCreditiAcquisiti("carriera fake1"), 0.01);

		controller.setCarriera("carriera fake2");
		assertEquals(25.33, controller.getMediaPesata(), 0.01);
		assertEquals(18.0, controller.getCreditiAcquisiti("carriera fake2"), 0.01);
	}
	
	@Test
	void testOK_DataAttualizzazione() {
		carriera = new Carriera();
		AttivitaFormativa af1 = new AttivitaFormativa(28006, "Fondamenti di Informatica T-2", 12);
		Esame esameFondT2 = new Esame(af1, LocalDate.of(2020, 7, 1), Voto.DICIOTTO);
		carriera.inserisci(esameFondT2);
		AttivitaFormativa af2 = new AttivitaFormativa(27993, "Analisi Matematica T-2", 6);
		Esame esameAnalisi2 = new Esame(af2, LocalDate.of(2020, 7, 1), Voto.VENTICINQUE);
		carriera.inserisci(esameAnalisi2);
		AttivitaFormativa af3 = new AttivitaFormativa(27991, "Analisi Matematica T-1", 9);
		Esame esameAnalisi1 = new Esame(af3, LocalDate.of(2020, 7, 1), Voto.TRENTAELODE);
		carriera.inserisci(esameAnalisi1);
		
		controller = new Controller(new TreeMap<>(Map.of("carriera fake1", carriera)));
		controller.setCarriera("carriera fake1");
		assertEquals(23.55, controller.getMediaPesata(), 0.01);
		assertEquals(LocalDate.now(), controller.getDataDiAttualizzazione());
	}
	
	@Test
	void testOK_SetDataAttualizzazione() {
		carriera = new Carriera();
		AttivitaFormativa af1 = new AttivitaFormativa(28006, "Fondamenti di Informatica T-2", 12);
		Esame esameFondT2 = new Esame(af1, LocalDate.of(2020, 7, 1), Voto.DICIOTTO);
		carriera.inserisci(esameFondT2);
		AttivitaFormativa af2 = new AttivitaFormativa(27993, "Analisi Matematica T-2", 6);
		Esame esameAnalisi2 = new Esame(af2, LocalDate.of(2020, 7, 1), Voto.VENTICINQUE);
		carriera.inserisci(esameAnalisi2);
		AttivitaFormativa af3 = new AttivitaFormativa(27991, "Analisi Matematica T-1", 9);
		Esame esameAnalisi1 = new Esame(af3, LocalDate.of(2020, 7, 1), Voto.TRENTAELODE);
		carriera.inserisci(esameAnalisi1);
		
		controller = new Controller(new TreeMap<>(Map.of("carriera fake1", carriera)));
		controller.setCarriera("carriera fake1");
		controller.setDataDiAttualizzazione(LocalDate.of(2022, 1, 1));
		assertEquals(23.55, controller.getMediaPesata(), 0.01);
		assertEquals(LocalDate.of(2022, 1, 1), controller.getDataDiAttualizzazione());
	}
	
	@Test
	void testOK_ElencoFunzioni() {
		carriera = new Carriera();
		AttivitaFormativa af1 = new AttivitaFormativa(28006, "Fondamenti di Informatica T-2", 12);
		Esame esameFondT2 = new Esame(af1, LocalDate.of(2020, 7, 1), Voto.DICIOTTO);
		carriera.inserisci(esameFondT2);
		AttivitaFormativa af2 = new AttivitaFormativa(27993, "Analisi Matematica T-2", 6);
		Esame esameAnalisi2 = new Esame(af2, LocalDate.of(2020, 7, 1), Voto.VENTICINQUE);
		carriera.inserisci(esameAnalisi2);
		AttivitaFormativa af3 = new AttivitaFormativa(27991, "Analisi Matematica T-1", 9);
		Esame esameAnalisi1 = new Esame(af3, LocalDate.of(2020, 7, 1), Voto.TRENTAELODE);
		carriera.inserisci(esameAnalisi1);
		
		controller = new Controller(new TreeMap<>(Map.of("carriera fake1", carriera)));
		controller.setCarriera("carriera fake1");

		assertEquals(Set.of("Nessun decadimento", "-1 voto al mese", "-1/2 voto ogni 10 gg"), controller.elencoFunzioni());
	}
	
	@Test
	void testOK_getFunction() {
		carriera = new Carriera();
		AttivitaFormativa af1 = new AttivitaFormativa(28006, "Fondamenti di Informatica T-2", 12);
		Esame esameFondT2 = new Esame(af1, LocalDate.of(2020, 7, 1), Voto.DICIOTTO);
		carriera.inserisci(esameFondT2);
		AttivitaFormativa af2 = new AttivitaFormativa(27993, "Analisi Matematica T-2", 6);
		Esame esameAnalisi2 = new Esame(af2, LocalDate.of(2020, 7, 1), Voto.VENTICINQUE);
		carriera.inserisci(esameAnalisi2);
		AttivitaFormativa af3 = new AttivitaFormativa(27991, "Analisi Matematica T-1", 9);
		Esame esameAnalisi1 = new Esame(af3, LocalDate.of(2020, 7, 1), Voto.TRENTAELODE);
		carriera.inserisci(esameAnalisi1);
		
		controller = new Controller(new TreeMap<>(Map.of("carriera fake1", carriera)));
		controller.setCarriera("carriera fake1");

		assertEquals(Attualizzatore.NESSUN_DECADIMENTO,controller.getFunction("Nessun decadimento")); 
		assertEquals(Attualizzatore.UN_VOTO_IN_MENO_AL_MESE,controller.getFunction("-1 voto al mese"));
		assertEquals(Attualizzatore.MEZZO_VOTO_IN_MENO_OGNI_10_GG,controller.getFunction("-1/2 voto ogni 10 gg"));
	}
}
