package pacchi.ui;

import java.util.Set;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pacchi.controller.Controller;
import pacchi.model.Valore;
import pacchi.model.Territorio;


public class GiocoPacchiAppMock extends Application {

	@Override
	public void start(Stage stage) {
		stage.setTitle("Gioco dei Pacchi - MOCK");
		//
		Set<Valore> premi = Set.of(
				new Valore(400),new Valore(40),new Valore(4),new Valore(600),new Valore(800),
				new Valore(400000),new Valore(200000),new Valore(40000),new Valore(20000), new Valore(5000)
				);
		Set<Territorio> territori = Set.of(
				new Territorio("Parma"), new Territorio("Piacenza"), new Territorio("Reggio Emilia"), new Territorio("Modena"), new Territorio("Bologna"),
				new Territorio("Ferrara"), new Territorio("Rimini"), new Territorio("Forlì"), new Territorio("Cesena"), new Territorio("Ravenna")
				);
		//
		var controller = new Controller(territori, premi);
		MainPane mainPanel = new MainPane(controller);
		//
		Scene scene = new Scene(mainPanel, 720, 500, Color.AQUAMARINE);
		stage.setScene(scene);stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	

}
