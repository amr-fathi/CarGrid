/**
 * 
 */
package com.aaghaffar.project.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aaghaffar.project.exception.CarGridServiceException;

/**
 * @author Amr Abdel-Ghaffar
 *
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = CarGridServiceException.class)
    public String handleBaseException(CarGridServiceException e) {
	return String.format("ErrorCode: %1$s, ErrorMessage: %2$s", e.getErrCode(), e.getMessage());
    }
}
