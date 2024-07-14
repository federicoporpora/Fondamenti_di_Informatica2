package speedcollege.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Attualizzatore {
	
	// **** CLASSE DA COMPLETARE ***************************************
	// **** DA FARE: 
	// ****     1) la funzione statica UN_VOTO_IN_MENO_AL_MESE qui di seguito definita
	// ****     2) il metodo mediaPesata (in fondo)
	// *****************************************************************
	
	private Carriera carriera;
	private BiFunction<Esame,LocalDate,Double> f = null;
	private LocalDate d;
	
	public static BiFunction<Esame,LocalDate,Double> NESSUN_DECADIMENTO =
			(esame, localdate) -> esame.getVotoIniziale().getValue().getAsInt()*(1.0);
			
	public static BiFunction<Esame,LocalDate,Double> UN_VOTO_IN_MENO_AL_MESE =
			(esame, localdate) -> {
				var period = Period.between(esame.getDate(), localdate);
				int diffGiorni = Math.max(0, period.getYears()*12 + period.getMonths())*30+period.getDays();
				double decadimento = diffGiorni / 30;
				double votoAttualizzato = esame.getVotoIniziale().getValue().getAsInt() - decadimento;
				return votoAttualizzato < 18 ? 18 : votoAttualizzato;
			};
			
	public static BiFunction<Esame,LocalDate,Double> MEZZO_VOTO_IN_MENO_OGNI_10_GG =
			(esame, localdate) -> {
				var period = Period.between(esame.getDate(), localdate);
				int diffGiorni = Math.max(0,(period.getYears()*12 + period.getMonths())*30+period.getDays());
				double decadimento = 0.5*(diffGiorni/10);
				double votoAttualizzato = esame.getVotoIniziale().getValue().getAsInt()-decadimento; 
				return votoAttualizzato<18 ? 18 : votoAttualizzato;
			};
					
	public static Map<String,BiFunction<Esame,LocalDate,Double>> elencoFunzioni() {
		return Map.of(
				"Nessun decadimento", NESSUN_DECADIMENTO,
				"-1 voto al mese", UN_VOTO_IN_MENO_AL_MESE,
				"-1/2 voto ogni 10 gg", MEZZO_VOTO_IN_MENO_OGNI_10_GG		
				);
	}
			
	//----------------------------------------

	public Attualizzatore(Carriera carriera, BiFunction<Esame,LocalDate,Double> f, LocalDate d) {
		this.carriera = carriera;
		this.f = f;
		this.d = d;
	}

	public Carriera getCarriera() {
		return carriera;
	}

	public void setCarriera(Carriera carriera) {
		this.carriera = carriera;
	}

	public BiFunction<Esame,LocalDate,Double> getFunction() {
		return f;
	}

	public void setFunction(BiFunction<Esame,LocalDate,Double> f) {
		this.f = f;
	}
	
	public LocalDate getDataDiAttualizzazione() {
		return d;
	}

	public void setDataDiAttualizzazione(LocalDate d) {
		this.d = d;
	}
	
	public double mediaPesata() {
		double numeratore = 0;
		for (Esame esame : carriera.getListaEsami()) if (esame.getVotoIniziale().getValue().isPresent()) numeratore += votoPesato(esame);
		return numeratore / this.creditiAcquisitiDaVoto();
	}

	private double votoPesato(Esame esame) {
		// può essere utile...
		return f.apply(esame,d)*esame.getAf().getCfu();
	}
	
	private String toStringVotoPesato(Esame e) {
		if (e.getVotoIniziale().ordinal()<Voto.DICIOTTO.ordinal()) return e.toString();
		else
		return e.toStringSenzaVoto() + "\t" + Math.rint( f.apply(e,d) * 100) / 100;
	}

	private double creditiAcquisitiDaVoto() {
		// può essere utile...
		return carriera.getListaEsami().stream()
				.filter(e -> e.getVotoIniziale().getValue().isPresent())
				.map(Esame::getAf)
				.mapToDouble(AttivitaFormativa::getCfu)
				.sum();
	}
	
	@Override
	public String toString() {
		return carriera.getListaEsami().stream().map(this::toStringVotoPesato).collect(Collectors.joining(System.lineSeparator()));
	}
	
}
