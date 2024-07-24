package pacchi.model;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;

public class Formatters {
	public static final NumberFormat priceFormatter = NumberFormat.getCurrencyInstance(Locale.ITALY);
	public static final NumberFormat valueFormatter = NumberFormat.getNumberInstance(Locale.ITALY);
	
	static {
		priceFormatter.setMaximumFractionDigits(0);
	}
	
	public static int parse(String s) {
		ParsePosition pos = new ParsePosition(0);
		Number n = valueFormatter.parse(s, pos);
		if (pos.getIndex()<s.length()) throw new IllegalArgumentException(s);
		return n.intValue();
}
}
