/**
 * 
 */
package com.aaghaffar.project.exception;


/**
 * @author Amr Abdel-Ghaffar
 * Enum representation of all errors that result in the service layer
 */
public enum ErrorCodes {
    EMPTY_INPUT_STRING(1, "Input string cannot be Empty."),
    INVALID_MAIN_DELIMITER(2, "Input string should be divided into 2 parts, delimited by %s"),
    INVALID_COORD_DELIMITER(3, "<current_position> should be divided into X and Y coordinates, delimited by %s"),
    EMPTY_NAV_STRING(4, "Navigation string cannot be Empty. "),
    EMPTY_COORD_STRING(5, "Coordinates string cannot be Empty. "),
    COORDS_NOT_INTEGERS(6, "Coordinates (X, Y) should be Integers"),
    DIM_NOT_INTEGERS(7, "Grid size configurations (grid.dim.x, grid.dim.y) should be Integers"),
    POS_OUT_OF_BOUNDRIES(8, "supplied %1$s coordinate should be >= 0 and <= %2$s"),
    INVALID_NAV_CHARS(9, "Invalid navigation characters used. Supported navigation characters are: L(Left), R(Right), F(Forward)"),
    ADVANCED_OUT_OF_BOUNDRIES(10, "Cannot move further towards %s. %s grid %s reached. Current position: %d, %d");
    


    private int errorCode;
    private String errorMsg;
    
    private ErrorCodes(int errorCode, String errorMsg) {
	this.errorCode = errorCode;
	this.errorMsg = errorMsg;
    }
    
    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public String toString() {
      return errorCode + ": " + errorMsg;
    }
}
