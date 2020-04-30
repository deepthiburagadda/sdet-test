package com.sdet.test.invokers;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CodeServiceInvoker implements ServiceInvoker{

    public Response getServiceResponse(String code) {
        Response res =
                given().contentType("application/json").pathParam("code", code).
                        get("https://restcountries.eu/rest/v2/alpha/{code}").then().extract().response();
        return res;
    }
}
