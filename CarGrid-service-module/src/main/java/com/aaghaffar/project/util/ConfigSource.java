/**
 * 
 */
package com.aaghaffar.project.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Amr Abdel-Ghaffar
 * A spring bean holding the available configuration properties for the application
 */

@Component
public class ConfigSource {


    @Value("${grid.dim.x}")
    private String gridDimXstr;

    @Value("${grid.dim.y}")
    private String gridDimYstr; 

    @Value("${delimiter}")
    private String delimiter;

    @Value("${navigation.chars.regex}")
    private String navigationCharsRegex;

    public String getGridDimXstr() {
        return gridDimXstr;
    }

    public void setGridDimXstr(String gridDimXstr) {
        this.gridDimXstr = gridDimXstr;
    }

    public String getGridDimYstr() {
        return gridDimYstr;
    }

    public void setGridDimYstr(String gridDimYstr) {
        this.gridDimYstr = gridDimYstr;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getNavigationCharsRegex() {
        return navigationCharsRegex;
    }

    public void setNavigationCharsRegex(String navigationCharsRegex) {
        this.navigationCharsRegex = navigationCharsRegex;
    }
}
