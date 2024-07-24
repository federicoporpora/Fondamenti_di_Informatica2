package pacchi.ui;

import java.util.HashMap;
import java.util.stream.Collectors;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import pacchi.controller.Controller;
import pacchi.controller.Fase;
import pacchi.model.Formatters;
import pacchi.model.Pacco;
import pacchi.model.Valore;
import pacchi.model.Risposta;



public class MainPane extends BorderPane {
	
	private Controller controller;
	
	private TextArea outputArea;
	private TextField txtRispostaDottore;
	private Button accetta, rifiuta, chiedi;
	private HashMap<Pacco,PaccoButton> mappaPacchiBottoni;
	private Label quantiPacchiDaAprire;
	private Risposta rispostaDottore;
	

	public MainPane(Controller controller) {
		this.controller=controller;
		mappaPacchiBottoni = new HashMap<>();
		//
		// ------ Area sinistra: zona di gioco, articolata in tre parti disposte verticalemente ------
		VBox leftBox = new VBox();
		leftBox.setPrefWidth(500);
			// ------ 1. Pannello bottoni pacco ------
			FlowPane buttonPane = new FlowPane();
			buttonPane.setPrefWidth(500);
			//
			// ***************
			// ****DA FARE****
			// Popolare il buttonPane con tanti PaccoButton quanti i pacchi da aprire (v. metodo getPacchiDaAprire del controller)
			// A tal fine, richiamare il metodo privato creaPaccoButton, che provvede ad agganciare  
			// ai bottoni il relativo gestore degli eventi (costituito dal metodo privato apriPacco) 
			// ***************
			//
			leftBox.getChildren().addAll(new Label("  "), buttonPane);
			//
			// ------ 2. Pannello bottone pacco concorrente ------
			FlowPane buttonConcorrentePane = new FlowPane();
			buttonConcorrentePane.setPrefWidth(500);
			var labelPaccoConcorrente = new Label("Pacco concorrente: ");
			labelPaccoConcorrente.setFont(Font.font("Courier New", FontWeight.BOLD, 14));
			buttonConcorrentePane.getChildren().add(labelPaccoConcorrente);
			creaPaccoButtonConcorrente(buttonConcorrentePane, controller.getPaccoConcorrente(), "orange");
			leftBox.getChildren().addAll(new Label("  "), buttonConcorrentePane);
			//
			// ------ 3. Area interazione col Dottore ------
			// ------ 3.a  indicazione di quanti pacchi aprire volta per volta ------
			quantiPacchiDaAprire = new Label("Pacchi da aprire: " + controller.quantiDaAprire());
			quantiPacchiDaAprire.setPrefWidth(500);
			quantiPacchiDaAprire.setFont(Font.font("Courier New", FontWeight.BOLD, 14));
			leftBox.getChildren().addAll(new Label("  "), quantiPacchiDaAprire);
			// ------ 3.b  indicazione di richiesta al Dottore
			var labelChiediDottore = new Label("Chiedi al Dottore: ");
			labelChiediDottore.setPrefWidth(500);
			labelChiediDottore.setFont(Font.font("Courier New", FontWeight.BOLD, 14));
			leftBox.getChildren().add(labelChiediDottore);
			// ------ 3.c  pulsanti e textbox per chiedere al Dottore, vedere l'offerta, accettare o rifiutare l'offerta ------
			var miniHBoxDottore = new HBox();
				miniHBoxDottore.setPrefWidth(500);
				miniHBoxDottore.setAlignment(Pos.CENTER);
				chiedi = new Button("Chiedi");
				chiedi.setDisable(true);
				chiedi.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
				chiedi.setOnAction(ev -> interpellaDottore());	// gestore eventi "interpella Dottore"
				miniHBoxDottore.getChildren().addAll(chiedi, new Label("   Risposta: "));
				txtRispostaDottore = new TextField(); 
				txtRispostaDottore.setEditable(false);
				txtRispostaDottore.setPrefWidth(130);
				miniHBoxDottore.getChildren().addAll(txtRispostaDottore, new Label("   ") );
				accetta = new Button("Accetta");
				accetta.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
				accetta.setDisable(true);
				accetta.setOnAction(ev -> accettaOfferta());	// gestore eventi "accetta offerta"
				rifiuta = new Button("Rifiuta");
				rifiuta.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
				rifiuta.setDisable(true);
				rifiuta.setOnAction(ev -> rifiutaOfferta());	// gestore eventi "rifiuta offerta"
				miniHBoxDottore.getChildren().addAll(accetta,new Label("   "), rifiuta);
			leftBox.getChildren().addAll(new Label("  "), miniHBoxDottore);
			this.setLeft(leftBox);
		// ------ 4. Area destra: tabellone premi ------
		VBox rightBox = new VBox();
			rightBox.setPrefWidth(200);
			outputArea = new TextArea();
			outputArea.setPrefSize(200,400);
			outputArea.setFont(Font.font("Courier New", FontWeight.NORMAL, 14));
			outputArea.setEditable(false);
			rightBox.getChildren().addAll(outputArea);
			outputArea.setText(sintetizzaOutput());
		this.setRight(rightBox);
	}

	private void creaPaccoButton(FlowPane buttonPane, Pacco p, String color) {
		var paccoButton = new PaccoButton(p.territorio(), p.numero(), color);
		mappaPacchiBottoni.put(p, paccoButton);
		paccoButton.setOnAction(ev -> apriPacco(p));
		buttonPane.getChildren().add(paccoButton);
	}
	
	private void creaPaccoButtonConcorrente(FlowPane pane, Pacco p, String color) {
		var paccoButton = new PaccoButton(p.territorio(), p.numero(), color);
		mappaPacchiBottoni.put(p, paccoButton);
		paccoButton.setOnAction( ev -> 
			Controller.alert("STOP", "Fase di gioco errata", "Non puoi aprire il pacco concorrente ora.") );
		pane.getChildren().add(paccoButton);
	}

	private void interpellaDottore() {
		//
		// ***************
		// ****DA FARE****
		// Preliminarmente, verifica che il controller sia nella fase INTERPELLA_DOTTORE, altrimenti emette
		// tramite alert un messaggio d'errore e ritorna senza fare nulla
		// Se la fase è corretta, interpella il Dottore e tramite l'omonimo metodo del controller, visualizza la risposta
		// nel campo di testo e ri-setta il controller nella fase ACCETTA_RIFIUTA, così da predisporsi al giro successivo
		// Infine, disabilita il tasto "Chiedi" e ri-abilita i due tasti "Accetta" e "Rifiuta" 
		// ***************
		//
	}	
	
	private void rifiutaOfferta() {
		if (controller.getFase()!=Fase.ACCETTA_RIFIUTA) {
			Controller.alert("STOP", "Fase di gioco errata", "Impossibile accettare o rifiutare offerte/cambi in questa fase)");
			return;
		}
		if (controller.premiRimasti().size()==2) {
			//
			controller.setFase(Fase.FINE);
			Controller.alert("FINE GIOCO", "Hai rifiutato l'offerta", "Hai vinto il contenuto del tuo pacco: " 
												+ Formatters.priceFormatter.format(controller.apriPaccoConcorrente().valore()));
			// resta da aprire l'ultimo pacco rimasto
			var ultimoPaccoDaAprire = controller.getPacchiDaAprire().iterator().next();
			aggiornaPaccoButton(ultimoPaccoDaAprire);
			aggiornaPaccoButton(controller.getPaccoConcorrente());
			controller.apriUltimoPacco(ultimoPaccoDaAprire.numero());
			sintetizzaOutput(); // per aggiornare il tabellone
		}
		else {
			controller.setFase(Fase.APERTURA_PACCHI);
			var q = controller.quantiDaAprire();
			Controller.alert("PROSECUZIONE GIOCO", "Hai rifiutato l'offerta","Ora dovrai aprire altri " + q + (" pacchi."));
			quantiPacchiDaAprire.setText("Pacchi da aprire: " + q);
		}
		accetta.setDisable(true);
		rifiuta.setDisable(true);
		txtRispostaDottore.setText("");
	}


	private void accettaOfferta() {
		if (controller.getFase()!=Fase.ACCETTA_RIFIUTA) {
			Controller.alert("STOP", "Fase di gioco errata", "Impossibile accettare o rifiutare offerte/cambi in questa fase.");
			return;
		}
		Controller.alert("FINE GIOCO", "Hai accettato l'offerta", 
					"Nel tuo pacco avevi: " + Formatters.priceFormatter.format(controller.apriPaccoConcorrente().valore()));
		accetta.setDisable(true);
		rifiuta.setDisable(true);
		aggiornaPaccoButton(controller.getPaccoConcorrente());
		controller.setFase(Fase.FINE);
	}

	private void apriPacco(Pacco p) {
		if (controller.getFase()!=Fase.APERTURA_PACCHI) {
			Controller.alert("STOP", "Fase di gioco errata", "Impossibile aprire pacchi in questa fase)");
			return;
		}
		if(controller.quantiDaAprire()==0) {
			Controller.alert("STOP", "Operazione inconsistente", "Già aperti tutti i pacchi previsti, ora chiedi al Dottore.");
			return;
		}
		aggiornaPaccoButton(p);
		controller.apriPacco(p.numero());
		quantiPacchiDaAprire.setText("Pacchi da aprire: " + controller.quantiDaAprire());
		outputArea.setText(sintetizzaOutput());
		if(controller.quantiDaAprire()==0) {
			controller.setFase(Fase.INTERPELLA_DOTTORE);
			chiedi.setDisable(false);
		}
	}

	private void aggiornaPaccoButton(Pacco p) {
		var button = this.mappaPacchiBottoni.get(p);
		button.setDisable(true);
		button.setText(p.toString() + System.lineSeparator() + Formatters.priceFormatter.format(p.premio().valore()));
	}
	
	private String sintetizzaOutput() {
		//
		// ***************
		// ****DA FARE****
		// Sintetizza le righe da mostrare nel tabellone premi, secondo il formato mostrato nelle figure:
		// - in alto, il pacco del concorrente (senza ovviamente svelarne il premio)
		// - sotto, i premi ancora disponibili, in ordine crescente (senza ovviamente indicare i relativi pacchi!)
		// ***************
		//		
		return 	"TABELLONE DA FARE " ; // FAKE, da sostituire con la giusta stringa generata
	}
	
}
