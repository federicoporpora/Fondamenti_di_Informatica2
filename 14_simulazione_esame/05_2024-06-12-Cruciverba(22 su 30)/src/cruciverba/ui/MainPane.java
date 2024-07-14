package cruciverba.ui;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringJoiner;

import cruciverba.controller.Controller;
import cruciverba.persistence.BadFileFormatException;


public class MainPane extends BorderPane {

	private TextArea areaSchemaBase, areaSchemaNumerato, areaHorVert;
	private Controller controller;
	private Button buttonCarica, buttonNumera, buttonHor, buttonVert;

	
	public MainPane(Controller controller, Stage stage) {
		this.controller=controller;
		
		VBox rightBox = new VBox();
			areaSchemaBase = new TextArea();
			areaSchemaBase.setPrefWidth(500);
			areaSchemaBase.setPrefHeight(200);
			areaSchemaBase.setFont(Font.font("Courier New", FontWeight.NORMAL, 12));
			areaSchemaBase.setEditable(false);
			rightBox.getChildren().addAll(new Label("Cruciverba da numerare:"), areaSchemaBase);
			areaSchemaNumerato = new TextArea();
			areaSchemaNumerato.setPrefWidth(500);
			areaSchemaNumerato.setPrefHeight(200);
			areaSchemaNumerato.setFont(Font.font("Courier New", FontWeight.NORMAL, 12));
			areaSchemaNumerato.setEditable(false);
			rightBox.getChildren().addAll(new Label("Cruciverba numerato:"), areaSchemaNumerato);
		this.setRight(rightBox);
		
		VBox leftBox = new VBox();
			buttonCarica = new Button("Carica schema");
			buttonCarica.setPrefWidth(200);
			buttonCarica.setOnAction(ev -> caricaSchema(stage));
			leftBox.getChildren().add(buttonCarica);
			
			buttonNumera = new Button("Numera schema");
			buttonNumera.setPrefWidth(200);
			buttonNumera.setOnAction(this::numeraSchema);
			leftBox.getChildren().add(buttonNumera);
			
			buttonHor = new Button("Orizzontali");
			buttonHor.setPrefWidth(200);
			buttonHor.setOnAction(this::orizzontali);
			leftBox.getChildren().add(buttonHor);
			
			buttonVert = new Button("Verticali");
			buttonVert.setPrefWidth(200);
			buttonVert.setOnAction(this::verticali);
			leftBox.getChildren().add(buttonVert);
			
			areaHorVert = new TextArea();
			areaHorVert.setPrefWidth(200);
			areaHorVert.setPrefHeight(300);
			areaHorVert.setFont(Font.font("Courier New", FontWeight.NORMAL, 12));
			areaHorVert.setEditable(false);
			leftBox.getChildren().add(areaHorVert);
		this.setLeft(leftBox);
	}
	
	void caricaSchema(Stage stage) {
		try {
			controller.leggiSchema();
			areaSchemaBase.setText(controller.getCruciverba().toString());
		} catch (IllegalArgumentException e) {
			Controller.alert("ERRORE", e.getMessage(), "IllegalArgumentException in controller.getCruciverba().toString()");
		} catch (BadFileFormatException e) {
			Controller.alert("ERRORE", e.getMessage(), "BadFileFormatException in controller.leggiSchema()");
		} catch (IOException e) {
			Controller.alert("ERRORE", e.getMessage(), "IOException in controller.leggiSchema()");
		}
	}
	
	void numeraSchema(ActionEvent ev) {
		try {
			areaSchemaNumerato.setText(controller.schemaNumerato());
		} catch (IllegalArgumentException e) {
			Controller.alert("ERRORE", e.getMessage(), "IllegalArgumentException in controller.schemaNumerato()");
		} catch (UnsupportedOperationException e) {
			Controller.alert("ERRORE", "Numerazione non ancora attiva", "Non è stato caricato alcun cruciverba");
		}
	}
	
	void orizzontali(ActionEvent ev) {
		try {
			areaHorVert.setText(formattaElenco("ORIZZONTALI", controller.orizzontali()));
		} catch (UnsupportedOperationException e) {
			Controller.alert("ERRORE", "Numerazione non ancora attiva", "Non è stato caricato alcun cruciverba");
		}
	}

	void verticali(ActionEvent ev) {
		try {
			areaHorVert.setText(formattaElenco("VERTICALI", controller.verticali()));
		} catch (UnsupportedOperationException e) {
			Controller.alert("ERRORE", "Numerazione non ancora attiva", "Non è stato caricato alcun cruciverba");
		}
	}
	
	private String formattaElenco(String titolo, Map<Integer,String> defs) {
		StringJoiner joiner = new StringJoiner(System.lineSeparator(), titolo, "");
		for(Entry<Integer,String> entry : defs.entrySet()) {
			joiner.add(entry.getKey() + " - " + entry.getValue());
		}
		return joiner.toString();
	}
}
