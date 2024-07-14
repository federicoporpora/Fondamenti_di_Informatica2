package cityparking.model;

import java.time.Duration;

public class Tariffa {
	private Duration durataUnita;
	private double costoIniziale, costoUnitaSuccessive, capGiornaliero, oltre;

	public Tariffa(Duration durataUnita, 
					double costoIniziale, double costoUnitaSuccessive, double capGiornaliero,
					double oltre) {
		if(durataUnita==null || durataUnita.toMinutes()<=0) throw new IllegalArgumentException("Durata illegale in tariffa:" + durataUnita);
		if(costoIniziale<0 || !Double.isFinite(costoIniziale)) throw new IllegalArgumentException("Costo iniziale illegale in tariffa:" + costoIniziale);
		if(costoUnitaSuccessive<0 || !Double.isFinite(costoUnitaSuccessive)) throw new IllegalArgumentException("Costo unità successive illegale in tariffa:" + costoUnitaSuccessive);
		if(capGiornaliero<0 || !Double.isFinite(capGiornaliero)) throw new IllegalArgumentException("Cap giornaliero illegale in tariffa:" + capGiornaliero);
		if(oltre<0 || !Double.isFinite(oltre)) throw new IllegalArgumentException("Costo oltre le prime 24h illegale in tariffa:" + oltre);
		this.durataUnita = durataUnita;
		this.costoIniziale = costoIniziale;
		this.costoUnitaSuccessive = costoUnitaSuccessive;
		this.capGiornaliero = capGiornaliero;
		this.oltre = oltre;
	}
	
	public Duration getDurataUnita() {
		return durataUnita;
	}
	
	public double getCostoIniziale() {
		return costoIniziale;
	}
	
	public double getCostoUnitaSuccessive() {
		return costoUnitaSuccessive;
	}
	
	public double getCapGiornaliero() {
		return capGiornaliero;
	}
	
	public double getOltre() {
		return oltre;
	}

	@Override
	public String toString() {
		return "Tariffa [durataUnita=" + durataUnita + ", costoIniziale=" + costoIniziale + ", costoUnitaSuccessive="
				+ costoUnitaSuccessive + ", capGiornaliero=" + capGiornaliero + ", oltre=" + oltre + "]";
	}
	
}
