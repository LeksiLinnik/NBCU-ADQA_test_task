package com.nbcu.api;

import static java.util.Optional.ofNullable;
import static java.util.function.Predicate.not;

import io.restassured.response.ValidatableResponse;
import java.util.Map;

import static com.nbcu.api.Specifications.withResponseStatus;
import static com.nbcu.api.Specifications.withResponseStatus200;
import static io.restassured.RestAssured.given;

public abstract class ApiService {

    protected ApiManager apiManager;

    public ApiService() {
        apiManager = new ApiManager();
    }

    protected abstract String getEndpoint();

    public ValidatableResponse get() {
        return new Response(
                given().spec(apiManager.getSpec())
                        .get(getEndpoint())
                        .then()
                        .log().all()
        ).spec(withResponseStatus200());
    }

    public ValidatableResponse get(String value) {
        return new Response(
                given().spec(apiManager.getSpec())
                        .get(getEndpoint(value))
                        .then()
                        .log().all()
        ).spec(withResponseStatus200());
    }

    public ValidatableResponse get(String value, String nestedRoute) {
        return new Response(
                given().spec(apiManager.getSpec())
                        .get(getEndpointWithNestedRoute(value, nestedRoute))
                        .then()
                        .log().all()
        ).spec(withResponseStatus200());
    }

    public ValidatableResponse get(Map<String, String> params) {
        return new Response(given()
                .spec(apiManager.getSpec())
                .params(params)
                .get(getEndpoint())
                .then()
        ).spec(withResponseStatus200());
    }

    private String getEndpoint(String value) {
        return ofNullable(value)
                .filter(not(String::isEmpty))
                .map(v -> getEndpoint() + v)
                .orElseGet(this::getEndpoint);
    }

    private String getEndpointWithNestedRoute(String value, String nestedRoute) {
        return ofNullable(value)
                .filter(not(String::isEmpty))
                .map(v -> getEndpoint() + v + "/" + nestedRoute)
                .orElseGet(this::getEndpoint);
    }

    public ValidatableResponse post(String body) {
        return post(body, 201);
    }

    public ValidatableResponse post(String body, int code) {
        return new Response(given()
                .spec(apiManager.getSpec())
                .body(body)
                .post(getEndpoint())
                .then()
                .log().all()
        ).spec(withResponseStatus(code));
    }

    public ValidatableResponse put(String body, String value) {
        return new Response(given()
                .spec(apiManager.getSpec())
                .body(body)
                .put(getEndpoint(value))
                .then()
                .log().all()
        ).spec(withResponseStatus200());
    }

    public ValidatableResponse patch(String body, String value) {
        return new Response(given()
                .spec(apiManager.getSpec())
                .body(body)
                .patch(getEndpoint(value))
                .then()
                .log().all()
        ).spec(withResponseStatus200());
    }

    public ValidatableResponse delete(String value) {
        return new Response(given()
                .spec(apiManager.getSpec())
                .delete(getEndpoint(value))
                .then()
                .log().all()
        ).spec(withResponseStatus200());
    }

}
