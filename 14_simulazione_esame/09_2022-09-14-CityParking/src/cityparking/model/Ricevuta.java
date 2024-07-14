package cityparking.model;

import java.text.NumberFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Ricevuta {
	private LocalDateTime inizio, fine;
	private double costo;
	
	public Ricevuta(LocalDateTime inizio, LocalDateTime fine, double costo) {
		if(inizio==null) throw new IllegalArgumentException("Data e ora iniziale nulla in ticket:" + inizio);
		if(fine==null) throw new IllegalArgumentException("Data e ora finale nulla in ticket:" + fine);
		if(costo<0 || !Double.isFinite(costo)) throw new IllegalArgumentException("Costo illegale in ticket :" + costo);
				
		this.inizio = inizio;
		this.fine = fine;
		this.costo = costo;
	}
	
	public LocalDateTime getInizioSosta() {
		return inizio;
	}
	
	public LocalDateTime getFineSosta() {
		return fine;
	}
	
	public double getCosto() {
		return costo;
	}
	
	public String getCostoAsString() {
		NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.ITALY);
		// NB: lo spazio prima/dopo il simbolo di valuta è il non-breakable space (codice 160), 
		//     NON lo spazio classico (codice 32)
		return formatter.format(costo);
	}
	
	@Override
	public String toString() {
		return "City Parking\ndalle " +
				inizio.toLocalTime().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.ITALY)) +
				" di " + 
				inizio.toLocalDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.ITALY)) +
				"\n" +
				" alle " +
				fine.toLocalTime().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.ITALY)) +
				" di " + 
				fine.toLocalDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.ITALY)) +
				"\n" +
				"Durata totale: " + toStringDuration(Duration.between(inizio, fine)) +
				"\n" +
				"Costo totale:  " + getCostoAsString();
	}
	
	private String toStringDuration(Duration duration) {
		int minuti = duration.toMinutesPart();
		String sMinuti = (minuti<10 ? "0" : "") + minuti;
		return duration.toHours() + ":" + sMinuti;
	}
	
}
