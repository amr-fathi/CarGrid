/**
 * 
 */
package com.aaghaffar.project.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.aaghaffar.project.api.util.LoggingInterceptor;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Amr Abdel-Ghaffar
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.aaghaffar.project" })
@EnableSwagger2
@SpringBootApplication
public class ApiApplicationContext extends WebMvcConfigurerAdapter {

    /**
     * @param args
     */
    public static void main(String[] args) {
	SpringApplication.run(ApiApplicationContext.class, args);
    }
    

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public Docket docket() {
	return new Docket(DocumentationType.SWAGGER_2).select()
		.apis(RequestHandlerSelectors.basePackage(getClass().getPackage().getName()))
		.paths(PathSelectors.any()).build().apiInfo(generateApiInfo());
    }

    private ApiInfo generateApiInfo() {
	return new ApiInfo("Build Restful Web service",
		"This service to show how create Restful Web service using Spring Boot and MVC ",
		"Version 1.0 - mw", "urn:tos", "amr.fathi85@gmail.com", "Apache 2.0",
		"http://www.apache.org/licenses/LICENSE-2.0");
    }
}
