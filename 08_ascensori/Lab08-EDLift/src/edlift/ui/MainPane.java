package edlift.ui;

import java.io.PrintWriter;
import java.util.List;

import edlift.controller.Controller;
import edlift.model.Installation;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
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

public class MainPane extends BorderPane {

	private ComboBox<Installation> combo;
	private Button startStopSimulation;
	private Controller controller;
	private TextArea outputArea;
	private ButtonPane buttonPane;

	public MainPane(Controller controller, Stage stage) {
		this.controller = controller;
		HBox topPane = new HBox();
		this.setTop(topPane);
		Insets insets = new Insets(20);
		//
		VBox leftPane = new VBox();
		combo = new ComboBox<>();
		List<Installation> installations = controller.getInstallations();
		combo.setItems(FXCollections.observableArrayList(installations));
		if (installations.size() > 0)
			combo.setValue(installations.get(0));
		combo.setOnAction(this::myViewUpdater);
		//
		leftPane.getChildren().add(new Label("Please choose a lift"));
		leftPane.getChildren().add(combo);
		VBox.setMargin(combo, insets);
		//
		HBox timerBox = new HBox();
		startStopSimulation = new Button("Start Simulation");
		startStopSimulation.setFont(Font.font("Verdana", FontWeight.NORMAL, 12));
		startStopSimulation.setOnAction(this::mySimulationHandler);
		HBox.setMargin(startStopSimulation, insets);
		timerBox.getChildren().add(startStopSimulation);
		leftPane.getChildren().add(timerBox);
		//
		setLeft(leftPane);
		//
		insets = new Insets(10);
		setMargin(leftPane, insets);
		outputArea = new TextArea();
		outputArea.setFont(Font.font("Verdana", FontWeight.NORMAL, 12));
		outputArea.setStyle("-fx-text-fill: black ;");
		VBox.setMargin(outputArea, insets);
		this.setCenter(outputArea);
		controller.setLogger(outputArea, new PrintWriter(System.out, true));
		//
		buttonPane = new ButtonPane(controller.getLift().getMinFloor(), controller.getLift().getMaxFloor());
		buttonPane.setOnAction(this::myButtonHandler);
		this.setBottom(buttonPane);
		outputArea.setText("");
	}

	private void myViewUpdater(ActionEvent e) {
		controller.setLift(combo.getValue().getLift());
		buttonPane = new ButtonPane(controller.getLift().getMinFloor(), controller.getLift().getMaxFloor());
		buttonPane.setOnAction(this::myButtonHandler);
		this.setBottom(buttonPane);
		outputArea.setText("");
		controller.stopSimulation();
		startStopSimulation.setText("Start Simulation");
	}

	private void mySimulationHandler(ActionEvent e) {
		if (startStopSimulation.getText().equals("Start Simulation")) {
			controller.startSimulation();
			startStopSimulation.setText("Stop Simulation");
		} else if (startStopSimulation.getText().equals("Stop Simulation")) {
			controller.stopSimulation();
			startStopSimulation.setText("Start Simulation");
		}
	}

	private void myButtonHandler(ActionEvent e) {
		// APPLICATION LOGIC HERE
		String buttonLabel = ((Button) e.getSource()).getText();
		int callingFloor = Integer.parseInt(buttonLabel);
		controller.goToFloor(callingFloor);
	}

}
