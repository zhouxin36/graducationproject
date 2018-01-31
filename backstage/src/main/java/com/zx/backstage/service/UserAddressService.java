package com.zx.backstage.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.api.bean.UserAddress;
import com.zx.api.bean.UserAddressExample;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/graducation/UserAddressService")
@FeignClient(name = "persistence")
public interface UserAddressService {

    @PostMapping("/countByExample")
    long countByExample(UserAddressExample example);

    @PostMapping("/deleteByExample")
    int deleteByExample(UserAddressExample example);

    @PostMapping("/deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("id") String id);

    @PostMapping(value = "/insert")
    int insert(UserAddress record);

    @PostMapping(value = "/insertSelective")
    int insertSelective(UserAddress record);

    @PostMapping(value = "/selectByExample")
    List<UserAddress> selectByExample(UserAddressExample example);

    @PostMapping("/selectByPrimaryKey")
    UserAddress selectByPrimaryKey(@RequestParam("id") String id);

    @PostMapping("/updateByExampleSelective")
    int updateByExampleSelective(EntityAndExample<UserAddress, UserAddressExample> exampleEntityAndExample);

    @PostMapping("/updateByExample")
    int updateByExample(EntityAndExample<UserAddress, UserAddressExample> exampleEntityAndExample);

    @PostMapping("/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserAddress record);

    @PostMapping("/updateByPrimaryKey")
    int updateByPrimaryKey(UserAddress record);
}

