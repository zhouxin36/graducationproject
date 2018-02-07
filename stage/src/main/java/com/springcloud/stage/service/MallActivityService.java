package com.springcloud.stage.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.api.bean.MallActivity;
import com.zx.api.bean.MallActivityExample;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/graducation/MallActivityService")
@FeignClient(name = "persistence")
public interface MallActivityService {

    @PostMapping("/countByExample")
    long countByExample(MallActivityExample example);

    @PostMapping("/deleteByExample")
    int deleteByExample(MallActivityExample example);

    @PostMapping("/deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("id") String id);

    @PostMapping(value = "/insert")
    int insert(MallActivity record);

    @PostMapping(value = "/insertSelective")
    int insertSelective(MallActivity record);

    @PostMapping(value = "/selectByExample")
    List<MallActivity> selectByExample(MallActivityExample example);

    @PostMapping("/selectByPrimaryKey")
    MallActivity selectByPrimaryKey(@RequestParam("id") String id);

    @PostMapping("/updateByExampleSelective")
    int updateByExampleSelective(EntityAndExample<MallActivity, MallActivityExample> exampleEntityAndExample);

    @PostMapping("/updateByExample")
    int updateByExample(EntityAndExample<MallActivity, MallActivityExample> exampleEntityAndExample);

    @PostMapping("/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MallActivity record);

    @PostMapping("/updateByPrimaryKey")
    int updateByPrimaryKey(MallActivity record);
}

