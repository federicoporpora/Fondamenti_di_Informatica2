package mastermind.model;

import java.util.Random;

public class MasterMind {

	private Combinazione segreta;
	
	public MasterMind(int lunghezzaCodice) {
		segreta = new Combinazione(lunghezzaCodice);
		sorteggiaCombinazione(segreta);
	}
	public Risposta calcolaRisposta(Combinazione tentativo) {
		int nPioliNeri = 0, nPioli = 0;
		for (int i = 0; i < segreta.dim(); i++) {
			if (tentativo.getPiolo(i).ordinal() == segreta.getPiolo(i).ordinal()) nPioliNeri++;
		}
		int[] occorrenzeTentativo = occorrenze(tentativo);
		int[] occorrenzeSegreta = occorrenze(segreta);
		for (int i = 0; i < occorrenzeTentativo.length; i++) {
			nPioli += (occorrenzeTentativo[i] < occorrenzeSegreta[i]) ? occorrenzeTentativo[i] : occorrenzeSegreta[i];
		}
		Risposta res = new Risposta(segreta.dim());
		int nCicli = nPioli;
		for (int i = 0; i < nCicli; i++) {
			if (nPioliNeri != 0) {
				res.setPiolo(i, PioloRisposta.NERO);
				nPioliNeri--;
				nPioli--;
			} else if (nPioli != 0) {
				res.setPiolo(i, PioloRisposta.BIANCO);
				nPioli--;
			}
		}
		return res;
	}
	public String combinazioneSegreta() { return segreta.toString(); }
	private int[] occorrenze(Combinazione tentativo) {
		int[] occorrenze = new int[PioloDiGioco.values().length];
		for (int i = 0; i < tentativo.dim(); i++) {
			occorrenze[tentativo.getPiolo(i).ordinal()]++;
		}
		return occorrenze;
	}
	protected void sorteggiaCombinazione(Combinazione segreta) {
		Random rand = new Random();
		for (int i = 0; i < segreta.dim(); i++) {
			segreta.setPiolo(i, PioloDiGioco.values()[rand.nextInt(1, PioloDiGioco.values().length)]);
		}
	}
}