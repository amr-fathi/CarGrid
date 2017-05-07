/**
 * 
 */
package com.aaghaffar.project.model;

/**
 * @author Amr Abdel-Ghaffar
 *
 */
public enum Orientation {
    N("North"), S("South"), E("East"), W("West");
    
    Orientation(String value) {
	this.value = value;
    }
    
    private String value;
    
    public String getValue() {
        return value;
    }
    
    @Override
    public String toString() {
	return this.getValue();
    }
    
    public Orientation getRight() {
	switch (this) {
	case N:
	    return E;
	case E:
	    return S;
	case S:
	    return W;
	case W:
	    return N;

	default:
	    return null;
	}
    }
    
    public Orientation getLeft() {
	switch (this) {
	case N:
	    return W;
	case E:
	    return N;
	case S:
	    return E;
	case W:
	    return S;

	default:
	    return null;
	}
    }
}
