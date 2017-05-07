/**
 * 
 */
package com.aaghaffar.project.api.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.aaghaffar.project.api.ApiApplicationContextTest;

/**
 * @author Amr Abdel-Ghaffar
 *
 */
public class NavigationControllerTest extends ApiApplicationContextTest {

    @Test
    public void testGetNavigation() throws Exception {
	this.mockMvc.perform(get("/navigate/5,5:RFLFRFLF")).andExpect(status().isOk())
		.andDo(MockMvcResultHandlers.print());
    }

}
