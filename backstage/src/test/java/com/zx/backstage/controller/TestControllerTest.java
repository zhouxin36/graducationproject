package com.zx.backstage.controller;

import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class TestControllerTest {

    @Before
    public void setUP() throws Exception{
        RestAssured.baseURI = "http://localhost/graducation";
        RestAssured.port = 8101;
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test1() {
        given().when().get("/hehe").then().body("data",is(1));
    }
}