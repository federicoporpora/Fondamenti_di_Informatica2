package tangenziale.model;

import java.util.Objects;
import java.util.OptionalInt;
import java.util.SortedMap;
import java.util.Map.Entry;


public record Autostrada(Tipologia tipologia, String id, SortedMap<Integer, String> profilo) {

	public static final String TANGENZIALE = "Tangenziale";
	
	public Autostrada {
		Objects.requireNonNull(tipologia, "Tipologia autostrada non può essere nulla");
		Objects.requireNonNull(id, "Identificativo autostrada non può essere nullo");
		Objects.requireNonNull(profilo, "Elenco caselli autostrada non può essere nullo");
		if(id.isBlank()) throw new IllegalArgumentException("Identificativo autostrada non può essere vuoto o blank");
		
		if (profilo.entrySet().stream().map(Entry::getValue).distinct().count() < profilo.size())
			throw new IllegalArgumentException("Profilo con due caselli omonimi: " + profilo);
		
		if (!profilo.keySet().contains(0))
			throw new IllegalArgumentException("Profilo con progressiva km che non parte da zero: " + profilo);		
	}
	
	public int numeroCaselli() {
		return profilo.size();
	}
	
	public OptionalInt progressivaKm(String nomeCasello) {
		return profilo.entrySet().stream().filter(e -> e.getValue().equals(nomeCasello)).mapToInt(Entry::getKey).findFirst();
	}
	
}