package com.api.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.api.utils.ConfigManager.*;

import static org.hamcrest.Matchers.*;

import com.api.constants.Roles;
import com.api.pojo.UserCredentials;
import static com.api.utils.AuthTokenProvider.*;

public class SpecUtil {
	
	
	public static RequestSpecification requestSpec() {
		
		RequestSpecification requestSpecification =  new RequestSpecBuilder()
		 .setBaseUri(getProperty("BASE_URI"))
		 .setContentType(ContentType.JSON)
		 .setAccept(ContentType.JSON)
		 .log(LogDetail.URI)
		 .log(LogDetail.METHOD)
		 .log(LogDetail.HEADERS)
		 .log(LogDetail.BODY)
		 .build();
		
		return requestSpecification;
	}
	
public static RequestSpecification requestSpec(Object payload) {
		
		RequestSpecification request =  new RequestSpecBuilder()
		 .setBaseUri(getProperty("BASE_URI"))
		 .setContentType(ContentType.JSON)
		 .setAccept(ContentType.JSON)
		 .setBody(payload)
		 .log(LogDetail.URI)
		 .log(LogDetail.METHOD)
		 .log(LogDetail.HEADERS)
		 .log(LogDetail.BODY)
		 .build();
		
		return request;
	}

public static RequestSpecification requestSpecWithAuth(Roles role) {
	
	RequestSpecification request =  new RequestSpecBuilder()
			 .setBaseUri(getProperty("BASE_URI"))
			 .setContentType(ContentType.JSON)
			 .setAccept(ContentType.JSON)
			 .addHeader("Authorization", getToken(role))
			 .log(LogDetail.URI)
			 .log(LogDetail.METHOD)
			 .log(LogDetail.HEADERS)
			 .log(LogDetail.BODY)
			 .build();
			
			return request;
}

public static ResponseSpecification responseSpec_OK() {
	
	ResponseSpecification responseSpecification = new ResponseSpecBuilder()
	  .expectContentType(ContentType.JSON)
	  .expectStatusCode(200)
	  .expectResponseTime(lessThan(1000L))
	  .log(LogDetail.ALL)
	  .build();
	return responseSpecification;
	
}

public static ResponseSpecification responseSpec_JSON(int statusCode) {
	
	ResponseSpecification responseSpecification = new ResponseSpecBuilder()
	  .expectContentType(ContentType.JSON)
	  .expectStatusCode(statusCode)
	  .expectResponseTime(lessThan(1000L))
	  .log(LogDetail.ALL)
	  .build();
	return responseSpecification;
	
}

public static ResponseSpecification responseSpec_TEXT(int statusCode) {
	
	ResponseSpecification responseSpecification = new ResponseSpecBuilder()
	  .expectStatusCode(statusCode)
	  .expectResponseTime(lessThan(1000L))
	  .log(LogDetail.ALL)
	  .build();
	return responseSpecification;
	
}

}
