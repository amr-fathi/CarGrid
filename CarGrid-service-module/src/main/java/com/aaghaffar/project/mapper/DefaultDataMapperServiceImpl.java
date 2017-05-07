/**
 * 
 */
package com.aaghaffar.project.mapper;

import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aaghaffar.project.exception.InvalidConfigException;
import com.aaghaffar.project.exception.InvalidInputException;
import com.aaghaffar.project.exception.PositionOutOfBoundiresException;
import com.aaghaffar.project.exception.ValidationException;
import com.aaghaffar.project.model.Coordinate;
import com.aaghaffar.project.model.Orientation;
import com.aaghaffar.project.model.Position;
import com.aaghaffar.project.validator.InputValidatorService;

/**
 * @author Amr Abdel-Ghaffar
 * This class represents the component responsible for data mappings between DOs (Domain Objects) and DTOs (Data Transfer Objects)
 */
@Component
public class DefaultDataMapperServiceImpl implements DataMapperService {

    @Autowired
    InputValidatorService validator;
    
    private final static Logger log = Logger.getLogger(DefaultDataMapperServiceImpl.class);

    /* (non-Javadoc)
     * @see com.aaghaffar.project.mapper.DataMapperService#mapInput(java.lang.String)
     */
    @Override
    public Optional<Pair<Position, String>> mapInput(String inputStr) throws ValidationException, PositionOutOfBoundiresException {
	log.info("Initializing input mapping");
	if (validator.isValidInput(inputStr)) {
	    String[] tokens = inputStr.split(":");
	    String[] coordinates = tokens[0].split(",");
	    Position pos = new Position(Orientation.N, new Coordinate(
		    Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1])));
	    Pair<Position, String> pair = Pair.of(pos, inputStr);
	    return Optional.of(pair);
	}
	return Optional.empty();
    }

    /* (non-Javadoc)
     * @see com.aaghaffar.project.mapper.DataMapperService#mapOutput(com.aaghaffar.project.model.Position)
     */
    @Override
    public String mapOutput(Position p) {
	log.info("Initializing output mapping");
	if(p != null && p.getCoordinate() != null) {
	    return Integer.toString(p.getCoordinate().getX())+","+Integer.toString(p.getCoordinate().getY()).trim();
	}
	return "";
    }
}
