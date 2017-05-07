# CarGrid
This is a sample multi-module spring boot application, driven in a TDD methodology
# 1 Overview
This is a test project for the sake of a Java technical interview. 
This application resembles a very simple multi-moduled spring boot application, and to demonstrate TDD knowledge & skills. Simply the application represents a car grid (with configurable dimensions), upon which the user inputs current position (cartesian coordinates) and a set of navigation commands (R: Right, L: Left, F: Forward). Accordingly the application calculates the target position and returns it to the user.
# 2 Architecture
* The application is divided into 3 main layers:
   * Domain -> CarGrid-models-module: contains the main building blocks of the application; Domain objects
   * Service-> CarGrid-service-modules: represents the business layer, with business logic encapsulated into reusable services. This layer is responsible for:
		1. Validating user inputs.
		2. Mapping between DTOs (String) & Domain objects
		3. Calculating the target position after applying navigation
   * Integration/Interface-> CarGrid-rest-api-module : This layer represents a presentation/integration layer, taking the form of a RESTful API to interact with the user and present results. It relies on the service layer for performing the actual navigation logic and corresponding validations. Basically, this is a RESTful interface exposing only a single API (/navigate).
# 3 Technologies
   * Java 1.8
   * Spring Core
   * Spring REST with Spring Boot
   * Mocking
   * Swagger-ui
   * Jackson
   * Maven
# 4 How to start
* To run from command line, I've added a maven wrapper around the project to facilitate builds without the need to install maven:
	1. After cloning/downloading the project, navigate to open a shell terminal and navigate to CarGrid root directory
	2. Depending on your operating system, choose the appropriate "mvnw" script, and execute:
	```
		mvnw.cmd clean install
	```
	3. Execute: 
	```
		mvnw.cmd -pl CarGrid-rest-api-module spring-boot:run 
	```
		(this will boot up an embedded tomcat server hosting the API layer)
	4. Open a browser, and navigate to: http://localhost:8080 (this will load the generated Swagger API documentation UI, which also provides a test client to use)

	Alternatively, you can import this project in the IDE you wish, and utlize the same maven commands.

