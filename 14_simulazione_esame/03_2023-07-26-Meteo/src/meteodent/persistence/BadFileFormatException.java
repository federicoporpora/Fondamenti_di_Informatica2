package meteodent.persistence;

public class BadFileFormatException extends Exception {

	private static final long serialVersionUID = 1L;

	public BadFileFormatException() {
	}

	public BadFileFormatException(String message) {
		super(message);
	}

}
