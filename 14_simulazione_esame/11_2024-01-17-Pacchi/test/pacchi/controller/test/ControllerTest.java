package pacchi.controller.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import org.junit.jupiter.api.Test;

import pacchi.controller.Controller;
import pacchi.controller.Fase;
import pacchi.controller.WrongPhaseException;
import pacchi.model.Numero;
import pacchi.model.Partita;
import pacchi.model.Territorio;
import pacchi.model.Valore;


class ControllerTest {
	
	@Test
	void testOK_Ctor() {
		var territori = Set.of(new Territorio("Dentinia"), new Territorio("Topolinia"), new Territorio("Paperopoli"), new Territorio("Trantor"));
		var premi = Set.of(new Valore(400000), new Valore(4000), new Valore(400), new Valore(40));
		var controller = new Controller(territori, premi);
		assertEquals(territori, controller.getTerritori());
		assertEquals(premi, controller.getPremi());
		assertEquals(premi, controller.premiRimasti());
		var partita = new Partita(territori, premi);
		assertEquals(partita, controller.getPartita());
	}
	
	@Test
	void testOK_pacchiDaAprire_e_concorrente() {
		var territori = Set.of(new Territorio("Dentinia"), new Territorio("Topolinia"), new Territorio("Paperopoli"), new Territorio("Trantor"));
		var premi = Set.of(new Valore(400000), new Valore(4000), new Valore(400), new Valore(40));
		var controller = new Controller(territori, premi);
		var pDaAprire = controller.getPacchiDaAprire();
		var pConc = controller.getPaccoConcorrente();
		assertEquals(territori.size()-1, pDaAprire.size());
		assertFalse(pDaAprire.contains(pConc));
		assertEquals(premi, controller.premiRimasti());
	}
	
	@Test
	void testOK_quantiAncoraDaAprire() {
		var territori = Set.of(new Territorio("Dentinia"), new Territorio("Topolinia"), new Territorio("Paperopoli"), new Territorio("Trantor"));
		var premi = Set.of(new Valore(400000), new Valore(4000), new Valore(400), new Valore(40));
		var controller = new Controller(territori, premi);
		var quantiMax = controller.getPacchiDaAprire().size();
		var quanti = controller.quantiDaAprire();
		assertTrue(quanti<quantiMax);
		assertTrue(quanti>0);
		assertEquals(premi, controller.premiRimasti());
	}

	@Test
	void testOK_offerta() {
		var territori = Set.of(new Territorio("Dentinia"), new Territorio("Topolinia"), new Territorio("Paperopoli"), new Territorio("Trantor"));
		var premi = Set.of(new Valore(400000), new Valore(4000), new Valore(400), new Valore(40));
		var controller = new Controller(territori, premi);
		var media = controller.getPartita().media();
		var offerta = controller.interpellaDottore().offerta().valore();
		assertTrue(offerta>=media/4 && offerta<=media);
		assertEquals(premi, controller.premiRimasti());
	}
	
	@Test
	void testOK_apriPaccoConc() {
		var territori = Set.of(new Territorio("Dentinia"), new Territorio("Topolinia"), new Territorio("Paperopoli"), new Territorio("Trantor"));
		var premi = Set.of(new Valore(400000), new Valore(4000), new Valore(400), new Valore(40));
		var controller = new Controller(territori, premi);
		assertEquals(controller.getPaccoConcorrente().premio(), controller.apriPaccoConcorrente());
		assertEquals(premi, controller.premiRimasti());
	}
	
	@Test
	void testOK_apriPacchi() {
		var territori = Set.of(new Territorio("Dentinia"), new Territorio("Topolinia"), new Territorio("Paperopoli"), new Territorio("Trantor"));
		var premi = Set.of(new Valore(400000), new Valore(4000), new Valore(400), new Valore(40));
		var controller = new Controller(territori, premi);
		var pConc = controller.getPaccoConcorrente();
		
		//--- apro tutti i pacchi (tranne l'ultimo, si resta nella sfida finale uno contro uno)
		
		var iterator = controller.getPacchiDaAprire().iterator();
		
		do {
			controller.setFase(Fase.APERTURA_PACCHI);
				//System.out.println("quanti da aprire a questo giro: " + controller.quantiDaAprire());
				//System.out.println("fase controller: " + controller.getFase());
			for(int k=0; k<controller.quantiDaAprire(); k++) {
				var n = iterator.next().numero();
				// System.out.println("apertura pacco n.: " + n.valore());				
				assertEquals(controller.apriPacco(n), controller.getPacco(n).premio());
				// System.out.println("premio: " + controller.getPacco(n).premio());
				assertNotEquals(pConc, controller.getPacco(n));
			}
			// System.out.println("quanti ancora da aprire a questo giro: " + controller.quantiDaAprire());
			// System.out.println("fase controller: " + controller.getFase());
			assertEquals(controller.quantiDaAprire()==0 ? Fase.INTERPELLA_DOTTORE : Fase.APERTURA_PACCHI, controller.getFase());
			// System.out.println("ci sono ancora pacchi da aprire? " + iterator.hasNext());
			// System.out.println("pacchi rimasti: " + controller.getPacchiDaAprire());
		} while(controller.getPacchiDaAprire().size()>1);
		
		//--- apro l'ultimo pacco
		
		var n = iterator.next().numero();
		controller.setFase(Fase.FINE);
		@SuppressWarnings("unused") var valoreUltimoPacco = controller.apriUltimoPacco(n);
		// System.out.println("ci sono ancora pacchi da aprire? " + iterator.hasNext());
		// System.out.println("pacchi rimasti: " + controller.getPacchiDaAprire());
		assertEquals(0, controller.getPacchiDaAprire().size());
	}
	
	@Test
	void testKO_Ctor_PremiNull() {
		var territori = Set.of(new Territorio("Dentinia"), new Territorio("Topolinia"), new Territorio("Paperopoli"), new Territorio("Trantor"));
		assertThrows(IllegalArgumentException.class, () -> new Controller(territori, null));
	}
	
	@Test
	void testKO_Ctor_TerritoriNull() {
		var premi = Set.of(new Valore(400000), new Valore(4000), new Valore(400), new Valore(40));
		assertThrows(IllegalArgumentException.class, () -> new Controller(null, premi));
	}
	
	@Test
	void testKO_ApriPacco_WrongPhase_ACCETTA_RIFIUTA() {
		var premi = Set.of(new Valore(400000), new Valore(4000), new Valore(400), new Valore(40));
		var territori = Set.of(new Territorio("Dentinia"), new Territorio("Topolinia"), new Territorio("Paperopoli"), new Territorio("Trantor"));
		var controller = new Controller(territori, premi);
		controller.setFase(Fase.ACCETTA_RIFIUTA);
		assertThrows(WrongPhaseException.class, () -> controller.apriPacco(new Numero(1)));
	}
	
	@Test
	void testKO_ApriPacco_WrongPhase_FINE() {
		var premi = Set.of(new Valore(400000), new Valore(4000), new Valore(400), new Valore(40));
		var territori = Set.of(new Territorio("Dentinia"), new Territorio("Topolinia"), new Territorio("Paperopoli"), new Territorio("Trantor"));
		var controller = new Controller(territori, premi);
		controller.setFase(Fase.FINE);
		assertThrows(WrongPhaseException.class, () -> controller.apriPacco(new Numero(1)));
	}
	
	@Test
	void testKO_ApriPacco_WrongPhase_INTERPELLA_DOTTORE() {
		var premi = Set.of(new Valore(400000), new Valore(4000), new Valore(400), new Valore(40));
		var territori = Set.of(new Territorio("Dentinia"), new Territorio("Topolinia"), new Territorio("Paperopoli"), new Territorio("Trantor"));
		var controller = new Controller(territori, premi);
		controller.setFase(Fase.INTERPELLA_DOTTORE);
		assertThrows(WrongPhaseException.class, () -> controller.apriPacco(new Numero(1)));
	}
	
	@Test
	void testKO_ApriUltimoPacco_WrongPhase() {
		var premi = Set.of(new Valore(400000), new Valore(4000), new Valore(400), new Valore(40));
		var territori = Set.of(new Territorio("Dentinia"), new Territorio("Topolinia"), new Territorio("Paperopoli"), new Territorio("Trantor"));
		var controller = new Controller(territori, premi);
		// la fase di default è APERTURA_PACCHI mentre apriUltimoPacco richiede FINE
		assertThrows(WrongPhaseException.class, () -> controller.apriUltimoPacco(new Numero(1)));
	}
		
}
