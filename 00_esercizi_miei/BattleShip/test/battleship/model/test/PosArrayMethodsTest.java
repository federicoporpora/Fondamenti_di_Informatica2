package battleship.model.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import battleship.model.Pos;


class PosArrayMethodsTest {

	@Test
	void testContains() {
		Pos[] v = {new Pos(1,5), new Pos(2,5), new Pos(3,5), new Pos(1,1), new Pos(1,2), new Pos(6,7), new Pos(6,8), new Pos(4,4)};
		for (Pos p : v) assertTrue(Pos.contains(v, p));
		assertFalse(Pos.contains(v, new Pos(1,4)));
		assertFalse(Pos.contains(v, new Pos(10,10)));
	}
	
	@Test
	void testCommons1() {
		Pos[] v1 = {new Pos(1,5), new Pos(2,5), new Pos(3,5), new Pos(1,1), new Pos(1,2), new Pos(6,7), new Pos(6,8), new Pos(4,4)};
		Pos[] v2 = {new Pos(3,4), new Pos(1,5), new Pos(6,8), new Pos(4,5), new Pos(4,6), new Pos(4,7), new Pos(1,1)};
		Pos[] v3 = {new Pos(1,5), new Pos(1,1), new Pos(6,8)};
		Pos[] result = Pos.findCommons(v1, v2); 
		for (Pos p : v3) assertTrue(Pos.contains(result, p));
	}
	
	@Test
	void testNoCommons() {
		Pos[] v1 = {new Pos(1,5), new Pos(2,5), new Pos(3,5), new Pos(1,1), new Pos(1,2), new Pos(6,7), new Pos(6,8), new Pos(4,4)};
		Pos[] v2 = {new Pos(3,4)};
		Pos[] result = Pos.findCommons(v1, v2); 
		assertEquals(0, result.length);
	}
	
	@Test
	void testCommons2() {
		Pos[] v1 = {new Pos(1,5), new Pos(2,5), new Pos(3,5), new Pos(1,1), new Pos(1,2), new Pos(6,7), new Pos(6,8), new Pos(4,4)};
		Pos[] v2 = {new Pos(2,5), new Pos(1,5), new Pos(1,1), new Pos(3,5), new Pos(6,8), new Pos(6,7), new Pos(1,2), new Pos(4,4)};
		Pos[] result = Pos.findCommons(v1, v2); 
		for (Pos p : v1) assertTrue(Pos.contains(result, p));
	}
	
}
