package com.nbcu.api;

import io.restassured.response.ValidatableResponse;

public class ApiException extends RuntimeException {

    public ApiException(ValidatableResponse response, Throwable throwable) {
        super(response.extract().asString(), throwable);
    }
}
