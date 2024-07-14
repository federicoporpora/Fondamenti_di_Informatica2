package trainstats.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import trainstats.model.StatProvider;
import trainstats.model.Train;


public class Controller {

	public static void alert(String title, String headerMessage, String contentMessage) {
		javafx.scene.control.Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}
	
	//--------------
	
	private Map<String,Train> trains;
	private List<StatProvider> providers;
	private StatProvider currentProvider;

	public Controller(SortedMap<String,Train> trains, List<StatProvider> providers) {
		if(trains==null) throw new IllegalArgumentException("mappa treni nulla");
		if(providers==null) throw new IllegalArgumentException("mappa provider nulla");
		if (trains.size()==0) throw new IllegalArgumentException("mappa treni vuota");
		if (providers.size()==0) throw new IllegalArgumentException("mappa provider vuota");
		this.trains = trains;
		this.providers = providers;
		this.currentProvider = providers.get(0); // default
	}
	
	public Train getTrain(String id) {
		return trains.get(id);
	}
	
	public List<String> getAvailableTrains() {
		return new ArrayList<String>(trains.keySet()); 
	}
	
	public List<StatProvider> getAvailableProviders() {
		return new ArrayList<>(providers); 
	}
	
	public long getStatsValue(Train treno) {
		return currentProvider.getStatistics(treno);
	}
	
	public String getStatsMessage(Train treno) {
		return currentProvider.getStatsMessage(treno);
	}

	public long getStatistics(String idTreno) {
		return this.getStatsValue(trains.get(idTreno));
	}
	
	public StatProvider getCurrentProvider() {
		return currentProvider;
	}
	
	public void setCurrentProvider(StatProvider provider) {
		currentProvider = provider;
	}

	public boolean hasActiveThreshold() {
		return currentProvider.hasThreshold();
	}

	public void setThreshold(int t) {
		if(currentProvider.hasThreshold()) currentProvider.setThreshold(t);
	}

	public int getThreshold() {
		if(currentProvider.hasThreshold()) return currentProvider.getThreshold();
		else throw new UnsupportedOperationException("Current provider has no threshold");
	}

}
