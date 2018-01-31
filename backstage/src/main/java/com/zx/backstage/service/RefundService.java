package com.zx.backstage.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.api.bean.Refund;
import com.zx.api.bean.RefundExample;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/graducation/RefundService")
@FeignClient(name = "persistence")
public interface RefundService {

    @PostMapping("/countByExample")
    long countByExample(RefundExample example);

    @PostMapping("/deleteByExample")
    int deleteByExample(RefundExample example);

    @PostMapping("/deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("id") String id);

    @PostMapping(value = "/insert")
    int insert(Refund record);

    @PostMapping(value = "/insertSelective")
    int insertSelective(Refund record);

    @PostMapping(value = "/selectByExample")
    List<Refund> selectByExample(RefundExample example);

    @PostMapping("/selectByPrimaryKey")
    Refund selectByPrimaryKey(@RequestParam("id") String id);

    @PostMapping("/updateByExampleSelective")
    int updateByExampleSelective(EntityAndExample<Refund, RefundExample> exampleEntityAndExample);

    @PostMapping("/updateByExample")
    int updateByExample(EntityAndExample<Refund, RefundExample> exampleEntityAndExample);

    @PostMapping("/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Refund record);

    @PostMapping("/updateByPrimaryKey")
    int updateByPrimaryKey(Refund record);
}

