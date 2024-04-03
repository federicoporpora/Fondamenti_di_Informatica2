package ticketsosta;

import java.time.*;

public class Ticket {

	private double costo;
	private LocalTime inizio, fine;
	@Override
	public String toString() {
		return "Ticket [costo=" + costo + ", inizio=" + inizio + ", fine=" + fine + "]";
	}
	public Ticket(double costo, LocalTime inizio, LocalTime fine) {
		super();
		this.costo = costo;
		this.inizio = inizio;
		this.fine = fine;
	}
	public double getCosto() {
		return costo;
	}
	public String getCostoAsString() {
		return "Il costo del biglietto è di €" + costo;
	}
	public LocalTime getInizioSosta() {
		return inizio;
	}
	public LocalTime getFineSosta() {
		return fine;
	}	
	private String toStringDuration() {
		
	}
}