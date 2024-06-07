package spesesanitarie.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;

public class Formatters {
	public static final DateTimeFormatter itDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static final NumberFormat itPriceFormatter = new DecimalFormat("¤\u00A0#,##0.00");
}