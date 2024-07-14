package tangenziale.controller.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import tangenziale.controller.Controller;
import tangenziale.model.Autostrada;
import tangenziale.model.Tipologia;
import tangenziale.persistence.BadFileFormatException;


class ControllerTest {

	private static Map<String,Autostrada> reteAutostradale;
	
	@BeforeAll
	public static void setup() {
		reteAutostradale = Map.of(
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
		
	}
		
	@Test
	void testOK() throws IOException, BadFileFormatException {
		var controller = new Controller(reteAutostradale);
		var listaCaselli = List.of("Petruvia Nord", "Petruvia Sud", "Aldino", "Dentinia ippodromo",
				 "Castelbello", "Molla", "Forte", "Ripalta",
				 "Montelusa Centro", "Montelusa Z.I.", "Vigata ovest", "Vigata Centro", "Vigata est", "Dentinia outlet",
				 "Sasso del Moro", "Val di Mezzo", "Val di Sopra", "Val d'altrove",
				 "via dei colli", "Aeroporto", "Fiera", "Stadio");
		assertEquals(listaCaselli.stream().sorted().toList(), controller.elencoCaselli());
	}
	
	@Test
	void testKO_ArgumentNull() throws IOException, BadFileFormatException {
		assertThrows( NullPointerException.class, () -> new Controller(null));
	}
	
	@Test
	void testKO_ArgumentoVuoto() throws IOException, BadFileFormatException {
		assertThrows( IllegalArgumentException.class, () -> new Controller(Map.of()));
	}
	
	// non collaudiamo trovaPercorso perché è solo una delega all'omonimo metodo di Percorso
	
}
