package dentburger.ui;

import java.util.stream.Stream;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import dentburger.controller.Controller;
import dentburger.model.Prodotto;
import dentburger.model.Categoria;
import dentburger.model.Menu;


public class DentBurgerAppMock extends Application {

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
			new Prodotto(Categoria.CONDIMENTO, "Sale", "bustina", 0.15),
			new Prodotto(Categoria.CONDIMENTO, "Pepe", "bustina", 0.15),
			new Prodotto(Categoria.CONDIMENTO, "Maionese", "bustina", 0.45),
			new Prodotto(Categoria.CONDIMENTO, "Ketchup", "bustina", 0.40)
	};
		
	@Override
	public void start(Stage stage) {
		stage.setTitle("DentBurger - MOCK");
		//
		var menu = new Menu();
		Stream.of(prodotti).forEach(menu::add);
		var controller = new Controller(menu);
		MainPane mainPanel = new MainPane(controller);
		//
		Scene scene = new Scene(mainPanel, 720, 500, Color.AQUAMARINE);
		stage.setScene(scene);stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	

}
