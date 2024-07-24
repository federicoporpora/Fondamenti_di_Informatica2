package pacchi.model;

public record Pacco(Territorio territorio, Numero numero, Valore premio) {
	public Pacco {
		if (territorio==null || numero==null || premio==null) 
			throw new IllegalArgumentException("Pacco con argomento illegale");
	}
	
	public static enum Stato { CHIUSO, APERTO };
	
	@Override public String toString() {
		return territorio().nome() + " n° " + numero.valore();
	}
}
