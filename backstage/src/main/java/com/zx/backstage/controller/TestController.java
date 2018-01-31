package com.zx.backstage.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zx.api.bean.User;
import com.zx.backstage.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    public static final Logger loggger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    UserService userService;

    @HystrixCommand(fallbackMethod = "test3_1")
    @GetMapping("/hehe3")
    public User test3(){
        return userService.selectByPrimaryKey("");
    }

    public User test3_1(){
        loggger.error("test3 is wrong");
        return new User();
    }
}
