package pacchi.persistence;

import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import pacchi.model.Territorio;
import pacchi.model.Formatters;
import pacchi.model.Valore;
import java.io.BufferedReader;


public class MyConfigReader implements ConfigReader {
	
	@Override
	public Set<Territorio> leggiTerritori(Reader reader) throws IOException, BadFileFormatException {
		if (reader==null) throw new IllegalArgumentException("Input reader null in leggiTerritori");
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line;
		var territori = new HashSet<Territorio>(); 
		while ((line = bufferedReader.readLine()) != null) {
			territori.add(new Territorio(line.trim()));
		}
		return territori;
	}

	@Override
	public Set<Valore> leggiPremi(Reader reader) throws IOException, BadFileFormatException {
		if (reader == null) throw new IllegalArgumentException("reader null");
		var bf = new BufferedReader(reader);
		String riga;
		Set<Valore> res = new HashSet<Valore>();
		for (int i = 0; i < 2; i++) {
			riga = bf.readLine();
			if (riga == null) throw new BadFileFormatException("fine del file troppo presto");
			if (i == 0) res.addAll(elaboraRiga(riga, ", ", "PREMI BASSI: "));
			else {
				if (elaboraRiga(riga, ", ", "PREMI ALTI: ").size() == res.size()) res.addAll(elaboraRiga(riga, ", ", "PREMI ALTI: "));
				else throw new BadFileFormatException("il numero di premi bassi è diverso dal numero di premi alti");
			}
			
		}
		return res;
		// ***************
		// ****DA FARE****
		// - legge i dati del file Premi.txt, delegando al metodo privato ausiliario elaboraRiga il parsing della singola riga-premi;
		//   da specifica, per prima ci dev'essere la riga coi PREMI ALTI, per seconda quella coi PREMI BASSI
		// - restituisce il Set dei Valori letti complessivamente dalle due righe
		// - lancia IllegalArgumentException con opportuno messaggio d’errore SOLO in caso di argomento (reader) nullo,
		//   altrimenti lancia BadFileFormatException con messaggio d’errore appropriato in caso di problemi nel formato del file 
		//   (quali ad esempio mancanza/eccesso di elementi, errori nel formato dei numeri, etc.), ivi incluso il caso in cui premi 
		//   alti e premi bassi non siano presenti in egual quantità.
		//   Lascia fluire in esterno IOException in caso di altri problemi di I/O.
		// ***************
	}
	//PREMI BASSI: 0, 1, 5, 10, 20, 50, 75, 100, 200, 500
	//PREMI ALTI: 5.000, 10.000, 15.000, 20.000, 30.000, ..., 200.000, 300.000
	private Set<Valore> elaboraRiga(String rigaPremi, String separatore, String header) throws BadFileFormatException {
		Set<Valore> res = new HashSet<Valore>();
		String[] headerResto = rigaPremi.split(header);
		if (headerResto.length != 2) throw new BadFileFormatException("non trovato l'header della riga " + rigaPremi +
				", l'header doveva essere " + header + " ma la lunghezza dei tokens è di " + headerResto.length);
		String[] premi = headerResto[1].split(separatore);
		int i = 0;
		try {
			for (i = 0; i < premi.length; i++) {
				res.add(new Valore(Integer.parseInt((premi[i].replace(".", "")).trim())));
			}
		} catch (NumberFormatException e) {
			throw new BadFileFormatException("PROBLEMI PROBLEMI PROBLEMI PARSING " + premi[i]);
		} catch (IllegalArgumentException e) {
			throw new BadFileFormatException("il numero è negativo");
		}
		return res;
		// ***************
		// ****DA FARE****
		// - elabora la singola riga premi (letta in precedenza dal metodo leggiPremi) e ne fa il parsing utilizzando
		//   come separatore dei valori la stringa separatore ricevuta come argomento, e come intestazione della riga
		//   la stringa header ricevuta come terzo argomento
		// - lancia BadFileFormatException con idoneo messaggio d’errore nel caso di numero di elementi errato o di
		//   header errato.
		// ***************
	}

}
