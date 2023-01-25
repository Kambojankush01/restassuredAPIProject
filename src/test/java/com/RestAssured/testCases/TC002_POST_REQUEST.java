package com.RestAssured.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.RestAssured.base.TestBase;
import com.RestAssured.utility.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_POST_REQUEST extends TestBase {
	
	String empName = RestUtils.empName();
	String empJob = RestUtils.empJob();
	
	@BeforeClass
	void postDetailsOfUser() throws InterruptedException {
		logger.info("*****************Started TC002_Post_User_Details*********************");
		
		//Specify Base URI	
		RestAssured.baseURI= "https://reqres.in";
		 
		//Request object 
		httpRequest = RestAssured.given();
		
		//Request payload sending along with post request		
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", empName);
		requestParams.put("job", empJob);
		
		httpRequest.header("Content-Type","application/json");
		
		httpRequest.body(requestParams.toJSONString()); // attach above data to request
		
		//Response object
		response = httpRequest.request(Method.POST,"/api/users");		
		
		Thread.sleep(3000);
	}
	
	@Test
	void checkSuccessCode() {
		logger.info("*****************Checking Success Code*********************");		

		String successCode=response.jsonPath().get("name");
		logger.info("Success Code ==> "+successCode);
		Assert.assertEquals(successCode, empName);
		String successCode1=response.jsonPath().get("job");
		Assert.assertEquals(successCode1, empJob);
	}
	
	@Test
	void checkStatusCode() {
		
		logger.info("*****************Checking Status Code*********************");		

		int statusCode = response.getStatusCode();
		logger.info("Status Code==> "+statusCode);
		Assert.assertEquals(statusCode, 201);
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
		Assert.assertEquals(statusLine, "HTTP/1.1 201 Created");
	}
	
	@Test
	void checkContentType() {
		logger.info("*****************Checking Content Type*********************");
		
		String contentType = response.header("Content-Type");
		logger.info("Content type is ==> "+ contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
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
		logger.info("*****************Finished TC002_Post_User_Details*********************");
	}
	
}
