package com.example.restassuredproject.api;

import com.example.restassuredproject.models.jsonplaceholder.*;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

import java.util.List;

public class JsonPlaceholderApi {

    public static Response getPosts() {
        return RestAssured.get("posts");
    }

    public static List<Post> extractPosts(Response response) {
        return response.getBody().as(new TypeRef<>() {
        });
    }

    public static Response getComments() {
        return RestAssured.get("comments");
    }

    public static List<Comment> extractComments(Response response) {
        return response.getBody().as(new TypeRef<>() {
        });
    }

    public static Response getAlbums() {
        return RestAssured.get("albums");
    }

    public static List<Album> extractAlbums(Response response) {
        return response.getBody().as(new TypeRef<>() {
        });
    }

    public static Response getPhotos() {
        return RestAssured.get("photos");
    }

    public static List<Photo> extractPhotos(Response response) {
        return response.getBody().as(new TypeRef<>() {
        });
    }

    public static Response getTodos() {
        return RestAssured.get("todos");
    }

    public static List<Todo> extractTodos(Response response) {
        return response.getBody().as(new TypeRef<>() {
        });
    }

    public static Response getUsers() {
        return RestAssured.get("users");
    }

    public static List<User> extractUsers(Response response) {
        return response.getBody().as(new TypeRef<>() {
        });
    }

    public static Response getPostById(int id) {
        return RestAssured.get(String.format("posts/%d", id));
    }

    public static Post extractPost(Response response) {
        return response.getBody().as(Post.class);
    }

    public static Response getCommentsOfPost(int id) {
        return RestAssured.get(String.format("posts/%d/comments", id));
    }


}
