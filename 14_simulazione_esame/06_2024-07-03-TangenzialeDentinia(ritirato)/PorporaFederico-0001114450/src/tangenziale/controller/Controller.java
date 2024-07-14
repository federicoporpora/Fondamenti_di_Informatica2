package tangenziale.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import tangenziale.model.Autostrada;
import tangenziale.model.MyPlanner;
import tangenziale.model.Percorso;
import tangenziale.model.Planner;


public class Controller {
	
	private Map<String, Autostrada> rete;
	private Planner planner;


	public Controller(Map<String,Autostrada> rete) {
		Objects.requireNonNull(rete, "Controller: la rete autostradale non può essere nulla");
		if (rete.size()==0) throw new IllegalArgumentException("Controller: la rete autostradale non può essere vuota");
		this.rete=rete;
		this.planner = new MyPlanner(rete);
	}
	
	public List<String> elencoCaselli(){
		return rete.values().stream().map(Autostrada::profilo)
									 .map(Map::values)
									 .flatMap(Collection::stream)
									 .sorted()
									 .filter(s -> !s.matches("M[1-4]"))
									 .filter(s -> !s.matches("Tangenziale"))
									 .toList();
	}
	
	public Percorso trovaPercorso(String da, String a) {
		return planner.trovaPercorso(da, a);
	}
	
	//--------------------------
	
	public static void alert(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}
}
