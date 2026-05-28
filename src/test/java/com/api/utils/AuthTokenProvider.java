package com.api.utils;

import static io.restassured.RestAssured.*;

import com.api.constants.Roles;
import com.api.request.model.UserCredentials;

import io.restassured.http.ContentType;

public class AuthTokenProvider {
	
	private AuthTokenProvider() {
		
	}
	
	public static String getToken(Roles role) {
		
		UserCredentials userCredentials = null;
		
		if (role == Roles.FD) {
			userCredentials = new UserCredentials("iamfd", "password");
		}
		else if(role == Roles.SUP) {
			userCredentials = new UserCredentials("iamsup", "password");
		}
		else if(role == Roles.ENG) {
			userCredentials = new UserCredentials("iameng", "password");
		}
		else if(role == Roles.QC) {
			userCredentials = new UserCredentials("iamqc", "password");
		}
		
		String token = 
		given()
		  .baseUri(ConfigManager.getProperty("BASE_URI"))
		  .contentType(ContentType.JSON)
		  .body(userCredentials)
		  
		  .when()
		  .post("login")
		  .then()
		  .statusCode(200)
		  .log().ifValidationFails()
		  .extract()
		  .body()
		  .jsonPath()
		  .getString("data.token");
		
		return token;
	}

}
