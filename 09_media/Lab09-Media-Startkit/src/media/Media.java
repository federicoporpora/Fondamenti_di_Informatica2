package media;

public abstract class Media {

	String title = null;
	int year = -1;
	
	public Media(int year, String title) {
		this.year = year;
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public int getYear() {
		return year;
	}
	
	public abstract Type getType();

	public void setTitle(String title) {
		this.title = title;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Media m) {
			if (this.getYear() == m.getYear() && this.title.equals(m.title)) return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Media [title=" + title + ", year=" + year + "]";
	}

}
