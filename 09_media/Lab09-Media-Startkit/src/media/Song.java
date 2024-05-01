package media;

import media.filters.HasDuration;
import media.filters.HasGenre;

public class Song extends Media implements HasDuration, HasGenre{

	private int duration = -1;
	private String genre = null;
	private String singer = null;

	public Song(String title, int year, String singer, int duration, String genre) {
		super(year, title);
		this.duration = duration;
		this.genre = genre;
		this.singer = singer;
	}

	public int getDuration() {
		return duration;
	}

	public String getGenre() {
		return genre;
	}

	public String getSinger() {
		return singer;
	}

	@Override
	public Type getType() {
		return Type.SONG;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Song s) {
			if (this.duration == s.duration && this.genre.equals(s.genre) && this.singer.equals(s.singer) && super.equals(o)) return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Song [duration=" + duration + ", genre=" + genre + ", singer=" + singer + ", title=" + title + ", year="
				+ year + "]";
	}

}
