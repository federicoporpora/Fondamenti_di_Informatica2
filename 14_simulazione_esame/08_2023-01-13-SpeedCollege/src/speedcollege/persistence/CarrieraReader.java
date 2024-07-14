package speedcollege.persistence;

import java.io.IOException;
import java.io.Reader;

import speedcollege.model.Carriera;

public interface CarrieraReader {
	public Carriera leggiCarriera(Reader rdr) throws IOException;
}