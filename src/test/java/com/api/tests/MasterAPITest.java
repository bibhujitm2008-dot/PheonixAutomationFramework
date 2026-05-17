package com.api.tests;

import static com.api.constants.Roles.FD;
import static com.api.utils.AuthTokenProvider.getToken;
import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.utils.SpecUtil;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class MasterAPITest {

	@Test
	public void masterAPITest() {
		
		given()
		   .spec(SpecUtil.requestSpecWithAuth(FD))
		   
		   .when()
		   .post("/master")
		   
		   .then()
		   .spec(SpecUtil.responseSpec_OK())
		   .body("message", equalTo("Success"))
		   .body("data", notNullValue())
		   .body("data", hasKey("mst_oem"))
		   .body("data", hasKey("mst_model"))
		   .body("$", hasKey("message"))
		   .body("data.mst_oem[0]", hasKey("name"))
		   .body("data.mst_oem.size()", greaterThan(0))
		   .body("data.mst_oem.id", everyItem(notNullValue()))
		   .body(matchesJsonSchemaInClasspath("response-schema/masterAPIResponseSchema-FD.json"));
		   
		              
	}
	
	@Test
	public void invalidTokenMasterAPITest() {
		
		given()
		   .spec(SpecUtil.requestSpec())
		   
		   .when()
		   .post("/master")
		   
		   .then()
		   .spec(SpecUtil.responseSpec_TEXT(401));
	}

}
