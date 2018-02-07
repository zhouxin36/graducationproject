package com.springcloud.stage.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.api.bean.Footmark;
import com.zx.api.bean.FootmarkExample;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/graducation/FootmarkService")
@FeignClient(name = "persistence")
public interface FootmarkService {

    @PostMapping("/countByExample")
    long countByExample(FootmarkExample example);

    @PostMapping("/deleteByExample")
    int deleteByExample(FootmarkExample example);

    @PostMapping("/deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("id") String id);

    @PostMapping(value = "/insert")
    int insert(Footmark record);

    @PostMapping(value = "/insertSelective")
    int insertSelective(Footmark record);

    @PostMapping(value = "/selectByExample")
    List<Footmark> selectByExample(FootmarkExample example);

    @PostMapping("/selectByPrimaryKey")
    Footmark selectByPrimaryKey(@RequestParam("id") String id);

    @PostMapping("/updateByExampleSelective")
    int updateByExampleSelective(EntityAndExample<Footmark, FootmarkExample> exampleEntityAndExample);

    @PostMapping("/updateByExample")
    int updateByExample(EntityAndExample<Footmark, FootmarkExample> exampleEntityAndExample);

    @PostMapping("/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Footmark record);

    @PostMapping("/updateByPrimaryKey")
    int updateByPrimaryKey(Footmark record);
}

