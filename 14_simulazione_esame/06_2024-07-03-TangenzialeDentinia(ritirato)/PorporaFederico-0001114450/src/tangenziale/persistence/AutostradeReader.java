package tangenziale.persistence;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;

import tangenziale.model.Autostrada;

public interface AutostradeReader {
	public final String END_TAG= "END";
	public Map<String,Autostrada> leggiAutostrade(Reader reader) throws IOException, BadFileFormatException;
}
