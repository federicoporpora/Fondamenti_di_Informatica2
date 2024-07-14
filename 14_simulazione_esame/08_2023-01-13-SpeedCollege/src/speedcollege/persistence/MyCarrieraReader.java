package speedcollege.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.Locale;

import speedcollege.model.AttivitaFormativa;
import speedcollege.model.Carriera;
import speedcollege.model.Esame;
import speedcollege.model.Voto;


public class MyCarrieraReader implements CarrieraReader {

	/*
	 * 	27991	ANALISI MATEMATICA T-1 (cfu:9)			12/01/20	RT
		27991	ANALISI MATEMATICA T-1 (cfu:9)			10/02/20	22
		28004	FONDAMENTI DI INFORMATICA T-1 (cfu:12)	13/02/20	24
		29228	GEOMETRIA E ALGEBRA T (cfu:6)			18/01/20	26
		26337	LINGUA INGLESE B-2 (cfu:6)
		27993	ANALISI MATEMATICA T-2 (cfu:6)			10/06/20	RE
		27993	ANALISI MATEMATICA T-2 (cfu:6)			02/07/20	RT
		28006	FONDAMENTI DI INFORMATICA T-2 (cfu:12)
		28011	RETI LOGICHE T (cfu:6)		
		...
	*/

	@Override
	public Carriera leggiCarriera(Reader rdr) throws IOException {
		if (rdr == null) throw new IllegalArgumentException("il reader è null");
		var carriera = new Carriera();
		var bf = new BufferedReader(rdr);
		String riga;
		while ((riga = bf.readLine()) != null) {
			if (riga.isEmpty()) continue;
			String[] tokens = riga.split("\\t+"), denominazioneECrediti;
			String nome;
			long codice;
			int crediti;
			LocalDate data;
			Voto voto;
			if (tokens.length != 2 && tokens.length != 4) throw new BadFileFormatException("la riga " + riga + " non è composta da 2 o 4 elementi");
			//ESAME = attività formativa, localdate, voto
			try {
				codice = Long.parseLong(tokens[0]);
				denominazioneECrediti = tokens[1].split("\\(cfu:");
				if (denominazioneECrediti.length != 2) throw new BadFileFormatException("formato " + tokens[1] + " errato, diverso da 2 tokens");
				nome = denominazioneECrediti[0];
				denominazioneECrediti = denominazioneECrediti[1].split("\\)");
				if (denominazioneECrediti.length != 1) throw new BadFileFormatException("formato " + tokens[1] + " errato, errore nella rimozione dell'ultima parentesi, tokens diversi da 1");
				crediti = Integer.parseInt(denominazioneECrediti[0]);
			} catch (NumberFormatException e) {
				throw new BadFileFormatException(e);
			}
			if (nome.isEmpty() || nome.isBlank()) throw new BadFileFormatException("il nome del corso è vuoto");
			if (codice < 1) throw new BadFileFormatException("il codice " + codice + " è minore di 1");
			if (tokens.length == 4) {
				DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
				try {
					data = LocalDate.parse(tokens[2], formatter);
					voto = Voto.of(tokens[3]);
				} catch (DateTimeParseException e) {
					throw new BadFileFormatException(e);
				} catch (NumberFormatException e) {
					throw new BadFileFormatException(e);
				} catch (IllegalArgumentException e) {
					throw new BadFileFormatException(e);
				}
				try {
					carriera.inserisci(new Esame(new AttivitaFormativa(codice, nome, crediti), data, voto));
				} catch (IllegalArgumentException e) {
					throw new BadFileFormatException(e);
				}
				
			}
		}
		return carriera;
	}

}