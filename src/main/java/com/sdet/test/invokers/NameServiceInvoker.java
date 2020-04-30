package com.sdet.test.invokers;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class NameServiceInvoker implements ServiceInvoker{

    public Response getServiceResponse(String name) {
        Response res =
                given().contentType("application/json").pathParam("name", name).
                        get("https://restcountries.eu/rest/v2/name/{name}").then().extract().response();
        return res;
    }
}
