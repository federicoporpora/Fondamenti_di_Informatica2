package crosswords.controller.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import crosswords.controller.Controller;
import crosswords.model.*;

public class ControllerTest {

	@Test
	public void testOK_BasicUnconfigured() {
		var controller = new Controller(5);
		assertEquals(5, controller.getSize());
	}

	@Test
	public void testOK_BasicFilled() {
		Scheme scheme = new Scheme(5);
		scheme.setCellRow(0, new int[] {1,2,3,4,5});
		scheme.setCellRow(1, new int[] {9,10,0,6,3});
		scheme.setCellRow(2, new int[] {10,4,12,0,13});
		scheme.setCellRow(3, new int[] {0,12,10,15,0});
		scheme.setCellRow(4, new int[] {16,10,1,1,10});
		assertEquals(5, scheme.getSize());
		assertTrue(scheme.isConfigured());
		var controller = new Controller(scheme);
		assertEquals(5, controller.getSize());
	}
	
	@Test
	public void testKO_CtorZero() {
		assertThrows(IllegalArgumentException.class, () -> new Controller(0));
	}

	@Test
	public void testKO_CtorNeg() {
		assertThrows(IllegalArgumentException.class, () -> new Controller(-1));
	}
	
	@Test
	public void testKO_CtorNull() {
		assertThrows(NullPointerException.class, () -> new Controller(null));
	}
	
}
