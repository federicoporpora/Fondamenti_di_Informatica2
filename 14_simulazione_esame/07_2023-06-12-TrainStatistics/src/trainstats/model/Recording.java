package trainstats.model;

import java.util.Optional;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Objects;

public class Recording {
	
	private static final String UNDEFINED_TIME = " --- ";
	private Optional<LocalTime> scheduledArrival, actualArrival, scheduledDeparture, actualDeparture;
	private DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.ITALY);
	private String station;

	public Recording(String station, Optional<LocalTime> scheduledArrival, Optional<LocalTime> actualArrival, 
									 Optional<LocalTime> scheduledDeparture, Optional<LocalTime> actualDeparture) {
		if (station == null || scheduledArrival == null || actualArrival == null || scheduledDeparture == null || actualDeparture == null)
			throw new IllegalArgumentException("un dato in input del costruttore è null");
		//
		if(scheduledArrival.isPresent() && actualArrival.isEmpty()) 
			throw new IllegalArgumentException("arrivo previsto e arrivo reale devono essere o entrambi presenti o entrambi assenti");
		if(scheduledDeparture.isPresent() && actualDeparture.isEmpty()) 
			throw new IllegalArgumentException("partenza prevista e partenza reale devono essere o entrambe presenti o entrambe assenti");
		if(scheduledArrival.isEmpty() && actualArrival.isEmpty() && scheduledDeparture.isEmpty() || 
		   scheduledArrival.isEmpty() && actualArrival.isEmpty() && actualDeparture.isEmpty() ||	
		   scheduledArrival.isEmpty() && scheduledDeparture.isEmpty() && actualDeparture.isEmpty() ||
		   actualArrival.isEmpty() && scheduledDeparture.isEmpty() && actualDeparture.isEmpty() ) 
			throw new IllegalArgumentException("non possono mancare tre orari su quattro");
		//
		if (scheduledDeparture.isPresent() && scheduledArrival.isPresent() &&
				scheduledDeparture.get().isBefore(scheduledArrival.get())) throw new IllegalArgumentException("la partenza non può essere dopo all'arrivo");
		//
		if (actualDeparture.isPresent() && scheduledArrival.isPresent() &&
				actualDeparture.get().isBefore(scheduledArrival.get()) ||
			actualDeparture.isPresent() && scheduledDeparture.isPresent() &&
				actualDeparture.get().isBefore(scheduledDeparture.get()))
			throw new IllegalArgumentException("la partenza non può essere prima dell'arrivo previsto o della partenza prevista");
		this.scheduledArrival = scheduledArrival;
		this.actualArrival = actualArrival;
		this.scheduledDeparture = scheduledDeparture;
		this.actualDeparture = actualDeparture;
		this.station=station;
	}

	public String getStation() {
		return station;
	}

	public Optional<LocalTime> getScheduledArrivalTime() {
		return scheduledArrival;
	}

	public Optional<LocalTime> getActualArrivalTime() {
		return actualArrival;
	}

	public Optional<LocalTime> getScheduledDepartureTime() {
		return scheduledDeparture;
	}

	public Optional<LocalTime> getActualDepartureTime() {
		return actualDeparture;
	}

	private String formatTime(Optional<LocalTime> time) {
		return time.isEmpty() ? UNDEFINED_TIME : formatter.format(time.get());
	}
	
	@Override
	public String toString() {
		return 	String.format("%-20s", station) + "\t" + 
				formatTime(scheduledArrival) + "\t" + formatTime(actualArrival) + "\t" +
				formatTime(scheduledDeparture) + "\t" + formatTime(actualDeparture);
	}

	@Override
	public int hashCode() {
		return Objects.hash(station, scheduledArrival, actualArrival, scheduledDeparture, actualDeparture);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Recording other = (Recording) obj;
		return  Objects.equals(station, other.station) &&
				Objects.equals(scheduledArrival, other.scheduledArrival) && Objects.equals(actualArrival, other.actualArrival) &&
				Objects.equals(scheduledDeparture, other.scheduledDeparture) && Objects.equals(actualDeparture, other.actualDeparture);
	}	
	
}
