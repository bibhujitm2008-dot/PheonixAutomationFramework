package com.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.Test;

import static com.api.constants.Roles.*;
import com.api.utils.SpecUtil;

public class UserDetailsAPITest {
	
	@Test
	public void userDetailsAPITest() throws IOException {
		
		
		given()
		 .spec(SpecUtil.requestSpecWithAuth(FD))
		 .when()
		 .get("userdetails")
		 .then()
		 .spec(SpecUtil.responseSpec_OK())
		 .body(matchesJsonSchemaInClasspath("response-schema/userDetailsFDAPIResponseSchema.json"));
	}

}
