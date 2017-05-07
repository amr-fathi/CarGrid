/**
 * 
 */
package com.aaghaffar.project.exception;

/**
 * @author Amr Abdel-Ghaffar
 * Exception class representing errors due to invalid configurations
 */
public class InvalidConfigException extends ValidationException {

    /**
     * 
     */
    private static final long serialVersionUID = 8857561287113675677L;

    /**
     * @param message
     */
    public InvalidConfigException(String message) {
	super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public InvalidConfigException(String message, Throwable ex) {
	super(message, ex);
    }

    public InvalidConfigException(int errorCode, String message, Throwable ex) {
	super(errorCode, message, ex);
    }
}
