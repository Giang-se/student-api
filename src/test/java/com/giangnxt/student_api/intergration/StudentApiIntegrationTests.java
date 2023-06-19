package com.giangnxt.student_api.intergration;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentApiIntegrationTests {

    @LocalServerPort
    private int port;

    private String studentPostPayload = "{\"id\":1000,\"firstName\":\"Giang\",\"lastName\":\"NXT\",\"studentClass\":\"1 A\"}";
    private String studentPutPayload = "{\"id\":1000,\"firstName\":\"Giang\",\"lastName\":\"NXT\",\"studentClass\":\"1 C\"}";

    @Test
    public void testCreateStudent() {
        RequestSpecification requestSpecification = RestAssured
                .given()
                .relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(studentPostPayload);

        Response response = requestSpecification.post(createURLWithPort("/students"));
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
        String responseBody = response.asString();
        assertEquals(responseBody, studentPostPayload);
    }

    @Test
    public void testGetStudent() {
        RequestSpecification requestSpecification = RestAssured
                .given()
                .relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");

        Response response = requestSpecification.get(createURLWithPort("/students/1000"));
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        String responseBody = response.asString();
        assertEquals(responseBody, studentPostPayload);
    }

    @Test
    public void testUpdateStudent() {
        RequestSpecification requestSpecification = RestAssured
                .given()
                .relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(studentPutPayload);

        Response response = requestSpecification.put(createURLWithPort("/students"));
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        String responseBody = response.asString();
        assertEquals(responseBody, studentPutPayload);
    }

    @Test
    public void testDeleteStudent() {
        RequestSpecification requestSpecification = RestAssured
                .given()
                .relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");

        Response response = requestSpecification.delete(createURLWithPort("/students/1000"));
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }


}
