package pacchi.ui;

import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pacchi.controller.Controller;
import pacchi.model.Valore;
import pacchi.model.Territorio;
import pacchi.persistence.BadFileFormatException;
import pacchi.persistence.MyConfigReader;


public class GiocoPacchiApp extends Application {

	@Override
	public void start(Stage stage) {
		stage.setTitle("L'eterno Gioco dei Pacchi");

		MyConfigReader configReader = new MyConfigReader();	
		
		Set<Territorio> territori = null;
		Set<Valore> premi = null;
		try {
			territori = configReader.leggiTerritori(new FileReader("Territori.txt"));
			premi = configReader.leggiPremi(new FileReader("Premi.txt"));
		} catch (BadFileFormatException | IOException e) {
			Controller.alert("Errore di lettura o formato del file errato", "Impossibile leggere i dati",
					"Dettagli:\n" + e.getMessage());
		}

		var controller = new Controller(territori, premi);
		MainPane mainPanel = new MainPane(controller);
		//
		Scene scene = new Scene(mainPanel, 700, 470, Color.AQUAMARINE);
		stage.setScene(scene);stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
