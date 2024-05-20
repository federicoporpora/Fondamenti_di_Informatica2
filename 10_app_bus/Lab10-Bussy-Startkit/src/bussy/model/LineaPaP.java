package bussy.model;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class LineaPaP extends Linea {

	public LineaPaP(String id, Map<Integer, Fermata> orariPassaggioAlleFermate) {
		super(id, orariPassaggioAlleFermate);
		if (this.isCircolare()) throw new IllegalArgumentException("la linea che doveva essere da punto a punto in realtà è circolare");
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
		if (this.getOrarioPassaggioAllaFermata(fermataDa) > this.getOrarioPassaggioAllaFermata(fermataA)) return Optional.empty();
		return Optional.of(new Percorso(fermataDa, fermataA, this, this.getOrarioPassaggioAllaFermata(fermataA) - this.getOrarioPassaggioAllaFermata(fermataDa)));
	}

}