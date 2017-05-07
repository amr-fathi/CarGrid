/**
 * 
 */
package com.aaghaffar.project.exception;

/**
 * @author Amr Abdel-Ghaffar
 * Exception class representing errors due to invalid inputs
 */
public class InvalidInputException extends ValidationException {

    /**
     * 
     */
    private static final long serialVersionUID = -2347245761474100086L;

    /**
     * 
     */
    public InvalidInputException() {
	super();
    }

    /**
     * @param arg0
     */
    public InvalidInputException(String message) {
	super(message);
    }

    public InvalidInputException(String message, Throwable ex) {
	super(message, ex);
    }

    public InvalidInputException(int errorCode, String message) {
	super(errorCode, message);
    }

    public InvalidInputException(int errorCode, String message, Throwable ex) {
	super(errorCode, message, ex);
    }
}
