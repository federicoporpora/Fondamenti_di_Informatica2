package dentburger.persistence.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.StringReader;
import org.junit.jupiter.api.Test;

import dentburger.model.Categoria;
import dentburger.persistence.BadFileFormatException;
import dentburger.persistence.MyMenuReader;


public class MyMenuReaderTest {

	@Test
	void testOK() throws IOException, BadFileFormatException {
		String fakefile = 
				    "Piatto	Burger	Basic		4,90\u00A0€\r\n"
				  + "Piatto	Burger	Formaggio	7,20\u00A0€\r\n"
				  + "Piatto	Burger	Bacon		7,50\u00A0€\r\n"
				  + "Piatto	Burger	Pollo		6,90\u00A0€\r\n"
				  + "Piatto	Burger	Pesce		4,20\u00A0€\r\n"
				  + "Piatto	Burger	Veg			6,50\u00A0€\r\n"
				  + "Piatto	Toast	Classico	6,50\u00A0€\r\n"
				  + "Piatto	Burger	Senza glutine	8,80\u00A0€\r\n"
				  + "Piatto	Wrap	Veggie			7,40\u00A0€\r\n"
				  + "Piatto	Wrap	Megamix			9,50\u00A0€\r\n"
				  + "Piatto	Fritti	4 Crocchette	4,60\u00A0€\r\n"
				  + "Piatto	Fritti	6 Crocchette	5,80\u00A0€\r\n"
				  + "Piatto	Fritti	8 Crocchette	7,00\u00A0€\r\n"
				  + "Piatto	Fritti	Alette di pollo	3,90\u00A0€\r\n"
				  + "Piatto	Insalata	Verde		3,50\u00A0€\r\n"
				  + "Piatto	Insalata	Mista		3,80\u00A0€\r\n"
				  + "Piatto	Insalata	con pollo	5,20\u00A0€\r\n"
				  + "Contorno	Patatine	Medie	2,70\u00A0€\r\n"
				  + "Contorno	Patatine	Grandi	3,60\u00A0€\r\n"
				  + "Contorno	Patatine	Formaggio	5,50\u00A0€\r\n"
				  + "Bevanda	Cola	Media		3,50\u00A0€\r\n"
				  + "Bevanda	Cola	Grande		4,50\u00A0€\r\n"
				  + "Bevanda	Cola0	Media		3,80\u00A0€\r\n"
				  + "Bevanda	Cola0	Grande		4,80\u00A0€\r\n"
				  + "Bevanda	Acqua	Media		1,50\u00A0€\r\n"
				  + "Bevanda	Aranciata	Media	3,60\u00A0€\r\n"
				  + "Dessert	Gelato	Panna		1,30\u00A0€\r\n"
				  + "Dessert	Gelato	Panna e cioccolato	1,80\u00A0€\r\n"
				  + "Dessert	Gelato	Panna e fragola		1,80\u00A0€\r\n"
				  + "Dessert	Gelato	Panna e caramello	1,80\u00A0€\r\n"
				  + "Dessert	Pancake	con sciroppo		6,10\u00A0€\r\n"
				  + "Dessert	Muffin	cioccolato	4,80\u00A0€\r\n"
				  + "Dessert	Muffin	mirtilli	4,80\u00A0€\r\n"
				  + "Dessert	Donut	basic		2,00\u00A0€\r\n"
				  + "Dessert	Donut	cioccolato	2,20\u00A0€\r\n"
				  + "Condimento	Bustina	Ketchup	0,30\u00A0€\r\n"
				  + "Condimento	Bustina	Maionese	0,30\u00A0€\r\n"
				  + "Condimento	Bustina	Senape	0,30\u00A0€\r\n"
				  + "Condimento	Bustina	Sale	0,10\u00A0€\r\n"
				  + "Condimento	Bustina	Pepe	0,10\u00A0€\r\n"
				  + "Condimento	Dispenser	Olio	0,20\u00A0€"
				  ;
		
		var menu = new MyMenuReader().leggiProdotti(new StringReader(fakefile));
		assertEquals(4, menu.getGeneriPerCategoria(Categoria.BEVANDA).size());
		assertEquals(5, menu.getGeneriPerCategoria(Categoria.PIATTO).size());
		assertEquals(1, menu.getGeneriPerCategoria(Categoria.CONTORNO).size());
		assertEquals(4, menu.getGeneriPerCategoria(Categoria.DESSERT).size());
		assertEquals(2, menu.getGeneriPerCategoria(Categoria.CONDIMENTO).size());
		
		assertEquals(0, menu.getProdottiPerCategoriaEGenere(Categoria.BEVANDA, "Succo").size());
		assertEquals(2, menu.getProdottiPerCategoriaEGenere(Categoria.BEVANDA, "Cola").size());
		assertEquals(2, menu.getProdottiPerCategoriaEGenere(Categoria.BEVANDA, "Cola0").size());
		assertEquals(1, menu.getProdottiPerCategoriaEGenere(Categoria.BEVANDA, "Acqua").size());
		assertEquals(0, menu.getProdottiPerCategoriaEGenere(Categoria.PIATTO, "Panino").size());
		assertEquals(7, menu.getProdottiPerCategoriaEGenere(Categoria.PIATTO, "Burger").size());
		assertEquals(2, menu.getProdottiPerCategoriaEGenere(Categoria.PIATTO, "Wrap").size());
		assertEquals(4, menu.getProdottiPerCategoriaEGenere(Categoria.PIATTO, "Fritti").size());
		assertEquals(3, menu.getProdottiPerCategoriaEGenere(Categoria.PIATTO, "Insalata").size());
		assertEquals(3, menu.getProdottiPerCategoriaEGenere(Categoria.CONTORNO, "Patatine").size());
		assertEquals(0, menu.getProdottiPerCategoriaEGenere(Categoria.CONTORNO, "Grissini").size());
		assertEquals(4, menu.getProdottiPerCategoriaEGenere(Categoria.DESSERT, "Gelato").size());
		assertEquals(0, menu.getProdottiPerCategoriaEGenere(Categoria.DESSERT, "Ciambella").size());
		assertEquals(2, menu.getProdottiPerCategoriaEGenere(Categoria.DESSERT, "Donut").size());
		assertEquals(1, menu.getProdottiPerCategoriaEGenere(Categoria.DESSERT, "Pancake").size());
		assertEquals(2, menu.getProdottiPerCategoriaEGenere(Categoria.DESSERT, "Muffin").size());
		assertEquals(5, menu.getProdottiPerCategoriaEGenere(Categoria.CONDIMENTO, "Bustina").size());
		
		assertEquals(0, menu.getProdottiPerCategoriaEGenere(Categoria.BEVANDA, "Bottiglietta").size());
		assertEquals(0, menu.getProdottiPerCategoriaEGenere(Categoria.PIATTO, "Primo").size());
		assertEquals(0, menu.getProdottiPerCategoriaEGenere(Categoria.DESSERT, "Torta").size());
		assertEquals(0, menu.getProdottiPerCategoriaEGenere(Categoria.CONDIMENTO, "Verdure").size());
	}
	
	@Test
	void testKO_WrongCat() throws IOException, BadFileFormatException {
		String fakefile = 
				    "Cibo	Burger	Basic		4,90\u00A0€\r\n"
				  + "Piatto	Burger	Formaggio	7,20\u00A0€\r\n"
				  + "Piatto	Burger	Bacon		7,50\u00A0€\r\n"
				  + "Piatto	Burger	Pollo		6,90\u00A0€\r\n"
				  + "Piatto	Burger	Pesce		4,20\u00A0€\r\n";
		assertThrows(BadFileFormatException.class, () -> new MyMenuReader().leggiProdotti(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_WrongPriceNeg() throws IOException, BadFileFormatException {
		String fakefile = 
				    "Piatto	Burger	Basic		-4,90\u00A0€\r\n"
				  + "Piatto	Burger	Formaggio	7,20\u00A0€\r\n"
				  + "Piatto	Burger	Bacon		7,50\u00A0€\r\n"
				  + "Piatto	Burger	Pollo		6,90\u00A0€\r\n"
				  + "Piatto	Burger	Pesce		4,20\u00A0€\r\n";
		assertThrows(BadFileFormatException.class, () -> new MyMenuReader().leggiProdotti(new StringReader(fakefile)));
	}	

	
	@Test
	void testKO_WrongPriceNoEuro() throws IOException, BadFileFormatException {
		String fakefile = 
				    "Piatto	Burger	Basic		1,00\u00A0$\r\n"
				  + "Piatto	Burger	Formaggio	7,20\u00A0€\r\n"
				  + "Piatto	Burger	Bacon		7,50\u00A0€\r\n"
				  + "Piatto	Burger	Pollo		6,90\u00A0€\r\n"
				  + "Piatto	Burger	Pesce		4,20\u00A0€\r\n";
		assertThrows(BadFileFormatException.class, () -> new MyMenuReader().leggiProdotti(new StringReader(fakefile)));
	}	

	
	@Test
	void testKO_WrongPriceNoSeparatorEuro() throws IOException, BadFileFormatException {
		String fakefile = 
				    "Piatto	Burger	Basic		4,90€\r\n"
				  + "Piatto	Burger	Formaggio	7,20\u00A0€\r\n"
				  + "Piatto	Burger	Bacon		7,50\u00A0€\r\n"
				  + "Piatto	Burger	Pollo		6,90\u00A0€\r\n"
				  + "Piatto	Burger	Pesce		4,20\u00A0€\r\n";
		assertThrows(BadFileFormatException.class, () -> new MyMenuReader().leggiProdotti(new StringReader(fakefile)));
	}	

	
	@Test
	void testKO_WrongPriceWrongSeparatorEuro() throws IOException, BadFileFormatException {
		String fakefile = 
				    "Piatto	Burger	Basic		4,90 €\r\n"
				  + "Piatto	Burger	Formaggio	7,20\u00A0€\r\n"
				  + "Piatto	Burger	Bacon		7,50\u00A0€\r\n"
				  + "Piatto	Burger	Pollo		6,90\u00A0€\r\n"
				  + "Piatto	Burger	Pesce		4,20\u00A0€\r\n";
		assertThrows(BadFileFormatException.class, () -> new MyMenuReader().leggiProdotti(new StringReader(fakefile)));
	}	

	@Test
	void testKO_WrongNumberOfItemsInLine1() throws IOException, BadFileFormatException {
		String fakefile = 
				    "Piatto	Basic		4,90\u00A0€\r\n"
				  + "Piatto	Burger	Formaggio	7,20\u00A0€\r\n"
				  + "Piatto	Burger	Bacon		7,50\u00A0€\r\n"
				  + "Piatto	Burger	Pollo		6,90\u00A0€\r\n"
				  + "Piatto	Burger	Pesce		4,20\u00A0€\r\n";
		assertThrows(BadFileFormatException.class, () -> new MyMenuReader().leggiProdotti(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_WrongNumberOfItemsInLine2() throws IOException, BadFileFormatException {
		String fakefile = 
				    "Piatto	Burger	Basic		4,90\u00A0€\r\n"
				  + "Piatto	Burger	7,20\u00A0€\r\n"
				  + "Piatto	Burger	Bacon		7,50\u00A0€\r\n"
				  + "Piatto	Burger	Pollo		6,90\u00A0€\r\n"
				  + "Piatto	Burger	Pesce		4,20\u00A0€\r\n";
		assertThrows(BadFileFormatException.class, () -> new MyMenuReader().leggiProdotti(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_WrongNumberOfItemsInLine2Price() throws IOException, BadFileFormatException {
		String fakefile = 
				    "Piatto	Burger	Basic		4,90\u00A0€\r\n"
				  + "Piatto	Burger	Formaggio	\u00A0€\r\n"
				  + "Piatto	Burger	Bacon		7,50\u00A0€\r\n"
				  + "Piatto	Burger	Pollo		6,90\u00A0€\r\n"
				  + "Piatto	Burger	Pesce		4,20\u00A0€\r\n";
		assertThrows(BadFileFormatException.class, () -> new MyMenuReader().leggiProdotti(new StringReader(fakefile)));
	}

	@Test
	void testKO_MissingNewlineInLine3() throws IOException, BadFileFormatException {
		String fakefile = 
				    "Piatto	Burger	Basic		4,90\u00A0€\r\n"
				  + "Piatto	Burger	Formaggio	5,60\u00A0€\r\n"
				  + "Piatto	Burger	Bacon		7,50\u00A0€"
				  + "Piatto	Burger	Pollo		6,90\u00A0€\r\n"
				  + "Piatto	Burger	Pesce		4,20\u00A0€\r\n";
		assertThrows(BadFileFormatException.class, () -> new MyMenuReader().leggiProdotti(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_WrongPriceInLine3() throws IOException, BadFileFormatException {
		String fakefile = 
				    "Piatto	Burger	Basic		4,90\u00A0€\r\n"
				  + "Piatto	Burger	Formaggio	5,60\u00A0€\r\n"
				  + "Piatto	Burger	Bacon		7.50\u00A0€\r\n"
				  + "Piatto	Burger	Pollo		6,90\u00A0€\r\n"
				  + "Piatto	Burger	Pesce		4,20\u00A0€\r\n";
		assertThrows(BadFileFormatException.class, () -> new MyMenuReader().leggiProdotti(new StringReader(fakefile)));
	}
	
	@Test
	void testKO_WrongPriceInLine4() throws IOException, BadFileFormatException {
		String fakefile = 
				    "Piatto	Burger	Basic		4,90\u00A0€\r\n"
				  + "Piatto	Burger	Formaggio	5,60\u00A0€\r\n"
				  + "Piatto	Burger	Bacon		7,50\u00A0€\r\n"
				  + "Piatto	Burger	Pollo		a,90\u00A0€\r\n"
				  + "Piatto	Burger	Pesce		4,20\u00A0€\r\n";
		assertThrows(BadFileFormatException.class, () -> new MyMenuReader().leggiProdotti(new StringReader(fakefile)));
	}

}
