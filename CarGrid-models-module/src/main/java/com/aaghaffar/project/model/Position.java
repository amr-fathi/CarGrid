/**
 * 
 */
package com.aaghaffar.project.model;

/**
 * @author Amr Abdel-Ghaffar
 *
 */
public class Position {
    private Orientation o;
    private Coordinate coordinate;
    
    public Position(Orientation o, Coordinate coordinate) {
	super();
	this.o = o;
	this.coordinate = coordinate;
    }

    public Orientation getOrientation() {
        return o;
    }

    public void setOrientation(Orientation o) {
        this.o = o;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public String toString() {
	return this.coordinate.toString();
    }
    
}
