package com.zx.backstage.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.api.bean.Pic;
import com.zx.api.bean.PicExample;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/graducation/PicService")
@FeignClient(name = "persistence")
public interface PicService {

    @PostMapping("/countByExample")
    long countByExample(PicExample example);

    @PostMapping("/deleteByExample")
    int deleteByExample(PicExample example);

    @PostMapping("/deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("id") String id);

    @PostMapping(value = "/insert")
    int insert(Pic record);

    @PostMapping(value = "/insertSelective")
    int insertSelective(Pic record);

    @PostMapping(value = "/selectByExample")
    List<Pic> selectByExample(PicExample example);

    @PostMapping("/selectByPrimaryKey")
    Pic selectByPrimaryKey(@RequestParam("id") String id);

    @PostMapping("/updateByExampleSelective")
    int updateByExampleSelective(EntityAndExample<Pic, PicExample> exampleEntityAndExample);

    @PostMapping("/updateByExample")
    int updateByExample(EntityAndExample<Pic, PicExample> exampleEntityAndExample);

    @PostMapping("/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Pic record);

    @PostMapping("/updateByPrimaryKey")
    int updateByPrimaryKey(Pic record);
}

