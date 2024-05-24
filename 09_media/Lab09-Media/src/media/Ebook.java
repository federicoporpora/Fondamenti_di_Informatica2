package media;

import java.util.Arrays;

import media.filters.HasGenre;

public class Ebook extends Media implements HasGenre {

	private String[] authors = null;
	private String genre = null;
	
	public Ebook(String title, int year, String[] authors, String genre) {
		super(year, title);
		this.authors = authors;
		this.genre = genre;
	}

	public String[] getAuthors() {
		return authors;
	}

	public String getGenre() {
		return genre;
	}

	@Override
	public Type getType() {
		return Type.EBOOK;
	}
	
	public void setAuthors(String[] authors) {
		this.authors = authors;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Ebook e) {
			if (utils.StringUtils.areEquivalent(this.authors, e.authors) && this.genre.equals(e.genre)
					&& super.equals(o)) return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Ebook [authors=" + Arrays.toString(authors) + ", genre=" + genre + ", title=" + title + ", year=" + year + "]";
	}

}
