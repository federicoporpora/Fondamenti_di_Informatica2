package meteodent.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;


public class Previsione {

	private String localita;
	private LocalDateTime dataOra;
	private Temperatura temperatura;
	private ProbPioggia probabilitaPioggia;

	public Previsione(String localita, LocalDate data, LocalTime ora, Temperatura temperatura, ProbPioggia probabilitaPioggia) {
		if(data==null || ora==null || localita==null || temperatura==null || probabilitaPioggia==null) throw new IllegalArgumentException("Argomento nullo in ctor Previsione");
		if (probabilitaPioggia.getValue()<0 || probabilitaPioggia.getValue()>100) throw new IllegalArgumentException("Prob. pioggia dev'essere nel range 0-100 in ctor Previsione");
		this.localita = localita;
		this.dataOra = LocalDateTime.of(data, ora);
		this.temperatura = temperatura;
		this.probabilitaPioggia = probabilitaPioggia;
	}

	public String getLocalita() {
		return localita;
	}

	public LocalDateTime getDataOra() {
		return dataOra;
	}

	public Temperatura getTemperatura() {
		return temperatura;
	}

	public ProbPioggia getProbabilitaPioggia() {
		return probabilitaPioggia;
	}
	
	@Override
	public String toString() {
		return Formatters.datetimeFormatter.format(getDataOra()) + ", " + "Temperatura " + getTemperatura().getValue() + "°, Prob. pioggia " + getProbabilitaPioggia().getValue() + "%";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataOra, localita, probabilitaPioggia, temperatura);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Previsione other = (Previsione) obj;
		return Objects.equals(dataOra, other.dataOra) && Objects.equals(localita, other.localita)
				&& Objects.equals(probabilitaPioggia, other.probabilitaPioggia)
				&& Objects.equals(temperatura, other.temperatura);
	}
	
	
}
