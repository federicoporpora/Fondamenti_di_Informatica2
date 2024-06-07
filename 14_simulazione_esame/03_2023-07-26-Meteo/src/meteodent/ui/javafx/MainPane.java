package meteodent.ui.javafx;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import meteodent.controller.Controller;
import meteodent.model.TipoBollettino;


public class MainPane extends BorderPane {
	
	private Controller controller;
	
	private ComboBox<String> localitaCombo;
	private ComboBox<LocalDate> dateCombo;
	private TextArea outputArea;
	private Button buttonDettagliato, buttonSintetico;
	

	public MainPane(Controller controller) {
		this.controller=controller;
		//
		VBox leftBox = new VBox();
		leftBox.setPrefWidth(200);
			HBox miniBoxIniziale = new HBox(); miniBoxIniziale.setAlignment(Pos.CENTER);
			Label titolino = new Label("Meteo Dent");
			titolino.setStyle("-fx-font-weight: bold");
			miniBoxIniziale.getChildren().addAll(titolino);
			leftBox.getChildren().addAll(new Label("  "), miniBoxIniziale, new Label("  "));
			//
			localitaCombo = new ComboBox<>(FXCollections.observableList(controller.getListaLocalita()));
			localitaCombo.setTooltip(new Tooltip("Scegliere la località"));
			localitaCombo.setPrefWidth(175);
			localitaCombo.setValue(localitaCombo.getItems().get(0));
			leftBox.getChildren().addAll(new Label(" Località:  "), localitaCombo);
			//
			localitaCombo.setOnAction(event -> populateDateCombo());
			//
			//
			dateCombo = new ComboBox<>();
			populateDateCombo();
			dateCombo.setTooltip(new Tooltip("Scegliere la data desiderata"));
			dateCombo.setPrefWidth(175);
			dateCombo.setValue(dateCombo.getItems().get(0));
			leftBox.getChildren().addAll(new Label(" Data:  "), dateCombo);
			//
			buttonDettagliato = new Button("Bollettino dettagliato");
			buttonDettagliato.setOnAction(event -> showPrevisione(TipoBollettino.DETTAGLIATO));
			buttonSintetico = new Button("Bollettino sintetico");
			buttonSintetico.setOnAction(event -> showPrevisione(TipoBollettino.SINTETICO));
			leftBox.getChildren().addAll(buttonDettagliato,buttonSintetico);
			//
			this.setLeft(leftBox);
		VBox rightBox = new VBox();
			rightBox.setPrefWidth(580);
			outputArea = new TextArea();
			outputArea.setPrefSize(580,500);
			outputArea.setFont(Font.font("Courier New", FontWeight.NORMAL, 11));
			outputArea.setEditable(false);
			rightBox.getChildren().addAll(outputArea);
		this.setRight(rightBox);
	}

	private void populateDateCombo() {
		localitaCombo.setItems(FXCollections.observableArrayList(controller.getListaLocalita()));
		if (localitaCombo.getValue() == null || localitaCombo.getValue().isEmpty()) localitaCombo.setValue(FXCollections.observableArrayList(controller.getListaLocalita()).getFirst());
		dateCombo.setItems(FXCollections.observableArrayList(controller.getDatePerLocalita(localitaCombo.getValue())));
		if (dateCombo.getValue() == null) dateCombo.setValue(FXCollections.observableArrayList(controller.getDatePerLocalita(localitaCombo.getValue())).getFirst());
	}
	
	private void showPrevisione(TipoBollettino tipo) {
		outputArea.setText(controller.getBollettino(localitaCombo.getValue(), dateCombo.getValue(), tipo).getTesto());
	}
}
