package battleship.model;

public record Pos(int row, int col) {
	public Pos(String coordinates) { // D10
		this(Integer.parseInt(coordinates.substring(1, coordinates.length())), (coordinates.charAt(0) - 'A') + 1);
	}

	public Pos next(Orientation orientation) {
		return next(orientation, 1);
	}

	public Pos next(Orientation orientation, int k) {
		if (orientation == Orientation.HORIZONTAL) return new Pos(this.row + k, this.col);
		return new Pos(this.row, this.col + k);
	}
	public boolean isWithin(int size) {
		return (size >= this.row && size >= this.col && this.row >= 1 && this.col >= 1);
	}
	
	public boolean exceeds(int size, String msg) {
		boolean outOfBounds = !isWithin(size);
		if (outOfBounds) System.err.println(msg);
		return outOfBounds;
	}
	
	@Override
	public String toString() {
		return String.valueOf((char) 'A' + col - 1) + row;
	}
	
	public int getOrdinal(int size) {
		return size * (row - 1) + (col - 1);
	}
	
	public boolean isColumnLessOrEqualTo(Pos other) {
		return (this.col <= other.col);
	}
	
	public boolean isRowLessOrEqualTo(Pos other) {
		return (this.row <= other.row);
	}
	
	public boolean isColumnGreaterOrEqualTo(Pos other) {
		return (this.col >= other.col);
	}
	
	public boolean isRowGreaterOrEqualTo(Pos other) {
		return (this.row >= other.row);
	}
	
	public boolean isColumnEqualTo(Pos other) {
		return (this.col == other.col);
	}
	
	public boolean isRowEqualTo(Pos other) {
		return (this.row == other.row);
	}
	
	public static Pos[] findCommons(Pos[] v1, Pos[] v2) {
		Pos[] shorter, longer;
		if (v1.length < v2.length) { shorter = v1; longer = v2; }
		else { shorter = v2; longer = v1; }
		Pos[] temp = new Pos[shorter.length];
		int tempIndex = 0;
		for (Pos p : shorter) {
			if (contains(longer, p)) temp[tempIndex++] = p;
		}
		return java.util.Arrays.copyOf(temp, tempIndex);
	}
	
	public static boolean contains(Pos[] v, Pos pos) {
		for (Pos p : v) {
			if (p.equals(pos)) return true;
		}
		return false;
	}
	
}