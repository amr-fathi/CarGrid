/**
 * 
 */
package com.aaghaffar.project.mapper;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.aaghaffar.project.ServiceContextTest;
import com.aaghaffar.project.exception.PositionOutOfBoundiresException;
import com.aaghaffar.project.exception.ValidationException;
import com.aaghaffar.project.model.Coordinate;
import com.aaghaffar.project.model.Orientation;
import com.aaghaffar.project.model.Position;

/**
 * @author Amr Abdel-Ghaffar
 *
 */
public class DataMapperServiceTest extends ServiceContextTest {

    @Autowired
    DataMapperService dataMapperService;

    @Test
    public void testMapInput_Success() throws ValidationException, PositionOutOfBoundiresException {
	String inputStr = "5,5:RFFLFFRRFF";
	Optional<Pair<Position, String>> result = dataMapperService.mapInput(inputStr);
	Position p = new Position(Orientation.N, new Coordinate(5, 5));
	assertEquals(true, result.isPresent());
	assertEquals(p.getOrientation(), result.get().getLeft().getOrientation());
	assertEquals(p.getCoordinate().getX(), result.get().getLeft().getCoordinate().getX());
	assertEquals(p.getCoordinate().getY(), result.get().getLeft().getCoordinate().getY());
	assertEquals(inputStr, result.get().getRight());
    }

    @Test
    public void testMapOutput_Success() {
	Position p = new Position(Orientation.N, new Coordinate(5, 5));
	String result = dataMapperService.mapOutput(p);
	assertEquals("5,5", result);
    }

}
