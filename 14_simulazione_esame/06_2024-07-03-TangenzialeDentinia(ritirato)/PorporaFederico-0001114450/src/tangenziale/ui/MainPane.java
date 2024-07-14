package tangenziale.ui;

import java.text.NumberFormat;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import tangenziale.controller.Controller;


public class MainPane extends BorderPane {

	private TextArea outputArea;
	private Controller controller;
	private ComboBox<String> comboEntrata, comboUscita;
	private Button calcola;
	private NumberFormat formatter;
	
	public MainPane(Controller controller) {
		this.controller = controller;
		
		this.formatter = NumberFormat.getCurrencyInstance(Locale.ITALY);

		HBox topPane = new HBox();
			//
			// ***** DA FARE ****
			//
			// Popolamento iniziale delle due combo comboEntrata, comboUscita con l'elenco dei caselli
			//
		topPane.getChildren().addAll(
				new Label("Entrata "), comboEntrata, new Label("   "),
				new Label("Uscita "), comboUscita 
				);
		this.setTop(topPane);
		
		HBox centerPane = new HBox();
			centerPane.setAlignment(Pos.CENTER);
			calcola = new Button("Calcola percorso");
			//
			// ***** DA FARE ****
			// Aggancio del pulsante al gestore degli eventi (metodo privato calcolaPercorso)
			//
			centerPane.getChildren().add(calcola);
		this.setCenter(centerPane);

		VBox bottomPane = new VBox();
			outputArea = new TextArea();
			outputArea.setEditable(false);
			outputArea.setPrefWidth(450);
			outputArea.setPrefHeight(150);
			outputArea.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
			outputArea.setText("");
			bottomPane.getChildren().addAll(
					new Label("Percorso e costi"),
					outputArea);
		this.setBottom(bottomPane);
	}

	private void calcolaPercorso(ActionEvent event) {
		//  Deve innanzitutto verificare che entrambi i caselli di entrata e uscita siano selezionati: 
		//  se così non è, deve emettere il messaggio d’errore sopra descritto e terminare senza fare nulla;  
		//  se  invece tutto è regolare, deve far calcolare il percorso al Controller ed emettere sull’area di testo 
		//  il messaggio con il risultato, formattato come nelle figure, completo dell’indicazione di costo 
		//  *formattata secondo lo standard italiano*
	}

}
