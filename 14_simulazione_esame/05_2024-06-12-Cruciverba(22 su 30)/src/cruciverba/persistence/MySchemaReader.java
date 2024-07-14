package cruciverba.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import java.util.ArrayList;

import cruciverba.model.Cruciverba;
import cruciverba.model.Orientamento;


public class MySchemaReader implements SchemaReader {
	
	private Reader inputReader;
	
	public MySchemaReader(Reader inputReader) {
		this.inputReader = inputReader;
	}
	
	public Cruciverba leggiSchema() throws BadFileFormatException, IOException {
		var bufferedReader = new BufferedReader(inputReader);
		var righe = new ArrayList<String>();
		String rigaLetta;
		while ((rigaLetta = bufferedReader.readLine()) != null) righe.add(rigaLetta);
		for (String riga : righe) if (riga.length() != righe.get(0).length())
			throw new BadFileFormatException("la riga '" + riga + "' ha una lunghezza diversa dalle " + "altre:\nquesta riga: " + riga.length() + ", la prima: " + righe.get(0).length());
		var cruciverba = new Cruciverba(righe.size(), righe.get(0).length());
		int nRiga = 0;
		for (String riga : righe) {
			var elementiRiga = SchemaReader.elementiRiga(riga);
			for (ElementoRiga elementoRiga : elementiRiga)
				cruciverba.setParola(nRiga, elementoRiga.pos(), elementoRiga.string(), Orientamento.ORIZZONTALE);
			nRiga++;
		}
		return cruciverba;
	}

}
