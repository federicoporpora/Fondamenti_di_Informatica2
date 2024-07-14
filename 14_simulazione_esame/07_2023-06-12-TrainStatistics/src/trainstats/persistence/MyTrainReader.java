package trainstats.persistence;

import java.io.IOException;
import java.io.Reader;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import trainstats.model.Train;


public class MyTrainReader implements TrainReader {

	@SuppressWarnings("unused")
	private DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.ITALY);
	
	@Override
	public Train readTrain(Reader rdr) throws IOException {
		
		/* Ogni riga descrive la fermata del treno in una stazione, specificando nell’ordine 
		  	- il nome della stazione
			- l’orario di arrivo previsto
			- l’orario di arrivo effettivo
			- l’orario di partenza previsto 
			- l’orario di partenza effettivo. 
		   Questi valori sono separati uno dall’altro da uno o più caratteri “punto e virgola” (‘;’).
		   Non è dato sapere se vi siano spazi intorno, prima, davanti ai vari campi. 
		   Tutti gli orari sono in formati italiano SHORT. 

		   IMPORTANTE: nel caso della stazione iniziale, sono indefiniti gli orari di arrivo; 
		   lo stesso accade nell’ultima stazione per gli orari di partenza. 
		   Al loro posto, per convenzione, è indicata una sequenza di **almeno due** trattini '-'
		   
		   Questo metodo deve lanciare:
		   - IllegalArgumentException con opportuno messaggio d’errore in caso di argomento (reader) nullo;
		   - BadFileFormatException con opportuno messaggio d’errore in caso di problemi nel formato del file 
		     (mancanza/eccesso di elementi, errori nel formato degli orari, etc.). 
		     IMPORTANTE: a questo riguardo, si tenga conto che il costruttore di Recording lancia IllegalArgumentException 
		     in una serie di situazioni relative ad argomenti “illogici”, perciò tale eccezione dovrà essere sostituita 
		     da un’opportuna BadFileFormatException.
		   - IOException, in caso, invece, di altri problemi di I/O
		    
		 */
		
		return null; // FAKE, da sostituire con implementazione effettiva
	}

}