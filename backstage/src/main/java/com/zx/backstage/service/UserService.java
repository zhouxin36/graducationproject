package com.zx.backstage.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.api.bean.User;
import com.zx.api.bean.UserExample;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/graducation/UserService")
@FeignClient(name = "persistence")
public interface UserService {

    @PostMapping("/countByExample")
    long countByExample(UserExample example);

    @PostMapping("/deleteByExample")
    int deleteByExample(UserExample example);

    @PostMapping("/deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("id") String id);

    @PostMapping(value = "/insert")
    int insert(User record);

    @PostMapping(value = "/insertSelective")
    int insertSelective(User record);

    @PostMapping(value = "/selectByExample")
    List<User> selectByExample(UserExample example);

    @PostMapping("/selectByPrimaryKey")
    User selectByPrimaryKey(@RequestParam("id") String id);

    @PostMapping("/updateByExampleSelective")
    int updateByExampleSelective(EntityAndExample<User,UserExample> exampleEntityAndExample);

    @PostMapping("/updateByExample")
    int updateByExample(EntityAndExample<User,UserExample> exampleEntityAndExample);

    @PostMapping("/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(User record);

    @PostMapping("/updateByPrimaryKey")
    int updateByPrimaryKey(User record);
}

