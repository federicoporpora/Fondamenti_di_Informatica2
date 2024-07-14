package trainstats.model.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import trainstats.model.Train;
import trainstats.model.Recording;
import trainstats.model.FinalDestinationStatProvider;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


class FinalDestinationStatProviderTest {
	
	private static Train train;
	private static List<Recording> recList;
	
	@BeforeAll
	static void createFakeTrain() {
		recList = new ArrayList<>();
		recList.add(new Recording("Milano C.le", Optional.empty(), Optional.empty(), Optional.of(LocalTime.of(13,10)), Optional.of(LocalTime.of(13,21)) ));
		recList.add(new Recording("Lodi",        Optional.of(LocalTime.of(13,45)), Optional.of(LocalTime.of(13,55)), Optional.of(LocalTime.of(13,47)), Optional.of(LocalTime.of(13,59)) ));
		recList.add(new Recording("Piacenza",    Optional.of(LocalTime.of(14,12)), Optional.of(LocalTime.of(14,22)), Optional.of(LocalTime.of(14,14)), Optional.of(LocalTime.of(14,30)) ));
		recList.add(new Recording("Fidenza",     Optional.of(LocalTime.of(14,35)), Optional.of(LocalTime.of(14,50)), Optional.of(LocalTime.of(14,38)), Optional.of(LocalTime.of(14,53)) ));
		recList.add(new Recording("Parma",       Optional.of(LocalTime.of(14,58)), Optional.of(LocalTime.of(15,10)), Optional.of(LocalTime.of(15,01)), Optional.of(LocalTime.of(15,13)) ));
		recList.add(new Recording("Reggio Emilia", Optional.of(LocalTime.of(15,18)), Optional.of(LocalTime.of(15,30)), Optional.of(LocalTime.of(15,20)), Optional.of(LocalTime.of(15,35)) ));
		recList.add(new Recording("Modena",      Optional.of(LocalTime.of(15,36)), Optional.of(LocalTime.of(15,50)), Optional.of(LocalTime.of(15,38)), Optional.of(LocalTime.of(15,55)) ));
		recList.add(new Recording("Bologna C.le",  Optional.of(LocalTime.of(16,10)), Optional.of(LocalTime.of(16,26)), Optional.empty(), Optional.empty() ));
		train = new Train(recList);
	}
	
	@Test
	void testOK() {
		var provider = new FinalDestinationStatProvider();
		assertEquals(16, provider.getStatistics(train));
		assertFalse(provider.hasThreshold());
		assertThrows(UnsupportedOperationException.class, () -> provider.setThreshold(0));
		assertThrows(UnsupportedOperationException.class, () -> provider.getThreshold());
	}
}
