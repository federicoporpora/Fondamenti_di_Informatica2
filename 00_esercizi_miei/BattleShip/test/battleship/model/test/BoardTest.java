package battleship.model.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import battleship.model.Board;
import battleship.model.Orientation;
import battleship.model.Pos;
import battleship.model.Ship;
import battleship.model.ShipType;
import battleship.model.ShotResult;

class BoardTest {

	@Test
	void testCtor() {
		var board = new Board(10, new Ship[] {
				new Ship(ShipType.PORTAEREI, new Pos(1,7), Orientation.HORIZONTAL, "portaerei1"),
				new Ship(ShipType.PORTAEREI, new Pos(3,1), Orientation.VERTICAL, "portaerei2"),
				new Ship(ShipType.INCROCIATORE, new Pos(1,4), Orientation.VERTICAL, "incrociatore1"),
				new Ship(ShipType.INCROCIATORE, new Pos(3,6), Orientation.HORIZONTAL, "incrociatore2"),
				new Ship(ShipType.INCROCIATORE, new Pos(10,6), Orientation.HORIZONTAL, "incrociatore3"),			
				new Ship(ShipType.INCROCIATORE, new Pos(6,10), Orientation.VERTICAL, "incrociatore4"),			
				new Ship(ShipType.CACCIATORPEDINIERE, new Pos(1,1), Orientation.HORIZONTAL, "cacciatorpediniere1"),
				new Ship(ShipType.CACCIATORPEDINIERE, new Pos(8,1), Orientation.HORIZONTAL, "cacciatorpediniere2"),
				new Ship(ShipType.CACCIATORPEDINIERE, new Pos(6,6), Orientation.VERTICAL, "cacciatorpediniere3"),
				new Ship(ShipType.SOMMERGIBILE, new Pos(5,4), Orientation.HORIZONTAL, "sommergibile1"),
				new Ship(ShipType.SOMMERGIBILE, new Pos(5,8), Orientation.HORIZONTAL, "sommergibile2"),
				new Ship(ShipType.SOMMERGIBILE, new Pos(10,2), Orientation.HORIZONTAL, "sommergibile3")
		});
		assertEquals(10, board.getSize());
		assertEquals(12, board.getShipNumber());
	}
	
	@Test
	void testCtorTwoOverlaps() {
		var board = new Board(10, new Ship[] {
				new Ship(ShipType.PORTAEREI, new Pos(1,7), Orientation.HORIZONTAL, "portaerei1"),
				new Ship(ShipType.PORTAEREI, new Pos(3,6), Orientation.VERTICAL, "portaerei2"),
				new Ship(ShipType.INCROCIATORE, new Pos(1,4), Orientation.VERTICAL, "incrociatore1"),
				new Ship(ShipType.INCROCIATORE, new Pos(3,6), Orientation.HORIZONTAL, "incrociatore2"), // overlaps -> scartato
				new Ship(ShipType.INCROCIATORE, new Pos(10,6), Orientation.HORIZONTAL, "incrociatore3"),			
				new Ship(ShipType.INCROCIATORE, new Pos(6,10), Orientation.VERTICAL, "incrociatore4"),			
				new Ship(ShipType.CACCIATORPEDINIERE, new Pos(1,1), Orientation.HORIZONTAL, "cacciatorpediniere1"),
				new Ship(ShipType.CACCIATORPEDINIERE, new Pos(8,9), Orientation.HORIZONTAL, "cacciatorpediniere2"), // overlaps -> scartato
				new Ship(ShipType.CACCIATORPEDINIERE, new Pos(6,6), Orientation.VERTICAL, "cacciatorpediniere3"),   // overlaps -> scartato
				new Ship(ShipType.SOMMERGIBILE, new Pos(5,4), Orientation.HORIZONTAL, "sommergibile1"),
				new Ship(ShipType.SOMMERGIBILE, new Pos(5,8), Orientation.HORIZONTAL, "sommergibile2"),
				new Ship(ShipType.SOMMERGIBILE, new Pos(10,2), Orientation.HORIZONTAL, "sommergibile3")
		});
		assertEquals(10, board.getSize());
		assertEquals(9, board.getShipNumber());
	}
	

	@Test
	void testFire() {
		var board = new Board(10, new Ship[] {
				new Ship(ShipType.PORTAEREI, new Pos(1,7), Orientation.HORIZONTAL, "portaerei1"),
				new Ship(ShipType.PORTAEREI, new Pos(3,1), Orientation.VERTICAL, "portaerei2"),
				new Ship(ShipType.INCROCIATORE, new Pos(1,4), Orientation.VERTICAL, "incrociatore1"),
				new Ship(ShipType.INCROCIATORE, new Pos(3,6), Orientation.HORIZONTAL, "incrociatore2"),
				new Ship(ShipType.INCROCIATORE, new Pos(10,6), Orientation.HORIZONTAL, "incrociatore3"),			
				new Ship(ShipType.INCROCIATORE, new Pos(6,10), Orientation.VERTICAL, "incrociatore4"),			
				new Ship(ShipType.CACCIATORPEDINIERE, new Pos(1,1), Orientation.HORIZONTAL, "cacciatorpediniere1"),
				new Ship(ShipType.CACCIATORPEDINIERE, new Pos(8,1), Orientation.HORIZONTAL, "cacciatorpediniere2"),
				new Ship(ShipType.CACCIATORPEDINIERE, new Pos(6,6), Orientation.VERTICAL, "cacciatorpediniere3"),
				new Ship(ShipType.SOMMERGIBILE, new Pos(5,4), Orientation.HORIZONTAL, "sommergibile1"),
				new Ship(ShipType.SOMMERGIBILE, new Pos(5,8), Orientation.HORIZONTAL, "sommergibile2"),
				new Ship(ShipType.SOMMERGIBILE, new Pos(10,2), Orientation.HORIZONTAL, "sommergibile3")
		});
		assertEquals(12, board.getShipNumber());
		assertEquals(ShotResult.ACQUA, 	 board.fire(new Pos(1,3)));
		assertEquals(12, board.getShipNumber());
		assertEquals(ShotResult.COLPITO, board.fire(new Pos(3,1)));
		assertEquals(12, board.getShipNumber());
		assertEquals(ShotResult.COLPITO, board.fire(new Pos(1,2)));
		assertEquals(12, board.getShipNumber());
		assertEquals(ShotResult.AFFONDATO, board.fire(new Pos(1,1)));
		assertEquals(11, board.getShipNumber());
		assertEquals(ShotResult.AFFONDATO, board.fire(new Pos(5,4)));
		assertEquals(10, board.getShipNumber());
		assertEquals(ShotResult.COLPITO, board.fire(new Pos(4,1)));
		assertEquals(10, board.getShipNumber());
		assertEquals(ShotResult.COLPITO, board.fire(new Pos(5,1)));
		assertEquals(10, board.getShipNumber());
		assertEquals(ShotResult.AFFONDATO, board.fire(new Pos(6,1)));
		assertEquals(9, board.getShipNumber());
	}


}
