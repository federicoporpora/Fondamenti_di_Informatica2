package meteodent.ui.javafx;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.SortedSet;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import meteodent.controller.Controller;
import meteodent.model.Previsione;
import meteodent.persistence.BadFileFormatException;
import meteodent.persistence.MyPrevisioniReader;

public class MeteoDentApp extends Application {

	@Override
	public void start(Stage stage) {
		stage.setTitle("Previsioni Meteo");

		MyPrevisioniReader prevReader = new MyPrevisioniReader();	
		
		Map<String, SortedSet<Previsione>> previsioni = null;
		try {
			previsioni = prevReader.leggiPrevisioni(new FileReader("Previsioni.txt"));
		} catch (BadFileFormatException | IOException e) {
			Controller.alert("Errore di lettura o formato del file errato", "Impossibile leggere i dati",
					"Dettagli:\n" + e.getMessage());
		}

		var controller = new Controller(previsioni);
		MainPane mainPanel = new MainPane(controller);
		//
		Scene scene = new Scene(mainPanel, 750, 350, Color.AQUAMARINE);
		stage.setScene(scene);stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
