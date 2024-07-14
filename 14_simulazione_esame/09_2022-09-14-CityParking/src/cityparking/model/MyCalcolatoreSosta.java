package cityparking.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class MyCalcolatoreSosta extends CalcolatoreSosta {

	private final Duration ventiquattroOre = Duration.ofHours(24);
	private final Duration dodiciOre = Duration.ofHours(12);
	
	public MyCalcolatoreSosta(Tariffa tariffa) {
		super(tariffa);
	}

	@Override
	public Ricevuta calcola(LocalDateTime inizio, LocalDateTime fine) throws BadTimeIntervalException {
		Duration durataSosta = Duration.between(inizio, fine);
		if (durataSosta.isNegative()) throw new BadTimeIntervalException("L'orario di fine sosta precede quello di inizio sosta");
		double costo = 0;
		// calcolo eventuale costo extra le prime 24h 
		if (durataSosta.compareTo(ventiquattroOre)>0){
			Duration extra24h = durataSosta.minus(ventiquattroOre); 
			do {
				costo += this.getTariffa().getOltre();
				extra24h = extra24h.minus(dodiciOre);
			} while(!extra24h.isNegative() && !extra24h.isZero());
			durataSosta = ventiquattroOre; // durata residua da tariffare
		}
		// calcolo costo entro le prime 24h
		// NB: il calcolo del numero di unità va arrotondato per eccesso!
		long numeroUnita = (long) Math.ceil(durataSosta.toMinutes()*1.0/this.getTariffa().getDurataUnita().toMinutes());
		double costoTeorico = this.getTariffa().getCostoUnitaSuccessive() * (numeroUnita-1) + this.getTariffa().getCostoIniziale();
		double costoReale = costoTeorico > this.getTariffa().getCapGiornaliero() ? this.getTariffa().getCapGiornaliero() : costoTeorico;
		costo += costoReale;
		return new Ricevuta(inizio, fine, costo);
	}

}
