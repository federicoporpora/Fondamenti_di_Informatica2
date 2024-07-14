package cruciverba.controller;

import java.io.IOException;
import java.util.SortedMap;

import cruciverba.model.Cruciverba;
import cruciverba.model.Numeratore;
import cruciverba.persistence.BadFileFormatException;
import cruciverba.persistence.SchemaReader;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class Controller  {
	
	public static void alert(String title, String headerMessage, String contentMessage) {
		javafx.scene.control.Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}

	
	private Cruciverba cruciverba;
	private SchemaReader schemaReader;
	private Numeratore numeratore;

	public Controller(SchemaReader schemaReader) {
		this.schemaReader = schemaReader;
		this.numeratore = null;
	}

	public void leggiSchema() throws BadFileFormatException, IOException {
		this.cruciverba = schemaReader.leggiSchema();
		this.numeratore = new Numeratore(cruciverba);
	}

	public Cruciverba getCruciverba() {
		return cruciverba;
	}
	
	public String schemaNumerato() {
		if(numeratore==null) throw new UnsupportedOperationException("Operazione non supportata: schema non ancora caricato");
		else return numeratore.toString();
	}
	
	public SortedMap<Integer,String> orizzontali(){
		if(numeratore==null) throw new UnsupportedOperationException("Operazione non supportata: schema non ancora caricato");
		else return numeratore.orizzontali();
	}
	
	public SortedMap<Integer,String> verticali(){
		if(numeratore==null) throw new UnsupportedOperationException("Operazione non supportata: schema non ancora caricato");
		else return numeratore.verticali();
	}

}
