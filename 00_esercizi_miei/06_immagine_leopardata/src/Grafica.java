import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Grafica extends Application {
	public void start(Stage stage){
		stage.setTitle("LEOPARDO");
		BorderPane panel = new BorderPane();
		var immagine = new Image("file:///F:/Fede/Coding/Fondamenti_di_Informatica2/00_esercizi_miei/06_immagine_leopardata/immagine_leopardata.jpg");
		panel.setCenter(new ImageView(immagine));
		Scene scene = new Scene(panel,Color.WHITE);
		stage.setScene(scene);
		stage.setMaxHeight(immagine.getHeight());
		stage.setMaxWidth(immagine.getWidth());
		stage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}

}