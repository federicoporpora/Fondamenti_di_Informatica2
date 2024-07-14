package cruciverba.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import cruciverba.controller.Controller;
import cruciverba.persistence.SchemaReader;
import cruciverba.persistence.MockSchemaReader;


public class CruciverbAppMock extends Application {

	private Controller controller;

	public void start(Stage stage) {
		SchemaReader reader = new MockSchemaReader();
		
		controller = new Controller(reader);
	
		stage.setTitle("Cruciverba");
		MainPane root = new MainPane(controller, stage);
		Scene scene = new Scene(root, 750, 500, Color.ALICEBLUE);
		stage.setScene(scene);
		stage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}
}
