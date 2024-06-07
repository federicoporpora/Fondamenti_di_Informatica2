package dentburger.model;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Formatters {
	// FAKE da sostituire con la giusta implementazione richiesta
	// priceFormatter è un formattatore per prezzi in Euro secondo lo standard italiano
	public static final NumberFormat priceFormatter = NumberFormat.getCurrencyInstance(Locale.ITALY);
	// FAKE da sostituire con la giusta implementazione richiesta
	// datetimeFormatter è un formattatore per data e ora secondo lo standard italiano SHORT 
	public static final DateTimeFormatter datetimeFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(Locale.ITALY);
}
