package dentburger.ui;

import java.io.FileReader;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import dentburger.controller.Controller;
import dentburger.model.Menu;
import dentburger.persistence.BadFileFormatException;
import dentburger.persistence.MyMenuReader;


public class DentBurgerApp extends Application {

	@Override
	public void start(Stage stage) {
		stage.setTitle("Dent Burger - The Ultimate Fast Food Ever!");

		MyMenuReader menuReader = new MyMenuReader();	
		
		Menu menu = null;
		try {
			menu = menuReader.leggiProdotti(new FileReader("DentBurgerList.txt"));
		} catch (BadFileFormatException | IOException e) {
			Controller.alert("Errore di lettura o formato del file errato", "Impossibile leggere i dati",
					"Dettagli:\n" + e.getMessage());
		}

		var controller = new Controller(menu);
		MainPane mainPanel = new MainPane(controller);
		//
		Scene scene = new Scene(mainPanel, 750, 350, Color.AQUAMARINE);
		stage.setScene(scene);stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
