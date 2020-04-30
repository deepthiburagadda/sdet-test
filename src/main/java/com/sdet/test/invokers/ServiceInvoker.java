package com.sdet.test.invokers;

import io.restassured.response.Response;

public interface ServiceInvoker {
    public Response getServiceResponse(String input);
}
