package mastermind.model;

import java.util.StringJoiner;

public class Gioco {

	private int dim, maxTentativi, numTentativi;
	private Combinazione[] tentativi;
	private Risposta[] risposte;
	private Status status;
	
	public Gioco(int maxTentativi, int dim) {
		this.maxTentativi = maxTentativi;
		this.dim = dim;
		this.numTentativi = 0;
		tentativi = new Combinazione[maxTentativi];
		risposte = new Risposta[maxTentativi];
		status = Status.IN_CORSO;
	}
	public int dimensione() { return dim; }
	public int maxTentativi() { return maxTentativi; }
	public int tentativiEffettuati() { return numTentativi; }
	public int tentativiRimasti() { return maxTentativi - numTentativi; }
	public Status stato() { return status; }
	public Risposta risposta(int index) { return risposte[index]; }
	public Combinazione tentativo(int index) { return tentativi[index]; }
	public String situazione() {
		StringJoiner res = new StringJoiner(System.lineSeparator());
		for (int i = 0; i < tentativiEffettuati(); i++) {
			String riga = Integer.toString(i + 1) + ") ";
			riga += tentativi[i].toString();
			if (tentativi[i].toString().length() < 20) { riga += "\t\t\t"; }
			else { riga += "\t\t"; }
			riga += risposte[i].toString();
			res.add(riga);
		}
		return res.toString();
	}
	public Status registraMossa(Combinazione tentativo, Risposta risposta) {
		numTentativi++;
		tentativi[numTentativi - 1] = tentativo;
		risposte[numTentativi - 1] = risposta;
		if (this.tentativiRimasti() == 0) { status = Status.PERSO; return status; }
		for (int i = 0; i < risposta.dim(); i++) {
			if (risposta.getPiolo(i) != PioloRisposta.NERO) break;
			if (i == risposta.dim() - 1) status = Status.VITTORIA;
		}
		return status;
	}
	public Risposta ultimaRisposta() {
		if (numTentativi == 0) return null;
		return risposte[numTentativi - 1];
	}
	public Combinazione ultimoTentativo() {
		if (numTentativi == 0) return null;
		return tentativi[numTentativi - 1];
	}
	public String toString() {
		return "Situazione:" + System.lineSeparator() + this.situazione() + System.lineSeparator() + "Gioco: " + status.toString();
	}
}
