package controller;

import media.Media;
import media.collection.MediaCollection;
import media.filters.Filter;

public class MediaController {

	private MediaCollection allMedias = null;
	
	public MediaController() {
		allMedias = new MediaCollection();
	}
	
	public MediaCollection getAll() {
		MediaCollection res = new MediaCollection(allMedias.size());
		for (int i = 0; i < allMedias.size(); i++) {
			res.add(allMedias.get(i));
		}
		return res;
	}
	
	public boolean add(Media m) {
		if (allMedias.indexOf(m) >= 0)
			return false;

		allMedias.add(m);
		return true;
	}
	
	public boolean remove(Media media) {
		if (allMedias.indexOf(media) != -1) {
			allMedias.remove(allMedias.indexOf(media));
			return true;
		}
		return false;
	}
	
	public MediaCollection find(Filter f) {
		MediaCollection res = new MediaCollection();
		for (int i = 0; i < allMedias.size(); i++) {
			if (f.filter(allMedias.get(i)))
				res.add(allMedias.get(i));
		}
		return res;		
	}

}