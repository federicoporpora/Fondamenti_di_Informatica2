package cityparking.ui;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import cityparking.controller.Controller;
import cityparking.controller.MyController;
import cityparking.model.Tariffa;
import cityparking.persistence.BadFileFormatException;
import cityparking.persistence.MyTariffaReader;
import cityparking.persistence.TariffaReader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CityParkingApp extends Application {

	private Controller controller;

	public void start(Stage stage) {
		controller = createController();
		if (controller == null) {
			Controller.alert("Errore irrecuperabile", "Errore nella creazione del controller", "Addio");
			System.exit(1);
		}
		stage.setTitle("City Parking");
		CityParkingPane root = new CityParkingPane(controller);
		
		Scene scene = new Scene(root, 350, 250, Color.ALICEBLUE);
		stage.setScene(scene);
		stage.show();
	}

	protected Controller createController() {
		TariffaReader tariffeReader = null;
		Tariffa tariffa = null;
		try {
			tariffeReader = new MyTariffaReader();
			tariffa = tariffeReader.leggiTariffa(new FileReader("Tariffa.txt"));
		} catch (FileNotFoundException e) {
			Controller.alert("Errore di I/O", "Errore nell'apertura del file tariffe", e.getMessage());
			return null;
		}
		catch (IOException e) {
			Controller.alert("Errore di I/O", "Errore durante la lettura del file tariffe", e.getMessage());
			return null;
		}
		catch (BadFileFormatException e) {
			Controller.alert("Errore di formato", "Formato errato nelle tariffe", e.getMessage());
			return null;
		}
		return new MyController(tariffa);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
