package trainstats.model.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import trainstats.model.Train;
import trainstats.model.Recording;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


class TrainTestWithEarlyArrivals {
	
	private static Train train;
	private static List<Recording> recList;
	
	@BeforeAll
	static void createFakeTrain() {
		recList = new ArrayList<>();
		recList.add(new Recording("Milano C.le", Optional.empty(), Optional.empty(), Optional.of(LocalTime.of(13,10)), Optional.of(LocalTime.of(13,11)) ));
		recList.add(new Recording("Lodi",        Optional.of(LocalTime.of(13,45)), Optional.of(LocalTime.of(13,44)), Optional.of(LocalTime.of(13,47)), Optional.of(LocalTime.of(13,47)) ));
		recList.add(new Recording("Piacenza",    Optional.of(LocalTime.of(14,12)), Optional.of(LocalTime.of(14,12)), Optional.of(LocalTime.of(14,14)), Optional.of(LocalTime.of(14,15)) ));
		recList.add(new Recording("Fidenza",     Optional.of(LocalTime.of(14,35)), Optional.of(LocalTime.of(14,32)), Optional.of(LocalTime.of(14,38)), Optional.of(LocalTime.of(14,38)) ));
		recList.add(new Recording("Parma",       Optional.of(LocalTime.of(14,58)), Optional.of(LocalTime.of(15,01)), Optional.of(LocalTime.of(15,01)), Optional.of(LocalTime.of(15,03)) ));
		recList.add(new Recording("Reggio Emilia", Optional.of(LocalTime.of(15,18)), Optional.of(LocalTime.of(15,18)), Optional.of(LocalTime.of(15,20)), Optional.of(LocalTime.of(15,20)) ));
		recList.add(new Recording("Modena",      Optional.of(LocalTime.of(15,36)), Optional.of(LocalTime.of(15,33)), Optional.of(LocalTime.of(15,38)), Optional.of(LocalTime.of(15,38)) ));
		recList.add(new Recording("Bologna C.le",  Optional.of(LocalTime.of(16,10)), Optional.of(LocalTime.of(16,8)), Optional.empty(), Optional.empty() ));
		train = new Train(recList);
	}
	
	@Test
	void testOK_BasicGetters() {
		assertEquals(8, train.getRecordings().size());
		assertEquals(recList, train.getRecordings());
		assertEquals("Milano C.le", train.getFirstStation());
		assertEquals("Bologna C.le", train.getLastStation());
	}
	
	@Test
	void testOK_DelayMap() {
		var map = Map.of("Fidenza",0L, "Parma",3L, "Reggio Emilia",0L, "Modena",0L, "Bologna C.le",0L, "Lodi",0L, "Piacenza", 0L);
		System.out.println(map);
		assertEquals(map, train.getDelayMap());
	}
	
	@Test
	void testOK_RecordingMap() {
		var map = recList.stream().collect(Collectors.toMap(Recording::getStation, r->r));
		assertEquals(map, train.getRecordingMap());
	}
	

}
