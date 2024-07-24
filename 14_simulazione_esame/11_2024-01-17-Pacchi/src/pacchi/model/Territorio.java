package pacchi.model;

public record Territorio(String nome) {
	public Territorio {
		if (nome==null || nome.isBlank()) throw new IllegalArgumentException("Il nome del territorio non può essere nullo o vuoto");
	}
}
