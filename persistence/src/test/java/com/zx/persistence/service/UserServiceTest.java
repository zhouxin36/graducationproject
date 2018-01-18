package com.zx.persistence.service;

import com.zx.api.bean.User;
import com.zx.api.bean.UserExample;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class UserServiceTest {

    @Before
    public void setUP() throws Exception{
        RestAssured.baseURI = "http://localhost/graducation";
        RestAssured.port = 8091;
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void updateByExampleSelective() {
        User user = new User();
        user.setId(1);
        user.setAge(22);
        given()
                .param("record",user)
                .param("example",new UserExample())
                .when()
                .post("/UserService/updateByExampleSelective")
                .then()
                .body("data",is(1));
    }
}