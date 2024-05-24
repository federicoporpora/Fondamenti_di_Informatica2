package battleship.model.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import battleship.model.Orientation;
import battleship.model.Pos;
import battleship.model.Ship;
import battleship.model.ShipType;
import battleship.model.ShotResult;

class ShipTest {

	@Test
	void testCtor() {
		final String name = "Augustus I";
		var sh = new Ship(ShipType.CACCIATORPEDINIERE, new Pos(1,1), Orientation.HORIZONTAL, name);
		assertEquals(ShipType.CACCIATORPEDINIERE, sh.getType());
		assertEquals(new Pos(1,1), sh.getPos());
		assertEquals(Orientation.HORIZONTAL, sh.getOrientation());
		assertEquals(sh.getType().getLength(), sh.getIntegrity());
		assertEquals(name, sh.getName());
	}
	
	@Test
	void testGetLastPos() {
		var sh = new Ship(ShipType.CACCIATORPEDINIERE, new Pos(1,1), Orientation.HORIZONTAL, "Augustus I");
		assertEquals(new Pos(1,1), sh.getPos());
		assertEquals(new Pos(1,1).next(Orientation.HORIZONTAL, sh.getType().getLength()-1), sh.getLastPos());
		var som = new Ship(ShipType.SOMMERGIBILE, new Pos(1,7), Orientation.HORIZONTAL, "Invisible");
		assertEquals(new Pos(1,7), som.getPos());
		assertEquals(new Pos(1,7).next(Orientation.HORIZONTAL, som.getType().getLength()-1), som.getLastPos());
		var som2 = new Ship(ShipType.SOMMERGIBILE, new Pos(1,7), Orientation.VERTICAL, "Invisible");
		assertEquals(new Pos(1,7), som2.getPos());
		assertEquals(new Pos(1,7).next(Orientation.VERTICAL, som2.getType().getLength()-1), som2.getLastPos());
	}
	
	@Test
	void testIntegrityH() {
		final String name = "Augustus I";
		var sh = new Ship(ShipType.PORTAEREI, new Pos(5,2), Orientation.HORIZONTAL, name);
		var integrity = sh.getType().getLength();
		for (int k=0; k<integrity-1; k++) {
			assertEquals(ShotResult.COLPITO, sh.decreaseIntegrity());
		}
		assertEquals(ShotResult.AFFONDATO, sh.decreaseIntegrity());
	}
	
	@Test
	void testIntegrityV() {
		final String name = "Augustus II";
		var sh = new Ship(ShipType.PORTAEREI, new Pos(5,2), Orientation.VERTICAL, name);
		var integrity = sh.getType().getLength();
		for (int k=0; k<integrity-1; k++) {
			assertEquals(ShotResult.COLPITO, sh.decreaseIntegrity());
		}
		assertEquals(ShotResult.AFFONDATO, sh.decreaseIntegrity());
	}

	@Test
	void testOrientationH() {
		var sh = new Ship(ShipType.PORTAEREI, new Pos(5,2), Orientation.HORIZONTAL, "Augustus II");
		var som = new Ship(ShipType.SOMMERGIBILE, new Pos(1,7), Orientation.HORIZONTAL, "Invisible");
		assertTrue(sh.isOrientationEqualTo(som));
		assertTrue(som.isOrientationEqualTo(sh));
		var som2 = new Ship(ShipType.SOMMERGIBILE, new Pos(1,7), Orientation.VERTICAL, "Unseen");
		assertFalse(som.isOrientationEqualTo(som2));
		assertFalse(som2.isOrientationEqualTo(som));
		assertFalse(som2.isOrientationEqualTo(sh));
	}
	
	@Test
	void testOrientationV() {
		var sh = new Ship(ShipType.PORTAEREI, new Pos(5,2), Orientation.VERTICAL, "Augustus II");
		var som = new Ship(ShipType.SOMMERGIBILE, new Pos(1,7), Orientation.VERTICAL, "Invisible");
		assertTrue(sh.isOrientationEqualTo(som));
		assertTrue(som.isOrientationEqualTo(sh));
		var som2 = new Ship(ShipType.SOMMERGIBILE, new Pos(1,7), Orientation.HORIZONTAL, "Unseen");
		assertFalse(som.isOrientationEqualTo(som2));
		assertFalse(som2.isOrientationEqualTo(som));
		assertFalse(som2.isOrientationEqualTo(sh));
	}
	
	@Test
	void testOverlapsPos() {
		var sh = new Ship(ShipType.PORTAEREI, new Pos(5,2), Orientation.VERTICAL, "Augustus II");   // da 5,2 a 8,2
		assertFalse(sh.overlaps(new Pos(4,2)));
		assertTrue(sh.overlaps(new Pos(5,2)));
		assertTrue(sh.overlaps(new Pos(6,2)));
		assertTrue(sh.overlaps(new Pos(7,2)));
		assertTrue(sh.overlaps(new Pos(8,2)));
		assertFalse(sh.overlaps(new Pos(9,2)));
		assertFalse(sh.overlaps(new Pos(5,3))); assertFalse(sh.overlaps(new Pos(5,1)));
		assertFalse(sh.overlaps(new Pos(6,3))); assertFalse(sh.overlaps(new Pos(6,1)));
		assertFalse(sh.overlaps(new Pos(7,3))); assertFalse(sh.overlaps(new Pos(7,1)));
		assertFalse(sh.overlaps(new Pos(8,3))); assertFalse(sh.overlaps(new Pos(8,1)));
		
		var som = new Ship(ShipType.SOMMERGIBILE, new Pos(1,2), Orientation.VERTICAL, "Invisible"); // da 1,2 a 1,2
		assertFalse(som.overlaps(new Pos(1,1)));
		assertFalse(som.overlaps(new Pos(1,3)));
		assertFalse(som.overlaps(new Pos(2,3)));
		assertTrue(som.overlaps(new Pos(1,2)));

		var inc = new Ship(ShipType.INCROCIATORE, new Pos(4,7), Orientation.HORIZONTAL, "General Smith"); // da 4,7 a 4,9
		assertFalse(inc.overlaps(new Pos(4,6)));
		assertFalse(inc.overlaps(new Pos(4,10)));
		assertFalse(inc.overlaps(new Pos(3,7)));
		assertFalse(inc.overlaps(new Pos(3,8)));
		assertFalse(inc.overlaps(new Pos(3,9)));
		assertFalse(inc.overlaps(new Pos(5,7)));
		assertFalse(inc.overlaps(new Pos(5,8)));
		assertFalse(inc.overlaps(new Pos(5,9)));
		assertTrue(inc.overlaps(new Pos(4,7)));
		assertTrue(inc.overlaps(new Pos(4,8)));
		assertTrue(inc.overlaps(new Pos(4,9)));
	}
	
	@Test
	void testOverlapsOtherShip() {
		var sh1 = new Ship(ShipType.PORTAEREI, new Pos(5,2), Orientation.VERTICAL, "Augustus II");   // da 5,2 a 8,2
		var sh2 = new Ship(ShipType.CACCIATORPEDINIERE, new Pos(5,1), Orientation.VERTICAL, "Augustus II");   // da 5,1 a 5,2
		assertFalse(sh1.overlaps(sh2));
		assertFalse(sh2.overlaps(sh1));
		var sh3 = new Ship(ShipType.CACCIATORPEDINIERE, new Pos(9,2), Orientation.VERTICAL, "Augustus II");   // da 9,2 a 10,2
		assertFalse(sh3.overlaps(sh1));
		assertFalse(sh1.overlaps(sh3));
		var inc1 = new Ship(ShipType.INCROCIATORE, new Pos(4,7), Orientation.HORIZONTAL, "General Smith"); // da 4,7 a 4,9
		assertFalse(inc1.overlaps(sh1));
		assertFalse(inc1.overlaps(sh2));
		assertFalse(inc1.overlaps(sh3));
		var inc2 = new Ship(ShipType.INCROCIATORE, new Pos(5,1), Orientation.HORIZONTAL, "General Grant"); // da 5,1 a 5,3
		assertTrue(inc2.overlaps(sh1));
		assertTrue(inc2.overlaps(sh2));
		assertFalse(inc2.overlaps(sh3));
		var som = new Ship(ShipType.SOMMERGIBILE, new Pos(5,2), Orientation.VERTICAL, "Invisible"); // da 5,2 a 5,2
		assertTrue(inc2.overlaps(som));
		assertTrue(inc2.overlaps(sh2));
		assertFalse(inc2.overlaps(sh3));
		assertTrue(inc2.overlaps(sh1));
		assertFalse(inc2.overlaps(inc1));
	}
	
}
