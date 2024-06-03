package oroscopo.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import oroscopo.model.Previsione;
import oroscopo.model.SegnoZodiacale;

public class TextFileOroscopoRepository implements OroscopoRepository {

	/*
	AMORE
	avrai la testa un po' altrove				4	ARIETE,TORO,GEMELLI
	divertimento nello stare insieme			8
	chi e' single avra' nuove occasioni			6
	servira' un po' di pazienza					4
	lasciatevi andare alle vostre sensazioni	6	PESCI
	grande intimita'							9
	dedicate piu' tempo al partner				6
	FINE
	...
	 * */
	
	private Map<String, List<Previsione>> data;

	public TextFileOroscopoRepository(Reader baseReader) throws IOException, BadFileFormatException {
		if (baseReader == null)
			throw new NullPointerException("baseReader nullo");

		data = new HashMap<>();

		BufferedReader reader = new BufferedReader(baseReader);
		String sezione = null;
		while ((sezione = reader.readLine()) != null) {
			if (sezione.contains(" ") || sezione.contains("\t")) {
				throw new BadFileFormatException("linea sezione errata");
			}
			data.put(sezione.trim().toLowerCase(), caricaPrevisioni(reader));
		}
	}

	private List<Previsione> caricaPrevisioni(BufferedReader reader) throws IOException, BadFileFormatException {

		List<Previsione> previsioni = new ArrayList<Previsione>();

		try {

			String riga = null;

			while (!(riga = reader.readLine().trim()).equalsIgnoreCase("FINE")) {

				if (riga.isEmpty()) continue;

				String[] tokens = riga.split("\\t+");
				
				if(tokens.length<2 || tokens.length>3 ) throw new BadFileFormatException("Errato numero di elementi in riga: " + riga);
				
				String frase = tokens[0].trim();
				String valoreAsString = tokens[1].trim();
				int valore;
				try {
					valore = Integer.parseInt(valoreAsString );
				}
				catch(NumberFormatException e) {
					throw new BadFileFormatException("Valore numerico non valido in riga: " + riga);
				}
				
				Previsione previsione = null;

				if (tokens.length==3) {
					String allowedSignsToken = tokens[2].trim();
					try {
						String[] signs = allowedSignsToken.split(",");
						Set<SegnoZodiacale> allowedSigns = new HashSet<>();
						for (String sign : signs) {
							allowedSigns.add(SegnoZodiacale.valueOf(sign.trim().toUpperCase()));
						}
						previsione = new Previsione(frase, valore, allowedSigns);
					} catch (Exception e) {
						throw new BadFileFormatException("lista segni contiene elementi errati: " + riga);
					}
				} else {
					previsione = new Previsione(frase, valore);
				}

				previsioni.add(previsione);
			}

		} catch (Exception e) {
			throw new BadFileFormatException(e);
		}

		if (previsioni.size() == 0)
			throw new BadFileFormatException("Nessuna previsione");

		return previsioni;

	}

	@Override
	public List<Previsione> getPrevisioni(String sezione) {
		return data.get(sezione.trim().toLowerCase());
	}

	@Override
	public Set<String> getSettori() {
		return data.keySet();
	}

}
