package pacchi.model.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import pacchi.model.Dottore;
import pacchi.model.Numero;
import pacchi.model.Pacco;
import pacchi.model.Valore;
import pacchi.model.StatoPartita;
import pacchi.model.Territorio;

class DottoreTest {

	@Test
	void testOK_Iniziale() { //uguale a quello di StatoPartitaTest1
		var pacchi = new HashSet<Pacco>(Set.of(
					new Pacco(new Territorio("Dentinia"),  new Numero(5), new Valore(100000)),
					new Pacco(new Territorio("Topolinia"), new Numero(3), new Valore(10000)),
					new Pacco(new Territorio("Paperopoli"), new Numero(2), new Valore(10))
					));
		var concorrente = new Pacco(new Territorio("Alaska"), new Numero(1), new Valore(1));
				
		var stato = new StatoPartita(pacchi, concorrente);
		assertEquals(pacchi, stato.getPacchiDaAprire());
		assertEquals(concorrente, stato.getPaccoConcorrente());
		assertEquals(pacchi.size(), stato.quantiPacchiDaAprire());

		Dottore dottore = new Dottore(stato);
		assertEquals(stato, dottore.getStatoPartita());
		assertEquals(stato.getPacchi().stream().map(Pacco::premio).mapToInt(Valore::valore).average().getAsDouble(), dottore.media(), 0.5);
	}
	
	@Test
	void testOK_InterpellaConTrePacchi() {
		var pacchi = new HashSet<Pacco>(Set.of(
				new Pacco(new Territorio("Dentinia"),  new Numero(5), new Valore(100000)),
				new Pacco(new Territorio("Topolinia"), new Numero(3), new Valore(10000)),
				new Pacco(new Territorio("Paperopoli"), new Numero(2), new Valore(10))
				));
		var concorrente = new Pacco(new Territorio("Alaska"), new Numero(1), new Valore(1));
			
		var stato = new StatoPartita(pacchi, concorrente);	
		var dottore = new Dottore(stato);
		int offertaMax = dottore.media();
		int offertaMin = offertaMax/4; 
		
		for(int i=0; i<20; i++) { 
			var risposta = dottore.interpella();
			// System.out.println(risposta);
			int offerta = risposta.offerta().valore();
			assertTrue(offerta>=offertaMin && offerta<=offertaMax);
		}
	}
	
	@Test
	void testOK_InterpellaConUnSoloPacco() {
		var pacchi = new HashSet<Pacco>(Set.of(
				new Pacco(new Territorio("Paperopoli"), new Numero(2), new Valore(10))
				));
		var concorrente = new Pacco(new Territorio("Alaska"), new Numero(1), new Valore(1));
			
		var stato = new StatoPartita(pacchi, concorrente);	
		var dottore = new Dottore(stato);
		int offertaMax = dottore.media();
		int offertaMin = offertaMax/4;
		
		for(int i=0; i<20; i++) { 
			var risposta = dottore.interpella();
			// System.out.println(risposta);
			int offerta = risposta.offerta().valore();
			assertTrue(offerta>=offertaMin && offerta<=offertaMax);
		}
	}
	
	@Test
	void testOK_InterpellaConDuePacchi() {
		var pacchi = new HashSet<Pacco>(Set.of(
				new Pacco(new Territorio("Dentinia"),  new Numero(5), new Valore(100000)),
				new Pacco(new Territorio("Paperopoli"), new Numero(2), new Valore(10))
				));
		var concorrente = new Pacco(new Territorio("Alaska"), new Numero(1), new Valore(1));
			
		var stato = new StatoPartita(pacchi, concorrente);	
		var dottore = new Dottore(stato);
		int offertaMax = dottore.media();
		int offertaMin = offertaMax/4; 
		
		for(int i=0; i<20; i++) { 
			var risposta = dottore.interpella();
			// System.out.println(risposta);
			int offerta = risposta.offerta().valore();
			assertTrue(offerta>=offertaMin && offerta<=offertaMax);
		}
	}

}
