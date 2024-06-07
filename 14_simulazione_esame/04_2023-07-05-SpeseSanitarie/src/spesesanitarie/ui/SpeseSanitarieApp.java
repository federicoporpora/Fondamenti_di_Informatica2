package spesesanitarie.ui;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import spesesanitarie.controller.Controller;
import spesesanitarie.model.DocumentoDiSpesa;
import spesesanitarie.persistence.MySpeseReader;
import spesesanitarie.persistence.SpeseReader;

public class SpeseSanitarieApp extends Application {

	@Override
	public void start(Stage stage) {
		stage.setTitle("Spese sanitarie");

		SpeseReader speseReader = new MySpeseReader();	
		List<DocumentoDiSpesa> spese = null;
		try {
			spese = speseReader.leggiSpese(new FileReader("spesesanitarie.txt"));
		} catch (IOException e) {
			Controller.alert("Errore di lettura o formato del file errato", "Impossibile leggere i dati",
					"Dettagli:\n" + e.getMessage());
		}

		var controller = new Controller(spese);
		MainPane mainPanel = new MainPane(controller);
		//
		Scene scene = new Scene(mainPanel, 720, 500, Color.AQUAMARINE);stage.setScene(scene);stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
