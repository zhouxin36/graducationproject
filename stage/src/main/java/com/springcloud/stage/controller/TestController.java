package com.springcloud.stage.controller;

import com.springcloud.stage.service.MessageService;
import com.zx.api.bean.Message;
import com.zx.api.bean.MessageExample;
import com.zx.api.utils.MyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Autowired
    MessageService messageService;
    @RequestMapping("/test2")
    public void test2(){
        Message message = new Message();
        message.setMessage("eee");
        message.setId(MyUtils.getUUID());
        messageService.insert(message);

    }

    @RequestMapping("/test3")
    public void test3(){
        MessageExample messageExample = new MessageExample();
        MessageExample.Criteria criteria = messageExample.createCriteria();
        criteria.andMessageEqualTo("eeee");
        List<Message> messages = messageService.selectByExample(messageExample);
        logger.info("--------->"+messages);

    }

}
