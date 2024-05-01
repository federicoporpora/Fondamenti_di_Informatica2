package media.filters;

import media.Media;
import media.Type;

public class TypeFilter implements Filter {

	private Type typeToFind;
	
	public TypeFilter(Type typeToFind) {
		setType(typeToFind);
	}

	public void setType(Type typeToFind) {
		this.typeToFind = typeToFind;
	}
	
	@Override
	public boolean filter(Media media) {
		if (media instanceof HasType m) {
			return (m.getType().equals(typeToFind));
		}
		return false;
	}

}
