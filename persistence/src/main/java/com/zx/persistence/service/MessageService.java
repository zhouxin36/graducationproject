package com.zx.persistence.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.persistence.bean.Message;
import com.zx.persistence.bean.MessageExample;
import com.zx.persistence.dao.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/MessageService")
public class MessageService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MessageMapper messageMapper;

    @PostMapping("/countByExample")
    public long countByExample(@RequestBody MessageExample example) {
        return messageMapper.countByExample(example);
    }

    @PostMapping("/deleteByExample")
    public int deleteByExample(@RequestBody MessageExample example) {
        return messageMapper.deleteByExample(example);
    }

    @PostMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(String id) {
        return messageMapper.deleteByPrimaryKey(id);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody Message record) {
        return messageMapper.insert(record);
    }

    @PostMapping("/insertSelective")
    public int insertSelective(@RequestBody Message record) {
        return messageMapper.insertSelective(record);
    }

    @PostMapping("/selectByExample")
    public List<Message> selectByExample(@RequestBody MessageExample example) {
        return messageMapper.selectByExample(example);
    }

    @PostMapping("/selectByPrimaryKey")
    public Message selectByPrimaryKey(String id) {
        return messageMapper.selectByPrimaryKey(id);
    }

    @PostMapping("/updateByExampleSelective")
    public int updateByExampleSelective(@RequestBody EntityAndExample<Message, MessageExample> exampleEntityAndExample) {
        return messageMapper.updateByExampleSelective(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByExample")
    public int updateByExample(@RequestBody EntityAndExample<Message, MessageExample> exampleEntityAndExample) {
        return messageMapper.updateByExample(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(@RequestBody Message record) {
        return messageMapper.updateByPrimaryKeySelective(record);
    }

    @PostMapping("/updateByPrimaryKey")
    public int updateByPrimaryKey(@RequestBody Message record) {
        return messageMapper.updateByPrimaryKey(record);
    }
}
