package trainstats.model;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Train {	
	private List<Recording> recordings;
	private Map<String,Recording> recordingMap; // (Station,Recording)
	private Map<String,Long> delayMap; 			// (Station,Delay) // per ipotesi, esclude la prima stazione
	private String firstStation, lastStation;
	
	public Train(List<Recording> recordings) {
		if (recordings == null) throw new IllegalArgumentException("registrazioni del treno null");
		if (recordings.size() < 2) throw new IllegalArgumentException("non ci sono almeno 2 stazioni");
		if (recordings.get(0).getScheduledArrivalTime().isPresent() || recordings.get(0).getActualArrivalTime().isPresent())
			throw new IllegalArgumentException("la stazione di partenza ha orari di arrivo");
		if (recordings.get(recordings.size()-1).getScheduledDepartureTime().isPresent() || recordings.get(recordings.size()-1).getActualDepartureTime().isPresent())
			throw new IllegalArgumentException("la stazione di arrivo ha orari di partenza");
		this.recordings = recordings;
		this.recordingMap = new HashMap<String, Recording>();
		this.delayMap = new HashMap<String, Long>();
        for (Recording recording : recordings) {
            recordingMap.put(recording.getStation(), recording);
            if (recording.getActualArrivalTime().isPresent() || recording.getScheduledArrivalTime().isPresent()) {
            	long delayMinutes = Duration.between(recording.getScheduledArrivalTime().get(), recording.getActualArrivalTime().get()).toMinutes();
                delayMap.put(recording.getStation(), delayMinutes > 0 ? delayMinutes : 0);
            }
        }
        this.firstStation = recordings.get(0).getStation();
        this.lastStation = recordings.get(recordings.size() - 1).getStation();
	}

	@Override
	public String toString() {
		return  String.format("%-20s%8s%8s%8s%8s%n", "stazione", "a.p.","a.r.","p.p.","p.r.") + 
				recordings.stream().map(Recording::toString).collect(Collectors.joining(System.lineSeparator()));
	}

	public List<Recording> getRecordings() {
		return recordings;
	}

	public Map<String, Recording> getRecordingMap() {
		return recordingMap;
	}

	public Map<String, Long> getDelayMap() {
		return delayMap;
	}
	
	public String getFirstStation() {
		return firstStation;
	}

	public String getLastStation() {
		return lastStation;
	}
	
	
}
