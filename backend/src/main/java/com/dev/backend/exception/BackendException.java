/**
 * 
 */
package com.dev.backend.exception;

/**
 * @author Jose Enrique Garcia
 *
 */
public class BackendException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5240555541561233268L;

	private BackendErrorCode errorCode;
	private String message;

	public BackendException(BackendErrorCode errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}

	public BackendErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(BackendErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
