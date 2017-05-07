/**
 * 
 */
package com.aaghaffar.project.api;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Amr Abdel-Ghaffar
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApiApplicationContext.class)
@ContextConfiguration
@WebAppConfiguration
public abstract class ApiApplicationContextTest {

    @Autowired
    protected WebApplicationContext wac;
    protected MockMvc mockMvc;

    @Before
    public void setup() {
	mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
}
