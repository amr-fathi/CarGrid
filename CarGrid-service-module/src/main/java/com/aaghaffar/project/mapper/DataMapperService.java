/**
 * 
 */
package com.aaghaffar.project.mapper;

import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import com.aaghaffar.project.exception.InvalidConfigException;
import com.aaghaffar.project.exception.InvalidInputException;
import com.aaghaffar.project.exception.PositionOutOfBoundiresException;
import com.aaghaffar.project.exception.ValidationException;
import com.aaghaffar.project.model.Position;

/**
 * @author Amr Abdel-Ghaffar
 *
 */
public interface DataMapperService {
    /**
     * Method responsible for mapping input string (DTO) to Domain Objects
     * @param inputStr
     * @return, an Optional value holding a Pair composing the two parts of a navigation string (i.e. current position + navigation string)
     * @throws ValidationException
     * @throws PositionOutOfBoundiresException
     */
    Optional<Pair<Position, String>> mapInput(String inputStr) throws ValidationException, PositionOutOfBoundiresException;
    
    /**
     * Method responsible for mapping Domain objects to DTO (String)
     * @param p, input position to map
     * @return string representation of the position
     */
    String mapOutput(Position p);
}
