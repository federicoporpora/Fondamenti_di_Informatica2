package trainstats.ui;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import trainstats.controller.Controller;
import trainstats.model.Train;
import trainstats.model.AverageStatProvider;
import trainstats.model.FinalDestinationStatProvider;
import trainstats.model.StatProvider;
import trainstats.model.ThresholdStatProvider;
import trainstats.persistence.TrainReader;
import trainstats.persistence.MyTrainReader;


public class TrainStatsApp extends Application {

	public static String[] listing(File dir, String extension) {
		if (dir.isDirectory()) { 
			return dir.list((f,name) -> name.endsWith(extension));
		}
		throw new IllegalArgumentException(dir + " is not a directory");
	}
	
	List<StatProvider> providers = List.of(
			new FinalDestinationStatProvider(),
			new AverageStatProvider(),
			new ThresholdStatProvider(5)
			);
	
	@Override
	public void start(Stage stage) {
		stage.setTitle("Train Statistics");
		
		TrainReader trainReader = new MyTrainReader();
		String[] filenames = listing(new File("."), ".txt");
		
		Map<String,Train> trainMap =
				Stream.of(filenames).collect(Collectors.toMap(
						filename -> filename.substring(0,filename.indexOf('.')), 
						filename -> {
							try {
								return trainReader.readTrain(new FileReader(filename));
							} catch (IOException | IllegalArgumentException e) {
								Controller.alert(
										"Errore di lettura o formato del file errato",
										"Impossibile leggere i dati",
										"Dettagli: " + e.getMessage());
								return null;
							}
						}
				));
		Controller controller = new Controller(new TreeMap<>(trainMap), providers);
		MainPane mainPanel = new MainPane(controller);
		//
		Scene scene = new Scene(mainPanel, 720, 500, Color.AQUAMARINE);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
