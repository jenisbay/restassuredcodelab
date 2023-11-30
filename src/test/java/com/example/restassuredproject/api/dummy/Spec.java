package com.example.restassuredproject.api.dummy;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;

public class Spec {

    public static RequestSpecification requestSpec(String url){
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification responseSpecOK(){
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_OK)
                .expectResponseTime(Matchers.lessThan(4000L))
                .build();
    }

}
