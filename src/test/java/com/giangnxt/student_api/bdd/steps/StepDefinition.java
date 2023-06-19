package com.giangnxt.student_api.bdd.steps;

import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinition {
    @LocalServerPort
    private int port = 8080;

    @When("user wants to query the details for the student with id {long}")
    public void getStudentDetails(Long id) {
        RequestSpecification requestSpecification = RestAssured
                .given()
                .relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");

        Response response = requestSpecification.get(createURLWithPort("/students/" + id));
        response.then().log().all();
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
