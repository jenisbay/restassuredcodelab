package com.example.restassuredproject.tests;

import com.example.restassuredproject.api.JsonPlaceholderApi;
import com.example.restassuredproject.models.jsonplaceholder.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class JsonPlaceholderTests {

    @BeforeMethod
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
    }

    @AfterMethod
    public void tearDown() {
        RestAssured.reset();
    }

    @Test
    public void testGetPosts() {
        Response response = JsonPlaceholderApi.getPosts();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);

        List<Post> posts = JsonPlaceholderApi.extractPosts(response);

        for (Post post : posts) {
            System.out.println(post);
        }
    }

    @Test
    public void testGetComments() {
        Response response = JsonPlaceholderApi.getComments();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);

        List<Comment> comments = JsonPlaceholderApi.extractComments(response);

        for (Comment comment : comments) {
            System.out.println(comment);
        }
    }

    @Test
    public void testGetAlbums() {
        Response response = JsonPlaceholderApi.getAlbums();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);

        List<Album> albums = JsonPlaceholderApi.extractAlbums(response);

        for (Album album : albums) {
            System.out.println(album);
        }
    }

    @Test
    public void testGetPhotos() {
        Response response = JsonPlaceholderApi.getPhotos();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);

        List<Photo> photos = JsonPlaceholderApi.extractPhotos(response);

        for (Photo photo : photos) {
            System.out.println(photo);
        }
    }

    @Test
    public void testGetTodos() {
        Response response = JsonPlaceholderApi.getTodos();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);

        List<Todo> todos = JsonPlaceholderApi.extractTodos(response);

        for (Todo todo : todos) {
            System.out.println(todo);
        }
    }

    @Test
    public void testGetUsers() {
        Response response = JsonPlaceholderApi.getUsers();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);

        List<User> users = JsonPlaceholderApi.extractUsers(response);

        for (User todo : users) {
            System.out.println(todo);
        }
    }

    @Test
    public void testGetPostById() {
        Response response = JsonPlaceholderApi.getPostById(1);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);

        Post post = JsonPlaceholderApi.extractPost(response);
        System.out.println(post);
    }

    @Test
    public void testGetCommentsOfPost() {
        Response response = JsonPlaceholderApi.getCommentsOfPost(1);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);

        List<Comment> comments = JsonPlaceholderApi.extractComments(response);
        for (Comment comment : comments) {
            System.out.println(comment);
        }

    }

}
