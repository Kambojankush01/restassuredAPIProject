package com.RestAssured.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC007_GET_REQUEST_Authentication {

	
	//@Test
	void Authorization_Test() {
		
		//Specify Base URI
		RestAssured.baseURI= "https://reqres.in/api/users/authentication";
		
		//Basic Authentication
		PreemptiveBasicAuthScheme authScheme= new PreemptiveBasicAuthScheme();
		authScheme.setUserName("abc");
		authScheme.setPassword("420");
		RestAssured.authentication=authScheme;
		
		//Request object 
		RequestSpecification httpRequest = RestAssured.given();
		
		//Response object
		Response response = httpRequest.request(Method.GET,"/");
		
		//Print response in console window
		String responseBody = response.getBody().asString();		
		System.out.println("Response Body is: "+responseBody);
		
		//Status Code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status code is : "+statusCode);
		Assert.assertEquals(statusCode, 200);
				
		
		
		
	}
}
