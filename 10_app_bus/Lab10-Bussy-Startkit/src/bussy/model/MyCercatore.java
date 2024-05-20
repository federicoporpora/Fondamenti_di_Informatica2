package bussy.model;

import java.util.Map;
import java.util.Map.Entry;
import java.util.OptionalInt;
import java.util.SortedSet;
import java.util.TreeSet;

public class MyCercatore implements Cercatore {

	private Map<String, Linea> mappaLinee;
	
	public MyCercatore(Map<String, Linea> mappaLinee){
		if (mappaLinee==null) throw new NullPointerException("mappa linee nulla");
		if (mappaLinee.isEmpty()) throw new IllegalArgumentException("mappa linee vuota");
		this.mappaLinee = mappaLinee;
	}

	@Override
	public SortedSet<Percorso> cercaPercorsi(String fermataDa, String fermataA, OptionalInt durataMax) {
		if (fermataDa == null || fermataA == null) throw new NullPointerException("le stringhe delle fermate sono null");
		if (durataMax == null) throw new NullPointerException("la duratamax è nulla");
		if (fermataDa.isEmpty() || fermataA.isEmpty()) throw new IllegalArgumentException("le stringhe delle fermate sono vuote");
		SortedSet<Percorso> tuttiPercorsiPossibili = new TreeSet<>();
		for (Entry<String, Linea> riga: mappaLinee.entrySet()) {
			if (riga.getValue().getPercorso(fermataDa, fermataA).isPresent()) {
				if (riga.getValue().getPercorso(fermataDa, fermataA).get().getDurata() > 0) tuttiPercorsiPossibili.add(riga.getValue().getPercorso(fermataDa, fermataA).get());
			}
		}
		SortedSet<Percorso> percorsiDirettiNonPiuLunghiDiTot;
		if (durataMax.isPresent()) {
			percorsiDirettiNonPiuLunghiDiTot = new TreeSet<Percorso>();
			for (Percorso percorso : tuttiPercorsiPossibili) {
				if (percorso.getDurata()<=durataMax.getAsInt()) {
					percorsiDirettiNonPiuLunghiDiTot.add(percorso);
				}
			}
		} else {
			percorsiDirettiNonPiuLunghiDiTot = tuttiPercorsiPossibili;
		}
		return tuttiPercorsiPossibili;
	}

	@Override
	public Map<String, Linea> getMappaLinee() {
		return mappaLinee;
	}

}
