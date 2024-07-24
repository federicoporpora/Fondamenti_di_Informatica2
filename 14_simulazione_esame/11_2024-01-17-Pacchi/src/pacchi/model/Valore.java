package pacchi.model;

public record Valore(int valore) {
	public Valore {
		if (valore<0) throw new IllegalArgumentException("Il premio non può essere un valore negativo.");
	}
	
	@Override public String toString() {
		return Formatters.priceFormatter.format(valore());
	}
}
