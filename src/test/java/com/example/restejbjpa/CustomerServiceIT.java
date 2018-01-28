package com.example.restejbjpa;

import com.example.restwsdemo.domain.Customer;
import com.jayway.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.given;

public class CustomerServiceIT {
    
    private static final String CUSTOMER_NAME = "Robert";
    private static final String CUSTOMER_SURNAME = "Kubica";
    

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/restejbjpademo/api";
    }


    @Test
    public void addCustomer(){
        delete("/customer/").then().assertThat().statusCode(200);

        Customer customer = new Customer(CUSTOMER_NAME, CUSTOMER_SURNAME);

        given().
                contentType(MediaType.APPLICATION_JSON).
                body(customer).
                when().
                post("/customer/").then().assertThat().statusCode(201);
    }
    
    @Test
    public void getAllCustomers(){
        delete("/customer/").then().assertThat().statusCode(200);

        Customer customer = new Customer(CUSTOMER_NAME, CUSTOMER_SURNAME);
        
        given().
		        contentType(MediaType.APPLICATION_JSON).
		        body(customer).
		        when().
		        post("/customer/").then().assertThat().statusCode(201);

        given().
                contentType(MediaType.APPLICATION_JSON).
                when().
                get("/customer/").then().statusCode(200);
        
    }
    
        
    
}