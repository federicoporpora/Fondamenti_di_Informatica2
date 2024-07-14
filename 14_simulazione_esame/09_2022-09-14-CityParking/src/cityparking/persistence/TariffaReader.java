package cityparking.persistence;

import java.io.IOException;
import java.io.Reader;

import cityparking.model.Tariffa;

public interface TariffaReader {
	Tariffa leggiTariffa(Reader reader) throws IOException, BadFileFormatException;
}
