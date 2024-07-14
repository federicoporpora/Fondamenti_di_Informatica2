package tangenziale.ui;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tangenziale.controller.Controller;
import tangenziale.persistence.BadFileFormatException;
import tangenziale.persistence.MyAutostradeReader;


public class TangenzialeApp extends Application {

	public void start(Stage stage) {
		stage.setTitle("Rete autostradale di Dentinia");
		
		try(FileReader fileReader = new FileReader("AutostradeDentinia.txt")){
			MainPane root = new MainPane(new Controller(
					new MyAutostradeReader().leggiAutostrade(fileReader)
					));

			Scene scene = new Scene(root, 500, 200, Color.ALICEBLUE);
			stage.setScene(scene);
			stage.show();
		}
		catch(FileNotFoundException e) {
			Controller.alert("ERRORE DI I/O", "File non trovato", "Impossibile aprire il file con la rete autostradale");
			System.exit(1);
		} catch (IOException e) {
			Controller.alert("ERRORE DI I/O", "Apertura non riuscita", "Impossibile aprire il file con la rete autostradale");
			System.exit(1);
		} catch (BadFileFormatException e) {
			Controller.alert("ERRORE DI FORMATO", "Errore di formato", "Il file con la descrizione della rete autostradale è danneggiato");
			System.exit(1);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
