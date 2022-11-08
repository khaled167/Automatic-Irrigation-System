package ais.exceptions;

public class InvalidKeyException extends RuntimeException {

	private static final long serialVersionUID = 3307685326654582960L;

	public InvalidKeyException() {
		super();
	}

	public InvalidKeyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidKeyException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidKeyException(String message) {
		super(message);
	}

	public InvalidKeyException(Throwable cause) {
		super(cause);
	}

}
