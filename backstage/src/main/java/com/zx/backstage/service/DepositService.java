package com.zx.backstage.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.api.bean.Deposit;
import com.zx.api.bean.DepositExample;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/graducation/DepositService")
@FeignClient(name = "persistence")
public interface DepositService {

    @PostMapping("/countByExample")
    long countByExample(DepositExample example);

    @PostMapping("/deleteByExample")
    int deleteByExample(DepositExample example);

    @PostMapping("/deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("id") String id);

    @PostMapping(value = "/insert")
    int insert(Deposit record);

    @PostMapping(value = "/insertSelective")
    int insertSelective(Deposit record);

    @PostMapping(value = "/selectByExample")
    List<Deposit> selectByExample(DepositExample example);

    @PostMapping("/selectByPrimaryKey")
    Deposit selectByPrimaryKey(@RequestParam("id") String id);

    @PostMapping("/updateByExampleSelective")
    int updateByExampleSelective(EntityAndExample<Deposit, DepositExample> exampleEntityAndExample);

    @PostMapping("/updateByExample")
    int updateByExample(EntityAndExample<Deposit, DepositExample> exampleEntityAndExample);

    @PostMapping("/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Deposit record);

    @PostMapping("/updateByPrimaryKey")
    int updateByPrimaryKey(Deposit record);
}

