package trainstats.model;

public class FinalDestinationStatProvider implements StatProvider {

	@Override
	public long getStatistics(Train train) {
		return train.getDelayMap().get(train.getLastStation());
	}

	@Override
	public String toString() {
		return "Ultima stazione";
	}
	
	@Override
	public String getStatsMessage(Train train) {
		return "Ritardo all'ultima stazione: " + getStatistics(train) + " min.";
	}

	public boolean hasThreshold() { 
		return false;
	}
	
	public void setThreshold(int threshold) {
		throw new UnsupportedOperationException("No threshold in FinalDestinationStatProvider");
	}
	
	public int getThreshold() {
		throw new UnsupportedOperationException("No threshold in FinalDestinationStatProvider");
	}
}
