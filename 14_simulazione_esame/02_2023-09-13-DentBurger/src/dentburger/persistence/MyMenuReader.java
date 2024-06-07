package dentburger.persistence;

import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.text.ParsePosition;
import java.io.BufferedReader;
import dentburger.model.Prodotto;
import dentburger.model.Categoria;
import dentburger.model.Formatters;
import dentburger.model.Menu;


public class MyMenuReader implements MenuReader {
	
	private static final int    TOKEN_COUNT = 4;
	private static final String SEPARATOR = "\t";
	
	/* ESEMPIO DI FILE
	  	Piatto	Burger	Basic		4,90 €
		Piatto	Burger	Formaggio	7,20 €
		Piatto	Toast	Classico	6,50 €
		...
		Piatto	Pollo	6 Crocchette	5,80 €
		Piatto	Pollo	8 Crocchette	7,00 €
		...
		Contorno	Patatine	Medie	2,70 €
		...
		Bevanda	Cola	Media		3,50 €
		Bevanda	Cola	Grande		4,50 €
		Bevanda	Acqua	Media		1,50 €
		...
		Dessert	Gelato	Panna e cioccolato	1,80 €
		Dessert	Gelato	Panna e fragola		1,80 €
		...
	 */
	
	@Override
	public Menu leggiProdotti(Reader reader) throws IOException, BadFileFormatException {
		// carica da un apposito Reader (già aperto) i dati necessari, restituendo un’istanza di Menu 
		// opportunamente popolata. Il metodo lancia IllegalArgumentException con opportuno messaggio 
		// d’errore in caso di argomento (reader) nullo, BadFileFormatException con messaggio d’errore 
		// appropriato in caso di problemi nel formato del file (mancanza/eccesso di elementi, categorie 
		// inesistenti, errori nel formato dei prezzi, etc.); in particolare, è richiesto di controllare 
		// con cura il formato del prezzo, verificando che non siano presenti punti decimali e che il 
		// numero sia letto interamente e non vi siano anomalie di sorta. Il metodo lancia infine 
		// IOException in caso di altri problemi di I/O.
		if (reader == null) throw new IllegalArgumentException("il reader è null");
		var bf = new BufferedReader(reader);
		var menu = new Menu();
		String riga;
		while ((riga = bf.readLine()) != null) {
			
			String[] tokens = riga.split(SEPARATOR+"+");;
			if (tokens.length != TOKEN_COUNT) throw new BadFileFormatException("il numero di token in una riga è diverso da " + TOKEN_COUNT);
			Categoria categoria;
			double prezzo;
			if (tokens[3].contains(".")) throw new BadFileFormatException("il prezzo contiene un .");
			try {
				prezzo = Formatters.priceFormatter.parse(tokens[3]).doubleValue();
			} catch (ParseException e) {
				throw new BadFileFormatException("errore nella formattazione del prezzo");
			}
			try {
				categoria = Categoria.valueOf(tokens[0].trim().toUpperCase());
			} catch (IllegalArgumentException e) {
				throw new BadFileFormatException("la categoria non esiste");
			}
			if (prezzo < 0.0) throw new BadFileFormatException("il prezzo è negativo");
			menu.add(new Prodotto(categoria, tokens[1], tokens[2], prezzo));
		}
		return menu;
	}

}
