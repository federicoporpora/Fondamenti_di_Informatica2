package meteodent.persistence;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.SortedSet;

import meteodent.model.Previsione;

public interface PrevisioniReader {
	Map<String, SortedSet<Previsione>> leggiPrevisioni(Reader reader) throws IOException, BadFileFormatException;
}
