package rfd.model;

public class MyPointOfInterest extends PointOfInterest {

	public MyPointOfInterest(String name, String progressivaKm) {
		super(name, progressivaKm);
		String[] tokens = progressivaKm.trim().split("\\+");
		if (tokens.length != 2) throw new IllegalArgumentException("la progressiva km " + progressivaKm + " non può avere un numero diverso di 2 token");
		if (tokens[1].length() != 3) throw new IllegalArgumentException("i metri in " + progressivaKm + " devono essere espressi da 3 cifre");
		try {
			Integer.parseInt(tokens[0]);
			Integer.parseInt(tokens[1]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public double getKmAsNum() {
		String[] tokens = this.getKm().trim().split("\\+");
		if (tokens.length != 2) throw new IllegalArgumentException("la progressiva km " + this.getKm() + " non può avere un numero diverso di 2 token");
		if (tokens[1].length() != 3) throw new IllegalArgumentException("i metri in " + this.getKm() + " devono essere espressi da 3 cifre");
		double km, m;
		try {
			km = Integer.parseInt(tokens[0]);
			m = Integer.parseInt(tokens[1]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(e);
		}
		return km + m / 1000.0;
	}

}
