package pacchi.model;

public record Risposta (Valore offerta) {
	
	@Override public String toString() {
		return offerta.toString();
	}
}
