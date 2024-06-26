package battleship.model;

public enum ShipType {
	SOMMERGIBILE(1), CACCIATORPEDINIERE(2), INCROCIATORE(3), PORTAEREI(4);
	private int len;
	private ShipType(int len) {
		this.len = len;
	}
	public int getLength() {
		return this.len;
	}
	public static ShipType of(int lenght) {
		return ShipType.values()[lenght - 1];
	}
}