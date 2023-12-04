package com.example.restassuredproject.tests;

import com.example.restassuredproject.FileUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

public class RestApiExampleTests {

    private static final String BASE_URI = "https://dummy.restapiexample.com";
    private static final String BASE_PATH = "/api/v1";

    @BeforeMethod
    public void setup() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = BASE_PATH;
    }

    @AfterMethod
    public void tearDown() {
        RestAssured.reset();
    }

    @Test
    public void testGetAllEmployees() {

        RestAssured.given()
                .get("/employees")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body(JsonSchemaValidator.matchesJsonSchema(FileUtils.getResourceFile("schemas/get_all_employees.json")))
                .time(Matchers.lessThan(5000L));

    }

    @Test
    public void testGetSingleEmployee() {

        RestAssured.given()
                .pathParam("id", 1)
                .get("/employee/{id}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body(JsonSchemaValidator.matchesJsonSchema(FileUtils.getResourceFile("schemas/get_single_employee.json")))
                .time(Matchers.lessThan(5000L));

    }

    @Test
    public void testCreateEmployee() {

        HashMap<String, Object> employee = new HashMap<>();
        employee.put("name", "test");
        employee.put("salary", 123);
        employee.put("age", 23);

        RestAssured.given()
                .body(employee)
                .post("/create")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body(JsonSchemaValidator.matchesJsonSchema(FileUtils.getResourceFile("schemas/create_employee.json")))
                .time(Matchers.lessThan(5000L))
                .extract()
                .body().asString();

    }

    @Test
    public void testUpdateEmployee() {

        HashMap<String, Object> employee = new HashMap<>();
        employee.put("name", "test");
        employee.put("salary", 123);
        employee.put("age", 23);

        RestAssured.given()
                .body(employee)
                .pathParam("id", 21)
                .put("/update/{id}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body(JsonSchemaValidator.matchesJsonSchema(FileUtils.getResourceFile("schemas/update_employee.json")))
                .time(Matchers.lessThan(5000L));

    }

    @Test
    public void testDeleteEmployee() {

        String body = RestAssured.given()
                .pathParam("id", 21)
                .get("/delete/{id}")
                .then()
                .assertThat()
//                .statusCode(HttpStatus.SC_OK)
//                .contentType(ContentType.JSON)
//                .body(JsonSchemaValidator.matchesJsonSchema(FileUtils.getResourceFile("schemas/delete_employee.json")))
                .time(Matchers.lessThan(5000L))
                .extract().body().asString();

        System.out.println(body);

    }

}
