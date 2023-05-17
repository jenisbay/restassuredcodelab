package com.example.restassuredproject.tests;

import com.example.restassuredproject.api.Specification;
import com.example.restassuredproject.models.*;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Clock;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ReqResTest {

    private static final String BASE_URL = "https://reqres.in/";

    @Test
    public void checkAvatarAndIdTest() {
        Specification.installSpecification(Specification.requestSpec(BASE_URL), Specification.responseSpecOk200());

        List<UserData> users = given()
                .get("api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

//        users.forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
//
//        Assert.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));

        for (UserData user : users) {
            Assert.assertTrue(user.getAvatar().contains(user.getId().toString()));
        }
    }

    @Test
    public void successSingleUserTest() {
        Specification.installSpecification(Specification.requestSpec(BASE_URL), Specification.responseSpecOk200());

        Integer id = 2;
        String email = "janet.weaver@reqres.in";
        String firstname = "Janet";
        String lastname = "Weaver";
        String avatar = "https://reqres.in/img/faces/2-image.jpg";

        Response response = given()
                .when()
                .get("api/users/2")
                .then()
                .extract().response();

        UserData userData = response.body().jsonPath().getObject("data", UserData.class);

        Assert.assertEquals(id, userData.getId());
        Assert.assertEquals(email, userData.getEmail());
        Assert.assertEquals(firstname, userData.getFirst_name());
        Assert.assertEquals(lastname, userData.getLast_name());
        Assert.assertEquals(avatar, userData.getAvatar());

        Assert.assertEquals(200, response.statusCode());
    }

    @Test
    public void singleUserNotFountTest() {
        Specification.installSpecification(Specification.requestSpec(BASE_URL), Specification.responseSpecNotFount404());

        Response response = given()
                .when()
                .get("api/users/23")
                .then()
                .extract().response();

        Assert.assertEquals(404, response.statusCode());
    }

    @Test
    public void listResourceTest() {
        Specification.installSpecification(Specification.requestSpec(BASE_URL), Specification.responseSpecOk200());

        List<Resource> resources = given()
                .get("api/unknown")
                .then()
                .extract().body().jsonPath().getList("data", Resource.class);

        Assert.assertNotNull(resources);

    }

    @Test
    public void singleResourceNotFoundTest() {
        Specification.installSpecification(Specification.requestSpec(BASE_URL), Specification.responseSpecNotFount404());

        Response response = given()
                .get("api/unknown/23")
                .then()
                .extract().response();

        Assert.assertEquals(404, response.statusCode());
    }

    @Test
    public void createTest() {
        Specification.installSpecification(Specification.requestSpec(BASE_URL), Specification.responseSpecOk201());

        String name = "morpheus";
        String job = "leader";

        User user = new User(name, job);
        UserResponse userResponse = given()
                .body(user)
                .post("api/users")
                .then().log().all()
                .extract().body().as(UserResponse.class, ObjectMapperType.GSON);

        String regex = "(\\.[0-9a-zA-Z]*)$";
        String currentTime = Clock.systemUTC().instant().toString().replaceAll(regex, "");

        Assert.assertEquals(name, userResponse.getName());
        Assert.assertEquals(job, userResponse.getJob());
        Assert.assertEquals(currentTime, userResponse.getCreatedAt().replaceAll(regex, ""));
    }

    @Test
    public void updateTest() {
        Specification.installSpecification(Specification.requestSpec(BASE_URL), Specification.responseSpecOk200());

        String name = "morpheus";
        String job = "zion resident";

        User user = new User(name, job);
        UpdateResponse userResponse = given()
                .body(user)
                .put("api/users/2")
                .then().log().all()
                .extract().body().as(UpdateResponse.class, ObjectMapperType.GSON);

        String regex = "(\\.[0-9a-zA-Z]*)$";
        String currentTime = Clock.systemUTC().instant().toString().replaceAll(regex, "");

        Assert.assertEquals(name, userResponse.getName());
        Assert.assertEquals(job, userResponse.getJob());
        Assert.assertEquals(currentTime, userResponse.getUpdatedAt().replaceAll(regex, ""));
    }

    @Test
    public void deleteTest() {
        Specification.installSpecification(Specification.requestSpec(BASE_URL), Specification.responseSpecOk204());

        Response response = given()
                .delete("api/users/2")
                .then().log().all()
                .extract().response();

        Assert.assertEquals(204, response.statusCode());
    }

    @Test
    public void successRegistration() {
        Specification.installSpecification(Specification.requestSpec(BASE_URL), Specification.responseSpecOk200());
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        Register register = new Register("eve.holt@reqres.in", "pistol");

        SuccessRegistration response = given()
                .body(register)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(SuccessRegistration.class, ObjectMapperType.GSON);

        Assert.assertEquals(id, response.getId());
        Assert.assertEquals(token, response.getToken());

    }

    @Test
    public void unSuccessRegistration() {
        Specification.installSpecification(Specification.requestSpec(BASE_URL), Specification.responseSpecError400());

        Register user = new Register("sydney@fife", "");

        UnSuccessRegistration response = given()
                .body(user)
                .post("api/register")
                .then().log().all()
                .extract().as(UnSuccessRegistration.class, ObjectMapperType.GSON);

        Assert.assertEquals("Missing password", response.getError());
    }

    @Test
    public void loginSuccessTest() {
        Specification.installSpecification(Specification.requestSpec(BASE_URL), Specification.responseSpecOk200());

        String email = "eve.holt@reqres.in";
        String password = "cityslicka";
        String token = "QpwL5tke4Pnpja7X4";

        Register user = new Register(email, password);

        SuccessLoginResponse response = given()
                .body(user)
                .post("api/login")
                .then().log().all()
                .extract().body().as(SuccessLoginResponse.class, ObjectMapperType.GSON);

        Assert.assertEquals(token, response.getToken());
    }

    @Test
    public void loginUnSuccessTest(){
        Specification.installSpecification(Specification.requestSpec(BASE_URL), Specification.responseSpecError400());

        String email = "peter@klaven";
        String password = "";
        String token = "QpwL5tke4Pnpja7X4";

        Register user = new Register(email, password);

        UnSuccessLoginResponse response = given()
                .body(user)
                .post("api/login")
                .then().log().all()
                .extract().body().as(UnSuccessLoginResponse.class, ObjectMapperType.GSON);

        Assert.assertEquals("Missing password", response.getError());

    }

    @Test
    public void delayedResponseTest(){
        Specification.installSpecification(Specification.requestSpec(BASE_URL), Specification.responseSpecOk200());

        List<UserData> users = given()
                .when()
                .get("api/users?delay=3")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        Assert.assertNotNull(users);

    }


}
