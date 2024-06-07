package meteodent.model;

import java.time.LocalDate;

public class Bollettino {

	private LocalDate giorno;
	private String localita;
	private int probabilitaPioggia, temperatura;
	private String testo;

	public Bollettino(LocalDate giorno, String localita, int probabilitaPioggia, int temperatura, String testo) {
		if(giorno==null || localita==null || testo==null) throw new IllegalArgumentException("Argomento nullo in ctor Bollettino");
		if (probabilitaPioggia<0 || probabilitaPioggia>100) throw new IllegalArgumentException("Prob. pioggia dev'essere nel range 0-100 in ctor Bollettino");
		this.giorno = giorno;
		this.localita = localita;
		this.probabilitaPioggia = probabilitaPioggia;
		this.temperatura = temperatura;
		this.testo = testo;
	}

	public LocalDate getGiorno() {
		return giorno;
	}

	public String getLocalita() {
		return localita;
	}
	
	public int getProbabilitaPioggia() {
		return probabilitaPioggia;
	}
	
	public int getTemperatura() {
		return temperatura;
	}
	
	public String getTesto() {
		return testo;
	}
	
	public String toString() {
		return "Previsioni per il giorno " + Formatters.dateFormatter.format(giorno) + " a " + localita + "\n" + getTesto();
	}

}
