package speedcollege.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.BiFunction;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import speedcollege.model.Attualizzatore;
import speedcollege.model.Carriera;
import speedcollege.model.Esame;

public class Controller {

	public static void alert(String title, String headerMessage, String contentMessage) {
		javafx.scene.control.Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}
	
	//--------------
	
	private Map<String,Carriera> mappaCarriere;
	private Attualizzatore attualizzatore;

	public Controller(SortedMap<String,Carriera> mappa) {
		if (mappa==null) throw new IllegalArgumentException("mappa carriere nulla");
		if (mappa.size()==0) throw new IllegalArgumentException("mappa carriere vuota");
		this.mappaCarriere = mappa;
		this.attualizzatore= new Attualizzatore(
				mappa.get(mappa.firstKey()),
				Attualizzatore.NESSUN_DECADIMENTO, LocalDate.now());
	}
	
	public Carriera getCarriera(String idCarriera) {
		return mappaCarriere.get(idCarriera);
	}
	
	public void setCarriera(String idCarriera) {
		Carriera carriera = mappaCarriere.get(idCarriera);
		if (carriera==null) throw new IllegalArgumentException("carriera inesistente");
		attualizzatore.setCarriera(	mappaCarriere.get(idCarriera) );
	}
	
	public List<String> getListaIdCarriere() {
		return new ArrayList<String>(mappaCarriere.keySet()); 
	}
	
	public double getMediaPesata() {
		return attualizzatore.mediaPesata();
	}

	public double getCreditiAcquisiti(String idCarriera) {
		return mappaCarriere.get(idCarriera).creditiAcquisiti();
	}
	
	@Override
	public String toString() {
		return attualizzatore.toString();
	}
	
	public BiFunction<Esame,LocalDate,Double> getFunction() {
		return attualizzatore.getFunction();
	}

	public void setFunction(BiFunction<Esame,LocalDate,Double> f) {
		attualizzatore.setFunction(f);;
	}
	
	public LocalDate getDataDiAttualizzazione() {
		return attualizzatore.getDataDiAttualizzazione();
	}

	public void setDataDiAttualizzazione(LocalDate d) {
		attualizzatore.setDataDiAttualizzazione(d);
	}
	
	public SortedSet<String> elencoFunzioni() {
		return new TreeSet<String>(Attualizzatore.elencoFunzioni().keySet()).descendingSet();
	}
	
	public Map<String,BiFunction<Esame,LocalDate,Double>> mappaFunzioni() {
		return Attualizzatore.elencoFunzioni();
	}
	
	public BiFunction<Esame,LocalDate,Double> getFunction(String idFunction) {
		return mappaFunzioni().get(idFunction);
	}

}
