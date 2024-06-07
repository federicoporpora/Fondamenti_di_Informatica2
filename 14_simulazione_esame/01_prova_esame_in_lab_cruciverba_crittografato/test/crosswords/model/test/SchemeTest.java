package crosswords.model.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import crosswords.model.*;

public class SchemeTest {

	@Test
	public void testOKBasicUnconfigured() {
		Scheme scheme = new Scheme(5);
		// System.out.print(scheme);
		assertEquals(5, scheme.getSize());
		assertFalse(scheme.isConfigured());
		// se non è configurato non si può invocare alcun metodo!
	}

	@Test
	public void testOKBasicFilled() {
		Scheme scheme = new Scheme(5);
		scheme.setCellRow(0, new int[] {1,2,3,4,5});
		scheme.setCellRow(1, new int[] {9,10,0,6,3});
		scheme.setCellRow(2, new int[] {10,4,12,0,13});
		scheme.setCellRow(3, new int[] {0,12,10,15,0});
		scheme.setCellRow(4, new int[] {16,10,1,1,10});
		//System.out.print(scheme);
		assertEquals(5, scheme.getSize());
		assertTrue(scheme.isConfigured());
		assertArrayEquals(new int[] {10,4,12,0,13}, scheme.getCellRow(2));
	}
	
	@Test
	public void testKO_CtorZero() {
		assertThrows(IllegalArgumentException.class, () -> new Scheme(0));
	}

	@Test
	public void testKO_CtorNeg() {
		assertThrows(IllegalArgumentException.class, () -> new Scheme(-1));
	}
	
	@Test
	public void testKO_getCellRowNeg() {
		Scheme scheme = new Scheme(5);
		scheme.setCellRow(0, new int[] {1,2,3,4,5});
		scheme.setCellRow(1, new int[] {9,10,0,6,3});
		scheme.setCellRow(2, new int[] {10,4,12,0,13});
		scheme.setCellRow(3, new int[] {0,12,10,15,0});
		scheme.setCellRow(4, new int[] {16,10,1,1,10});
		assertEquals(5, scheme.getSize());
		assertTrue(scheme.isConfigured());
		assertThrows(IllegalArgumentException.class, () -> scheme.getCellRow(-1));
	}
	
	@Test
	public void testKO_getCellRow_RowOutOfRange() {
		Scheme scheme = new Scheme(5);
		scheme.setCellRow(0, new int[] {1,2,3,4,5});
		scheme.setCellRow(1, new int[] {9,10,0,6,3});
		scheme.setCellRow(2, new int[] {10,4,12,0,13});
		scheme.setCellRow(3, new int[] {0,12,10,15,0});
		scheme.setCellRow(4, new int[] {16,10,1,1,10});
		assertEquals(5, scheme.getSize());
		assertTrue(scheme.isConfigured());
		assertThrows(IllegalArgumentException.class, () -> scheme.getCellRow(5));
	}
	
	@Test
	public void testKO_getCell_ColOutOfRange() {
		Scheme scheme = new Scheme(5);
		scheme.setCellRow(0, new int[] {1,2,3,4,5});
		scheme.setCellRow(1, new int[] {9,10,0,6,3});
		scheme.setCellRow(2, new int[] {10,4,12,0,13});
		scheme.setCellRow(3, new int[] {0,12,10,15,0});
		scheme.setCellRow(4, new int[] {16,10,1,1,10});
		assertEquals(5, scheme.getSize());
		assertTrue(scheme.isConfigured());
		assertThrows(IllegalArgumentException.class, () -> scheme.getCell(3,5));
	}
	
	@Test
	public void testKO_getCell_RowOutOfRange() {
		Scheme scheme = new Scheme(5);
		scheme.setCellRow(0, new int[] {1,2,3,4,5});
		scheme.setCellRow(1, new int[] {9,10,0,6,3});
		scheme.setCellRow(2, new int[] {10,4,12,0,13});
		scheme.setCellRow(3, new int[] {0,12,10,15,0});
		scheme.setCellRow(4, new int[] {16,10,1,1,10});
		assertEquals(5, scheme.getSize());
		assertTrue(scheme.isConfigured());
		assertThrows(IllegalArgumentException.class, () -> scheme.getCell(5,2));
	}
	
	@Test
	public void testKO_setCellRow_ArgNull() {
		Scheme scheme = new Scheme(5);
		assertThrows(NullPointerException.class, () -> scheme.setCellRow(2, null));
	}
	
	@Test
	public void testKO_setCellRow_WrongArgLength() {
		Scheme scheme = new Scheme(5);
		assertThrows(IllegalArgumentException.class, () -> scheme.setCellRow(2, new int[] {4,12,0,13}));
	}
	
	@Test
	public void testKO_setCellRow_RowOutOfRange() {
		Scheme scheme = new Scheme(5);
		assertThrows(IllegalArgumentException.class, () -> scheme.setCellRow(2, new int[] {27,4,12,0,13}));
	}
	
	@Test
	public void testKO_setCellRow_LessThan0() {
		Scheme scheme = new Scheme(5);
		assertThrows(IllegalArgumentException.class, () -> scheme.setCellRow(2, new int[] {25,4,12,-1,13}));
	}
}
