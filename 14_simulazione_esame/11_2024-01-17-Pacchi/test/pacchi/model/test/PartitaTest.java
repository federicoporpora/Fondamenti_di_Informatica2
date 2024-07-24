package pacchi.model.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

import pacchi.model.Pacco;
import pacchi.model.Partita;
import pacchi.model.Valore;
import pacchi.model.Territorio;


class PartitaTest {

	@Test
	void testOK_Ctor() {
		var territori = Set.of(new Territorio("Dentinia"),new Territorio("Topolinia"),new Territorio("Paperopoli"));
		var premi = Set.of(new Valore(400000),new Valore(4000),new Valore(400));
		var partita = new Partita(territori, premi);
		assertEquals(territori, partita.getTerritori());
		assertEquals(premi, partita.getPremi());
	}

	@Test
	void testKO_Ctor_SeteDiLunghezzaDiversa1() {
		var territori = Set.of(new Territorio("Dentinia"),new Territorio("Topolinia"));
		var premi = Set.of(new Valore(400000),new Valore(4000),new Valore(400));
		assertThrows(IllegalArgumentException.class, () -> new Partita(territori, premi));
	}
	
	@Test
	void testKO_Ctor_SeteDiLunghezzaDiversa2() {
		var territori = Set.of(new Territorio("Dentinia"),new Territorio("Topolinia"),new Territorio("Paperopoli"));
		var premi = Set.of(new Valore(400000),new Valore(400));
		assertThrows(IllegalArgumentException.class, () -> new Partita(territori, premi));
	}
	
	@Test
	void testKO_Ctor_ArgNull1() {
		var territori = Set.of(new Territorio("Dentinia"),new Territorio("Topolinia"),new Territorio("Paperopoli"));
		assertThrows(IllegalArgumentException.class, () -> new Partita(territori, null));
	}
	
	@Test
	void testKO_Ctor_ArgNull2() {
		var premi = Set.of(new Valore(400000),new Valore(4000),new Valore(400));
		assertThrows(IllegalArgumentException.class, () -> new Partita(null, premi));
	}

	@Test
	void testOK_InitialConfig() {
		var territori = Set.of(new Territorio("Dentinia"),new Territorio("Topolinia"),new Territorio("Paperopoli"));
		var premi = Set.of(new Valore(400000),new Valore(4000),new Valore(400));
		var partita = new Partita(territori, premi);		
		var stato = partita.getStatoPartita();
		assertEquals(partita.getPacchi(),stato.getPacchiDaAprire());
		assertEquals(Collections.emptySet(), stato.getPacchiAperti());
	}
	
	@Test
	void testOK_GeneraPacchi() {
		var territori = Set.of(new Territorio("Dentinia"),new Territorio("Topolinia"),new Territorio("Paperopoli"));
		var premi = Set.of(new Valore(400000),new Valore(4000),new Valore(400));
		var partita = new Partita(territori, premi);
		var pacchi = partita.generaPacchi(territori, premi);
		//
		var numeroDiTerritori = territori.size();
		var numeroDiPremi = territori.size();
		assertEquals(numeroDiTerritori,numeroDiPremi); // check già effettuato dal costruttore, ma comunque...
		var numeroAttesoDiPacchi = pacchi.size();
		assertEquals(numeroDiTerritori, numeroAttesoDiPacchi);
		//
		var quantiPremiDistintiNeiPacchiGenerati = pacchi.stream().map(Pacco::premio).distinct().count();
		assertEquals(numeroAttesoDiPacchi, quantiPremiDistintiNeiPacchiGenerati);
		var quantiNumeriDistintiNeiPacchiGenerati = pacchi.stream().map(Pacco::numero).distinct().count();
		assertEquals(numeroAttesoDiPacchi, quantiNumeriDistintiNeiPacchiGenerati);
		var quantiTerritoriDistintiNeiPacchiGenerati = pacchi.stream().map(Pacco::territorio).distinct().count();
		assertEquals(numeroAttesoDiPacchi, quantiTerritoriDistintiNeiPacchiGenerati);
	}
	

	@Test
	void testOK_ApriPacchi() {
		var territori = Set.of(new Territorio("Dentinia"),new Territorio("Topolinia"),new Territorio("Paperopoli"));
		var premi = Set.of(new Valore(400000),new Valore(4000),new Valore(400));
		var partita = new Partita(territori, premi);
		
		var stato = partita.getStatoPartita();
		var SetaInizialePacchi = partita.getPacchi();
		assertEquals(SetaInizialePacchi, stato.getPacchiDaAprire());
		assertEquals(Collections.emptySet(), stato.getPacchiAperti());
		
		// var paccoConcorrente = stato.getPaccoConcorrente();
		// System.out.println("Concorrente: " + paccoConcorrente);
		var numeroInizialePacchi = stato.getPacchiDaAprire().size();
		//  System.out.println("Pacchi da aprire: " + stato.getPacchiDaAprire());
		
		var numeriAperti = new HashSet<Integer>();
		for (Pacco p : stato.getPacchiDaAprire()) {
			// System.out.println("Sto aprendo " + p.numero().n());
			numeriAperti.add(p.numero().valore());
			@SuppressWarnings("unused") var premio = partita.apriPacco(p.numero());
			assertTrue(stato.getPacchiAperti().contains(p));
		}
		// System.out.println("Numeri aperti: " + numeriAperti);
		assertEquals(0, stato.getPacchiDaAprire().size());
		assertEquals(numeroInizialePacchi, stato.getPacchiAperti().size());
		
	}
}
