package spesesanitarie.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;

import spesesanitarie.model.Formatters;
import spesesanitarie.model.DocumentoDiSpesa;
import spesesanitarie.model.Tipologia;
import spesesanitarie.model.VoceDiSpesa;

	/*
		03/01/2022;Farmacia;€ 25,71
		FC;€ 4,98
		FC;€ 8,04
		FC;€ 4,41
		FC;€ 8,28
		18/01/2022;Farmacia;€ 16,65
		FC;€ 16,65
		24/01/2022;Farmacia;€ 3,00
		TK;€ 3,00
		25/01/2022;Dentista;€ 300,00
		LP;€ 300,00
		...
	*/

public class MySpeseReader implements SpeseReader {

	@Override
	public List<DocumentoDiSpesa> leggiSpese(Reader rdr) throws IOException {
		//
		// ***** DA IMPLEMENTARE *****
		// Si suggerisce di delegare a un apposito metodo privato leggiVoci la lettura delle voci di spese
		//
		return null; // FAKE, da implementare
	}


	// Metodo privato ausiliario
	//
	private List<VoceDiSpesa> leggiVoci(int nItems, BufferedReader reader) throws IOException {
		//
		// ***** DA IMPLEMENTARE *****
		//
		return null; // FAKE, da implementare
	}

}