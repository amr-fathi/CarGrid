package com.aaghaffar.project.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;





import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;

import com.aaghaffar.project.ServiceContextTest;
import com.aaghaffar.project.exception.CarGridServiceException;
import com.aaghaffar.project.exception.PositionOutOfBoundiresException;

public class NavigationServiceTest extends ServiceContextTest{

    @Autowired
    NavigationService mockedNavService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

//    @Before
//    public void setUp() throws Exception {
//	mockedNavService = mock(NavigationService.class);
//	when(mockedNavService.navigate("5,5:RFLFRFLF")).thenReturn("7,7");
//	when(mockedNavService.navigate("6,6:FFLFFLFFLFF")).thenReturn("6,6");
//	when(mockedNavService.navigate("5,5:FLFLFFRFFF")).thenReturn("1,4");
//    }
    
    @Test
    public void testNavigate_Success_1() throws CarGridServiceException {
	String navStr1 = "5,5:RFLFRFLF";
	String result = mockedNavService.navigate(navStr1);
	assertEquals("Position returned as expected", "7,7", result);
    }

    @Test
    public void testNavigate_Success_2() throws CarGridServiceException {
	String navStr2 = "6,6:FFLFFLFFLFF";
	String result = mockedNavService.navigate(navStr2);
	assertEquals("Position returned as expected", "6,6", result);
    }

    @Test
    public void testNavigate_Success_3() throws CarGridServiceException {
	String navStr3 = "5,5:FLFLFFRFFF";
	String result = mockedNavService.navigate(navStr3);
	assertEquals("Position returned as expected", "1,4", result);
    }
    
    @Test
    public void testNavigate_Failure_TargetPositionOutOfBounds() throws CarGridServiceException {
	String navStr3 = "5,14:FFFFF";	
	thrown.expect(CarGridServiceException.class);
	thrown.expectCause(IsInstanceOf.<Throwable>instanceOf(PositionOutOfBoundiresException.class));
	thrown.expect(hasProperty("errCode", is(10)));
	mockedNavService.navigate(navStr3);
    }

}
