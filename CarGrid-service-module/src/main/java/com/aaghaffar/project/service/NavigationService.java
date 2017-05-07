/**
 * 
 */
package com.aaghaffar.project.service;

import com.aaghaffar.project.exception.CarGridServiceException;
import com.aaghaffar.project.exception.InvalidConfigException;
import com.aaghaffar.project.exception.InvalidInputException;
import com.aaghaffar.project.exception.PositionOutOfBoundiresException;


/**
 * @author Amr Abdel-Ghaffar
 *
 */
public interface NavigationService {
    /**
     * Method responsible for handling car navigation business logic
     * @param inputStr, string resembling input for navigation logic (e.g. 5,5:RFFLFFRRRFFF)
     * @return result position after navigation (e.g. 4,3)
     * @throws CarGridServiceException
     */
    String navigate(String inputStr) throws CarGridServiceException; 
}
