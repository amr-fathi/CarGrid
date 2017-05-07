/**
 * 
 */
package com.aaghaffar.project.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.aaghaffar.project.exception.CarGridServiceException;
import com.aaghaffar.project.service.NavigationService;

/**
 * @author Amr Abdel-Ghaffar
 *
 */
@RestController
public class NavigationController {

    @Autowired
    NavigationService navigationService;

    @GetMapping("/navigate/{navStr}")
    public String navigate(@Valid @PathVariable String navStr) throws CarGridServiceException {
	return navigationService.navigate(navStr);
    }

}
