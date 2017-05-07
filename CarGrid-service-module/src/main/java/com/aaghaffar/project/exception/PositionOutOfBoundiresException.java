/**
 * 
 */
package com.aaghaffar.project.exception;

/**
 * @author Amr Abdel-Ghaffar
 * Exception class representing errors due to invalid positions depending on grid size/dimensions
 */
public class PositionOutOfBoundiresException extends CarGridServiceException {

    /**
     * 
     */
    private static final long serialVersionUID = 4226249726656629578L;

    /**
     * @param arg0
     */
    public PositionOutOfBoundiresException(String message) {
	super(message);
    }

    public PositionOutOfBoundiresException(int errorCode, String message) {
	super(errorCode, message);
    }
}
