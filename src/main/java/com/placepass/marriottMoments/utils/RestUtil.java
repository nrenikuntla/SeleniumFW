package com.placepass.marriottMoments.utils;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

public class RestUtil {
	public int get(String url) {
		Response resp = RestAssured.given().auth().basic("pp-marriott", "pilotMiami").when().get(url);
		int respCode = resp.getStatusCode();
		return respCode;
	}
}
