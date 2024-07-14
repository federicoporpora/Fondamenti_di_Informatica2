package tangenziale.model.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import tangenziale.model.Autostrada;
import tangenziale.model.MyPlanner;
import tangenziale.model.Planner;
import tangenziale.model.Tipologia;


class MyPlannerTest {

	private static Map<String,Autostrada> reteAutostradale;
	private static Planner planner;
	
	@BeforeAll
	public static void setup() {
		reteAutostradale =  Map.of(
				"M3", new Autostrada(Tipologia.AUTOSTRADA, "M3",
						new TreeMap<>(Map.of(45, "Petruvia Nord", 40, "Petruvia Sud", 25, "Aldino", 1, "Dentinia ippodromo", 0, "Tangenziale"))
						),
				"M4", new Autostrada(Tipologia.AUTOSTRADA, "M4",
						new TreeMap<>(Map.of(0, "Tangenziale", 24, "Castelbello", 37, "Molla", 50, "Forte", 77, "Ripalta"))
						),
				"M1", new Autostrada(Tipologia.AUTOSTRADA, "M1",
						new TreeMap<>(Map.of(85, "Montelusa Centro", 78, "Montelusa Z.I.", 48, "Vigata ovest", 42, "Vigata Centro", 37, "Vigata est", 12, "Dentinia outlet", 0, "Tangenziale"))
						),
				"M2", new Autostrada(Tipologia.AUTOSTRADA, "M2",
						new TreeMap<>(Map.of(0, "Tangenziale", 18, "Sasso del Moro", 39, "Val di Mezzo", 49, "Val di Sopra", 76, "Val d'altrove"))
						),
				"Tangenziale", new Autostrada(Tipologia.TANGENZIALE, "Tangenziale", 
						new TreeMap<>(Map.of(0, "M2", 4, "via dei colli", 9, "M1", 13, "Aeroporto", 19, "M3", 23, "Fiera", 26, "Stadio", 32, "M4"))
						)
				);
		planner = new MyPlanner(reteAutostradale);
	}
	
	@Test
	void testOK1_TuttoSuStessaAutostrada() {
		var percorso = planner.trovaPercorso("Petruvia Nord", "Dentinia ippodromo");
		//System.out.println(percorso);
		assertEquals(44,percorso.lunghezza());
		assertEquals(44,percorso.lunghezza(Tipologia.AUTOSTRADA));
		assertEquals(0, percorso.lunghezza(Tipologia.TANGENZIALE));
		assertEquals(44,percorso.lunghezzaUtile());
	}
	
	@Test
	void testOK2_TuttoSuStessaAutostrada() {
		var percorso = planner.trovaPercorso("Dentinia ippodromo", "Petruvia Nord");
		//System.out.println(percorso);
		assertEquals(44,percorso.lunghezza());
		assertEquals(44,percorso.lunghezza(Tipologia.AUTOSTRADA));
		assertEquals(0, percorso.lunghezza(Tipologia.TANGENZIALE));
		assertEquals(44,percorso.lunghezzaUtile());
	}

	@Test
	void testOK3_MetaAutostradaMetaTangenziale_Gratis() {
		var percorso = planner.trovaPercorso("Dentinia ippodromo", "Sasso del Moro");
		System.out.println(percorso);
		assertEquals(38,percorso.lunghezza());
		assertEquals(19,percorso.lunghezza(Tipologia.AUTOSTRADA));
		assertEquals(19,percorso.lunghezza(Tipologia.TANGENZIALE));
		assertEquals(38,percorso.lunghezzaUtile());
	}

	@Test
	void testOK4_QuasiTuttaTangenziale_OvviamenteGratis() {
		var percorso = planner.trovaPercorso("Dentinia ippodromo", "via dei colli");
		//System.out.println(percorso);
		assertEquals(16,percorso.lunghezza());
		assertEquals(1, percorso.lunghezza(Tipologia.AUTOSTRADA));
		assertEquals(15,percorso.lunghezza(Tipologia.TANGENZIALE));
		assertEquals(1, percorso.lunghezzaUtile());
	}

	@Test
	void testOK5_ParteAutostradaParteTangenziale_Gratis() {
		var percorso = planner.trovaPercorso("Aeroporto", "Petruvia Sud");
		//System.out.println(percorso);
		assertEquals(46,percorso.lunghezza());
		assertEquals(40,percorso.lunghezza(Tipologia.AUTOSTRADA));
		assertEquals(6,percorso.lunghezza(Tipologia.TANGENZIALE));
		assertEquals(40,percorso.lunghezzaUtile());
	}
	
	@Test
	void testOK6_ParteAutostradaParteTangenziale_APagamento() {
		var percorso = planner.trovaPercorso("Aeroporto", "Forte");
		// System.out.println(percorso);
		assertEquals(69,percorso.lunghezza());
		assertEquals(50,percorso.lunghezza(Tipologia.AUTOSTRADA));
		assertEquals(19,percorso.lunghezza(Tipologia.TANGENZIALE));
		assertEquals(69,percorso.lunghezzaUtile());
	}
	
	@Test
	void testKO_ArgNull() {
		assertThrows(NullPointerException.class, () -> new MyPlanner(null));
	}
	
	@Test
	void testKO_ArgVuoto() {
		assertThrows(IllegalArgumentException.class, () -> new MyPlanner(Map.of()));
	}

	@Test
	void testKO_NoTangenziale() {
		assertThrows(IllegalArgumentException.class, () -> new MyPlanner(Map.of(
				"M1", new Autostrada(Tipologia.AUTOSTRADA, "M1",
						new TreeMap<>(Map.of(85, "Montelusa Centro", 78, "Montelusa Z.I.", 48, "Vigata ovest", 42, "Vigata Centro", 37, "Vigata est", 12, "Dentinia outlet", 0, "Tangenziale"))
						),
				"M2", new Autostrada(Tipologia.AUTOSTRADA, "M2",
						new TreeMap<>(Map.of(0, "Tangenziale", 18, "Sasso del Moro", 39, "Val di Mezzo", 49, "Val di Sopra", 76, "Val d'altrove"))
						)				
				)));
	}

}
