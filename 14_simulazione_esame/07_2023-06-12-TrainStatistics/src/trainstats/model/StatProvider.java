package trainstats.model;

public interface StatProvider {
	public long getStatistics(Train train);
	public String getStatsMessage(Train train);
	public boolean hasThreshold();
	public void setThreshold(int threshold);
	public int getThreshold();
}
