package spesesanitarie.controller;

import java.util.Arrays;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import spesesanitarie.model.AnalizzatoreSpese;
import spesesanitarie.model.DocumentoDiSpesa;
import spesesanitarie.model.Tipologia;


public class Controller {

	public static void alert(String title, String headerMessage, String contentMessage) {
		javafx.scene.control.Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}

	private List<DocumentoDiSpesa> spese;
	private AnalizzatoreSpese analizzatore;
	
	//--------------
	
	public Controller(List<DocumentoDiSpesa> spese) {
		if(spese==null || spese.isEmpty()) throw new IllegalArgumentException("lista spese nulla o vuota");
		this.spese = spese;
		this.analizzatore = new AnalizzatoreSpese(spese);
	}

	public List<DocumentoDiSpesa> getSpese() {
		return spese;
	}
	

	public List<Tipologia> getTipologie() {
		return Arrays.asList(Tipologia.values());
	}
	
	public double totaleSpesePer(Tipologia t){
		return analizzatore.somma(t);
	}
	
	public List<DocumentoDiSpesa> filtraPer(Tipologia t){
		return analizzatore.filtraPer(t);
	}
	
	public double totaleSpeseDetraibili(){
		return Tipologia.tipologieDetraibili().stream().mapToDouble(this::totaleSpesePer).sum();
	}

	public double totaleSpeseNonDetraibili(){
		return Tipologia.tipologieNonDetraibili().stream().mapToDouble(this::totaleSpesePer).sum();
	}
	

}
