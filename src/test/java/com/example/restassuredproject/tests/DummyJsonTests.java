package com.example.restassuredproject.tests;

import com.example.restassuredproject.FileUtils;
import com.example.restassuredproject.api.dummy.Spec;
import com.example.restassuredproject.models.dummy.todo.Data;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.module.jsv.JsonSchemaValidatorSettings;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyData;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

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

        RequestSpecification request = RestAssured.given();
        request.baseUri(BASE_URL);
        request.pathParam("id", 5);

        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(request);

        Response response = request.get("todos/user/{id}");
        ResponseSpecification responseSpecification = request.expect();
        ValidatableResponse validatableResponse = response.then();

        validatableResponse.assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .time(Matchers.lessThan(7000L))
                .header("vary", "Accept-Encoding")
                .body("total", Matchers.equalTo(3))
                .body("todos.id", Matchers.hasItems(19))
                .body("todos.completed", Matchers.everyItem(Matchers.oneOf(true, false)))
                .log().body();


    }

    @Test
    public void testAddNewTodo(ITestContext context) throws JsonProcessingException, JSONException {

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("todo", "ObjectNode Todo");
        objectNode.put("complete", false);
        objectNode.put("userId", 5);

        ObjectNode expectedBody = objectMapper.createObjectNode();
        expectedBody.put("id", 151);
        expectedBody.put("todo", "ObjectNode Todo");
        expectedBody.put("userId", 5);

        ObjectNode expectedBody1 = objectMapper.createObjectNode();
        expectedBody1.put("id", 151);
        expectedBody1.put("todo", "ObjectNode Todo");
        expectedBody1.put("userId", 5);


        RequestSpecification request = RestAssured.given();
        request.baseUri(BASE_URL);
        request.basePath("todos");
        request.contentType(ContentType.JSON);
        request.body(objectNode);

        Response response = request.post("/add");

        ValidatableResponse validatableResponse = response.then();
        validatableResponse
                .assertThat()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(FileUtils.getResourceFile("json_schema.json")))
                .body(Matchers.equalTo(objectMapper.writeValueAsString(expectedBody)));

        ResponseBodyData body = response.body();
        JSONAssert.assertEquals(objectMapper.writeValueAsString(expectedBody1), body.asString(), JSONCompareMode.STRICT_ORDER);
        System.out.println(response.getStatusCode());
        System.out.println(body.asString());
        System.out.println(expectedBody.equals(expectedBody1));
    }

    @Test
    public void testUpdateTodo() {
        RequestSpecification request = RestAssured.given();
        request.baseUri(BASE_URL);
        request.basePath("todos");
        request.contentType(ContentType.XML);
        request.body(FileUtils.getResourceFile("update.xml"));

        // To retrieve details from RequestSpecification by using Specification.query() method
        // Examples below
        // queryableRequestSpecification.getBody()
        // queryableRequestSpecification.getHeader()
        // queryableRequestSpecification.getBaseUri()
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(request);

        Response response = request.put("/1");

        ResponseBodyData body = response.getBody();
        System.out.println(body.asString());
    }

    @Test
    public void testData() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);



        //        Data data = new Data();
//        data.setTodo("Todo");
//        data.setCompleted(true);
//        data.setUserId(8);

        String json = """
                {
                "todo": "TODO",
                "completed": false,
                "userId": 5,
                "address": {
                    "street": "Street",
                    "country": "USA"
                }
                }
                """;

//        Data data = objectMapper.readValue(json, Data.class);
//        System.out.println(data.getTodo());
//        System.out.println(data.isCompleted());
//        System.out.println(data.getUserId());

        JsonNode jsonNode = objectMapper.readTree(json);
        System.out.println(jsonNode.at("/address/country").asText());
        System.out.println(jsonNode.at("/address/countries").asBoolean());
//        Iterator<String> fields = jsonNode.fieldNames();
//        while (fields.hasNext()) {
//            String key = fields.next();
//            switch (jsonNode.get(key).getNodeType()){
//                case BOOLEAN -> {
//                    System.out.println(jsonNode.get(key).asBoolean());
//                }
//                case STRING -> {
//                    System.out.println(jsonNode.get(key).asText());
//                }
//                case NUMBER -> {
//                    System.out.println(jsonNode.get(key).asInt());
//                }
//            }
//        }


    }
}
