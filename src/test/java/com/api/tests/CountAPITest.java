package com.api.tests;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import static com.api.constants.Roles.*;
import static com.api.utils.AuthTokenProvider.*;

import io.restassured.http.ContentType;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static com.api.utils.ConfigManager.*;

import static io.restassured.RestAssured.*;

public class CountAPITest {

	@Test
	public void verifyCountAPIResponse() {
		
		given()
		  .baseUri(getProperty("BASE_URI"))
		  .contentType(ContentType.JSON)
		  .header("Authorization", getToken(FD))
		  .log().all()
		  
		  .when()
		  .get("/dashboard/count")
		  
		  .then()
		  .log().all()
		  .statusCode(200)
		  .time(lessThan(1000L))
		  .body("message", equalTo("Success"))
		  .body("data", notNullValue())
		  .body("data.size()", equalTo(3))
		  .body("data.count", everyItem(greaterThanOrEqualTo(0)))
		  .body("data.label", everyItem(notNullValue()))
		  .body(matchesJsonSchemaInClasspath("response-schema/countAPIResponseSchema-FD.json"))
		  .body("data.key", containsInAnyOrder("pending_for_delivery", "created_today", "pending_fst_assignment"));

	}
	
	@Test
	public void countAPITestWithMissingToken() {
		
		given()
		  .baseUri(getProperty("BASE_URI"))
		  .contentType(ContentType.JSON)
		  .log().all()
		  
		  .when()
		  .get("/dashboard/count")
		  
		  .then()
		  .log().all()
		  .statusCode(401);
		
	}

}
