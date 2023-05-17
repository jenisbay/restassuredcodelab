package com.example.restassuredproject.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specification {

    public static RequestSpecification requestSpec(String url){
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification responseSpecOk200(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    public static ResponseSpecification responseSpecError400(){
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
    }

    public static ResponseSpecification responseSpecNotFount404(){
        return new ResponseSpecBuilder()
                .expectStatusCode(404)
                .build();
    }


    public static void installSpecification(RequestSpecification request, ResponseSpecification response){
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }

    public static ResponseSpecification responseSpecOk201() {
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .build();
    }

    public static ResponseSpecification responseSpecOk204() {
        return new ResponseSpecBuilder()
                .expectStatusCode(204)
                .build();
    }
}
