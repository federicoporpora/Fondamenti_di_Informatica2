package tangenziale.model;

import java.util.OptionalInt;
import java.util.Map.Entry;
import java.util.Objects;

public class Tratta {
	
	private OptionalInt kmIniziali, kmFinali;
	private String inizio, fine;
	private Autostrada autostrada;

	public Tratta(String inizio, String fine, Autostrada autostrada) {
		Objects.requireNonNull(inizio, "Tratta: inizio non può essere null");
		Objects.requireNonNull(fine, "Tratta: fine non può essere null");
		Objects.requireNonNull(autostrada, "Tratta: autostrada non può essere null");
		if (inizio.isBlank()) throw new IllegalArgumentException("Tratta: inizio non può essere vuota o blank");
		if (fine.isBlank()) throw new IllegalArgumentException("Tratta: fine non può essere vuota o blank");
		
		this.inizio=inizio;
		this.fine=fine;
		this.autostrada=autostrada;
		
		kmIniziali= OptionalInt.empty(); 
		kmFinali= OptionalInt.empty();
		for (Entry<Integer, String> entry : autostrada.profilo().entrySet()) {
			if (entry.getValue().equals(inizio)) kmIniziali = OptionalInt.of(entry.getKey());
			if (entry.getValue().equals(fine)) kmFinali = OptionalInt.of(entry.getKey());
		}
		if (kmIniziali.isEmpty() || kmFinali.isEmpty()) 
			throw new IllegalArgumentException("Tratta: uno dei due caselli non esiste nell'autostrada specificata");
	}
	
	public int km() {
		return Math.abs(kmIniziali.getAsInt()-kmFinali.getAsInt());
	}
	
	public String inizio() {
		return inizio;
	}
	
	public String fine() {
		return fine;
	}

	public Autostrada autostrada() {
		return autostrada;
	}

	@Override public String toString() {
		return "da " + inizio + " a " + fine + " via " + autostrada.id() + ", km " + km();
	}
}
