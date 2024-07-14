package trainstats.ui;

import java.time.LocalTime;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import trainstats.controller.Controller;
import trainstats.model.Train;
import trainstats.model.AverageStatProvider;
import trainstats.model.FinalDestinationStatProvider;
import trainstats.model.Recording;
import trainstats.model.StatProvider;
import trainstats.model.ThresholdStatProvider;


public class TrainStatsAppMock extends Application {

	public static void alert(String title, String headerMessage, String contentMessage) {
		javafx.scene.control.Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}
	
	List<StatProvider> providers = List.of(
			new FinalDestinationStatProvider(),
			new AverageStatProvider(),
			new ThresholdStatProvider(5)
			);
	
	@Override
	public void start(Stage stage) {
		stage.setTitle("Train Statistics - MOCK");

		Controller controller = new Controller(creaTrenoFake(), providers);
		MainPane mainPanel = new MainPane(controller);
		//
		Scene scene = new Scene(mainPanel, 720, 500, Color.AQUAMARINE);
		stage.setScene(scene);
		stage.show();
	}
	
	private SortedMap<String,Train> creaTrenoFake() {
		List<Recording> recordings = new ArrayList<>();
		recordings.add(new Recording("Milano C.le", Optional.empty(), Optional.empty(), Optional.of(LocalTime.of(13,10)), Optional.of(LocalTime.of(13,21)) ));
		recordings.add(new Recording("Lodi",        Optional.of(LocalTime.of(13,45)), Optional.of(LocalTime.of(13,55)), Optional.of(LocalTime.of(13,47)), Optional.of(LocalTime.of(13,59)) ));
		recordings.add(new Recording("Piacenza",    Optional.of(LocalTime.of(14,12)), Optional.of(LocalTime.of(14,22)), Optional.of(LocalTime.of(14,14)), Optional.of(LocalTime.of(14,30)) ));
		recordings.add(new Recording("Fidenza",     Optional.of(LocalTime.of(14,35)), Optional.of(LocalTime.of(14,50)), Optional.of(LocalTime.of(14,38)), Optional.of(LocalTime.of(14,53)) ));
		recordings.add(new Recording("Parma",       Optional.of(LocalTime.of(14,58)), Optional.of(LocalTime.of(15,10)), Optional.of(LocalTime.of(15,01)), Optional.of(LocalTime.of(15,13)) ));
		recordings.add(new Recording("Reggio Emilia", Optional.of(LocalTime.of(15,18)), Optional.of(LocalTime.of(15,30)), Optional.of(LocalTime.of(15,20)), Optional.of(LocalTime.of(15,35)) ));
		recordings.add(new Recording("Modena",      Optional.of(LocalTime.of(15,36)), Optional.of(LocalTime.of(15,50)), Optional.of(LocalTime.of(15,38)), Optional.of(LocalTime.of(15,55)) ));
		recordings.add(new Recording("Bologna C.le",  Optional.of(LocalTime.of(16,10)), Optional.of(LocalTime.of(16,26)), Optional.empty(), Optional.empty() ));
		var train = new Train(recordings);
		return new TreeMap<>(Map.of("treno fake", train));
	}

	public static void main(String[] args) {
		launch(args);
	}
	

}
