package pacchi.persistence;

import java.io.IOException;
import java.io.Reader;
import java.util.Set;

import pacchi.model.Valore;
import pacchi.model.Territorio;


public interface ConfigReader {
	Set<Territorio> leggiTerritori(Reader reader) throws IOException, BadFileFormatException;
	Set<Valore> leggiPremi(Reader reader) throws IOException, BadFileFormatException;
}
