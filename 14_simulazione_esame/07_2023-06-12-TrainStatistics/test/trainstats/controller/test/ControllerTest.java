package trainstats.controller.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import trainstats.controller.Controller;
import trainstats.model.Train;
import trainstats.model.AverageStatProvider;
import trainstats.model.FinalDestinationStatProvider;
import trainstats.model.Recording;
import trainstats.model.StatProvider;
import trainstats.model.ThresholdStatProvider;


class ControllerTest {

	private static Train train;
	private static List<Recording> recList;
	
	@BeforeAll
	static void creaTrenoFake() {
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
	
	private static List<StatProvider> providers = List.of(
			new FinalDestinationStatProvider(),
			new AverageStatProvider(),
			new ThresholdStatProvider(5)
			);
		
	Controller controller;
	
	@Test
	void testOK_Ctor() {
		controller = new Controller(new TreeMap<>(Map.of("fake train", train)), providers);
		//
		assertEquals(providers, controller.getAvailableProviders());
		assertEquals(providers.get(0), controller.getCurrentProvider());
		//
		assertEquals(List.of("fake train"), controller.getAvailableTrains());
		assertEquals(train, controller.getTrain("fake train"));
	}
		
	@Test
	void testOK_Statistics() {
		controller = new Controller(new TreeMap<>(Map.of("fake train", train)), providers);
		// il default provider è il primo, ovvero FinalDestination
		assertEquals(16, controller.getStatistics("fake train"));
		assertFalse(controller.hasActiveThreshold());
		// altri provider
		controller.setCurrentProvider(providers.get(1)); // Average
		assertEquals(13, controller.getStatistics("fake train"));
		assertEquals(13, controller.getStatsValue(train));
		assertFalse(controller.hasActiveThreshold());
		//
		controller.setCurrentProvider(providers.get(2)); // Threshold
		assertTrue(controller.hasActiveThreshold());
		assertEquals(100, controller.getStatistics("fake train"));
		assertEquals(100, controller.getStatsValue(train));
		controller.setCurrentProvider(providers.get(0)); // FinalDest
		assertEquals(16, controller.getStatistics("fake train"));
		assertEquals(16, controller.getStatsValue(train));
	}
	
	@Test
	void testOK_StatisticsWithThreshold() {
		controller = new Controller(new TreeMap<>(Map.of("fake train", train)), providers);
		controller.setCurrentProvider(providers.get(2)); // Threshold
		assertTrue(controller.hasActiveThreshold());
		//
		assertEquals(5, controller.getThreshold());
		assertEquals(100, controller.getStatistics("fake train"));
		assertEquals(100, controller.getStatsValue(train));
		controller.setThreshold(10);
		assertEquals(10, controller.getThreshold());
		assertEquals(71, controller.getStatistics("fake train"));
		assertEquals(71, controller.getStatsValue(train));
		controller.setThreshold(15);
		assertEquals(15, controller.getThreshold());
		assertEquals(14, controller.getStatistics("fake train"));
		assertEquals(14, controller.getStatsValue(train));
	}
	
	@Test
	void testKO_OtherProvidersWithoutThreshold() {
		controller = new Controller(new TreeMap<>(Map.of("fake train", train)), providers);
		//
		controller.setCurrentProvider(providers.get(0)); // FinalDest
		assertThrows(UnsupportedOperationException.class, () -> controller.getThreshold());
		//
		controller.setCurrentProvider(providers.get(1)); // Average
		assertThrows(UnsupportedOperationException.class, () -> controller.getThreshold());
	}

}
