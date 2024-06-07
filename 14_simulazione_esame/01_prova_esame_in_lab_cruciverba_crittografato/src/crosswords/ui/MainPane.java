package crosswords.ui;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import crosswords.controller.Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class MainPane extends BorderPane {

	private TextArea output;
	private Controller controller;
	private ComboBox<Integer> numbers;
	private ComboBox<String> letters;
	private Button button;
	
	
	public MainPane(Controller controller, Stage stage) {
		this.controller=controller;
		HBox topPane = new HBox();
		this.setTop(topPane);

		output = new TextArea();
		output.setPrefColumnCount(12);
		output.setPrefRowCount(12);
		output.setFont(Font.font("Courier New", FontWeight.NORMAL, 12));

		output.setEditable(false);
		updateGrid();
		this.setCenter(output);

		HBox bottomPane = new HBox();
		numbers= new ComboBox<Integer>();
		letters= new ComboBox<String>();
		populateComboNumbers(numbers);
		populateComboLetters(letters);
		button = new Button("Imposta");
		this.setBottom(bottomPane);
		
		
		bottomPane.getChildren().add(numbers);
		bottomPane.getChildren().add(letters);
		bottomPane.getChildren().add(button);
		button.setOnAction(this::updateMapping);
	}
	
	private void populateComboNumbers(ComboBox<Integer> combo) {
		var interi = new LinkedList<Integer>();
		for (int i = 0; i <= 26; i++) {
			interi.add(i);
		}
		combo.setItems(FXCollections.observableArrayList(interi));
	}
	
	private void populateComboLetters(ComboBox<String> combo) {
		var lettere = new LinkedList<String>();
		for (char c = 'A'; c <= 'Z'; c++) {
			lettere.add(String.valueOf(c));
		}
		combo.setItems(FXCollections.observableArrayList(lettere));
	}
	
	private void updateGrid() {
		output.setText(controller.getGame().toString());
	}
	
	private void updateMapping(ActionEvent event) {
		this.controller.updateMapping(this.numbers.getValue(), this.letters.getValue());
		updateGrid();
	}
	
}