package Stepdefinition;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Addplacepojo;
import pojo.Locationpojo;
import resources.APIResources;
import resources.TestData;
import resources.Utils;

public class Addplace extends Utils{
	ResponseSpecification res;
	RequestSpecification reqs;
	Response response;
	APIResources resourceAPI;
	static String place_ID;
	
	TestData td = new TestData();
	
	@Given("Add place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String Address) throws IOException {
		
		reqs = given().log().all().spec(requestspecification()).body(td.Testpayload(name, language, Address));
	}

	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		res =  new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getresource());
		
		if(method.equalsIgnoreCase("POST"))
		response =  reqs.when().post(resourceAPI.getresource())
				.then().log().all().spec(res).extract().response();
		
		else if(method.equalsIgnoreCase("GET"))
			response =  reqs.when().get(resourceAPI.getresource())
			.then().log().all().spec(res).extract().response();
	}

	@Then("the API got success with status code {int}")
	public void the_api_got_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(),200);
	   
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String string, String string2) {
		String responseString = response.asString();
	    JsonPath js = new JsonPath(responseString);
	    assertEquals(getJsonpath(response,string),string2);
	}
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedname, String resourcename) throws IOException {
	    place_ID = getJsonpath(response,"place_id");
		reqs = given().log().all().spec(requestspecification()).queryParam("place_id", place_ID);
		user_calls_with_http_request(resourcename, "GET");
		String actualname = getJsonpath(response,"name");
		assertEquals(actualname,expectedname);
		System.out.println(actualname);
	}
	
	@Given("Delete place Payload")
	public void delete_place_payload() throws IOException {
		reqs = given().log().all().spec(requestspecification()).body(td.deletepayload(place_ID));
	}
}