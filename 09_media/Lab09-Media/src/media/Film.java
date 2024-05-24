package media;

import java.util.Arrays;

import media.filters.HasDuration;
import media.filters.HasGenre;

public class Film extends Media implements HasGenre, HasDuration {

	private String[] actors = null;
	private String director = null;
	private int duration = -1;
	private String genre = null;

	public Film(String title, int year, String director, int duration, String[] actors, String genre) {
		super(year, title);
		this.actors = actors;
		this.director = director;
		this.duration = duration;
		this.genre = genre;
	}

	public String[] getActors() {
		return actors;
	}
	public String getDirector() {
		return director;
	}
	public int getDuration() {
		return duration;
	}
	public String getGenre() {
		return genre;
	}
	@Override
	public Type getType() {
		return Type.FILM;
	}
	public void setActors(String[] actors) {
		this.actors = actors;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}

	public boolean equals(Object o) {
		if (o instanceof Film f) {
			if (utils.StringUtils.areEquivalent(this.actors, f.actors) && this.director.equals(f.director) && this.duration == f.duration
					&& this.genre.equals(f.genre) && super.equals(o)) return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Film [actors=" + Arrays.toString(actors) + ", director=" + director + ", duration=" + duration
				+ ", genre=" + genre + ", title=" + title + ", year=" + year + "]";
	}

}
