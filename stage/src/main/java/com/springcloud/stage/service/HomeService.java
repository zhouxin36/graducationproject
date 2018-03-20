package com.springcloud.stage.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.api.bean.Home;
import com.zx.api.bean.HomeExample;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/graducation/HomeService")
@FeignClient(name = "persistence")
public interface HomeService {

    @PostMapping("/countByExample")
    long countByExample(HomeExample example);

    @PostMapping("/deleteByExample")
    int deleteByExample(HomeExample example);

    @PostMapping("/deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("id") String id);

    @PostMapping(value = "/insert")
    int insert(Home record);

    @PostMapping(value = "/insertSelective")
    int insertSelective(Home record);

    @PostMapping(value = "/selectByExample")
    List<Home> selectByExample(HomeExample example);

    @PostMapping("/selectByPrimaryKey")
    Home selectByPrimaryKey(@RequestParam("id") String id);

    @PostMapping("/updateByExampleSelective")
    int updateByExampleSelective(EntityAndExample<Home, HomeExample> exampleEntityAndExample);

    @PostMapping("/updateByExample")
    int updateByExample(EntityAndExample<Home, HomeExample> exampleEntityAndExample);

    @PostMapping("/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Home record);

    @PostMapping("/updateByPrimaryKey")
    int updateByPrimaryKey(Home record);
}

