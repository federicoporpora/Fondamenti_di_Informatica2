package meteodent.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import meteodent.model.Bollettino;
import meteodent.model.MyGeneratoreBollettino;
import meteodent.model.Previsione;
import meteodent.model.TipoBollettino;


public class Controller {

	public static void alert(String title, String headerMessage, String contentMessage) {
		javafx.scene.control.Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}

	private Map<String, SortedSet<Previsione>> previsioniMap;
	
	//--------------
	
	public Controller(Map<String, SortedSet<Previsione>> previsioniMap) {
		if(previsioniMap==null || previsioniMap.isEmpty()) throw new IllegalArgumentException("previsioni inesistenti!");
		this.previsioniMap = previsioniMap;
	}

	public Map<String, SortedSet<Previsione>> getMappaPrevisioni() {
		return previsioniMap;
	}

	public List<String> getListaLocalita() {
		return previsioniMap.keySet().stream().sorted().toList();
	}

	public Set<LocalDate> getDatePerLocalita(String localita) {
		Collection<Previsione> previsioni = previsioniMap.get(localita);
		TreeSet<LocalDate> distinctDates = new TreeSet<>();
		for (Previsione previsione : previsioni) {
			LocalDate dayDate = previsione.getDataOra().toLocalDate();
			if (!distinctDates.contains(dayDate)) {
				distinctDates.add(dayDate);
			}
		}
		return distinctDates;
	}

	public List<Previsione> getPrevisioni(String localita, LocalDate data) {
		SortedSet<Previsione> previsioni = previsioniMap.get(localita);
		return filterByDate(previsioni, data);
	}

	public Bollettino getBollettino(String localita, LocalDate data, TipoBollettino tipoBollettino) {
		List<Previsione> previsioni = getPrevisioni(localita, data);
		return new MyGeneratoreBollettino().generaBollettino(previsioni, tipoBollettino);
	}
	
	private List<Previsione> filterByDate(SortedSet<Previsione> previsioni, LocalDate data) {
		List<Previsione> listaPrevisioniFiltrate = new ArrayList<>();
		for (Previsione previsione : previsioni) {
			if (previsione.getDataOra().toLocalDate().isEqual(data)) {
				listaPrevisioniFiltrate.add(previsione);
			}
		}
		return listaPrevisioniFiltrate;
	}

}
