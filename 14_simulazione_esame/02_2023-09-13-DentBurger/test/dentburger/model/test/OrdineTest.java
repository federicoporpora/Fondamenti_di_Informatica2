package dentburger.model.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import dentburger.model.Prodotto;
import dentburger.model.Ordine;
import dentburger.model.Categoria;


class OrdineTest {

	@Test
	void testOK() {
		var prodotti = List.of(
				new Prodotto(Categoria.BEVANDA, "Succo", "Arancia Amara", 3.50),
				new Prodotto(Categoria.PIATTO, "Burger", "Basic", 4.90),
				new Prodotto(Categoria.CONTORNO, "Patatine", "Medie", 2.70)
		);
		var dataOra = LocalDateTime.of(2023,8,15, 12,30);
		var ordine = new Ordine(prodotti, dataOra);					
		assertEquals(prodotti, ordine.getProdotti());
		assertEquals(dataOra,  ordine.getDataOra());
		assertEquals(11.10,    ordine.getPrezzo(), 0.01);
	}
	
	@Test
	void testOK_Aggiungi() {
		var prodotti = new ArrayList<>(List.of(
				new Prodotto(Categoria.BEVANDA, "Succo", "Arancia Amara", 3.50),
				new Prodotto(Categoria.PIATTO, "Burger", "Basic", 4.90),
				new Prodotto(Categoria.CONTORNO, "Patatine", "Medie", 2.70)
		));
		var dataOra = LocalDateTime.of(2023,8,15, 12,30);
		var ordine = new Ordine(prodotti, dataOra);					
		assertEquals(prodotti, ordine.getProdotti());
		assertEquals(dataOra, ordine.getDataOra());
		assertEquals(11.10, ordine.getPrezzo(), 0.01);
		var nuovoProdotto = new Prodotto(Categoria.CONTORNO, "Patatine", "Medie", 2.70);
		ordine.aggiungi(nuovoProdotto);
		assertTrue(ordine.getProdotti().contains(nuovoProdotto));
		assertEquals(13.80, ordine.getPrezzo(), 0.01);
	}
	
	@Test
	void testOK_Rimozione() {
		var prodotti = new ArrayList<>(List.of(
				new Prodotto(Categoria.BEVANDA, "Succo", "Arancia Amara", 3.50),
				new Prodotto(Categoria.PIATTO, "Burger", "Basic", 4.90),
				new Prodotto(Categoria.CONTORNO, "Patatine", "Medie", 2.70)
		));
		var dataOra = LocalDateTime.of(2023,8,15, 12,30);
		var ordine = new Ordine(prodotti, dataOra);					
		assertEquals(prodotti, ordine.getProdotti());
		assertEquals(dataOra, ordine.getDataOra());
		assertEquals(11.10, ordine.getPrezzo(), 0.01);
		var patatine = new Prodotto(Categoria.CONTORNO, "Patatine", "Medie", 2.70);
		ordine.rimuovi(patatine);
		assertFalse(ordine.getProdotti().contains(patatine));
		assertEquals(8.40, ordine.getPrezzo(), 0.01);
	}
	
	@Test
	void testKO_Aggiungi() {
		var prodotti = new ArrayList<>(List.of(
				new Prodotto(Categoria.BEVANDA, "Succo", "Arancia Amara", 3.50),
				new Prodotto(Categoria.PIATTO, "Burger", "Basic", 4.90),
				new Prodotto(Categoria.CONTORNO, "Patatine", "Medie", 2.70)
		));
		var dataOra = LocalDateTime.of(2023,8,15, 12,30);
		var ordine = new Ordine(prodotti, dataOra);					
		assertThrows(IllegalArgumentException.class, () -> ordine.aggiungi(null));
	}
	
	@Test
	void testKO_RimozioneNull() {
		var prodotti = new ArrayList<>(List.of(
				new Prodotto(Categoria.BEVANDA, "Succo", "Arancia Amara", 3.50),
				new Prodotto(Categoria.PIATTO, "Burger", "Basic", 4.90),
				new Prodotto(Categoria.CONTORNO, "Patatine", "Medie", 2.70)
		));
		var dataOra = LocalDateTime.of(2023,8,15, 12,30);
		var ordine = new Ordine(prodotti, dataOra);					
		assertThrows(IllegalArgumentException.class, () -> ordine.rimuovi(null));
	}

	@Test
	void testKO_RimozioneNonPresente() {
		var prodotti = new ArrayList<>(List.of(
				new Prodotto(Categoria.BEVANDA, "Succo", "Arancia Amara", 3.50),
				new Prodotto(Categoria.PIATTO, "Burger", "Basic", 4.90),
				new Prodotto(Categoria.CONTORNO, "Patatine", "Medie", 2.70)
		));
		var dataOra = LocalDateTime.of(2023,8,15, 12,30);
		var ordine = new Ordine(prodotti, dataOra);					
		assertFalse(ordine.rimuovi(
				new Prodotto(Categoria.BEVANDA, "ColaCola", "senza caffeina", 4.50)
				));
	}

	@Test
	void testKO_ListaNulla() {
		var dataOra = LocalDateTime.of(2023,8,15, 12,30);
		assertThrows(IllegalArgumentException.class, () -> new Ordine(null, dataOra));
	}

	@Test
	void testOK_Equals(){
		var prodotti1 = List.of(
				new Prodotto(Categoria.BEVANDA, "Succo", "Arancia Amara", 3.50),
				new Prodotto(Categoria.PIATTO, "Burger", "Basic", 4.90),
				new Prodotto(Categoria.CONTORNO, "Patatine", "Medie", 2.70)
		);
		var dataOra = LocalDateTime.of(2023,8,15, 12,30);
		var o1 = new Ordine(prodotti1, dataOra);					
		var o2 = new Ordine(prodotti1, dataOra);					
		assertEquals(o1,o2);
		var prodotti2 = List.of(
				new Prodotto(Categoria.BEVANDA, "Succo", "Arancia Amara", 3.50),
				new Prodotto(Categoria.PIATTO, "Burger", "Basic", 4.90),
				new Prodotto(Categoria.CONTORNO, "Patatine", "Medie", 2.70)
		);
		var o3 = new Ordine(prodotti2, dataOra);					
		var o4 = new Ordine(prodotti2, dataOra);					
		assertEquals(o3,o4);
	}

	@Test
	void testKO_Equals(){
		var prodotti1 = List.of(
				new Prodotto(Categoria.BEVANDA, "Succo", "Arancia Amara", 3.50),
				new Prodotto(Categoria.PIATTO, "Burger", "Basic", 4.90),
				new Prodotto(Categoria.CONTORNO, "Patatine", "Medie", 2.70)
		);
		var dataOra = LocalDateTime.of(2023,8,15, 12,30);
		var o1 = new Ordine(prodotti1, dataOra);					
		var o3 = new Ordine(prodotti1, dataOra.plusHours(1));
		var o4 = new Ordine(prodotti1, dataOra.plusDays(1));					
		assertNotEquals(o1,o3);
		assertNotEquals(o3,o4);
		assertNotEquals(o1,o4);
		var prodotti2 = List.of(
				new Prodotto(Categoria.PIATTO, "Burger", "Basic", 4.90),
				new Prodotto(Categoria.BEVANDA, "Succo", "Arancia Amara", 3.50),
				new Prodotto(Categoria.CONTORNO, "Patatine", "Medie", 2.70)
		);
		var o5 = new Ordine(prodotti1, dataOra);					
		var o6 = new Ordine(prodotti2, dataOra);					
		assertNotEquals(o5,o6);
	}

	@Test
	void testOK_HashCode(){
		var prodotti1 = List.of(
				new Prodotto(Categoria.BEVANDA, "Succo", "Arancia Amara", 3.50),
				new Prodotto(Categoria.PIATTO, "Burger", "Basic", 4.90),
				new Prodotto(Categoria.CONTORNO, "Patatine", "Medie", 2.70)
		);
		var dataOra = LocalDateTime.of(2023,8,15, 12,30);
		var o1 = new Ordine(prodotti1, LocalDateTime.of(2023,8,15, 12,30));					
		var o2 = new Ordine(prodotti1, LocalDateTime.of(2023,8,15, 12,30));					
		assertEquals(o1.hashCode(),o2.hashCode());
		var prodotti2 = List.of(
				new Prodotto(Categoria.BEVANDA, "Succo", "Arancia Amara", 3.50),
				new Prodotto(Categoria.PIATTO, "Burger", "Basic", 4.90),
				new Prodotto(Categoria.CONTORNO, "Patatine", "Medie", 2.70)
		);
		var o3 = new Ordine(prodotti2, dataOra);					
		var o4 = new Ordine(prodotti2, dataOra);					
		assertEquals(o3.hashCode(),o4.hashCode());
	}

	@Test
	void testKO_HashCode(){
		var prodotti1 = List.of(
				new Prodotto(Categoria.BEVANDA, "Succo", "Arancia Amara", 3.50),
				new Prodotto(Categoria.PIATTO, "Burger", "Basic", 4.90),
				new Prodotto(Categoria.CONTORNO, "Patatine", "Medie", 2.70)
		);
		var dataOra = LocalDateTime.of(2023,8,15, 12,30);
		var o1 = new Ordine(prodotti1, dataOra);					
		var o3 = new Ordine(prodotti1, dataOra.plusHours(1));
		var o4 = new Ordine(prodotti1, dataOra.plusDays(1));					
		assertNotEquals(o1.hashCode(),o3.hashCode());
		assertNotEquals(o3.hashCode(),o4.hashCode());
		assertNotEquals(o1.hashCode(),o4.hashCode());
		var prodotti2 = List.of(
				new Prodotto(Categoria.PIATTO, "Burger", "Basic", 4.90),
				new Prodotto(Categoria.BEVANDA, "Succo", "Arancia Amara", 3.50),
				new Prodotto(Categoria.CONTORNO, "Patatine", "Medie", 2.70)
		);
		var o5 = new Ordine(prodotti1, dataOra);					
		var o6 = new Ordine(prodotti2, dataOra);					
		assertNotEquals(o5.hashCode(),o6.hashCode());
	}

}
