package trainstats.model;

public class ThresholdStatProvider implements StatProvider {
	
	private int threshold;
	
	public ThresholdStatProvider() {
		this.threshold=0;
	}

	public ThresholdStatProvider(int threshold) {
		this.threshold=threshold;
	}
	
	
	// ******************** DA COMPLETARE QUI ***************************
	@Override
	public long getStatistics(Train train) {
		int inRitardo = 0;
		for (long ritardo : train.getDelayMap().values()) {
			if (ritardo > threshold) inRitardo++;
		}
		return (long)(inRitardo/train.getDelayMap().values().size()) * 100;
	}
	// *************** FINE PARTE DA COMPLETARE **************************

	@Override
	public String toString() {
		return "A soglia";
	}

	@Override
	public String getStatsMessage(Train train) {
		return "Stazioni in cui il treno è giunto con oltre " + threshold + " min. di ritardo: " + getStatistics(train) + "%";
	}

	public boolean hasThreshold() { 
		return true;
	}
	
	public void setThreshold(int threshold) {
		this.threshold=threshold;
	}
	
	public int getThreshold() {
		return threshold; 
	}
}
