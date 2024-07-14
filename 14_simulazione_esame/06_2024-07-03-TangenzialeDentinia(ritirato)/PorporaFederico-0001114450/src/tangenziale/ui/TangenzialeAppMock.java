package tangenziale.ui;

import java.util.Map;
import java.util.TreeMap;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tangenziale.controller.Controller;
import tangenziale.model.Autostrada;
import tangenziale.model.Tipologia;


public class TangenzialeAppMock extends TangenzialeApp {

	@Override
	public void start(Stage stage) {
		stage.setTitle("Rete autostradale di Dentinia - MOCK");
		
		var reteAutostradale =  Map.of(
				"M3", new Autostrada(Tipologia.AUTOSTRADA, "M3",
						new TreeMap<>(Map.of(45, "Petruvia Nord", 40, "Petruvia Sud", 25, "Aldino", 1, "Dentinia ippodromo", 0, "Tangenziale"))
						),
				"M4", new Autostrada(Tipologia.AUTOSTRADA, "M4",
						new TreeMap<>(Map.of(0, "Tangenziale", 24, "Castelbello", 37, "Molla", 50, "Forte", 77, "Ripalta"))
						),
				"M1", new Autostrada(Tipologia.AUTOSTRADA, "M1",
						new TreeMap<>(Map.of(85, "Montelusa Centro", 78, "Montelusa Z.I.", 48, "Vigata ovest", 42, "Vigata Centro", 37, "Vigata est", 12, "Dentinia outlet", 0, "Tangenziale"))
						),
				"M2", new Autostrada(Tipologia.AUTOSTRADA, "M2",
						new TreeMap<>(Map.of(0, "Tangenziale", 18, "Sasso del Moro", 39, "Val di Mezzo", 49, "Val di Sopra", 76, "Val d'altrove"))
						),
				"Tangenziale", new Autostrada(Tipologia.TANGENZIALE, "Tangenziale", 
						new TreeMap<>(Map.of(0, "M2", 4, "via dei colli", 9, "M1", 13, "Aeroporto", 19, "M3", 23, "Fiera", 26, "Stadio", 32, "M4"))
						)
				);
		
			MainPane root = new MainPane(new Controller(reteAutostradale));

			Scene scene = new Scene(root, 500, 200, Color.ALICEBLUE);
			stage.setScene(scene);
			stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
