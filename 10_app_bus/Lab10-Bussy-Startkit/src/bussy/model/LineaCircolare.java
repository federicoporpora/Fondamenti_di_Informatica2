package bussy.model;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class LineaCircolare extends Linea {
	public LineaCircolare(String id, Map<Integer, Fermata> orariPassaggioAlleFermate) {
		super(id, orariPassaggioAlleFermate);
		if (!this.isCircolare()) throw new IllegalArgumentException("la linea che doveva essere circolare in realtà è da punto a punto");
	}
	@Override
	public Optional<Percorso> getPercorso(String fermataDa, String fermataA) {
		boolean fermataDaPresent = false, fermataAPresent = false;
		Set<Map.Entry<Integer, Fermata>> righe = this.getOrariPassaggioAlleFermate().entrySet();
		for (Map.Entry<Integer, Fermata> riga : righe) {
			if (riga.getValue().getNome().equals(fermataDa)) fermataDaPresent = true;
			if (riga.getValue().getNome().equals(fermataA)) fermataAPresent = true;
		}
		if (!fermataDaPresent || !fermataAPresent) return Optional.empty();
		if (isCapolinea(fermataDa) && !isCapolinea(fermataA)) return Optional.of(new Percorso(fermataDa, fermataA, this, this.getOrarioPassaggioAllaFermata(fermataA)));
		if (!isCapolinea(fermataDa) && isCapolinea(fermataA)) return Optional.of(new Percorso(fermataDa, fermataA, this, this.getOrarioPassaggioAllaFermata(fermataA) - this.getOrarioPassaggioAllaFermata(fermataDa)));
		if (isCapolinea(fermataDa) && isCapolinea(fermataA)) return Optional.of(new Percorso(fermataDa, fermataA, this, this.getOrarioPassaggioAllaFermata(fermataA)));
		if (!isCapolinea(fermataDa) && !isCapolinea(fermataA) && this.getOrarioPassaggioAllaFermata(fermataDa) < this.getOrarioPassaggioAllaFermata(fermataA))
			return Optional.of(new Percorso(fermataDa, fermataA, this, this.getOrarioPassaggioAllaFermata(fermataA) - this.getOrarioPassaggioAllaFermata(fermataDa)));
		else {
			return Optional.of(new Percorso(fermataDa, fermataA, this, this.getOrarioPassaggioAllaFermata(this.getCapolineaFinale().getValue().getNome()) - (this.getOrarioPassaggioAllaFermata(fermataDa) - this.getOrarioPassaggioAllaFermata(fermataA))));
		}
	}
	private boolean isCapolinea(String fermata) {
		if (this.getCapolineaIniziale().getValue().getId().equals(fermata)) return true;
		return false;
	}
}
