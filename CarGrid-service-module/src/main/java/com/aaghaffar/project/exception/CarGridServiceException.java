/**
 * 
 */
package com.aaghaffar.project.exception;

/**
 * @author Amr Abdel-Ghaffar
 * Generic service layer exception
 */
public class CarGridServiceException extends Exception{

    /**
     * 
     */
    private static final long serialVersionUID = 9176904665996525202L;
    
    private int errCode;

    public CarGridServiceException() {
	super();
    }

    public CarGridServiceException(String message, Throwable ex) {
	super(message, ex);
    }

    public CarGridServiceException(String message) {
	super(message);
    }


    /**
     * @param message
     */
    public CarGridServiceException(int errCode, String message) {
	super(message);
	this.errCode = errCode;
    }

    /**
     * @param message
     */
    public CarGridServiceException(int errCode, String message, Throwable ex) {
	super(message, ex);
	this.errCode = errCode;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }
}
