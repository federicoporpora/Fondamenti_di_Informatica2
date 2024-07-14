package tangenziale.model.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.OptionalInt;
import java.util.TreeMap;
import tangenziale.model.Autostrada;
import tangenziale.model.Tipologia;


class AutostradaTest {

	@Test
	void testOK() {
		var mappaCaselli = new TreeMap<>(Map.of(0, "Casello 1", 4, "Casello 3", 9, "Casello 8", 14, "Casello ultimo"));
		var autostrada = new Autostrada(Tipologia.AUTOSTRADA, "Mx", mappaCaselli);
		assertEquals(4, autostrada.numeroCaselli());
		assertEquals(OptionalInt.of(0), autostrada.progressivaKm("Casello 1"));
		assertEquals(OptionalInt.of(4), autostrada.progressivaKm("Casello 3"));
		assertEquals(OptionalInt.of(9), autostrada.progressivaKm("Casello 8"));
		assertEquals(OptionalInt.of(14), autostrada.progressivaKm("Casello ultimo"));
		assertEquals(OptionalInt.empty(), autostrada.progressivaKm("Casello X"));
	}
	
	@Test
	void testKO_TipologiaNulla() {
		var mappaCaselli = new TreeMap<>(Map.of(0, "Casello 1", 4, "Casello 3", 9, "Casello 8", 14, "Casello ultimo"));
			assertThrows(NullPointerException.class, () -> new Autostrada(null, "Mx", mappaCaselli)); 
	}
	
	@Test
	void testKO_IdNullo() {
		var mappaCaselli = new TreeMap<>(Map.of(0, "Casello 1", 4, "Casello 3", 9, "Casello 8", 14, "Casello ultimo"));
			assertThrows(NullPointerException.class, () -> new Autostrada(Tipologia.AUTOSTRADA, null, mappaCaselli)); 
	}
	
	@Test
	void testKO_IdVuoto() {
		var mappaCaselli = new TreeMap<>(Map.of(0, "Casello 1", 4, "Casello 3", 9, "Casello 8", 14, "Casello ultimo"));
			assertThrows(IllegalArgumentException.class, () -> new Autostrada(Tipologia.AUTOSTRADA, " ", mappaCaselli)); 
	}
	
	@Test
	void testKO_ProfiloNullo() {
			assertThrows(NullPointerException.class, () -> new Autostrada(Tipologia.AUTOSTRADA, "Mx", null)); 
	}
	
	@Test
	void testKO_MancanzaCaselloAlKm0() {
		var mappaCaselli = new TreeMap<>(Map.of(1, "Casello 1", 4, "Casello 3", 9, "Casello 8", 14, "Casello ultimo"));
		assertThrows(IllegalArgumentException.class, () -> new Autostrada(Tipologia.AUTOSTRADA, "MancaKm0", mappaCaselli));
	}
	
	@Test
	void testKO_CaselliOmonimi() {
		var mappaCaselli = new TreeMap<>(Map.of(0, "Casello 1", 4, "Casello 3", 9, "Casello 3", 14, "Casello ultimo"));
		assertThrows(IllegalArgumentException.class, () -> new Autostrada(Tipologia.AUTOSTRADA, "MancaKm0", mappaCaselli));
	}
	
}
