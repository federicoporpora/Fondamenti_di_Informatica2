package mastermind.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import mastermind.controller.Controller;

public class MasterMindApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	public static int NUMERO_TENTATIVI = 10;
	public static int DIMENSIONE_CODICE_SEGRETO = 4;
	
	private Controller controller;

	public void start(Stage stage) {
		controller = new Controller(NUMERO_TENTATIVI, DIMENSIONE_CODICE_SEGRETO);
		System.out.println("Configuration read");
		stage.setTitle("Master Mind");
		MasterMindPane root = new MasterMindPane(controller, stage);
		
		Scene scene = new Scene(root, 650, 300, Color.ALICEBLUE);
		stage.setScene(scene);
		stage.show();
	}

}