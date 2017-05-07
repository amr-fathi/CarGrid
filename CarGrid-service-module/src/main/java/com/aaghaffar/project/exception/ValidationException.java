/**
 * 
 */
package com.aaghaffar.project.exception;

/**
 * @author Amr Abdel-Ghaffar
 * Generic Exception class representing errors resulting from business validations
 */
public class ValidationException extends CarGridServiceException {

    /**
     * 
     */
    private static final long serialVersionUID = 7248940082700343724L;

    /**
     * 
     */
    public ValidationException() {
	super();
    }

    /**
     * @param arg0
     * @param arg1
     */
    public ValidationException(String message, Throwable ex) {
	super(message, ex);
    }

    /**
     * @param message
     */
    public ValidationException(String message) {
	super(message);
    }

    public ValidationException(int errorCode, String message) {
	super(errorCode, message);
    }

    public ValidationException(int errorCode, String message, Throwable ex) {
	super(errorCode, message, ex);
    }
}
