package com.example.restassuredproject.tests;

import com.example.restassuredproject.api.dummy.Spec;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DummyJsonTests {

    private final static String BASE_URL = "https://dummyjson.com/";

    @Test
    public void testGetAllUsers() {
        given(Spec.requestSpec(BASE_URL), Spec.responseSpecOK())
                .get("users")
                .then().log().ifValidationFails();
    }

    @Test
    public void testGetSingleUser() {
        given(Spec.requestSpec(BASE_URL), Spec.responseSpecOK())
                .get("user")
                .then().log().ifValidationFails();
    }

    @Test
    public void testSearchUser() {
        given(Spec.requestSpec(BASE_URL), Spec.responseSpecOK())
                .get(String.format("users/search?q=%s", "John"))
                .then().log().ifValidationFails();
    }

    @Test
    public void testFilterUsers() {
        given(Spec.requestSpec(BASE_URL), Spec.responseSpecOK())
                .get("users/filter?key=hair.color&value=Brown")
                .then().log().ifValidationFails();
    }

    @Test
    public void testLimitAndSkipUsers() {
        given(Spec.requestSpec(BASE_URL), Spec.responseSpecOK())
                .get("users?limit=5&skip=10&select=firstName,age")
                .then().log().ifValidationFails();
    }

    @Test
    public void testGetCartsOfUserByUserId() {
        given(Spec.requestSpec(BASE_URL), Spec.responseSpecOK())
                .get("users/5/carts")
                .then().log().ifValidationFails();
    }

    @Test
    public void testGetPostsOfUserByUserId() {
        given(Spec.requestSpec(BASE_URL), Spec.responseSpecOK())
                .get("users/5/posts")
                .then().log().ifValidationFails();
    }

    @Test
    public void testGetTodosOfUserByUserId() {
        given(Spec.requestSpec(BASE_URL), Spec.responseSpecOK())
                .get("users/5/todos")
                .then().log().ifValidationFails();
    }

}
