package cityparking.persistence;

import java.io.IOException;
import java.io.Reader;

import cityparking.model.Tariffa;


public class MyTariffaReader implements TariffaReader {
	
	// *** DA REALIZZARE ***
	@Override
	public Tariffa leggiTariffa(Reader reader) throws IOException, BadFileFormatException {
		return null; // fake
	}
		
}
