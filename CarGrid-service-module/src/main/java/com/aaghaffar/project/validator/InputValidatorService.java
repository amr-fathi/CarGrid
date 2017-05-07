/**
 * 
 */
package com.aaghaffar.project.validator;

import com.aaghaffar.project.exception.InvalidConfigException;
import com.aaghaffar.project.exception.InvalidInputException;
import com.aaghaffar.project.exception.PositionOutOfBoundiresException;
import com.aaghaffar.project.exception.ValidationException;

/**
 * @author Amr Abdel-Ghaffar
 *
 */
public interface InputValidatorService {
    /**
     * Method responsible for encapsulating the business validation logic for received input string
     * @param inputStr, input string to validate
     * @return true if valid, false otherwise
     * @throws ValidationException, thrown whenever invalid inputs are received
     * @throws PositionOutOfBoundiresException, thrown to denote input position out of grid boundries
     */
    boolean isValidInput(String inputStr) throws ValidationException, PositionOutOfBoundiresException;
}
