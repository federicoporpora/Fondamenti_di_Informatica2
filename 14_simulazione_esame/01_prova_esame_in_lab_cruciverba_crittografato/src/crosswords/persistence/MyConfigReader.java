package crosswords.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import crosswords.model.Scheme;

public class MyConfigReader implements ConfigReader {

	protected Scheme board;
	private int size;
	
	public MyConfigReader(Reader reader) throws IOException, BadFileFormatException {
		if (reader == null) throw new NullPointerException("Il reader è null");
		var bf = new BufferedReader(reader);
		this.size = parseFirstLine(bf.readLine());
		this.board = parseOtherLines(bf);
	}
	
	@Override
	public int getSize() {
		return size;
	}

	@Override
	public Scheme getScheme() {
		return board;
	}
	
	private int parseFirstLine(String line) throws BadFileFormatException {
		String[] tokens = line.split(" ");
		if (!tokens[0].equals("DIM")) throw new BadFileFormatException("errore in DIM prima riga");
		String[] dimensioni = tokens[1].split("x");
		if (dimensioni.length != 2 || !dimensioni[0].equals(dimensioni[1]) || Integer.parseInt(dimensioni[0]) != Integer.parseInt(dimensioni[1]))
			throw new BadFileFormatException("errore in DIMxDIM prima riga");
		return Integer.parseInt(dimensioni[0]);
	}
	
	private Scheme parseOtherLines(BufferedReader reader) throws IOException, BadFileFormatException {
		var res = new Scheme(size);
		String riga;
		int nRiga = 0;
		while ((riga = reader.readLine()) != null) {
			String[] tokens = riga.split(" ");
			var cifreInt = new int[this.size];
			if (tokens.length != this.size) throw new BadFileFormatException("il numero di cifre della griglia è sbagliato");
			else {
				for (int i = 0; i < this.size; i++) {
					if (tokens[i].equals("#")) {
						cifreInt[i] = 0;
					}
					else {
						try { 
							cifreInt[i] = Integer.parseInt(tokens[i]);
						} catch (NumberFormatException e) {
							throw new BadFileFormatException("all'interno della griglia c'è uno o più caratteri che non sono interi");
						}
					}
						
				}
				try {
					res.setCellRow(nRiga, cifreInt);
				} catch (IllegalArgumentException e) {
					throw new BadFileFormatException("errore in setCellRow");
				}
			}
			nRiga++;
		}
		return res;
	}
	
}