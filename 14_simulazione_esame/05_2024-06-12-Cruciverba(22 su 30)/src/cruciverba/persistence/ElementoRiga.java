package cruciverba.persistence;

import java.util.Objects;

public record ElementoRiga(String string, int pos) {

	public ElementoRiga {
		Objects.requireNonNull(string, "ElementoRiga: item non può essere null");
		if(string.isBlank()) throw new IllegalArgumentException("ElementoRiga: stringa non può essere vuota o blank");
		if(pos<0) throw new IllegalArgumentException("ElementoRiga: pos non può essere negativo");
	}
	
}
