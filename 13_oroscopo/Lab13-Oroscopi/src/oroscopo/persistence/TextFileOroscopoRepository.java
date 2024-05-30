package oroscopo.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.Set;
import oroscopo.model.Previsione;


public class TextFileOroscopoRepository implements OroscopoRepository {

	private Map<String, List<Previsione>> data;
	
	private List<Previsione> caricaPrevisioni(BufferedReader reader) throws IOException, BadFileFormatException {
		String riga = reader.readLine();
		
	}
	
	public TextFileOroscopoRepository(Reader reader) throws IOException, BadFileFormatException {
		if (reader == null) throw new NullPointerException("il reader è null");
		var bufferedReader = new BufferedReader(reader);
		while ()
	}

	@Override
	public List<Previsione> getPrevisioni(String sezione) {
		return data.get(sezione.toUpperCase());
	}

	@Override
	public Set<String> getSettori() {
		return data.keySet();
	}

}
