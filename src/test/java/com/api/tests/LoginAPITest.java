package com.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import com.api.utils.SpecUtil;

public class LoginAPITest {
	
    @Test
	public void loginAPITest() throws IOException {
    	
    	
		UserCredentials userCreds = new UserCredentials("iamfd", "password");
		
		 given()
		  .spec(SpecUtil.requestSpec(userCreds))
		  
		  .when()
		  .post("login")
		  
		  .then()
		  .spec(SpecUtil.responseSpec_OK())
		  .body("message", equalTo("Success"))
		  .body(matchesJsonSchemaInClasspath("response-schema/loginResponseSchema.json"));
		 
		 
		    

	}

}
