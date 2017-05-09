/**
 * 
 */
package com.aaghaffar.project.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Amr Abdel-Ghaffar
 * JSON representation of navigation result
 */
@JsonInclude
public class NavigationResponse {
    String result;
    
    public NavigationResponse(String result) {
	this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
