package com.zx.persistence.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.persistence.bean.User;
import com.zx.persistence.bean.UserExample;
import com.zx.persistence.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/UserService")
public class UserService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/countByExample")
    public long countByExample(@RequestBody UserExample example) {
        return userMapper.countByExample(example);
    }

    @PostMapping("/deleteByExample")
    public int deleteByExample(@RequestBody UserExample example) {
        return userMapper.deleteByExample(example);
    }

    @PostMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(String id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody User record) {
        return userMapper.insert(record);
    }

    @PostMapping("/insertSelective")
    public int insertSelective(@RequestBody User record) {
        return userMapper.insertSelective(record);
    }

    @PostMapping("/selectByExample")
    public List<User> selectByExample(@RequestBody UserExample example) {
        return userMapper.selectByExample(example);
    }

    @PostMapping("/selectByPrimaryKey")
    public User selectByPrimaryKey(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @PostMapping("/updateByExampleSelective")
    public int updateByExampleSelective(@RequestBody EntityAndExample<User, UserExample> exampleEntityAndExample) {
        return userMapper.updateByExampleSelective(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByExample")
    public int updateByExample(@RequestBody EntityAndExample<User, UserExample> exampleEntityAndExample) {
        return userMapper.updateByExample(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(@RequestBody User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @PostMapping("/updateByPrimaryKey")
    public int updateByPrimaryKey(@RequestBody User record) {
        return userMapper.updateByPrimaryKey(record);
    }
}
