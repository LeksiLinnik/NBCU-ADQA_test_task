package com.nbcu.api;

import io.restassured.specification.RequestSpecification;
import lombok.Getter;

public class ApiManager {

    @Getter private RequestSpecification spec;

    public static final String APP_HOST = "https://jsonplaceholder.typicode.com/";

    public ApiManager() {
        spec = Specifications.requestSpecification(APP_HOST);
    }
}
