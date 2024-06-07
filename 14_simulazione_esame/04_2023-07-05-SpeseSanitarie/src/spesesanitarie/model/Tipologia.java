package spesesanitarie.model;

import java.util.Arrays;
import java.util.Set;
import java.util.Optional;
import java.util.stream.Collectors;

public enum Tipologia {
	FC("Farmaco",		Detraibile.SI),
	DM("Disp. medico",	Detraibile.SI),
	TK("Ticket",		Detraibile.SI),
	LP("Prestaz. medica",	Detraibile.SI),
	AS("Altre spese",	Detraibile.NO);

	private String descrizione;
	private Detraibile detraibile;
	private static Optional<Set<Tipologia>> tipologieDetraibili = Optional.empty(), tipologieNonDetraibili = Optional.empty();

	Tipologia(String descrizione, Detraibile detraibile) {
		this.descrizione=descrizione;
		this.detraibile=detraibile;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public boolean isDetraibile() {
		return detraibile==Detraibile.SI;
	}
	public boolean isNonDetraibile() {
		return detraibile==Detraibile.NO;
	}
	
	@Override public String toString() {
		return this.name() + " - " + this.descrizione;
	}
	
	public static Set<Tipologia> tipologieDetraibili(){
		if (tipologieDetraibili.isEmpty()) tipologieDetraibili = Optional.of(
				Arrays.stream(Tipologia.values()).filter(Tipologia::isDetraibile).collect(Collectors.toSet()) );
		return tipologieDetraibili.get();
	}
	
	public static Set<Tipologia> tipologieNonDetraibili(){
		if (tipologieNonDetraibili.isEmpty()) tipologieNonDetraibili = Optional.of(
				Arrays.stream(Tipologia.values()).filter(Tipologia::isNonDetraibile).collect(Collectors.toSet()) );
		return tipologieNonDetraibili.get();
	}

}
