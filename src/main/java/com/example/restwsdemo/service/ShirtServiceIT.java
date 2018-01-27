package com.example.restwsdemo.service;

import com.example.restwsdemo.domain.Shirt;
import com.jayway.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.given;

public class ShirtServiceIT {

    private static final String SHIRT_NAME = "Nike air";
    private static final String SHIRT_SIZE = "M";
    private static final String SHIRT_COLOR = "Purple";

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/restwsejbdemo/api";
    }


    @Test
    public void addShirt(){
        delete("/shirt/").then().assertThat().statusCode(200);

        Shirt shirt = new Shirt(SHIRT_NAME, SHIRT_SIZE, SHIRT_COLOR);

        given().
                contentType(MediaType.APPLICATION_JSON).
                body(shirt).
                when().
                post("/shirt/").then().assertThat().statusCode(201);
    }

}