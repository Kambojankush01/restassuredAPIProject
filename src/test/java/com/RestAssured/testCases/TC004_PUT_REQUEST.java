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

public class TC004_PUT_REQUEST extends TestBase{
	
	String userName = RestUtils.empName();
	String userJob = RestUtils.empJob();
	
	@BeforeClass
	void updateUser() throws InterruptedException {
		logger.info("*****************Started TC004_Update_User_Details*********************");
		
		RestAssured.baseURI = "https://reqres.in";
		
		httpRequest = RestAssured.given();
		
		//JSONObject is a class that represents a simple JSON. we can add key-value pairs using the 
		// put method
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", userName);
		requestParams.put("job", userJob);
		
		httpRequest.header("Content-Type","application/json");
		httpRequest.body(requestParams.toJSONString());
		
		response = httpRequest.request(Method.PUT,"/api/users/"+empID);
		
		Thread.sleep(3000);
	
	}
	
	@Test
	void checkSuccessCode() {
		logger.info("*****************Checking Success Code*********************");		

		String successCode=response.jsonPath().get("name");
		logger.info("Success Code ==> "+successCode);
		Assert.assertEquals(successCode, userName);
		String successCode1=response.jsonPath().get("job");
		Assert.assertEquals(successCode1, userJob);
	}
	
	@Test
	void checkStatusCode() {
		
		logger.info("*****************Checking Status Code*********************");		

		int statusCode = response.getStatusCode();
		logger.info("Status Code==> "+statusCode);
		Assert.assertEquals(statusCode, 200);
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
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
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
		logger.info("*****************Finished TC004_Update_User_Details*********************");
	}
	
	
	
	
	
	
	
	

}
