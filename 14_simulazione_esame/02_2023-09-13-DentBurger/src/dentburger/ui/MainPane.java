package dentburger.ui;

import dentburger.controller.Controller;
import dentburger.model.Categoria;
import dentburger.model.Prodotto;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class MainPane extends BorderPane {
	
	private Controller controller;
	
	private ComboBox<String> genereCombo;
	private ComboBox<Categoria> catCombo;
	private ComboBox<Prodotto> prodCombo;
	private TextArea outputArea;
	private Button aggiungi, rimuovi;
	

	public MainPane(Controller controller) {
		this.controller=controller;
		//
		VBox leftBox = new VBox();
		leftBox.setPrefWidth(200);
			HBox miniBoxIniziale = new HBox(); miniBoxIniziale.setAlignment(Pos.CENTER);
			Label titolino = new Label("Dent Burger");
			titolino.setStyle("-fx-font-weight: bold");
			miniBoxIniziale.getChildren().addAll(titolino);
			leftBox.getChildren().addAll(new Label("  "), miniBoxIniziale, new Label("  "));
			//
			// ***** DA FARE: instanziare e popolare la combo categorie
			//
			catCombo.setTooltip(new Tooltip("Scegliere la categoria"));
			catCombo.setPrefWidth(175);
			leftBox.getChildren().addAll(new Label(" Categoria:  "), catCombo);
			//
			// ***** DA FARE: agganciare a catCombo l'apposito gestore degli eventi, popolaComboGeneri
			//
			genereCombo = new ComboBox<>();
			genereCombo.setTooltip(new Tooltip("Scegliere il genere"));
			genereCombo.setPrefWidth(175);
			leftBox.getChildren().addAll(new Label(" Genere:  "), genereCombo);
			//
			// ***** DA FARE: agganciare a genereCombo l'apposito gestore degli eventi, popolaComboProdotti
			//
			prodCombo = new ComboBox<>();
			prodCombo.setTooltip(new Tooltip("Scegliere il prodotto"));
			prodCombo.setPrefWidth(175);
			leftBox.getChildren().addAll(new Label(" Prodotto:  "), prodCombo);
			//
			aggiungi = new Button("Aggiungi");
			rimuovi = new Button("Rimuovi");
			//
			// ***** DA FARE: agganciare ai pulsanti l'apposito gestore degli eventi, modificaOrdine
			//
			leftBox.getChildren().addAll(new Label(" "), aggiungi,rimuovi);
			//
			this.setLeft(leftBox);
		VBox rightBox = new VBox();
			rightBox.setPrefWidth(580);
			outputArea = new TextArea();
			outputArea.setPrefSize(580,500);
			outputArea.setFont(Font.font("Courier New", FontWeight.NORMAL, 11));
			outputArea.setEditable(false);
			rightBox.getChildren().addAll(outputArea);
		this.setRight(rightBox);
	}

	private void popolaComboGeneri() {
		// **** DA FARE ****
		// Recupera dalla combo delle categorie la Categoria selezionata (se esiste, altrimenti non fa nulla)
		// e, sulla base di quella, recupera dal Controller la lista dei soli generi di interesse: se tale 
		// lista ha almeno un elemento, provvede a selezionare il primo come default nella combo. 
		// Infine, richiama popolaComboProdotti per propagare il popolamento conseguente alla terza combo.
	}

	private void popolaComboProdotti() {
		// **** DA FARE ****
		// Recupera dalle due combo delle categorie e dei generi la Categoria e il genere selezionati (se 
		// esistono: altrimenti non fa nulla) e, sulla base di questi, recupera dal Controller la lista dei 
		// soli prodotti di interesse: come sopra, se tale lista ha almeno un elemento provvede a selezionare 
		// il primo come default nella combo.
	}
	
	private void modificaOrdine(Operazione op) {
		// **** DA FARE ****
		// Recupera dalla combo prodotti il Prodotto attualmente selezionato (se esiste: altrimenti emette 
		// un apposito messaggio d’errore tramite la funzione alert del Controller), quindi distingue il 
		// pulsante premuto tramite l’argomento Operazione ricevuto e procede a invocare l’appropriato metodo
		// del Controller per aggiornare l’ordine. 
		// Infine, aggiorna la visualizzazione dell’ordine, recuperato tramite l’apposito metodo del 
		// Controller, sull’area di output.
	}
	
}
