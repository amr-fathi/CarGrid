/**
 * 
 */
package com.aaghaffar.project.validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.aaghaffar.project.exception.ErrorCodes;
import com.aaghaffar.project.exception.InvalidConfigException;
import com.aaghaffar.project.exception.InvalidInputException;
import com.aaghaffar.project.exception.PositionOutOfBoundiresException;
import com.aaghaffar.project.exception.ValidationException;
import com.aaghaffar.project.util.ConfigSource;

/**
 * @author Amr Abdel-Ghaffar
 * This class represents the component responsible for business validations.
 */
@Component
public class DefaultInputValidatorServiceImpl implements InputValidatorService {

    @Autowired
    ConfigSource configSrc;
    
    private final static Logger log = Logger.getLogger(DefaultInputValidatorServiceImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see com.aaghaffar.project.validator.InputValidatorService#isValidInput(java.lang.String)
     */
    @Override
    public boolean isValidInput(String inputStr) throws ValidationException,
	    PositionOutOfBoundiresException {
	log.info("Initializing validation");
	validateNotEmpty(inputStr, ErrorCodes.EMPTY_INPUT_STRING); // Make sure the input string is
								   // not empty/null
	validateInputDelimited(inputStr, configSrc.getDelimiter(), // Make sure the input string is
								   // delimited
		ErrorCodes.INVALID_MAIN_DELIMITER);
	String[] tokens = inputStr.split(configSrc.getDelimiter());
	validateTokens(tokens); // Make sure needed tokens are parsable & valid
	validateNotEmpty(tokens[0], ErrorCodes.EMPTY_COORD_STRING); // Make sure position
								    // coordinates are not
								    // empty/null
	validateInputDelimited(tokens[0], ",", ErrorCodes.INVALID_COORD_DELIMITER); // Make sure
										    // position
										    // coordinates
										    // are delimited
	validateNotEmpty(tokens[1], ErrorCodes.EMPTY_NAV_STRING); // Make sure navigation string is
								  // not empty/null
	String[] coordinates = tokens[0].split(",");
	int xCo;
	int yCo;
	int gridSizeX;
	int gridSizeY;
	try {
	    xCo = validateStringAsInteger(coordinates[0]); // Make sure position coordinates are
							   // integers
	    yCo = validateStringAsInteger(coordinates[1]);
	} catch (NumberFormatException e) {
	    throw new InvalidInputException(ErrorCodes.COORDS_NOT_INTEGERS.getErrorCode(),
		    ErrorCodes.COORDS_NOT_INTEGERS.getErrorMsg() + printUsage(), e);
	}
	try {
	    gridSizeX = validateStringAsInteger(configSrc.getGridDimXstr()); // Make sure
									     // configurable grid
									     // dimensions are
									     // integers
	    gridSizeY = validateStringAsInteger(configSrc.getGridDimYstr());
	} catch (NumberFormatException e) {
	    throw new InvalidConfigException(ErrorCodes.DIM_NOT_INTEGERS.getErrorCode(),
		    ErrorCodes.DIM_NOT_INTEGERS.getErrorMsg() + printUsage(), e);
	}
	validateWithinRange(xCo, yCo, gridSizeX, gridSizeY); // Make sure position coordinates are
							     // within grid range
	validateNavigationChars(tokens[1]); // Make sure navigation characters are valid (i.e. R, F,
	log.info("Validation completed successfully");				    // L)
	return true;
    }

    /**
     * Validate correct set of tokens (0: position coordinates, 1: navigation string) are available
     * @param tokens
     * @throws InvalidInputException
     */
    private void validateTokens(String[] tokens) throws InvalidInputException {
	log.info("Validating input string tokens");
	if (tokens.length == 0) {
	    log.error("Found missing Input String token");
	    throw new InvalidInputException(ErrorCodes.EMPTY_INPUT_STRING.getErrorCode(),
		    ErrorCodes.EMPTY_INPUT_STRING.getErrorMsg() + printUsage());
	}
	else if (tokens.length < 2) {
	    log.error("Found missing navigation String token");
	    throw new InvalidInputException(ErrorCodes.EMPTY_NAV_STRING.getErrorCode(),
		    ErrorCodes.EMPTY_NAV_STRING.getErrorMsg() + printUsage());
	}
    }

    /**
     * Validate input not null or empty
     * @param inputStr
     * @throws InvalidInputException
     */
    private void validateNotEmpty(String inputStr, ErrorCodes errCode) throws InvalidInputException {
	log.info("Validating input string is not empty");
	if (StringUtils.isBlank(inputStr)) {
	    log.error("Found empty input string, resulting: "+errCode.toString());
	    throw new InvalidInputException(errCode.getErrorCode(), errCode.getErrorMsg()
		    + printUsage());
	}
    }

    /**
     * Validate input is delimited
     * @param inputStr, String to validate
     * @param delimiter, delimiter to use for validation
     * @param errCode, corresponding error code if not valid
     * @throws InvalidInputException
     */
    private void validateInputDelimited(String inputStr, String delimiter, ErrorCodes errCode)
	    throws InvalidInputException {
	log.info("Validating input string is delimited");
	if (!inputStr.contains(delimiter)) {
	    log.error("Found invalid delimiter, resulting: "+errCode.toString());
	    throw new InvalidInputException(errCode.getErrorCode(), String.format(
		    errCode.getErrorMsg(), delimiter)
		    + printUsage());
	}
    }

    /**
     * Validate a string is parsable as Integer
     * @param input, input string to parse
     * @return integer value
     * @throws NumberFormatException
     */
    private int validateStringAsInteger(String input) throws NumberFormatException {
	log.info("Validating input is parsable as Integer");
	return Integer.parseInt(input);
    }

    /**
     * Validate coordinates are within grid dimensions
     * @param xCo, X coordinate
     * @param yCo, Y coordinate
     * @param gridSizeX, grid's X dimension
     * @param gridSizeY, grid's Y dimension
     * @throws PositionOutOfBoundiresException
     */
    private void validateWithinRange(int xCo, int yCo, int gridSizeX, int gridSizeY)
	    throws PositionOutOfBoundiresException {
	log.info("Validating coordinates are within range");
	if (xCo < 0 || xCo > gridSizeX) {
	    log.error(String.format("xCo. not within range. xCo: %1$d, grid-X-Dimension: %2$d", xCo, gridSizeX));
	    throw new PositionOutOfBoundiresException(
		    ErrorCodes.POS_OUT_OF_BOUNDRIES.getErrorCode(), String.format(
			    ErrorCodes.POS_OUT_OF_BOUNDRIES.getErrorMsg(), "X", gridSizeX)
			    + printUsage());
	}
	if (yCo < 0 || yCo > gridSizeY) {
	    log.error(String.format("yCo. not within range. yCo: %1$d, grid-Y-Dimension: %2$d", yCo, gridSizeY));
	    throw new PositionOutOfBoundiresException(
		    ErrorCodes.POS_OUT_OF_BOUNDRIES.getErrorCode(), String.format(
			    ErrorCodes.POS_OUT_OF_BOUNDRIES.getErrorMsg(), "Y", gridSizeY)
			    + printUsage());
	}
    }

    /**
     * Validate a navigation string is composed of supported navigation steps, depicted by a Regular expression
     * @param navStr, string to validate
     * @throws InvalidInputException
     */
    private void validateNavigationChars(String navStr) throws InvalidInputException {
	log.info("Validating navigation characters are valid");
	if (!navStr.trim().matches(configSrc.getNavigationCharsRegex())) {
	    log.error("Navigation string contains unsupported characters");
	    throw new InvalidInputException(ErrorCodes.INVALID_NAV_CHARS.getErrorCode(),
		    ErrorCodes.INVALID_NAV_CHARS.getErrorMsg() + printUsage());
	}
    }

    /**
     * Creates a generic usage text
     * @return
     */
    private String printUsage() {
	StringBuilder usageSB = new StringBuilder();
	usageSB.append(String
		.format("%nUsage: input_String (e.g. <current_position>%s<navigation string>). (e.g. 5,5:RFLFRFLF) %n",
			configSrc.getDelimiter()));
	usageSB.append(String.format("current_position: X, Y (e.g. 5,5). %n"));
	usageSB.append(String
		.format("Navigation String: sequence of directions (L (Left), R (Right), F(Forward)) (e.g. RFLFRFLF). %n"));
	return usageSB.toString();
    }

}
