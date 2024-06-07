package dentburger.model.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import dentburger.model.Prodotto;
import dentburger.model.Categoria;


class ProdottoTest {

	@Test
	void testOK() {
		var p = new Prodotto(Categoria.BEVANDA, "Succo", "Arancia Amara", 3.50);
		assertEquals(Categoria.BEVANDA, p.getCategoria());
		assertEquals("Succo", p.getGenere());
		assertEquals("Arancia Amara", p.getSpecifica());
		assertEquals(3.50, p.getPrezzo(), 0.01);
	}
	
	@Test
	void testKO_PrezzoNegativo() {
		assertThrows(IllegalArgumentException.class, () -> new Prodotto(Categoria.BEVANDA, "Succo", "Arancia Amara", -0.50));
	}
	
	@Test
	void testKO_PrezzoPiuInfinito() {
		assertThrows(IllegalArgumentException.class, () -> new Prodotto(Categoria.BEVANDA, "Succo", "Arancia Amara", Double.POSITIVE_INFINITY));
	}
	
	@Test
	void testKO_PrezzoMenoInfinito() {
		assertThrows(IllegalArgumentException.class, () -> new Prodotto(Categoria.BEVANDA, "Succo", "Arancia Amara", Double.NEGATIVE_INFINITY));
	}
	
	@Test
	void testKO_PrezzoIndefinito() {
		assertThrows(IllegalArgumentException.class, () -> new Prodotto(Categoria.BEVANDA, "Succo", "Arancia Amara", Double.NaN));
	}

	@Test
	void testKO_NullCat() {
		assertThrows(IllegalArgumentException.class, () -> new Prodotto(null, "Succo", "Arancia Amara", 3.50));
	}

	@Test
	void testKO_NullDen() {
		assertThrows(IllegalArgumentException.class, () -> new Prodotto(Categoria.BEVANDA, null, "Arancia Amara", 3.50));
	}
	
	@Test
	void testKO_NullSpec() {
		assertThrows(IllegalArgumentException.class, () -> new Prodotto(Categoria.BEVANDA, "Succo", null, 3.50));
	}
	
	@Test
	void testKO_EmptyDen() {
		assertThrows(IllegalArgumentException.class, () -> new Prodotto(Categoria.BEVANDA, "", "Arancia Amara", 3.50));
	}
	
	@Test
	void testKO_EmptySpec() {
		assertThrows(IllegalArgumentException.class, () -> new Prodotto(Categoria.BEVANDA, "Succo", "", 3.50));
	}
	
	@Test
	void testKO_BlankDen() {
		assertThrows(IllegalArgumentException.class, () -> new Prodotto(Categoria.BEVANDA, " \t", "Arancia Amara", 3.50));
	}
	
	@Test
	void testKO_BlankSpec() {
		assertThrows(IllegalArgumentException.class, () -> new Prodotto(Categoria.BEVANDA, "Succo", "\t\t", 3.50));
	}

	@Test
	void testOK_Equals(){
		assertEquals( 
				new Prodotto(Categoria.BEVANDA, "Succo", "Arancia Amara", 3.50),
				new Prodotto(Categoria.BEVANDA, "Succo", "Arancia Amara", 3.50) );
		assertEquals( 
				new Prodotto(Categoria.BEVANDA, "Succo", "Arancia Amara", 3.50),
				new Prodotto(Categoria.BEVANDA, "Succo", "Arancia Amara", 4.05) );
	}

	@Test
	void testOK_Hashcode(){
		assertEquals( 
				new Prodotto(Categoria.BEVANDA, "Succo", "Arancia Amara", 3.50).hashCode(),
				new Prodotto(Categoria.BEVANDA, "Succo", "Arancia Amara", 3.50).hashCode() );
		assertEquals( 
				new Prodotto(Categoria.BEVANDA, "Succo", "Arancia Amara", 3.50).hashCode(),
				new Prodotto(Categoria.BEVANDA, "Succo", "Arancia Amara", 4.05).hashCode() );
	}
}
