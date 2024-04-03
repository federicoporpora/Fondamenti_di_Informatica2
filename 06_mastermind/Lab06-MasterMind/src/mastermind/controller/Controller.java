package mastermind.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import mastermind.model.Combinazione;
import mastermind.model.Gioco;
import mastermind.model.MasterMind;
import mastermind.model.Risposta;
import mastermind.model.Status;


public class Controller {
	private Gioco gioco;
	private int dim, maxTentativi;
	private MasterMind brain; 
	
	public static void alert(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}
	
	public Controller(int maxTentativi, int dim) {
		this.dim=dim;
		this.maxTentativi=maxTentativi;
		init(maxTentativi, dim);
	}
	
	private void init(int maxTentativi, int dim) {
		this.gioco=new Gioco(maxTentativi,dim);
		this.brain=new MasterMind(dim);
	}
		
	public int maxTentativi() {
		return maxTentativi;
	}
	
	public void restart() {
		init(maxTentativi, dim);		
	}

	public int dimensioneCodice() {
		return dim;
	}

	public Status status() {
		return gioco.stato();
	}

	public int tentativiEffettuati() {
		return gioco.tentativiEffettuati();
	}

	public int tentativiRimasti() {
		return gioco.tentativiRimasti();
	}

	public Status tenta(Combinazione tentativo) {
		Risposta risposta = brain.calcolaRisposta(tentativo);
		return gioco.registraMossa(tentativo,risposta);
	}

	public String situazione() {
		return gioco.situazione();
	}

	public String combinazioneSegreta() {
		return brain.combinazioneSegreta();
	}	
}
