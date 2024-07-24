package pacchi.controller;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import pacchi.model.Territorio;
import pacchi.model.Valore;
import pacchi.model.Numero;
import pacchi.model.Pacco;
import pacchi.model.Partita;
import pacchi.model.Risposta;


public class Controller {

	public static void alert(String title, String headerMessage, String contentMessage) {
		javafx.scene.control.Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}

	private Set<Territorio> territori;
	private Set<Valore> premi; 
	private Partita partita;
	
	private int nMax, quantiDaAprire;
	private Fase fase;
	
	//--------------
	
	public Controller(Set<Territorio> territori, Set<Valore> premi) {
		if(territori==null) throw new IllegalArgumentException("Lista territori nulla nel construttore del Controller!");
		if(premi==null) throw new IllegalArgumentException("Lista territori premi nel construttore del Controller!");
		this.territori = territori;
		this.premi = premi;
		this.partita = new Partita(territori, premi);
		this.nMax = premi.size()/3;
		this.setFase(Fase.APERTURA_PACCHI);
	}

	public Set<Territorio> getTerritori() {
		return territori;
	}

	public Set<Valore> getPremi() {
		return premi;
	}
	
	public Set<Pacco> getPacchiDaAprire() {
		return partita.getStatoPartita().getPacchiDaAprire();
	}

	public Partita getPartita() {
		return partita;
	}
	
	public Valore apriPacco(Numero numeroPacco) {
		if(fase != Fase.APERTURA_PACCHI) throw new WrongPhaseException("Operazione inconsistente - Apertura pacchi non possibile in questa fase di gioco: " + fase);
		if(quantiDaAprire>0) {
			quantiDaAprire--;
			if(quantiDaAprire==0) setFase(Fase.INTERPELLA_DOTTORE);
			return partita.apriPacco(numeroPacco);
		} else {
			throw new WrongPhaseException("Operazione inconsistente - Già aperti tutti i pacchi previsti, ora chiedi al Dottore.");
		}
	}
	
	public Valore apriUltimoPacco(Numero numeroPacco) {
		if(fase != Fase.FINE) throw new WrongPhaseException("Operazione inconsistente - Apertura ultimo pacco non possibile in questa fase di gioco");
		if(quantiDaAprire==0) {
			quantiDaAprire--;
			return partita.apriPacco(numeroPacco);
		} else {
			throw new WrongPhaseException("Operazione inconsistente - Già aperti tutti i pacchi previsti.");
		}
	}
	
	public Pacco getPacco(Numero n) {
		return partita.getPacco(n);
	}
	
	public Risposta interpellaDottore() {
		return partita.interpellaDottore();
	}
	
	public SortedSet<Valore> premiRimasti(){
		var premiRimasti = partita.getStatoPartita().getPacchiDaAprire()
				.stream()
				.map(Pacco::premio)
				.collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Valore::valore))));
			premiRimasti.add(this.getPaccoConcorrente().premio());
		return premiRimasti;
	}
	
	private int sorteggiaQuantiDaAprire() {
		int res = (int) Math.floor(Math.random()*nMax)+1;
		return Math.min(res, this.premiRimasti().size()-2);
	}
	
	public Valore apriPaccoConcorrente() {
		return partita.getStatoPartita().getPaccoConcorrente().premio();
	}
	
	public Pacco getPaccoConcorrente() {
		return partita.getStatoPartita().getPaccoConcorrente();
	}

	public Fase getFase() {
		return fase;
	}

	public void setFase(Fase fase) {
		this.fase = fase;
		if(fase==Fase.APERTURA_PACCHI) {
			quantiDaAprire = sorteggiaQuantiDaAprire();
		}
	}
	
	public int quantiDaAprire() {
		return quantiDaAprire;
	}
	
}
