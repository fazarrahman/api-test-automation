# API TEST AUTOMATION

## How to run
### Spring boot rest api 
#### run in the terminal : ./mvnw spring-boot:run
### API Test
#### run in the terminal : ./mvnw clean test

## Step to run the test
1. Run the springboot rest API
2. Modify API endpoint url and path in src/test/resources/config.properties file.
3. Run the API Test
4. To generate the report, run the test from intellij. Then check the result in /test-output/emailable-report.html

## Techstack used
#### API mock : spring boot rest API
#### API Test : rest-assured
#### API Test result reporting : TestNG

### To be platform dependent, this solution (Rest API and API Test framework) can be dockerized
