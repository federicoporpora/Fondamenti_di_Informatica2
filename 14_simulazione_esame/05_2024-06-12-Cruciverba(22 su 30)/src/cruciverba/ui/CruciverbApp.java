package cruciverba.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;

import cruciverba.controller.Controller;
import cruciverba.persistence.SchemaReader;
import cruciverba.persistence.MySchemaReader;


public class CruciverbApp extends Application {

	private Controller controller;

	public void start(Stage stage) {
		SchemaReader schemaReader = null;
		try {
			schemaReader = new MySchemaReader(new FileReader("schema.txt"));
		} catch (FileNotFoundException e) {
			System.err.println("File non trovato");
			System.exit(1);
		}
		controller = new Controller(schemaReader);
	
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
