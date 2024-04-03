package mastermind.model;

import java.util.StringJoiner;

public class Risposta {

	private PioloRisposta[] risposta;
	private int dim;	
	
	public Risposta(int dim) {
		risposta = new PioloRisposta[dim];
		for (int i = 0; i < dim; i++) {
			risposta[i] = PioloRisposta.VUOTO;
		}
		this.dim = dim;
	}
	public int dim() { return dim; }
	public boolean equals(Risposta that) {
		if (this.dim() != that.dim()) { return false; }
		for (int i = 0; i < dim(); i++) {
			if (this.risposta[i] != that.risposta[i]) { return false; }
		}
		return true;
	}
	public PioloRisposta getPiolo(int index) { return risposta[index]; }
	public void setPiolo(int index, PioloRisposta pr) { risposta[index] = pr; }
	public boolean vittoria() {
		for (PioloRisposta piolo : risposta) {
			if (piolo != PioloRisposta.NERO) { return false; }
		}
		return true;
	}
	public String toString() {
		StringJoiner res = new StringJoiner(",");
		for (PioloRisposta piolo : risposta) {
			res.add(piolo.toString());
		}
		return res.toString();
	}
}
