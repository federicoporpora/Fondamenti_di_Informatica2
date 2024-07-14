package cityparking.controller;

import java.time.LocalDate;

import cityparking.model.MyCalcolatoreSosta;
import cityparking.model.Tariffa;
import cityparking.model.Ricevuta;
import cityparking.model.BadTimeIntervalException;
import cityparking.model.CalcolatoreSosta;


public class MyController implements Controller {

	@SuppressWarnings("unused")
	private CalcolatoreSosta calc;

	public MyController(Tariffa tariffa) {
		calc = new MyCalcolatoreSosta(tariffa);
	}

	
	// *** DA REALIZZARE ***
	@Override
	public Ricevuta calcolaSosta(LocalDate dataInizio, String strOraInizio, LocalDate dataFine, String strOraFine) 
			throws BadTimeIntervalException, BadTimeFormatException {
		return null; // fake
	}
	
}
