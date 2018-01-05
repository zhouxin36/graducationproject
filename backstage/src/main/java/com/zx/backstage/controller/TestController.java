package com.zx.backstage.controller;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zx.api.bean.User;
import com.zx.api.bean.UserExample;
import com.zx.backstage.config.ApiResponse;
import com.zx.backstage.config.CaptchaAPI;
import com.zx.backstage.dto.VerifyDTO;
import com.zx.backstage.service.UserService;
import com.zx.backstage.utils.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

@RestController
public class TestController {

    public static final Logger loggger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    UserService userService;

    @HystrixCommand(fallbackMethod = "test3_1")
    @GetMapping("/hehe3")
    public User test3(){
        return userService.selectByPrimaryKey(1);
    }

    public User test3_1(){
        loggger.error("test3 is wrong");
        return new User();
    }
}
