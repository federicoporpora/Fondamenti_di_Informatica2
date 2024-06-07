package spesesanitarie.ui;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import spesesanitarie.controller.Controller;
import spesesanitarie.model.DocumentoDiSpesa;
import spesesanitarie.persistence.MySpeseReader;
import spesesanitarie.persistence.SpeseReader;

public class SpeseSanitarieAppMock extends Application {

	String fakefile =
			  "07/10/2022;Struttura privata;1;€ 130,00\n"
			+ "LP;€ 130,00\n"
			+ "21/10/2022;Medico;1;€ 100,00\n"
			+ "LP;€ 100,00\n"
			+ "24/10/2022;Farmacia;5;€ 28,05\n"
			+ "TK;€ 5,11\n"
			+ "TK;€ 5,00\n"
			+ "FC;€ 10,71\n"
			+ "AS;€ 4,23\n"
			+ "TK;€ 3,00\n"
			+ "25/10/2022;Farmacia;2;€ 14,85\n"
			+ "FC;€ 7,83\n"
			+ "AS;€ 7,02\n"
			+ "03/11/2022;Farmacia;1;€ 9,67\n"
			+ "FC;€ 9,67\n"
			+ "08/11/2022;Farmacia;3;€ 23,97\n"
			+ "DM;€ 2,52\n"
			+ "AS;€ 12,90\n"
			+ "FC;€ 8,55\n"
			+ "22/11/2022;AUSL;1;€ 72,00\n"
			+ "LP;€ 72,00\n"
			+ "23/11/2022;Farmacia;3;€ 9,78\n"
			+ "TK;€ 3,00\n"
			+ "TK;€ 2,61\n"
			+ "FC;€ 4,17\n"
			+ "09/12/2022;Farmacia;1;€ 26,50\n"
			+ "AS;€ 26,50\n"
			+ "15/12/2022;Struttura privata;1;€ 21,00\n"
			+ "TK;€ 21,00\n"
			+ "21/12/2022;Farmacia;2;€ 5,61\n"
			+ "TK;€ 2,61\n"
			+ "TK;€ 3,00\n"
			+ "23/12/2022;Farmacia;2;€ 27,81\n"
			+ "FC;€ 6,39\n"
			+ "FC;€ 21,42\n"
			+ "27/12/2022;Farmacia;1;€ 1,80\n"
			+ "DM;€ 1,80\n"
			+ "";
	
	@Override
	public void start(Stage stage) {
		stage.setTitle("Spese Sanitarie - MOCK");

		SpeseReader speseReader = new MySpeseReader();	
		List<DocumentoDiSpesa> spese = null;
		try {
			spese = speseReader.leggiSpese(new StringReader(fakefile));
		} catch (IOException e) {
			Controller.alert("Errore di lettura o formato del file errato", "Impossibile leggere i dati",
					"Dettagli: " + e.getMessage());
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
