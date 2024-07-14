package trainstats.ui;

import java.text.NumberFormat;
import java.util.Locale;

import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import trainstats.controller.Controller;
import trainstats.model.StatProvider;


public class MainPane extends BorderPane {
	
	private Controller controller;
	
	private ComboBox<String> trainCombo;
	private ComboBox<StatProvider> providerCombo;
	private TextArea area;
	private NumberFormat formatter;
	private Slider slider;

	public MainPane(Controller controller) {
		this.controller=controller;
		formatter = NumberFormat.getInstance(Locale.ITALY);
		formatter.setMaximumFractionDigits(2);
		formatter.setMinimumFractionDigits(2);
		//
		VBox leftBox = new VBox();
		leftBox.setPrefWidth(200);
			HBox miniBoxIniziale = new HBox(); miniBoxIniziale.setAlignment(Pos.CENTER);
			Label titolino = new Label("Statistiche ritardi treni");
			titolino.setStyle("-fx-font-weight: bold");
			miniBoxIniziale.getChildren().addAll(titolino);
			leftBox.getChildren().addAll(new Label("  "), miniBoxIniziale, new Label("  "));
			//
			// ****************** DA COMPLETARE QUI **************
			// inserire il popolamento di trainCombo
			//
			trainCombo.setTooltip(new Tooltip("Scegliere il treno su cui operare"));
			trainCombo.setPrefWidth(175);
			leftBox.getChildren().addAll(new Label(" Scelta treno:  "), trainCombo);
			//
			// ****************** DA COMPLETARE QUI **************
			// agganciare a trainCombo il gestore degli eventi myHandle 
			//
			// ****************** DA COMPLETARE QUI **************
			// inserire il popolamento di providerCombo e la preselezione del primo elemento come deafult
			//
			providerCombo.setPrefWidth(175);
			leftBox.getChildren().addAll(new Label(" Criterio di valutazione:  "), providerCombo);
			//
			// ****************** DA COMPLETARE QUI **************
			// agganciare a providerCombo il gestore degli eventi myHandle
			//
			// ****************** DA COMPLETARE QUI **************
			// creare lo Slider opportunamente configurato
			//
			slider.setShowTickMarks(true);
			slider.setShowTickLabels(true);
			slider.setMajorTickUnit(10);
			//
			// ****************** DA COMPLETARE QUI **************
			// agganciare allo slider il gestore degli eventi myHandle
			//
			leftBox.getChildren().addAll(new Label(" Soglia (min.):  "), slider);
			//
			this.setLeft(leftBox);
		VBox rightBox = new VBox();
			rightBox.setPrefWidth(580);
			area = new TextArea();
			area.setPrefSize(580,500);
			area.setFont(Font.font("Courier New", FontWeight.NORMAL, 11));
			area.setEditable(false);
			rightBox.getChildren().addAll(area);
		this.setRight(rightBox);
	}

	private void myHandle() {
		
		/* ****************** DA IMPLEMENTARE **************
		 * Il gestore deve:
			- recuperare dalla combo treni il nome del treno scelto e, sulla base di quello, recuperare l’oggetto Train 
			  corrispondente per le successive operazioni
			- impostare come provider corrente nel Controller lo StatProvider selezionato nell’apposita combo
			- recuperare dallo Slider il valore della soglia desiderata e impostarla nel Controller
			- emettere nell’area di testo i dati del treno e, subito sotto, l’esito del calcolo della corrispondente 
			  statistica
		*/

	}
	
}
