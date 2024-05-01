package media.filters;

import media.Media;

public class DurationFilter implements Filter {

	private int duration = -1;
	
	public DurationFilter(int durata) {
		setDuration(durata);
	}

	public void setDuration(int durata) {
		this.duration = durata;
	}
	
	@Override
	public boolean filter(Media media) {
		if (media instanceof HasDuration m) {
			return (this.duration == 0 || (m.getDuration() <= this.duration));
		}
		return false;
	}

}
