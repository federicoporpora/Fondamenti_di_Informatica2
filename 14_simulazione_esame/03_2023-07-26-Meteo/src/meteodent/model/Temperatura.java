package meteodent.model;

import java.util.Objects;

public class Temperatura {
	private int temperatura;

	Temperatura(int temperatura) {
		this.temperatura = temperatura;
	}
	
	public static Temperatura of(int temperatura) {
		return new Temperatura(temperatura);
	}

	public int getValue() {
		return temperatura;
	}

	@Override
	public String toString() {
		return "Temperatura di " + temperatura + "°C";
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(temperatura);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Temperatura other = (Temperatura) obj;
		return temperatura == other.temperatura;
	}
	
}
