package com.RestAssured.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.RestAssured.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC005_DELETE_REQUEST extends TestBase{ 
	
	@BeforeClass
	void getListOfUsers() throws InterruptedException {
		
		logger.info("*****************Started TC005_Delete_First_User*********************");
		//Specify Base URI
		RestAssured.baseURI= "https://reqres.in";
		
		//Request object 
		httpRequest = RestAssured.given();
		
		//Response object
		response = httpRequest.request(Method.GET,"/api/users?page=2");
		
		//First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		//capture id
		int userID = jsonPathEvaluator.get("data[0].id");
		response = httpRequest.request(Method.DELETE,"/api/users/"+userID);
		
		Thread.sleep(3000);
	}
	
	
	@Test
	void checkStatusCode() {
		
		logger.info("*****************Checking Status Code*********************");		

		int statusCode = response.getStatusCode();
		logger.info("Status Code==> "+statusCode);
		Assert.assertEquals(statusCode, 204);
	}
	
	@Test
	void checkResponseTime() {

		logger.info("*****************Checking Response Time*********************");		
		
		long responseTime = response.getTime(); //Getting response time
		logger.info("Response Time is==> "+ responseTime);
		if(responseTime>3000)
			logger.warn("Response Time is greater than 3000");
			
		Assert.assertTrue(responseTime<3000);	
		
	}
	
	@Test
	void checkStatusLine() {
		logger.info("*****************Checking Status Line*********************");
		
		String statusLine=response.getStatusLine();
		logger.info("Status Line is==> "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 204 No Content");
	}	
	
	@Test
	void checkServerType() {
		logger.info("*****************Checking Server Type*********************");
		
		String serverType = response.header("Server");
		logger.info("Server Type is ==> "+serverType);
		Assert.assertEquals(serverType, "cloudflare");
	}
	
	
	
	
	@AfterClass
	void tearDown() {
		logger.info("*****************Finished TC005_Delete_First_User*********************");
	}

}
