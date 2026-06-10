package com.api.tests;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import static com.api.utils.DateTimeUtil.*;
import com.api.utils.SpecUtil;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class CreateJobAPITest {

	@Test
	public void createJobAPITest() {

		Customer customer = new Customer("Bibhu", "Moh", "9620465999", "", "xyz12@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("C405", "Prestige city", "dommasandra", "Sharjapur",
				"Chambenahalli", "560087", "India", "Karnataka");
		CustomerProduct customerProduct = new CustomerProduct(getTimeDaysAgo(10), "99015215576205",
				"99015215576205", "99015215576205", getTimeDaysAgo(10), 1, 1);
		Problems problems = new Problems(1, "Battery issue");
		List<Problems> problemsList = new ArrayList<Problems>();
		problemsList.add(problems);
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct,
				problemsList);

		given().spec(SpecUtil.requestSpecWithAuth(Roles.FD, createJobPayload)).log().all().when().post("/job/create")
				.then().spec(SpecUtil.responseSpec_OK())
				.body(matchesJsonSchemaInClasspath("response-schema/createJobAPIResponseSchema.json"))
				.body("message", equalTo("Job created successfully. ")).body("data.mst_service_location_id", equalTo(1))
				.body("data.job_number", startsWith("JOB_"));

	}

}
