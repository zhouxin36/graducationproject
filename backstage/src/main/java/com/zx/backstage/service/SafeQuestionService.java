package com.zx.backstage.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.api.bean.SafeQuestion;
import com.zx.api.bean.SafeQuestionExample;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/graducation/SafeQuestionService")
@FeignClient(name = "persistence")
public interface SafeQuestionService {

    @PostMapping("/countByExample")
    long countByExample(SafeQuestionExample example);

    @PostMapping("/deleteByExample")
    int deleteByExample(SafeQuestionExample example);

    @PostMapping("/deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("id") String id);

    @PostMapping(value = "/insert")
    int insert(SafeQuestion record);

    @PostMapping(value = "/insertSelective")
    int insertSelective(SafeQuestion record);

    @PostMapping(value = "/selectByExample")
    List<SafeQuestion> selectByExample(SafeQuestionExample example);

    @PostMapping("/selectByPrimaryKey")
    SafeQuestion selectByPrimaryKey(@RequestParam("id") String id);

    @PostMapping("/updateByExampleSelective")
    int updateByExampleSelective(EntityAndExample<SafeQuestion, SafeQuestionExample> exampleEntityAndExample);

    @PostMapping("/updateByExample")
    int updateByExample(EntityAndExample<SafeQuestion, SafeQuestionExample> exampleEntityAndExample);

    @PostMapping("/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SafeQuestion record);

    @PostMapping("/updateByPrimaryKey")
    int updateByPrimaryKey(SafeQuestion record);
}

