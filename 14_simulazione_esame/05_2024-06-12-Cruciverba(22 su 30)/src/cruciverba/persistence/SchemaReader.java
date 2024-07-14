package cruciverba.persistence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cruciverba.model.Cruciverba;

public interface SchemaReader {
	
	Cruciverba leggiSchema() throws BadFileFormatException, IOException;

	final static char SEPARATORE = '-';
		
	public static List<ElementoRiga> elementiRiga(String riga) {
		int i=0;
		List<ElementoRiga> listaElementiRiga = new ArrayList<>();
		while(i<riga.length()) {
			while(riga.charAt(i)==SchemaReader.SEPARATORE && i<riga.length()) i++; // salta caselle nere
			int prossimaNera = riga.indexOf(SchemaReader.SEPARATORE, i);
			if (prossimaNera<0) prossimaNera = riga.length();
			var elementoRiga = riga.substring(i, prossimaNera);
			listaElementiRiga.add(new ElementoRiga(elementoRiga, i));
			i = prossimaNera+1;
		}
		return listaElementiRiga;
	}
}
