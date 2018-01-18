package com.zx.persistence.service;

import com.zx.api.bean.User;
import com.zx.api.bean.UserExample;
import com.zx.persistence.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/UserService")
public class UserService {
    @Autowired
    UserMapper userMapper;

    @PostMapping("/selectByExample")
    public List<User> selectByExample(@RequestBody UserExample example){
        System.out.println(example.getOredCriteria().toString());
        return userMapper.selectByExample(example);
    }

    @PostMapping("/countByExample")
    public long countByExample(UserExample example){
        return userMapper.countByExample(example);
    }

    @PostMapping("/deleteByExample")
    public int deleteByExample(UserExample example){
        return userMapper.deleteByExample(example);
    }

    @PostMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(Integer id){
        return userMapper.deleteByPrimaryKey(id);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody User record){
        return userMapper.insert(record);
    }

    @PostMapping("/insertSelective")
    public int insertSelective(User record){
        return userMapper.insertSelective(record);
    }

    @PostMapping("/selectByPrimaryKey")
    public User selectByPrimaryKey(@RequestParam("id") Integer  id){
        return userMapper.selectByPrimaryKey(id);
    }

    @PostMapping("/updateByExampleSelective")
    public int updateByExampleSelective(User record, UserExample example){
        System.out.println("id:"+record.getId()+",age:"+record.getAge()+":::"+example.toString());
        return userMapper.updateByExampleSelective(record,example);
    }

    @PostMapping("/updateByExample")
    public int updateByExample(User record, UserExample example){
        return userMapper.updateByExample(record,example);
    }

    @PostMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(User record){
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @PostMapping("/updateByPrimaryKey")
    public int updateByPrimaryKey(User record){
        return userMapper.updateByPrimaryKey(record);
    }
}
