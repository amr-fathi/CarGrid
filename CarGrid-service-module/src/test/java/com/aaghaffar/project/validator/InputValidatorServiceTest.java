/**
 * 
 */
package com.aaghaffar.project.validator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;

import com.aaghaffar.project.ServiceContextTest;
import com.aaghaffar.project.exception.InvalidInputException;
import com.aaghaffar.project.exception.PositionOutOfBoundiresException;
import com.aaghaffar.project.exception.ValidationException;

/**
 * @author Amr Abdel-Ghaffar
 *
 */
public class InputValidatorServiceTest extends ServiceContextTest {

    @Autowired
    private InputValidatorService validatorService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testIsValidInput_Success() throws ValidationException,
	    PositionOutOfBoundiresException {
	String inputStr = "5,5:RFFLFFRRFL";
	boolean isValid = validatorService.isValidInput(inputStr);
	assertEquals(true, isValid);
    }

    @Test
    public void testIsValidInput_Failure_EmptyInputString() throws ValidationException,
	    PositionOutOfBoundiresException {
	String inputStr = "";
	thrown.expect(InvalidInputException.class);
	thrown.expect(hasProperty("errCode", is(1)));
	validatorService.isValidInput(inputStr);
    }

    @Test
    public void testIsValidInput_Failure_EmptyInputString2() throws ValidationException,
	    PositionOutOfBoundiresException {
	String inputStr = ":";
	thrown.expect(InvalidInputException.class);
	thrown.expect(hasProperty("errCode", is(1)));
	validatorService.isValidInput(inputStr);
    }

    @Test
    public void testIsValidInput_Failure_MissingMainDelimiter() throws ValidationException,
	    PositionOutOfBoundiresException {
	thrown.expect(InvalidInputException.class);
	thrown.expect(hasProperty("errCode", is(2)));
	String inputStr = "5,5RFFLFFRRFL";
	validatorService.isValidInput(inputStr);
    }

    @Test
    public void testIsValidInput_Failure_InvalidMainDelimiter() throws ValidationException,
	    PositionOutOfBoundiresException {
	thrown.expect(InvalidInputException.class);
	thrown.expect(hasProperty("errCode", is(2)));
	String inputStr = "5,5;RFFLFFRRFL";
	validatorService.isValidInput(inputStr);
    }

    @Test
    public void testIsValidInput_Failure_MissingCoordDelimiter() throws ValidationException,
	    PositionOutOfBoundiresException {
	thrown.expect(InvalidInputException.class);
	thrown.expect(hasProperty("errCode", is(3)));
	String inputStr = "55:RFFLFFRRFL";
	validatorService.isValidInput(inputStr);
    }

    @Test
    public void testIsValidInput_Failure_InvalidCoordDelimiter() throws ValidationException,
	    PositionOutOfBoundiresException {
	thrown.expect(InvalidInputException.class);
	thrown.expect(hasProperty("errCode", is(3)));
	String inputStr = "5'5:RFFLFFRRFL";
	validatorService.isValidInput(inputStr);
    }

    @Test
    public void testIsValidInput_Failure_EmptyCoordString() throws ValidationException,
	    PositionOutOfBoundiresException {
	thrown.expect(InvalidInputException.class);
	thrown.expect(hasProperty("errCode", is(5)));
	String inputStr = ":RFFLFFRRFL";
	validatorService.isValidInput(inputStr);
    }

    @Test
    public void testIsValidInput_Failure_EmptyNavigationString() throws ValidationException,
	    PositionOutOfBoundiresException {
	thrown.expect(InvalidInputException.class);
	thrown.expect(hasProperty("errCode", is(4)));
	String inputStr = "5,5:";
	validatorService.isValidInput(inputStr);
    }

    @Test
    public void testIsValidInput_Failure_CoordNotIntegers() throws ValidationException,
	    PositionOutOfBoundiresException {
	thrown.expect(hasProperty("errCode", is(6)));
	String inputStr = "x,y:RFFLFFRRFL";
	validatorService.isValidInput(inputStr);
    }

    @Test
    public void testIsValidInput_Failure_PositionOutOfBoundries() throws ValidationException,
	    PositionOutOfBoundiresException {
	thrown.expect(PositionOutOfBoundiresException.class);
	thrown.expect(hasProperty("errCode", is(8)));
	String inputStr = "25,25:RFFLFFRRFL";
	validatorService.isValidInput(inputStr);
    }

    @Test
    public void testIsValidInput_Failure_InvalidNavigationChars() throws ValidationException,
	    PositionOutOfBoundiresException {
	thrown.expect(InvalidInputException.class);
	thrown.expect(hasProperty("errCode", is(9)));
	String inputStr = "5,5:RFFYFFARFZ";
	validatorService.isValidInput(inputStr);
    }
}
