package meteodent.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class MyGeneratoreBollettino implements GeneratoreBollettino {

	@Override
	public Bollettino generaBollettino(List<Previsione> previsioni, TipoBollettino tipoBollettino) {
		if (previsioni == null) throw new IllegalArgumentException("la lista è nulla");
		if (previsioni.isEmpty()) throw new IllegalArgumentException("la lista è vuota");
		for (int i = 0; i < previsioni.size(); i++) {
			for (int j = 0; j < previsioni.size(); j++) {
				if (i != j && previsioni.get(i).getDataOra().isEqual(previsioni.get(j).getDataOra()))
					throw new IllegalArgumentException("non possono esserci due previsioni con lo stesso orario");
			}
		}
		if (!previsioni.getFirst().getDataOra().isEqual(previsioni.getFirst().getDataOra().withHour(0).withMinute(0))) {
			previsioni.addFirst(new Previsione(previsioni.getFirst().getLocalita(), previsioni.getFirst().getDataOra().toLocalDate(),
					LocalTime.of(0, 0), previsioni.getFirst().getTemperatura(), previsioni.getFirst().getProbabilitaPioggia()));
		}
		if (!previsioni.getLast().getDataOra().isEqual(previsioni.getLast().getDataOra().withHour(23).withMinute(59))) {
			previsioni.addLast(new Previsione(previsioni.getLast().getLocalita(), previsioni.getLast().getDataOra().toLocalDate(),
					LocalTime.of(23, 59), previsioni.getLast().getTemperatura(), previsioni.getLast().getProbabilitaPioggia()));
		}
		double probPioggia = 0.0, mediaTemp = 0.0;
		
		for (int i = 1; i < previsioni.size(); i++) {
			if (!previsioni.get(i).getLocalita().equals(previsioni.getFirst().getLocalita()))
				throw new IllegalArgumentException("la " + (i + 1) + "a previsione ha la località sbagliata");
			
			if (!previsioni.get(i).getDataOra().toLocalDate().equals(previsioni.getFirst().getDataOra().toLocalDate()))
				throw new IllegalArgumentException("la " + (i + 1) + "a previsione ha un'ora sbagliata");
			
			probPioggia += ((previsioni.get(i-1).getProbabilitaPioggia().getValue() + previsioni.get(i).getProbabilitaPioggia().getValue())/2.0)
					* Duration.between(previsioni.get(i-1).getDataOra().toLocalTime(), previsioni.get(i).getDataOra().toLocalTime()).toMinutes();
			mediaTemp += ((previsioni.get(i-1).getTemperatura().getValue() + previsioni.get(i).getTemperatura().getValue())/2.0)
					* Duration.between(previsioni.get(i-1).getDataOra().toLocalTime(), previsioni.get(i).getDataOra().toLocalTime()).toMinutes();
		}
		
		probPioggia /= Duration.between(LocalTime.of(0, 0), LocalTime.of(23, 59)).toMinutes();
		mediaTemp /= Duration.between(LocalTime.of(0, 0), LocalTime.of(23, 59)).toMinutes();
		
		String testo = 
				(tipoBollettino==TipoBollettino.DETTAGLIATO ?
						previsioni.stream().map(Previsione::toString).collect(Collectors.joining("\n","","\n")) : "")
				+
				SintesiGiornata.getTestoAnnuncio((int)Math.round(probPioggia))
				+
				"\ne temperatura media di " + (int)Math.round(mediaTemp) + "°C";
				;
		
		return new Bollettino(previsioni.getFirst().getDataOra().toLocalDate(), previsioni.getFirst().getLocalita(), (int)Math.round(probPioggia), (int)Math.round(mediaTemp), testo);
	}
	
}
