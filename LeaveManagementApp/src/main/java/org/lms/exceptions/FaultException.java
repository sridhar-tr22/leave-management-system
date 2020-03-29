package org.lms.exceptions;

/**
 * @author User1
 *
 */
public class FaultException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public FaultException(String message) {
		super(message);
	}
	
	public FaultException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
