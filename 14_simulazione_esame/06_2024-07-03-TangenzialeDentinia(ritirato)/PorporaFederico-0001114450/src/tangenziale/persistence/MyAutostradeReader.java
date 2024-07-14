package tangenziale.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;
import tangenziale.model.Autostrada;
import tangenziale.model.Tipologia;

//M3
//45	Petruvia Nord
//40	Petruvia Sud
//25	Aldino
//1	Dentinia Ippodromo
//0	Tangenziale
//END

public class MyAutostradeReader implements AutostradeReader {

	public Map<String,Autostrada> leggiAutostrade(Reader reader) throws IOException, BadFileFormatException {
		if (reader == null) throw new NullPointerException("Il reader è null");
		BufferedReader innerReader = new BufferedReader(reader);
		TreeMap<String, Autostrada> result = new TreeMap<>();
		String riga;
		while ((riga = innerReader.readLine()) != null) {
			String id = riga;
			var caselli = new TreeMap<Integer, String>();
			riga = innerReader.readLine();
			if (riga == null) throw new BadFileFormatException("la prima riga dopo all'autostrada è null");
			while (!riga.equals(AutostradeReader.END_TAG)) {
				String[] tokens = riga.split("\\t+");
				if (tokens.length != 2) throw new BadFileFormatException("non 2 token in riga " + riga);
				try {
					int km = Integer.parseInt(tokens[0]);
					if (caselli.containsKey(km)) throw new BadFileFormatException("l'intero della riga " + riga + "è già presente nella mappa dei caselli");
					if (caselli.containsValue(tokens[1].trim())) throw new BadFileFormatException("il casello della riga " + riga + " è già presente nella mappa dei caselli");
					caselli.put(km, tokens[1].trim());
				} catch (NumberFormatException e) {
					throw new BadFileFormatException("errore in riga " + riga + " nel parsing dell'intero");
				}
				riga = innerReader.readLine();
				if (riga == null) throw new BadFileFormatException("finito il file senza END");
			}
			riga = innerReader.readLine();
			if (riga == null || riga.isEmpty()) {
				if (id.equals(Autostrada.TANGENZIALE)) result.put(id, new Autostrada(Tipologia.TANGENZIALE, id, caselli));
				else result.put(id, new Autostrada(Tipologia.AUTOSTRADA, id, caselli));
			} else throw new BadFileFormatException("la riga è null o vuota");
		}
		if (result.isEmpty()) throw new BadFileFormatException("la mappa result è vuota");
		if (result.keySet().stream().filter(autostrada -> autostrada.equals(Autostrada.TANGENZIALE)).count() != 1)
			throw new BadFileFormatException("C'è più di una o nessuna tangenziale");
		return result; 
	}
	
}
