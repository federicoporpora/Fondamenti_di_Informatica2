package meteodent.model;

import java.util.Objects;

public class ProbPioggia {
	private int probPioggia;

	ProbPioggia(int probPioggia) {
		this.probPioggia = probPioggia;
	}
	
	public static ProbPioggia of(int probPioggia) {
		if (probPioggia<0 || probPioggia>100) throw new IllegalArgumentException("Prob. pioggia dev'essere nel range 0-100");
		return new ProbPioggia(probPioggia);
	}

	public int getValue() {
		return probPioggia;
	}

	@Override
	public String toString() {
		return "Probabilità di pioggia del " + probPioggia + "%";
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(probPioggia);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		ProbPioggia other = (ProbPioggia) obj;
		return probPioggia == other.probPioggia;
	}	
	
}
