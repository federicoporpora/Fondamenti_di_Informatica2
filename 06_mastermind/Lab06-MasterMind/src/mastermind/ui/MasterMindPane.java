package mastermind.ui;

import java.util.ArrayList;
import java.util.List;

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
import javafx.stage.Stage;
import mastermind.controller.Controller;
import mastermind.model.Combinazione;
import mastermind.model.PioloDiGioco;
import mastermind.model.Status;

public class MasterMindPane extends BorderPane {

	private TextArea output;
	private Button restart, inserisci, svela;
	private Controller controller;
	private Label rimasti, status;
	private List<ComboBox<PioloDiGioco>> bottoniCodice;
	
	public MasterMindPane(Controller controller, Stage stage) {
		this.controller=controller;
		//
		VBox topPane = new VBox();
		topPane.setAlignment(Pos.CENTER);
		bottoniCodice = new ArrayList<>();
		HBox buttonRow = new HBox();
		buttonRow.setAlignment(Pos.CENTER);
		for (int i=0; i< controller.dimensioneCodice(); i++) {
			ComboBox<PioloDiGioco> combo = new ComboBox<>(FXCollections.observableArrayList(PioloDiGioco.values()));
			combo.getItems().remove(0); // eliminiamo VUOTO dall'elenco
			combo.setOnAction(this::colorer);
			bottoniCodice.add(combo);
			buttonRow.getChildren().add(combo);
		}
		inserisci = new Button("Inserisci");
		topPane.getChildren().addAll(buttonRow,inserisci);
		inserisci.setOnAction(this::gioca);
		this.setTop(topPane);
		//
		output = new TextArea();
		output.setPrefColumnCount(65);
		output.setPrefRowCount(10);
		output.setFont(Font.font("Courier New", FontWeight.NORMAL, 12));
		this.setRight(output);
		//
		VBox leftPane = new VBox();
		leftPane.setAlignment(Pos.CENTER_LEFT);
		restart = new Button("Restart");
		leftPane.getChildren().add(restart);
		restart.setOnAction(this::restarter);
		svela = new Button("Svela");
		leftPane.getChildren().add(svela);
		svela.setOnAction(this::reveal);
		this.setLeft(leftPane);		
		//
		HBox bottomPane = new HBox();
		bottomPane.getChildren().addAll(new Label("Max tentativi: "+controller.maxTentativi() + "  "));
		rimasti = new Label("Tentativi rimasti: " + controller.tentativiRimasti() + "  ");
		status = new Label("Stato del gioco: " + controller.status());
		bottomPane.getChildren().addAll(rimasti, status);
		this.setBottom(bottomPane);
	}
	
	private void gioca(ActionEvent e) {
		Combinazione tentativo = new Combinazione(controller.dimensioneCodice());
		for (int i=0; i< controller.dimensioneCodice(); i++) {
			PioloDiGioco piolo = bottoniCodice.get(i).getValue();
			if (piolo==null) {
				Controller.alert("Errore", "Colore indefinito", "Uno dei colori non è stato ancora selezionato");
				return;
			}
			tentativo.setPiolo(i, piolo);
		}
		controller.tenta(tentativo);
		output.setText(controller.situazione());
		rimasti.setText("Tentativi rimasti: " + controller.tentativiRimasti() + "  ");
		status.setText("Stato del gioco: " + controller.status());
		if (!controller.status().equals(Status.IN_CORSO)) {
			output.appendText("\n\n" + "Gioco terminato");
			inserisci.setDisable(true);
		}
	}

	private void reveal(ActionEvent e) {
		output.appendText("\n\n" + "Combinazione segreta: " + controller.combinazioneSegreta());
	}
	
	private void restarter(ActionEvent e) {
		controller.restart();
		output.setText("");
		inserisci.setDisable(false);
		rimasti.setText("Tentativi rimasti: " + controller.tentativiRimasti() + "  ");
		status.setText("Stato del gioco: " + controller.status());
		for (ComboBox<PioloDiGioco> c : bottoniCodice) {
			c.getSelectionModel().clearSelection();
			c.setStyle("-fx-background-color: #C0C0C0;");
		}
	}

	private void colorer(ActionEvent e) {
		@SuppressWarnings("unchecked")
		ComboBox<PioloDiGioco> combo = (ComboBox<PioloDiGioco>)(e.getSource());
		if (combo.getValue()!=null) {
			combo.setStyle("-fx-background-color: " + combo.getValue().rgb() + ";");
		};
	}
	
}
