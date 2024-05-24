package media;

import java.util.Arrays;

public class Photo extends Media {

	private String[] authors = null;
	
	public Photo(String title, int year, String[] authors) {
		super(year, title);
		this.authors = authors;
	}

	public String[] getAuthors() {
		return authors;
	}

	@Override
	public Type getType() {
		return Type.PHOTO;
	}
	
	public void setAuthors(String[] authors) {
		this.authors = authors;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Photo p) {
			if (utils.StringUtils.areEquivalent(this.authors, p.authors) && super.equals(o)) return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Photo [authors=" + Arrays.toString(authors) + ", title=" + title + ", year=" + year + "]";
	}
	
}
