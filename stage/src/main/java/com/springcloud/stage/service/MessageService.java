package com.springcloud.stage.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.api.bean.Message;
import com.zx.api.bean.MessageExample;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/graducation/MessageService")
@FeignClient(name = "persistence")
public interface MessageService {

    @PostMapping("/countByExample")
    long countByExample(MessageExample example);

    @PostMapping("/deleteByExample")
    int deleteByExample(MessageExample example);

    @PostMapping("/deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("id") String id);

    @PostMapping(value = "/insert")
    int insert(Message record);

    @PostMapping(value = "/insertSelective")
    int insertSelective(Message record);

    @PostMapping(value = "/selectByExample")
    List<Message> selectByExample(MessageExample example);

    @PostMapping("/selectByPrimaryKey")
    Message selectByPrimaryKey(@RequestParam("id") String id);

    @PostMapping("/updateByExampleSelective")
    int updateByExampleSelective(EntityAndExample<Message, MessageExample> exampleEntityAndExample);

    @PostMapping("/updateByExample")
    int updateByExample(EntityAndExample<Message, MessageExample> exampleEntityAndExample);

    @PostMapping("/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Message record);

    @PostMapping("/updateByPrimaryKey")
    int updateByPrimaryKey(Message record);
}

