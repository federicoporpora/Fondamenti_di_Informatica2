package pacchi.model;

import java.util.Random;

public class Dottore {

	private StatoPartita statoPartita;
	
	public Dottore(StatoPartita statoPartita) {
		this.statoPartita = statoPartita;
	}
	
	public Risposta interpella() {
		return new Risposta(new Valore(new Random().nextInt(media() / 4, media())));
	}

	public StatoPartita getStatoPartita() {
		return statoPartita;
	}
	
	public int media() {
		double media = statoPartita.getPacchiDaAprire().stream().map(Pacco::premio).mapToDouble(Valore::valore).average().getAsDouble();
		media = (media * statoPartita.getPacchiDaAprire().size() + statoPartita.getPaccoConcorrente().premio().valore()) / (statoPartita.getPacchiDaAprire().size()+1);
		return (int) Math.round(media);
	}
			
}
