package com.example.restejbjpa;

import com.example.restwsdemo.domain.Customer;
import com.example.restwsdemo.domain.Shirt;
import com.example.restwsdemo.domain.Supplier;
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
    
    private static final String CUSTOMER_NAME = "Robert";
    private static final String CUSTOMER_SURNAME = "Kubica";
    
    private static final String SUPPLIER_NAME = "abc.pl";
    private static final String SUPPLIER_PHONE = "555444333";

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/restejbjpademo/api";
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
    
    @Test
    public void getAllShirts(){
        delete("/shirt/").then().assertThat().statusCode(200);

        Shirt shirt = new Shirt(SHIRT_NAME, SHIRT_SIZE, SHIRT_COLOR);
        
        given().
		        contentType(MediaType.APPLICATION_JSON).
		        body(shirt).
		        when().
		        post("/shirt/").then().assertThat().statusCode(201);

        given().
                contentType(MediaType.APPLICATION_JSON).
                when().
                get("/shirt/").then().statusCode(200);
        
    }
    
    @Test
    public void getShirtsBySize(){
        delete("/shirt/").then().assertThat().statusCode(200);

        Shirt shirt = new Shirt(SHIRT_NAME, SHIRT_SIZE, SHIRT_COLOR);
        
        given().
		        contentType(MediaType.APPLICATION_JSON).
		        body(shirt).
		        when().
		        post("/shirt/").then().assertThat().statusCode(201);

        given().
                contentType(MediaType.APPLICATION_JSON).
                when().
                get("/shirt/size/" + SHIRT_SIZE).then().statusCode(200);
        
    }
    
    @Test
    public void getShirtsByCustomer(){
        delete("/shirt/").then().assertThat().statusCode(200);


        Shirt shirt = new Shirt(SHIRT_NAME, SHIRT_SIZE, SHIRT_COLOR);
        Customer customer = new Customer(CUSTOMER_NAME, CUSTOMER_SURNAME);
        Supplier supplier = new Supplier(SUPPLIER_NAME, SUPPLIER_PHONE);
        
        given().
        contentType(MediaType.APPLICATION_JSON).
        body(customer).
        when().
        post("/customer/").then().assertThat().statusCode(201);
        
        given().
        contentType(MediaType.APPLICATION_JSON).
        body(supplier).
        when().
        post("/supplier/").then().assertThat().statusCode(201);
        
        given().
        contentType(MediaType.APPLICATION_JSON).
        body(shirt).
        when().
        post("/shirt/").then().assertThat().statusCode(201);
        
        given().
        contentType(MediaType.APPLICATION_JSON).
        when().
        get("/shirt/relations/").then().statusCode(200);
        
        
        given().
                contentType(MediaType.APPLICATION_JSON).
                when().
                get("/shirt/customer/" + CUSTOMER_SURNAME).then().statusCode(200);
        
    }
    
    
    
}