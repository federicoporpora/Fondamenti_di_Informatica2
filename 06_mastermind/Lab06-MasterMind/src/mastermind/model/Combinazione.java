package mastermind.model;

import java.util.StringJoiner;

public class Combinazione {

	private PioloDiGioco[] combinazione;
	private int dim;	
	
	public Combinazione(int dim) {
		combinazione = new PioloDiGioco[dim];
		for (int i = 0; i < dim; i++) {
			combinazione[i] = PioloDiGioco.VUOTO;
		}
		this.dim = dim;
	}
	public int dim() { return dim; }
	public boolean equals(Combinazione that) {
		if (this.dim() != that.dim()) { return false; }
		for (int i = 0; i < dim(); i++) {
			if (this.combinazione[i] != that.combinazione[i]) { return false; }
		}
		return true;
	}
	public PioloDiGioco getPiolo(int index) { return combinazione[index]; }
	public void setPiolo(int index, PioloDiGioco c) { combinazione[index] = c; }
	public String toString() {
		StringJoiner res = new StringJoiner(",");
		for (PioloDiGioco piolo : combinazione) {
			res.add(piolo.toString());
		}
		return res.toString();
	}
}