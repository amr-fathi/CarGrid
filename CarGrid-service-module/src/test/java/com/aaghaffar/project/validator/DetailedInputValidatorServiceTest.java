/**
 * 
 */
package com.aaghaffar.project.validator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

import com.aaghaffar.project.ServiceContextTest;
import com.aaghaffar.project.exception.InvalidConfigException;
import com.aaghaffar.project.exception.PositionOutOfBoundiresException;
import com.aaghaffar.project.exception.ValidationException;

/**
 * @author Amr Abdel-Ghaffar
 *
 */
@TestPropertySource(properties = { "grid.dim.x=w", "grid.dim.y=h" })
public class DetailedInputValidatorServiceTest extends ServiceContextTest {

    @Autowired
    private InputValidatorService validatorService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test(expected = InvalidConfigException.class)
    public void testIsValidInput_Failure_GridDimensionsNotIntegers() throws ValidationException,
	    PositionOutOfBoundiresException {
	String inputStr = "5,5:RFFLFFRRFL";
	validatorService.isValidInput(inputStr);
	thrown.expect(hasProperty("errCode", is(7)));
    }

}
