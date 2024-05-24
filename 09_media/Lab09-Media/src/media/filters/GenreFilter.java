package media.filters;

import media.Media;

public class GenreFilter implements Filter {

	public String genre = null;
	
	public GenreFilter(String genre) {
		setGenre(genre);
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public boolean filter(Media media) {
		if (media instanceof HasGenre m) {
			return (this.genre.equals(" ") || m.getGenre().equals(this.genre));
		}
		return false;
	}

}
