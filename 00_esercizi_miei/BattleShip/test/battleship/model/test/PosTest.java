package battleship.model.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import battleship.model.Orientation;
import battleship.model.Pos;

class PosTest {

	@Test
	void testCtor() {
		var pos = new Pos(1,5);
		assertEquals(5, pos.col());
		assertEquals(1, pos.row());
	}

	@Test
	void testCtorString() {
		var pos = new Pos("E1");
		assertEquals(5, pos.col());
		assertEquals(1, pos.row());
		var extremePos = new Pos("E10");
		assertEquals(5, extremePos.col());
		assertEquals(10, extremePos.row());
	}
	
	@Test
	void testToString() {
		assertEquals("E4", new Pos(4,5).toString());
		assertEquals("J9", new Pos(9,10).toString());
		assertEquals("E4", new Pos(4,5).toString());
	}

	
	@Test
	void testNext() {
		var pos = new Pos(1,5);
		var nextVPos = pos.next(Orientation.VERTICAL);
		assertEquals(5, nextVPos.col());
		assertEquals(2, nextVPos.row());
		var nextHPos = pos.next(Orientation.HORIZONTAL);
		assertEquals(6, nextHPos.col());
		assertEquals(1, nextHPos.row());
	}
	
	@Test
	void testNextK() {
		var pos = new Pos(1,5);
		var nextVPos = pos.next(Orientation.VERTICAL, 4);
		assertEquals(5, nextVPos.col());
		assertEquals(5, nextVPos.row());
		var nextHPos = pos.next(Orientation.HORIZONTAL, 5);
		assertEquals(10, nextHPos.col());
		assertEquals(1, nextHPos.row());
	}
	
	@Test
	void testBoundaries() {
		var pos = new Pos(1,5);
		assertTrue(pos.isWithin(10));
		assertTrue(pos.isWithin(5));
		assertTrue(pos.exceeds(4, "boom"));
		assertFalse(pos.exceeds(5, "ok"));
	}
	
	@Test
	void testOrdinal() {
		assertEquals(0, new Pos(1,1).getOrdinal(10));
		assertEquals(34, new Pos(4,5).getOrdinal(10));
		assertEquals(89, new Pos(9,10).getOrdinal(10));
		assertEquals(19, new Pos(4,5).getOrdinal(5));
	}
	
	@Test
	void testIsColumnLE() {
		assertTrue(new Pos(1,1).isColumnLessOrEqualTo(new Pos(1,1)));
		assertTrue(new Pos(7,4).isColumnLessOrEqualTo(new Pos(4,5)));
		assertTrue(new Pos(7,5).isColumnLessOrEqualTo(new Pos(4,5)));
		assertTrue(new Pos(7,5).isColumnLessOrEqualTo(new Pos(9,10)));
		assertTrue(new Pos(10,8).isColumnLessOrEqualTo(new Pos(1,8)));
	}
	
	@Test
	void testIsColumnEquals() {
		assertTrue(new Pos(1,1).isColumnEqualTo(new Pos(1,1)));
		assertTrue(new Pos(7,5).isColumnEqualTo(new Pos(4,5)));
		assertTrue(new Pos(7,10).isColumnEqualTo(new Pos(9,10)));
		assertTrue(new Pos(10,8).isColumnEqualTo(new Pos(1,8)));
	}
	
	@Test
	void testIsColumnGE() {
		assertTrue(new Pos(1,1).isColumnGreaterOrEqualTo(new Pos(1,1)));
		assertTrue(new Pos(7,6).isColumnGreaterOrEqualTo(new Pos(4,5)));
		assertTrue(new Pos(7,5).isColumnGreaterOrEqualTo(new Pos(4,5)));
		assertTrue(new Pos(7,10).isColumnGreaterOrEqualTo(new Pos(9,9)));
		assertTrue(new Pos(10,8).isColumnGreaterOrEqualTo(new Pos(1,8)));
	}
	
	@Test
	void testIsRowLE() {
		assertTrue(new Pos(1,1).isRowLessOrEqualTo(new Pos(1,1)));
		assertTrue(new Pos(7,4).isRowLessOrEqualTo(new Pos(7,4)));
		assertTrue(new Pos(6,5).isRowLessOrEqualTo(new Pos(7,4)));
		assertTrue(new Pos(7,5).isRowLessOrEqualTo(new Pos(9,10)));
		assertTrue(new Pos(10,8).isRowLessOrEqualTo(new Pos(10,9)));
	}
	
	@Test
	void testIsRowEquals() {
		assertTrue(new Pos(1,1).isRowEqualTo(new Pos(1,1)));
		assertTrue(new Pos(7,5).isRowEqualTo(new Pos(7,8)));
		assertTrue(new Pos(10,10).isRowEqualTo(new Pos(10,7)));
		assertTrue(new Pos(8,2).isRowEqualTo(new Pos(8,6)));
	}
	
	@Test
	void testIsRowGE() {
		assertTrue(new Pos(1,1).isRowGreaterOrEqualTo(new Pos(1,1)));
		assertTrue(new Pos(8,6).isRowGreaterOrEqualTo(new Pos(7,5)));
		assertTrue(new Pos(9,5).isRowGreaterOrEqualTo(new Pos(9,5)));
		assertTrue(new Pos(7,10).isRowGreaterOrEqualTo(new Pos(7,9)));
		assertTrue(new Pos(10,8).isRowGreaterOrEqualTo(new Pos(1,8)));
	}
}
