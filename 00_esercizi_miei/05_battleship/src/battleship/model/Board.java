package battleship.model;

import java.util.StringJoiner;

public class Board {
	private Ship[] ships;
	private int size;
	private int shipCount;
	private Shot[] shotRegistry;
	private int shotCount;

	public Board(int size, Ship[] ships) {
		this.size=size;
		this.shipCount=0;
		this.ships = new Ship[ships.length];
		for (Ship ship : ships) addShip(ship);
		this.shotRegistry = new Shot[size*size];
		this.shotCount=0;
	}
	
	private boolean addShip(Ship ship) {
		
		// 1) controllo range posizione iniziale
		if (ship.getPos().exceeds(size, "Posizione fuori range")) return false;
		
		// 2) controllo range posizione finale
		if(ship.getLastPos().exceeds(size, "Lunghezza nave eccessiva per " + ship.toString() + ": posizione finale =")) return false;

		// 3) verifica di non overlap con altre navi
		for (int i=0; i<shipCount; i++)
			if (ships[i].overlaps(ship)) {
				System.err.println(ship + " overlaps with existing ship " + ships[i]);
				return false;
			}
		
		// se tutto ok, la inseriamo
		this.ships[shipCount++] = ship;
		
		return true;
	}

	public ShotResult fire(Pos pos) {
		if (pos.exceeds(size, "Posizione sparo fuori range ")) return null;
		
		ShotResult result = ShotResult.ACQUA;
		for (int i=0; i<shipCount; i++) {
			if (ships[i].overlaps(pos)) { result=ships[i].decreaseIntegrity(); break; }
		}
		shotRegistry[shotCount++] = new Shot(pos,result);
		return result;
	}
	
	/*
	private boolean overlap(Ship ship1, Pos pos) {
		return switch (ship1.getOrientation()) {
				case HORIZONTAL -> r1 == row && col>=c1 && col<=c2;
				case VERTICAL   -> c1 == col && row>=r1 && row<=r2;
			};
	}
	*/

	public int getSize() {
		return size;
	}
	
	public int sunk() {
		int result=0;
		for (int i=0; i<shipCount; i++) if(ships[i].getIntegrity()==0) result++;
		return result;
	}

	public int getShipNumber() {
		return shipCount-sunk();
	}
	
	public String revealAsList() {
		var joiner = new StringJoiner(System.lineSeparator());
		for (int i=0; i<shipCount; i++) joiner.add(ships[i].toString());
		return joiner.toString();
	}

	public String revealAsMap() {
		var sb = new StringBuilder();
		for (int j=0; j<size; j++) { 
			sb.repeat("~", size); sb.append(System.lineSeparator()); // la riga è quindi lunga più di size, è size+len(separator)
		}	
		var lineLen = size + System.lineSeparator().length();
		for (int i=0; i<shipCount; i++) {
			var ship = ships[i];
			for(int k=0; k<ship.getType().getLength(); k++) // per ogni cella della nave
				sb.replace(ship.getPos().next(ship.getOrientation(),k).getOrdinal(lineLen), ship.getPos().next(ship.getOrientation(),k).getOrdinal(lineLen)+1, "\u25A0");
		}
		return sb.toString();
	}
	
	public String toString() {
		var sb = new StringBuilder();
		for (int j=0; j<size; j++) { 
			sb.repeat("-", size); sb.append(System.lineSeparator()); // la riga è quindi lunga più di size, è size+len(separator)
		}
		var lineLen = size + System.lineSeparator().length();
		for(int i=0; i<shotCount; i++) {
			var shot = shotRegistry[i];
			sb.replace(shot.pos().getOrdinal(lineLen), shot.pos().getOrdinal(lineLen)+1, shot.result()==ShotResult.ACQUA ? "~" : "\u25A0");			
		}
		return sb.toString();
	}
	
}
