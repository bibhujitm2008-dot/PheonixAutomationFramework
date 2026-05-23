package com.api.tests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.SpecUtil;

public class CreateJobAPITest {

	@Test
	public void createJobAPITest() {
		
		Customer customer = new Customer("Bibhu", "Moh", "9620465999", "", "xyz12@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("C405", "Prestige city", "dommasandra", "Sharjapur", "Chambenahalli", "560087", "India", "Karnataka");
		CustomerProduct customerProduct = new CustomerProduct("2025-11-05T18:30:00.000Z", "49045213376205", "49045213376205", "49045213376205", "2025-11-05T18:30:00.000Z", 1, 1);
		Problems problems = new Problems(1, "Battery issue");
		Problems[] problemsArray = new Problems[1];
		problemsArray[0] = problems;
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsArray);
		
		 given()
		  .spec(SpecUtil.requestSpecWithAuth(Roles.FD, createJobPayload))
		  .log().all()
		.when()
		  .post("/job/create")
		.then()
		  .spec(SpecUtil.responseSpec_OK());
		  
		  

	}

}
