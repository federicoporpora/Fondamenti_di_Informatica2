package dentburger.model.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import dentburger.model.Prodotto;
import dentburger.model.Categoria;
import dentburger.model.Menu;

class MenuTest {

	Prodotto[] prodotti = {
			new Prodotto(Categoria.BEVANDA, "Succo", "Arancia Amara", 3.50),
			new Prodotto(Categoria.BEVANDA, "Succo", "Limone verde", 3.60),
			new Prodotto(Categoria.BEVANDA, "Acqua", "Naturale", 1.50),
			new Prodotto(Categoria.BEVANDA, "Acqua", "Frizzante", 1.50),
			new Prodotto(Categoria.PIATTO, "Panino", "Prosciutto crudo", 5.50),
			new Prodotto(Categoria.PIATTO, "Panino", "Prosciutto cotto", 4.30),
			new Prodotto(Categoria.PIATTO, "Panino", "Mortadella", 5.10),
			new Prodotto(Categoria.PIATTO, "Insalata", "Verdina", 4.90),
			new Prodotto(Categoria.PIATTO, "Insalata", "Mista maxi", 7.80),
			new Prodotto(Categoria.CONTORNO, "Patatine", "Medie", 3.50),
			new Prodotto(Categoria.CONTORNO, "Patatine", "Grandi", 4.50),
			new Prodotto(Categoria.CONTORNO, "Grissini", "Medi", 1.50),
			new Prodotto(Categoria.DESSERT, "Gelato", "Un gusto", 1.50),
			new Prodotto(Categoria.DESSERT, "Gelato", "Due gusti", 2.70),
			new Prodotto(Categoria.DESSERT, "Ciambella", "col buco", 1.80),
			new Prodotto(Categoria.DESSERT, "Ciambella", "senza buco", 1.90),
			new Prodotto(Categoria.DESSERT, "Muffin", "Mirtilli", 1.50),
			new Prodotto(Categoria.CONDIMENTO, "Sale", "Bustina", 0.15),
			new Prodotto(Categoria.CONDIMENTO, "Pepe", "Bustina", 0.15),
			new Prodotto(Categoria.CONDIMENTO, "Maionese", "Bustina", 0.45),
			new Prodotto(Categoria.CONDIMENTO, "Ketchup", "Bustina", 0.40)
	};

	
	@Test
	void testOK() {
		var menu = new Menu();
		Stream.of(prodotti).forEach(menu::add);
		
		assertEquals(2, menu.getGeneriPerCategoria(Categoria.BEVANDA).size());
		assertEquals(2, menu.getGeneriPerCategoria(Categoria.PIATTO).size());
		assertEquals(2, menu.getGeneriPerCategoria(Categoria.CONTORNO).size());
		assertEquals(3, menu.getGeneriPerCategoria(Categoria.DESSERT).size());
		assertEquals(4, menu.getGeneriPerCategoria(Categoria.CONDIMENTO).size());
		
		assertEquals(2, menu.getProdottiPerCategoriaEGenere(Categoria.BEVANDA, "Succo").size());
		assertEquals(2, menu.getProdottiPerCategoriaEGenere(Categoria.BEVANDA, "Acqua").size());
		assertEquals(3, menu.getProdottiPerCategoriaEGenere(Categoria.PIATTO, "Panino").size());
		assertEquals(2, menu.getProdottiPerCategoriaEGenere(Categoria.PIATTO, "Insalata").size());
		assertEquals(2, menu.getProdottiPerCategoriaEGenere(Categoria.CONTORNO, "Patatine").size());
		assertEquals(1, menu.getProdottiPerCategoriaEGenere(Categoria.CONTORNO, "Grissini").size());
		assertEquals(2, menu.getProdottiPerCategoriaEGenere(Categoria.DESSERT, "Gelato").size());
		assertEquals(2, menu.getProdottiPerCategoriaEGenere(Categoria.DESSERT, "Ciambella").size());
		assertEquals(1, menu.getProdottiPerCategoriaEGenere(Categoria.DESSERT, "Muffin").size());
		assertEquals(1, menu.getProdottiPerCategoriaEGenere(Categoria.CONDIMENTO, "Sale").size());
		
		assertEquals(0, menu.getProdottiPerCategoriaEGenere(Categoria.BEVANDA, "Bottiglietta").size());
		assertEquals(0, menu.getProdottiPerCategoriaEGenere(Categoria.PIATTO, "Primo").size());
		assertEquals(0, menu.getProdottiPerCategoriaEGenere(Categoria.DESSERT, "Torta").size());
		assertEquals(0, menu.getProdottiPerCategoriaEGenere(Categoria.CONDIMENTO, "Verdure").size());
	}
	
	@Test
	void testOK_GetGeneriPerCategoria() {
		var menu = new Menu();
		Stream.of(prodotti).forEach(menu::add);
		
		assertEquals(Set.of("Succo", "Acqua"), menu.getGeneriPerCategoria(Categoria.BEVANDA));
		assertEquals(Set.of("Insalata", "Panino"), menu.getGeneriPerCategoria(Categoria.PIATTO));
		assertEquals(Set.of("Patatine", "Grissini"), menu.getGeneriPerCategoria(Categoria.CONTORNO));
		assertEquals(Set.of("Muffin", "Ciambella", "Gelato"), menu.getGeneriPerCategoria(Categoria.DESSERT));
		assertEquals(Set.of("Sale","Pepe","Maionese","Ketchup"), menu.getGeneriPerCategoria(Categoria.CONDIMENTO));
	}

	@Test
	void testKO_ProdottoDuplicatoStessoPrezzo() {
		var menu = new Menu();
		Stream.of(prodotti).forEach(menu::add);
		assertThrows(IllegalArgumentException.class, () -> menu.add(new Prodotto(Categoria.BEVANDA, "Succo", "Arancia Amara", 3.50)));
	}

	@Test
	void testKO_ProdottoDuplicatoPrezzoDiverso() {
		var menu = new Menu();
		Stream.of(prodotti).forEach(menu::add);
		assertThrows(IllegalArgumentException.class, () -> menu.add(new Prodotto(Categoria.BEVANDA, "Succo", "Arancia Amara", 2.22)));
	}

}
