package com.zx.backstage.service;

import com.zx.api.bean.User;
import com.zx.api.bean.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/graducation/UserService")
@FeignClient(name = "persistence")
public interface UserService {

    @PostMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(@RequestParam("id") Integer id);

    @PostMapping("/insert")
    public int insert(User record);

    @PostMapping("/insertSelective")
    public int insertSelective(User record);

    @PostMapping("/selectByPrimaryKey")
    public User selectByPrimaryKey(@RequestParam("id") Integer id);

    @PostMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(User record);

    @PostMapping("/updateByPrimaryKey")
    public int updateByPrimaryKey(User record);
}

