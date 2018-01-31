package com.zx.backstage.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.api.bean.Sorder;
import com.zx.api.bean.SorderExample;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/graducation/SorderService")
@FeignClient(name = "persistence")
public interface SorderService {

    @PostMapping("/countByExample")
    long countByExample(SorderExample example);

    @PostMapping("/deleteByExample")
    int deleteByExample(SorderExample example);

    @PostMapping("/deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("id") String id);

    @PostMapping(value = "/insert")
    int insert(Sorder record);

    @PostMapping(value = "/insertSelective")
    int insertSelective(Sorder record);

    @PostMapping(value = "/selectByExample")
    List<Sorder> selectByExample(SorderExample example);

    @PostMapping("/selectByPrimaryKey")
    Sorder selectByPrimaryKey(@RequestParam("id") String id);

    @PostMapping("/updateByExampleSelective")
    int updateByExampleSelective(EntityAndExample<Sorder, SorderExample> exampleEntityAndExample);

    @PostMapping("/updateByExample")
    int updateByExample(EntityAndExample<Sorder, SorderExample> exampleEntityAndExample);

    @PostMapping("/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Sorder record);

    @PostMapping("/updateByPrimaryKey")
    int updateByPrimaryKey(Sorder record);
}

