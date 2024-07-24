package pacchi.controller;

public class WrongPhaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public WrongPhaseException() {
		super();
	}

	public WrongPhaseException(String message) {
		super(message);
	}

	public WrongPhaseException(Throwable cause) {
		super(cause);
	}

	public WrongPhaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public WrongPhaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
