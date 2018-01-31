package com.zx.backstage.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.api.bean.Forder;
import com.zx.api.bean.ForderExample;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/graducation/ForderService")
@FeignClient(name = "persistence")
public interface ForderService {

    @PostMapping("/countByExample")
    long countByExample(ForderExample example);

    @PostMapping("/deleteByExample")
    int deleteByExample(ForderExample example);

    @PostMapping("/deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("id") String id);

    @PostMapping(value = "/insert")
    int insert(Forder record);

    @PostMapping(value = "/insertSelective")
    int insertSelective(Forder record);

    @PostMapping(value = "/selectByExample")
    List<Forder> selectByExample(ForderExample example);

    @PostMapping("/selectByPrimaryKey")
    Forder selectByPrimaryKey(@RequestParam("id") String id);

    @PostMapping("/updateByExampleSelective")
    int updateByExampleSelective(EntityAndExample<Forder, ForderExample> exampleEntityAndExample);

    @PostMapping("/updateByExample")
    int updateByExample(EntityAndExample<Forder, ForderExample> exampleEntityAndExample);

    @PostMapping("/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Forder record);

    @PostMapping("/updateByPrimaryKey")
    int updateByPrimaryKey(Forder record);
}

