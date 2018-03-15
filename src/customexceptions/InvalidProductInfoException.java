package customexceptions;

import java.time.LocalDate;

import products.Product;

public final class InvalidProductInfoException extends Exception {

	public InvalidProductInfoException() {
		super();
	};
	
	public InvalidProductInfoException(String message) {
		super(message);
	}
	
	public InvalidProductInfoException(Throwable cause) {
		super(cause);
	}
	
	public InvalidProductInfoException(String message, Throwable cause) {
		super(message, cause);
	}
}
