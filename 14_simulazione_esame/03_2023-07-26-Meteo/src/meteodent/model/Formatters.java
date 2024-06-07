package meteodent.model;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Formatters {
	// FAKE FORMATTERS, DA SOSTITUIRE CON QUELLI EFFETTIVI!!
	public static final DateTimeFormatter itDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(Locale.ITALY);
	public static final DateTimeFormatter datetimeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.ITALY);
}
