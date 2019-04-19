# Customer Statement Service 

  This project is to process customer statement by validating duplicate entries & accounts validation for the Customers . 
  
  A Service to generate reports based on the input file type uploaded by the user 

## Assumptions :

 * Input file format can be either CSV or XML 
 * Input file will have list of entries of Customer's accounts such as "accountNumber","description","endBalance","mutation","reference","startBalance"
 * A service need to validate Duplicate entries of "Reference" & sum of "EndBalance"
 * Since we used H2- inmem DB for storage of the validated data, We  can validate input file against existing entries in DB.
 * Output report will be CSV or XML file based on the input type & Output will be  a consolidated report from DB . 
 * "Unsupported file format" message will be sent to client if the input type is other than CSV or XML .
 
## Softwares / Technologies Used:
 
  * Eclipse - IDE for Developement 
  * JDK 8 
  * Embedded tomcat
  * Spring Boot 2
  * Junit 4
  * H2 in memory Database
  * Maven 
  * Spring Data JPA
  * OpenCSV for CSV 
  * JAXB for xml 
  * Swagger for API documentation 
  * Spring Boot maven plugin 
  * Sl4J for logging 

## Configurations: 

  #### Sl4J : Application.properties
  - logging.level.org.springframework.web=ERROR
  - logging.level.com.rabobank=INFO
  - logging.file=logs/application.log
  
  #### H2 DB : Application.properties
  - spring.h2.console.enabled=true
  - spring.h2.console.path=/h2
  - spring.datasource.url=jdbc:h2:file:~/test
  - spring.datasource.username=sa
  - spring.datasource.password=
  - spring.datasource.driver-class-name=org.h2.Driver  
  
  #### Junit Test resource file package : src.test.resources
	  
	
  
 ## Build & Deployment :
  1) git clone https://github.com/vineshnagarajan/customer-statement-service.git
  2) cd customer-statement-service/
  3) mvnw spring-boot:run or mvn spring-boot:run (if we want use installed maven)
  4)Application will be started with tomcat
  
 ## Running & Testing :
  As we are using Swagger , we can use swagger rest client for testing out application
  
  1) hit swagger url in browser : http://localhost:8080/swagger-ui.html#!/statement45controller/getValidatedStatementUsingPOST
  
  2) upload input file using choosefile button 
  
  3) click Tryout button
  
  4) ResponseBody will have downloadable report file with name "OutputReport.csv" or "OutputReport.xml" based on input 
  
  5) for Unsupported input file format , below json response will be give 
  ```
  Response Body
	{
	  "description": "UnSupported File Format , File format should be CSV or XML",
	  "error": "Incorrect File Format"
	}
  ```
  
  6) Use H2 Db console url to check data in in-mem database : http://localhost:8080/h2/login.jsp
     -> console will have default connection strings , click connect button 
     -> CUSTOMER_STATEMENTS  is a table name which holds all validated data 
     
  #### run Junit:   
    * mvnw test / mvn test 
    
    ```
	[INFO] Results:
    [INFO] Tests run: 17, Failures: 0, Errors: 0, Skipped: 0
    ```

  