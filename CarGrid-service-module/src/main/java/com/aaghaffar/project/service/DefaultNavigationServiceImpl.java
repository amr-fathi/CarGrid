/**
 * 
 */
package com.aaghaffar.project.service;

import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaghaffar.project.exception.CarGridServiceException;
import com.aaghaffar.project.exception.ErrorCodes;
import com.aaghaffar.project.exception.PositionOutOfBoundiresException;
import com.aaghaffar.project.exception.ValidationException;
import com.aaghaffar.project.mapper.DataMapperService;
import com.aaghaffar.project.model.Coordinate;
import com.aaghaffar.project.model.Orientation;
import com.aaghaffar.project.model.Position;
import com.aaghaffar.project.util.ConfigSource;

/**
 * @author Amr Abdel-Ghaffar This is the main service class representing the implementation of
 *         navigation business logic
 */
@Service
public class DefaultNavigationServiceImpl implements NavigationService {

    @Autowired
    DataMapperService dataMapperService;

    @Autowired
    ConfigSource configSrc;

    private int gridDimX;
    private int gridDimY;

    private final static Logger log = Logger.getLogger(DefaultNavigationServiceImpl.class);

    private void init() {
	gridDimX = Integer.parseInt(configSrc.getGridDimXstr());
	gridDimY = Integer.parseInt(configSrc.getGridDimYstr());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aaghaffar.project.service.NavigationService#navigate(java.lang.String)
     */
    @Override
    public String navigate(String inputStr) throws CarGridServiceException {
	Optional<Pair<Position, String>> opt;
	try {
	    opt = dataMapperService.mapInput(inputStr);
	} catch (ValidationException | PositionOutOfBoundiresException e) {
	    log.error(String.format("Error in navigate(). ErrorCode: %1$s, ErrorMessage: %2$s",
		    e.getErrCode(), e.getMessage()));
	    throw new CarGridServiceException(e.getErrCode(), e.getMessage(), e);
	}
	init();
	Position newPosition = null;
	if (opt.isPresent()) {
	    Pair<Position, String> inputPair = opt.get();
	    try {
		newPosition = navigate(inputPair.getLeft(), inputPair.getRight());
		log.info("Navigation performed successfully. Result: "+newPosition.toString());
	    } catch (PositionOutOfBoundiresException e) {
		log.error(String.format("Error in navigate(). ErrorCode: %1$s, ErrorMessage: %2$s",
			e.getErrCode(), e.getMessage()));
		throw new CarGridServiceException(e.getErrCode(), e.getMessage(), e);
	    }
	}
	return dataMapperService.mapOutput(newPosition);
    }

    /**
     * Method responsible for handling navigation logic depending on the passed parameters. 'R'
     * results into shifting direction towards right, 'L' results into shifting direction towards
     * left, and 'F' results into advancing ahead
     * 
     * @param pos
     *            , current position to begin navigation from
     * @param navStr
     *            , set of instructions denoting navigation steps
     * @return
     * @throws PositionOutOfBoundiresException
     */
    private Position navigate(Position pos, String navStr) throws PositionOutOfBoundiresException {
	log.info("Initializing navigation sequence");
	for (char c : navStr.toCharArray()) {
	    switch (c) {
	    case 'R':
	    case 'L':
		pos.setOrientation(reOrient(pos.getOrientation(), (char) c));
		break;
	    case 'F':
		advance(pos.getOrientation(), pos.getCoordinate());
		break;
	    default:
		break;
	    }
	}
	return pos;
    }

    /**
     * Method responsible for handling advancement logic; depending on the current orientation (i.e.
     * N, W, E, S), the advancement is determined (i.e. advance towards +ve X-axis or -ve X-axis and
     * so on.
     * 
     * @param currOrient
     *            , the current orientation of the car (North, West, East, South)
     * @param currCoord
     *            , the current coordinates of the car to begin navigation from
     * @throws PositionOutOfBoundiresException
     */
    private void advance(Orientation currOrient, Coordinate currCoord)
	    throws PositionOutOfBoundiresException {
	switch (currOrient) {
	case N:
	    log.debug("advancing 1 step forward towards North");
	    if ((currCoord.getY() + 1) > gridDimY)
		throw new PositionOutOfBoundiresException(
			ErrorCodes.ADVANCED_OUT_OF_BOUNDRIES.getErrorCode(), String.format(
				ErrorCodes.ADVANCED_OUT_OF_BOUNDRIES.getErrorMsg(),
				currOrient.toString(), "Maximum", "Y-dimension", currCoord.getX(),
				currCoord.getY()));
	    currCoord.setY(currCoord.getY() + 1);
	    break;
	case S:
	    log.debug("advancing 1 step forward towards South");
	    if ((currCoord.getY() - 1) < 0)
		throw new PositionOutOfBoundiresException(
			ErrorCodes.ADVANCED_OUT_OF_BOUNDRIES.getErrorCode(), String.format(
				ErrorCodes.ADVANCED_OUT_OF_BOUNDRIES.getErrorMsg(),
				currOrient.toString(), "Minimum", "Y-dimension", currCoord.getX(),
				currCoord.getY()));
	    currCoord.setY(currCoord.getY() - 1);
	    break;
	case W:
	    log.debug("advancing 1 step forward towards West");
	    if ((currCoord.getX() - 1) < 0)
		throw new PositionOutOfBoundiresException(
			ErrorCodes.ADVANCED_OUT_OF_BOUNDRIES.getErrorCode(), String.format(
				ErrorCodes.ADVANCED_OUT_OF_BOUNDRIES.getErrorMsg(),
				currOrient.toString(), "Minimum", "X-dimension", currCoord.getX(),
				currCoord.getY()));
	    currCoord.setX(currCoord.getX() - 1);
	    break;
	case E:
	    log.debug("advancing 1 step forward towards East");
	    if ((currCoord.getX() + 1) > gridDimX)
		throw new PositionOutOfBoundiresException(
			ErrorCodes.ADVANCED_OUT_OF_BOUNDRIES.getErrorCode(), String.format(
				ErrorCodes.ADVANCED_OUT_OF_BOUNDRIES.getErrorMsg(),
				currOrient.toString(), "Maximum", "X-dimension", currCoord.getX(),
				currCoord.getY()));
	    currCoord.setX(currCoord.getX() + 1);
	    break;
	default:
	    break;
	}
    }

    /**
     * Method responsible for re-calculating a car's orientation depending on the passed argument,
     * i.e. L or R. Specific handling of what's current left or current right is delegated to the
     * model class itself (i.e. Orientation.java)
     * 
     * @param currOrient
     *            , the current orientation of the car (North, West, East, South)
     * @param c
     *            , the navigation command/step (i.e. Left or Right)
     * @return resulting orientation after re-calculating, or null if invalid
     */
    private Orientation reOrient(Orientation currOrient, char c) {
	switch (c) {
	case 'L':
	    log.debug("reorienting towards left");
	    return currOrient.getLeft();
	case 'R':
	    log.debug("reorienting towards right");
	    return currOrient.getRight();
	default:
	    break;
	}
	return null;
    }
}
