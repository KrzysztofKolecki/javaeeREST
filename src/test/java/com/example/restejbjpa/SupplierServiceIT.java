package com.example.restejbjpa;

import com.example.restwsdemo.domain.Supplier;
import com.jayway.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.given;

public class SupplierServiceIT {
    
    private static final String SUPPLIER_NAME = "abc.pl";
    private static final String SUPPLIER_PHONE = "555444333";
    

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/restejbjpademo/api";
    }


    @Test
    public void addSupplier(){
        delete("/supplier/").then().assertThat().statusCode(200);

        Supplier supplier = new Supplier(SUPPLIER_NAME, SUPPLIER_PHONE);

        given().
                contentType(MediaType.APPLICATION_JSON).
                body(supplier).
                when().
                post("/supplier/").then().assertThat().statusCode(201);
    }
    
    @Test
    public void getAllSuppliers(){
        delete("/supplier/").then().assertThat().statusCode(200);

        Supplier supplier = new Supplier(SUPPLIER_NAME, SUPPLIER_PHONE);
        
        given().
		        contentType(MediaType.APPLICATION_JSON).
		        body(supplier).
		        when().
		        post("/supplier/").then().assertThat().statusCode(201);

        given().
                contentType(MediaType.APPLICATION_JSON).
                when().
                get("/supplier/").then().statusCode(200);
        
    }
    
        
    
}