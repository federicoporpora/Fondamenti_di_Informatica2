package pacchi.ui;

import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import pacchi.model.Numero;
import pacchi.model.Territorio;

public class PaccoButton extends Button {

	private Numero numero;

	public PaccoButton(Territorio territorio, Numero numero, String color) {
		super(territorio.nome() + " n° " + numero.valore());
		this.setTextAlignment(TextAlignment.CENTER);
		this.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
		if (!color.equalsIgnoreCase("default")) this.setStyle("-fx-background-color: " + color + ";");
		this.numero=numero;
		this.setPrefSize(165,40);
	}

	public Numero getNumero() {
		return numero;
	}
	
}
