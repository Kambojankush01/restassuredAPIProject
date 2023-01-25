package com.RestAssured.utility;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {

	public static String empName() {
		String generatedString = RandomStringUtils.randomAlphabetic(1);
		return ("John"+generatedString);
	}
	public static String empJob() {
		String generatedString = RandomStringUtils.randomNumeric(2);
		return generatedString;
	}
	
}
