package com.RestAssured.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC006_GET_REQUEST {

	
	@Test
	void getListOfUsersAll_Nodes() {
		
		//Specify Base URI
		RestAssured.baseURI= "https://reqres.in";
		
		//Request object 
		RequestSpecification httpRequest = RestAssured.given();
		
		//Response object
		Response response = httpRequest.request(Method.GET,"/api/users/2");
		
		JsonPath jsonpath = response.jsonPath();
		
		System.out.println(jsonpath.get("data.first_name").toString());
		System.out.println(jsonpath.get("data.last_name").toString());
		System.out.println(jsonpath.get("data.avatar").toString());
		System.out.println(jsonpath.get("support.url").toString());
		System.out.println(jsonpath.get("support.text").toString());
		
		Assert.assertEquals(jsonpath.get("data.first_name").toString(), "Janet");
		
		
		
		
		
	}
}
