/**
 * 
 */
package com.aaghaffar.project.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aaghaffar.project.api.model.NavigationError;
import com.aaghaffar.project.exception.CarGridServiceException;

/**
 * @author Amr Abdel-Ghaffar
 * This class acts as a global exception handler, specifying the generic service level exception to expect, 
 * And the corresponding action (an error message to be given to the user)
 *
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = CarGridServiceException.class)
    public NavigationError handleBaseException(CarGridServiceException e) {
	return new NavigationError(e.getErrCode(), e.getMessage());
//	return String.format("ErrorCode: %1$s, ErrorMessage: %2$s", e.getErrCode(), e.getMessage());
    }
}
