package com.api.tests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;

import io.restassured.http.ContentType;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class LoginAPITest {
	
    @Test
	public void loginAPITest() {
		
		UserCredentials userCreds = new UserCredentials("iamfd", "password");
		
		 given()
		  .baseUri("http://64.227.160.186:9000/v1")
		  .contentType(ContentType.JSON)
		  .accept(ContentType.JSON)
		  .body(userCreds)
		  .log().all()
		  
		  .when()
		  .post("login")
		  
		  .then()
		  .log().all()
		  .statusCode(200)
		  .time(lessThan(2000L))
		  .body("message", equalTo("Success"))
		  .body(matchesJsonSchemaInClasspath("response-schema/loginResponseSchema.json"));
		 
		 
		    

	}

}
