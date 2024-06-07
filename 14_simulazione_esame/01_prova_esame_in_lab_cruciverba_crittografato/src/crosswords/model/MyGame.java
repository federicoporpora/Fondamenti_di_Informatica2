package crosswords.model;

import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

public class MyGame extends Scheme implements Game {

	private Map<Integer, Optional<String>> charMap;
	
	public MyGame(int size) {
		super(size);
		this.charMap = new HashMap<Integer, Optional<String>>();
	}

	@Override
	public void setMapping(int num, String value) {
		if (num < 0 || num > 26) throw new IllegalArgumentException("provato a mappare un intero out of range");
		charMap.put(num, Optional.ofNullable(value));
	}

	@Override
	public Optional<String> getMapping(int num) {
		return charMap.getOrDefault(num, Optional.empty());
	}

	@Override
	public boolean isFull() {
		for (int i = 0; i < this.getSize(); i++) {
			for (int j = 0; j < this.getSize(); j++) {
				if (this.getCell(i, j) == 0) continue;
				if (charMap.get(this.getCell(i, j)).isEmpty()) return false;
			}
		}
		return true;
	}
	
	@Override
	public void setCellRow(int num, int[] numValues) {
		super.setCellRow(num, numValues);
		for (int i = 0; i < numValues.length; i++) {
			charMap.putIfAbsent(numValues[i], Optional.empty());
		}
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < this.getSize(); i++) {
			for (int j = 0; j < this.getSize(); j++) {
				if (this.getCell(i, j) == 0) res.append("#");
				else if (charMap.containsKey(this.getCell(i, j)) && charMap.get(this.getCell(i, j)).isPresent())
					res.append(charMap.get(this.getCell(i, j)).get());
				else res.append(this.getCell(i, j));
				res.append(' ');
			}
			res.append('\n');
		}
		return res.toString();
	}
	
}
