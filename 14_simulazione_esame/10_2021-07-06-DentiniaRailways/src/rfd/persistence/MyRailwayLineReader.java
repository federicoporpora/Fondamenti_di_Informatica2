package rfd.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import rfd.model.MyPointOfInterest;
import rfd.model.PointOfInterest;
import rfd.model.RailwayLine;

public class MyRailwayLineReader implements RailwayLineReader {

	@Override
	public RailwayLine getRailwayLine(Reader rdr) throws IOException {
		if (rdr == null) throw new IllegalArgumentException("reader null");
		var bf = new BufferedReader(rdr);
		String riga;
		Map<String, PointOfInterest> map = new HashMap<>();
		SortedSet<String> set = new TreeSet<String>();
		while ((riga = bf.readLine()) != null) {
			String[] tokens = riga.split("\\t+");
			if (tokens.length != 2) throw new IllegalArgumentException("alla riga " + riga + " ci sono più di 2 tokens");
			if (tokens[1].charAt(0) >= '0' && tokens[1].charAt(0) <= '9') throw new IllegalArgumentException("alla riga " + riga + " il nome della stazione inizia con un numero");
			String[] nome = tokens[1].split("\\+");
			if (nome.length == 0 || nome[0].isBlank()) throw new IllegalArgumentException("alla riga " + riga + " non è presente il nome della stazione");
			if (nome[0].endsWith(" ")) throw new IllegalArgumentException("alla riga " + riga + " c'è uno spazio prima del +");
			if (!tokens[1].endsWith("+")) map.put(tokens[1], new MyPointOfInterest(tokens[1], tokens[0]));
			else {
				map.put(nome[0], new MyPointOfInterest(nome[0], tokens[0]));
				set.add(nome[0]);
			}
		}
		return new RailwayLine(map, set);
	}

}
