package cityparking.controller;

import java.time.LocalDate;

import cityparking.model.BadTimeIntervalException;
import cityparking.model.Ricevuta;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public interface Controller {
	
	public static void alert(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}
	
	public Ricevuta calcolaSosta(LocalDate dataInizio, String oraInizio, LocalDate dataFine, String oraFine) 
			throws BadTimeIntervalException, BadTimeFormatException;
}
