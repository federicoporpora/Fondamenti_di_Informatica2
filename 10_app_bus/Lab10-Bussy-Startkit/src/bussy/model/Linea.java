package bussy.model;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public abstract class Linea {

	private String id;
	private Map<Integer, Fermata> orariPassaggioAlleFermate;
	
	public Linea(String id, Map<Integer, Fermata> orariPassaggioAlleFermate) {
		if (id.equals("")) throw new IllegalArgumentException("l'id della linea è vuoto");
		if (orariPassaggioAlleFermate.isEmpty()) throw new IllegalArgumentException("la mappa è vuota");
		if (id.equals(null)) throw new NullPointerException("l'id della linea è null");
		if (orariPassaggioAlleFermate.equals(null)) throw new NullPointerException("la mappa è null");
		this.id = id;
		this.orariPassaggioAlleFermate = orariPassaggioAlleFermate;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, orariPassaggioAlleFermate);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Linea other = (Linea) obj;
		return Objects.equals(id, other.id)
				&& Objects.equals(orariPassaggioAlleFermate, other.orariPassaggioAlleFermate);
	}
	public String getId() {
		return id;
	}
	public Map<Integer, Fermata> getOrariPassaggioAlleFermate() {
		return orariPassaggioAlleFermate;
	}
	public int getOrarioPassaggioAllaFermata(String nomeFermata) {
		Set<Map.Entry<Integer, Fermata>> righe = orariPassaggioAlleFermate.entrySet();
		for (Map.Entry<Integer, Fermata> riga : righe) {
			if (riga.getValue().getNome().equals(nomeFermata)) return riga.getKey();
		}
		throw new IllegalArgumentException("La fermata non esiste");
	}
	public Entry<Integer, Fermata> getCapolineaIniziale() {
		Set<Map.Entry<Integer, Fermata>> righe = orariPassaggioAlleFermate.entrySet();
		for (Map.Entry<Integer, Fermata> riga: righe) {
			if (riga.getKey().equals(0)) return riga;
		}
		return null;
	}
	public Entry<Integer, Fermata> getCapolineaFinale() {
		Set<Map.Entry<Integer, Fermata>> righe = orariPassaggioAlleFermate.entrySet();
		int minMax = 0;
		for (Map.Entry<Integer, Fermata> riga: righe) {
			if (riga.getKey().compareTo(minMax) > 0) minMax = riga.getKey();
		}
		for (Map.Entry<Integer, Fermata> riga : righe) {
			if (riga.getKey().compareTo(minMax) == 0) return riga;
		}
		return null;		
	}
	public boolean isCapolineaIniziale(String fermata) {
		if (this.getCapolineaIniziale().getValue().getNome().equals(fermata)) return true;
		return false;
	}
	public boolean isCapolineaFinale(String fermata) {
		if (this.getCapolineaFinale().getValue().getNome().equals(fermata)) return true;
		return false;
	}
	public boolean isCircolare() {
		if (getCapolineaIniziale().getValue().equals(getCapolineaFinale().getValue())) return true;
		return false;
	}
	@Override
	public String toString() {
		return "Linea [id=" + id + ", orariPassaggioAlleFermate=" + orariPassaggioAlleFermate + "]";
	}
	public abstract Optional<Percorso> getPercorso(String fermataDa, String fermataA);
}