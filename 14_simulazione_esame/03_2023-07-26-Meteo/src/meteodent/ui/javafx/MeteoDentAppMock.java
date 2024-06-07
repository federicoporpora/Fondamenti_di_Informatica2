package meteodent.ui.javafx;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;
import java.util.Set;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import meteodent.controller.Controller;
import meteodent.model.Previsione;
import meteodent.model.ProbPioggia;
import meteodent.model.Temperatura;

public class MeteoDentAppMock extends Application {

	Map<String, SortedSet<Previsione>> previsioniMap = Map.of(
			"Giove", Set.of(
					new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(10,25), Temperatura.of(21), ProbPioggia.of(25)),
					new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(12,25), Temperatura.of(24), ProbPioggia.of(25)),
					new Previsione("Giove", LocalDate.of(2023,10,11), LocalTime.of(16,51), Temperatura.of(22), ProbPioggia.of(71))
					).stream().collect(Collectors.toCollection(() -> new TreeSet<>((Comparator.comparing(Previsione::getDataOra))))),
							
			"Marte", Set.of(
					new Previsione("Marte", LocalDate.of(2023,10,12), LocalTime.of(10,00), Temperatura.of(55), ProbPioggia.of(5)),
					new Previsione("Marte", LocalDate.of(2023,10,12), LocalTime.of(12,00), Temperatura.of(57), ProbPioggia.of(5)),
					new Previsione("Marte", LocalDate.of(2023,10,12), LocalTime.of(15,30), Temperatura.of(54), ProbPioggia.of(7))
					).stream().collect(Collectors.toCollection(() -> new TreeSet<>((Comparator.comparing(Previsione::getDataOra))))),
							
			"Luna", Set.of(
					new Previsione("Luna", LocalDate.of(2023,10,13), LocalTime.of(10,32), Temperatura.of(41), ProbPioggia.of(2)),
					new Previsione("Luna", LocalDate.of(2023,10,13), LocalTime.of(12,32), Temperatura.of(44), ProbPioggia.of(1)),
					new Previsione("Luna", LocalDate.of(2023,10,13), LocalTime.of(16,32), Temperatura.of(42), ProbPioggia.of(3)),
					new Previsione("Luna", LocalDate.of(2023,10,14), LocalTime.of(11,0), Temperatura.of(41), ProbPioggia.of(3)),
					new Previsione("Luna", LocalDate.of(2023,10,14), LocalTime.of(13,0), Temperatura.of(44), ProbPioggia.of(4)),
					new Previsione("Luna", LocalDate.of(2023,10,14), LocalTime.of(17,0), Temperatura.of(42), ProbPioggia.of(1))
					).stream().collect(Collectors.toCollection(() -> new TreeSet<>((Comparator.comparing(Previsione::getDataOra)))))
			);
	
	@Override
	public void start(Stage stage) {
		stage.setTitle("Previsioni Meteo - MOCK");
		//
		var controller = new Controller(previsioniMap);
		MainPane mainPanel = new MainPane(controller);
		//
		Scene scene = new Scene(mainPanel, 720, 500, Color.AQUAMARINE);
		stage.setScene(scene);stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	

}
