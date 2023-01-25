package com.RestAssured.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.RestAssured.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_GET_REQUEST extends TestBase {

	
	@BeforeClass
	void getListOfUsers() throws InterruptedException {
		
		logger.info("*****************Started TC001_Get_List_of_User*********************");
		//Specify Base URI
		RestAssured.baseURI= "https://reqres.in";
		
		//Request object 
		httpRequest = RestAssured.given();
		
		//Response object
		response = httpRequest.request(Method.GET,"/api/users?page=2");
		
		Thread.sleep(3000);
	}
	
	
	
	@Test
	void checkResponseBody() {
		
		logger.info("*****************Checking Response Body*********************");		
		
		String responseBody = response.getBody().asString();		
		logger.info("Response Body==> "+responseBody);
		Assert.assertTrue(responseBody!=null);
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
	
	@Test
	void checkContentEncoding() {
		logger.info("*****************Checking Content Encoding*********************");
		
		String  contentEncoding= response.header("Content-Encoding");
		logger.info("Content-Encoding is ==> "+contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
	}
	
	@Test
	void checkContentLength() {
		logger.info("*****************Checking Content Length*********************");
		
		String  contentLength= response.header("Content-Length");
		logger.info("Content-Length is ==> "+contentLength);
		
	}
	
	@Test
	void checkCookies() {
		logger.info("*****************Checking Cookies*********************");
		
		String  cookies= response.getCookie("PHPSESSID");
	}
	
	
	@AfterClass
	void tearDown() {
		logger.info("*****************Finished TC001_Get_List_of_User*********************");
	}
			
	
	
	
}
