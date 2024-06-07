package dentburger.controller.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

import dentburger.controller.Controller;
import dentburger.model.Prodotto;
import dentburger.model.Categoria;
import dentburger.model.Menu;


class ControllerTest {
	
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
			new Prodotto(Categoria.CONDIMENTO, "Bustina", "Sale", 0.15),
			new Prodotto(Categoria.CONDIMENTO, "Bustina", "Pepe", 0.15),
			new Prodotto(Categoria.CONDIMENTO, "Bustina", "Maionese", 0.45),
			new Prodotto(Categoria.CONDIMENTO, "Bustina", "Ketchup", 0.40)
	};	

	@Test
	void testOK_Ctor(){
		var menu = new Menu();
		Stream.of(prodotti).forEach(menu::add);
		var controller = new Controller(menu);
		assertEquals(menu, controller.getMenu());
	}
	
	@Test
	void testOK_getCategorie(){
		var menu = new Menu();
		Stream.of(prodotti).forEach(menu::add);
		var controller = new Controller(menu);
		assertEquals(
				List.of(Categoria.BEVANDA, Categoria.CONDIMENTO, Categoria.CONTORNO, Categoria.DESSERT, Categoria.PIATTO),
				controller.getCategorie());
	}

	@Test
	void testOK_getGeneriPerCategoria(){
		var menu = new Menu();
		Stream.of(prodotti).forEach(menu::add);
		var controller = new Controller(menu);
		assertEquals(2, controller.getGeneriPerCategoria(Categoria.BEVANDA).size());
		assertEquals(2, controller.getGeneriPerCategoria(Categoria.PIATTO).size());
		assertEquals(2, controller.getGeneriPerCategoria(Categoria.CONTORNO).size());
		assertEquals(3, controller.getGeneriPerCategoria(Categoria.DESSERT).size());
		assertEquals(1, controller.getGeneriPerCategoria(Categoria.CONDIMENTO).size());
	}
	
	@Test
	void testOK_getProdottiPerGenereCategoria(){
		var menu = new Menu();
		Stream.of(prodotti).forEach(menu::add);
		var controller = new Controller(menu);
		assertEquals(2, controller.getProdottiPerGenereCategoria(Categoria.BEVANDA, "Succo").size());
		assertEquals(2, controller.getProdottiPerGenereCategoria(Categoria.BEVANDA, "Acqua").size());
		assertEquals(3, controller.getProdottiPerGenereCategoria(Categoria.PIATTO, "Panino").size());
		assertEquals(2, controller.getProdottiPerGenereCategoria(Categoria.PIATTO, "Insalata").size());
		assertEquals(2, controller.getProdottiPerGenereCategoria(Categoria.CONTORNO, "Patatine").size());
		assertEquals(1, controller.getProdottiPerGenereCategoria(Categoria.CONTORNO, "Grissini").size());
		assertEquals(2, controller.getProdottiPerGenereCategoria(Categoria.DESSERT, "Gelato").size());
		assertEquals(2, controller.getProdottiPerGenereCategoria(Categoria.DESSERT, "Ciambella").size());
		assertEquals(1, controller.getProdottiPerGenereCategoria(Categoria.DESSERT, "Muffin").size());
		assertEquals(4, controller.getProdottiPerGenereCategoria(Categoria.CONDIMENTO, "Bustina").size());
		
		assertEquals(0, controller.getProdottiPerGenereCategoria(Categoria.BEVANDA, "Bottiglietta").size());
		assertEquals(0, controller.getProdottiPerGenereCategoria(Categoria.PIATTO, "Primo").size());
		assertEquals(0, controller.getProdottiPerGenereCategoria(Categoria.DESSERT, "Torta").size());
		assertEquals(0, controller.getProdottiPerGenereCategoria(Categoria.CONDIMENTO, "Verdure").size());
	}
		
}
