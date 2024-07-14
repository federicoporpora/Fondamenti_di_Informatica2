package trainstats.model;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class AverageStatProvider implements StatProvider {

	// ******************** DA COMPLETARE QUI ***************************
	@Override
	public long getStatistics(Train train) {
		long media = 0;
		for(long num : train.getDelayMap().values()) media += num;
		return media / train.getDelayMap().values().size();
	}
	// *************** FINE PARTE DA COMPLETARE **************************

	@Override
	public String toString() {
		return "Media ritardi";
	}

	@Override
	public String getStatsMessage(Train train) {
		return "Media ritardi: " + getStatistics(train) + " min.";
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
