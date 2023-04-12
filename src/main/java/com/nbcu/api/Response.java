package com.nbcu.api;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.ResponseSpecification;

public class Response {
    private ValidatableResponse validatableResponse;

    public Response(ValidatableResponse validatableResponse) {
        this.validatableResponse = validatableResponse;
    }

    public ValidatableResponse spec(ResponseSpecification responseSpecification) {
        try {
            return validatableResponse.spec(responseSpecification);
        } catch (Throwable e) {
            throw new ApiException(validatableResponse, e);
        }
    }
}
