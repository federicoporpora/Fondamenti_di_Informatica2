package trainstats.model.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import trainstats.model.Recording;


class RecordingTest {

	@Test
	void testOK_Ctor_AllPresent() {
		var r = new Recording("Paperopoli", Optional.of(LocalTime.of(12,30)), Optional.of(LocalTime.of(12,37)),
				Optional.of(LocalTime.of(12,34)), Optional.of(LocalTime.of(12,42)));
		assertEquals("Paperopoli", r.getStation());
		assertEquals(LocalTime.of(12,30), r.getScheduledArrivalTime().get());
		assertEquals(LocalTime.of(12,37), r.getActualArrivalTime().get());
		assertEquals(LocalTime.of(12,34), r.getScheduledDepartureTime().get());
		assertEquals(LocalTime.of(12,42), r.getActualDepartureTime().get());
	}

	@Test
	void testOK_Ctor_ArrivalsNotPresent() {
		var r = new Recording("Paperopoli", Optional.empty(), Optional.empty(),
				Optional.of(LocalTime.of(12,34)), Optional.of(LocalTime.of(12,42)));
		assertEquals("Paperopoli", r.getStation());
		assertTrue(r.getScheduledArrivalTime().isEmpty());
		assertTrue(r.getActualArrivalTime().isEmpty());
		assertEquals(LocalTime.of(12,34), r.getScheduledDepartureTime().get());
		assertEquals(LocalTime.of(12,42), r.getActualDepartureTime().get());
	}

	@Test
	void testOK_Ctor_DeparturesNotPresent() {
		var r = new Recording("Paperopoli", Optional.of(LocalTime.of(12,30)), Optional.of(LocalTime.of(12,37)),
				Optional.empty(), Optional.empty());
		assertEquals("Paperopoli", r.getStation());
		assertEquals(LocalTime.of(12,30), r.getScheduledArrivalTime().get());
		assertEquals(LocalTime.of(12,37), r.getActualArrivalTime().get());
		assertTrue(r.getScheduledDepartureTime().isEmpty());
		assertTrue(r.getActualDepartureTime().isEmpty());
	}

	@Test
	void testOK_Equals() {
		var r1 = new Recording("Paperopoli", Optional.of(LocalTime.of(12,30)), Optional.of(LocalTime.of(12,37)),
				Optional.of(LocalTime.of(12,34)), Optional.of(LocalTime.of(12,42)));
		var r2 = new Recording("Paperopoli", Optional.of(LocalTime.of(12,30)), Optional.of(LocalTime.of(12,37)),
				Optional.of(LocalTime.of(12,34)), Optional.of(LocalTime.of(12,42)));
		assertEquals(r1,r2);
		assertEquals(r1.hashCode(),r2.hashCode());
	}

	@Test
	void testKO_Null_1arg() {
		assertThrows(IllegalArgumentException.class, () -> new Recording(null, Optional.of(LocalTime.of(12,30)), Optional.of(LocalTime.of(12,37)),
				Optional.of(LocalTime.of(12,34)), Optional.of(LocalTime.of(12,42))));
	}
	
	@Test
	void testKO_Null_2arg() {
		assertThrows(IllegalArgumentException.class, () -> new Recording("a", null, Optional.of(LocalTime.of(12,37)),
				Optional.of(LocalTime.of(12,34)), Optional.of(LocalTime.of(12,42))));
	}
	
	@Test
	void testKO_Null_3arg() {
		assertThrows(IllegalArgumentException.class, () -> new Recording("a", Optional.of(LocalTime.of(12,30)), null,
				Optional.of(LocalTime.of(12,34)), Optional.of(LocalTime.of(12,42))));
	}
	
	@Test
	void testKO_Null_4arg() {
		assertThrows(IllegalArgumentException.class, () -> new Recording("a", Optional.of(LocalTime.of(12,30)), Optional.of(LocalTime.of(12,37)),
				null, Optional.of(LocalTime.of(12,42))));
	}
	
	@Test
	void testKO_Null_5arg() {
		assertThrows(IllegalArgumentException.class, () -> new Recording("a", Optional.of(LocalTime.of(12,30)), Optional.of(LocalTime.of(12,37)),
				Optional.of(LocalTime.of(12,34)), null));
	}

	@Test
	void testKO_SchedArrivalAfterDeparture() {
		assertThrows(IllegalArgumentException.class, () -> new Recording("a", Optional.of(LocalTime.of(12,30)), Optional.of(LocalTime.of(12,37)),
				Optional.of(LocalTime.of(12,24)), Optional.of(LocalTime.of(12,42))));
	}

	@Test
	void testKO_ActualArrivalAfterDeparture() {
		assertThrows(IllegalArgumentException.class, () -> new Recording("a", Optional.of(LocalTime.of(12,30)), Optional.of(LocalTime.of(12,37)),
				Optional.of(LocalTime.of(12,24)), Optional.of(LocalTime.of(12,36))));
	}

	@Test
	void testKO_SchedArrivalAfterActualDeparture() {
		assertThrows(IllegalArgumentException.class, () -> new Recording("a", Optional.of(LocalTime.of(12,30)), Optional.of(LocalTime.of(12,37)),
				Optional.of(LocalTime.of(12,32)), Optional.of(LocalTime.of(12,29))));
	}

	@Test
	void testKO_SchedArrivalEmptyButActualArrivalPresent() {
		assertThrows(IllegalArgumentException.class, () -> new Recording("a", Optional.empty(), Optional.of(LocalTime.of(12,37)),
				Optional.of(LocalTime.of(12,32)), Optional.of(LocalTime.of(12,29))));
	}

	@Test
	void testKO_ActualArrivalEmptyButScheduledArrivalPresent() {
		assertThrows(IllegalArgumentException.class, () -> new Recording("a", Optional.of(LocalTime.of(12,30)), Optional.empty(),
				Optional.of(LocalTime.of(12,32)), Optional.of(LocalTime.of(12,29))));
	}

	@Test
	void testKO_SchedDepartureEmptyButActualDeparturePresent() {
		assertThrows(IllegalArgumentException.class, () -> new Recording("a", Optional.of(LocalTime.of(12,30)), Optional.of(LocalTime.of(12,37)),
				Optional.empty(), Optional.of(LocalTime.of(12,29))));
	}

	@Test
	void testKO_ActualDepartureEmptyButScheduledDeparturePresent() {
		assertThrows(IllegalArgumentException.class, () -> new Recording("a", Optional.of(LocalTime.of(12,30)), Optional.of(LocalTime.of(12,37)), 
				Optional.of(LocalTime.of(12,32)), Optional.empty()));
	}
	
	@Test
	void testKO_ThreeMissingTimes_1stPresent() {
		assertThrows(IllegalArgumentException.class, () -> new Recording("a", Optional.of(LocalTime.of(12,30)), Optional.empty(), 
				Optional.empty(), Optional.empty() ));
	}

	@Test
	void testKO_ThreeMissingTimes_2ndPresent() {
		assertThrows(IllegalArgumentException.class, () -> new Recording("a", Optional.empty(), Optional.of(LocalTime.of(12,37)), 
				Optional.empty(), Optional.empty() ));
	}

	@Test
	void testKO_ThreeMissingTimes_3rdPresent() {
		assertThrows(IllegalArgumentException.class, () -> new Recording("a", Optional.empty(), Optional.empty(), 
				Optional.of(LocalTime.of(12,32)), Optional.empty() ));
	}

	@Test
	void testKO_ThreeMissingTimes_4thPresent() {
		assertThrows(IllegalArgumentException.class, () -> new Recording("a", Optional.empty(), Optional.empty(), 
				Optional.empty(), Optional.of(LocalTime.of(12,41)) ));
	}


}

