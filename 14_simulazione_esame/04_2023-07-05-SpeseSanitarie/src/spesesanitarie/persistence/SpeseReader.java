package spesesanitarie.persistence;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import spesesanitarie.model.DocumentoDiSpesa;


public interface SpeseReader {
	public List<DocumentoDiSpesa> leggiSpese(Reader rdr) throws IOException;
}