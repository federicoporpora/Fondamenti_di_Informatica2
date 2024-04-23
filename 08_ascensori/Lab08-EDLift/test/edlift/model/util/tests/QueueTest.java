package edlift.model.util.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edlift.model.util.Queue;

public class QueueTest {

	@Test
	public void testQueueOK() {
		Queue queue = new Queue(4);
		assertFalse(queue.hasItems());
		assertEquals(0, queue.size());
		System.out.println(queue.peek());
		assertEquals(Integer.MIN_VALUE, queue.peek());
	}

	@Test
	public void testQueueOK_OneInsert() {
		Queue queue = new Queue(4);
		assertTrue(queue.insert(11));
		assertTrue(queue.hasItems());
		assertEquals(1, queue.size());
		assertEquals(11, queue.peek());
	}
	
	@Test
	public void testQueueOK_TwoInserts() {
		Queue queue = new Queue(4);
		assertTrue(queue.insert(11));
		assertTrue(queue.insert(12));
		assertTrue(queue.hasItems());
		assertEquals(2, queue.size());
		assertEquals(11, queue.peek());
	}

	@Test
	public void testQueueOK_ThreeInserts() {
		Queue queue = new Queue(4);
		assertTrue(queue.insert(11));
		assertTrue(queue.insert(15));
		assertTrue(queue.insert(13));
		assertTrue(queue.hasItems());
		assertEquals(3, queue.size());
		assertEquals(11, queue.peek());
	}
	
	@Test
	public void testQueueOK_FourInserts() {
		Queue queue = new Queue(4);
		assertTrue(queue.insert(11));
		assertTrue(queue.insert(15));
		assertTrue(queue.insert(15));
		assertTrue(queue.insert(13));
		assertTrue(queue.hasItems());
		assertEquals(4, queue.size());
		assertEquals(11, queue.peek());
	}
	
	@Test
	public void testQueueKO_CapacityExceeded() {
		Queue queue = new Queue(4);
		queue.insert(11);
		queue.insert(12);
		queue.insert(13);
		queue.insert(11);
		assertFalse(queue.insert(15));
		assertEquals(11, queue.peek());
	}

	@Test
	public void testQueueOK_OneInsertAndOneExtract() {
		Queue queue = new Queue(4);
		assertTrue(queue.insert(11));
		assertTrue(queue.hasItems());
		assertEquals(1, queue.size());
		assertEquals(11, queue.peek());
		assertEquals(11, queue.extract());
		assertEquals(Integer.MIN_VALUE, queue.peek());
	}
	
	@Test
	public void testQueueOK_FourInsertsAndExtract() {
		Queue queue = new Queue(4);
		assertTrue(queue.insert(11));
		assertTrue(queue.insert(15));
		assertTrue(queue.insert(15));
		assertTrue(queue.insert(13));
		assertTrue(queue.hasItems());
		assertEquals(4, queue.size());
		assertEquals(11, queue.peek());
		assertEquals(11, queue.extract());
		assertTrue(queue.hasItems());
		assertEquals(3, queue.size());
		assertEquals(15, queue.peek());
		assertEquals(15, queue.extract());
		assertTrue(queue.hasItems());
		assertEquals(2, queue.size());
		assertEquals(15, queue.peek());
		assertEquals(15, queue.extract());
		assertTrue(queue.hasItems());
		assertEquals(1, queue.size());
		assertEquals(13, queue.peek());
		assertEquals(13, queue.extract());
		assertFalse(queue.hasItems());
		assertEquals(0, queue.size());
		assertEquals(Integer.MIN_VALUE, queue.peek());
	}
}
