package edlift.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

import edlift.controller.Controller;
import edlift.model.Lift;
import edlift.model.Installation;


public class EDLiftApp extends Application {

	private Controller controller;

	public void start(Stage stage) {
		List<Installation> edifici = List.of(
				new Installation( "Villa Portolani", Lift.of("HEALTHY", -2, 4, 1.0)),
				new Installation( "Condominio Girasoli", Lift.of("BASIC", -1, 6, 0.9)),
				new Installation( "Palazzina Ferrari", Lift.of("BASIC", 0, 4, 0.15)),
				new Installation( "Grattacielo Salutisti", Lift.of("HEALTHY", -2, 7, 1.0)),
				new Installation( "Grattacielo Porpora", Lift.of("MULTIFLOOR", -1, 10, 1.0))
		);		
		controller = new Controller(edifici); 
	
		stage.setTitle("EDLift simulator");
		MainPane root = new MainPane(controller, stage);
		Scene scene = new Scene(root, 820, 550, Color.ALICEBLUE);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
