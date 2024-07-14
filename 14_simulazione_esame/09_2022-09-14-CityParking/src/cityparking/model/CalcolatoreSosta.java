package cityparking.model;

import java.time.LocalDateTime;

public abstract class CalcolatoreSosta {
	
	private Tariffa tariffa;

	public CalcolatoreSosta(Tariffa tariffa) {
		this.tariffa = tariffa;
	}

	public abstract Ricevuta calcola(LocalDateTime inizio, LocalDateTime fine) throws BadTimeIntervalException;

	public Tariffa getTariffa() {
		return tariffa;
	} 
}
