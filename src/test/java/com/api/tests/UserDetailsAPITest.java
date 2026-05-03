package com.api.tests;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.constants.Roles;

import static com.api.utils.AuthTokenProvider.*;

import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class UserDetailsAPITest {
	
	@Test
	public void userDetailsAPITest() throws IOException {
		
		
		Header authHeader = new Header("Authorization", getToken(Roles.FD));
		given()
		 .baseUri(getProperty("BASE_URI"))
		 .header(authHeader)
		 .accept(ContentType.JSON)
		 .log().all()
		 .when()
		 .get("userdetails")
		 .then()
		 .log().all()
		 .statusCode(200)
		 .time(lessThan(2000L))
		 .body(matchesJsonSchemaInClasspath("response-schema/userDetailsFDAPIResponseSchema.json"));
	}

}
