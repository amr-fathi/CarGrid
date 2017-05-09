/**
 * 
 */
package com.aaghaffar.project.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Amr Abdel-Ghaffar
 * Json representation of navigation errors
 */
@JsonInclude
public class NavigationError {
    int errorCode;
    String errorMessage;
    
    public NavigationError(int errorCode, String errorMessage) {
	this.errorCode = errorCode;
	this.errorMessage = errorMessage;
    }
    public int getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
