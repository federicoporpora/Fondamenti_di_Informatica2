package dentburger.persistence;

import java.io.IOException;
import java.io.Reader;
import dentburger.model.Menu;

public interface MenuReader {
	Menu leggiProdotti(Reader reader) throws IOException, BadFileFormatException;
}
