package com.RestAssured.DDTestCases;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.RestAssured.base.TestBase;
import com.RestAssured.utility.XLUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC008_DDT_POST_REQUEST{
		
	@Test(dataProvider="empdataprovider")
	void postNewEmployees(String ename,String ejob) {
		
		RestAssured.baseURI="https://reqres.in";
		
		RequestSpecification httpRequest = RestAssured.given();
		
		//Creating data/payload to send along with the post request
		JSONObject requestParams= new JSONObject();
		requestParams.put("name",ename);
		requestParams.put("job",ejob);
		
		//Adding the header
		httpRequest.header("Content-Type","application/json");
		
		//Adding the body/payload
		httpRequest.body(requestParams.toJSONString());
		
		//Sending Post Request
		Response response=httpRequest.request(Method.POST,"/api/users");
		
		//Capture response body to perform validations
		String responseBody=response.getBody().asString();
		System.out.println("Response Body is : "+ responseBody);
		
		Assert.assertEquals(responseBody.contains(ename), true);
		Assert.assertEquals(responseBody.contains(ejob), true);
		Assert.assertEquals(response.getStatusCode(), 201);		
		
	}

	
	
	@DataProvider(name="empdataprovider")
	String[][] getEmpData() throws IOException{
		
		//Read data from excel
		String path="./EmpData/EmpData.xlsx";
		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colnum = XLUtils.getCellCount(path, "Sheet1", 1);
		
		String empData[][]= new String[rownum][colnum];
		
		for(int i=1;i<=rownum;i++) {
			for(int j=0;j<colnum;j++) {
				empData[i-1][j]= XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		
//		String empData[][]= {{"abc123","Singer"},{"xys123","Dancer"},{"MNJ","Magician"}};
		return (empData);
	}
}
