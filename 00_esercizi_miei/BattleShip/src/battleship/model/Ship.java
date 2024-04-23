package battleship.model;

public class Ship {
	private int integrity;
	private String name;
	private Orientation orientation;
	private Pos pos;
	private ShipType shiptype;
	
	public Ship(ShipType shiptype, Pos pos, Orientation orientation, String name) {
		this.name = name;
		this.orientation = orientation;
		this.pos = pos;
		this.shiptype = shiptype;
		this.integrity = shiptype.getLength();
	}
	
	public int getIntegrity() {
		return integrity;
	}
	public String getName() {
		return name;
	}
	public Orientation getOrientation() {
		return orientation;
	}
	public Pos getPos() {
		return pos;
	}
	public Pos getLastPos() {
		return this.pos.next(orientation, shiptype.getLength()-1);
	}
	public Pos[] getPositions() {
		Pos[] res = new Pos[shiptype.getLength()];
		for (int i = 0; i < shiptype.getLength(); i++) {
			res[i] = pos.next(orientation, i);
		}
		return res;
	}
	public ShipType getType() {
		return shiptype;
	}
	public ShotResult decreaseIntegrity() {
		integrity--;
		if (integrity == 0) return ShotResult.AFFONDATO;
		return ShotResult.COLPITO;
	}
	public boolean isOrientationEqualTo(Ship other) {
		return this.getOrientation() == other.getOrientation();
	}
	public boolean overlaps(Pos pos) {
		Pos[] positions = this.getPositions();
		for (Pos eachPos : positions) {
			if (eachPos.isColumnEqualTo(pos) && eachPos.isRowEqualTo(pos)) return true;
		}
		return false;
	}
	public boolean overlaps(Ship other) {
		Pos[] thisPositions = this.getPositions();
		Pos[] otherPositions = other.getPositions();
		for (Pos eachThisPos : thisPositions) {
			for (Pos eachOtherPos : otherPositions) {
				if (eachThisPos.isColumnEqualTo(eachOtherPos) && eachThisPos.isRowEqualTo(eachOtherPos)) return true;
			}
		}
		return false;
	}
	@Override
	public String toString() {
		return "Ship [integrity=" + integrity + ", name=" + name + ", orientation=" + orientation + ", pos=" + pos
				+ ", shiptype=" + shiptype + "]";
	}
	
}