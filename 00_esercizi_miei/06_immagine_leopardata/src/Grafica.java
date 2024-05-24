import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Grafica extends Application {
	public void start(Stage stage){
		stage.setTitle("aaaaaaaaaaaaaaaaaaa");
		BorderPane panel = new BorderPane();
		panel.setCenter(new ImageView(new Image("immagine_leopardata.jpg", 538, 360, true, false)));
		Scene scene = new Scene(panel,Color.WHITE);
		stage.setScene(scene);
		stage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}

}