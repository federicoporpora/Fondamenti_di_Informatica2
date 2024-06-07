package meteodent.persistence;

import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import meteodent.model.Formatters;
import meteodent.model.Previsione;
import meteodent.model.ProbPioggia;
import meteodent.model.Temperatura;

public class MyPrevisioniReader implements PrevisioniReader {
	
	private static final int    TOKEN_COUNT = 5;
	private static final String SEPARATOR = ",";
	private static final String DEGREE_SYMBOL = "°";
	private static final String PERCENT_SYMBOL = "%";
	
//	Bologna, 12/07/22, 02:00, 18°, 91%
//	Bologna, 12/07/22, 05:00, 16°, 92%
//	Bologna, 12/07/22, 08:00, 20°, 87%
//	Bologna, 12/07/22, 11:00, 24°, 64%
//	Ferrara, 18/07/22, 05:00, 18°, 91%
//	Ferrara, 18/07/22, 15:00, 16°, 92%
//	Ferrara, 18/07/22, 22:00, 20°, 87%
//	Reggio Emilia, 11/07/22, 02:00, -1°, 21%
//	Reggio Emilia, 11/07/22, 10:00, -1°, 32%
//	Reggio Emilia, 11/07/22, 18:00, 6°, 50%
	
	@Override
	public Map<String, SortedSet<Previsione>> leggiPrevisioni(Reader reader) throws IOException, BadFileFormatException {
		// Si consiglia di incapsulare nel metodo privato leggiSingolaPrevisione (sotto)
		// la logica di lettura della singola previsione per una data città, giorno e ora
		// In tal modo, questo metodo dovrà concentrarsi solo sulla logica generale
		if (reader == null) throw new IllegalArgumentException("il reader è null");
		var bf = new BufferedReader(reader);
		var map = new HashMap<String, SortedSet<Previsione>>();
		Previsione previsione;
		while ((previsione = leggiSingolaPrevisione(bf)) != null) {
			if (map.containsKey(previsione.getLocalita())) {
				map.get(previsione.getLocalita()).add(previsione);
			} else {
				var nuovePrevisioni = new TreeSet<Previsione>(Comparator.comparing(Previsione::getDataOra));
				nuovePrevisioni.add(previsione);
				map.put(previsione.getLocalita(), nuovePrevisioni);
			}
		}
		return map;
	}

	private Previsione leggiSingolaPrevisione(BufferedReader reader) throws IOException, BadFileFormatException {
		String riga;
		if ((riga = reader.readLine()) == null) return null;
		String[] tokens = riga.split(SEPARATOR);
		if (tokens.length != TOKEN_COUNT) {
			throw new BadFileFormatException("il numero di token è sbagliato (" + tokens.length + ")");
		}
		String localita = tokens[0];
		if (localita.isEmpty()) throw new BadFileFormatException("localita vuota");
		LocalDate data;
		try {
			data = LocalDate.parse(tokens[1].trim(), Formatters.dateFormatter);
		} catch (DateTimeParseException e) {
			throw new BadFileFormatException("errore nella parse della data");
		}
		LocalTime ora;
		try {
			ora = LocalTime.parse(tokens[2].trim(), Formatters.datetimeFormatter);
		} catch (DateTimeParseException e) {
			throw new BadFileFormatException("errore nella parse dell'orario");
		}
		int temperatura;
		try {
			temperatura = Integer.parseInt(tokens[3].trim().replaceAll(DEGREE_SYMBOL, ""));
		} catch (NumberFormatException e) {
			throw new BadFileFormatException("problema nel parse della temperatura");
		}
		int probabilitaPioggia;
		try {
			probabilitaPioggia = Integer.parseInt(tokens[4].trim().replaceAll(PERCENT_SYMBOL, ""));
		} catch (NumberFormatException e) {
			throw new BadFileFormatException("problema nel parse della pioggia");
		}
		
		return new Previsione(localita, data, ora, Temperatura.of(temperatura), ProbPioggia.of(probabilitaPioggia));
	}

}
