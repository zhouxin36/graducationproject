package com.springcloud.stage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    Logger logger = LoggerFactory.getLogger(TestController.class);
    @Value("${file_base_url}")
    String FILE_BASE_URL;

    @Value("${notify_url}")
    String NOTIFY_URL;

    @Value("${return_url}")
    String RETURN_URL;

    @Value("${bank_url}")
    String BANK_URL;
    @RequestMapping("/test")
    public void test(){
        logger.info("------->"+FILE_BASE_URL);
        logger.info("------->"+NOTIFY_URL);
        logger.info("------->"+RETURN_URL);
        logger.info("------->"+BANK_URL);
    }

}
