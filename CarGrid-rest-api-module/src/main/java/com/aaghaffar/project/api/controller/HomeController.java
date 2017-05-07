/**
 * 
 */
package com.aaghaffar.project.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import springfox.documentation.annotations.ApiIgnore;

/**
 * @author Amr Abdel-Ghaffar
 *
 */
@Controller
@ApiIgnore
public class HomeController {

    @RequestMapping("/")
    public String home() {
	return "redirect:swagger-ui.html";
    }

}
